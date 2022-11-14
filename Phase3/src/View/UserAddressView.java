package View;

import DTO.UserAddressDto;

import java.util.ArrayList;
import java.util.Scanner;

public class UserAddressView {
    public void showMyAddressStart() {
        System.out.println(
                "┌---------------------------------------------------------------┐\n" +
                "│       \t\t\t 등록된 주소 목록 조회 \t\t\t\t            │\n" +
                "│---------------------------------------------------------------│");
    }

    public void showMyAddress(ArrayList<UserAddressDto> userAddressDto) {
        for (int i = 0; i < userAddressDto.size(); i++) {
            String output = String.format(
                    "│      등록된 주소 %d : %s"
                    , i + 1, userAddressDto.get(i).uAddress);
            System.out.println(output);
        }
    }

    public void showMyAddressEnd() {
        System.out.println("└---------------------------------------------------------------┘\n");
    }

    public void insertAddressStart() {
        System.out.println("------------------------------------------------------------------\n");
        System.out.println("                     등록할 주소를 입력해주세요.                       \n");
    }

    public String insertAddress() {
        Scanner in = new Scanner(System.in);
        System.out.print("주소 입력: ");
        String uaddress = in.nextLine();
        return uaddress;
    }

    public void insertAddressEnd() {
        System.out.println("                          주소 등록 성공 !");
        System.out.println("------------------------------------------------------------------\n");
    }
}
