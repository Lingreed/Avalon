package com.bryan.controller.v100.vo.sys.dict;

import javax.validation.constraints.NotNull;

/**
 * ClassName: AddDictModel
 * Function: 字典项添加
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public class DictAddReq {
    private Integer id;

    /**
     * 字典项id
     */
    @NotNull(message = "请选择字典类型")
    private Integer sysDictTypeId;

    /**
     * 字典标识
     */
    @NotNull(message = "字典标识不能为空")
    private String dictCode;

    /**
     * 字典值
     */
    @NotNull(message = "字典值不能为空")
    private String dictValue;

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

    public Integer getSysDictTypeId() {
        return sysDictTypeId;
    }

    public void setSysDictTypeId(Integer sysDictTypeId) {
        this.sysDictTypeId = sysDictTypeId;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
