package View;

import Controller.UsersController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UsersView {

    public void signUpStart() {
        System.out.println("------------------------------------------------------------------\n");
        System.out.println("                 아래 정보를 '#' 단위로 입력해주세요.");
        System.out.println("유저이름(30자 이내), 비밀번호(15자 이내), 학과(학부), 연락처(000-000-0000) \n");
    }

    public void signUpEnd() {
        System.out.println("                           회원가입 성공 !");
        System.out.println("------------------------------------------------------------------\n");
    }

    public void loginStart() {
        System.out.println("------------------------------------------------------------------\n");
        System.out.println("               유저이름과 비밀번호를 입력해주세요.                       \n");
    }

    public void loginEnd() {
        System.out.println("                         로그인 성공!                                 ");
        System.out.println("------------------------------------------------------------------\n");
    }

    public void loginFail() {
        System.out.println("           회원이 아닙니다! 회원가입 후 이용해주세요.                      ");
        System.out.println("------------------------------------------------------------------\n");
    }

    public void signUp() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String signUpInfo = in.readLine();
            String[] signUpInfoSubStr = signUpInfo.split("#");
            in.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
