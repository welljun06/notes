package indi.welljun.web.controller;

import indi.welljun.domain.User;
import indi.welljun.exception.UserExistException;
import indi.welljun.service.BusinessService;
import indi.welljun.service.impl.BusinessServiceImpl;
import indi.welljun.utils.WebUtils;
import indi.welljun.web.formBean.RegisterForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            RegisterForm form = WebUtils.request2Bean(request, RegisterForm.class);
            boolean b = form.validate();
            if(!b) {
                request.setAttribute("form", form);
                request.getRequestDispatcher("/WEB-INF/client/register.jsp").forward(request, response);
            }
            else {
                User c = WebUtils.request2Bean(request, User.class);
                BusinessService service = new BusinessServiceImpl();
                try {
                    service.register(c);
                    request.setAttribute("message", "恭喜您，注册成功！！！2秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content='2;url=" + request.getContextPath() + "/LoginUIServlet'");
                    request.getRequestDispatcher("/message.jsp").forward(request, response);
                    return;
                } catch (UserExistException e) {
                    form.getErrors().put("uname", "注册的用户名已存在！！！");
                    request.setAttribute("form", form);
                    request.getRequestDispatcher("/WEB-INF/client/register.jsp").forward(request, response);

                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("message", "服务器出现未知错误！！！");
                    request.getRequestDispatcher("/message.jsp").forward(request, response);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
