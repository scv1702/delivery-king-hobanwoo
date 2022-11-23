package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContainsModel {
    Oracle database;
    OrderMenuModel orderMenuModel;
    public ContainsModel(Oracle database, OrderMenuModel orderMenuModel) {
        this.database = database;
        this.orderMenuModel = orderMenuModel;
    }

    public void containsInsert(int orderId, int reviewId) throws SQLException {
        ArrayList<Integer> orderMenuIdList = orderMenuModel.getOrderMenuIdByOrderId(orderId);
        String sql = "INSERT INTO CONTAINS VALUES ( ?, ?, ? )";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        for (Integer orderMenuId : orderMenuIdList){
            //System.out.println(orderMenuId);
            ps.setInt(1, orderMenuId);
            ps.setInt(2, orderId);
            ps.setInt(3, reviewId);
            ps.executeQuery();
        }
    }
}
