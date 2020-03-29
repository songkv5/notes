package com.ws.design.pattern.pipline;

import com.ws.design.pattern.DpException;

/**
 * @Author willis
 * @desc
 * @since 2020年03月29日 19:52
 */
public class PipLineException extends DpException {
    public PipLineException(Integer code, String msg) {
        super(code, msg);
    }
}
