package com.bryan.common.constant;

import com.bryan.common.utils.StringUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApiResult implements Serializable{

    private static final long serialVersionUID = -3071342128966753547L;

    private static final Logger logger = LoggerFactory.getLogger(ApiResult.class);

    /**
	 * 默认返回成功编码200
	 */
	private String code = "200";

	/**
	 * 默认返回消息提示操作成功
	 */
	private String msg = "操作成功";

	/**
	 * 响应体data
	 */
    private Object result = null;
	
	/**
	 * 错误的参数值
	 */
    private String errorParam = "";

	public ApiResult() {
	}

	public ApiResult(String code) {
		super();
		this.code = code;
	}

	public ApiResult(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	public String getErrorParam() {
		return errorParam;
	}

	public void setErrorParam(String errorParam) {
		this.errorParam = errorParam;
	}
	
	public ApiResult addCode(String code){
		this.setCode(code);
		return this;
	}
	
	public ApiResult addMsg(String msg){
		this.setMsg(msg);
		return this;
	}
	
	/**
	 * @Title: addResult  
	 * @Description: 不需要字典项处理
	 * @param data
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ApiResult addResult(Object data) {
		if(data instanceof PageInfo){//分页信息返回封装
			Map<String,Object> rstMap = new HashMap<>();
			PageInfo pageInfo = (PageInfo) data;
			rstMap.put("firstPage", pageInfo.getFirstPage());
			rstMap.put("hasNextPage", pageInfo.isHasNextPage()?1:0);
			rstMap.put("hasPreviousPage", pageInfo.isHasPreviousPage()?1:0);
			rstMap.put("navigatepageNums", pageInfo.getNavigatepageNums());
			rstMap.put("pageNum", pageInfo.getPageNum());
			rstMap.put("pageSize", pageInfo.getPageSize());
			rstMap.put("pages", pageInfo.getPages());
			rstMap.put("prePage", pageInfo.getPrePage());
			rstMap.put("total", pageInfo.getTotal());
			rstMap.put("list", pageInfo.getList());
			this.setResult(rstMap);
		}else{
			this.setResult(data);
		}
		return this;
	}
	
	/**
	 * 
	 * @Title: addDictResult  
	 * @Description: 含有字典项结果处理
	 * @param data
	 * @return
	 */
	public ApiResult addDictResult(Object data) {
		this.setResult(convertResult(data,Boolean.TRUE,Boolean.FALSE));
		return this;
	}
	
	/**
	 * 
	 * @Title: addDictFilterFieldResult  
	 * @Description: 含有字典项结果及屏蔽字段处理
	 * @param data
	 * @return
	 */
	public ApiResult addDictFilterFieldResult(Object data) {
		this.setResult(convertResult(data,Boolean.TRUE,Boolean.TRUE));
		return this;
	}
	
	/**
	 * 
	 * @Title: addFilterFieldResult
	 * @Description: 含有屏蔽字段处理
	 * @param data
	 * @return
	 */
	public ApiResult addFilterFieldResult(Object data) {
		this.setResult(convertResult(data,Boolean.FALSE,Boolean.TRUE));
		return this;
	}
	
	/**
	 * 对象特殊转换(包括：理字典项解释字段、是否添加字段)
	 * 
	 * @param obj 处理对象
	 * @param dictFlag 是否处理字典项解释字段(true：如果有字典项字段，则会添加一个字典项解释字段，key：原字段+Name，value：字段解释)
	 * @param filterFieldFlag 是否过滤字段(true:如果是需要屏蔽的字段，则不会添加到result中)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Object convertResult(Object obj,boolean dictFlag,boolean filterFieldFlag){
		try {
			if(obj instanceof Collection){//处理集合
				if(!((Collection<?>) obj).isEmpty()){
					Collection<Object> resaultCollection = (Collection<Object>) obj.getClass().newInstance();
					/*遍历集合对象并将字典字段转换*/
					for (Object object : (Collection<Object>)obj) {
						resaultCollection.add(convertResult(object,dictFlag,filterFieldFlag));//将对象中字典字段处理后，转换为map，存入新对象并返回
					}
					return resaultCollection;
				}
			}else if(obj instanceof PageInfo){//处理分页
				Map<String,Object> resaultMap = new HashMap<>();
				PageInfo<?> pageInfo = (PageInfo<?>) obj;
				resaultMap.put("firstPage", pageInfo.getFirstPage());
				resaultMap.put("hasNextPage", pageInfo.isHasNextPage()?1:0);
				resaultMap.put("hasPreviousPage", pageInfo.isHasPreviousPage()?1:0);
				resaultMap.put("navigatepageNums", pageInfo.getNavigatepageNums());
				resaultMap.put("pageNum", pageInfo.getPageNum());
				resaultMap.put("pageSize", pageInfo.getPageSize());
				resaultMap.put("pages", pageInfo.getPages());
				resaultMap.put("prePage", pageInfo.getPrePage());
				resaultMap.put("total", pageInfo.getTotal());
				/*分页集合处理*/
				resaultMap.put("list", convertResult(pageInfo.getList(),dictFlag,filterFieldFlag));
				return resaultMap;
			} else if(obj instanceof Map){//处理map
				/*如果是map，则遍历map中对象,递归处理*/
				if(!((Map<String, Object>) obj).isEmpty()){
					Map<String,Object> resaultMap = (Map<String, Object>)obj.getClass().newInstance();
					for (Map.Entry<String, Object> entry : ((Map<String, Object>) obj).entrySet()) {
						resaultMap.put(entry.getKey(), convertResult(entry.getValue(),dictFlag,filterFieldFlag));
					}
					return resaultMap;
				}
			} else if(obj.getClass().getName().startsWith("com.bryan")) {//处理业务对象
		        /*将对象中字段取出遍历，放入map中，如果字段为配置字典项字段则处理后新增字段存入map*/
				Map<String,Object> result = new HashMap<>();//创建返回对象
				/*获取对象属性数组*/
		        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors();
		        for (PropertyDescriptor property : propertyDescriptors) {
		            String key = property.getName();
		            if("TType".equals(key)){key = "tType";}// 设计时候没注意tType这一种反射之后变成了TType。
		            if (key.equals("class")) {
		            	continue;
		            }
		            /*得到property对应的getter方法*/  
		            Object value = property.getReadMethod().invoke(obj);
		            if(value != null){
		            	if(isBusWrapClass(value)){//判断是否是基本数据类型
		            		/*基本数据类型处理*/
			            	if(filterFieldFlag){//是否过滤字段
			            		if(!FilterField.isFilterField(obj.getClass(), key)){//过滤字段,判断是否是需要过滤的字段
			            			result.put(key, value);
			            		}
			            	}else{
			            		/*不过滤字段则直接添加*/
			            		result.put(key, value);
			            	}
			            	/*字典项解释字段*/
			    			if(dictFlag){
			    				/*获取字典项字段,如果不为空则为字典项字段*/
			    				String dictValue = DictConvert.getDictFieldValue(obj.getClass(), key, value);
			        			if(StringUtil.isNotEmpty(dictValue)){
			        				result.put(key + "Name", dictValue);
			        			}
			    			}
		            	}else{
		            		/*对象递归处理*/
		            		result.put(key, convertResult(value, dictFlag, filterFieldFlag));
		            	}
		            }
		        }
		        return result;
			}
		} catch (Exception e) {
			logger.info("addDictResult,error:{}",e.getMessage());
		}
		return obj;//处理失败或是基本数据类型直接返回
	}
	
	/**
	 * 判断对象是否是业务相关基本数据类型，包含String，BigDecimal,Date，包装类，基本数据类型
	 * @param clz
	 * @return
	 */
	private static boolean isBusWrapClass(Object clz) {
		try {
			if(clz instanceof String || clz instanceof BigDecimal || clz instanceof Date){
				return true;
			}
			return clz.getClass().isPrimitive() ||
					((Class<?>) clz.getClass().getField("TYPE").get(null)).isPrimitive();
		} catch (Exception e) {
			return false;
		}
	}
	
	public ApiResult addErrorParam(String param){
		this.setErrorParam(param);
		return this;
	}
	
	public static ApiResult newInstance() {
	    ApiResult apiResult = new ApiResult();
		//填充默认code,message
		apiResult.setCode(ApiCodeConstant.CODE_SUCCESS);
		apiResult.setMsg("操作成功");
		apiResult.setErrorParam("");
		apiResult.setResult("");
		return apiResult;
	}
	
	
}
