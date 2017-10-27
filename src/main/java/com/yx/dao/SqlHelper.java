package com.yx.dao;

import java.sql.*;

public class SqlHelper {

    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private static String dbURL = "jdbc:sqlserver://EZ-20170108LRHR\\CFHY:1433;DatabaseName=db_MedicalCrab";

    private static String userName = "sa";

    private static String userPwd = "123456";


    private static Connection getCoonection() {
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(dbURL, userName, userPwd);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("连接失败");
        }
        return null;
    }

    public static ResultSet executeQuery(String SQL) throws SQLException {
            Connection conn = getCoonection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            return rs;
    }

    public static boolean executeUpdate(String SQL) throws SQLException {
        Connection conn = getCoonection();
        Statement stmt = conn.createStatement();
        int result = stmt.executeUpdate(SQL);
        if (result > 0)
            return true;
        return false;
    }

}
