package Model;

import View.Announcement;

import java.sql.*;

public class Department {
    private final Oracle database;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO DEPARTMENT VALUES ( ? )";

    public Department(Oracle database, UsersModel usersModel) throws SQLException {
        this.database = database;
        this.announcement = new Announcement();
    }
}
