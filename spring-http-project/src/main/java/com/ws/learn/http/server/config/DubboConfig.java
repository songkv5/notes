package com.ws.learn.http.server.config;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallbackRegistry;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @author willis
 * @chapter Dubbo 配置类
 * @section
 * @since 2019年05月21日 11:37
 */
@Configuration
@DubboComponentScan(basePackages = "com.ws")
public class DubboConfig {

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig bean = new ApplicationConfig();
        bean.setName("spring-http-project");
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
    public ConsumerConfig consumerConfig() {
        ConsumerConfig bean = new ConsumerConfig();
//        DubboFallbackRegistry.setConsumerFallback(new DubboFallbackImpl());
//        DubboFallbackRegistry.setConsumerFallback(new DubboFallbackImpl());
        bean.setCheck(false);
        return bean;
    }

    /**
     * 启动注解配置sentinel
     * 也可以使用dubbocallback，{@link DubboFallback}
     * @return
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}