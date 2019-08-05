package com.ws.learn.http.server.invoker;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ws.learn.api.response.CommonResponse;
import com.ws.learn.rpc.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通过注解使用hystrix
 */
@Component
public class TestInvoker {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestInvoker.class);
    @Autowired
    private TestService testService;

    /**
     * 参考 https://github.com/Netflix/Hystrix/wiki/Configuration
     * @param num
     * @return
     */
    @HystrixCommand(groupKey = "TestService", commandKey = "getARandomNumber",
            fallbackMethod = "fallback",
            threadPoolKey = "test",
            commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),// 如果5秒没返回则执行降级逻辑
    }, threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "10``"),
            @HystrixProperty(name = "maxQueueSize", value = "102400000"),
    })
    public Long getARandomNumber(Integer num) {
        CommonResponse<Long> response = testService.getARandomNumber(num);
        if (response != null) {
            return response.getData();
        }
        return null;
    }

    public Long fallback(Integer num) {
        LOGGER.warn("执行了降级逻辑,num={}", num);
        return 100L;
    }

    public void reset() {
        testService.reset();
    }
}
