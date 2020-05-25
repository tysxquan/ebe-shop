package com.sxquan.manage.common.handler;


import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.exception.ShopException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

/**
 * @Description 全局异常处理器
 * @Author sxquan
 * @Date 2020/1/7 21:47
 */
@Slf4j
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ServerResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return ServerResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"系统内部异常");
    }

    @ResponseBody
    @ExceptionHandler(value = ShopException.class)
    public ServerResponse handleRetailException(ShopException e) {
        log.error("系统错误", e);
        return ServerResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }


    /**
     * 认证异常
     *
     * @param e
     * @return ServerResponse
     */
    @ResponseBody
    @ExceptionHandler(value = AuthenticationException.class)
    public ServerResponse handleAuthenticationException(AuthenticationException e) {
        log.debug("AuthenticationException", e);
        return ServerResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public ServerResponse handleIncorrectCredentialsException(IncorrectCredentialsException e) {
        log.debug("IncorrectCredentialsException", e);
        return ServerResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"用户名或密码错误！");
    }
    /**
     * 授权异常
     *
     * @param e
     * @return ServerResponse
     */
    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public ServerResponse handleAuthorizationException(AuthorizationException e){
        log.debug("AuthorizationException", e);
        return ServerResponse.error(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public String  handleUnauthorizedException(UnauthorizedException e, HttpServletRequest request, HttpServletResponse response) {
        log.debug("UnauthorizedException", e);
        request.setAttribute("javax.servlet.error.status_code",HttpStatus.FORBIDDEN.value());
        return "forward:/error";
    }

    /**
     * session失效
     * @param e
     * @return ServerResponse
     */
    @ResponseBody
    @ExceptionHandler(value = ExpiredSessionException.class)
    public ServerResponse handleExpiredSessionException(ExpiredSessionException e) {
        log.debug("ExpiredSessionException", e);
        return ServerResponse.error(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
    }
    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return ServerResponse
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ServerResponse validExceptionHandler(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return ServerResponse.error(HttpStatus.BAD_REQUEST.value(),message.toString());
    }
    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return ServerResponse
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ServerResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return ServerResponse.error(HttpStatus.BAD_REQUEST.value(), message.toString());
    }
}
