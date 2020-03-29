package com.ws.design.pattern;

/**
 * @Author willis
 * @desc
 * @since 2020年03月29日 19:51
 */
public class DpException extends RuntimeException {
    private Integer code;
    private String message;

    public DpException() {
    }

    public DpException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
    }
}
