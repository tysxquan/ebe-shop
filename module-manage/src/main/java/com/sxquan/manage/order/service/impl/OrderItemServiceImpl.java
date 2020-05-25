package com.sxquan.manage.order.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.manage.order.mapper.OrderItemMapper;
import com.sxquan.manage.order.pojo.OrderItem;
import com.sxquan.manage.order.service.IOrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单商品详情表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

    @Override
    public List<OrderItem> findOrderItemByOrderId(String orderId) {

        return baseMapper.selectList(new LambdaQueryWrapper<OrderItem>()
                .eq(OrderItem::getOrderId,orderId));
    }

    @Override
    public void deleteOrderItemByOrderId(List<String> orderIdLIst) {
      baseMapper.delete(new LambdaQueryWrapper<OrderItem>()
            .in(OrderItem::getOrderId,orderIdLIst));
    }
}
