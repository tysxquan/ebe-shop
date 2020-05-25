package com.sxquan.manage.spec.controller;


import com.sxquan.manage.spec.pojo.SpecGroup;
import com.sxquan.manage.spec.pojo.SpecParam;
import com.sxquan.manage.spec.service.ISpecGroupService;
import com.sxquan.manage.spec.service.ISpecParamService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author sxquan
 * @since 2020/3/11 0:25
 */
@Controller("/specView")
public class ViewController {

    @Autowired
    ISpecGroupService specGroupService;

    @Autowired
    ISpecParamService specParamService;

    @GetMapping("spec/group")
    @RequiresPermissions("specGroup:view")
    public String specGroup(){ return "spec/group/group";}

    @GetMapping("spec/group/add")
    @RequiresPermissions("specGroup:add")
    public String specGroupAdd(){ return "spec/group/groupAdd";}

    @GetMapping("spec/group/update/{groupId}")
    @RequiresPermissions("specGroup:update")
    public String specGroupUpdate(@PathVariable Long groupId, Model model) {
        SpecGroup specGroup = specGroupService.findSpecGroupById(groupId);
        model.addAttribute("specGroup",specGroup);
        return "spec/group/groupUpdate";
    }

    @GetMapping("spec/param/add")
    @RequiresPermissions("specParam:add")
    public String specParamAdd(){ return "spec/param/paramAdd";}

    @GetMapping("spec/param/update/{specParamId}")
    @RequiresPermissions("specParam:update")
    public String specParamUpdate(@PathVariable Long specParamId, Model model){
        SpecParam specParam = specParamService.findSpecParamById(specParamId);
        model.addAttribute("specParam",specParam);
        return "spec/param/paramUpdate";
    }
}
