package com.ws.design.pattern.pipline.demo.entity;

import lombok.Data;

/**
 * @Author willis
 * @desc
 * @since 2020年03月29日 20:11
 */
@Data
public class Product {
    /** 产品ID*/
    private Long productId;
    /** 产品名称*/
    private String name;
    /** 在线状态。1=在线；2=下架*/
    private Integer onlineStatus;
}
