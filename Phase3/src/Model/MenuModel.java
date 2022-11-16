package Model;

import DTO.MenuDto;
import DTO.StoreDto;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class MenuModel {
    private final Oracle database;
    private final StoreModel storeModel;

    public MenuModel(Oracle database, StoreModel storeModel) {
        this.database = database;
        this.storeModel = storeModel;
    }

    public MenuDto getMenu(ResultSet rs) throws SQLException {
       MenuDto menu = new MenuDto();
        while (rs.next()) {
            menu.menuId = rs.getInt(1);
            menu.storeId = rs.getInt(2);
            menu.menuName = rs.getString(3);
            menu.description = rs.getString(4);
            menu.image = rs.getString(5);
            menu.price = rs.getInt(6);
        }
        return menu;
    }

    public MenuDto getMenuByMenuName(String menuName) throws SQLException {
        String sql = "SELECT * FROM MENU WHERE Mname = '" + menuName + "'";
        Statement stmt = this.database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return getMenu(rs);
    }

    public ArrayList<MenuDto> getMenuListByStoreName(String storeName) throws SQLException {
        int storeId = this.storeModel.getStoreByName(storeName).getStoreId();
        String sql = "SELECT * FROM MENU WHERE STORE_ID = " + storeId;
        Statement stmt = this.database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<MenuDto> menuList = new ArrayList<>();
        while (rs.next()) {
            menuList.add(getMenu(rs));
        }
        return menuList;
    }
}
