package com.sxquan.manage.order.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.pojo.order.Orders;
import com.sxquan.manage.order.service.IOrdersService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单主表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @GetMapping("list")
    @RequiresPermissions("order:view")
    public ServerResponse orderList(Orders orders, RequestPage requestPage){
        // String createTimeRange = systemUser.getCreateTimeRange();
        // if (StringUtils.isNotBlank(createTimeRange)){
        //     String[] split = StringUtils.splitByWholeSeparator(createTimeRange, SystemConstant.SEPARATOR_MINUS);
        //     systemUser.setCreateTimeStart(split[0]);
        //     systemUser.setCreateTimeEnd(split[1]+" 23:59:59");
        // }
        IPage<Orders> page = ordersService.listOrder(orders, requestPage);
        return ServerResponse.success(new LayuiPage<>(page.getRecords(),page.getTotal()));
    }

    @DeleteMapping("{orderIds}")
    @RequiresPermissions("order:delete")
    public ServerResponse deleteOrder(@PathVariable String orderIds) {
        ordersService.deleteOrderByOrderIds(orderIds);
        return ServerResponse.success();
    }


}

