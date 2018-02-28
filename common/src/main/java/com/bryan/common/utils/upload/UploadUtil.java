package com.bryan.common.utils.upload;

import com.bryan.common.coder.Base64Util;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/28
 */
public class UploadUtil {

    private static Logger logger = LoggerFactory.getLogger(UploadUtil.class);

    /**
     * 图片上传
     * @param type 图片类型
     * @param multipartFile 图片文件
     * @param markTag 01 需要加水印，02不需要
     * @return
     */
    public static String imgUpload(String type, MultipartFile multipartFile, String markTag) {
        String path = "";
        CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file = fi.getStoreLocation();
        // 检测是否为合法图片
        ImageUtil.check(file);
        // 图片上传至七牛云
        try {
            if("01".equals(markTag)){
                // 需要加水印
                String stampPath = UploadUtil.class.getResource("/waterMark.png").getPath();
                byte[] img = ImageUtil.markImageByIcon(stampPath, fi.getStoreLocation(), -45);
                path = QiniuUtil.upload(null, type, img);
            }else{
                // 不需要加水印
                path = QiniuUtil.upload(fi, type, null);
            }
        } catch (IOException e) {
            logger.error("七牛云图片上传失败，文件名{}", e);
        }
        return path;
    }

    /**
     * 图片上传
     * @param type 图片类型
     * @param base64Str 图片base64 代码
     * @return
     */
    public static String imgUploadBase64(String type, String base64Str) {
        String path = "";
        // 检测是否为合法图片
        ImageUtil.checkForBase64(base64Str);
        // 图片上传至七牛云
        try {
            byte[] bs = Base64Util.decode(base64Str);
            path = QiniuUtil.upload(null, type, bs);
        } catch (IOException e) {
            logger.error("七牛云图片上传失败，文件名{}", e);
        }
        return path;
    }

    /**
     * 图片上传
     * @param type 图片类型
     * @param imgByte 图片文件
     * @return
     */
    public static String imgUpload(String type, byte[] imgByte) {
        String path = "";
        // 图片上传至七牛云
        try {
            path = QiniuUtil.upload(null, type, imgByte);
        } catch (Exception e) {
            logger.error("qiniu图片上传错误:",e);
        }
        return path;
    }

}
