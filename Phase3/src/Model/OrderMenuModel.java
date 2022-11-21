package Model;

import DTO.MenuDto;
import DTO.OrderMenuDto;

import java.sql.*;
import java.util.ArrayList;

public class OrderMenuModel {
    private final Oracle database;
    private final MenuModel menuModel;

    public OrderMenuModel(Oracle database, MenuModel menuModel) {
        this.database = database;
        this.menuModel = menuModel;
    }

    // orderMenuList: menuName, quantity
    public ArrayList<OrderMenuDto> insert(int orderId, ArrayList<OrderMenuDto> orderMenuList) throws SQLException {
        String insertTemplate = "INSERT INTO ORDER_MENU VALUES ( ?, ?, ?, ?, ?, ? )";
        int orderMenuId = 0;
        ArrayList<OrderMenuDto> orderedMenuList = new ArrayList<>();
        PreparedStatement ps = this.database.getPreparedStatement(insertTemplate);
        for (OrderMenuDto orderMenu : orderMenuList) {
            ps.setInt(1, ++orderMenuId);/**/
            ps.setInt(2, orderId);
            MenuDto menu = this.menuModel.getMenuByMenuName(orderMenu.menuName);
            ps.setString(3, menu.menuName);
            ps.setString(4, menu.image);
            ps.setInt(5, menu.price);
            ps.setInt(6, orderMenu.quantity);
            ps.addBatch();
            ps.clearParameters() ;
            orderedMenuList.add(new OrderMenuDto(
                    orderMenuId,
                    orderId,
                    orderMenu.menuName,
                    menu.image,
                    menu.price,
                    orderMenu.quantity
            ));
        }
        int[] resInsert = ps.executeBatch();
        System.out.println(resInsert.length);
        ps.close();
        if (resInsert.length == orderedMenuList.size()) {
            return orderedMenuList;
        } else {
            return null;
        }
    }
}
