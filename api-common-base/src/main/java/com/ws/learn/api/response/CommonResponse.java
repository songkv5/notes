package com.ws.learn.api.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponse<T> implements Serializable {
    private int code = 200;
    private String msg;
    private T data;

    public static <T> CommonResponse<T> responseWith(int code, T data, String msg) {
        CommonResponse<T> result = new CommonResponse<T>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }
}
