package com.sxquan.manage.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author sxquan
 * @since 2020/5/12 16:38
 */
@Controller("ViewUser")
public class ViewController {

    @GetMapping("third_user")
    public String vip(){
        return "user/user";
    }
}
