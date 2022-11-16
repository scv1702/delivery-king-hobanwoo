package View;

import DTO.MenuDto;
import DTO.OrderMenuDto;
import DTO.OrdersDto;

import java.util.ArrayList;
import java.util.Scanner;

public class OrdersView {
    public void orderStart() {
        System.out.println(
                "┌---------------------------------------------------------------┐\n" +
                        "│       \t\t\t\t 주문 하기 페이지 \t\t\t\t            │\n" +
                        "│---------------------------------------------------------------│");
    }

    public void orderEnd(OrdersDto ordersDto) {
        System.out.println(ordersDto.orderId);
        System.out.println(ordersDto.storeId);
        for (OrderMenuDto orderMenu: ordersDto.orderMenuDtoList) {
            System.out.println(orderMenu.menuName);
            System.out.println(orderMenu.quantity);
        }
        System.out.println(ordersDto.payment);
        System.out.println(ordersDto.state);
        System.out.println(ordersDto.orderDate);
        System.out.println("│       \t\t\t\t 주문 접수 완료 ! \t\t\t            │");
        System.out.println("└---------------------------------------------------------------┘\n");
    }

    public OrdersDto order() {
        Scanner in = new Scanner(System.in);
        System.out.print("│  가게 이름: ");
        String storeName = in.nextLine();
        System.out.print("│  주문할 메뉴 개수: ");
        int menuCount = Integer.parseInt(in.nextLine());
        ArrayList<OrderMenuDto> orderMenuList = new ArrayList<>();
        for (int i = 0; i < menuCount; i++) {
            System.out.print("│  메뉴 이름 : ");
            String menuName = in.nextLine();
            System.out.print("│  주문 수량 : ");
            int quantity = Integer.parseInt(in.nextLine());
            orderMenuList.add(new OrderMenuDto(menuName, quantity));
        }
        System.out.print("│  결제 방식 : ");
        String payment = in.nextLine();
        return new OrdersDto(storeName, orderMenuList, payment);
    }
}