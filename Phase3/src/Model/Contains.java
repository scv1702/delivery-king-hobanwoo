package Model;

import View.Announcement;

import java.sql.*;

public class Contains {
    private final Connection conn;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO CONTAINS VALUES ( ?, ?, ? )";

    public Contains(Oracle database, UsersModel usersModel) throws SQLException {
        this.conn = database.getConnection();
        this.announcement = new Announcement();
    }
}