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
import java.util.List;

@WebServlet(name = "GetAllNotesServlet", urlPatterns = "/GetAllNotesServlet")
public class GetAllNotesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = (User) request.getSession().getAttribute("user");
        BusinessServiceImpl service = new BusinessServiceImpl();
        List<Note> create = service.getAllNotes(user.getUid());
        List<Note> received = service.getReceivedNote(user.getUid());
        System.out.println(user.getUid());
        response.setCharacterEncoding("utf-8");

        JSONObject jObject = new JSONObject();
        try {
            JSONArray jArray = new JSONArray();
            JSONArray jrArray = new JSONArray();
            for (Note note : create)
            {
                JSONObject noteJSON = new JSONObject();
                noteJSON.put("nname", note.getNname());
                noteJSON.put("content", note.getContent());
                noteJSON.put("nid", note.getNid());
                noteJSON.put("createTime", note.getCreateTime());
                noteJSON.put("aid", note.getAid());
                noteJSON.put("aname", user.getUname());
                jArray.put(noteJSON);
            }
            for (Note note : received)
            {
                JSONObject noteJSON = new JSONObject();
                noteJSON.put("nname", note.getNname());
                noteJSON.put("content", note.getContent());
                noteJSON.put("nid", note.getNid());
                noteJSON.put("createTime", note.getCreateTime());
                noteJSON.put("aid", note.getAid());
                noteJSON.put("aname", user.getUname());
                jrArray.put(noteJSON);
            }
            jObject.put("create", jArray);
            jObject.put("receive", jrArray);
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        System.out.println(jObject);
        PrintWriter out = response.getWriter();
        out.print(jObject);//返给ajax请求
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
