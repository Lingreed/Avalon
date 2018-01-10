package com.bryan.common.context;


import com.bryan.common.constant.GlobalConstant;
import com.bryan.common.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();

	public static int ADMIN_ROLE_ID = 1;

	/**
	 * 用户访问IP
	 */
	public static ThreadLocal<String> IP_THREADLOCAL = new ThreadLocal<>();

	/**
	 * 用户访问客户端类型
	 */
	public static ThreadLocal<String> CLIENT_TYPE_THREADLOCAL = new ThreadLocal<>();

	/**
	 * 广告商IDFA
	 */
	public static ThreadLocal<String> IDFA_THREADLOCAL = new ThreadLocal<>();

	/**
	 *手机唯一标识UDID
	 */
	public static ThreadLocal<String> UDID_THREADLOCAL = new ThreadLocal<>();
	
	/**
	 * 版本号version
	 */
	public static ThreadLocal<String> VERSION_THREADLOCAL = new ThreadLocal<>();
	
	/**
	 * authToken  token
	 */
	public static ThreadLocal<String> TOKEN_THREADLOCAL = new ThreadLocal<>();

    /**
     * 传输数据
     */
    public static final ThreadLocal<Map<String, Object>> TRANSFER_LOCAL = new ThreadLocal<>();

	/**
	 * 初始化字典类型对应的字典map
	 */
	public static final ThreadLocal<Map<String,Map<String,String>>> DICT_LOCAL = new ThreadLocal<>();

	/**
	 * 获取当前对象实例
	 * 
	 * @return
	 */
	public static Global getInstance() {
		return global;
	}

	/**
	 * 获取用户IP
	 * 
	 * @return
	 */
	public static String getIP() {
		Object retObj = Global.IP_THREADLOCAL.get();
		String ip = retObj == null ? "" : retObj.toString();
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "127.0.0.1";
		}
		if (StringUtil.isNotBlank(ip)) {
			return ip.split(",")[0];
		}
		return ip;
	}

	/**
	 * 获取Token
	 * 
	 * @return
	 */
	public static String getToken() {
		Object retObj = Global.TOKEN_THREADLOCAL.get();
		if(retObj == null){
			return "";
		}else{
			return String.valueOf(retObj);
		}
	}

	/**
	 * 获取设备类型 01PC,02H5,03Android,04iOS,99其他
	 */
	public static String getClientType() {
		String device = Global.CLIENT_TYPE_THREADLOCAL.get();
		if ("01".equals(device)) {
			return GlobalConstant.CLIENT_TYPE_PC;
		}
		if ("02".equals(device)) {
            return GlobalConstant.CLIENT_TYPE_HTML;
        }
		if ("03".equals(device)) {
			return GlobalConstant.CLIENT_TYPE_ANDROID;
		}
		if ("04".equals(device)) {
			return GlobalConstant.CLIENT_TYPE_IOS;
		}
		return GlobalConstant.CLIENT_TYPE_PC;
	}

	/**
	 * 获取传输数据
	 * 
	 * @return
	 */
	public static Map<String, Object> getTransfer() {
		Map<String, Object> map = TRANSFER_LOCAL.get();
		if (map == null) {
			map = new HashMap<>();
			TRANSFER_LOCAL.set(map);
		}
		return map;
	}

	/**
	 * 设置传输数据
	 * 
	 * @param key
	 * @param value
	 */
	public static void setTransfer(String key, Object value) {
		Map<String, Object> map = getTransfer();
		map.put(key, value);
		TRANSFER_LOCAL.set(map);
	}

}
