package com.ueditor;

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
    private static String mAccessKey = PropertiesConfigUtils.getProperty("AccessKey");
    private static String mSecertKey = PropertiesConfigUtils.getProperty("SecertKey");
    //要上传的空间
    private static String mBucketName = PropertiesConfigUtils.getProperty("BucketName");
    private Auth mAuth;
    private UploadManager mUploadManager;


    public UploadUtil(String accessKey, String secertKEY, String bucketName) {
        mAccessKey = accessKey;
        mSecertKey = secertKEY;
        mBucketName = bucketName;
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
}
