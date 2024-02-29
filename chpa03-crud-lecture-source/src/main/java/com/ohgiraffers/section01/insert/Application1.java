package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        // dml작업을 할때는 int값으로 반환을 해주는 거라서
        /* DML(insert, update, delete) 작업시에는 반환 결과가 int값이 된다.
        * ResultSet을 쓰지 않는다.
        * 기존의 조회일 때 executeQuery() 를 사용했지만,
        * DML작업 시에는 executeUpdate()를 사용함. */
        int result = 0;     // DML 작업을 하면 작업이 일어난 행의 갯수를 반환(int)

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query =  prop.getProperty("insertMenu");
            System.out.println(query);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1,"봉골레청국장");
            pstmt.setInt(2,9000);
            pstmt.setInt(3, 1);
            pstmt.setString(4,"Y");

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }
        System.out.println("result = " + result);


    }
}
