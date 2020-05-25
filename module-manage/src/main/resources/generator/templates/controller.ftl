package ${basePackage}.${modulePackage}.${controllerPackage};

import com.sxquan.manage.common.annotation.ControllerEndpoint;

import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import ${basePackage}.${modulePackage}.${entityPackage}.${className};
import ${basePackage}.${modulePackage}.${servicePackage}.I${className}Service;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

/**
 * ${tableComment} 前端控制器
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Validated
@Controller("${className?uncap_first}")
public class ${className}Controller {

    @Autowired
    private I${className}Service ${className?uncap_first}Service;

    @GetMapping("${className?uncap_first}")
    public String ${className?uncap_first}Index(){
        return "${className?uncap_first}/${className?uncap_first}";
    }


    @ResponseBody
    @GetMapping("all")
    @RequiresPermissions("${className?uncap_first}:list")
    public ServerResponse get${className}All(${className} ${className?uncap_first}) {
        return ServerResponse.success(${className?uncap_first}Service.find${className}All(${className?uncap_first}));
    }

    @ResponseBody
    @GetMapping("list")
    @RequiresPermissions("${className?uncap_first}:list")
    public ServerResponse ${className?uncap_first}List( ${className} ${className?uncap_first}, RequestPage request) {
    IPage<${className}> page = this.${className?uncap_first}Service.find${className}List(${className?uncap_first}, request);
        return ServerResponse.success(new LayuiPage<>(page.getRecords(),page.getTotal()));
    }

    @ResponseBody
    @PostMapping()
    @RequiresPermissions("${className?uncap_first}:add")
    @ControllerEndpoint(operation = "新增${className}", exceptionMessage = "新增${className}失败")
    public ServerResponse add${className}(@Valid ${className} ${className?uncap_first}) {
        this.${className?uncap_first}Service.add${className}(${className?uncap_first});
        return ServerResponse.success();
    }

    @ResponseBody
    @DeleteMapping("{ids}")
    @RequiresPermissions("${className?uncap_first}:delete")
    @ControllerEndpoint(operation = "删除${className}", exceptionMessage = "删除${className}失败")
    public ServerResponse delete${className}(@PathVariable String ids) {
        String[] split = StringUtils.split(ids, StringPool.COMMA);
        this.${className?uncap_first}Service.delete${className}(Arrays.asList(split));
        return ServerResponse.success();
    }

    @ResponseBody
    @PutMapping()
    @RequiresPermissions("${className?uncap_first}:update")
    @ControllerEndpoint(operation = "修改${className}", exceptionMessage = "修改${className}失败")
    public ServerResponse update${className}(${className} ${className?uncap_first}) {
        this.${className?uncap_first}Service.update${className}(${className?uncap_first});
        return ServerResponse.success();
    }

}
