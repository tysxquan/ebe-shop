package com.sxquan.manage.common.util;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sxquan.core.exception.ShopException;
import com.sxquan.core.util.ShopUtil;
import com.sxquan.manage.common.properties.EbeProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author sxquan
 * @description 上传工具
 * @since 2020/2/28 16:55
 */
@Slf4j
@Component
public class UploadUtil {

    private static EbeProperties properties;

    /**
     *  图片类型
     */
    public static final String IMAGE = "image";

    @Autowired
    public void setDispatchingProperties(EbeProperties properties) {
        UploadUtil.properties = properties;
    }

    /**
     * 上传单个图片
     *
     * @param multipartFile 图片
     * @param subPath 当前路径
     * @return 生成新的文件名
     */
    public static String uploadImg(MultipartFile multipartFile,String subPath) {
        String filename;
        try {
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //获得文件类型
            String contentType = multipartFile.getContentType();
            assert contentType != null;
            //判断是否是图片类型
            if (!IMAGE.equalsIgnoreCase(StringUtils.substringBefore(contentType, StringPool.SLASH))) {
                throw new ShopException("请上传图片类型的文件!");
            }
            //获得文件后缀名
            String suffixName = StringUtils.substringAfter(contentType,StringPool.SLASH);
            //得到文件名
            filename = uuid + "." + suffixName;
            //合并目录
            Path path = Paths.get(properties.getProfile(), subPath);
            //文件保存路径localPath项目路径
            File file = new File(path.toString() ,filename);

            //判断是否存在目录
            if (!file.getParentFile().exists()) {
                //创建目录
                boolean isSuccess = file.getParentFile().mkdirs();
                if (!isSuccess) {
                    log.warn("创建目录失败，可能目录已存在!");
                }
            }
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error(ShopUtil.getTrace(e));
            throw new ShopException("文件上传异常!");
        }
        return filename;
    }

    /**
     * 删除文件
     * @param subPath 当前路径
     * @param fileName 文件名
     */
    public static void delFile(String subPath,String fileName){
        Path path = Paths.get(properties.getProfile(), subPath);
        File file = new File(path.toString(), fileName);
        if (file.exists()) {
            file.delete();
        } else {
           throw new ShopException("该文件不存在！");
        }
    }
}
