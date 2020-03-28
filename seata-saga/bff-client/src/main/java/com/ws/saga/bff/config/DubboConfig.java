package com.ws.saga.bff.config;

import com.ws.seata.saga.dubbo.client.Service.BalanceService;
import com.ws.seata.saga.dubbo.client.Service.InventoryService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author willis
 * @desc
 * @since 2020年03月05日 19:21
 */
@Configuration
@DubboComponentScan("com.ws.saga.bff")
public class DubboConfig {
    @Reference
    private BalanceService balanceService;
    @Reference
    private InventoryService inventoryService;
    @Bean
    public RegistryConfig registryConfig(@Value("${dubbo.registry.address:}") String zkurl) {
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(zkurl);
        registry.setCheck(false);
        registry.setProtocol("zookeeper");
        return registry;
    }

    @Bean
    public ApplicationConfig applicationConfig(@Value("${dubbo.application.name}") String name) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName(name);
        return application;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig bean = new ConsumerConfig();
        bean.setCheck(false);
        return bean;
    }
}
