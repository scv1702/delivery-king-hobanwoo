package Model;

import DTO.UserAddressDto;
import DTO.UsersDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserAddressModel {
    private final Oracle database;
    private ArrayList<UserAddressDto> userAddress;

    public UserAddressModel(Oracle database) {
        this.database = database;
        this.userAddress = null;
    }

    public ArrayList<UserAddressDto> showMyAddress(UsersDto user) throws SQLException {
        this.userAddress = new ArrayList<>();
        String sql = "SELECT UADDRESS " +
                     "FROM USER_ADDRESS " +
                     "WHERE USER_ID=?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, user.userId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            String uaddress = rs.getString(1);
            this.userAddress.add(new UserAddressDto(user.userId, uaddress));
        }
        ps.close();
        return this.userAddress;
    }

    public boolean insert(UsersDto user, String newAddress) throws SQLException {
        String sql = "INSERT INTO USER_ADDRESS VALUES ( ?, ? )";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, user.userId);
        ps.setString(2, newAddress);
        int resINSERT = ps.executeUpdate();
        ps.close();
        return resINSERT == 1;
    }
}
