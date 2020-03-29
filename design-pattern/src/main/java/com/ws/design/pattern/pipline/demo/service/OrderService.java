package com.ws.design.pattern.pipline.demo.service;

import com.ws.design.pattern.pipline.demo.entity.Product;
import com.ws.design.pattern.pipline.demo.entity.User;

/**
 * @Author willis
 * @desc
 * @since 2020年03月29日 20:15
 */
public class OrderService {
    /**
     * 下单返回订单id
     * @param user
     * @param product
     * @param count
     * @return
     */
    public Long createOrder(User user, Product product, int count) {
        return System.currentTimeMillis();
    }
}
