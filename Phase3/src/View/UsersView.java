package View;

import DTO.UsersDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UsersView {

    public void signUpStart() {
        System.out.println("------------------------------------------------------------------\n");
        System.out.println("                 아래 정보를 '#' 단위로 입력해주세요.");
        System.out.println("유저이름(30자 이내), 비밀번호(15자 이내), 단과대학, 연락처(000-000-0000) \n");
    }

    public UsersDto signUp() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String signUpInfo = in.readLine();
            String [] signUpInfoSubStr = signUpInfo.split("#"); // # 단위로 끊어서 받아오기
            String username =  signUpInfoSubStr[0];
            String password = signUpInfoSubStr[1];
            String dName = signUpInfoSubStr[2];
            String phoneNumber = signUpInfoSubStr[3];
            return new UsersDto(username, password, dName, phoneNumber);
        } catch (IOException e) {
            System.out.println("입력 오류");
        }
        return null;
    }

    public void signUpEnd() {
        System.out.println("                           회원가입 성공 !");
        System.out.println("------------------------------------------------------------------\n");
    }

    public void loginStart() {
        System.out.println("------------------------------------------------------------------\n");
        System.out.println("               유저이름과 비밀번호를 입력해주세요.                       \n");
    }

    public UsersDto login() {
        Scanner in = new Scanner(System.in);
        System.out.print("사용자 이름: ");
        String userName = in.nextLine();
        System.out.print("비밀번호: ");
        String password = in.nextLine();
        return new UsersDto(userName, password);
    }

    public void loginEnd() {
        System.out.println("                         로그인 성공!                                 ");
        System.out.println("------------------------------------------------------------------\n");
    }

    public void loginFail() {
        System.out.println("           회원이 아닙니다! 회원가입 후 이용해주세요.                      ");
        System.out.println("------------------------------------------------------------------\n");
    }

    public void profile() {
        System.out.println(
                        "┌----┬------------------------------------------┐\n" +
                        "│ NO │\t\t\t\t\t기능\t\t\t            │\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 1  │  내 프로필 보기\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 2  │  주소 등록하기\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 3  │  등록된 주소 목록 조회\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 4  │  보유 쿠폰 조회\t\t\t\t\t\t\t│\n" +
                        "└----┴------------------------------------------┘");
    }

    public void myProfile(UsersDto usersDto) {
        String output = String.format(
                "┌-----------------------------------------------┐\n" +
                "│                  내 정보 확인                   │\n" +
                "│----┬------------------------------------------│\n" +
                "│ 1  │  유저번호 : %d\n" +
                "│----┼------------------------------------------│\n" +
                "│ 2  │  유저이름 : %s \n" +
                "│----┼------------------------------------------│\n" +
                "│ 3  │  학과 : %s\n" +
                "│----┼------------------------------------------│\n" +
                "│ 4  │  비밀번호 : %s\n" +
                "│----┼------------------------------------------│\n" +
                "│ 5  │  연락처 : %s\n" +
                "│----┼------------------------------------------│\n" +
                "│ 6  │  멤버십 등급 : %s\n" +
                "└----┴------------------------------------------┘"
                , usersDto.userId, usersDto.username, usersDto.dName, usersDto.password, usersDto.phoneNumber, usersDto.membershipTier);
        System.out.println(output);
    }
}