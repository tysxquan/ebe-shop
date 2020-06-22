package com.sxquan.manage.spec.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.pojo.spec.SpecParam;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.spec.service.ISpecParamService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 规格组下的参数表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2020-03-10
 */
@RestController
@RequestMapping("/spec/param")
public class SpecParamController {

    @Autowired
    ISpecParamService specParamService;

    @GetMapping("list")
    @RequiresPermissions("specGroup:update")
    public ServerResponse specParamList(SpecParam specParam, RequestPage requestPage) {
        IPage<SpecParam> page = specParamService.listSpecParam(specParam,requestPage);
        return ServerResponse.success(new LayuiPage<>(page.getRecords(),page.getTotal()));
    }
    @GetMapping("/form/{groupId}")
    public ServerResponse getSpecParamByGorupId(@PathVariable Long groupId){
        return ServerResponse.success( specParamService.findSpecParamByGroupId(groupId));
    }

    @PostMapping()
    @RequiresPermissions("specParam:add")
    @ControllerEndpoint(operation = "新增规格属性",exceptionMessage = "新增规格属性失败")
    public ServerResponse addSpecParam(SpecParam specParam) {
        specParamService.saveSpecParam(specParam);
        return ServerResponse.success();
    }

    @PutMapping()
    @RequiresPermissions("specParam:update")
    @ControllerEndpoint(operation = "修改规格属性",exceptionMessage = "修改规格属性失败")
    public ServerResponse updateSpecParam(SpecParam specParam) {
        specParamService.updateById(specParam);
        return ServerResponse.success();
    }

    @DeleteMapping("{specParamIds}")
    @RequiresPermissions("specParam:delete")
    @ControllerEndpoint(operation = "删除规格属性",exceptionMessage = "删除规格属性失败")
    public ServerResponse deleteSpecParam(@PathVariable String specParamIds) {
        specParamService.deleteSpecParam(specParamIds);
        return ServerResponse.success();
    }

}

