package com.cleancarSMS.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @version 1.0 123
 * @author lixingji
 * @since 2015.2.27
 */
public class JDBC_Connect {
    private static String drivername="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://localhost:3306/cleancardb?useUnicode=true&characterEncoding=UTF-8";
    private static String username="root";
    private static String password="Youyixi168";//"Youyixi168";

/*    private static String url = "jdbc:mysql://127.5.55.130:3306/campusserver";
    private static String username = "adminwZ2BKfA";
    private static String password = "fu2_1UkVs72U";*/

    static {
        try {
            Class.forName(drivername);
            System.out.println("成功创建驱动");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("连接数据库成功");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return conn;
    }

    public static void free(ResultSet rs, Connection conn, PreparedStatement pstmt) {
        try {
            if (rs != null)
                rs.close();
            System.out.println("resultset关闭成功");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("resultset关闭失败");
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                System.out.println("connection关闭成功");
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println("连接关闭失败");
                e2.printStackTrace();
            } finally {
                try {
                    if (pstmt != null)
                        pstmt.close();
                    System.out.println("statement关闭成功");
                } catch (Exception e3) {
                    // TODO: handle exception
                    System.out.println("statement关闭失败");
                    e3.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        JDBC_Connect.getConnection();
    }
}
