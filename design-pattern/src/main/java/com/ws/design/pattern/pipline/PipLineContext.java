package com.ws.design.pattern.pipline;

import lombok.Getter;

/**
 * @Author willis
 * @desc 管道模式上线文，用于在执行过程中传递信息
 * @since 2020年03月29日 19:20
 * T : 上下文传递的对象类型
 */
public class PipLineContext<T> {
    @Getter
    private T data;

    public PipLineContext(T data) {
        this.data = data;
    }
}
