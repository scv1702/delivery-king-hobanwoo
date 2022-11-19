package DTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrdersDto {
    public int orderId;
    public int userId;
    public int storeId;
    public String storeName;
    public String payment;
    public String state;
    public String orderDate;
    public ArrayList<OrderMenuDto> orderMenuDtoList;

    public OrdersDto(String storeName, ArrayList<OrderMenuDto> orderMenuDtoList, String payment) {
        this.storeName = storeName;
        this.orderMenuDtoList = orderMenuDtoList;
        this.payment = payment;
    }

    public OrdersDto(int ordersCount, int storeId, ArrayList<OrderMenuDto> orderMenuDtoList, String payment, String state, String date) {
        this.orderId = ordersCount;
        this.storeId = storeId;
        this.orderMenuDtoList = orderMenuDtoList;
        this.payment = payment;
        this.state = state;
        this.orderDate = date;
    }

    public OrdersDto(int orderId, int userId,  int storeId, String storeName, String payment, String state, String orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.payment = payment;
        this.state = state;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getPayment() {
        return payment;
    }

    public String getState() {
        return state;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public ArrayList<OrderMenuDto> getOrderMenuDtoList() {
        return orderMenuDtoList;
    }
}
