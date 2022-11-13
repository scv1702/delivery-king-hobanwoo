package Model;

import View.Announcement;

import java.sql.*;

public class Cooperates {
    private final Connection conn;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO COOPERATES VALUES ( ?, ? )";

    public Cooperates(Oracle database, UsersModel usersModel) throws SQLException {
        this.conn = database.getConnection();
        this.announcement = new Announcement();
    }
}