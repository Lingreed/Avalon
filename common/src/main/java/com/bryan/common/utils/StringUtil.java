package com.bryan.common.utils;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils {

	private static final char SEPARATOR = '_';
	private static final String CHARSET_NAME = "UTF-8";

	/**
	 * 字符串空处理，去除首尾空格 如果str为null，返回"",否则返回str
	 *
	 * @param str
	 * @return
	 */
	public static String isNull(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	/**
	 * 将对象转为字符串
	 *
	 * @param o
	 * @return
	 */
	public static String isNull(Object o) {
		if (o == null) {
			return "";
		}
		String str;
		if (o instanceof String) {
			str = (String) o;
		} else {
			str = o.toString();
		}
		return str.trim();
	}

	/**
	 * 检验是否为空或空字符串
	 *
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return StringUtil.isNull(str).equals("");
	}

	/**
	 * 检验是否非空字符串
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtil.isNull(str).equals("");
	}

	/**
	 * 转换为字节数组
	 *
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 转换为字节数组
	 *
	 * @param bytes
	 * @return
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	/**
	 * 是否包含字符串
	 *
	 * @param str
	 *            验证字符串
	 * @param strs
	 *            字符串组
	 * @return 包含返回true
	 */
	public static boolean inString(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equals(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		return m.replaceAll("");
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 *
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html) {
		if (html == null) {
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 *
	 * @param txt
	 * @return
	 */
	public static String toHtml(String txt) {
		if (txt == null) {
			return "";
		}
		return replace(replace(Encodes.escapeHtml(txt), "\n", "<br/>"), "\t", "&nbsp; &nbsp; ");
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 *
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}


	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null || String.valueOf(val).equals("") || String.valueOf(val).equals("null")) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}


	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 驼峰命名法工具
	 *
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 驼峰命名法工具
	 *
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 驼峰命名法工具
	 *
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toUnderScoreCase(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 如果不为空，则设置值
	 *
	 * @param target
	 * @param source
	 */
	public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)) {
		}
	}

	/**
	 * 转换为JS获取对象值，生成三目运算返回结果
	 *
	 * @param objectString
	 *            对象串 例如：row.user.id
	 *            返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
	 */
	public static String jsGetVal(String objectString) {
		StringBuilder result = new StringBuilder();
		StringBuilder val = new StringBuilder();
		String[] vals = split(objectString, ".");
		for (String val1 : vals) {
			val.append(".").append(val1);
			result.append("!").append(val.substring(1)).append("?'':");
		}
		result.append(val.substring(1));
		return result.toString();
	}

	/**
	 * 首字母大写
	 *
	 * @param s
	 * @return
	 */
	public static String firstCharUpperCase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
	}

	public static String beginDateString(String str) {
		return str + " 00:00:01";
	}

	public static String endDateString(String str) {
		return str + " 23:59:59";
	}

	/**
	 * 字符串解码UTF-8
	 *
	 * @param str
	 * @return
	 */
	public static String urlDecoderUTF8(String str) {
		try {
			if (StringUtil.isNotBlank(str)) {
				return URLDecoder.decode(str, "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static int getNum(int start, int end) {
		return (int) (Math.random() * (end - start + 1) + start);
	}

	/**
	 * 返回手机号码
	 */
	private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153,180,186,187,189"
			.split(",");

	public static String getTel() {
		int index = getNum(0, telFirst.length - 1);
		String first = telFirst[index];
		String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
		String thrid = String.valueOf(getNum(1, 9100) + 10000).substring(1);
		return first + second + thrid;
	}

	public static String getHideMobile(String mobile) {
		if (StringUtil.isNotBlank(mobile) && mobile.length() == 11) {
			return mobile.substring(0, 3) + "*****" + mobile.substring(7, 11);
		}
		return mobile;
	}

    public static String getHideUsername(String username) {
	    if(isNotEmpty(username)){
	        return username.substring(0,2) + "********" + username.substring(username.length()-2,username.length());
        }
        return "";
    }

    public static String getHideCardNO(String cardNO) {
	    if(StringUtil.isNotBlank(cardNO) && cardNO.length() >= 16){//借记卡最低16位
            return cardNO.substring(0, 6) + "******" + cardNO.substring(cardNO.length()-4, cardNO.length());
        }
        return cardNO;
    }
    
    /**
     * @Title: getHideIdNo  
     * @Description: 隐藏身份证
     * @return
     */
    public static String getHideIdNo(String idNo){
    	if(StringUtil.isNoneEmpty(idNo) && idNo.length()>12){
    		int length = idNo.length();
    	    return idNo.substring(0, 8) + "******" + idNo.substring(length -4 , length);
    	}
    	return idNo;
    }
    
    /**
     * @Title: getHideRealName  
     * @Description: 隐藏真实姓名
     * @param realName
     * @return
     */
    public static String getHideRealName(String realName){
    	if(StringUtil.isNotEmpty(realName) && realName.length()>1){
    		return realName.substring(0, 1) + "**";
    	}
    	return realName;
    }
    
    /**
     * @Title: getHideCompany  
     * @Description: 隐藏公司名称
     * @param companyName
     * @return
     */
    public static String getHideCompany(String companyName){
    	if(StringUtil.isNotEmpty(companyName) && companyName.length()>=6){
    		int length = companyName.length();
    		return companyName.substring(0,2) + "******" + companyName.substring(length -4, length);
    	}
    	return companyName;
    }
    
    /**
     * @Title: getHideOrgCode  
     * @Description: 隐藏组织机构代码
     * @param orgCode
     * @return
     */
    public static String getHideOrgCode(String orgCode){
    	if(StringUtil.isNotEmpty(orgCode) && orgCode.length()>6){
    		int length = orgCode.length();
    		return orgCode.substring(0,2) + "****" + orgCode.substring(length -4, length);
    	}
    	return orgCode;
    }
    
    /**
     * @Title: getHidelicenseNumber  
     * @Description: 隐藏企业注册码
     * @param licenseNumber
     * @return
     */
    public static String getHidelicenseNumber(String licenseNumber){
    	if(StringUtil.isNotEmpty(licenseNumber) && licenseNumber.length()>8){
    		int length = licenseNumber.length();
    		return licenseNumber.substring(0,4) + "*******" + licenseNumber.substring(length -4, length);
    	}
    	return licenseNumber;
    }
    
    /**
     * @Title: getHideHouseNo  
     * @Description: 隐藏房产证号码
     * @param houseNo
     * @return
     */
    public static String getHideHouseNo(String houseNo){
    	if(StringUtil.isNotEmpty(houseNo) && houseNo.length()>6){
    		int length = houseNo.length();
    		return houseNo.substring(0,3) + "****" + houseNo.substring(length -3, length);
    	}
    	return houseNo;
    }
    
    /**
     * @Title: getHidePlateNumber  
     * @Description: 隐藏车牌号码
     * @param plateNumber
     * @return
     */
    public static String getHidePlateNumber(String plateNumber){
    	if(StringUtil.isNotEmpty(plateNumber) && plateNumber.length()>6){
    		int length = plateNumber.length();
    		return plateNumber.substring(0,3) + "***" + plateNumber.substring(length -2, length);
    	}
    	return plateNumber;
    }

    public static String getHideEmail(String email){
		if(StringUtil.isNotBlank(email)){
			if(email.contains("@")){
				String[] mails = email.split("@");
				String mailName = mails[0];
				String mailSufix = mails[1];
				if(StringUtil.isBlank(mailName) || mailName.length()==1){
					return email;
				}
				if(mailName.length()==2){
					return (mailName.subSequence(0, 1) + "*" + "@" +mailSufix);
				}
				if(mailName.length() >= 3){
					return mailName.substring(0,2) + "*" + 
				           mailName.substring(2,mailName.length()).replaceAll("\\S", "*")+ "@" +
				           mailSufix;
				}
			}else{
				return email;
			}
		}
		return email;
	}

	public static Integer formatVersion(String version) {
		return StringUtil.isBlank(version) ? 0 : Integer.parseInt(version.replace(".", ""));
	}

	public static String getFristImgStr(String content) {
		// 用正则匹配

		Pattern ATTR_PATTERN = Pattern.compile("<img[^<>]*?\\ssrc=['\"]?(.*?)['\"]?\\s.*?>", Pattern.CASE_INSENSITIVE);
		Matcher matcher = ATTR_PATTERN.matcher(content);
		String str = "";
		while (matcher.find() && isBlank(str)) {
			str += matcher.group(1) + ",";
		}
		return str;
	}

	/**
	 * @Title: getUuid  
	 * @Description: 获取uuid
	 * @return
	 */
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * @Title: getLike  
	 * @Description: 返回like 模糊查询
	 * @param value
	 * @return
	 */
	public static String getLike(String value){
		return "%" + value + "%";
	}
	
	/**
	 * 拼接数组
	 */
	public static String concatByStr(List<String> list, String str){
		if(list == null || list.size() == 0 ){
			return null;
		}
		String concatVal = "";
		int i = 1;
		for (String value : list) {
			if(i ==  list.size()){
				concatVal += value;
			}else{
				concatVal += value + str;
			}
			i ++;
		}
		return concatVal;
	}
	
	/**
	 * @Title: splitByStr  
	 * @Description: 分割成list
	 * @param value
	 * @param regex
	 * @return
	 */
	public static List<String> splitByStr(String value, String regex){
		if(StringUtil.isEmpty(value)){
			return null;
		}
		String[] array = value.split(regex);
		List<String> valueList = new ArrayList<>();
		for (String valu : array) {
			valueList.add(valu);
		}
		return valueList;
	}
	
}
