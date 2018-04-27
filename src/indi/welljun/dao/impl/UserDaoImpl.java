package indi.welljun.dao.impl;

import indi.welljun.dao.UserDao;
import indi.welljun.domain.User;
import indi.welljun.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao{
    /*根据查询结果设置用户信息*/
    private User setUser(ResultSet rs){
        User c = new User();
        try {
            while (rs.next()) {
                c.setUid(rs.getInt("uid"));
                c.setUname(rs.getString("uname"));
                c.setPassword(rs.getString("password"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setUtype(rs.getString("utype"));
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return c;
    }
    /*添加用户*/
    @Override
    public void addUser(User c) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "INSERT INTO users(uname,password,email,phone,utype) VALUES(?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            st.setString(1,c.getUname());
            st.setString(2,c.getPassword());
            st.setString(3,c.getEmail());
            st.setString(4,c.getPhone());
            st.setString(5,c.getUtype());
            st.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
    /*按照用户名和密码查找用户并返回该用户*/
    @Override
    public User findUser(String uname, String password) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "SELECT * FROM users WHERE uname=? and password=?";
            st = conn.prepareStatement(sql);
            st.setString(1,uname);
            st.setString(2,password);
            rs = st.executeQuery();
            User c = setUser(rs);
            return c;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
    /*判断用户名是否存在*/
    @Override
    public boolean findUser(String uname){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "SELECT * FROM users WHERE uname=?";
            st = conn.prepareStatement(sql);
            st.setString(1,uname);
            rs = st.executeQuery();
            if(rs.next()){ //如果存在该用户
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
    /*根据用户id返回用户信息*/
    @Override
    public User findUser(int uid){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "SELECT * FROM users WHERE uid=?";
            st = conn.prepareStatement(sql);
            st.setInt(1,uid);
            rs = st.executeQuery();
            User c = setUser(rs);
            return c;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
    /*根据用户名返回用户id*/
    public int getUserId(String uname){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.GetConnection();
            String sql = "SELECT uid FROM users WHERE uname=?";
            st = conn.prepareStatement(sql);
            st.setString(1,uname);
            rs = st.executeQuery();
            int c=0;
            while (rs.next()) {
                c=rs.getInt("uid");
            }
            return c;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
