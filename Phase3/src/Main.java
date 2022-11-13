import Model.Menu;
import Model.Oracle;
import Model.Orders;
import Model.UsersModel;
import View.Announcement;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1600:xe";
    private static final String DB_ID = "delivery_king_hobanwoo";
    private static final String DB_PASSWORD = "comp322";
    private static final Announcement announcement = new Announcement();

    public static void waitBeforeReprint(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) throws SQLException {
        Oracle database = new Oracle(DB_URL, DB_ID, DB_PASSWORD);
        UsersModel usersModel = new UsersModel(database);
        Menu menu = new Menu(database, usersModel);
        Review review = new Review(database, usersModel);
        Orders orders = new Orders(database, usersModel);
        Scanner in = new Scanner(System.in);

        try {
            while (true) {
                announcement.main();
                switch (in.nextInt()) {
                    case 1:
                        usersModel.signUp();
                        break;
                    case 2:
                        usersModel.login();
                        break;
                    default:
                        System.out.println("잘못 입력하셨습니다.");
                        break;
                }
                waitBeforeReprint();
                if (usersModel.isLogin()) {
                    while (true) {
                        announcement.functionSelect();
                        switch (in.nextInt()) {
                            case 1:
                                usersModel.profile();
                                break;
                            case 2:
                                menu.main();
                                break;
                            case 3:
                                System.out.print("│  가게 이름 : ");
                                String storeName = in.nextLine();
                                System.out.print("│  주문 메뉴 이름 : ");
                                String menuName = in.nextLine();
                                System.out.print("│  주문 수량 : ");
                                int quantity = in.nextInt();
                                System.out.print("│  결제 방식 : ");
                                String payment = in.nextLine();
                                orders.order(storeName, menuName, quantity, payment);
                                break;
                            case 4:
                                orders.myOrder();
                                break;
                            case 5:
                                review.review();
                                break;
                            case 6:
                                announcement.programExit();
                                in.close();
                                System.exit(0);
                                break;
                        }
                        waitBeforeReprint();
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }
}