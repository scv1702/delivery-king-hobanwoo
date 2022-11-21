package View;

import DTO.UserAddressDto;

import java.util.ArrayList;
import java.util.Scanner;

public class UserAddressView {
    public void showMyAddress(ArrayList<UserAddressDto> userAddressList) {
        int i = 0;
        for (UserAddressDto userAddress : userAddressList) {
            System.out.println(i + 1 + ". " + userAddress.uAddress);
            i++;
        }
        System.out.println();
    }

    public String insertAddress() {
        Scanner in = new Scanner(System.in);
        System.out.print("등록할 주소: ");
        String address = in.nextLine();
        System.out.println();
        return address;
    }

    public void insertAddressEnd() {
        System.out.println("주소가 등록됐습니다.\n");
    }
}
