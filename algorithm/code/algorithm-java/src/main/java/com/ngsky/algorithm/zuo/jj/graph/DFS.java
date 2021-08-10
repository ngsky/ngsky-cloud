package com.ngsky.algorithm.zuo.jj.graph;

import java.util.*;

/**
 * 深度优先遍历
 */
public class DFS {
    public static void dfs(Node n) {
        if (null == n) return;
        Stack<Node> stack = new Stack<>();
        HashSet<Node> visit = new HashSet<>();
        stack.add(n);
        visit.add(n);
        // 处理业务逻辑
        System.out.println(n);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            for (Node nd : node.nexts) {
                if (!visit.contains(nd)) {  // 如果还为使用过
                    stack.add(node); // 先放父节点,再放子节点
                    stack.add(nd);
                    visit.add(nd); // 标记当前节点已经使用过
                    // 处理业务逻辑，找到一条路径，使用后回退到上一步
                    System.out.println(nd);
                    break;
                }
            }
        }
    }
}
