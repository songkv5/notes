package com.ws.design.pattern.pipline;

import lombok.Getter;

/**
 * @Author willis
 * @desc 管道执行器
 * @since 2020年03月29日 19:32
 * T context类型
 * R 返回类型
 */
public class PipLineHolder {
    @Getter
    private PipLineContext context;
    @Getter
    private Node head;

    private PipLineHolder() {
    }

    public static Builder newBuilder() {
        return new Builder();
    }
    public static class Builder{
        private PipLineHolder pipLineHolder;
        private Builder() {
            pipLineHolder = new PipLineHolder();
        }
        public Builder context(PipLineContext context) {
            this.pipLineHolder.context = context;
            return this;
        }
        public Builder head(Node node) {
            this.pipLineHolder.head = node;
            return this;
        }
        public PipLineHolder build() {
            return this.pipLineHolder;
        }
    }
}
