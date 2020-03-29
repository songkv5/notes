package com.ws.design.pattern.pipline.demo.service;

import com.ws.design.pattern.pipline.demo.entity.Product;

/**
 * @Author willis
 * @desc
 * @since 2020年03月29日 20:10
 */
public class ProductService {
    public Product getProduct(Long productId) {
        Product p = new Product();
        p.setProductId(productId);
        p.setName("葵花宝典");
        p.setOnlineStatus(1);
        return p;
    }
}
