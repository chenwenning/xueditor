
package com.ueditor;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class QiNiuUti {

    private static String uptoken = null;

    // 重用 uploadManager。一般地，只需要创建一个 uploadManager 对象
    private final static UploadManager uploadManager = new UploadManager();

    private static BucketManager bucketManager;

    private static String bucket;

    private static Auth auth;

    static {
        try {
            String ak = PropertiesConfigUtils.getProperty("AccessKey");
            ;
            String sk = PropertiesConfigUtils.getProperty("SecertKey");
            bucket = PropertiesConfigUtils.getProperty("BucketName");
            if (StringUtils.isNotBlank(ak) && StringUtils.isNotBlank(sk)) {
                auth = Auth.create(ak, sk);
                uptoken = auth.uploadToken(bucket);
                bucketManager = new BucketManager(auth);
            }
        } catch (Exception e) {

        }
    }

    /**
     * 上传文件
     *
     * @param byteOrFile
     * @param key
     * @throws Exception
     */
    public static void uploadFile(byte[] byteOrFile, String key) throws Exception {
        try {
            if (key.startsWith("/"))
                key = key.substring(1, key.length());
            String token = getToken(key, false);
            Response res = uploadManager.put(byteOrFile, key, token);
            if (res.isOK()) {
                // System.out.println(res);

            } else {
                throw new Exception("status:" + res.statusCode + ",error:" + res.error);
            }
        } catch (QiniuException e) {

        }
    }

    public static void uploadFile(File byteOrFile, String key, boolean overwrite)
            throws QiniuException {
        try {
            if (key.startsWith("/"))
                key = key.substring(1, key.length());
            String token = getToken(key, overwrite);
            Response res = uploadManager.put(byteOrFile, key, token);
            if (res.isOK()) {
                // System.out.println(res);
            } else {
                throw new RuntimeException("status:" + res.statusCode + ",error:" + res.error);
            }
        } catch (QiniuException e) {

        }
    }

    private static String getToken(String key, boolean overwrite) {
        return overwrite ? auth.uploadToken(bucket, key) : uptoken;
    }

    public static boolean isStoreInQiNiu() {
        return StringUtils.isNotBlank(uptoken);
    }

    public static List<String> listFile(String prefix, int limit, String delimiter) {
        BucketManager.FileListIterator it = bucketManager.createFileListIterator(bucket, prefix,
                limit, delimiter);
        List<String> list = new ArrayList();
        while (it.hasNext()) {
            FileInfo[] items = it.next();
            if (items.length > 0) {
                for (int i = 0; i < items.length; i++) {
                    FileInfo fi = items[i];
                    if (fi != null) {
                        list.add(fi.key);
                    }
                }
            }
        }
        return list;
    }
}
