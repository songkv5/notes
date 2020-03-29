package com.ws.design.pattern.pipline;

/**
 * @Author willis
 * @desc
 * @since 2020年03月29日 20:45
 */
public class PipLineExecutors {
    public static <R> PipLineResult<R> start(PipLineHolder pipLineHolder) {
        PipLineContext<?> context = pipLineHolder.getContext();
        Node head = pipLineHolder.getHead();
        if (head == null) {
            System.out.println("起始节点为空");
        }

        PipLineResult<R> result = head.process(context);
        Node crt = head.next();
        // 链式调用
        while(crt != null) {
            result = crt.process(context);
            crt = crt.next();
        }
        return result;
    }
}
