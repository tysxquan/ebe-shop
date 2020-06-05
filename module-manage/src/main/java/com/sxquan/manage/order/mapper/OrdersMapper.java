package com.sxquan.manage.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxquan.core.pojo.order.Orders;
import com.sxquan.manage.order.pojo.vo.OrderInfoVO;

/**
 * <p>
 * 订单主表 Mapper 接口
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    /**
     * 通过订单编号查询详情
     * @param orderId 订单编号
     * @return
     */
    OrderInfoVO selectOrderInfoByOrderId(String orderId);


}
