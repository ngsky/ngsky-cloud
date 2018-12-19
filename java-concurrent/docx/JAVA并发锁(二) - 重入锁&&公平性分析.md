# ReentrantLock 原理分析
## CAS与Unsafe
### Unsafe
该类在 sun.misc.Unsafe 中，是一个final类型的类，是不可继承类，同时类中大部分操作都是native方法,调用本地方法来进行硬件操作。几个常见的方法：

```
// 获取类变量在内存中的偏移地址
public native long staticFieldOffset(Field var1);
// 获取实例变量在内存中的偏移地址
public native long objectFieldOffset(Field var1);
// 获取数组的第一个元素的偏移地址
public native int arrayBaseOffset(Class<?> var1);
// 获取数组中元素的增量地址
public native int arrayIndexScale(Class<?> var1);
// 原子操作比较交换对象的值
public final native boolean compareAndSwapObject(Object var1, long var2, Object var4, Object var5);
// 原子操作比较交换Integer类型的值
public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
// 原子操作比较交换Long类型的值
public final native boolean compareAndSwapLong(Object var1, long var2, long var4, long var6);
```

### CAS[compare and swap], 比较和交换。
在java中整个并发操作都依赖于CAS，CAS在java.util.concurrent[J.U.C]中地位很高，很多并发操作都由CAS来实现。它是一种无锁算法，在不适用锁的情况下，实现多线程之间变量的共享，乐观锁的实现的一种。

CAS有三个操作数：
1.内存值V
2.需要进行比较的值A,(可理解为在没有其它线程操作时从内存中获取到的值)
3.需要更新的值B

CAS比较交换原则：当且仅当A与V的值相等，才会进行更新操作(将B值写入到内存中)，否则会一直循环，进行比较，直到A与V值相等后退出。***比较+更新 是一个原则操作。***
AtomicInteger 类：

```
public class AtomicInteger extends Number implements java.io.Serializable {
    private static final long serialVersionUID = 6214790243416807050L;
    // setup to use Unsafe.compareAndSwapInt for updates
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    // cas中需要比较的值A在内存中的偏移位置
    private static final long valueOffset;
    static {
        try {
            valueOffset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }
    // cas中需要比较的值A，采用volatile 修饰，表示线程间可见。
    private volatile int value;
    ...
}
```

AtomicInteger 类中的 final int addAndGet(int delta) ，更新 Integer值的原子操作：
```
public final int addAndGet(int delta) {
  return unsafe.getAndAddInt(this, valueOffset, delta) + delta;
}
public final int getAndAddInt(Object var1, long var2, int var4) {
  int var5;
  do {
    var5 = this.getIntVolatile(var1, var2);
  } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
    return var5;
}
```
e.g.
声明：以下 T1,T2,T3 时刻是指线程之间的上下文切换时刻。

假设有三个线程Thread1,Thread2,Thread3, 开始时AtomicInteger 中的value值为3，也就是A=3, V=3。
T1时刻，三个线程都没有作任何更新操作，只有Thread1作读操作。
T2时刻，Thread2需要作更新操作，需要更新的值B=4。整个原子操作过程，首先会将A值与内存V值进行比较，发现A=V，那么接下来会将B值写入到内存中，即V=4，那么此时期望比较的值A也等于4。
T3时刻，Thread1也需要进行更新操作，但是发现期望值A！=内存值V，那么不会进行更新操作，此时Thread1会循环进行比较，直到A=V时，将值B写入到内存中，或者经过一段时间返回false。
T4时刻，Thread3进行读操作，不做任何处理，直接将内存值V返回即可。

整个操作过程如下图所示：
![](https://i.imgur.com/hbefZ9G.png)

### CAS 问题
- ABA问题
一个线程在更新之前会进行比较，这没有任何问题，但是在比较之前，如果其它线程将内存值从原来的V=3变成V=4，再变成V=3，那么在需要更新操作的线程中，比较A与V值时，发现没有任何变化，即A==V，那么需要更新的线程会误以为没有更新过，会直接将数据B写入到内存中，但是在内存中确实V值进行过更新。

可通过对内存值V进行版本标记的方式解决。

- 可能造成无限循环，耗时耗内存
在比较A与V时，如果A与V一直不相等，那么cas会无限循环的去比较，直到A==V时，才会退出cas，进行线程的其它操作。造成长时间的消耗内存。