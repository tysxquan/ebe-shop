package com.sxquan.manage.business.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 中国行政地址信息表
 * </p>
 *
 * @author sxquan
 * @since 2020-03-06
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
@TableName("region")
@ApiModel(value="Region对象", description="中国行政地址信息表")
public class Region implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "唯一性编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "行政区划id")
    @TableField("city_code")
    private String cityCode;

    @ApiModelProperty(value = "父级id")
    @TableField("parent_code")
    private String parentCode;

    @ApiModelProperty(value = "行政区划全称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "省市区全称聚合")
    @TableField("merger_name")
    private String mergerName;

    @ApiModelProperty(value = "行政区划简称")
    @TableField("short_name")
    private String shortName;

    @ApiModelProperty(value = "行政区划简称聚合")
    @TableField("merger_short_name")
    private String mergerShortName;

    @ApiModelProperty(value = "行政区划级别country:国家,province:省份,city:市,district:区县,street:街道")
    @TableField("level")
    private String level;

    @ApiModelProperty(value = "级别 0.国家，1.省(直辖市) 2.市 3.区(县),4.街道")
    @TableField("level_type")
    private Integer levelType;

    @ApiModelProperty(value = "电话区划号码")
    @TableField("telephone_code")
    private String telephoneCode;

    @ApiModelProperty(value = "邮编")
    @TableField("zip_code")
    private String zipCode;

    @ApiModelProperty(value = "拼音")
    @TableField("name_pinyin")
    private String namePinyin;

    @ApiModelProperty(value = "简拼")
    @TableField("name_jianpin")
    private String nameJianpin;

    @ApiModelProperty(value = "城市中心点")
    @TableField("center")
    private String center;

    @ApiModelProperty(value = "首字母")
    @TableField("name_first_char")
    private String nameFirstChar;

    @ApiModelProperty(value = "经度")
    @TableField("longitude")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    @TableField("latitude")
    private String latitude;

    @ApiModelProperty(value = "状态 1可修改 2不可修改 3已删除")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "历史版本")
    @TableField("version")
    private String version;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "是否有子项")
    private Boolean haveChild;
}
