package com.bryan.common.utils.upload;

import com.bryan.common.config.SysConfigUtil;
import com.bryan.common.config.SysKey;
import com.bryan.common.utils.DateUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;

/**
 * ClassName: QiniuUtil
 * Function: 七牛云存储文件上传工具类.
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/28
 */
public class QiniuUtil {

    private static Logger _log = LoggerFactory.getLogger(QiniuUtil.class);

    /**
     * 七牛云帐号的ACCESS_KEY和SECRET_KEY
     */
    private static String ACCESS_KEY = SysConfigUtil.getString(SysKey.QINIU_ACCESS_KEY);
    private static String SECRET_KEY = SysConfigUtil.getString(SysKey.QINIU_SECRET_KEY);

    /**
     * 上传的空间
     */
    private static String BUCKET_NAME = SysConfigUtil.getString(SysKey.QINIU_BUCKET_NAME);

    public static String DOWNLOAD_URL = SysConfigUtil.getString(SysKey.QINIU_DOWNLOAD_URL);

    /**
     * 密钥配置
     */
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    /**
     * 创建上传对象
     */
    private static UploadManager uploadManager = new UploadManager();

    private static BucketManager bucketManager = null;

    /**
     * 简单上传，使用默认策略，只需要设置上传的空间名就可以了
     *
     * @return
     */
    public static String getUpToken() {
        return auth.uploadToken(BUCKET_NAME);
    }

    /**
     * 文件上传
     *
     * @param img byte数组
     * @param type 文件类型
     * @param fi 文件
     * @return 上传图片下载地址
     */
    public static String upload(DiskFileItem fi, String type, byte[] img) throws IOException {
        try {
            Response res;
            String KEY;
            // 直接使用文件上传
            if (img == null) {
                String fileName = fi.getName();
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                // 文件名：文件类型 + 年月日+UUID + 文件后缀
                KEY = type + "/" + DateUtil.getYear() + "/" + DateUtil.getMonth() + "/" + DateUtil.getDay() + "/"
                        + UUID.randomUUID() + suffix;
                res = uploadManager.put(fi.getStoreLocation(), KEY, getUpToken());
            } else{
                // 文件名：文件类型 + 年月日+UUID + 文件后缀
                KEY = type + "/" + DateUtil.getYear() + "/" + DateUtil.getMonth() + "/" + DateUtil.getDay() + "/"
                        + UUID.randomUUID() + ".jpg";
                res = uploadManager.put(img, KEY, getUpToken());
            }
            // 打印返回的信息
            _log.info("七牛云上传回调信息" + res.bodyString() + "--" + res.address);
            _log.info("KEY:" + KEY);
            String FileUrl = getKey(KEY);
            return DOWNLOAD_URL + FileUrl;
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            _log.error("七牛云请求失败：" + r.toString());
            try {
                // 响应的文本信息
                _log.info("七牛云上传回调信息" + r.bodyString());
                return null;
            } catch (QiniuException e1) {
                _log.error("七牛云上传错误",e1);
            }
        }
        return null;
    }

    /**
     * 七牛云下载链接生成
     *
     * @return
     */
    public static String getKey(String key) {
        // 调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
        String downloadRUL = auth.privateDownloadUrl(key);
        if (_log.isDebugEnabled()) {
            _log.debug("七牛云下载地址：" + downloadRUL);
        }
        return downloadRUL;
    }

    /**
     * 七牛云删除单个文件
     *
     * @param key
     * @return
     */
    public static boolean delete(String key) {
        bucketManager = new BucketManager(auth);
        try {
            bucketManager.delete(BUCKET_NAME, key);
            return true;
        } catch (QiniuException e) {
            // 捕获异常信息
            Response r = e.response;
            _log.info("七牛云删除异常信息：" + r.toString());
        }
        return false;
    }

    public static String getToken() {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET_NAME);
        System.out.println(upToken);
        return upToken;
    }

    public static String download(String url) {
        //调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
        String downloadRUL = auth.privateDownloadUrl(url, 3600);
        System.out.println(downloadRUL);
        return downloadRUL;
    }

    public static void main(String[] args) {
        System.out.println(download("http://ojjm444jx.bkt.clouddn.com/201701111839457c23b2.png"));
    }
}
