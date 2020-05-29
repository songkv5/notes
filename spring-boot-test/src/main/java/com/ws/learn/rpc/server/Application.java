package com.ws.learn.rpc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 程序启动类
 * Http 端口：9000
 * Dubbo服务端口 ：9200
 */
@SpringBootApplication(scanBasePackages = "com.ws.learn.rpc.server")
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setWebApplicationType(WebApplicationType.SERVLET);
        ConfigurableApplicationContext context = app.run(args);
        Runtime.getRuntime().addShutdownHook(new Thread(context::close));
    }
}
