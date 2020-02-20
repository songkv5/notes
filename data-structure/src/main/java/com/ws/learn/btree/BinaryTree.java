package com.ws.learn.btree;

import com.ws.learn.btree.entity.Node;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author willis
 * @desc
 * @since 2020年02月19日 21:18
 */
@Data
public class BinaryTree<T> {
    /** 根节点*/
    private Node<T> root;

    /**
     * 插入左子节点
     * @param pNode 被插入节点的父节点
     * @param node 被插入的节点(是一颗子树)
     */
    public void insertL(Node<T> pNode, Node<T> node) {
        if (pNode == null || node == null) {
            return;
        }
        pNode.setL(node);
    }
    /**
     * 插入右子节点
     * @param pNode 被插入节点的父节点
     * @param node 被插入的节点(是一颗子树)
     */
    public void insertR(Node<T> pNode, Node<T> node) {
        if (pNode == null || node == null) {
            return;
        }
        pNode.setR(node);
    }

    public int length(Node<T> node) {
        if (node.getL() == null && node.getR() == null) {
            return 1;
        } else {
            int ll = length(node.getL());
            int rl = length(node.getR());
            return 1 + (ll > rl ? ll : rl);
        }
    }

    /**
     * 先根遍历二叉树
     * @param node
     * @return
     */
    public List<T> preList(Node<T> node) {
        if (node == null) {
            return (List<T>) Collections.EMPTY_LIST;
        }
        List<T> list = new ArrayList<T>();
        list.add(node.getData());
        if (node.getR() == null && node.getL() == null) {
            return list;
        } else {
            if (node.getL() != null) {
                List<T> lList = preList(node.getL());
                list.addAll(lList);
            }
            if (node.getR() != null) {
                List<T> rList = preList(node.getR());
                list.addAll(rList);
            }
            return list;
        }
    }

    /**
     * 后根遍历二叉树
     * @param node
     * @return
     */
    public List<T> afterList(Node<T> node) {
        if (node == null) {
            return (List<T>) Collections.EMPTY_LIST;
        }
        List<T> list = new ArrayList<T>();
        Node<T> l = node.getL();
        Node<T> r = node.getR();
        if (l == null && r == null) {
            list.add(node.getData());
            return list;
        } else {
            if (l != null) {
                list.addAll(afterList(l));
            }
            if (r != null) {
                list.addAll(afterList(r));
            }
            list.add(node.getData());
            return list;
        }
    }

    /**
     * 中根遍历二叉树
     * @param node
     * @return
     */
    public List<T> midList(Node<T> node) {
        if (node == null) {
            return (List<T>) Collections.EMPTY_LIST;
        }
        List<T> list = new ArrayList<T>();
        Node<T> l = node.getL();
        Node<T> r = node.getR();
        if (l == null && r == null) {
            list.add(node.getData());
            return list;
        } else {
            List<T> ll = midList(l);
            list.addAll(ll);
            list.add(node.getData());
            List<T> rl = midList(r);
            list.addAll(rl);
            return list;
        }
    }


}
