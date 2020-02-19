package com.ws.learn.test;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.ws.learn.api.response.CommonResponse;
import com.ws.learn.http.server.config.TestCommand;
import com.ws.learn.http.server.invoker.TestInvoker;
import com.ws.learn.rpc.service.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestHystrix extends BaseTest {
    @Autowired
    private TestService testService;
    @Autowired
    private TestInvoker testInvoker;
    @Test
    public void test() {
        testService.reset();
        int i= 1;
        for (i = 1; i <= 15; i ++) {
            TestCommand command = new TestCommand(testService, i);
            CommonResponse<Long> result = command.execute();
            logger.info("call {} times, result={}, 熔断器状态：{}", i, JSON.toJSONString(result), command.isCircuitBreakerOpen());
        }

        try {
            Thread.sleep(10000);
            for (; i < 25; i ++) {
                TestCommand command = new TestCommand(testService, i);
                CommonResponse<Long> result = command.execute();
                logger.info("call {} times, result={}, 熔断器状态：{}", i, JSON.toJSONString(result), command.isCircuitBreakerOpen());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---");
    }
}
