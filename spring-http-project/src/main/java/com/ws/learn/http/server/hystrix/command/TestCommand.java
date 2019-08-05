package com.ws.learn.http.server.hystrix.command;

import com.netflix.hystrix.*;
import com.ws.learn.api.response.CommonResponse;
import com.ws.learn.rpc.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 手动配置hystrix
 */
public class TestCommand extends HystrixCommand<CommonResponse<Long>> {
    Logger logger = LoggerFactory.getLogger(TestCommand.class);
    private TestService testService;
    private Integer param;
    @Override
    protected CommonResponse<Long> run() throws Exception {
        long startTime = System.currentTimeMillis();
        CommonResponse<Long> aRandomNumber = testService.getARandomNumber(param);
        logger.info("耗时：{}ms, param={}", System.currentTimeMillis() -  startTime, param);
        return aRandomNumber;
    }

    public TestCommand(TestService testService, Integer param) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("testService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("getARandomNumber"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        //至少有10个请求，熔断器才进行错误率的计算
                        .withCircuitBreakerRequestVolumeThreshold(10)
                        //熔断器中断请求5秒后会进入半打开状态,放部分流量过去重试
                        .withCircuitBreakerSleepWindowInMilliseconds(5000)
                        //错误率达到50开启熔断保护
                        .withCircuitBreakerErrorThresholdPercentage(50)
                        .withExecutionTimeoutEnabled(true)
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                        //最大并发请求量
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(10))
                        .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(10)));
        this.testService = testService;
        this.param = param;
    }

    @Override
    protected CommonResponse<Long> getFallback() {
        logger.error("请求失败，param={}", param);
        return CommonResponse.responseWith(201, -1L, "Failed");
    }

}
