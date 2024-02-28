package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Connection con = getConnection();

        /* 쿼리를 운반하고 결과를 반환하는 객체
        * 쿼리문을 저장하고 실행하는 기능을 하는 용도의 인터페이스*/
        Statement stmt = null;

        /* 조회의 결과 반환되는 객체
        * select 결과집합을 받아 올 용도의 인터페이스 */
        ResultSet rset = null;

        try {
            /* connection을 이용하여 statement 인스턴스 생성 */
            stmt = con.createStatement();
            rset = stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
            // (석현) SQL문은 관례적으로 대문자로 작성한다.

            while (rset.next()) {
                /* next() : ResultSet의 커서 위치를 내리면서 행이 존재하면 true, 존재하지 않으면 false를 반환 */
                System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            /* 맨 마지막에 열렸던 애 부터 순서대로 닫아줌*/
            close(rset);
            close(stmt);
            close(con);
        }


    }
}
