package Model;

import DTO.UsersDto;

import java.sql.*;

public class UsersModel {
    private final Oracle database;
    private UsersDto users;

    public UsersModel(Oracle database) {
        this.database = database;
        this.users = null;
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
            ps.close();
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
            this.users = new UsersDto(userId, userName, dName, password, phoneNumber, membershipTier);
            ps.close();
            return this.users;
        }
        ps.close();
        return null;
    }

    public UsersDto getUsers() throws SQLException {
        getMembershipTier();
        return this.users;
    }

    public void getMembershipTier() throws SQLException {
        String sql = "SELECT COUNT(*) " +
                "FROM USERS U, ORDERS O " +
                "WHERE U.User_ID = ? " +
                "AND U.User_ID = O.User_ID " +
                "GROUP BY U.User_ID";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, this.users.userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            if (count >= 0 && count < 2) {
                this.users.membershipTier = "고마워요";
            } else if (count >= 2 && count < 4) {
                this.users.membershipTier = "최고에요";
            } else {
                this.users.membershipTier = "사랑해요";
            }
        }
    }

    public void updateMembershipTier() throws SQLException {
        getMembershipTier();
        String sql = "UPDATE USERS " +
                "SET Membership_Tier = ? " +
                "WHERE User_ID = ?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setString(1, this.users.membershipTier);
        ps.setInt(2, this.users.userId);
        ps.executeUpdate();
        ps.close();
    }
}
