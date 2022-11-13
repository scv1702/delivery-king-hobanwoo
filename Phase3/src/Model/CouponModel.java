package Model;

import View.Announcement;

import java.sql.*;

public class CouponModel {
    private final Oracle database;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO COUPON VALUES ( ?, ?, ?, TO_DATE(?, 'yyyy-mm-dd'), ?, ?)";

    public CouponModel(Oracle database, UsersModel usersModel) throws SQLException {
        this.database = database;
        this.announcement = new Announcement();
    }
}
