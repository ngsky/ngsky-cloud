package com.ngsky.algorithm.zuo;

/**
 * 假设一个固定大小为W的窗口，依次滑过数组arr
 * 返回每一次滑动状态的最大值
 * e.g.
 * input: arr=[4,3,5,4,3,3,6,7] w=3
 * output: [5,5,5,4,6,7]
 */
public class SlideWindow_210906 {

    public static int[] windowMaxVal(int[] arr, int w){
        int len = arr.length;
        if(len < w) return null;
        int[] ans = new int[len-w];
        int idx = 0;
        // 双端队列维护从左往右：大->小 的状态
        int[] queue = new int[w];
        int l = 0, r = 0, s = 0;

        for(int i = 0;i<len;i++){
           //  可以放入元素：1.queue为空 2.queue从尾部开始元素比当前元素大
            while(s > 0 && (arr[queue[s >= w && r == 0 ? w-1 : r-1]] <= arr[i])){  // 尾部弹出元素
               if(s >= w) {
                  r = r == 0 ? w-1 : r-1;
                  s = s-1;
               } else {
                  r = r == 0 ? 0 : r-1;
                  s = s -1;
               }
            }
            // 添加元素,存储下标
            queue[r] = i;
            r = r + 1;
            r = r >= w ? r%w : r;
            s = s + 1;
            // 弹出队头元素，记录当前窗口状态最大值
            if(i >= w-1 && idx < len - w) {
                if(i-w+1 >= queue[l]){
                    ans[idx++] = arr[queue[l++]];
                    l = l >= w ? l % w : l;
                    s = s - 1;
                }else {
                    ans[idx++] = arr[queue[l]];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args){
        SlideWindow_210906 sw = new SlideWindow_210906();
        int[] arr = {4,3,5,4,3,3,6,7};
        int w = 3;
        int[] ans = sw.windowMaxVal(arr, w);

        for(int i = 0;i<ans.length;i++){
            System.out.print(ans[i] + " ");
        }
    }
}
