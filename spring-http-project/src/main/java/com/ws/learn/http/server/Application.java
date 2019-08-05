package com.ws.learn.http.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 程序启动类
 * Http 端口：8080
 * Dubbo服务端口 ：8280
 */
@SpringBootApplication(scanBasePackages = "com.ws.learn.http.server")
@ImportResource("classpath:/spring.xml")
// 添加spring对hystrix的整合
@EnableCircuitBreaker
@EnableHystrixDashboard
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setWebApplicationType(WebApplicationType.SERVLET);
        ConfigurableApplicationContext context = app.run(args);
        Runtime.getRuntime().addShutdownHook(new Thread(context::close));
    }
}
