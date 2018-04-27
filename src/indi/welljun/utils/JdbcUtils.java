package indi.welljun.utils;

import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    private static Properties config = new Properties();
    //静态代码只执行一次，因为静态代码块在类加载时执行，类永远只加载一次
    static {
        try {
            config.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
            //加载数据库驱动
            Class.forName(config.getProperty("driver"));
        }
        //如果db文件无法读取，则无法连接数据库
        catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    //连接
    public static Connection GetConnection() throws SQLException {
        return DriverManager.getConnection(config.getProperty("url"),config.getProperty("username"),config.getProperty("password"));
    }
    //释放连接
    public static void release(Connection conn, Statement st, ResultSet rs) {
        if (rs!=null) {
            try {
                rs.close();
            }
            catch (Exception e) {
                e.printStackTrace();//在后台记录异常
            }
        }
        if (st!=null) {
            try {
                st.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn!=null) {
            try {
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
