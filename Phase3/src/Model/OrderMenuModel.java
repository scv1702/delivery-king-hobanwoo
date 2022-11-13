package Model;

import View.Announcement;

import java.sql.*;

public class OrderMenuModel {
    private final Oracle database;
    private final String insertTemplate = "INSERT INTO ORDER_MENU VALUES ( ?, ?, ?, ?, ?, ? )";

    public OrderMenuModel(Oracle database) {
        this.database = database;
    }
}
