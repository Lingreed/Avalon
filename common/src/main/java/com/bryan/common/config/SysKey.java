package com.bryan.common.config;

/**
 * ClassName: SysKey
 * Function:  系统配置key
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/6
 */
public enum SysKey {
    USER_NAME_PREFIX("userNamePrefix"),
    WEB_URL("webUrl"),
    WEB_TEL("webTel"),
    WEB_NAME("webName"),
    QINIU_ACCESS_KEY("qiniuAccessKey"),
    QINIU_SECRET_KEY("qiniuSecretKey"),
    QINIU_BUCKET_NAME("qiniuBucketname"),
    QINIU_DOWNLOAD_URL("qiniuDownloadUrl"),
    YEAR_DAYS("yearDays"),
    NAMESRV_ADDR("NamesrvAddr"),
    EMAIL_SERVER_HOST("emailServerHost"),
    EMAIL_SERVER_NICK("emailServerNick"),
    EMAIL_SERVER_USERNAME("emailServerUsername"),
    EMAIL_SERVER_PWD("emailServerPwd"),
    REDENV_USE_TAG("redEnvUseTag"),
    WEB_ONLINE_DATE("webOnlineDate"),//网站上线日期
    DRAW_CASH_TAG("drawcashTag"),//平台垫付手续费，10：平台垫付,20：个人承担
    DRAW_CASH_FEE("drawcashFee"),//提现手续费（元）
    DRAW_CASH_MONTH_FREE("drawcashMonthFree"),//网站上线日期
    WATER_MARK("waterMark"),// 水印
    RECHARGE_FEE_RATE("rechargeFeeRate"),// 充值费率,网关
    RECHARGE_QUICK_FEE_RATE("rechargeQuickFeeRate"),// 充值费率，快捷和h5
    SIGN_PLACE("signPlace"),// 协议签署地方
    PLATFORM_NAME("platformName"),// 网站全称
    PLATFORM_ADDR("platformAddr"),// 网站地址
    RETURN_RECHARGE_H5_URL("returnRechargeH5Url"),// h5充值页面回调
    RETURN_RECHARGE_PC_URL("returnRechargePcUrl"),// pc充值页面回调
    SMS_WEB_NAME("smsWebName"),// 短信后缀
    SMS_MERCHANT("smsMerchant"),// 配置短信提供商,01沃动
    WD_URL("wdUrl"),//沃动请求url
    WD_ID("wdId"),// 沃动账户id
    WD_NAME("wdName"),// 沃动 账户名
    WD_PASS("wdPass"), // 沃动名称
    IS_DEBUG("isDebug"),// 系统配置:是否开启调试模式,10是,20否;线上必须20
    RSA_PRIVATE_KEY("rsaPrivateKey"), // 加密私钥
    RSA_PUBLIC_KEY("rsaPublicKey"), // 加密公钥
    MD5_KEY("md5Key"),// md5 的key
    WEIXIN_APPID("weiXinAppid"),// 微信公众号的id
    WEIXIN_SECRET("weiXinSecret");// 微信公众号 的秘钥

    private String key;

    SysKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
