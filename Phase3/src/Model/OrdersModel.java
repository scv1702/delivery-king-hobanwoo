package Model;

import DTO.OrderMenuDto;
import DTO.OrdersDto;
import DTO.StoreDto;

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
            int storeId = this.storeModel.getStoreByName(orders.storeName).getStoreId();
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

    public ArrayList<OrdersDto> getOrdersByUser(int userId) throws SQLException {
        ArrayList<OrdersDto> orderList = new ArrayList<>();

        String sql = "SELECT * FROM ORDERS WHERE USER_ID = ? AND STATE = ?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, "배달 완료");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int orderId = rs.getInt("Order_ID");
            int storeId = rs.getInt("Store_ID");
            String payment = rs.getString("Payment");
            String state = rs.getString("State");
            String orderDate = rs.getString("Order_Date");

            String storeName = storeModel.getStoreNameById(storeId);

            orderList.add(new OrdersDto(
                    orderId,
                    userId,
                    storeId,
                    storeName,
                    payment,
                    state,
                    orderDate
            ));
        }
        if (orderList.size() == 0){
            return null;
        }else {
            return orderList;
        }

    }


}
