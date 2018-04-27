package indi.welljun.web.controller;

import indi.welljun.service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@WebServlet(name = "SendNoteServlet", urlPatterns = "/SendNoteServlet")
public class SendNoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uname = request.getParameter("uname");
        uname= URLDecoder.decode(uname, "UTF-8");
        String id = request.getParameter("nid");
        id = URLDecoder.decode(id,"UTF-8");
        int nid = Integer.parseInt(id);
        BusinessServiceImpl service = new BusinessServiceImpl();
        int uid = service.getUserId(uname);
        service.sendNote(uid,nid);

        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //将数据拼接成JSON格式
        out.print("{\"message\":\"1\"}");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
