<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.${modulePackage}.${mapperPackage}.${className}Mapper">

    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${basePackage}.${modulePackage}.${entityPackage}.${className}">
        <#if columns??>
        <#list columns as column>
            <#if column.isKey = true>
        <id column="${column.name}" property="${column.field?uncap_first}"/>
            <#else>
        <result column="${column.name}" property="${column.field?uncap_first}"/>
            </#if>
        </#list>
        </#if>
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="BaseColumnList">
        <#if columns??>
            <#list columns as column>${column.name}<#if column_has_next>, </#if></#list>
        </#if>
    </sql>
</mapper>
