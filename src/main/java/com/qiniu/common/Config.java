package com.qiniu.common;

import java.nio.charset.Charset;

/**
 * Created by chenwenning on 2016/7/28.
 */
public class Config {

    public static final String VERSION = "7.0.0";
    public static final int BLOCK_SIZE = 4194304;
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static String API_HOST = "http://api.qiniu.com";
    public static String RSF_HOST = "http://rsf.qbox.me";
    public static String RS_HOST = "http://rs.qbox.me";
    public static String IO_HOST = "http://iovip.qbox.me";
    //public static String IO_HOST = "http://iovip-z1.qbox.me";
    public static String UP_HOST = "http://up-z1.qiniu.com";
    public static String UP_HOST_BACKUP = "http://upload.qiniu.com";
    public static int PUT_THRESHOLD = 4194304;
    public static int CONNECT_TIMEOUT = 10000;
    public static int RESPONSE_TIMEOUT = 30000;
    public static int RETRY_MAX = 5;

    private Config() {
    }

    /**
     * 原配置
     */

    /*public static final String VERSION = "7.0.0";
    public static final int BLOCK_SIZE = 4194304;
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static String API_HOST = "http://api.qiniu.com";
    public static String RSF_HOST = "http://rsf.qbox.me";
    public static String RS_HOST = "http://rs.qbox.me";
    public static String IO_HOST = "http://iovip.qbox.me";
    public static String UP_HOST = "http://up.qiniu.com";
    public static String UP_HOST_BACKUP = "http://upload.qiniu.com";
    public static int PUT_THRESHOLD = 4194304;
    public static int CONNECT_TIMEOUT = 10000;
    public static int RESPONSE_TIMEOUT = 30000;
    public static int RETRY_MAX = 5;

    private Config() {
    }*/
}
