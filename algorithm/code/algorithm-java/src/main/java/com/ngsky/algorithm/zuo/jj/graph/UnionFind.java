//package com.ngsky.algorithm.zuo.jj.graph;
//import java.util.*;
//
///**
// * 并查集
// */
//public class UnionFind {
//   public static class Node<V> {
//      V value;
//      public Node(V v){
//         value = v;
//      }
//   }
//
//   public static class UnionSet<V>{
//      public HashMap<V, Node<V>> nodes; // 值对应节点
//      public HashMap<Node<V>, Node<V>> parents; // 父子节点关系
//      public HashMap<Node<V>, Integer> sizeMap; // 和当前节点在同一个集合中的节点数量
//      // 通过给定列表值创建并查集
//      public UnionSet(List<V> values){
//          nodes = new HashMap<>();
//          parents = new HashMap<>();
//          sizeMap = new HashMap<>();
////        for(V v: values) {
////           Node<V> node = new Node<>(v);
////           nodes.put(node, v);
////           parents.put(node, node); // 暂且认为当前节点的父节点为自己
////           sizeMap.put(node, 1);   // 暂且认为自己独立成为一个集合
////        }
//      }
//
//      // 找到当前节点的父节点
//      public Node<V> findFather(Node<V> cur){
//        Stack<Node<V>>  path = new Stack<>();
//        while(cur != parents.get(cur)){
//           path.push(cur);
//           cur = parents.get(cur);
//        }
//        while(!path.isEmpty()){
//            parents.put(path.pop(), cur);
//        }
//        return cur;
//      }
//
//      // 判断是否为同一个集合
//      public boolean isSameSet(V a, V b){
//         if(!nodes.containsKey(a) || !nodes.containsKey(b)) {
//             return false;
//         }
//         return findFather(nodes.get(a)  == findFather(nodes.get(b)));
//      }
//
//      public void union(V a, V b){
//          if(!nodes.containsKey(a) || !nodes.containsKey(b)){
//              return;
//          }
//          Node<V> aHead = findFather(nodes.get(a));
//          Node<V> bHead = findFather(nodes.get(b));
//          if(ahead != bHead){
//              int aSetSize = sizeMap.get(aHead);
//              int bSetSize = sizeMap.get(bHead);
//              if(aSetSize >= bSetSize){
//                  parents.put(bHead, aHead);
//                  sizeMap.put(aHead, aSetSize + bSetSize);
//                  sizeMap.remove(bHead);
//              }else {
//                  parents.put(aHead, bHead);
//                  sizeMap.put(bHead, aSetSize + bSetSize);
//                  sizeMap.remove(aHead);
//              }
//          }
//      }
//   }
//}
