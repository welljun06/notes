package indi.welljun.dao.impl;

import indi.welljun.dao.SendDao;
import indi.welljun.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SendDaoImpl implements SendDao{
    //用户接收笔记
    @Override
    public void sendNote(int uid, int nid){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "INSERT INTO send(uid,nid,sendTime) VALUES(?,?,now())";
            st = conn.prepareStatement(sql);
            st.setInt(1,uid);
            st.setInt(2,nid);
            st.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
