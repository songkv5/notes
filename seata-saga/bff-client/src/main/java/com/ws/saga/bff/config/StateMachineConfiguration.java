package com.ws.saga.bff.config;

import io.seata.saga.engine.StateMachineConfig;
import io.seata.saga.engine.StateMachineEngine;
import io.seata.saga.engine.config.DbStateMachineConfig;
import io.seata.saga.engine.impl.ProcessCtrlStateMachineEngine;
import io.seata.saga.rm.StateMachineEngineHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import javax.sql.DataSource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author willis
 * @desc
 * @since 2020年03月05日 19:28
 */
@Configuration
public class StateMachineConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public DbStateMachineConfig dbStateMachineConfig() throws Exception{
        DbStateMachineConfig result = new DbStateMachineConfig();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:statelang/*.json");
        result.setResources(resources);
        result.setEnableAsync(true);
        result.setThreadPoolExecutor((ThreadPoolExecutor) threadPool().getObject());
        result.setApplicationId("saga_demo");
        result.setTxServiceGroup("saga_demo_group");
        result.setDataSource(dataSource);

        return result;
    }

    @Bean
    public ThreadPoolExecutorFactoryBean threadPool() {
        ThreadPoolExecutorFactoryBean tp = new ThreadPoolExecutorFactoryBean();
        tp.setCorePoolSize(1);
        tp.setMaxPoolSize(20);
        return tp;
    }


    @Bean
    public ProcessCtrlStateMachineEngine stateMachineEngine(@Qualifier("dbStateMachineConfig") StateMachineConfig stateMachineConfig){
        ProcessCtrlStateMachineEngine engine = new ProcessCtrlStateMachineEngine();
        engine.setStateMachineConfig(stateMachineConfig);
        return engine;
    }
    @Bean
    public StateMachineEngineHolder stateMachineEngineHolder(@Qualifier("stateMachineEngine") StateMachineEngine stateMachineEngine){

        StateMachineEngineHolder stateMachineEngineHolder = new StateMachineEngineHolder();
        stateMachineEngineHolder.setStateMachineEngine(stateMachineEngine);
        return stateMachineEngineHolder;
    }
}
