package com.ngsky.algorithm.zuo.jj.graph;

import java.util.*;

/**
 * 图 - 拓扑排序
 *
 * 寻找入度为0的节点，依次加入到队列中，并更新与该节点相连的其它节点的入度
 */
public class TopologySort {
    public static List<Node> topologySort(Graph graph){
       HashMap<Node,Integer> inMap = new HashMap<>(); // key:节点,value:节点剩余的入度
        Queue<Node> zeroQueue = new LinkedList<>(); // 入度为0的节点才能进入
        for(Node node: graph.nodes.values()){
           inMap.put(node, node.in);
           if(node.in == 0){
               zeroQueue.add(node);
           }
        }
        List<Node> ans = new ArrayList<>();
        while(!zeroQueue.isEmpty()){
           Node n = zeroQueue.poll();
           ans.add(n) ;
           // 弹出节点后将该节点指向的其它节点入度减1
            for(Node d: n.nexts){
                inMap.put(d, inMap.get(d) - 1);
                // 如果此时有节点入度为0的，那么也要将之加入到队列中
                if(inMap.get(d) == 0){
                    zeroQueue.add(d);
                }
            }
        }
        return ans;
    }
}
