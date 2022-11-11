import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;
public class Main {
    /** 신찬규
    public static final String URL = "jdbc:oracle:thin:@localhost:1600:xe";
    public static final String USER_UNIVERSITY = "university";
    public static final String USER_PASSWD = "comp322";
     **/
    // 노준혁
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";

    public static final String USER_UNIVERSITY = "deliverykinghobanwoo"; // DB스키마 변수 수정 아직 안함.
    public static final String USER_PASSWD = "comp322";

    public static Connection conn = null; // Connection object
    public static Statement stmt = null;	// Statement object

    /////////////// Setting  ////////////////////////
    static PreparedStatement ps = null;
    static ResultSet rs= null;
    static Announcement ment = new Announcement(); // announcement를 위한 객체생성
    static int User_ID = 0;

    // PreparedStatement를 통한 SQL Templete 사용
    static String Store_Templete="INSERT INTO STORE VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    static String Menu_Templete="INSERT INTO MENU VALUES ( ?, ?, ?, ?, ?, ? )";
    static String Department_Templete="INSERT INTO DEPARTMENT VALUES ( ? )";
    static String Users_Templete="INSERT INTO USERS VALUES ( ?, ?, ?, ?, ?, ? )";
    static String User_Address_Templete="INSERT INTO USER_ADDRESS VALUES ( ?, ? )";
    static String Coupon_Templete="INSERT INTO COUPON VALUES ( ?, ?, ?, TO_DATE(?, 'yyyy-mm-dd'), ?, ? )";
    static String Review_Templete="INSERT INTO REVIEW VALUES ( ?, ?, ?, ?, ?, TO_DATE(?, 'yyyy-mm-dd') )";
    static String Orders_Templete="INSERT INTO ORDERS VALUES ( ?, ?, ?, ?, ?, TO_DATE(?, 'yyyy-mm-dd') )";
    static String Order_Menu_Templete="INSERT INTO ORDER_MENU VALUES ( ?, ?, ?, ?, ?, ? )";
    static String Contains_Templete="INSERT INTO CONTAINS VALUES ( ?, ?, ? )";
    static String Cooperates_Templete="INSERT INTO COOPERATES VALUES ( ?, ? )";

    static void WaitBeforeReprint(){
        try {
            Thread.sleep(1500); // 바로 다른 출력문이 나오지 않게 뜸 들이기
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
        connectToDb();

        Scanner in = new Scanner(System.in);

        while (true) {
            // Main announcement
            ment.mainAnnouncement();

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
            WaitBeforeReprint(); // 바로 다른 출력문이 나오지 않게 뜸 들이기
            if (isLogin) {
                while (true) {
                    // announcement
                    ment.FunctionSelect();

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
                            // announcement
                            ment.ProgramExitAnnouncement();

                            try {
                                ps.close();
                                rs.close();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }

                            System.exit(0);
                            break;
                    }
                    WaitBeforeReprint(); // 바로 다른 출력문이 나오지 않게 뜸 들이기
                }
            }
        }
    }

