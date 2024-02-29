package com.ohgiraffers.section03.sqlinjection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {

    private static String empId = "210";
    private static String empName = "' OR 1=1 AND EMP_ID = '200";
    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '"
                + empId + "' AND EMP_NAME = '" + empName + "'";
        // 이렇게 조건을 작성해서 true 가 나오게 하는게 SQL Injection이다.
        System.out.println(query);

//        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '"
//                + empId + "' AND EMP_NAME = '" + empName + "'";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if (rset.next()) {
                System.out.println(rset.getString("EMP_NAME")+ "님 환영합니다.");
            } else {
                System.out.println("회원 정보가 없습니다.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }


    }
}
