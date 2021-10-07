package com.ngsky.algorithm.zuo.a2021.a10;

import java.util.Comparator;
import java.util.Arrays;
import java.util.TreeMap;

public class A10_05_ChooseWork {

    public static class Job {
        private int hard;  // 工作难度
        private int money; // 工作报酬

        public Job(int hard, int money) {
            this.hard = hard;
            this.money = money;
        }
    }

    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job j1, Job j2) {
            // 比较规则：先按照工作难度从小到达排序，如果工作难度相同则按照工作报酬从大到小排序
            return j1.hard != j2.hard ? j1.hard - j2.hard : j2.money - j1.money;
        }
    }

    // jobs: 工作集合, ability: 每个人的能力
    public static int[] chooseWork(Job[] jobs, int[] ability){
        Arrays.sort(jobs, new JobComparator());
        // 构建有序表：存储每种工作难度中报酬最高的job
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(jobs[0].hard, jobs[0].money);
        Job prev = jobs[0];
        for(int i = 1;i<jobs.length;i++){
            if(jobs[i].hard != prev.hard && jobs[i].money > prev.money){
                prev = jobs[i];
                map.put(prev.hard, prev.money);
            }
        }
        // 找到难度小于等于能力的最大难度，相对应的报酬
        int[] ans = new int[ability.length];
        for(int i = 0;i<ability.length;i++){
            Integer key = map.floorKey(ability[i]);
            ans[i] = key == null ? 0 : map.get(key);
        }
        return ans;
    }

}
