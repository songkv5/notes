package com.ws.zk.mock;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.test.TestingServer;

/**
 * @Author willis
 * @desc
 * @since 2020年03月05日 12:15
 */
@Slf4j
public class ZkMock {

    public static void main(String[] args) throws Exception{
        mockZk();
    }

    private static void mockZk() throws Exception{
        TestingServer server = new TestingServer(2181, true);
        log.info("ZK Mock Server Started ~ ");
        server.start();
    }
}
