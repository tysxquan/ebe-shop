package com.sxquan.manage.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.util.ShopUtil;
import com.sxquan.manage.common.enums.SpuImageSrcEnum;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.order.mapper.OrdersMapper;
import com.sxquan.manage.order.pojo.Orders;
import com.sxquan.manage.order.pojo.vo.OrderInfoVO;
import com.sxquan.manage.order.service.IOrderItemService;
import com.sxquan.manage.order.service.IOrdersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 订单主表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Autowired
    private EbeProperties properties;

    @Autowired
    private IOrderItemService orderItemService;

    @Override
    public IPage<Orders> listOrder(Orders order, RequestPage requestPage) {
        IPage<Orders> page = new Page<>(requestPage.getPageNum(),requestPage.getPageSize());
        return baseMapper.selectPage(page,new LambdaQueryWrapper<Orders>()
                .like(StringUtils.isNotBlank(order.getOrderId()),Orders::getOrderId,order.getOrderId()));

    }

    @Override
    public OrderInfoVO findOrderInfoByOrderId(String orderId) {
        OrderInfoVO order = baseMapper.selectOrderInfoByOrderId(orderId);
        order.setUserTrueMame(ShopUtil.desensitizedIdName(order.getUserTrueMame()));
        order.setUserMobile(ShopUtil.desensitizedIdMobile(order.getUserMobile()));
        order.getOrderItems().forEach( x -> {
            x.setCover(properties.getFileServer() + SpuImageSrcEnum.COVER.getSrc() + x.getCover());
        });
        return order;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteOrderByOrderIds(String orderIds) {
        String[] split = orderIds.split(StringPool.COMMA);
        List<String> orderIdList = Arrays.asList(split);
        baseMapper.delete(new LambdaQueryWrapper<Orders>()
                .in(Orders::getOrderId,orderIdList));
        orderItemService.deleteOrderItemByOrderId(orderIdList);
    }
}
