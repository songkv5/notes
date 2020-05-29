package com.ws.learn.http.server.controller;

import com.ws.learn.http.server.MyException;
import com.ws.learn.http.server.invoker.TestInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2020年05月28日 21:01
 */
@RestController
public class TestController {

    @Autowired
    private TestInvoker testInvoker;
    @RequestMapping("/test/{number}")
    public Object test(@PathVariable("number") Integer number) throws Throwable{
        try {
            return testInvoker.getARandomNumber(number);
        } catch (Exception e) {
        }
        return 1;
    }

    @RequestMapping("/test2/{number}")
    public Object test2(@PathVariable("number") Integer number) throws Throwable{
        try {
            return testInvoker.getARandomNumber2(number);
        } catch (Exception e) {
        }
        return 1;
    }
}