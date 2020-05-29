package com.ws.learn.http.server.config;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.fastjson.JSON;
import com.ws.learn.http.server.MyException;

import java.util.Map;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2020年05月28日 20:50
 */
public class DubboFallbackImpl implements DubboFallback {
    @Override
    public Result handle(Invoker<?> invoker, Invocation invocation, BlockException ex) {
        Object[] arguments = invocation.getArguments();
        System.out.println("参数:" + JSON.toJSONString(arguments));

        Class<?> anInterface = invoker.getInterface();
        System.out.println("interface is " + anInterface.getClass().getTypeName());

        String methodName = invocation.getMethodName();
        System.out.println("method is " + methodName);
        throw new MyException();
    }

    public static class MyResult implements Result{
        @Override
        public Object getValue() {
            return 10000L;
        }

        @Override
        public Throwable getException() {
            return null;
        }

        @Override
        public boolean hasException() {
            return false;
        }

        @Override
        public Object recreate() throws Throwable {
            return null;
        }

        @Override
        public Object getResult() {
            return null;
        }

        @Override
        public Map<String, String> getAttachments() {
            return null;
        }

        @Override
        public String getAttachment(String key) {
            return null;
        }

        @Override
        public String getAttachment(String key, String defaultValue) {
            return null;
        }
    }
}