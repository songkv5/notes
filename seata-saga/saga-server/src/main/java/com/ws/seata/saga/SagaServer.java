package com.ws.seata.saga;

import io.seata.server.Server;

/**
 * @Author willis
 * @desc
 * @since 2020年03月05日 12:28
 */
public class SagaServer {
    public static void main(String[] args) throws Exception{
        // 默认端口8091
        Server server = new Server();
        server.main(args);
    }
}
