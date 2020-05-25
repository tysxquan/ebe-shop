package com.sxquan.manage.monitor.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author sxquan
 * @since 2020/3/19 18:14
 */
@Controller("/monitorView")
public class ViewController {

    @GetMapping("monitor/operationLog")
    @RequiresPermissions("operationLog:view")
    public String monitorOperationLog() {return "monitor/operationLog/operationLog";}

    @GetMapping("logLogin")
    @RequiresPermissions("logLogin:view")
    public String logLoginIndex(){
        return "monitor/logLogin/logLogin";
    }

}
