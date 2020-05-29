package com.ws.zk;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallbackRegistry;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2020年05月28日 20:49
 */
public class Config {
    public static void init() {
        DubboFallbackRegistry.setConsumerFallback(new DubboFallbackImpl());
    }
}