package View;

import DTO.StoreDto;

import java.util.ArrayList;
import java.util.Scanner;

public class StoreView {
    public int storeMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println("1. 전체 가게 목록");
        System.out.println("2. 내 단과대학 제휴 업체 목록");
        System.out.println("3. 카테고리별 가게 목록");
        System.out.println("4. 내 주변 업체 목록");
        System.out.println("5. 가게 메뉴 목록");
        System.out.print(": ");
        int select = Integer.parseInt(in.nextLine());
        System.out.println();
        return select;
    }

    public void stores(ArrayList<StoreDto> storeList) {
        for (StoreDto store: storeList) {
            System.out.println("가게 이름: " + store.getStoreName());
            System.out.println("가게 주소: " + store.getAddress());
            System.out.println("카테고리: " + store.getFoodCategory());
            System.out.println("전화번호: " + store.getPhoneNumber());
            System.out.println("가게 설명: " + store.getDescription());
            System.out.println("배달 요금: " + store.getDeliveryFee());
            System.out.println("영업 시간: " + store.getBusinessHour() + '\n');
        }
    }

    public String storesByCategory() {
        Scanner in = new Scanner(System.in);
        System.out.print("카테고리: ");
        String category = in.nextLine();
        System.out.println();
        return category;
    }

    public String storesByAddress() {
        Scanner in = new Scanner(System.in);
        System.out.print("주소: ");
        String address = in.nextLine();
        System.out.println();
        return address;
    }

    public String menuListOfStore() {
        Scanner in = new Scanner(System.in);
        System.out.print("가게 이름: ");
        String storeName = in.nextLine();
        System.out.println();
        return storeName;
    }
}
