package com.bryan.controller.v100.vo.sys.dict;

import com.bryan.controller.v100.vo.PageReq;

/**
 * ClassName: DictQueryReq
 * Function: 字典查询
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/22
 */
public class DictQueryReq extends PageReq {
    /**
     * 字典项id
     */
    private Integer sysDictTypeId;

    /**
     * 字典标识
     */
    private String dictCode;

    /**
     * 字典id
     */
    private Integer dictId;

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

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }
}
