package Model;

import View.Announcement;

import java.sql.*;

public class DepartmentModel {
    private final Oracle database;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO DEPARTMENT VALUES ( ? )";

    public DepartmentModel(Oracle database, UsersModel usersModel) throws SQLException {
        this.database = database;
        this.announcement = new Announcement();
    }
}
