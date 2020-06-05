package com.sxquan.manage.system.controller;


import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.exception.ShopException;
import com.sxquan.core.pojo.monitor.LogLogin;
import com.sxquan.core.pojo.system.SystemUser;
import com.sxquan.manage.common.service.ValidateCodeService;
import com.sxquan.manage.monitor.service.ILogLoginService;
import com.sxquan.manage.system.service.ISystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

/**
 * @description 登录模块前端控制器
 * @author sxquan
 * @since 2019/12/20 22:38
 */
@Slf4j
@Validated
@RestController
public class LoginController {

    @Autowired
    private ISystemUserService systemUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private ILogLoginService logLoginService;

    @PostMapping("/login")
    public ServerResponse login(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password,
            @NotBlank(message = "{required}") String verifyCode,
            boolean rememberMe,HttpServletRequest request) throws SystemException {
        HttpSession session = request.getSession();
        validateCodeService.check(session.getId(), verifyCode);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password,rememberMe);
        subject.login(token);
        //更新用户登录时间
        systemUserService.updateLoginTime(username);

        //记录登录日志
        LogLogin logLogin = new LogLogin();
        logLogin.setUsername(username);
        logLogin.setSystemBrowserInfo();
        logLoginService.addLogLogin(logLogin);
        return ServerResponse.success();
    }

    @PostMapping("register")
    public ServerResponse register( @NotBlank(message = "{required}") String username,
                                    @NotBlank(message = "{required}") String password){
        SystemUser user = systemUserService.findUserByUsername(username);
        if (user != null){
            throw new ShopException("用户已存在");
        }
        systemUserService.register(username,password);
        return ServerResponse.success();
    }

    @GetMapping("image/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ShopException {
        validateCodeService.create(request, response);
    }
}
