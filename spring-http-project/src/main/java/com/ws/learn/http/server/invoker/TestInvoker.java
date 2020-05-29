package com.ws.learn.http.server.invoker;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.dubbo.config.annotation.Reference;
import com.ws.learn.http.server.MyException;
import com.ws.learn.rpc.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestInvoker {
    @Reference(group = "test", retries = -1)
    private TestService testService;

    @Reference(group = "test2", retries = -1, timeout = 300)
    private TestService testService2;

    public Long getARandomNumber(Integer s) {
        try {
            return testService.getARandomNumber(s).getData();
        } catch (Exception e) {
            if (e instanceof MyException) {
                System.out.println("-------");
                return 100000L;
            }
            Throwable cause = e.getCause();
            if (cause instanceof MyException) {
                return 100000L;
            }
            return 0L;
        }
    }
    @SentinelResource(value = "test2/com.ws.learn.rpc.service.TestService:getARandomNumber(java.lang.Integer)",
            blockHandler = "getARandomNumber2Handler")
    public Long getARandomNumber2(Integer s) throws BlockException{
        try {
            return testService2.getARandomNumber(s).getData();
        } catch (Exception e) {
            // 通过抛出blockexception来捕获降级类型
            throw new DegradeException("sss");
        }
    }

    public Long getARandomNumber2Handler(Integer s, BlockException ex) {
        if (ex instanceof DegradeException) {
            log.info("触发熔断降级策略");
            return 99999L;
        }
        return 1111111111L;
    }
}
