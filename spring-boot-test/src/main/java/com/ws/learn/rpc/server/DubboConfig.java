package com.ws.learn.rpc.server;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author willis<songkai01 @ fangdd.com>
 * @chapter Dubbo 配置类
 * @section
 * @since 2019年05月21日 11:37
 */
@Configuration
@DubboComponentScan(basePackages = "com.ws.learn.rpc.server.service.impl")
public class DubboConfig {

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig bean = new ApplicationConfig();
        bean.setName("estate.search.fdd");
        return bean;
    }

    @Bean
    public RegistryConfig registryConfig(@Value("${dubbo.registry.addr}") String address) {
        RegistryConfig bean = new RegistryConfig();
        bean.setAddress(address);
        bean.setProtocol("zookeeper");
        return bean;
    }

    @Bean
    public ProtocolConfig protocolConfig(@Value("${dubbo.port}") int port) {
        ProtocolConfig bean = new ProtocolConfig();
        bean.setName("dubbo");
        bean.setPort(port);
        bean.setServer("netty");
        return bean;
    }

    /*@Bean
    public ProviderConfig providerConfig() {
        ProviderConfig bean = new ProviderConfig();
        bean.setFilter("traceIdProvider,default");
        return bean;
    }
    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig bean = new ConsumerConfig();
        bean.setCheck(false);
        bean.setFilter("traceIdConsumer,default");
        return bean;
    }*/
}