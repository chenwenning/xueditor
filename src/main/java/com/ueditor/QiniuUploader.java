package com.ueditor;

import com.qiniu.common.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

import java.io.IOException;

/**
 * Created by chenwenning on 2016/7/27.
 * 七牛的文档  http://7xnonj.com2.z0.glb.qiniucdn.com/index.htm    http://blog.csdn.net/netdxy/article/details/50222631
 *
 * 重点  http://o9gnz92z5.bkt.clouddn.com/article/index.html#dora-api-handbook
 */
public class QiniuUploader {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = PropertiesConfigUtils.getProperty("AccessKey");
    String SECRET_KEY = PropertiesConfigUtils.getProperty("SecertKey");
    //要上传的空间
    String bucketname = PropertiesConfigUtils.getProperty("BucketName");
    //上传到七牛后保存的文件名
    String key = "0160729145547.png";
    //上传文件的路径
    String FilePath = "C:\\Users\\chenwenning\\Desktop\\0160729145547.png";
    String urlbase64 = UrlSafeBase64.encodeToString(bucketname+":0160729145547.png");
    //图片缩放(预转持续化)
   /* String pfrops6 = "imageView2/2/w/300/h/300|saveas/"+urlbase64;*/

    /* 图片水印处理      String pictureurl = UrlSafeBase64.encodeToString("http://oayhgt4fd.bkt.clouddn.com/peter1314.png");*/
    String pictureurl = UrlSafeBase64.encodeToString("http://oayhgt4fd.bkt.clouddn.com/peter1314.png");

    String pfrops5 = "watermark/1/image/"+pictureurl+"|saveas/"+urlbase64;

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象
    UploadManager uploadManager = new UploadManager();

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken(){
        return auth.uploadToken(bucketname,null,3600,new StringMap().put("persistentOps",pfrops5));
    }

    public void upload() throws IOException{
        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }

    public static void main(String args[]) throws IOException{
        new QiniuUploader().upload();
    }
}
