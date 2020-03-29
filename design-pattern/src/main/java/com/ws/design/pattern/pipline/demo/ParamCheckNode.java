package com.ws.design.pattern.pipline.demo;

import com.ws.design.pattern.pipline.AbstractNode;
import com.ws.design.pattern.pipline.PipLineContext;
import com.ws.design.pattern.pipline.PipLineException;
import com.ws.design.pattern.pipline.PipLineResult;

/**
 * @Author willis
 * @desc 参数检测环节
 * @since 2020年03月29日 19:42
 */
public class ParamCheckNode extends AbstractNode<DemoContextAttach, Void> {
    // 在spring容器中可以使用依赖注入进来
    private DataCheckNode dataCheckNode = new DataCheckNode();

    public ParamCheckNode() {
        super.setNext(dataCheckNode);
    }

    @Override
    public PipLineResult<Void> process(PipLineContext<DemoContextAttach> pipLineContext) {
        System.out.println("正在检查参数正确性");
        DemoContextAttach data = pipLineContext.getData();
        DemoRequest request = data.getRequest();
        Long userId = request.getUserId();
        // 假设userId必传，在这里校验
        if (userId == null || userId <= 0) {
            System.out.println("参数不合法：userId");
            throw new PipLineException(-1, "参数不合法");
        }

        return PipLineResult.successWithData(null);
    }
}
