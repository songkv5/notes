package com.ws.learn.http.server.config;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallbackRegistry;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2020年05月29日 10:59
 */
public class ApplicationListener implements SpringApplicationRunListener {
    public ApplicationListener() {
    }

    public ApplicationListener(SpringApplication application, String[] args) {
    }

    @Override
    public void starting() {

    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void started(ConfigurableApplicationContext context) {

        // 配置sentinel规则
        /**
         * {@link DegradeRule}:熔断规则
         * 参考：https://github.com/alibaba/Sentinel/wiki/%E7%86%94%E6%96%AD%E9%99%8D%E7%BA%A7
         */
        DegradeRule rule = new DegradeRule();
        rule.setResource("com.ws.learn.rpc.service.TestService:getARandomNumber(java.lang.Integer)");
        // 降级执行后，10s后会重试练级
        rule.setTimeWindow(10);
        rule.setCount(5);
        //一分钟内失败次数过多
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        DegradeRuleManager.loadRules(Arrays.asList(rule));



        // 也可以注册dubbo降级回调，与注解方式选其一即可
//        DubboFallbackRegistry.setConsumerFallback(new DubboFallbackImpl());
    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}