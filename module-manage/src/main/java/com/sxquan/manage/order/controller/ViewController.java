package com.sxquan.manage.order.controller;


import com.sxquan.manage.order.pojo.vo.OrderInfoVO;
import com.sxquan.manage.order.service.IOrdersService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author sxquan
 * @since 2020/5/11 23:39
 */
@Controller("OrderView")
public class ViewController {

    @Autowired
    private IOrdersService ordersService;

    @GetMapping("/order")
    public String order(){
        return "/order/order";
    }

    @GetMapping("/order/{orderId}")
    @RequiresPermissions("order:view")
    public String getOrderInfo(@PathVariable String orderId, Model model) {
        OrderInfoVO order = ordersService.findOrderInfoByOrderId(orderId);
        model.addAttribute("orderInfo",order);
        return "/order/orderInfo";
    }


}
