package com.bryan.dao.user.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ClassName: UserCorpInfo
 * Function: 用户企业信息表实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
@Table(name = "user_corp_info")
public class UserCorpInfo implements Serializable {

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
     * 企业名称
     */
    @Column(name = "corp_name")
    private String corpName;

    /**
     * 所属行业,字典项value
     */
    private String industry;

    /**
     * 企业规模
     */
    @Column(name = "corp_scale")
    private String corpScale;

    /**
     * 企业性质,字典项value
     */
    @Column(name = "corp_nature")
    private String corpNature;

    /**
     * 股东人数
     */
    @Column(name = "shareholders_number")
    private Integer shareholdersNumber;

    /**
     * 注册资金
     */
    @Column(name = "reg_amt")
    private BigDecimal regAmt;

    /**
     * 税务登记证号码
     */
    @Column(name = "tax_number")
    private String taxNumber;

    /**
     * 营业执照号码
     */
    @Column(name = "license_number")
    private String licenseNumber;

    /**
     * 组织机构代码
     */
    @Column(name = "agency_code")
    private String agencyCode;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 座机号码
     */
    private String tel;

    /**
     * 总资产
     */
    @Column(name = "total_assets")
    private BigDecimal totalAssets;

    /**
     * 成立时间
     */
    @Column(name = "establish_time")
    private Date establishTime;

    /**
     * 销售规模
     */
    @Column(name = "sales_scale")
    private String salesScale;

    /**
     * 企业注册地址
     */
    @Column(name = "corp_reg_address")
    private String corpRegAddress;

    /**
     * 企业办公地址
     */
    @Column(name = "office_address")
    private String officeAddress;

    /**
     * 主营业务
     */
    @Column(name = "main_business")
    private String mainBusiness;

    /**
     * 运营状况
     */
    @Column(name = "ope_state")
    private String opeState;

    /**
     * 企业信用记录
     */
    @Column(name = "credit_record")
    private String creditRecord;

    /**
     * 法人姓名
     */
    @Column(name = "legal_name")
    private String legalName;

    /**
     * 法人身份证
     */
    @Column(name = "legal_id_no")
    private String legalIdNo;

    /**
     * 法人手机
     */
    @Column(name = "legal_mobile")
    private String legalMobile;

    /**
     * 法人住址
     */
    @Column(name = "legal_address")
    private String legalAddress;

    /**
     * 首席财务官名称
     */
    @Column(name = "cfo_name")
    private String cfoName;

    /**
     * 首席财务官手机号
     */
    @Column(name = "cfo_mobile")
    private String cfoMobile;

    /**
     * 首席财务官身份证号码
     */
    @Column(name = "cfo_id_no")
    private String cfoIdNo;

    /**
     * 首席财务官居住地址
     */
    @Column(name = "cfo_address")
    private String cfoAddress;

    /**
     * 担保详情
     */
    @Column(name = "guarant_detail")
    private String guarantDetail;

    /**
     *
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
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id 要设置的id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 要设置的用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取企业名称
     *
     * @return 企业名称
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * 设置企业名称
     *
     * @param corpName 要设置的企业名称
     */
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    /**
     * 获取所属行业,字典项value
     *
     * @return 所属行业, 字典项value
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置所属行业,字典项value
     *
     * @param industry 要设置的所属行业,字典项value
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * 获取企业规模
     *
     * @return 企业规模
     */
    public String getCorpScale() {
        return corpScale;
    }

    /**
     * 设置企业规模
     *
     * @param corpScale 要设置的企业规模
     */
    public void setCorpScale(String corpScale) {
        this.corpScale = corpScale;
    }

    /**
     * 获取企业性质,字典项value
     *
     * @return 企业性质, 字典项value
     */
    public String getCorpNature() {
        return corpNature;
    }

    /**
     * 设置企业性质,字典项value
     *
     * @param corpNature 要设置的企业性质,字典项value
     */
    public void setCorpNature(String corpNature) {
        this.corpNature = corpNature;
    }

