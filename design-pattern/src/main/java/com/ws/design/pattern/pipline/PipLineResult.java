package com.ws.design.pattern.pipline;

import lombok.Data;

/**
 * @Author willis
 * @desc 管道执行结果
 * @since 2020年03月29日 20:31
 */
@Data
public class PipLineResult<T> {
    private T data;
    private String msg;

    public PipLineResult(T data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public PipLineResult() {
    }

    public static <T> PipLineResult<T> successWithData(T data) {
        return new PipLineResult<>(data, "success");
    }
}
