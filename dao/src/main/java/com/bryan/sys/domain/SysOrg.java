package com.bryan.sys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SysOrg
 * Function: 组织机构实体
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
@Table(name = "sys_org")
public class SysOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父级ID
     */
    private Integer pid;

    /**
     * 机构名称
     */
    @Column(name = "org_name")
    private String orgName;

    /**
     * 排序
     */
    @Column(name = "org_sort")
    private Integer orgSort;

    /**
     * 区编码
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 机构类型字典项
     */
    @Column(name = "org_type")
    private String orgType;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系人
     */
    @Column(name = "contact_user")
    private String contactUser;

    /**
     * 联系电话
     */
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 邮编
     */
    @Column(name = "zip_code")
    private String zipCode;

    /**
     * 传真
     */
    private String fax;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态10启用,20禁用
     */
    private String state;

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
     * 获取父级ID
     *
     * @return 父级ID
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父级ID
     *
     * @param pid 要设置的父级ID
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取机构名称
     *
     * @return 机构名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 设置机构名称
     *
     * @param orgName 要设置的机构名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 获取排序
     *
     * @return 排序
     */
    public Integer getOrgSort() {
        return orgSort;
    }

    /**
     * 设置排序
     *
     * @param orgSort 要设置的排序
     */
    public void setOrgSort(Integer orgSort) {
        this.orgSort = orgSort;
    }

    /**
     * 获取区编码
     *
     * @return 区编码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置区编码
     *
     * @param areaCode 要设置的区编码
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 获取机构类型字典项
     *
     * @return 机构类型字典项
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * 设置机构类型字典项
     *
     * @param orgType 要设置的机构类型字典项
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * 获取地址
     *
     * @return 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 要设置的地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取联系人
     *
     * @return 联系人
     */
    public String getContactUser() {
        return contactUser;
    }

    /**
     * 设置联系人
     *
     * @param contactUser 要设置的联系人
     */
    public void setContactUser(String contactUser) {
        this.contactUser = contactUser;
    }

    /**
     * 获取联系电话
     *
     * @return 联系电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 设置联系电话
     *
     * @param contactPhone 要设置的联系电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * 获取邮编
     *
     * @return 邮编
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 设置邮编
     *
     * @param zipCode 要设置的邮编
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 获取传真
     *
     * @return 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 要设置的传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 获取邮箱
     *
     * @return 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 要设置的邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取状态10启用,20禁用
     *
     * @return 状态10启用, 20禁用
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态10启用,20禁用
     *
     * @param state 要设置的状态10启用,20禁用
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取备注
     *
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 要设置的备注
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
     * 获取更新时间
     *
     * @return 更新时间
     */
    public Date getMtime() {
        return mtime;
    }

    /**
     * 设置更新时间
     *
     * @param mtime 要设置的更新时间
     */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    /**
     * 获取扩展字段1
     *
     * @return 扩展字段1
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 设置扩展字段1
     *
     * @param ext1 要设置的扩展字段1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    /**
     * 获取扩展字段2
     *
     * @return 扩展字段2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 设置扩展字段2
     *
     * @param ext2 要设置的扩展字段2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    /**
     * 获取扩展字段3
     *
     * @return 扩展字段3
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * 设置扩展字段3
     *
     * @param ext3 要设置的扩展字段3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
}
