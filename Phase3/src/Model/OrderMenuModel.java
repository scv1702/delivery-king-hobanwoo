package Model;

import View.Announcement;

import java.sql.*;

public class OrderMenuModel {
    private final Oracle database;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO ORDER_MENU VALUES ( ?, ?, ?, ?, ?, ? )";

    public OrderMenuModel(Oracle database, UsersModel usersModel) throws SQLException {
        this.database = database;
        this.announcement = new Announcement();
    }
}
