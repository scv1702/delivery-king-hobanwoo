import java.sql.*;
import java.util.Scanner;

public class Main {
    public static final String URL = "jdbc:oracle:thin:@localhost:1600:xe";
    public static final String USER_UNIVERSITY = "university";
    public static final String USER_PASSWD = "comp322";

    public static Connection conn = null; // Connection object
    public static Statement stmt = null;	// Statement object

    public static void main(String[] args) {
        connectToDb();

        System.out.println("1. 회원 가입");
        System.out.println("2. 로그인");

        Scanner in = new Scanner(System.in);

        while (true) {
            boolean isLogin = false;
            switch (in.nextInt()) {
                case 1:
                    signup();
                    break;
                case 2:
                    isLogin = login();
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.");
                    break;
            }
            if (isLogin) {
                while (true) {
                    switch (in.nextInt()) {
                        case 1:
                            profile();
                            break;
                        case 2:
                            menu();
                            break;
                        case 3:
                            order();
                            break;
                        case 4:
                            myOrder();
                            break;
                        case 5:
                            review();
                            break;
                        case 6:
                            System.exit(0);
                            break;
                    }
                }
            }
        }
    }

    public static void profile() {

    }

    public static void menu() {

    }

    public static void order() {

    }

    public static void myOrder() {

    }

    public static void review() {

    }

    public static void signup() {

    }

    public static boolean login() {
        return true;
    }

    public static void connectToDb() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Loading: Success!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver Loading = " + e.getMessage());
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(URL, USER_UNIVERSITY, USER_PASSWD);
            stmt = conn.createStatement();
            System.out.println("Oracle Connected.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Cannot get a connection: " + ex.getLocalizedMessage());
            System.err.println("cannot get a connection: " + ex.getMessage());
            System.exit(1);
        }
    }
}