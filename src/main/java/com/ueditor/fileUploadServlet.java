package com.ueditor;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenwenning on 2016/7/27.
 */
public class fileUploadServlet extends HttpServlet {

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
        Uploader up = new Uploader(request);
        up.setSavePath("upload"); //保存路径
        String[] fileType = {".rar" , ".doc" , ".docx" , ".zip" , ".pdf" , ".txt" , ".swf", ".wmv",".xlsx",".xls"};  //允许的文件类型
        up.setAllowFiles(fileType);
        up.setMaxSize(100000000);        //允许的文件最大尺寸，单位KB
        try{
            up.upload();
        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("url",up.getUrl());
        returnMap.put("fileType",up.getType());
        returnMap.put("state",up.getState());
        returnMap.put("original",up.getOriginalName());
        System.out.println(JSON.toJSONString(returnMap));
        response.getWriter().print(JSON.toJSONString(returnMap));
    }
}
