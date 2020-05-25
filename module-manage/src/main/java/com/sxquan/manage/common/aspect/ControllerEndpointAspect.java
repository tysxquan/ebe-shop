package com.sxquan.manage.common.aspect;


import com.sxquan.core.exception.ShopException;
import com.sxquan.core.util.ShopUtil;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.monitor.service.IOperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @description 日志记录
 * @author sxquan
 * @Date 2020/02/27 15:52
 */
@Slf4j
@Aspect
@Component
public class ControllerEndpointAspect extends AspectSupport {

    @Autowired
    private IOperationLogService systemLogService;

    @Pointcut("@annotation(com.sxquan.manage.common.annotation.ControllerEndpoint)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws ShopException {
        Object result;
        Method targetMethod = resolveMethod(point);
        ControllerEndpoint annotation = targetMethod.getAnnotation(ControllerEndpoint.class);
        String operation = annotation.operation();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        long start = System.currentTimeMillis();
        try {
            result = point.proceed();
            if (StringUtils.isNotBlank(operation)) {
                systemLogService.saveLog(point, targetMethod, request, operation,start,true);
            }
            return result;
        } catch (Throwable throwable) {
            String exceptionMessage = annotation.exceptionMessage();
            String message = throwable.getMessage();
            if (StringUtils.isNotBlank(operation)) {
                systemLogService.saveLog(point, targetMethod, request, exceptionMessage,start, false);
            }
            log.error(ShopUtil.getTrace(throwable));
            String error = ShopUtil.containChinese(message) ? exceptionMessage + "，" + message : exceptionMessage;
            throw new ShopException(error);

        }
    }
}



