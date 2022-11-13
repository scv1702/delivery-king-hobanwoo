package Model;

import View.Announcement;

import java.sql.*;

public class UserAddressModel {
    private final Oracle database;
    private final String insertTemplate = "INSERT INTO USER_ADDRESS VALUES ( ?, ? )";

    public UserAddressModel(Oracle database, UsersModel usersModel) throws SQLException {
        this.database = database;
    }
}