    /**
     * 获取股东人数
     *
     * @return 股东人数
     */
    public Integer getShareholdersNumber() {
        return shareholdersNumber;
    }

    /**
     * 设置股东人数
     *
     * @param shareholdersNumber 要设置的股东人数
     */
    public void setShareholdersNumber(Integer shareholdersNumber) {
        this.shareholdersNumber = shareholdersNumber;
    }

    /**
     * 获取注册资金
     *
     * @return 注册资金
     */
    public BigDecimal getRegAmt() {
        return regAmt;
    }

    /**
     * 设置注册资金
     *
     * @param regAmt 要设置的注册资金
     */
    public void setRegAmt(BigDecimal regAmt) {
        this.regAmt = regAmt;
    }

    /**
     * 获取税务登记证号码
     *
     * @return 税务登记证号码
     */
    public String getTaxNumber() {
        return taxNumber;
    }

    /**
     * 设置税务登记证号码
     *
     * @param taxNumber 要设置的税务登记证号码
     */
    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    /**
     * 获取营业执照号码
     *
     * @return 营业执照号码
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * 设置营业执照号码
     *
     * @param licenseNumber 要设置的营业执照号码
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * 获取组织机构代码
     *
     * @return 组织机构代码
     */
    public String getAgencyCode() {
        return agencyCode;
    }

    /**
     * 设置组织机构代码
     *
     * @param agencyCode 要设置的组织机构代码
     */
    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    /**
     * 获取联系人
     *
     * @return 联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人
     *
     * @param contact 要设置的联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取座机号码
     *
     * @return 座机号码
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置座机号码
     *
     * @param tel 要设置的座机号码
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取总资产
     *
     * @return 总资产
     */
    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    /**
     * 设置总资产
     *
     * @param totalAssets 要设置的总资产
     */
    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    /**
     * 获取成立时间
     *
     * @return 成立时间
     */
    public Date getEstablishTime() {
        return establishTime;
    }

    /**
     * 设置成立时间
     *
     * @param establishTime 要设置的成立时间
     */
    public void setEstablishTime(Date establishTime) {
        this.establishTime = establishTime;
    }

    /**
     * 获取销售规模
     *
     * @return 销售规模
     */
    public String getSalesScale() {
        return salesScale;
    }

    /**
     * 设置销售规模
     *
     * @param salesScale 要设置的销售规模
     */
    public void setSalesScale(String salesScale) {
        this.salesScale = salesScale;
    }

    /**
     * 获取企业注册地址
     *
     * @return 企业注册地址
     */
    public String getCorpRegAddress() {
        return corpRegAddress;
    }

    /**
     * 设置企业注册地址
     *
     * @param corpRegAddress 要设置的企业注册地址
     */
    public void setCorpRegAddress(String corpRegAddress) {
        this.corpRegAddress = corpRegAddress;
    }

    /**
     * 获取企业办公地址
     *
     * @return 企业办公地址
     */
    public String getOfficeAddress() {
        return officeAddress;
    }

    /**
     * 设置企业办公地址
     *
     * @param officeAddress 要设置的企业办公地址
     */
    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    /**
     * 获取主营业务
     *
     * @return 主营业务
     */
    public String getMainBusiness() {
        return mainBusiness;
    }

    /**
     * 设置主营业务
     *
     * @param mainBusiness 要设置的主营业务
     */
    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    /**
     * 获取运营状况
     *
     * @return 运营状况
     */
    public String getOpeState() {
        return opeState;
    }

    /**
     * 设置运营状况
     *
     * @param opeState 要设置的运营状况
     */
    public void setOpeState(String opeState) {
        this.opeState = opeState;
    }

    /**
     * 获取企业信用记录
     *
     * @return 企业信用记录
     */
    public String getCreditRecord() {
        return creditRecord;
    }

    /**
     * 设置企业信用记录
     *
     * @param creditRecord 要设置的企业信用记录
     */
    public void setCreditRecord(String creditRecord) {
        this.creditRecord = creditRecord;
    }

