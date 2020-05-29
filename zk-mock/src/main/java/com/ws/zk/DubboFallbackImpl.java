package com.ws.zk;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.fastjson.JSON;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2020年05月28日 20:50
 */
public class DubboFallbackImpl implements DubboFallback {
    public Result handle(Invoker<?> invoker, Invocation invocation, BlockException ex) {
        Object[] arguments = invocation.getArguments();
        System.out.println("参数:" + JSON.toJSONString(arguments));

        Class<?> anInterface = invoker.getInterface();
        System.out.println("interface is " + anInterface.getClass().getName());

        String methodName = invocation.getMethodName();
        System.out.println("method is " + methodName);
        invoker.invoke(invocation);
        return null;
    }
}