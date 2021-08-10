package com.ngsky.algorithm.zuo.jj.graph;

/**
 * 图边
 */
public class Edge {
    private int weight;
    private Node from; // 从哪个节点开始
    private Node to; // 指向哪个节点
    public Edge(int weight, Node from,Node to){
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
