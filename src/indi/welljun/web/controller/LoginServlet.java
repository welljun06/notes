package indi.welljun.web.controller;

import indi.welljun.domain.Note;
import indi.welljun.domain.User;
import indi.welljun.service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet",urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uname = request.getParameter("uname");
        String password = request.getParameter("password");
        BusinessServiceImpl service = new BusinessServiceImpl();
        User user = service.userLogin(uname, password);
        System.out.print(user.getUname());
        if(user.getUname()!=null) {
            request.getSession().setAttribute("user", user);
            // 让用户登录成功后，跳转到首页
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        request.setAttribute("message", "对不起，用户名或密码错误！！请重新登录！2秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content='2;url="+request.getContextPath()+"/LoginUIServlet'>");
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
