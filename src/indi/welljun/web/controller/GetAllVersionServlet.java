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
import java.util.List;

@WebServlet(name = "GetAllVersionServlet",urlPatterns = "/GetAllVersionServlet")
public class GetAllVersionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        User user = (User) request.getSession().getAttribute("user");
        String uid = request.getParameter("uid");
        String nname = request.getParameter("nname");
        uid = URLDecoder.decode(uid,"UTF-8");
        nname= URLDecoder.decode(nname, "UTF-8");
        int id = Integer.parseInt(uid);
        BusinessServiceImpl service = new BusinessServiceImpl();
        List<Note> lists = service.findNotes(id,nname);
        User user = service.findUser(id);
//        System.out.println(user.getUid());
        response.setCharacterEncoding("utf-8");
        JSONObject jObject = new JSONObject();
        try {
            JSONArray jArray = new JSONArray();
            for (Note note : lists)
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
            jObject.put("lists", jArray);
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
