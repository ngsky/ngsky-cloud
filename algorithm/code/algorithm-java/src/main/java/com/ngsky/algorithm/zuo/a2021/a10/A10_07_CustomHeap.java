package com.ngsky.algorithm.zuo.a2021.a10;

public class A10_07_CustomHeap {
    public static class MaxHeap {
        private int[] heap;
        private int capacity;
        private int size;
        public MaxHeap(int capacity){
            this.capacity = capacity;
            this.heap = new int[capacity];
        }
        public boolean isEmpty(){
            return size == 0;
        }
        public boolean isFull(){
            return size == capacity;
        }
        // push操作：先将要加入的值挂到堆的最后位置，然后通过这个值与其负节点值进行比较，进行上浮操作
        public void push(int v){
            if(isFull()) throw new RuntimeException("heap is full!");
            heap[size] = v;
            heapInsert(size++);
        }
        // pop操作：弹出堆顶元素，弹出之后需要进行heapify操作，重新找到合适堆顶元素
        public int pop(){
            if(isEmpty()) throw new RuntimeException("heap is empty!");
            int ans = heap[0];
            heap[0] = heap[--size];
            heapify(0, size);
            return ans;
        }

        // 上浮 ni 位置的节点，找到合适的位置
        private void heapInsert(int ci){
           while(heap[ci] > heap[(ci-1)/2]){
               swap(ci, (ci-1)/2);
               ci = (ci-1)/2;
           }
        }
        // 下浮过程
        private void heapify(int ci, int ni) {
           int left = ci * 2 + 1;
           while(left < ni){
               // 如果有有节点，则先比较左右节点的值，找到最大节点，然后再与根节点比较，找到根、左、右中最大的节点位置
               int longest = ((left+1) < ni) ? heap[left+1] > heap[left] ? left + 1 : left : left;
               longest = heap[longest] > heap[ci] ? longest : ci;
               if(longest == ci) break; // 说明当前根节点就是最大的值，无需向下寻找
               swap(longest, ci);
               ci = longest;
               left = ci * 2 + 1;  // 继续向下寻找
           }
        }
        private void swap(int ci, int ni){
            int t = heap[ci];
            heap[ci] = heap[ni];
            heap[ni] = t;
        }
    }

    public static class MinHeap {
        private int[] heap;
        private int capacity;
        private int size;
        public MinHeap(int capacity){
            this.capacity = capacity;
            this.heap = new int[capacity];
        }
        public boolean isEmpty(){
            return size == 0;
        }
        public boolean isFull(){
            return size == capacity;
        }
        public void push(int v){
            if(isFull()) throw new RuntimeException("heap is full!");
            heap[size] = v;
            heapInsert(size++);
        }
        public int pop(){
            if(isEmpty()) throw new RuntimeException("heap is empty!");
            int ans = heap[0];
            heap[0] = heap[--size];
            heapify(0, size);
            return ans;
        }

        // 上浮找到合适位置
        private void heapInsert(int ci){
            while(heap[ci] < heap[(ci-1)/2]){ // 当前节点值小于其父节点的值
               swap(ci, (ci-1)/2);
               ci = (ci-1)/2;
            }
        }
        // 下浮找到堆顶合适元素
        private void heapify(int ci, int ni){
            int left = ci * 2 + 1;
            while(left < ni){
                int shortest = ((left + 1) < ni) ? heap[left+1] < heap[left] ? left+1 : left : left;
                shortest = heap[shortest] < heap[ci] ? shortest : ci;
                if(shortest == ci) break;
                swap(shortest, ci);
                ci = shortest;
                left = ci * 2 + 1;
            }
        }

        private void swap(int ci, int ni){
            int t = heap[ci];
            heap[ci] = heap[ni];
            heap[ni] = t;
        }
    }

    // 对数器 验证算法是否正确
    public static class TestHeap {
        private int[] heap;
        private int capacity;
        private int size;
        private boolean max;
        public TestHeap(int capacity, boolean max){
            this.capacity = capacity;
            this.max = max;
            this.heap = new int[capacity];
        }
        public boolean isEmpty(){
            return size == 0;
        }
        public boolean isFull(){
            return size == capacity;
        }
        public void push(int v){
            if(isFull()) throw new RuntimeException("heap is full!");
            heap[size++] = v;
        }
        public int pop(){
            if(isEmpty()) throw new RuntimeException("heap is empty!");
            int ans = heap[0];
            int idx = 0;
            for(int i = 1;i<size;i++) {
                if((max && heap[i] > ans) || (!max && heap[i] < ans)){
                    ans = heap[i];
                    idx = i;
                }
            }
            swap(idx, --size);
            return ans;
        }
        private void swap(int ci, int ni){
            int t = heap[ci];
            heap[ci] = heap[ni];
            heap[ni] = t;
        }
    }

    public static void main(String[] args){
        System.out.println("test start...");
        int value = 10000;
        int capacity = 1000;
        int testTimes = 1000000;
        for(int i = 0;i<testTimes;i++){
            int curCapacity = (int) (Math.random() * capacity) + 1;
            MaxHeap maxHeap = new MaxHeap(curCapacity);
            MinHeap minHeap = new MinHeap(curCapacity);
            TestHeap testMaxHeap = new TestHeap(curCapacity, true);
            TestHeap testMinHeap = new TestHeap(curCapacity, false);
            int curOpsTimes = (int)(Math.random() * testTimes);
            for(int j = 0;j<curOpsTimes;j++){
                if(maxHeap.isEmpty() != testMaxHeap.isEmpty()){
                    System.out.println("maxHeap Ops!");
                }
                if(maxHeap.isFull() != testMaxHeap.isFull()){
                    System.out.println("maxHeap Ops!");
                }
                // maxHeap
                if(maxHeap.isEmpty()){
                   int curV = (int) (Math.random() * value);
                   maxHeap.push(curV);
                   testMaxHeap.push(curV);
                } else if(maxHeap.isFull()){
                   if(maxHeap.pop() != testMaxHeap.pop()) {
                      System.out.println("maxHeap Ops!");
                   }
                }else {
                   if(Math.random() < 0.5) {
                       int curV = (int) (Math.random() * value);
                       maxHeap.push(curV);
                       testMaxHeap.push(curV);
                   }else {
                       if(maxHeap.pop() != testMaxHeap.pop()) {
                           System.out.println("maxHeap Ops!");
                       }
                   }
                }

                // minHeap
                if(minHeap.isEmpty() != testMinHeap.isEmpty()){
                    System.out.println("minHeap Ops!");
                }
                if(minHeap.isFull() != testMinHeap.isFull()){
                    System.out.println("minHeap Ops!");
                }
                if(minHeap.isEmpty()){
                    int curV = (int) (Math.random() * value);
                    minHeap.push(curV);
                    testMinHeap.push(curV);
                } else if(minHeap.isFull()){
                    if(minHeap.pop() != testMinHeap.pop()){
                        System.out.println("minHeap ops!");
                    }
                }else {
                    if(Math.random() < 0.5){
                        int curV = (int) (Math.random() * value);
                        minHeap.push(curV);
                        testMinHeap.push(curV);
                    } else {
                        if(minHeap.pop() != testMinHeap.pop()){
                            System.out.println("minHeap ops!");
                        }
                    }
                }
            }
        }

        System.out.println("test end!");
    }
}
