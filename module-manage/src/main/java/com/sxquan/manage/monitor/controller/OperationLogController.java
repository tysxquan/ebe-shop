package com.sxquan.manage.monitor.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sxquan.core.constant.SystemConstant;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.manage.monitor.pojo.OperationLog;
import com.sxquan.manage.monitor.service.IOperationLogService;
import com.sxquan.manage.system.pojo.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
/**
 * <p>
 * 管理员操作日志 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2020-02-27
 */
@RestController
@RequestMapping("/monitor/operationLog")
public class OperationLogController {

    @Autowired
    private IOperationLogService operationLogService;

    @GetMapping("list")
    @RequiresPermissions("operationLog:view")
    public ServerResponse operationLogList(OperationLog operationLog, RequestPage requestPage){
        String createTimeRange = operationLog.getCreateTimeRange();
        if (StringUtils.isNotBlank(createTimeRange)){
            String[] split = StringUtils.splitByWholeSeparator(createTimeRange, SystemConstant.SEPARATOR_MINUS);
            operationLog.setCreateTimeStart(split[0]);
            operationLog.setCreateTimeEnd(split[1]+" 23:59:59");
        }
        IPage<OperationLog> page = operationLogService.operationLogList(operationLog, requestPage);
        return ServerResponse.success(new LayuiPage<>(page.getRecords(),page.getTotal()));
    }

    @GetMapping("user")
    public ServerResponse getUserOperationLog(){
        SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getPrincipal();
        List<OperationLog> userOperationLog = operationLogService.findUserOperationLog(systemUser.getSystemUserId());
        return ServerResponse.success(userOperationLog);
    }

    @DeleteMapping("{logIds}")
    @RequiresPermissions("operationLog:delete")
    public ServerResponse deleteOperationLog(@PathVariable String logIds) {
        String[] split = StringUtils.split(logIds, StringPool.COMMA);
        operationLogService.removeByIds(Arrays.asList(split));
        return ServerResponse.success();
    }

}

