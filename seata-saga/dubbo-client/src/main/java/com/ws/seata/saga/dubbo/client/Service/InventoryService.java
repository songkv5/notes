package com.ws.seata.saga.dubbo.client.Service;

/**
 * @Author willis
 * @desc
 * @since 2020年03月05日 14:12
 */
public interface InventoryService {
    /**
     * 扣库存
     *
     * @param count
     * @return
     */
    boolean reduce(String businessKey, int count);

    /**
     * 扣库存的补偿接口
     *
     * @return
     */
    boolean compensateReduce(String businessKey);
}
