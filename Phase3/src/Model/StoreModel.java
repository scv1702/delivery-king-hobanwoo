package Model;

import DTO.StoreDto;

import java.sql.*;
import java.util.ArrayList;

public class StoreModel {
    private final Oracle database;

    public StoreModel(Oracle database) {
        this.database = database;
    }

    public ArrayList<StoreDto> getStores(ResultSet rs) throws SQLException {
        ArrayList<StoreDto> storeList = new ArrayList<>();
        while (rs.next()) {
            String storeName = rs.getString("Store_Name");
            String address = rs.getString("Address");
            String foodCategory = rs.getString("Food_Category");
            String phoneNumber = rs.getString("Phone_Number");
            String description = rs.getString("Description");
            int deliveryFee = rs.getInt("Delivery_Fee");
            int businessHour = rs.getInt("Business_Hour");
            storeList.add(new StoreDto(
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

    public ArrayList<StoreDto> getStoresByMultipleCategory(String category) throws SQLException {
        String[] categorySubStr = category.split("#"); // # 단위로 끊어서 받아오기
        String sql = "";
        for (int i = 0; i < categorySubStr.length; i++) {
            sql += "SELECT * FROM STORE WHERE FOOD_CATEGORY = '" + categorySubStr[i] + "'";
            if (i != categorySubStr.length - 1) {
                sql += " UNION ";
            }
        }
        Statement stmt = this.database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return getStores(rs);
    }

    public ArrayList<StoreDto> getStoresByAddress(String address) throws SQLException {
        String sql = "SELECT * FROM STORE S " +
                "WHERE S.STORE_ID IN (SELECT S1.STORE_ID " +
                "FROM Store S1 " +
                "WHERE S1.ADDRESS LIKE '%" + address + "%')";
        Statement stmt = this.database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return getStores(rs);
    }

    public ArrayList<StoreDto> getStoresByDepartment(int userId) throws SQLException {
        String sql = "SELECT * " +
                "FROM USERS U, DEPARTMENT D, Cooperates C, Store S " +
                "WHERE U.User_ID = ? " +
                "AND U.Dname = C.Dname " +
                "AND C.Store_ID = S.Store_ID " +
                "AND U.Dname = D.Dname";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        return getStores(rs);
    }

    public StoreDto getStoreByName(String storeName) throws SQLException {
        String sql = "SELECT * FROM STORE WHERE Store_Name = '" + storeName + "'";
        Statement stmt = this.database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return getStores(rs).get(0);
    }

    public String getStoreNameById(int storeId) throws SQLException {
        String storeName = null;
        String sql = "SELECT * FROM STORE WHERE STORE_ID = ?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, storeId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            storeName = rs.getString("Store_Name");
        }
        return storeName;
    }

    public ArrayList<StoreDto> getStoresByMyReview(int userId) throws SQLException {
        String sql = "SELECT DISTINCT * " +
                "FROM Store S, USERS U, Review R " +
                "WHERE U.User_ID = ? " +
                "AND U.User_ID = R.User_ID " +
                "AND R.Store_ID = S.Store_ID";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, userId);
        return getStores(ps.executeQuery());
    }
}
