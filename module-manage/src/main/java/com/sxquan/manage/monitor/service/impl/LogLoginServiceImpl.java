package com.sxquan.manage.monitor.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.monitor.LogLogin;
import com.sxquan.core.util.HttpContextUtil;
import com.sxquan.manage.common.util.IpAddrUtil;
import com.sxquan.manage.monitor.mapper.LogLoginMapper;
import com.sxquan.manage.monitor.service.ILogLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登录日志表 Service实现
 *
 * @author sxquan
 * @since 2020-05-25 20:55:35
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LogLoginServiceImpl extends ServiceImpl<LogLoginMapper, LogLogin> implements ILogLoginService {

    @Autowired
    private LogLoginMapper logLoginMapper;

    @Override
    public IPage<LogLogin> findLogLoginList(LogLogin logLogin, RequestPage request) {
        LambdaQueryWrapper<LogLogin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotBlank(logLogin.getUsername()),LogLogin::getUsername,logLogin.getUsername());
        queryWrapper.between(StringUtils.isNotBlank(logLogin.getLoginTimeRange()),LogLogin::getLoginTime,logLogin.getLoginTimeStart(),logLogin.getLoginTimeEnd());
        Page<LogLogin> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public void addLogLogin(LogLogin logLogin) {
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IpAddrUtil.getIpAddr(request);
        logLogin.setIp(ip);
        logLogin.setLocation(IpAddrUtil.getCityInfo(ip));
        this.save(logLogin);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateLogLogin(LogLogin logLogin) {
        this.updateById(logLogin);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteLogLogin(List<String> idList) {
        this.removeByIds(idList);
	}
}
