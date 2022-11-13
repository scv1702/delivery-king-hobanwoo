package Model;

import DTO.UsersDto;
import View.Announcement;

import java.sql.*;


public class UsersModel {
    private final Announcement announcement;
    private final Oracle database;
    private UsersDto usersDto;
    private UserAddressModel userAddressModel;

    public UsersModel(Oracle database) throws SQLException {
        this.announcement = new Announcement();
        this.database = database;
        this.usersDto = null;
    }

    public UsersDto insert(UsersDto user) throws SQLException {
        String sql = "SELECT MAX(USER_ID) FROM USERS"; // 이렇게 하면 동시성 문제 있다는데 다른 방법도 알아봐야할듯?
        Statement stmt = this.database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int userCount = -1;
        while (rs.next()) {
            userCount = rs.getInt(1);
        }
        if (userCount >= 0) {
            String insertTemplate = "INSERT INTO USERS VALUES ( ?, ?, ?, ?, ?, ? )";
            PreparedStatement ps = this.database.getPreparedStatement(insertTemplate);
            ps.setInt(1, ++userCount);
            ps.setString(2, user.username);
            ps.setString(4, user.password);
            ps.setString(3, user.dName);
            ps.setString(5, user.phoneNumber);
            ps.setString(6, "고마워요");
            int resInsert = ps.executeUpdate();
            if (resInsert == 1) {
                return new UsersDto(userCount, user.username, user.password, user.dName, user.phoneNumber, "고마워요");
            }
        }
        return null;
    }

    public UsersDto login(UsersDto usersDto) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE User_Name=? AND Password=?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setString(1, usersDto.username);
        ps.setString(2, usersDto.password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int userId = rs.getInt(1);
            String userName = rs.getString(2);
            String dName = rs.getString(3);
            String password = rs.getString(4);
            String phoneNumber = rs.getString(5);
            String membershipTier = rs.getString(6);
            this.usersDto = new UsersDto(userId, userName, dName, password, phoneNumber, membershipTier);
            return this.usersDto;
        }
        return null;
    }

    public UsersDto getUsers() {
        return this.usersDto;
    }
}
