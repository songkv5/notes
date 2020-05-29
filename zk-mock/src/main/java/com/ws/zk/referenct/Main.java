package com.ws.zk.referenct;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.ws.learn.rpc.service.TestService;
import com.ws.zk.Config;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2020年05月27日 17:25
 */
public class Main {
    public static final String ZK_URL = "localhost:2181";
    public static void main(String[] args) {
        Config.init();
        try {
            ZooKeeper zk = new ZooKeeper(ZK_URL, 1000, new Watcher() {
                public void process(WatchedEvent event) {

                }
            });
            ApplicationConfig config = new ApplicationConfig();
            RegistryConfig registryConfig = new RegistryConfig();
            registryConfig.setAddress(ZK_URL);
            registryConfig.setProtocol("zookeeper");
            config.setRegistry(registryConfig);

            config.setName("testConsumer");
            ReferenceConfig<TestService> referenceConfig = new ReferenceConfig<TestService>();
            referenceConfig.setRegistry(registryConfig);
            referenceConfig.setGroup("test");
            referenceConfig.setTimeout(100);
            referenceConfig.setApplication(config);
            referenceConfig.setInterface(TestService.class);
            TestService testService = referenceConfig.get();
            System.out.println(testService.getARandomNumber(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}