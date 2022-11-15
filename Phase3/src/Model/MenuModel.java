package Model;

import DTO.MenuDto;
import View.Announcement;

import java.sql.*;
import java.util.Scanner;

public class MenuModel {
    private final Oracle database;
    private final String insertTemplate = "INSERT INTO MENU VALUES ( ?, ?, ?, ?, ?, ? )";

    public MenuModel(Oracle database) {
        this.database = database;
    }

    public void main() throws SQLException {
//        Scanner in = new Scanner(System.in);
//        announcement.menu();
//        switch (in.nextInt()) {
//            case 1: // 음식 카테고리별 제휴업체 조회
//                System.out.println("아직 구현 전");
//                // 아래 case2 포맷과 동일하게 생성하면 될 듯
//                break;
//            case 2: // 내 학과 전체 제휴업체 메뉴
//                /* query */
//                String sql = "";
//                PreparedStatement ps = this.database.getPreparedStatement(sql);
//                ResultSet rs = ps.executeQuery();
//
//                int[] Menu_ID = {0, 1};
//                int[] Store_ID = {12, 31};
//                String[] Mname = {"닭갈비", "소고기"};
//                String[] Description = {"닭갈비는 파닥파닥~", "소고기는 음메~"};
//                String[] Image = {"12.jpg", "31.jpg"};
//
//                announcement.affiliatesStart();
//                for (int i = 0; i < Menu_ID.length; i++) {
//                    announcement.affiliates(
//                        Menu_ID[i],
//                        Store_ID[i],
//                        Mname[i],
//                        Description[i],
//                        Image[i]
//                    );
//                }
//                announcement.affiliatesEnd();
//                break;
//            case 3: // 내 주소에 있는 학과 제휴업체 메뉴
//                System.out.println("아직 구현 전");
//                // case 1 포맷과 동일하게 생성하면 될 듯
//                break;
//        }
//        in.close();
    }

    public MenuDto getMenu(String menuName) {
        MenuDto menu = new MenuDto();
        try {
            String sql = "SELECT * FROM MENU WHERE Mname = '" + menuName + "'";
            Statement stmt = this.database.getStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                menu.menuId = rs.getInt(1);
                menu.storeId = rs.getInt(2);
                menu.menuName = rs.getString(3);
                menu.description = rs.getString(4);
                menu.image = rs.getString(5);
                menu.price = rs.getInt(6);
            }
            return menu;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
