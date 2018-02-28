package com.bryan.common.coder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName: Base64Util
 * Function:  base64 工具类
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/28
 */
public class Base64Util {
    private static Logger logger = LoggerFactory.getLogger(Base64Util.class);

    /**
     * @param plainByte
     * @return
     * @Title: encode
     * @Description: base64 编码
     */
    public static String encode(byte[] plainByte) {

        BASE64Encoder encode = new BASE64Encoder();

        return encode.encode(plainByte);
    }

    /**
     * @param plain
     * @return
     * @Title: encode
     * @Description: base64 编码
     */
    public static String encodeFromString(String plain) {

        BASE64Encoder encode = new BASE64Encoder();

        return encode.encode(plain.getBytes());
    }

    /**
     * @param codeStr
     * @return
     * @Title: decode
     * @Description: base64 解码
     */
    public static byte[] decode(String codeStr) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            return decoder.decodeBuffer(codeStr);
        } catch (Exception e) {
            logger.error("base64解码错误", e);
            return null;
        }
    }

    /**
     * @param codeStr
     * @return
     * @Title: decode
     * @Description: base64 解码
     */
    public static String decodeToString(String codeStr) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            return decoder.decodeStr(codeStr);
        } catch (Exception e) {
            logger.error("base64解码错误", e);
            return null;
        }
    }
}
