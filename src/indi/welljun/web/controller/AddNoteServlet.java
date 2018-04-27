package indi.welljun.web.controller;

import indi.welljun.domain.User;
import indi.welljun.service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@WebServlet(name = "AddNoteServlet",urlPatterns = {"/AddNoteServlet"})
public class AddNoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        User user = (User) request.getSession().getAttribute("user");
        String nname = request.getParameter("nname");
        nname= URLDecoder.decode(nname, "UTF-8");
        String content = request.getParameter("content");
        content=URLDecoder.decode(content, "UTF-8");
        BusinessServiceImpl service = new BusinessServiceImpl();
        //判断该用户是否存在该笔记
        boolean flag = service.findNote(user.getUid(),nname);
        System.out.print(flag);
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("utf-8");
        if(flag){
            out.print("{\"message\":\"1\"}");
        }
        else {
            service.addNote(user.getUid(), nname, content);
            //将数据拼接成JSON格式
            out.print("{\"nname\":\"" + nname + "\",\"content\":\"" + content + "\",\"message\":\"0\"}");
        }
        out.flush();
        out.close();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
