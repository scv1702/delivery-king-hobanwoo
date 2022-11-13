package Model;

import java.sql.*;

public class CooperatesModel {
    private final Oracle database;
    private final String insertTemplate = "INSERT INTO COOPERATES VALUES ( ?, ? )";

    public CooperatesModel(Oracle database, UsersModel usersModel) {
        this.database = database;
    }
}