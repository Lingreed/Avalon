package com.bryan.common.utils.upload;

import com.bryan.common.coder.Base64Util;
import com.bryan.common.exception.ServiceException;
import org.devlib.schmidt.imageinfo.ImageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片工具类
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/28
 */
public class ImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    //长度
    public static String imgWidthName = "imgWidth";

    //高度
    public static String imgHightName = "imgHignt";

    /**
     * 判断是否为合法图片
     * @param bs
     * @return
     */
    public static boolean fileIsImageForBase64(byte[] bs) {
        try {
            BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(bs));
            if (null == bufImg) {
                return false;
            }
        } catch (Exception e) {
            logger.error("fileIsImageForBase64,error",e);
        }
        return true;
    }

    public static String shiftD(String str) {
        int size = str.length();
        char[] chs = str.toCharArray();
        for (int i = 0; i < size; i++) {
            if (chs[i] <= 'Z' && chs[i] >= 'A') {
                chs[i] = (char) (chs[i] + 32);
            }
        }
        return new String(chs);
    }

    /**
     * 计算图片压缩后的值
     * @param imgWidth 图片长度
     * @param imgHight 图片高度
     * @param criticalImgWidth 图片需要压缩的临界大小
     * @return
     */
    public static Map<String, Float> getCalculateSize(float imgWidth, float imgHight, float criticalImgWidth){
        Map<String, Float> map = new HashMap<>();
        if (imgWidth !=0 && imgHight !=0 && imgWidth>criticalImgWidth) {
            double ratio = imgWidth/imgHight;//得到宽高比例
            //计算压缩倍数
            imgWidth = criticalImgWidth;
            imgHight = (float) (criticalImgWidth/ratio);
        }
        map.put(imgWidthName, imgWidth);
        map.put(imgHightName, imgHight);
        return map;
    }

    public static void check(File file){
        try {
            FileInputStream in = new FileInputStream(file);
            ImageInfo ii = new ImageInfo();
            ii.setInput(in); // in can be InputStream or RandomAccessFile
            ii.setDetermineImageNumber(true); // default is false
            ii.setCollectComments(true); // default is false
            if (!ii.check()) {
                throw new ServiceException("上传文件不是图片,请重新选择");
            }
        } catch (Exception e) {
            throw new ServiceException("上传文件不是图片,请重新选择");
        }
    }

    /**
     * @Title: checkForBase64
     * @Description: 校验base64是否是图片
     * @param base64Str
     */
    public static void checkForBase64(String base64Str){
        try {
            byte[] bs = Base64Util.decode(base64Str);
            ImageInfo ii = new ImageInfo();
            ii.setInput(new ByteArrayInputStream(bs)); // in can be InputStream or RandomAccessFile
            ii.setDetermineImageNumber(true); // default is false
            ii.setCollectComments(true); // default is false
            if (!ii.check()) {
                throw new ServiceException("上传文件不是图片,请重新选择");
            }
        } catch (Exception e) {
            throw new ServiceException("上传文件不是图片,请重新选择");
        }
    }

    /**
     * 图片转化成base64字符串
     * @Title: GetImageStr
     * @Description: TODO
     * @param url
     * @return
     */
    public static String GetImageStr(String url) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = url;// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            logger.error("GetImageStr", e);
        }
        // 对字节数组Base64编码
        return Base64Util.encode(data);
    }

    /**
     * 给图片添加水印、可设置水印图片旋转角度
     *
     * @param iconPath
     *            水印图片路径
     * @param srcImgFile
     *            源图片路径
     * @param degree
     *            水印图片旋转角度
     */
    public static byte[] markImageByIcon(String iconPath, File srcImgFile, Integer degree) {
        OutputStream os = null;
        try {
            Image srcImg = ImageIO.read(srcImgFile);

            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 得到画笔对象
            Graphics2D g = buffImg.createGraphics();

            // 设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
                    0, null);

            if (null == degree) {
                // 设置水印旋转
                // g.rotate(Math.toRadians(degree), (double) buffImg.getWidth()
                // / 2, (double) buffImg.getHeight() / 2);
                degree = 0;
            }
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(iconPath);
            // 得到Image对象。
            Image img = imgIcon.getImage();
            float alpha = 0.6f; // 透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 表示水印图片的位置
            if (srcImg.getWidth(null) > 200 && srcImg.getHeight(null) > 50) {
                int x = 1;
                int y = 1;
                for (int i = 0; i < 1000; i++) {
                    // 设置水印旋转
                    g.rotate(Math.toRadians(degree), (double) x, (double) y);
                    g.drawImage(img, x, y, null);
                    g = buffImg.createGraphics();
                    x += 300;
                    if (x >= srcImg.getWidth(null)) {
                        x = 0;
                        y += 300;
                    }
                    if (x >= (srcImg.getWidth(null) + 200) && (y >= srcImg.getHeight(null) + 100)) {
                        break;
                    }
                }
            } else {
                g.drawImage(img, srcImg.getWidth(null) / 2, srcImg.getHeight(null) / 2, null);
            }
			/*
			 * g.drawImage(img, 0, 0, null); g.drawImage(img, 30, 30, null);
			 * g.drawImage(img, 60, 60, null); g.drawImage(img, 90, 90, null);
			 * g.drawImage(img, 120, 120, null); g.drawImage(img, 150, 300,
			 * null);
			 */
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            g.dispose();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(buffImg, "jpg", bos);
            byte[] data = bos.toByteArray();
            return data;
        } catch (Exception e) {
            logger.error("markImageByIcon", e);
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                logger.error("markImageByIcon", e);
            }
        }
        return null;
    }
}
