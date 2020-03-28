package com.ws.saga.bff.controller;

import com.ws.seata.saga.dubbo.client.Service.BalanceService;
import com.ws.seata.saga.dubbo.client.Service.InventoryService;
import io.seata.saga.engine.StateMachineEngine;
import io.seata.saga.statelang.domain.StateMachineInstance;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author willis
 * @desc
 * @since 2020年03月08日 18:59
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private StateMachineEngine stateMachineEngine;

    @Reference(id = "balanceService", retries = 0, timeout = 1000)
    private BalanceService balanceService;
    @Reference(id = "inventoryService", retries = 0, timeout = 1000)
    private InventoryService inventoryService;

    @RequestMapping("/demo/commit")
    public Object testgtx() {
        Map<String, Object> startParams = new HashMap<>(3);
        String businessKey = String.valueOf(System.currentTimeMillis());
        startParams.put("businessKey", businessKey);
        startParams.put("count", 10);
        startParams.put("amount", new BigDecimal("100"));
        // 启动状态机
        StateMachineInstance instance = stateMachineEngine.startWithBusinessKey("reduceInventoryAndBalance", null, businessKey, startParams);
        System.out.println("xid:" + instance.getId());
        return instance.getId();
    }
}
