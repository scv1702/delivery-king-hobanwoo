package Model;


import DTO.CouponDto;
import DTO.UsersDto;

import java.sql.*;
import java.util.ArrayList;

public class CouponModel {
    private final Oracle database;
    private ArrayList<CouponDto> coupons;
    public CouponModel(Oracle database) {
        this.database = database;
        this.coupons = null;
    }

    public ArrayList<CouponDto> showMyCoupons(UsersDto user) throws SQLException {
        this.coupons = new ArrayList<>();
        String sql = "SELECT * " +
                     "FROM COUPON " +
                     "WHERE USER_ID=?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, user.userId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int couponId = rs.getInt(1);
            int userId = rs.getInt(2);
            int discountAmount = rs.getInt(3);
            String expirationDate = rs.getString(4);
            int minimumOrderAmount = rs.getInt(5);
            String state = rs.getString(6);
            this.coupons.add(new CouponDto(couponId, userId, discountAmount, expirationDate, minimumOrderAmount, state));
        }
        ps.close();
        return this.coupons;
    }
}
