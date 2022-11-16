package Controller;

import DTO.OrdersDto;
import DTO.UsersDto;
import Model.OrdersModel;
import View.OrdersView;

import java.sql.SQLException;

public class OrdersController {
    private final OrdersModel ordersModel;
    private final OrdersView ordersView = new OrdersView();

    public OrdersController(OrdersModel ordersModel) { this.ordersModel = ordersModel; };

    public void order() {
        this.ordersView.orderStart();
        try {
            OrdersDto orders = ordersView.order();
            OrdersDto ordered = this.ordersModel.insert(orders);
            if (ordered != null) {
                this.ordersView.orderEnd(ordered);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}