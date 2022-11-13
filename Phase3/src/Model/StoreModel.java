package Model;

import java.sql.*;

public class StoreModel {
    private final Oracle database;
    private final String insertTemplate = "INSERT INTO STORE VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";

    public StoreModel(Oracle database, UsersModel usersModel) {
        this.database = database;
    }
}
