package com.ws.learn.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class TestCommand extends HystrixCommand<String> {
    @Override
    protected String run() throws Exception {
        return null;
    }

    public TestCommand(HystrixCommandGroupKey group) {
        super(group);
    }

}
