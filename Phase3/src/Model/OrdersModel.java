package Model;

import DTO.OrderMenuDto;
import DTO.OrdersDto;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OrdersModel {
    private final Oracle database;
    private final UsersModel usersModel;
    private final OrderMenuModel orderMenuModel;
    private final StoreModel storeModel;

    public OrdersModel(Oracle database, UsersModel usersModel, OrderMenuModel orderMenuModel, StoreModel storeModel) {
        this.database = database;
        this.usersModel = usersModel;
        this.orderMenuModel = orderMenuModel;
        this.storeModel = storeModel;
    }

    public OrdersDto insert(OrdersDto orders) throws SQLException {
        String sql = "SELECT MAX(Order_ID) FROM ORDERS"; // 이렇게 하면 동시성 문제 있다는데 다른 방법도 알아봐야할듯?
        Statement stmt = this.database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int orderId = -1;
        while (rs.next()) {
            orderId = rs.getInt(1);
        }
        if (orderId >= 0) {
            String insertTemplate = "INSERT INTO ORDERS VALUES ( ?, ?, ?, ?, ?, TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss'))";
            PreparedStatement ps = this.database.getPreparedStatement(insertTemplate);
            ps.setInt(1, ++orderId);
            int usersId = this.usersModel.getUsers().userId;
            ps.setInt(2, usersId);
            int storeId = this.storeModel.getStoreId(orders.storeName);
            ps.setInt(3, storeId);
            ps.setString(4, orders.payment);
            ps.setString(5, "주문 중");
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            ps.setString(6, date);
            int resInsert = ps.executeUpdate();
            ps.close();
            if (resInsert == 1) {
                ArrayList<OrderMenuDto> orderedMenuList = this.orderMenuModel.insert(orderId, orders.orderMenuDtoList);
                return new OrdersDto(orderId, orders.storeId, orderedMenuList, orders.payment, "주문 중", date);
            }
        }
        return null;
    }
}
