package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Application1 {
    public static void main(String[] args) {

        Connection con = null;
        // (석현) Connection = 통로

        try {
            /* 사용할 Driver 등록*/
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection
                    ("jdbc:mysql://localhost/employee", "ohgiraffers", "ohgiraffers");

            System.out.println("con = " + con);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
