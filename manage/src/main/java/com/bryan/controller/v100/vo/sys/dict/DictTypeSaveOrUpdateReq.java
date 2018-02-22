package com.bryan.controller.v100.vo.sys.dict;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public class DictTypeSaveOrUpdateReq {

    private Integer id;

    /**
     * 类型标识
     */
    @NotBlank(message = "请填写字典类型标识")
    private String typeCode;

    /**
     * 类型名称
     */
    @NotBlank(message = "请填写字典类型名称")
    private String typeName;

    /**
     * 状态10启用,20禁用
     */
    private String state;

    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
