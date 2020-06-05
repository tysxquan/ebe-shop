package com.sxquan.manage.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.pojo.order.PayLog;
import com.sxquan.manage.order.mapper.PayLogMapper;
import com.sxquan.manage.order.service.IPayLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单支付日志 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements IPayLogService {

}
