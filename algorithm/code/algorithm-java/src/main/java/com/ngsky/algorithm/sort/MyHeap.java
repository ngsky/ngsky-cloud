package com.ngsky.algorithm.sort;

public class MyHeap {

    public static void main(String[] args){
        // 大根堆
//        MyMaxHeap mmh = new MyMaxHeap(20);
//        for(int i = 0;i<15;i++){
//           mmh.push(i);
//        }
//        for(int i = 0;i<10;i++){
//            System.out.println(mmh.pop());
//        }

        // 小根堆
        MyMinHeap mmh = new MyMinHeap(20);
        for(int i = 0;i<15;i++){
            mmh.push(i);
        }
        for(int i = 0;i<15;i++){
            System.out.println(mmh.pop());
        }
    }

    /**
     * 大根堆
     */
    public static class MyMaxHeap{
        private int[] heap;
        private final int limit;
        private int heapSize;
        public MyMaxHeap(int limit){
            this.heap = new int[limit];
            this.limit = limit;
            this.heapSize = 0;
        }
        public boolean isEmpty(){
            return heapSize == 0;
        }
        public boolean isFull(){
            return heapSize == limit;
        }
        // 添加元素
        public void push(int value){
            if(heapSize == limit) throw new RuntimeException("heap is full!");
            // 首先将元素放到最后位置
            heap[heapSize]  = value;
            // 添加完元素，heapSize 自然应该增长，对元素进行上浮操作
            heapInsert(heap, heapSize++);
        }
        public void heapInsert(int[] arr, int idx){
           // 当idx位置为0或者idx元素无法击败父亲节点，停止上浮
            while(arr[idx] > arr[(idx-1)/2]){
                // 当前节点与父亲节点交换
                swap(arr, idx, (idx-1)/2);
                // 当前节点上浮为父亲节点
                idx = (idx-1)/2;
            }
        }

        // 弹出元素，并且保证剩下的元素也是大根堆
        public int pop(){
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }
        // 下沉操作，如果有左孩子，则将左右孩子进行比较
        public void heapify(int[] arr, int idx, int heapSzie){
            // 当子节点中较大的孩子的值都比自己(arr[idx])小的时候，停止
            int left = 2*idx + 1;
            // 有左孩子才会有可能有右孩子
            while(left < heapSize){
                // right = left + 1
                int largest = arr[left + 1] < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                largest = arr[largest] > arr[idx] ? largest : idx;
                if(largest == idx) break;
                swap(heap, idx, largest);
                idx = largest;
                left = 2 * idx + 1;
            }
        }

        public void swap(int[] arr, int l, int r){
            int t = arr[l];
            arr[l] = arr[r];
            arr[r] = t;
        }
    }

    /**
     * 小根堆
     */
    public static class MyMinHeap{
        private int[] heap;
        private final int limit;
        private int heapSize;
        public MyMinHeap(int limit){
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }
        public boolean isEmpty(){
            return heapSize == 0;
        }
        public boolean isFull(){
            return heapSize == limit;
        }
        // push
        public void push(int value){
            if(heapSize == limit) throw new RuntimeException("heap is full");
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }
        public void heapInsert(int[] arr, int idx){
           while(arr[idx] < arr[(idx-1)/2]) {
               swap(arr, idx, (idx-1)/2);
               idx = (idx-1)/2;
           }
        }

        // pop
        public int pop(){
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        public void heapify(int[] arr, int idx, int heapSize){
            int left = 2*idx + 1;
            while(left < heapSize){
                int smallest = left + 1 < heapSize && arr[left+1]<arr[left]?left+1:left;
                smallest = arr[smallest]<arr[idx]?smallest:idx;
                if(smallest == idx) break;
                swap(arr, smallest, idx);
                idx = smallest;
                left = 2*idx + 1;
            }
        }

        public void swap(int[] arr, int l, int r){
            int t = arr[l];
            arr[l] = arr[r];
            arr[r] = t;
        }
    }
}
