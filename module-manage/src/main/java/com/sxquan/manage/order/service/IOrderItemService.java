package com.sxquan.manage.order.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.manage.order.pojo.OrderItem;

import java.util.List;

/**
 * <p>
 * 订单商品详情表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
public interface IOrderItemService extends IService<OrderItem> {

    /**
     * 通过订单编号查询商品列表
     * @param orderId 订单编号
     * @return
     */
    List<OrderItem> findOrderItemByOrderId(String orderId);

    /**
     * 通过订单编号查删除商品列表
     * @param orderIdLIst 订单编号集合
     * @return
     */
    void deleteOrderItemByOrderId(List<String> orderIdLIst);
}
