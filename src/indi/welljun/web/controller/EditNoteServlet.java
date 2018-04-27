package indi.welljun.web.controller;

import indi.welljun.domain.User;
import indi.welljun.service.BusinessService;
import indi.welljun.service.impl.BusinessServiceImpl;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@WebServlet(name = "EditNoteServlet",urlPatterns = "/EditNoteServlet")
public class EditNoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = (User) request.getSession().getAttribute("user");
        String nname = request.getParameter("nname");
        nname= URLDecoder.decode(nname, "UTF-8");
        String content = request.getParameter("content");
        content=URLDecoder.decode(content, "utf-8");
        BusinessServiceImpl service = new BusinessServiceImpl();
        service.editNode(user.getUid(),nname,content);

        int nid = service.getNoteId(user.getUid(),nname);
        response.setCharacterEncoding("utf-8");

        //将数据拼接成JSON格式
        JSONObject noteJSON = new JSONObject();
        try {
            noteJSON.put("message", "修改成功");
            noteJSON.put("nid", nid);
        }catch (JSONException jse) {
            jse.printStackTrace();
        }
        System.out.println(noteJSON);
        PrintWriter out = response.getWriter();
        out.print(noteJSON);//返给ajax请求
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
