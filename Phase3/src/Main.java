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
        StoreModel storeModel = new StoreModel(database);
        MenuModel menuModel = new MenuModel(database, storeModel);
        OrderMenuModel orderMenuModel = new OrderMenuModel(database, menuModel);
        ContainsModel containsModel = new ContainsModel(database, orderMenuModel);
        OrdersModel ordersModel = new OrdersModel(database, usersModel, orderMenuModel, storeModel);
        ReviewModel reviewModel = new ReviewModel(database, storeModel);

        // Controllers
        UsersController usersController = new UsersController(usersModel, userAddressModel, couponModel);
        OrdersController ordersController = new OrdersController(ordersModel);
        StoreController storeController = new StoreController(storeModel, menuModel, usersModel);
        ReviewController reviewController = new ReviewController(reviewModel, usersModel, ordersModel, containsModel, storeModel);

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
                    System.out.println("?????? ?????????????????????.\n");
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
                                    System.out.println("?????? ?????????????????????.\n");
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
                                case 1: // ?????? ??????
                                    reviewController.write();
                                    break;
                                case 2: // ?????? ??????
                                    reviewController.update();
                                    break;
                                case 3: // ?????? ??????
                                    reviewController.delete();
                                    break;
                                case 4: // ?????? ??? ?????? ??????
                                    reviewController.myReview();
                                    break;
                                case 5: // ?????? ????????? ?????? ??????
                                    reviewController.reviewByStoreName();
                                    break;
                                case 6: // ?????? ????????? ???????????? ?????? ?????? ??????
                                    reviewController.unReviewedOrdersList();
                                    break;
                                case 7: // ????????? ????????? ?????? ??????
                                    reviewController.storesByMyReview();
                                    break;
                                default:
                                    System.out.println("?????? ?????????????????????.\n");
                                    break;
                            }
                            break;
                        case 6:
                            programExit();
                            database.closeConnection();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("?????? ?????????????????????.\n");
                            break;
                    }
                }
            }
        }
    }

    public static int startMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println("??????????????? ???????????? ?????? ?????????, ????????? ???????????? ??????!");
        System.out.println("1. ?????? ??????");
        System.out.println("2. ?????????");
        System.out.print(": ");
        int select = Integer.parseInt(in.nextLine());
        System.out.println();
        return select;
    }

    public static int mainMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println("1. ??? ??????");
        System.out.println("2. ?????? ??????");
        System.out.println("3. ?????? ??????");
        System.out.println("4. ??? ?????? ??????");
        System.out.println("5. ??????");
        System.out.println("6. ???????????? ??????");
        System.out.print(": ");
        int select = Integer.parseInt(in.nextLine());
        System.out.println();
        return select;
    }

    public static void programExit() {
        System.out.println("????????? ??? ?????????!\n");
    }
}