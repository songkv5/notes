package com.ws.learn.http.server.invoker;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ws.learn.rpc.service.TestService;
import org.springframework.stereotype.Component;

@Component
public class TestInvoker {
    @Reference
    private TestService testService;
}
