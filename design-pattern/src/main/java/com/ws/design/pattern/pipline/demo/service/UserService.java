package com.ws.design.pattern.pipline.demo.service;

import com.ws.design.pattern.pipline.demo.entity.User;

/**
 * @Author willis
 * @desc
 * @since 2020年03月29日 20:05
 */
public class UserService {
    public User getUser(Long userId) {
        User user = new User();
        user.setUserId(userId);
        user.setUserName("willis");
        user.setAuthenticated(true);
        return user;
    }
}
