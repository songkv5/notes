package com.ws.design.pattern.pipline.demo;

import com.ws.design.pattern.pipline.AbstractNode;
import com.ws.design.pattern.pipline.PipLineContext;
import com.ws.design.pattern.pipline.PipLineResult;
import com.ws.design.pattern.pipline.demo.entity.Product;
import com.ws.design.pattern.pipline.demo.entity.User;
import com.ws.design.pattern.pipline.demo.service.OrderService;

/**
 * @Author willis
 * @desc
 * @since 2020年03月29日 20:17
 */
public class OrderCreateNode extends AbstractNode<DemoContextAttach, Long> {
    private OrderService orderService = new OrderService();
    @Override
    public PipLineResult<Long> process(PipLineContext<DemoContextAttach> pipLineContext) {
        System.out.println("正在下单");
        DemoContextAttach data = pipLineContext.getData();
        Product product = data.getProduct();
        User user = data.getUser();
        Long orderId = orderService.createOrder(user, product, 2);
        return PipLineResult.successWithData(orderId);
    }
}
