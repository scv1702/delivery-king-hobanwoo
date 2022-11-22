package View;

import DTO.OrderMenuDto;
import DTO.OrdersDto;

import java.util.ArrayList;
import java.util.Scanner;

public class OrdersView {
    public void orderEnd() {
        System.out.println("주문 접수 됐습니다.\n");
    }

    public OrdersDto order() {
        Scanner in = new Scanner(System.in);
        System.out.print("가게 이름: ");
        String storeName = in.nextLine();
        System.out.print("주문 메뉴 개수: ");
        int menuCount = Integer.parseInt(in.nextLine());
        ArrayList<OrderMenuDto> orderMenuList = new ArrayList<>();
        for (int i = 0; i < menuCount; i++) {
            System.out.print("메뉴 이름: ");
            String menuName = in.nextLine();
            System.out.print("주문 수량: ");
            int quantity = Integer.parseInt(in.nextLine());
            orderMenuList.add(new OrderMenuDto(menuName, quantity));
        }
        System.out.print("결제 방식(카드, 현금) : ");
        String payment = in.nextLine();
        System.out.println();
        return new OrdersDto(storeName, orderMenuList, payment);
    }

    public void showMyOrders(ArrayList<OrdersDto> ordersList) {
        for (OrdersDto orders : ordersList){
            System.out.println("가게 이름 : " + orders.getStoreName());
            System.out.println("결제 수단 : " + orders.getPayment());
            System.out.println("주문 상태: " + orders.getState());
            System.out.println("주문일 : " + orders.getOrderDate() + '\n');
        }
    }

    public void noMyOrder() {
        System.out.println("주문 내역이 없습니다.\n");
    }
}
