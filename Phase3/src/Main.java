import Controller.OrdersController;
import Controller.UsersController;
import DTO.UsersDto;
import Model.*;

import java.sql.*;
import java.util.Scanner;

public class Main {
    // PORT CHANGE!
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1600:xe";
    private static final String DB_ID = "delivery_king_hobanwoo";
    private static final String DB_PASSWORD = "comp322";

    public static void waitBeforeReprint() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        Oracle database = new Oracle(DB_URL, DB_ID, DB_PASSWORD);

        // Models
        UsersModel usersModel = new UsersModel(database);
        UserAddressModel userAddressModel = new UserAddressModel(database);
        CouponModel couponModel = new CouponModel(database);
        StoreModel storeModel = new StoreModel(database, usersModel);
        MenuModel menuModel = new MenuModel(database);
        OrderMenuModel orderMenuModel = new OrderMenuModel(database, menuModel);

        // Controllers
        UsersController usersController = new UsersController(usersModel);
        OrdersController ordersController = new OrdersController(new OrdersModel(database, usersModel, orderMenuModel, storeModel));

        Scanner in = new Scanner(System.in);
        UsersDto isLogined = null;

        while (true) {
            start();
            switch (in.nextInt()) {
                case 1:
                    usersController.signUp();
                    break;
                case 2:
                    isLogined = usersController.login();
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.");
                    break;
            }
            waitBeforeReprint();
            if (isLogined != null) {
                while (true) {
                    functionSelect();
                    switch (in.nextInt()) {
                        case 1:
                            usersController.profile(userAddressModel, couponModel);
                            break;
                        case 2:
                            // menuController.main();
                            break;
                        case 3:
                            ordersController.order();
                            break;
                        case 4:
                            // orders.myOrder();
                            break;
                        case 5:
                            // review.review();
                            break;
                        case 6:
                            programExit();
                            in.close();
                            database.closeConnection();
                            System.exit(0);
                            break;
                    }
                    waitBeforeReprint();
                }
            }
        }
    }

    public static void start() {
        System.out.println(
                "┌-----------------------------------------------┐\n" +
                        "│\t\t\t\t배달왕 호반우가 간다!\t\t\t\t│\n" +
                        "│\t\t\t<경북대학교 제휴업체 배달 서비스>\t\t\t│\n" +
                        "│-----------------------------------------------│\n" +
                        "│\t\t혹시, 배달왕 호반우가 처음이신가요?\t\t\t│\n" +
                        "│\t\t1. 회원가입\t\t\t\t\t\t\t\t│\n" +
                        "│-----------------------------------------------│\n" +
                        "│\t\t회원이라면, 로그인 해주세요.\t\t\t\t\t│\n" +
                        "│\t\t2. 로그인\t\t\t\t\t\t\t\t│\n" +
                        "└-----------------------------------------------┘");
    }

    public static void functionSelect() {
        System.out.println(
                "┌----┬------------------------------------------┐\n" +
                        "│ NO │\t\t\t\t\t기능\t\t\t            │\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 1  │  내 정보 \t\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 2  │  menu();\t\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 3  │  order();\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 4  │  myOrder();\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 5  │  review();\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 6  │  종료하기\t\t\t\t\t\t\t\t\t│\n" +
                        "└----┴------------------------------------------┘");
    }

    public static void programExit() {
        System.out.println("------------------------------------------------------------------\n");
        System.out.println("                         다음에 또 만나요 !                            ");
    }
}