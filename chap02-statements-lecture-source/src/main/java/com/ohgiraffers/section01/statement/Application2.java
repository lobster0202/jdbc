package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {

        /* 1. Connection - (통로) - 생성 */
        Connection con = getConnection();

        /* 2. Statment - (쿼리 운반 객체) - 선언 */
        Statement stmt = null;

        /* 3. ResultSet - (조회의 결과 객체) - 선언 */
        ResultSet rset = null;

        try {
            /* 4. Connection의 createStatement()를 이용한 Statement 인스턴스 생성 */
            stmt = con.createStatement();

            Scanner sc = new Scanner(System.in);
            System.out.print("사번을 입력하세요 : ");
            String empId = sc.nextLine();

            String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";

            System.out.println(query);

            /* 5. executeQuery()로 쿼리문 실행하고 결과를 ResultSet으로 반환 받음 */
            rset = stmt.executeQuery(query);

            /* 6. ResultSet에 담긴 결과물을 컬럼 이름을 이용해 꺼내어 출력 */
            if(rset.next()) {
                System.out.println(rset.getString("EMP_ID")
                        + ", " + rset.getString("EMP_NAME"));
            } else {
                System.out.println("해당 사원의 조회 결과가 없습니다.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            /* 7. 사용한 자원 반납 */
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
