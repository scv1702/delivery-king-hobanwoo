package Model;

import View.Announcement;

import java.sql.*;

public class DepartmentModel {
    private final Oracle database;
    private final String insertTemplate = "INSERT INTO DEPARTMENT VALUES ( ? )";

    public DepartmentModel(Oracle database, UsersModel usersModel) {
        this.database = database;
    }
}