    /**
     * 获取法人姓名
     *
     * @return 法人姓名
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * 设置法人姓名
     *
     * @param legalName 要设置的法人姓名
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    /**
     * 获取法人身份证
     *
     * @return 法人身份证
     */
    public String getLegalIdNo() {
        return legalIdNo;
    }

    /**
     * 设置法人身份证
     *
     * @param legalIdNo 要设置的法人身份证
     */
    public void setLegalIdNo(String legalIdNo) {
        this.legalIdNo = legalIdNo;
    }

    /**
     * 获取法人手机
     *
     * @return 法人手机
     */
    public String getLegalMobile() {
        return legalMobile;
    }

    /**
     * 设置法人手机
     *
     * @param legalMobile 要设置的法人手机
     */
    public void setLegalMobile(String legalMobile) {
        this.legalMobile = legalMobile;
    }

    /**
     * 获取法人住址
     *
     * @return 法人住址
     */
    public String getLegalAddress() {
        return legalAddress;
    }

    /**
     * 设置法人住址
     *
     * @param legalAddress 要设置的法人住址
     */
    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    /**
     * 获取首席财务官名称
     *
     * @return 首席财务官名称
     */
    public String getCfoName() {
        return cfoName;
    }

    /**
     * 设置首席财务官名称
     *
     * @param cfoName 要设置的首席财务官名称
     */
    public void setCfoName(String cfoName) {
        this.cfoName = cfoName;
    }

    /**
     * 获取首席财务官手机号
     *
     * @return 首席财务官手机号
     */
    public String getCfoMobile() {
        return cfoMobile;
    }

    /**
     * 设置首席财务官手机号
     *
     * @param cfoMobile 要设置的首席财务官手机号
     */
    public void setCfoMobile(String cfoMobile) {
        this.cfoMobile = cfoMobile;
    }

    /**
     * 获取首席财务官身份证号码
     *
     * @return 首席财务官身份证号码
     */
    public String getCfoIdNo() {
        return cfoIdNo;
    }

    /**
     * 设置首席财务官身份证号码
     *
     * @param cfoIdNo 要设置的首席财务官身份证号码
     */
    public void setCfoIdNo(String cfoIdNo) {
        this.cfoIdNo = cfoIdNo;
    }

    /**
     * 获取首席财务官居住地址
     *
     * @return 首席财务官居住地址
     */
    public String getCfoAddress() {
        return cfoAddress;
    }

    /**
     * 设置首席财务官居住地址
     *
     * @param cfoAddress 要设置的首席财务官居住地址
     */
    public void setCfoAddress(String cfoAddress) {
        this.cfoAddress = cfoAddress;
    }

    /**
     * 获取担保详情
     *
     * @return 担保详情
     */
    public String getGuarantDetail() {
        return guarantDetail;
    }

    /**
     * 设置担保详情
     *
     * @param guarantDetail 要设置的担保详情
     */
    public void setGuarantDetail(String guarantDetail) {
        this.guarantDetail = guarantDetail;
    }

    /**
     * 获取
     *
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置
     *
     * @param remark 要设置的
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 要设置的创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取创建ip
     *
     * @return 创建ip
     */
    public String getCip() {
        return cip;
    }

    /**
     * 设置创建ip
     *
     * @param cip 要设置的创建ip
     */
    public void setCip(String cip) {
        this.cip = cip;
    }

    /**
     * 获取修改时间
     *
     * @return 修改时间
     */
    public Date getMtime() {
        return mtime;
    }

    /**
     * 设置修改时间
     *
     * @param mtime 要设置的修改时间
     */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    /**
     * 获取修改ip
     *
     * @return 修改ip
     */
    public String getMip() {
        return mip;
    }

    /**
     * 设置修改ip
     *
     * @param mip 要设置的修改ip
     */
    public void setMip(String mip) {
        this.mip = mip;
    }
}
