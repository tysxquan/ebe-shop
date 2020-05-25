package com.sxquan.manage.monitor.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.util.IPUtil;
import com.sxquan.manage.monitor.mapper.OperationLogMapper;
import com.sxquan.manage.monitor.pojo.OperationLog;
import com.sxquan.manage.monitor.service.IOperationLogService;
import com.sxquan.manage.system.pojo.SystemUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * 管理员操作日志 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-02-27
 */
@Slf4j
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

    @Override
    public void saveLog(ProceedingJoinPoint point, Method method, HttpServletRequest request, String operation, Boolean result) {
        OperationLog systemLog = new OperationLog();
        // 设置操作用户
        SystemUser user = (SystemUser) SecurityUtils.getSubject().getPrincipal();
        if (user != null) {
            systemLog.setSystemUserId(user.getSystemUserId());
            systemLog.setUsername(user.getUsername());
        }
        //设置方法名
        systemLog.setMethod(method.getDeclaringClass().getName() + StringPool.DOT + method.getName());
        //设置操作描述
        systemLog.setOperation(operation);
        //设置用户ip
        String ip = IPUtil.getIpAddr(request);
        systemLog.setIp(ip);
        //设置ip地址的位置
        if (StringUtils.isNotBlank(ip) && ip.length() <= IPUtil.IP_LENGTH) {
            systemLog.setLocation(IPUtil.getCityInfo(ip));
        }
        //设置请求参数
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = u.getParameterNames(method);
        Object[] args = point.getArgs();
        if (args != null && parameterNames != null) {
            StringBuilder sb = new StringBuilder();
            StringBuilder stringBuilder = handleParams(sb, args, parameterNames);
            systemLog.setParam(stringBuilder.toString());
        }
        //操作结果
        systemLog.setResult(result);
        this.save(systemLog);
    }

    @Override
    public List<OperationLog> findUserOperationLog(Long systemUserId) {
        return baseMapper.selectList(new LambdaQueryWrapper<OperationLog>()
                .eq(OperationLog::getSystemUserId,systemUserId)
                .orderByDesc(OperationLog::getCreateTime)
                .last("limit 10"));
    }

    @Override
    public IPage<OperationLog> operationLogList(OperationLog operationLog, RequestPage requestPage) {
        Page<OperationLog> page = new Page<>(requestPage.getPageNum(),requestPage.getPageSize());
        return baseMapper.selectPage(page,new LambdaQueryWrapper<OperationLog>()
                .like(StringUtils.isNotBlank(operationLog.getUsername()),OperationLog::getUsername,operationLog.getUsername())
                .eq(ObjectUtils.isNotEmpty(operationLog.getResult()),OperationLog::getResult,operationLog.getResult())
                .between(StringUtils.isNotBlank(operationLog.getCreateTimeRange()), OperationLog::getCreateTime,operationLog.getCreateTimeStart(),operationLog.getCreateTimeEnd())
                );
    }

    private StringBuilder handleParams(StringBuilder params, Object[] args, String[] paramNames) {
        try {
            for (int i = 0; i < args.length; i++) {
                if (!(args[i] instanceof MultipartFile) && !(args[i] instanceof MultipartFile[])) {
                    try {
                        String jsonString = JSON.toJSONString(args[i]);
                        params.append(paramNames[i]).append(jsonString).append(" ");
                    } catch (JSONException e) {
                        log.warn("格式化Json异常");
                    }
                }
            }
        } catch (Exception e) {
            params.append("参数解析失败");
        }
        return params;
    }

}
