package com.ngsky.algorithm.zuo.jj.graph;

import java.util.*;

/**
 * 图节点
 */
public class Node {
    public int value; // 节点值
    public int in; // 节点入度
    public int out; // 节点出度
    public ArrayList<Node> nexts;// 直接相连的节点
    public ArrayList<Edge> edges;// 直接相连的边
    public Node(int value){
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
