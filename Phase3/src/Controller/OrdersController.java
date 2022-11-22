package Controller;

import DTO.OrdersDto;
import Model.OrdersModel;
import View.OrdersView;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersController {
    private final OrdersModel ordersModel;
    private final OrdersView ordersView = new OrdersView();

    public OrdersController(OrdersModel ordersModel) {
        this.ordersModel = ordersModel;
    }

    public void order() {
        try {
            OrdersDto orders = ordersView.order();
            OrdersDto ordered = this.ordersModel.insert(orders);
            if (ordered != null) {
                this.ordersView.orderEnd();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void myOrder(){
        try {
            ArrayList<OrdersDto> myOrder = this.ordersModel.getOrdersByUser();
            if (myOrder != null) {
                this.ordersView.showMyOrders(this.ordersModel.getOrdersByUser());
            } else {
                this.ordersView.noMyOrder();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}
