package kr.or.kosa.utils;

import java.sql.*;

public class ConnectionHelper {
    Connection conn = null;

    public static Connection getConnection(String dsn) {
        Connection conn = null;
        try {
            if(dsn.equals("oracle")) {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kosa", "wjstks01!");
            }else if(dsn.equals("mariadb")) {
                Class.forName("org.mariadb.jdbc.Driver");
                conn= DriverManager.getConnection("jdbc:mariadb://localhost:3306/douzone","root","1234");
            }else if(dsn.equals("mysql")) {
                conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/douzone","root","1234");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static Connection getConnection(String dsn, String id, String pwd) {
        Connection conn = null;
        try {
            if(dsn.equals("oracle")) {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",id, pwd);
            }else if(dsn.equals("mariadb")) {
                conn= DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/kosadb",id,pwd);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void close(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void close(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void close(Statement stmt) {
        if(stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void close(PreparedStatement preStmt) {
        if(preStmt != null) {
            try {
                preStmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}