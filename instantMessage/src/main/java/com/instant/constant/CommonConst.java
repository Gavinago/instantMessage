package com.instant.constant;
/**
 * 公共常量
 *
 */
public class CommonConst {
    /*字符编码 */
    public static final String CHARACTER_ENCODING = "utf-8";
    /* ContentType */
    public static final String CONTENT_TYPE = "application/json; charset=utf-8";

    /* 数据返回的公共key */
    public static final String DEFAULT_MSG_POP_KEY = "message";
    public static final String DEFAULT_RESULT_CODE_KEY = "code";
    public static final String DEFAULT_RESULT_STATUS_KEY = "status";
    public static final String DEFAULT_RESULT_DATA_KEY = "data";
    
    public static final String DEFAULT_APPKEY_KEY = "appKey";
    public static final String DEFAULT_TACCOUNT_KEY = "tAccount";
    public static final String DEFAULT_TUSER_KEY = "tUser";
    
    public static final String DEFAULT_TUSER_ID = "tuserId";
    public static final String DEFAULT_TUSER_PORTRAIT = "usePortrait";
    public static final String DEFAULT_TUSER_NICKNAME = "userNickname";
    public static final String DEFAULT_APPUSERID_KEY = "appUserId";
    /* token 签发者 */
    public static final String JSON_WEB_TOKEN_ISSUER = "Gavin";
    /* token 有效期 15 天 */
    public static final Long JSON_WEB_TOKEN_EXPTIME = 1000*60*60*24*15L;
    public static final String JSON_WEB_TOKEN_KEY = "jwtToken";
    
    /* 账号存储 key accountMap    账号的存储 appkey:TAccount*/
    public static final String DEFAULT_TACCOUNT_MAP_KEY = "accountMap";
    /*  appkey token */
    public static final String DEFAULT_ALL_TUSER_MAP_KEY = "allUserMap";
}
