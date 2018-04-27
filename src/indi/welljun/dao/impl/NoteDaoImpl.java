package indi.welljun.dao.impl;

import indi.welljun.dao.NoteDao;
import indi.welljun.domain.Note;
import indi.welljun.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NoteDaoImpl implements NoteDao{
    private List<Note> setNote(ResultSet rs){
        List<Note> list = new LinkedList<Note>();
        try {
            while (rs.next()) {
                Note c = new Note();
                c.setAid(rs.getInt("aid"));
                c.setNid(rs.getInt("nid"));
                c.setNname(rs.getString("nname"));
                c.setContent(rs.getString("content"));
                c.setCreateTime(rs.getString("createTime"));
                list.add(c);
            }
        } catch (Exception e){


        }
        return list;
    }

    /*创建笔记*/
    @Override
    public void addNote(int aid,String nname, String content){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "INSERT INTO notes(aid,nname,content,createTime,version) VALUES(?,?,?,now(),'new')";

            st = conn.prepareStatement(sql);
            st.setInt(1,aid);
            st.setString(2,nname);
            st.setString(3,content);
            st.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
    /*返回用户所有笔记*/
    @Override
    public List<Note> getAllNotes(int uid){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "select * from notes where aid=? and version='new' order by createTime desc";
            st = conn.prepareStatement(sql);
            st.setInt(1,uid);
            rs = st.executeQuery();
            List<Note> list=new LinkedList<Note>();
            list = setNote(rs);
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
    /*删除笔记*/
    @Override
    public void delNote(int nid){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "DELETE FROM notes WHERE nid=?";
            st = conn.prepareStatement(sql);
            st.setInt(1,nid);
            st.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
    /*判断用户是否已经创建该笔记*/
    @Override
    public boolean findNote(int uid, String nname){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "SELECT * FROM notes WHERE aid=? and nname=?";
            st = conn.prepareStatement(sql);
            st.setInt(1,uid);
            st.setString(2,nname);
            rs = st.executeQuery();
            if(rs.next()){ //如果存在该笔记
                return true;
            }
            else
                return false;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
    /*修改笔记版本*/
    @Override
    public void changeVer(int uid, String nname){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "update notes Set version='old' where aid=? and nname=?";
            st = conn.prepareStatement(sql);
            st.setInt(1,uid);
            st.setString(2,nname);
            st.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
    /*返回笔记所有版本*/
    @Override
    public List<Note> findNotes(int uid, String nname){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "select * from notes where aid=? and nname=? order by createTime";
            st = conn.prepareStatement(sql);
            st.setInt(1,uid);
            st.setString(2,nname);
            rs = st.executeQuery();
            List<Note> list=new LinkedList<Note>();
            list = setNote(rs);
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
    /*返回笔记信息*/
    @Override
    public Note getNoteInfo(int nid){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "select * from notes where nid=?";
            st = conn.prepareStatement(sql);
            st.setInt(1,nid);
            rs = st.executeQuery();
            Note c = new Note();
            while (rs.next()) {
                c.setAid(rs.getInt("aid"));
                c.setNid(rs.getInt("nid"));
                c.setNname(rs.getString("nname"));
                c.setContent(rs.getString("content"));
                c.setCreateTime(rs.getString("createTime"));
            }
            return c;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
    /*返回笔记ID*/
    public int getNoteId(int uid, String nname){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "select nid from notes where aid=? and nname=? AND version='new'";
            st = conn.prepareStatement(sql);
            st.setInt(1,uid);
            st.setString(2,nname);
            rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("nid");
            }
            return 0;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
    /*返回用户收到的所有笔记*/
    public List<Note> getReceivedNote(int uid){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "select * from notes,send where send.uid =? and send.nid=notes.nid";
            st = conn.prepareStatement(sql);
            st.setInt(1,uid);
            rs = st.executeQuery();
            List<Note> list=new LinkedList<Note>();
            list = setNote(rs);
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
