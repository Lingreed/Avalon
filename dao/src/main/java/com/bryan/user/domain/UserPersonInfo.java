package com.bryan.user.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ClassName: UserPersonInfo
 * Function: 用户个人信息表实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
@Table(name = "user_person_info")
public class UserPersonInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 证件号码
     */
    @Column(name = "id_number")
    private String idNumber;

    /**
     * 证件地址
     */
    @Column(name = "id_address")
    private String idAddress;

    /**
     * 省编码
     */
    @Column(name = "province_code")
    private String provinceCode;

    /**
     * 性别:01男,02女
     */
    private String sex;

    /**
     * 出生年月
     */
    private String birth;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 座机
     */
    private String tel;

    /**
     * 传真
     */
    private String fax;

    /**
     * 婚姻状况,00未知,01已婚,02未婚,03离异,04丧偶
     */
    private String marriage;

    /**
     * 有无子女,00未知,01有,02无
     */
    @Column(name = "child_tag")
    private String childTag;

    /**
     * 个人年收入
     */
    @Column(name = "annual_income")
    private BigDecimal annualIncome;

    /**
     * 所在单位
     */
    private String company;

    /**
     * 职位
     */
    private String position;

    /**
     * 有无工作,00未知,01全职,02兼职,03无工作
     */
    @Column(name = "work_tag")
    private String workTag;

    /**
     * 公司地址
     */
    @Column(name = "company_address")
    private String companyAddress;

    /**
     * 有无房产,00未知,01有,02无
     */
    @Column(name = "house_tag")
    private String houseTag;

    /**
     * 固定资产描述
     */
    @Column(name = "fixed_assets")
    private String fixedAssets;

    /**
     * 活跃资产描述
     */
    @Column(name = "active_assets")
    private String activeAssets;

    /**
     * 个人简介
     */
    @Column(name = "personal_profile")
    private String personalProfile;

    /**
     * 个人信用记录
     */
    @Column(name = "credit_record")
    private String creditRecord;

    /**
     * 担保详情
     */
    @Column(name = "guarant_detail")
    private String guarantDetail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 创建ip
     */
    private String cip;

    /**
     * 修改时间
     */
    private Date mtime;

    /**
     * 修改ip
     */
    private String mip;

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
     * 获取用户id
     *
     * @return 用户id
     */
    public Integer getUserId(){
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 要设置的用户id
     */
    public void setUserId(Integer userId){
        this.userId = userId;
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
     * 获取证件号码
     *
     * @return 证件号码
     */
    public String getIdNumber(){
        return idNumber;
    }

    /**
     * 设置证件号码
     *
     * @param idNumber 要设置的证件号码
     */
    public void setIdNumber(String idNumber){
        this.idNumber = idNumber;
    }

    /**
     * 获取证件地址
     *
     * @return 证件地址
     */
    public String getIdAddress(){
        return idAddress;
    }

    /**
     * 设置证件地址
     *
     * @param idAddress 要设置的证件地址
     */
    public void setIdAddress(String idAddress){
        this.idAddress = idAddress;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * 获取性别:01男,02女
     *
     * @return 性别:01男,02女
     */
    public String getSex(){
        return sex;
    }

    /**
     * 设置性别:01男,02女
     *
     * @param sex 要设置的性别:01男,02女
     */
    public void setSex(String sex){
        this.sex = sex;
    }

    /**
     * 获取出生年月
     *
     * @return 出生年月
     */
    public String getBirth(){
        return birth;
    }

    /**
     * 设置出生年月
     *
     * @param birth 要设置的出生年月
     */
    public void setBirth(String birth){
        this.birth = birth;
    }

    /**
     * 获取年龄
     *
     * @return 年龄
     */
    public Integer getAge(){
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 要设置的年龄
     */
    public void setAge(Integer age){
        this.age = age;
    }

    /**
     * 获取座机
     *
     * @return 座机
     */
    public String getTel(){
        return tel;
    }

    /**
     * 设置座机
     *
     * @param tel 要设置的座机
     */
    public void setTel(String tel){
        this.tel = tel;
    }

    /**
     * 获取传真
     *
     * @return 传真
     */
    public String getFax(){
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 要设置的传真
     */
    public void setFax(String fax){
        this.fax = fax;
    }

    /**
     * 获取婚姻状况,00未知,01已婚,02未婚,03离异,04丧偶
     *
     * @return 婚姻状况,00未知,01已婚,02未婚,03离异,04丧偶
     */
    public String getMarriage(){
        return marriage;
    }

    /**
     * 设置婚姻状况,00未知,01已婚,02未婚,03离异,04丧偶
     *
     * @param marriage 要设置的婚姻状况,00未知,01已婚,02未婚,03离异,04丧偶
     */
    public void setMarriage(String marriage){
        this.marriage = marriage;
    }

    /**
     * 获取有无子女,00未知,01有,02无
     *
     * @return 有无子女,00未知,01有,02无
     */
    public String getChildTag(){
        return childTag;
    }

    /**
     * 设置有无子女,00未知,01有,02无
     *
     * @param childTag 要设置的有无子女,00未知,01有,02无
     */
    public void setChildTag(String childTag){
        this.childTag = childTag;
    }

    /**
     * 获取个人年收入
     *
     * @return 个人年收入
     */
    public BigDecimal getAnnualIncome(){
        return annualIncome;
    }

    /**
     * 设置个人年收入
     *
     * @param annualIncome 要设置的个人年收入
     */
    public void setAnnualIncome(BigDecimal annualIncome){
        this.annualIncome = annualIncome;
    }

    /**
     * 获取所在单位
     *
     * @return 所在单位
     */
    public String getCompany(){
        return company;
    }

    /**
     * 设置所在单位
     *
     * @param company 要设置的所在单位
     */
    public void setCompany(String company){
        this.company = company;
    }

    /**
     * 获取职位
     *
     * @return 职位
     */
    public String getPosition(){
        return position;
    }

    /**
     * 设置职位
     *
     * @param position 要设置的职位
     */
    public void setPosition(String position){
        this.position = position;
    }

    /**
     * 获取有无工作,00未知,01全职,02兼职,03无工作
     *
     * @return 有无工作,00未知,01全职,02兼职,03无工作
     */
    public String getWorkTag(){
        return workTag;
    }

    /**
     * 设置有无工作,00未知,01全职,02兼职,03无工作
     *
     * @param workTag 要设置的有无工作,00未知,01全职,02兼职,03无工作
     */
    public void setWorkTag(String workTag){
        this.workTag = workTag;
    }

    /**
     * 获取公司地址
     *
     * @return 公司地址
     */
    public String getCompanyAddress(){
        return companyAddress;
    }

    /**
     * 设置公司地址
     *
     * @param companyAddress 要设置的公司地址
     */
    public void setCompanyAddress(String companyAddress){
        this.companyAddress = companyAddress;
    }

    /**
     * 获取有无房产,00未知,01有,02无
     *
     * @return 有无房产,00未知,01有,02无
     */
    public String getHouseTag(){
        return houseTag;
    }

    /**
     * 设置有无房产,00未知,01有,02无
     *
     * @param houseTag 要设置的有无房产,00未知,01有,02无
     */
    public void setHouseTag(String houseTag){
        this.houseTag = houseTag;
    }

    /**
     * 获取固定资产描述
     *
     * @return 固定资产描述
     */
    public String getFixedAssets(){
        return fixedAssets;
    }

    /**
     * 设置固定资产描述
     *
     * @param fixedAssets 要设置的固定资产描述
     */
    public void setFixedAssets(String fixedAssets){
        this.fixedAssets = fixedAssets;
    }

    /**
     * 获取活跃资产描述
     *
     * @return 活跃资产描述
     */
    public String getActiveAssets(){
        return activeAssets;
    }

    /**
     * 设置活跃资产描述
     *
     * @param activeAssets 要设置的活跃资产描述
     */
    public void setActiveAssets(String activeAssets){
        this.activeAssets = activeAssets;
    }

    /**
     * 获取个人简介
     *
     * @return 个人简介
     */
    public String getPersonalProfile(){
        return personalProfile;
    }

    /**
     * 设置个人简介
     *
     * @param personalProfile 要设置的个人简介
     */
    public void setPersonalProfile(String personalProfile){
        this.personalProfile = personalProfile;
    }

    /**
     * 获取个人信用记录
     *
     * @return 个人信用记录
     */
    public String getCreditRecord(){
        return creditRecord;
    }

    /**
     * 设置个人信用记录
     *
     * @param creditRecord 要设置的个人信用记录
     */
    public void setCreditRecord(String creditRecord){
        this.creditRecord = creditRecord;
    }

    /**
     * 获取担保详情
     *
     * @return 担保详情
     */
    public String getGuarantDetail(){
        return guarantDetail;
    }

    /**
     * 设置担保详情
     *
     * @param guarantDetail 要设置的担保详情
     */
    public void setGuarantDetail(String guarantDetail){
        this.guarantDetail = guarantDetail;
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
     * 获取创建ip
     *
     * @return 创建ip
     */
    public String getCip(){
        return cip;
    }

    /**
     * 设置创建ip
     *
     * @param cip 要设置的创建ip
     */
    public void setCip(String cip){
        this.cip = cip;
    }

    /**
     * 获取修改时间
     *
     * @return 修改时间
     */
    public Date getMtime(){
        return mtime;
    }

    /**
     * 设置修改时间
     *
     * @param mtime 要设置的修改时间
     */
    public void setMtime(Date mtime){
        this.mtime = mtime;
    }

    /**
     * 获取修改ip
     *
     * @return 修改ip
     */
    public String getMip(){
        return mip;
    }

    /**
     * 设置修改ip
     *
     * @param mip 要设置的修改ip
     */
    public void setMip(String mip){
        this.mip = mip;
    }
}
