package com.ohgiraffers.section02.update;

import com.ohgiraffers.model.dto.MenuDTO;

import java.sql.Connection;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {

        /* 1. 변경할 메뉴 코드, 이름, 가격을 입력 받기 */
        Scanner sc = new Scanner(System.in);
        Connection con = getConnection();
        int result = 0;
        Properties prop = new Properties();

        MenuDTO updateMenu = new MenuDTO();

        System.out.print("변경할 메뉴의 번호를 입력하세요 : ");
        int menuCode = sc.nextInt();
        System.out.print("메뉴 이름을 뭐로 변경하시겠습니까? : ");
        sc.nextLine();
        String menuName = sc.nextLine();
        System.out.print("가격은 얼마로 하시겠습니까 ? : ");
        int menuPrice = sc.nextInt();

        /* 2. MenuDTO 객체를 생성하여 입력받은 값으로 setting */
        updateMenu.setMenuCode(menuCode);
        updateMenu.setMenuName(menuName);
        updateMenu.setMenuPrice(menuPrice);

        /* 3. UpdateController의 updateMenu() 메소드 호출 */
        UpdateController uc = new UpdateController();
        uc.updateMenu(updateMenu);


        /* 4. update 결과에 따라 성공, 실패 메세지 출력 */
        if (result > 0 ) {
            System.out.println("메뉴 변경 성공!");
        } else {
            System.out.println("메뉴 변경 실패!");
        }
    }
}
