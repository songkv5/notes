package com.ws.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;

public class Test {
    public static void main(String[] args) {
        CuratorFramework cf = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .sessionTimeoutMs(500)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("test")
                .build();
        try {
            cf.start();
            cf.create().withMode(CreateMode.PERSISTENT).forPath("/willis","willis root".getBytes());
            cf.create().withMode(CreateMode.EPHEMERAL).forPath("/willis/test","willis test".getBytes());
            while(true){}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
