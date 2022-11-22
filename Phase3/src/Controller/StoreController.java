package Controller;

import DTO.MenuDto;
import DTO.StoreDto;
import Model.MenuModel;
import Model.StoreModel;
import View.MenuView;
import View.StoreView;

import java.sql.SQLException;
import java.util.ArrayList;

public class StoreController {
    private final StoreModel storeModel;
    private final StoreView storeView = new StoreView();
    private final MenuModel menuModel;
    private final MenuView menuView = new MenuView();

    public StoreController(StoreModel storeModel, MenuModel menuModel) {
        this.storeModel = storeModel;
        this.menuModel = menuModel;
    }

    public int storeMenu() {
        return this.storeView.storeMenu();
    }

    public void stores() {
        try {
            ArrayList<StoreDto> storeList = this.storeModel.getAllStores();
            this.storeView.showStores(storeList);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void storesByCategory() {
        try {
            String category = this.storeView.selectCategory();
            ArrayList<StoreDto> storeList = this.storeModel.getStoresByCategory(category);
            this.storeView.showStores(storeList);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void storesByAddress() {
        try {
            String address = this.storeView.selectAddress();
            ArrayList<StoreDto> storeList = this.storeModel.getStoresByAddress(address);
            this.storeView.showStores(storeList);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void storesByDepartment() {
        try {
            ArrayList<StoreDto> storeList = this.storeModel.getStoresByDepartment();
            this.storeView.showStores(storeList);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void menuListOfStore() {
        try {
            String storeName = this.storeView.showMenuListOfStore();
            int storeId = this.storeModel.getStoreByName(storeName).getStoreId();
            ArrayList<MenuDto> menuList = this.menuModel.getMenuListByStoreId(storeId);
            for (MenuDto menu: menuList) {
                this.menuView.showMenu(menu);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}
