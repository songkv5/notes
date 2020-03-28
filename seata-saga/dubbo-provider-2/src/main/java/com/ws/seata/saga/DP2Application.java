package com.ws.seata.saga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author willis
 * @desc
 * @since 2020年03月05日 17:58
 */
@SpringBootApplication(scanBasePackages = "com.ws.seata.saga")
public class DP2Application {
    public static void main(String[] args) {
        SpringApplication app =  new SpringApplication(DP2Application.class);
        app.setWebApplicationType(WebApplicationType.SERVLET);
        ConfigurableApplicationContext ctx = app.run(args);

    }
}
