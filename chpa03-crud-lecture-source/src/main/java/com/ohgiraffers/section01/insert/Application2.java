package com.ohgiraffers.section01.insert;

import com.ohgiraffers.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {
        /* 1. 메뉴의 이름, 가격, 카테고리 코드, 판매여부를 입력 받기(Scanner 이용) */

        Scanner sc = new Scanner(System.in);
        Connection con = getConnection();
        PreparedStatement pstmt = null;

        Properties prop = new Properties();

        System.out.print("메뉴 이름을 입력하세요 : ");
        String menuName = sc.nextLine();
        System.out.print("메뉴 가격을 입력하세요 : ");
        int menuPrice = sc.nextInt();
        System.out.print("메뉴의 범주 코드를 입력하세요 : ");
        int categoryCode = sc.nextInt();
        System.out.print("메뉴의 판매 여부를 입력하세요(Y/N) : ");
        sc.nextLine(); // 버퍼에 개행문자가 남아 있기 때문에 제거하기 위해
        String orderableStatus = sc.nextLine().toUpperCase();

        /* MenuDTO 객체를 생성하여 입력받은 값으로 setting */
        MenuDTO newMenu = new MenuDTO();
        newMenu.setMenuName(menuName);
        newMenu.setMenuPrice(menuPrice);
        newMenu.setCategoryCode(categoryCode);
        newMenu.setOrderableStatus(orderableStatus);

        /* InsertController의 insertMenu() 메소드 호출*/
        InsertController ic = new InsertController();
        int result = ic.insertMenu(newMenu);

        /* isnert 결과에 따라서 성공이면 '메뉴 등록 성공!', 실패이면 '메뉴 등록 실패!' 출력 */
        if (result > 0) {
            System.out.println("메뉴 등록 성공!");
        } else {
            System.out.println("메뉴 등록 실패!");
        }

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("insertMenu");

//            pstmt = con.prepareStatement(query);
//            pstmt.setString(1, menuName);
//            pstmt.setInt(2,menuPrice);
//            pstmt.setInt(3,categoryCode);
//            pstmt.setString(4,orderableStatus);

//            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }



    }
}
