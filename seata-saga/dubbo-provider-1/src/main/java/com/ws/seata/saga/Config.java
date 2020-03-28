package com.ws.seata.saga;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author willis
 * @desc
 * @since 2020年03月05日 13:45
 */
@Configuration
@DubboComponentScan(basePackages = {"com.ws.seata.saga"})
public class Config {

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
    public ProtocolConfig protocolConfig(@Value("${dubbo.protocol.port}") int port) {
        ProtocolConfig bean = new ProtocolConfig();
        bean.setName("dubbo");
        bean.setPort(port);
        bean.setServer("netty");
        return bean;
    }

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        return providerConfig;
    }

}
