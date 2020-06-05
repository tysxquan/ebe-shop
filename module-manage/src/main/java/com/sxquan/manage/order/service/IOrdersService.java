package com.sxquan.manage.order.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.order.Orders;
import com.sxquan.manage.order.pojo.vo.OrderInfoVO;

/**
 * <p>
 * 订单主表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
public interface IOrdersService extends IService<Orders> {
    /**
     * 分页查询列表
     * @param order 条件
     * @param requestPage 分页条件
     * @return
     */
    IPage<Orders> listOrder(Orders order, RequestPage requestPage);

    /**
     * 通过订单编号查询详情
     * @param orderId 订单编号
     * @return
     */
    OrderInfoVO findOrderInfoByOrderId(String orderId);

    /**
     * 通过订单编号删除
     * @param orderIds 订单编号
     */
    void deleteOrderByOrderIds(String orderIds);
}
