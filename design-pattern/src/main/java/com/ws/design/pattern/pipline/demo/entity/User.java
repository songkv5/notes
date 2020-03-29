package com.ws.design.pattern.pipline.demo.entity;

import lombok.Data;

/**
 * @Author willis
 * @desc
 * @since 2020年03月29日 20:06
 */
@Data
public class User {
    /** 用户ID*/
    private Long userId;
    /** 用户名*/
    private String userName;
    /** 用户是否通过认证*/
    private Boolean authenticated;



}
