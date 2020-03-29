package com.ws.design.pattern.pipline;

import lombok.Setter;

import java.util.concurrent.TimeUnit;

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

    protected void mockWasteTime() {
        for (int i = 0; i < 6; i ++) {
            System.out.print(".");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
