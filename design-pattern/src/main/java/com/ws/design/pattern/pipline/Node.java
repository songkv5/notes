package com.ws.design.pattern.pipline;

/**
 * @Author willis
 * @desc 管道节点抽象，用于执行每个节点的动作
 * @since 2020年03月29日 19:24
 */
public interface Node<T, R> {
    /**
     * 下一个执行节点
     * @return
     */
    Node next();

    /**
     * 设置下个节点
     * @param next
     */
    void setNext(Node next);

    /**
     * 执行当前操作
     * @param pipLineContext
     * @return
     */
    PipLineResult<R> process(PipLineContext<T> pipLineContext);
}
