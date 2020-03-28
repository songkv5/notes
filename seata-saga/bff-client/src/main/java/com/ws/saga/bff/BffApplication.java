package com.ws.saga.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author willis
 * @desc
 * @since 2020年03月05日 19:21
 */
@SpringBootApplication(scanBasePackages = "com.ws.saga.bff")
//@ImportResource(locations = {"classpath:/seata-saga.xml"})
public class BffApplication {


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BffApplication.class);
        ConfigurableApplicationContext ctx = application.run(args);
    }
}
