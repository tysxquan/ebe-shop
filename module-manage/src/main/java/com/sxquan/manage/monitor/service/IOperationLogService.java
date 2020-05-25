package com.sxquan.manage.monitor.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.constant.SystemConstant;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.manage.monitor.pojo.OperationLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * 管理员操作日志 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-02-27
 */
public interface IOperationLogService extends IService<OperationLog> {

    /**
     * 异步保存日志
     * @param point 切点
     * @param method Method
     * @param request HttpServletRequest
     * @param operation 操作内容
     * @param result 操作结果
     */
    @Async(SystemConstant.ASYNC_POOL)
    void saveLog(ProceedingJoinPoint point, Method method, HttpServletRequest request, String operation, Boolean result );

    /**
     * 查询当前用户的操作数据（最新10条）
     * @param systemUserId 用户id
     * @return
     */
    List<OperationLog> findUserOperationLog(Long systemUserId);

    /**
     * 查找操作数据集合
     * @param operationLog 筛选条
     * @param requestPage 分页参数
     * @return
     */
    IPage<OperationLog> operationLogList(OperationLog operationLog, RequestPage requestPage);
}
