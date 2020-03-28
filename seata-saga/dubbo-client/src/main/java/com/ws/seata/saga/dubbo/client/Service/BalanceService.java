package com.ws.seata.saga.dubbo.client.Service;

public interface BalanceService {
    /**
     * 扣余额
     *
     * @param count
     * @return
     */
    boolean reduce(String businessKey, int count);

    /**
     * 扣余额的补偿接口
     *
     * @return
     */
    boolean compensateReduce(String businessKey);
}
