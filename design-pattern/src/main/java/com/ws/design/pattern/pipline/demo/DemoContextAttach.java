package com.ws.design.pattern.pipline.demo;

import com.ws.design.pattern.pipline.demo.entity.Product;
import com.ws.design.pattern.pipline.demo.entity.User;
import lombok.Data;

/**
 * @Author willis
 * @desc 上下文附属数据
 * @since 2020年03月29日 19:47
 */
@Data
public class DemoContextAttach {
    private User user;
    private Product product;
    /**
     * 参数传递
     */
    private DemoRequest request;
}
