package com.ohgiraffers.section03.delete;

import com.ohgiraffers.model.dto.MenuDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        /* 1. 삭제할 메뉴 코드 입력받기
        *  2. DeleteController의 deleteMenu() 메소드 호출
        *  3. delete 결과에 따라 성공이면 '메뉴 삭제 성공!' 실패이면 '메뉴 삭제 실패!' 출력
        *  entry key = "deleteMenu"                                                 */

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        Scanner sc = new Scanner(System.in);

        System.out.print("삭제할 메뉴의 번호를 입력해주세요 : ");
        int menuCode = sc.nextInt();

        MenuDTO deleteMenu = new MenuDTO();
        deleteMenu.setMenuCode(menuCode);

        DeleteController dc = new DeleteController();
        int result = dc.deleteMenu(deleteMenu);

        if (result > 0) {
            System.out.println("메뉴 삭제 성공!");
        } else {
            System.out.println("메뉴 삭제 실패!");
        }

    }
}
