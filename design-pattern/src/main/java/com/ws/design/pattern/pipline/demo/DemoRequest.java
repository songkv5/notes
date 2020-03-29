package com.ws.design.pattern.pipline.demo;

import lombok.Data;

/**
 * @Author willis
 * @desc
 * @since 2020年03月29日 19:43
 */
@Data
public class DemoRequest {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 产品ID
     */
    private Long productId;
}
