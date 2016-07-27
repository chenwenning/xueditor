package com.ueditor;

import com.baidu.ueditor.ActionEnter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chenwenning on 2016/7/26.
 *
 * http://blog.csdn.net/huangwenyi1010/article/details/51638123
 */
public class GetConfigServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这里就是把controller.jsp代码copy下来
        request.setCharacterEncoding( "utf-8" );
        response.setHeader("Content-Type" , "text/html");
        String roolPath = request.getSession().getServletContext().getRealPath("/");//config.json存放的位置目录
        //记得把config.json放到/Test/WEB-INF/下
        System.out.println(roolPath+"======"+request.getParameter("action"));
        String configStr = new ActionEnter(request, roolPath).exec();
        System.out.println(configStr);
        response.getWriter().write(configStr);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp);
    }
}
