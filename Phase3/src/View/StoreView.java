package View;

import DTO.StoreDto;

import java.util.ArrayList;
import java.util.Scanner;

public class StoreView {
    public void stores(ArrayList<StoreDto> storeList) {
        for (StoreDto store: storeList) {
            System.out.println(store.getStoreName());
            System.out.println(store.getAddress());
            System.out.println(store.getFoodCategory());
            System.out.println(store.getPhoneNumber());
            System.out.println(store.getDescription());
            System.out.println(store.getDeliveryFee());
            System.out.println(store.getBusinessHour());
        }
    }

    public String storesByCategory() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the category of the store you want to see: ");
        return in.nextLine();
    }

    public String storesByAddress() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the address of the store you want to see: ");
        return in.nextLine();
    }

    public String menuListOfStore() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the store you want to see: ");
        return in.nextLine();
    }
}
