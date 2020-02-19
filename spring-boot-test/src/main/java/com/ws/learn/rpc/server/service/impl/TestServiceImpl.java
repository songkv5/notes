package com.ws.learn.rpc.server.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ws.learn.api.response.CommonResponse;
import com.ws.learn.rpc.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@org.springframework.stereotype.Service
public class TestServiceImpl implements TestService {
    Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public CommonResponse<Long> getARandomNumber(Integer param) {
        Long source = Long.MAX_VALUE;
        int c = counter.getAndIncrement();
        logger.info("count is {};param={}", c, param);
        if (c <= 10) {
            try {
                Thread.sleep(600);
            } catch (Exception e) {

            }
        }
        return CommonResponse.responseWith(200, (long) (Math.random() * source), "success");
    }

    @Override
    public CommonResponse<Long> reset() {
        counter.getAndSet(0);
        return CommonResponse.responseWith(200, 0L, "success");
    }
}
