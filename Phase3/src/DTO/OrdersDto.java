package DTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrdersDto {
    public int orderId;
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
}
