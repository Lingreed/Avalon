package com.bryan.controller.v100.vo.sys.dict;

import com.bryan.controller.v100.vo.PageReq;

/**
 * ClassName: DictTypeQueryReq
 * Function: 字典类型查询
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public class DictTypeQueryReq extends PageReq {
    /**
     * 类型标识
     */
    private String typeCode;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 状态10启用,20禁用
     */
    private String state;

    /**
     * 字典项id
     */
    private Integer dictTypeId;

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

    public Integer getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(Integer dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

}
