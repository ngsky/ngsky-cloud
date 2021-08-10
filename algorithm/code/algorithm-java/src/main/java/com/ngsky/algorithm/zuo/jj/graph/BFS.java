package com.ngsky.algorithm.zuo.jj.graph;

import java.util.*;

/**
 * 广度优先遍历
 */
public class BFS {
    public static void bfs(Node n){
        if(null == n) return;
        Queue<Node> queue = new LinkedList<>();  // 利用队列构建BFS
        HashSet<Node> visit = new HashSet<>();   // 标记访问过的节点
        queue.add(n);
        visit.add(n);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            // 处理业务逻辑
            System.out.println(node);
            for(Node nd: node.nexts){
                if(!visit.contains(nd)){
                    queue.add(nd);
                    visit.add(nd);
                }
            }
        }
    }
}