    public static void profile() {
        try {
            /////////////쿼리문 수행해서 값 받아오기////////
            String sql = "SELECT * from USERS WHERE USER_ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, User_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                String User_Name = rs.getString(2);
                String Dname = rs.getString(3);
                String Password = rs.getString(4);
                String Phone_Number= rs.getString(5);
                String Membership_Tier = rs.getString(6);

                // announcement
                ment.ProfileAnnouncement(
                        User_ID,
                        User_Name,
                        Dname,
                        Password,
                        Phone_Number,
                        Membership_Tier
                );
            }
        } catch (SQLException e) {
            System.err.println("sql error = " + e.getMessage());
        }
    }

    public static void menu() {
        Scanner in = new Scanner(System.in);
        // Menu 기능 인터페이스 announcement
        ment.menuAnnouncement();

        int funSelect= in.nextInt();
        switch (funSelect){
            case 1: // 음식 카테고리별 제휴업체 조회
                System.out.println("아직 구현 전");
                // 아래 case2 포맷과 동일하게 생성하면 될 듯
                break;
            case 2: // 내 학과 전체 제휴업체 메뉴
                ///////////////   쿼리문      ////////
                ////////////////////////////////////

                ///////////////////////////////////
                /////////// 테스트 코드//////////////
                // Start Announcement
                ment.All_AffiliatesStartAnnouncement();

                int Menu_ID[] = {0, 1};
                int Store_ID[] = {12, 31};
                String Mname[] = {"닭갈비", "소고기"};
                String Description[] = {"닭갈비는 파닥파닥~", "소고기는 음메~"};
                String Image[] = {"12.jpg", "31.jpg"};

                // announcement
                for(int i=0; i<Menu_ID.length; i++){
                    ment.All_AffiliatesAnnouncement(
                            Menu_ID[i],
                            Store_ID[i],
                            Mname[i],
                            Description[i],
                            Image[i]
                    );
                }

                // End Announcement
                ment.All_AffiliatesEndAnnouncement();
                ////////////////////////////////////////
                ////////////////////////////////////////
                break;
            case 3: // 내 주소에 있는 학과 제휴업체 메뉴
                System.out.println("아직 구현 전");
                // case1 포맷과 동일하게 생성하면 될 듯

                break;
        }
    }

    public static void order() {
        Scanner in = new Scanner(System.in);
        ////////////////////////////////////////////////////
        /////////////////////  테스트 코드  //////////////////

        // Start Announcement
        ment.orderStartAnnouncement();

        //////////////// order 주문하기 format /////////////////
        // order 파트 format
        int Order_ID= 0; // 자동으로 오름차순으로 되도록
        int User_ID= 0; // 유저ID는 회원정보에서 가져올 수 있도록
        int Store_ID= 0;
        String Payment="";
        String State= "접수 중"; // 접수 중 으로 고정
        String Created_At= "2021-09-25 15:43:48"; // 작성일 현재 날짜로 받아올 수 있도록 수정

        // order_menu 파트 format
        int Order_Menu_ID= 0;
        String Menu_Name="";
        String Menu_Image="3.jpg"; // 메뉴 이미지도 Menu를 통해서 가져올 수 있도록
        int Menu_Price=0; // 메뉴가격도 Menu를 통해서 가져올 수 있도록
        int Quantity=0;

        // 입력 받기
        System.out.print("│  주문시킬 가게ID : ");
        Store_ID= in.nextInt();
        in.nextLine(); // Int받고 String받을 때 개행문자 제거 하기 위함.

        ///////////문제 있는지 없는지 테스트하고 문제없다면  ////////////////
        ////////// Order_Menu로 바로 이어지도록          ////////////////
        ////// 단일 메뉴뿐 아니라 여러 메뉴를 주문 시킬 수 있도록 수정 필요 //////////
        System.out.print("│  주문 메뉴 이름 : ");
        Menu_Name = in.nextLine();
        System.out.print("│  주문 수량 : ");
        Quantity= in.nextInt();
        in.nextLine(); // Int받고 String받을 때 개행문자 제거 하기 위함.
        System.out.print("│  결제 방식 : ");
        Payment= in.nextLine();

        // End announcement
        ment.orderEndAnnouncement();
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
    }

    public static void myOrder() {
        ///////////////////////////////////
        /////////// 테스트 코드//////////////
        // Start Announcement
        ment.myOrderStartAnnouncement();

        int Order_ID[] = {0, 1};
        int User_ID[] = {7, 8};
        int Store_ID[] = {12, 31};
        String Payment[] = {"카드", "현금"};
        String State[] = {"배달 완료", "배달 중"};
        String Order_Date[] = {
                "2021-08-23 12:37:48",
                "2021-08-26 23:37:49"};

        // announcement
        for(int i=0; i<Order_ID.length; i++){
            ment.myOrderAnnouncement(
                    Order_ID[i],
                    User_ID[i],
                    Store_ID[i],
                    Payment[i],
                    State[i],
                    Order_Date[i]
            );
        }
        // End Announcement
        ment.myOrderEndAnnouncement();
        ////////////////////////////////////////
        ////////////////////////////////////////
    }

    public static void review() {
        ///////////////////////////////////
        ////////////테스트 코드//////////////
        Scanner in = new Scanner(System.in);

        // Review 기능 인터페이스 announcement
        ment.ReviewAnnouncement();

        int funSelect= in.nextInt();
        switch (funSelect){
            case 1: // 리뷰 작성
                in = new Scanner(System.in);
                ///////////////////////////////////////////////////////
                ///////// 테스트 코드 ///////////////////////////////////
                // Start announcement
                ment.ReviewStartAnnouncement(1);

                // Review 작성 format
                int Review_ID= 0; // 자동으로 오름차순으로 되도록
                int User_ID= 0; // 유저ID는 회원정보에서 가져올 수 있도록
                int Store_ID= 0;
                int Star_Rating=0;
                String Comments= "";
                String Created_At= "2021-09-25 15:43:48"; // 작성일 현재 날짜로 받아올 수 있도록

                // 입력 받기
                System.out.print("│  가게ID : ");
                Store_ID= in.nextInt();
                System.out.print("│  평점 : ");
                Star_Rating= in.nextInt();
                in.nextLine(); // Int형 받고 string형 받았을 때의 개행문자 제거
                System.out.print("│  리뷰 내용 : ");
                Comments= in.nextLine();

                //////////////쿼리문///////////////
                //////////////       ////////////
                /////////////////////////////////
                // Data announcement
                ment.ReviewDataAnnouncement(
                        1,
                        Review_ID,
                        User_ID,
                        Store_ID,
                        Star_Rating,
                        Comments,
                        Created_At
                );
                /////////////////////////////////////////////////
                /////////////////////////////////////////////////
                break;
            case 2: // 리뷰 수정
                // Start announcement
                ment.ReviewStartAnnouncement(2);
                System.out.println("아직 구현 전");
                break;
            case 3: // 리뷰 삭제
                // Start announcement
                ment.ReviewStartAnnouncement(3);
                System.out.println("아직 구현 전");
                break;
            case 4: // 내가 쓴 리뷰 조회
                // Start announcement
                ment.ReviewStartAnnouncement(4);
                System.out.println("아직 구현 전");
                break;
            case 5: // 선택 가게 리뷰 조회
                // Start announcement
                ment.ReviewStartAnnouncement(5);
                System.out.println("아직 구현 전");
                break;
        }
        // End announcement
        ment.ReviewEndAnnouncement();
        //////////////////////////////////////////
        //////////////////////////////////////////
    }

    public static void signup() {
        // User_ID는 계속 쓸 수 있도록 전역으로 받고 유지
        // 멤버십티어는 디폴트값 => 고마워요. 입력 받을 필요 X

        // Start announcement
        ment.SignUpStartAnnouncement();

        // 회원가입 정보 입력받기
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String signUpInfo= in.readLine();
            String [] signUpInfoSubStr = signUpInfo.split("#"); // # 단위로 끊어서 받아오기

            String sql ="SELECT NVL(MAX(USER_ID)+1,0) " +      // 이렇게 하면 동시성 문제 있다는데 다른 방법도 알아봐야할듯?
                        "FROM USERS";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                User_ID= rs.getInt(1);
                break;
            }
            sql = Users_Templete;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, User_ID); // User_ID
            ps.setString(2, signUpInfoSubStr[0]); // USER_NAME
            ps.setString(4, signUpInfoSubStr[1]); // PASSWORD
            ps.setString(3, signUpInfoSubStr[2]); // DNAME
            ps.setString(5, signUpInfoSubStr[3]); // PHONE_NUMBER
            ps.setString(6, "고마워요"); // Membership_tier
            int resINSERT = ps.executeUpdate();
            if(resINSERT==0){
                // announcement
                ment.SignUpEndAnnouncement();
            }else{
                System.out.println("양식을 다시 확인하세요.");
            }
        } catch (IOException | SQLException | ArrayIndexOutOfBoundsException e) {
            System.out.println("양식을 다시 확인하세요.");
        }
    }

    public static boolean login() {
        Scanner in = new Scanner(System.in);
        // Login format
        String user_Name= "";
        String password= "";

        // Start announcement
        ment.LoginStartAnnouncement();
        // 입력 받기
        System.out.print("유저이름 : ");
        user_Name= in.nextLine();
        System.out.print("비밀번호 : ");
        password= in.nextLine();

        ////////////쿼리문 회원인지 아닌지 검사///////////
        try {
            String sql = "SELECT User_ID " +
                         "FROM USERS " +
                         "WHERE User_Name=? AND Password=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user_Name);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
                User_ID= rs.getInt(1); // User_ID는 계속 사용할 수 있게 전역으로 받아둠.
            }else{
                // Login Fail announcement
                ment.LoginFailAnnouncement();
                return false;
            }
        }catch(SQLException ex2) {
            System.err.println("sql error = " + ex2.getMessage());
            System.exit(1);
        }

        // End announcement
        ment.LoginEndAnnouncement();
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
            System.out.println("------------------------------------------------------------------");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Cannot get a connection: " + ex.getLocalizedMessage());
            System.err.println("cannot get a connection: " + ex.getMessage());
            System.exit(1);
        }
    }
}