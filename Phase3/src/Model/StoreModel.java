package Model;

import DTO.StoreDto;

import java.sql.*;
import java.util.ArrayList;

public class StoreModel {
    private final Oracle database;
    private final UsersModel usersModel;

    public StoreModel(Oracle database, UsersModel usersModel)  {
        this.database = database;
        this.usersModel = usersModel;
    }

    public ArrayList<StoreDto> getStores(ResultSet rs) throws SQLException {
        ArrayList<StoreDto> storeList = new ArrayList<>();
        while (rs.next()) {
            int storeId = rs.getInt("Store_ID");
            String storeName = rs.getString("Store_Name");
            String address = rs.getString("Address");
            String foodCategory = rs.getString("Food_Category");
            String phoneNumber = rs.getString("Phone_Number");
            String description = rs.getString("Description");
            int deliveryFee = rs.getInt("Delivery_Fee");
            int businessHour = rs.getInt("Business_Hour");
            storeList.add(new StoreDto(
                    storeId,
                    storeName,
                    address,
                    foodCategory,
                    phoneNumber,
                    description,
                    deliveryFee,
                    businessHour
            ));
        }
        return storeList;
    }

    public ArrayList<StoreDto> getAllStores() throws SQLException {
        String sql = "SELECT * FROM STORE";
        Statement stmt = this.database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return getStores(rs);
    }

    public ArrayList<StoreDto> getStoresByCategory(String category) throws SQLException {
        String sql = "SELECT * FROM STORE WHERE FOOD_CATEGORY = '" + category + "'";
        Statement stmt = this.database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return getStores(rs);
    }

    public ArrayList<StoreDto> getStoresByAddress(String address) throws SQLException {
        String sql = "SELECT * FROM STORE WHERE ADDRESS LIKE '%" + address + "%'";
        Statement stmt = this.database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return getStores(rs);
    }

    public ArrayList<StoreDto> getStoresByDepartment() throws SQLException {
        int userID = this.usersModel.getUsers().userId;
        String sql = "SELECT * " +
                "FROM USERS U, DEPARTMENT D, Cooperates C, Store S " +
                "WHERE U.User_ID = ? " +
                "AND U.Dname = C.Dname " +
                "AND C.Store_ID = S.Store_ID " +
                "AND U.Dname = D.Dname";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();
        return getStores(rs);
    }

    public StoreDto getStoreByName(String storeName) throws SQLException {
        String sql = "SELECT * FROM STORE WHERE Store_Name = '" + storeName + "'";
        Statement stmt = this.database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return getStores(rs).get(0);
    }
}
