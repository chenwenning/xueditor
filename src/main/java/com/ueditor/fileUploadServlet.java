package com.ueditor;

import com.alibaba.fastjson.JSON;
import com.qiniu.http.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenwenning on 2016/7/27.
 */
public class fileUploadServlet extends HttpServlet {


    private String savePath = "upload";

    /**
     * 上传图片
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding( "utf-8" );
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        String ueditorAction = request.getParameter("action");
        if(ueditorAction.equals("")){

        }
        QiniuUploaderutil ups = new QiniuUploaderutil(request);
        String address = getFolderByRoot(savePath,request)+"/"+ups.originalName;
        Response res = ups.uploadToQiNiu();
        System.out.println(JSON.toJSONString(res));
        if(res.statusCode == 200){
            Map<String,Object> returnMap = new HashMap<String,Object>();
            returnMap.put("url",address);
            System.out.println(address);
            //returnMap.put("fileType",up.getType());
            returnMap.put("state","SUCCESS");
            //returnMap.put("original",up.getOriginalName());
            System.out.println(JSON.toJSONString(returnMap));
            response.getWriter().print(JSON.toJSONString(returnMap));
        }
        System.out.println(JSON.toJSONString(res));
        /*Uploader up = new Uploader(request);
        up.setSavePath("upload"); //保存路径
        String[] fileType = {".rar" , ".doc" , ".docx" , ".zip" , ".pdf" , ".txt" , ".swf", ".wmv",".xlsx",".xls"};  //允许的文件类型
        up.setAllowFiles(fileType);
        up.setMaxSize(100000000);        //允许的文件最大尺寸，单位KB
        QiniuUploaderutil ups = new QiniuUploaderutil(request);
        try{
            up.upload();

        }catch (Exception e){
            e.printStackTrace();
        }
       */
    }

    private String getFolderByRoot(String path,HttpServletRequest request) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        path += "/" + formater.format(new Date());
        File dir = new File(this.getPhysicalPathByRoot(path,request));
        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                return "";
            }
        }
        return path;
    }

    private String getPhysicalPathByRoot(String path,HttpServletRequest request) {
        String realPath = request.getSession().getServletContext()
                .getRealPath("/");
        return new File(realPath).getPath() + "/" + path;
    }
}
