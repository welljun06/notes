package indi.welljun.web.controller;

import indi.welljun.domain.Note;
import indi.welljun.domain.User;
import indi.welljun.service.impl.BusinessServiceImpl;
import org.codehaus.jettison.json.JSONArray;
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

@WebServlet(name = "GetNoteInfoServlet", urlPatterns = "/GetNoteInfoServlet")
public class GetNoteInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("nid");
        id= URLDecoder.decode(id, "UTF-8");
        int nid = Integer.parseInt(id);
        System.out.print(nid);
        BusinessServiceImpl service = new BusinessServiceImpl();
        Note note = service.getNoteInfo(nid);
        User user = service.findUser(note.getAid());
        System.out.print(note.getNname());
        response.setCharacterEncoding("utf-8");

        //将数据拼接成JSON格式
        JSONObject noteJSON = new JSONObject();
        try {

            noteJSON.put("nname", note.getNname());
            noteJSON.put("content", note.getContent());
            noteJSON.put("nid", note.getNid());
            noteJSON.put("createTime", note.getCreateTime());
            noteJSON.put("aid", note.getAid());
            noteJSON.put("uname", user.getUname());

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
