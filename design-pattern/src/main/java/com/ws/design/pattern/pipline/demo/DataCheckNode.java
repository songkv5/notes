package com.ws.design.pattern.pipline.demo;

import com.ws.design.pattern.pipline.AbstractNode;
import com.ws.design.pattern.pipline.PipLineContext;
import com.ws.design.pattern.pipline.PipLineException;
import com.ws.design.pattern.pipline.PipLineResult;
import com.ws.design.pattern.pipline.demo.entity.Product;
import com.ws.design.pattern.pipline.demo.entity.User;
import com.ws.design.pattern.pipline.demo.service.ProductService;
import com.ws.design.pattern.pipline.demo.service.UserService;

/**
 * @Author willis
 * @desc
 * @since 2020年03月29日 20:00
 */
public class DataCheckNode extends AbstractNode<DemoContextAttach, Boolean> {
    // 如果使用了spring，可以使用自动注入的方式
    private UserService userService = new UserService();
    private ProductService productService = new ProductService();
    private OrderCreateNode orderCreateNode = new OrderCreateNode();

    public DataCheckNode() {
        // 下个节点
        super.setNext(orderCreateNode);
    }

    @Override
    public PipLineResult<Boolean> process(PipLineContext<DemoContextAttach> pipLineContext) {
        System.out.println("正在检查数据是否满足条件");
        DemoContextAttach data = pipLineContext.getData();
        DemoRequest request = data.getRequest();
        Long userId = request.getUserId();
        Long productId = request.getProductId();
        User user = userService.getUser(userId);
        if (user == null) {
            throw new PipLineException(-1, "用户不存在");
        }
        if (!user.getAuthenticated()) {
            throw new PipLineException(-1, "用户没有通过实名认证");
        }
        Product product = productService.getProduct(productId);
        if (product == null) {
            throw new PipLineException(-1, "产品不存在");
        }
        if (!product.getOnlineStatus().equals(1)) {
            throw new PipLineException(-1, "产品不在线，请稍后再试");
        }
        data.setUser(user);
        data.setProduct(product);
        return PipLineResult.successWithData(true);
    }
}
