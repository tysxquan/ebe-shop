package com.sxquan.manage.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.pojo.user.User;
import com.sxquan.manage.user.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
@RestController
@RequestMapping("/third_user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("list")
    @RequiresPermissions("third_user:view")
    public ServerResponse vipList(User user, RequestPage requestPage){
        // String createTimeRange = systemUser.getCreateTimeRange();
        // if (StringUtils.isNotBlank(createTimeRange)){
        //     String[] split = StringUtils.splitByWholeSeparator(createTimeRange, SystemConstant.SEPARATOR_MINUS);
        //     systemUser.setCreateTimeStart(split[0]);
        //     systemUser.setCreateTimeEnd(split[1]+" 23:59:59");
        // }
        IPage<User> page = userService.listVip(user, requestPage);
        return ServerResponse.success(new LayuiPage<>(page.getRecords(),page.getTotal()));
    }
}

