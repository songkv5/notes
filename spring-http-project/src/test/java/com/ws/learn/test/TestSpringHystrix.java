package com.ws.learn.test;

import com.alibaba.fastjson.JSON;
import com.ws.learn.http.server.invoker.TestInvoker;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2019年07月09日 17:01
 */
public class TestSpringHystrix extends BaseTest {
    @Autowired
    private TestInvoker testInvoker;

    @Test
    public void test() {
        testInvoker.reset();
        int i= 1;
        for (i = 1; i <= 15; i ++) {
            Long result = testInvoker.getARandomNumber(i);
            logger.info("call {} times, result={}, 熔断器状态：{}", i, JSON.toJSONString(result));
        }

        try {
            Thread.sleep(10000);
            for (; i < 25; i ++) {
                Long result = testInvoker.getARandomNumber(i);
                logger.info("call {} times, result={}, 熔断器状态：{}", i, JSON.toJSONString(result));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---");
    }
}