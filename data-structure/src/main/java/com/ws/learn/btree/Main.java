package com.ws.learn.btree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.learn.btree.entity.Node;

import java.util.List;

/**
 * @Author willis
 * @desc
 * @since 2020年02月19日 21:51
 */
public class Main {
    public static void main(String[] args) {
        Node<Integer> root = new Node<Integer>(1);
        Node<Integer> n2 = new Node<Integer>(2);
        Node<Integer> n3 = new Node<Integer>(3);
        Node<Integer> n4 = new Node<Integer>(4);
        Node<Integer> n5 = new Node<Integer>(5);

        n2.setL(n4);
        n2.setR(n5);
        root.setL(n2);
        root.setR(n3);

        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
        binaryTree.setRoot(root);

        List<Integer> pList = binaryTree.preList(root);
        System.out.println("先根=" + JSON.toJSONString(pList, SerializerFeature.PrettyFormat));

        List<Integer> aList = binaryTree.afterList(root);
        System.out.println("后根=" + JSON.toJSONString(aList, SerializerFeature.PrettyFormat));

        List<Integer> mList = binaryTree.midList(root);
        System.out.println("中根=" + JSON.toJSONString(mList, SerializerFeature.PrettyFormat));

        System.out.println("高度=" + binaryTree.length(root));
    }
}
