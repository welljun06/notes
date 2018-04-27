package indi.welljun.web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutServlet",urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.removeAttribute("user");
        }
        // 注销成功，跳转到全局消息显示页面，显示注销成功消息，并控制消息显示页面过1秒后跳转到首页
        request.setAttribute("message", "注销成功，浏览器将在1秒后跳转，如果没有跳转，你就点...！！<meta http-equiv='refresh' content='1;url="+request.getContextPath()+"/index.jsp'>");
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
}
