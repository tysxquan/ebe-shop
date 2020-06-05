package com.sxquan.core.pojo.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sxquan.core.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 代码生成配置表
 * </p>
 *
 * @author sxquan
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("generator_config")
@ApiModel(value="GeneratorConfig对象", description="代码生成配置表")
public class GeneratorConfig implements Serializable {

    private static final long serialVersionUID = -1761654575555474802L;

    /**
     *有前缀
     */
    public static final String TRIM_YES = "1";
    /**
     *无前缀
     */
    public static final String TRIM_NO = "0";

    /**
     * java文件路径，固定值
     */
    private transient String javaPath = "src/main/java/";
    /**
     * 配置文件存放路径，固定值
     */
    private transient String resourcesPath = "src/main/resources/";

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "作者")
    @TableField("author")
    @Size(max = 20, message = "{maxlength}")
    private String author;

    @ApiModelProperty(value = "基础包名")
    @TableField("base_package")
    @Size(max = 50, message = "{maxlength}")
    private String basePackage;

    @ApiModelProperty(value = "模块名")
    @TableField("module_package")
    @Size(max = 30, message = "{maxlength}")
    private String modulePackage;

    @ApiModelProperty(value = "entity文件存放路径")
    @TableField("entity_package")
    @Size(max = 20, message = "{maxlength}")
    private String entityPackage;

    @ApiModelProperty(value = "mapper文件存放路径")
    @TableField("mapper_package")
    @Size(max = 20, message = "{maxlength}")
    private String mapperPackage;

    @ApiModelProperty(value = "mapper xml文件存放路径")
    @TableField("mapper_xml_package")
    @Size(max = 20, message = "{maxlength}")
    private String mapperXmlPackage;

    @ApiModelProperty(value = "servcie文件存放路径")
    @TableField("service_package")
    @Size(max = 20, message = "{maxlength}")
    private String servicePackage;

    @ApiModelProperty(value = "serviceImpl文件存放路径")
    @TableField("service_impl_package")
    @Size(max = 20, message = "{maxlength}")
    private String serviceImplPackage;

    @ApiModelProperty(value = "controller文件存放路径")
    @TableField("controller_package")
    @Size(max = 20, message = "{maxlength}")
    private String controllerPackage;

    @ApiModelProperty(value = "是否去除前缀 1是 0否")
    @TableField("is_trim")
    private String isTrim;

    @ApiModelProperty(value = "前缀内容")
    @TableField("trim_value")
    private String trimValue;

    /**
     * 文件生成日期
     */
    private transient String date = DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN);

    /**
     * 表名
     */
    private transient String tableName;
    /**
     * 表注释
     */
    private transient String tableComment;
    /**
     * 数据表对应的类名
     */
    private transient String className;
}
