package Model;

import java.sql.*;

public class StoreModel {
    private final Oracle database;
    private final String insertTemplate = "INSERT INTO STORE VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";

    public StoreModel(Oracle database, UsersModel usersModel) {
        this.database = database;
    }

    public int getStoreId(String storeName) throws SQLException {
        String sql = "SELECT Store_ID FROM STORE WHERE Store_Name = '" + storeName + "'";
        Statement stmt = this.database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int storeId = -1;
        while (rs.next()) {
            storeId = rs.getInt(1);
        }
        return storeId;
    }
}
