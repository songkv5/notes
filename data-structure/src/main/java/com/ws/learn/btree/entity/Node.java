package com.ws.learn.btree.entity;

import lombok.Data;

/**
 * @Author willis
 * @desc
 * @since 2020年02月19日 19:19
 */
@Data
public class Node<T> {
    /**
     * 数据
     */
    private T data;
    /**
     * 左子树
     */
    private Node<T> l;
    /**
     * 右子树
     */
    private Node<T> r;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> l, Node<T> r) {
        this.data = data;
        this.l = l;
        this.r = r;
    }

    public Node() {
    }
}
