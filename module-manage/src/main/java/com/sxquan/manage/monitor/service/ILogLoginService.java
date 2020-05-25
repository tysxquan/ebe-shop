package com.sxquan.manage.monitor.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.manage.monitor.pojo.LogLogin;

import java.util.List;

/**
 * 登录日志表 Service接口
 *
 * @author sxquan
 * @since 2020-05-25 20:55:35
 */
public interface ILogLoginService extends IService<LogLogin> {
    /**
     * 查询列表（分页）
     *
     * @param logLogin logLogin
     * @param request 分页条件
     * @return IPage<LogLogin>
     */
    IPage<LogLogin> findLogLoginList(LogLogin logLogin, RequestPage request);


    /**
     * 新增登录日志
     *
     * @param logLogin logLogin
     */
    void addLogLogin(LogLogin logLogin);

    /**
     * 修改
     *
     * @param logLogin logLogin
     */
    void updateLogLogin(LogLogin logLogin);

    /**
     * 通过主键删除
     *
     * @param idList 主键集合
     */
    void deleteLogLogin(List<String> idList);
}
