package com.ngsky.algorithm.zuo;

import com.ngsky.algorithm.GenBinaryTree;
import com.ngsky.algorithm.Node;
import java.util.*;

/**
 * 找到二叉树中符合搜索二叉树条件的最大拓扑结构
 */
public class Pro_03_07_02 {
    public static void main(String[]  args){
        int[] arr = {6,1,12,0,3,10,13,-1,-1,-1,-1,4,14,20,16,-1,-1,-1,-1,-1,-1,-1,-1,2,5,11,15};
        Node head = new GenBinaryTree().gen(arr);
        System.out.println(bstTopoSize2(head));
    }

    static class Record{
        int l;
        int r;
        public Record(int l, int r){
            this.l = l;
            this.r = r;
        }
    }

    // 主方法
    public static int bstTopoSize2(Node head){
       Map<Node, Record> map = new HashMap<>();
       return posOrder(head, map);
    }
   // 后序遍历
    public static int posOrder(Node n, Map<Node,Record> map){
       if(null == n)  return 0;
       int ls = posOrder(n.left, map);
       int rs = posOrder(n.right, map);
       modifyMap(n.left, n.v, map, true);
       modifyMap(n.right, n.v, map, false);
       Record lr = map.get(n.left);
       Record rr = map.get(n.right);
       int lbst = null == lr ? 0 : lr.l + lr.r + 1;
       int rbst = null == rr ? 0 : rr.l + rr.r + 1;
       map.put(n, new Record(lbst, rbst));
       return Math.max(lbst+rbst+1, Math.max(ls, rs));
    }

    public static int modifyMap(Node n, int v, Map<Node, Record> map, boolean s){
       if(null == n || !map.containsKey(n)) return 0;
       Record r = map.get(n);
       // 如果左节点大于父节点值，则删除左侧节点贡献值,  如果右侧节点小于父节点值，则删除右侧节点对应的贡献值
       if((s && n.v > v) || (!s && n.v < v)){
           map.remove(n);
           return r.l + r.r + 1;
       }else {
           // 如果是左节点，则考虑右侧边缘，如果是右侧节点，则考虑左侧边缘
           int minus = modifyMap(s ?  n.right:n.left, v, map, s);
           if(s){
               r.r = r.r - minus; // 删除右侧不满足条件的节点数
           }else {
               r.l = r.l - minus; // 删除左侧不满足条件的节点数
           }
           map.put(n, r);
           return minus;
       }
    }
}
