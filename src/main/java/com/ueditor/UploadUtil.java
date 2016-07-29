package com.ueditor;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.IOException;

/**
 * Created by chenwenning on 2016/7/27.
 */
public class UploadUtil {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    private String mAccessKey;
    private String mSecertKey;
    //要上传的空间
    private String mBucketName;
    private Auth mAuth;
    private UploadManager mUploadManager;


    public UploadUtil() {
        mAccessKey = PropertiesConfigUtils.getProperty("AccessKey");
        mSecertKey = PropertiesConfigUtils.getProperty("SecertKey");
        mBucketName = PropertiesConfigUtils.getProperty("BucketName");
        //密钥配置
        mAuth = Auth.create(mAccessKey, mSecertKey);
        mUploadManager = new UploadManager();
    }

    private String getUpToken() {
        return mAuth.uploadToken(mBucketName);
    }

    public Response upload(String filePath, String fileName) throws IOException {
        try {
            return mUploadManager.put(filePath, fileName, getUpToken());
        } catch (QiniuException e) {
            return e.response;
        }
    }

    public void uploadFile(byte[] byteOrFile, String key) throws Exception {
        try {
            Response res = mUploadManager.put(byteOrFile, key, getUpToken());
            if (res.isOK()) {
                System.out.println(JSON.toJSONString(res));
            } else {
                throw new Exception("status:" + res.statusCode + ",error:" + res.error);
            }
        } catch (QiniuException e) {

            Response r = e.response;
            // 请求失败时简单状态信息
        }
    }
    /**
     * 实现图片的裁剪
     */



}
