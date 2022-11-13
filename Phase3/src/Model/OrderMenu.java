package Model;

import View.Announcement;

import java.sql.*;

public class OrderMenu {
    private final Connection conn;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO ORDER_MENU VALUES ( ?, ?, ?, ?, ?, ? )";

    public OrderMenu(Oracle database, UsersModel usersModel) throws SQLException {
        this.conn = database.getConnection();
        this.announcement = new Announcement();
    }
}
