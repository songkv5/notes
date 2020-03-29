package com.ws.design.pattern.pipline;

import lombok.Setter;

/**
 * @Author willis
 * @desc
 * @since 2020年03月29日 20:01
 */
public abstract class AbstractNode<T, R> implements Node<T, R> {
    @Setter
    private Node<T, ?> next;
    @Override
    public Node next() {
        return next;
    }

    @Override
    public void setNext(Node next) {
        this.next = next;
    }
}
