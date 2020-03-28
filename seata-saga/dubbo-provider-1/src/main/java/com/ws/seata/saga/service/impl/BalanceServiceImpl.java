package com.ws.seata.saga.service.impl;

import com.ws.seata.saga.dubbo.client.Service.BalanceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author willis
 * @desc
 * @since 2020年03月05日 17:43
 */
@Slf4j
@Service
public class BalanceServiceImpl implements BalanceService {
    @Override
    public boolean reduce(String businessKey, int count) {
        if (businessKey == "throw") {
            throw new RuntimeException("reduce balance failed");
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("调用com.ws.seata.saga.service.impl.BalanceServiceImpl.reduce方法");
        return true;
    }

    @Override
    public boolean compensateReduce(String businessKey) {
        if (businessKey == "throw") {
            throw new RuntimeException("reduce balance failed");
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("调用com.ws.seata.saga.service.impl.BalanceServiceImpl.compensateReduce");
        return true;
    }
}
