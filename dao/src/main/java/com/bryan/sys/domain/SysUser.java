package com.bryan.sys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SysUser  
 * Function: 系统用户实体
 */
 @Table(name = "sys_user")
public class SysUser implements Serializable {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * 机构id
     */
    @Column(name = "org_id")
    private Integer orgId;
    
    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;
    
    /**
     * 登录密码
     */
    @Column(name = "login_pwd")
    private String loginPwd;
    
    /**
     * 用户状态10启用,20禁用,30冻结
     */
    private String state;
    
    /**
     * 工号
     */
    @Column(name = "job_no")
    private String jobNo;
    
    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;
    
    /**
     * 性别:性别:01男,02女
     */
    private String sex;
    
    /**
     * 手机号
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 头像地址
     */
    @Column(name = "head_pic")
    private String headPic;
    
    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;
    
    /**
     * 最后登录ip
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 创建时间
     */
    private Date ctime;
    
    /**
     * 更新时间
     */
    private Date mtime;
    
    /**
     * 扩展字段1
     */
    private String ext1;
    
    /**
     * 扩展字段2
     */
    private String ext2;
    
    /**
     * 扩展字段3
     */
    private String ext3;
    
   /**
    * 获取id
    *
    * @return id
    */
    public Integer getId(){
        return id;
    }
    
    /**
     * 设置id
     * 
     * @param id 要设置的id
     */
    public void setId(Integer id){
        this.id = id;
    }
    
   /**
    * 获取机构id
    *
    * @return 机构id
    */
    public Integer getOrgId(){
        return orgId;
    }
    
    /**
     * 设置机构id
     * 
     * @param orgId 要设置的机构id
     */
    public void setOrgId(Integer orgId){
        this.orgId = orgId;
    }

   /**
    * 获取用户名
    *
    * @return 用户名
    */
    public String getUserName(){
        return userName;
    }
    
    /**
     * 设置用户名
     * 
     * @param userName 要设置的用户名
     */
    public void setUserName(String userName){
        this.userName = userName;
    }

   /**
    * 获取登录密码
    *
    * @return 登录密码
    */
    public String getLoginPwd(){
        return loginPwd;
    }
    
    /**
     * 设置登录密码
     * 
     * @param loginPwd 要设置的登录密码
     */
    public void setLoginPwd(String loginPwd){
        this.loginPwd = loginPwd;
    }

   /**
    * 获取用户状态10启用,20禁用,30冻结
    *
    * @return 用户状态10启用,20禁用,30冻结
    */
    public String getState(){
        return state;
    }
    
    /**
     * 设置用户状态10启用,20禁用,30冻结
     * 
     * @param state 要设置的用户状态10启用,20禁用,30冻结
     */
    public void setState(String state){
        this.state = state;
    }

   /**
    * 获取工号
    *
    * @return 工号
    */
    public String getJobNo(){
        return jobNo;
    }
    
    /**
     * 设置工号
     * 
     * @param jobNo 要设置的工号
     */
    public void setJobNo(String jobNo){
        this.jobNo = jobNo;
    }

   /**
    * 获取真实姓名
    *
    * @return 真实姓名
    */
    public String getRealName(){
        return realName;
    }
    
    /**
     * 设置真实姓名
     * 
     * @param realName 要设置的真实姓名
     */
    public void setRealName(String realName){
        this.realName = realName;
    }

   /**
    * 获取性别:性别:01男,02女
    *
    * @return 性别:性别:01男,02女
    */
    public String getSex(){
        return sex;
    }
    
    /**
     * 设置性别:性别:01男,02女
     * 
     * @param sex 要设置的性别:性别:01男,02女
     */
    public void setSex(String sex){
        this.sex = sex;
    }

   /**
    * 获取手机号
    *
    * @return 手机号
    */
    public String getMobilePhone(){
        return mobilePhone;
    }
    
    /**
     * 设置手机号
     * 
     * @param mobilePhone 要设置的手机号
     */
    public void setMobilePhone(String mobilePhone){
        this.mobilePhone = mobilePhone;
    }

   /**
    * 获取邮箱
    *
    * @return 邮箱
    */
    public String getEmail(){
        return email;
    }
    
    /**
     * 设置邮箱
     * 
     * @param email 要设置的邮箱
     */
    public void setEmail(String email){
        this.email = email;
    }

   /**
    * 获取头像地址
    *
    * @return 头像地址
    */
    public String getHeadPic(){
        return headPic;
    }
    
    /**
     * 设置头像地址
     * 
     * @param headPic 要设置的头像地址
     */
    public void setHeadPic(String headPic){
        this.headPic = headPic;
    }

   /**
    * 获取最后登录时间
    *
    * @return 最后登录时间
    */
    public Date getLastLoginTime(){
        return lastLoginTime;
    }
    
    /**
     * 设置最后登录时间
     * 
     * @param lastLoginTime 要设置的最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime){
        this.lastLoginTime = lastLoginTime;
    }

   /**
    * 获取最后登录ip
    *
    * @return 最后登录ip
    */
    public String getLastLoginIp(){
        return lastLoginIp;
    }
    
    /**
     * 设置最后登录ip
     * 
     * @param lastLoginIp 要设置的最后登录ip
     */
    public void setLastLoginIp(String lastLoginIp){
        this.lastLoginIp = lastLoginIp;
    }

   /**
    * 获取备注
    *
    * @return 备注
    */
    public String getRemark(){
        return remark;
    }
    
    /**
     * 设置备注
     * 
     * @param remark 要设置的备注
     */
    public void setRemark(String remark){
        this.remark = remark;
    }

   /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCtime(){
        return ctime;
    }
    
    /**
     * 设置创建时间
     * 
     * @param ctime 要设置的创建时间
     */
    public void setCtime(Date ctime){
        this.ctime = ctime;
    }

   /**
    * 获取更新时间
    *
    * @return 更新时间
    */
    public Date getMtime(){
        return mtime;
    }
    
    /**
     * 设置更新时间
     * 
     * @param mtime 要设置的更新时间
     */
    public void setMtime(Date mtime){
        this.mtime = mtime;
    }

   /**
    * 获取扩展字段1
    *
    * @return 扩展字段1
    */
    public String getExt1(){
        return ext1;
    }
    
    /**
     * 设置扩展字段1
     * 
     * @param ext1 要设置的扩展字段1
     */
    public void setExt1(String ext1){
        this.ext1 = ext1;
    }

   /**
    * 获取扩展字段2
    *
    * @return 扩展字段2
    */
    public String getExt2(){
        return ext2;
    }
    
    /**
     * 设置扩展字段2
     * 
     * @param ext2 要设置的扩展字段2
     */
    public void setExt2(String ext2){
        this.ext2 = ext2;
    }

   /**
    * 获取扩展字段3
    *
    * @return 扩展字段3
    */
    public String getExt3(){
        return ext3;
    }
    
    /**
     * 设置扩展字段3
     * 
     * @param ext3 要设置的扩展字段3
     */
    public void setExt3(String ext3){
        this.ext3 = ext3;
    }

}