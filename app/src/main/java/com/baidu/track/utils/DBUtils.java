package com.baidu.track.utils;

import com.baidu.track.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DBUtils {
    private static String driver = "com.mysql.jdbc.Driver";//MySQL 驱动
    private static String url = "jdbc:mysql://mabowen.online/mbw";//MYSQL数据库连接Url
    private static String user = "mbw";//用户名
    private static String password = "mbw";//密码

    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver); //
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static Map<String, String> login(User user) {
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConnection();
        try {
            Statement st = conn.createStatement();
            String sql = "select * from user where username ='" + user.getUsername()
                    + "' and password ='" + user.getPassword() + "'";
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                conn.close();
                st.close();
                res.close();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void register(User user){
        Connection conn = getConnection();
        try {
            Statement st = conn.createStatement();
            String sql = "insert into user (username,password,shenfen) value ('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getShenfen()+"')";
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
