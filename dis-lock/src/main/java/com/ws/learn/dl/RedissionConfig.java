package com.ws.learn.dl;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2020年08月11日 15:37
 */
public class RedissionConfig {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSentinelServers();

        RedissonClient redissonClient = Redisson.create(config);
        RLock rl = redissonClient.getLock("test");
        // 拿锁
        rl.lock();
        try {
            //干点啥
        } finally {
            // 还锁
            rl.unlock();
        }
    }
}