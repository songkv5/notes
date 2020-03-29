package com.ws.design.pattern.pipline.demo;

import com.ws.design.pattern.pipline.PipLineContext;
import com.ws.design.pattern.pipline.PipLineExecutors;
import com.ws.design.pattern.pipline.PipLineHolder;
import com.ws.design.pattern.pipline.PipLineResult;

/**
 * @Author willis
 * @desc
 * @since 2020年03月29日 20:19
 */
public class Main {
    public static void main(String[] args) {
        DemoRequest request = new DemoRequest();
        request.setUserId(3024581L);
        request.setProductId(1L);

        DemoContextAttach att = new DemoContextAttach();
        att.setRequest(request);


        PipLineHolder pipLineHolder = PipLineHolder.newBuilder().context(new PipLineContext(att)).head(new ParamCheckNode())
                .build();

        // 启动管道
        PipLineResult<Long> result = PipLineExecutors.start(pipLineHolder);
        System.out.println("orderId=" + result.getData());
    }
}
