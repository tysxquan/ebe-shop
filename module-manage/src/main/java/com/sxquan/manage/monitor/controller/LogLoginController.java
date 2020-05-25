package com.sxquan.manage.monitor.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sxquan.core.constant.SystemConstant;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.monitor.pojo.LogLogin;
import com.sxquan.manage.monitor.service.ILogLoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 登录日志表 前端控制器
 *
 * @author sxquan
 * @since 2020-05-25 20:55:35
 */
@Slf4j
@Validated
@RestController()
@RequestMapping("/monitor/logLogin")
public class LogLoginController {

    @Autowired
    private ILogLoginService logLoginService;

    @GetMapping("list")
    @RequiresPermissions("logLogin:view")
    public ServerResponse logLoginList(LogLogin logLogin, RequestPage request) {
    String loginTimeRange = logLogin.getLoginTimeRange();
    if (StringUtils.isNotBlank(loginTimeRange)){
        String[] split = StringUtils.splitByWholeSeparator(loginTimeRange, SystemConstant.SEPARATOR_MINUS);
        logLogin.setLoginTimeStart(split[0]);
        logLogin.setLoginTimeEnd(split[1]+" 23:59:59");
    }
    IPage<LogLogin> page = this.logLoginService.findLogLoginList(logLogin, request);
        return ServerResponse.success(new LayuiPage<>(page.getRecords(),page.getTotal()));
    }

    @DeleteMapping("{ids}")
    @RequiresPermissions("logLogin:delete")
    @ControllerEndpoint(operation = "删除登录日志信息", exceptionMessage = "删除登录日志信息失败")
    public ServerResponse deleteLogLogin(@PathVariable String ids) {
        String[] split = StringUtils.split(ids, StringPool.COMMA);
        this.logLoginService.deleteLogLogin(Arrays.asList(split));
        return ServerResponse.success();
    }


}
