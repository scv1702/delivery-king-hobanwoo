package DTO;

public class OrderMenuDto {
    public int orderMenuId;
    public int orderId;
    public String menuName;
    public String image;
    public int price;
    public int quantity;

    public OrderMenuDto(int orderMenuId, int orderId, String menuName, String image, int price, int quantity) {
        this.orderMenuId = orderMenuId;
        this.orderId = orderId;
        this.menuName = menuName;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderMenuDto(String menuName, int quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }
}
