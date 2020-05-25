package com.sxquan.manage.order.controller;


import com.sxquan.core.entity.ServerResponse;
import com.sxquan.manage.order.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单商品详情表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
@RestController
@RequestMapping("/order")
public class OrderItemController {

    @Autowired
    private IOrderItemService orderItemService;

    @GetMapping("/{orderId}/item")
    public ServerResponse orderItemList(@PathVariable String orderId) {
        return ServerResponse.success(orderItemService.findOrderItemByOrderId(orderId));
    }

}

