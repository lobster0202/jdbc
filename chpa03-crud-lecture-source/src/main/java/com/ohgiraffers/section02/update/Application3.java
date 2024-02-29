package com.ohgiraffers.section02.update;

import com.ohgiraffers.model.dto.MenuDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application3 {
    public static void main(String[] args) {
        /* 1. 변경할 메뉴 코드, 이름, 가격을 입력 받기
        *  2. MenuDTO 객체를 생성하여 입력받은 값으로 setting
        *  3. UpdateController의 updateMenu() 메소드 호출
        *  4. update 결과에 따라 성공, 실패 메세지 출력      */

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();
        Scanner sc = new Scanner(System.in);

        System.out.println("변경할 메뉴의 번호를 입력해주세요 : ");
        int menuCode = sc.nextInt();
        System.out.println("변경할 메뉴의 가격을 입력해주세요 : ");
        sc.nextLine();
        int menuPrice = sc.nextInt();
        System.out.println("변경할 메뉴의 이름을 입력해주세요 : ");
        String menuName = sc.nextLine();

        MenuDTO updateMenu = new MenuDTO();
        updateMenu.setMenuName(menuName);
        updateMenu.setMenuPrice(menuPrice);
        updateMenu.setMenuCode(menuCode);

        UpdateController uc = new UpdateController();
        uc.updateMenu(updateMenu);

        if (result > 0 ) {
            System.out.println("메뉴 변경 성공!");
        } else {
            System.out.println("메뉴 변경 실패!");
        }
    }
}
