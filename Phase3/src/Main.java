import Controller.OrdersController;
import Controller.ReviewController;
import Controller.StoreController;
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

    public static void main(String[] args) throws SQLException {
        Oracle database = new Oracle(DB_URL, DB_ID, DB_PASSWORD);

        // Models
        UsersModel usersModel = new UsersModel(database);
        UserAddressModel userAddressModel = new UserAddressModel(database);
        CouponModel couponModel = new CouponModel(database);
        StoreModel storeModel = new StoreModel(database, usersModel);
        MenuModel menuModel = new MenuModel(database, storeModel);
        OrderMenuModel orderMenuModel = new OrderMenuModel(database, menuModel);
        OrdersModel ordersModel = new OrdersModel(database, usersModel, orderMenuModel, storeModel);
        ReviewModel reviewModel = new ReviewModel(database, storeModel);

        // Controllers
        UsersController usersController = new UsersController(usersModel, userAddressModel, couponModel);
        OrdersController ordersController = new OrdersController(ordersModel);
        StoreController storeController = new StoreController(storeModel, menuModel);
        ReviewController reviewController = new ReviewController(reviewModel, usersModel, ordersModel);

        UsersDto loginedUser = null;

        while (true) {
            switch (startMenu()) {
                case 1:
                    usersController.signUp();
                    break;
                case 2:
                    loginedUser = usersController.login();
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.\n");
                    break;
            }
            if (loginedUser != null) {
                while (true) {
                    switch (mainMenu()) {
                        case 1:
                            usersController.usersMenu();
                            break;
                        case 2:
                            switch (storeController.storeMenu()) {
                                case 1:
                                    storeController.stores();
                                    break;
                                case 2:
                                    storeController.storesByDepartment();
                                    break;
                                case 3:
                                    storeController.storesByCategory();
                                    break;
                                case 4:
                                    storeController.storesByAddress();
                                    break;
                                case 5:
                                    storeController.menuListOfStore();
                                    break;
                                default:
                                    System.out.println("잘못 입력하셨습니다.\n");
                                    break;
                            }
                            break;
                        case 3:
                            ordersController.order();
                            break;
                        case 4:
                            ordersController.myOrder();
                            break;
                        case 5:
                            switch (reviewController.reviewMenu()) {
                                case 1: // 리뷰 쓰기
                                    reviewController.write();
                                    break;
                                case 2: // 리뷰 수정
                                    reviewController.update();
                                    break;
                                case 3: // 리뷰 삭제
                                    reviewController.delete();
                                    break;
                                case 4: // 내가 쓴 리뷰 보기
                                    reviewController.myReview();
                                    break;
                                case 5: // 가게 이름별 리뷰 보기
                                    reviewController.reviewByStoreName();
                                    break;
                                case 6: // 아직 리뷰를 작성하지 않은 주문 내역
                                    reviewController.unReviewedOrdersList();
                                    break;
                                default:
                                    System.out.println("잘못 입력하셨습니다.\n");
                                    break;
                            }
                            break;
                        case 6:
                            programExit();
                            database.closeConnection();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("잘못 입력하셨습니다.\n");
                            break;
                    }
                }
            }
        }
    }

    public static int startMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println("경북대학교 제휴업체 배달 서비스, 배달왕 호반우가 간다!");
        System.out.println("1. 회원 가입");
        System.out.println("2. 로그인");
        System.out.print(": ");
        int select = Integer.parseInt(in.nextLine());
        System.out.println();
        return select;
    }

    public static int mainMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println("1. 내 정보");
        System.out.println("2. 가게 검색");
        System.out.println("3. 주문 하기");
        System.out.println("4. 내 주문 내역");
        System.out.println("5. 리뷰");
        System.out.println("6. 프로그램 종료");
        System.out.print(": ");
        int select = Integer.parseInt(in.nextLine());
        System.out.println();
        return select;
    }

    public static void programExit() {
        System.out.println("다음에 또 만나요!\n");
    }
}