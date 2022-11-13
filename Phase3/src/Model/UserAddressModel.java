package Model;

import View.Announcement;

import java.sql.*;

public class UserAddressModel {
    private final Connection conn;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO USER_ADDRESS VALUES ( ?, ? )";

    public UserAddressModel(Oracle database, UsersModel usersModel) throws SQLException {
        this.conn = database.getConnection();
        this.announcement = new Announcement();
    }
}
