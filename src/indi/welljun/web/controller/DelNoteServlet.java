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

@WebServlet(name = "DelNoteServlet", urlPatterns = "/DelNoteServlet")
public class DelNoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String nid = request.getParameter("nid");
        nid= URLDecoder.decode(nid, "UTF-8");
        int c = Integer.parseInt(nid);
        BusinessServiceImpl service = new BusinessServiceImpl();
        service.delNote(c);

        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //将数据拼接成JSON格式
        out.print("{\"message\":\"删除成功！\"}");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
