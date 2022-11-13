package Model;

import java.sql.*;

public class ContainsModel {
    private final Oracle database;
    private final String insertTemplate = "INSERT INTO CONTAINS VALUES ( ?, ?, ? )";

    public ContainsModel(Oracle database, UsersModel usersModel) {
        this.database = database;
    }
}