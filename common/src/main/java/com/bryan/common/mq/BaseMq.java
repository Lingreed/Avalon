package com.bryan.common.mq;

public abstract class BaseMq {
    /**
     * 初始化业务类型
     *
     * @param
     */
    public BaseMq() {
        initBizType();
    }

    /**
     * 业务类型,如果业务类型只有一种
     * 可以为空,不用区分
     */
    public String bizType = "";

    /**
     * 初始化mq 业务类型
     */
    public abstract void initBizType();

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

}
