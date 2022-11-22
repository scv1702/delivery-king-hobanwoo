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

    public void showStores(ArrayList<StoreDto> storeList) {
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

    public int selectCategoryCount() {
        Scanner in = new Scanner(System.in);
        System.out.println("1. 1개의 카테고리로 검색하기");
        System.out.println("2. 여러 개의 카테고리로 검색하기");
        System.out.print(": ");
        int select = in.nextInt();
        return select;
    }

    public String selectCategory() {
        Scanner in = new Scanner(System.in);
        System.out.print("카테고리: ");
        String category = in.nextLine();
        System.out.println();
        return category;
    }

    public String selectMultipleCategory() {
        Scanner in = new Scanner(System.in);
        System.out.println("카테고리들을 '#' 단위로 입력해주세요.");
        System.out.print("카테고리: ");
        String multipleCategory = in.nextLine();
        System.out.println();
        return multipleCategory;
    }

    public String selectAddress() {
        Scanner in = new Scanner(System.in);
        System.out.print("주소: ");
        String address = in.nextLine();
        System.out.println();
        return address;
    }

    public String showMenuListOfStore() {
        Scanner in = new Scanner(System.in);
        System.out.print("가게 이름: ");
        String storeName = in.nextLine();
        System.out.println();
        return storeName;
    }
}
