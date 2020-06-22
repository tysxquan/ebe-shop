package com.sxquan.manage.spec.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.pojo.spec.SpecGroup;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.spec.service.ISpecGroupService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 * 规格组表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2020-03-10
 */
@RestController
@RequestMapping("/spec/group")
public class SpecGroupController {

    @Autowired
    ISpecGroupService specGroupService;

    @GetMapping("list")
    @RequiresPermissions("specGroup:view")
    public ServerResponse specGroupList(SpecGroup specGroup, RequestPage requestPage) {
        IPage<SpecGroup> page = specGroupService.listSpecGroup(specGroup,requestPage);
        return ServerResponse.success(new LayuiPage<>(page.getRecords(),page.getTotal()));
    }

    @GetMapping("/form/all")
    public ServerResponse specGroupFormAll() {
        return ServerResponse.success( specGroupService.findSpecGroupFormAll());
    }

    @GetMapping("/form/{spuId}")
    public ServerResponse getSpecGroupBySpuId(@PathVariable Long spuId){
        return ServerResponse.success( specGroupService.findSpecGroupBySpuId(spuId));
    }

    @PostMapping()
    @RequiresPermissions("specGroup:add")
    @ControllerEndpoint(operation = "新增规格组",exceptionMessage = "新增规则组失败")
    public ServerResponse specGroupAdd(SpecGroup specGroup) {
        specGroupService.save(specGroup);
        return ServerResponse.success();
    }

    @PutMapping()
    @RequiresPermissions("specGroup:update")
    @ControllerEndpoint(operation = "修改规格组",exceptionMessage = "修改规格组失败")
    public ServerResponse specGroupUpdate(SpecGroup specGroup) {
        specGroupService.updateById(specGroup);
        return ServerResponse.success();
    }

    @DeleteMapping("/{specGroupIds}")
    @RequiresPermissions("specGroup:delete")
    @ControllerEndpoint(operation = "删除规格组",exceptionMessage = "删除规格组失败")
    public ServerResponse specGroupDelete(@PathVariable String specGroupIds) {
        String[] split = StringUtils.split(specGroupIds, StringPool.COMMA);
        specGroupService.removeByIds(Arrays.asList(split));
        return ServerResponse.success();
    }
}

