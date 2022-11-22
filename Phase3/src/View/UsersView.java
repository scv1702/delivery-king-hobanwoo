package View;

import DTO.UsersDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UsersView {
    public UsersDto signUp() {
        System.out.println("아래 정보를 '#' 단위로 입력해주세요.");
        System.out.println("사용자 이름(30자 이내)#비밀번호(15자 이내)#단과대학#연락처(000-000-0000)");
        System.out.print(": ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String signUpInfo = in.readLine();
            System.out.println();
            String[] signUpInfoSubStr = signUpInfo.split("#"); // # 단위로 끊어서 받아오기
            String username = signUpInfoSubStr[0];
            String password = signUpInfoSubStr[1];
            String dName = signUpInfoSubStr[2];
            String phoneNumber = signUpInfoSubStr[3];
            return new UsersDto(username, password, dName, phoneNumber);
        } catch (IOException e) {
            System.out.println("다시 입력해주세요.\n");
        }
        return null;
    }

    public void signUpEnd() {
        System.out.println("회원 가입 됐습니다.\n");
    }

    public void loginStart() {
        System.out.println("사용자 이름과 비밀번호를 입력해주세요.");
    }

    public UsersDto login() {
        Scanner in = new Scanner(System.in);
        System.out.print("사용자 이름: ");
        String userName = in.nextLine();
        System.out.print("비밀번호: ");
        String password = in.nextLine();
        System.out.println();
        return new UsersDto(userName, password);
    }

    public void loginEnd() {
        System.out.println("로그인 됐습니다.\n");
    }

    public void loginFail() {
        System.out.println("사용자 이름 또는 비밀번호가 맞지 않습니다.\n");
    }

    public int usersMenu() {
        System.out.println("1. 내 프로필");
        System.out.println("2. 주소 등록하기");
        System.out.println("3. 내 주소 목록");
        System.out.println("4. 내 쿠폰 목록");
        System.out.print(": ");
        Scanner in = new Scanner(System.in);
        int select = Integer.parseInt(in.nextLine());
        System.out.println();
        return select;
    }

    public void showMyProfile(UsersDto usersDto) {
        System.out.println("사용자 이름: " + usersDto.username);
        System.out.println("단과대학: " + usersDto.dName);
        System.out.println("연락처: " + usersDto.phoneNumber);
        System.out.println("멤버십 등급: " + usersDto.membershipTier + '\n');
    }
}