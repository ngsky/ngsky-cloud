package com.ngsky.algorithm.zuo.jj.graph;

import java.util.*;

public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph(){
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
