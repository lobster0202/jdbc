package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;


/* PreparedStatement는 Statement와 달리
* placeholder(?)를 사용한 쿼리를 파싱하고 캐싱하여
* 다시 재해석 하는 과정을 생략함으로써
* 한번 캐싱되어 있고 불완전하게 작성되어 있는
* 쿼리 실행의 경우 Statement보다 빠르다. */
public class Application2 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("사번을 입력하세요 : ");
        String empId = sc.nextLine();

        String entYn = "N";

        /* PreparedStatement는 Statement때와 달리
           placeholder(?)를 활용한 하나의 문자열 형태로 쿼리 작성 가능 */
        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ? AND ENT_YN = ? ";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,empId);
            // 1번의 뜻은 첫번째 물음표에 할당하겠다는 뜻
            pstmt.setString(2,entYn);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                System.out.println(rset.getString("EMP_ID")
                        + ", " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
    }
}
