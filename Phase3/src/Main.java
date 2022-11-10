import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class Main {
    // 신찬규
    // public static final String URL = "jdbc:oracle:thin:@localhost:1600:xe";

    // 노준혁
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";

    public static final String USER_UNIVERSITY = "university";
    public static final String USER_PASSWD = "comp322";

    public static Connection conn = null; // Connection object
    public static Statement stmt = null;	// Statement object

    static Announcement ment = new Announcement(); // announcement를 위한 객체생성
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
            if (isLogin) {
                // announcement
                ment.FunctionSelect();

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
                            // announcement
                            ment.ProgramExitAnnouncement();
                            System.exit(0);
                            break;
                    }
                }
            }
        }
    }

    public static void profile() {
        /////////////쿼리문 수행해서 값 받아오기////////
        ////////                           ////////
        ///////////////////////////////////////////
        /////////// 테스트 코드//////////////
        int User_ID = 0;
        String User_Name = "나는 배달왕 이지안";
        String Dname = "컴퓨터학부";
        String Password = "abc12345";
        String Phone_Number = "010-300-0030";
        String Membership_Tier = "고마워요";
        ///////////////////////////////////

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

    public static void menu() {
        Scanner in = new Scanner(System.in);

        // announcement
        ment.menuAnnouncement();

        int funSelect= in.nextInt();
        switch (funSelect){
            case 1: // 내 학과 전체 제휴업체 메뉴
                ///////////////   쿼리문      ////////
                ///////////////              ///////
                ////////////////////////////////////

                ///////////////////////////////////
                /////////// 테스트 코드//////////////
                // StartAnnouncement
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

                // EndAnnouncement
                ment.All_AffiliatesEndAnnouncement();

                ////////////////////////////////////////
                ////////////////////////////////////////
                break;
            case 2: // 내 주소에 있는 학과 제휴업체 메뉴
                System.out.println("아직 구현 전");
                // case1 포맷과 동일하게 생성하면 될 듯

                break;
        }
    }

    public static void order() {
        System.out.println("order Function");
    }

    public static void myOrder() {
        System.out.println("mrOrder Function");
    }

    public static void review() {
        System.out.println("review Function");
    }

    public static void signup() {
        /**
         유저이름#비밀번호#학과(학부)#연락처
         ID값은 자동으로 오름차순.
         멤버십티어는 디폴트값 => 고마워요. 입력 받을 필요 X
         **/

        // announcement
        ment.SignUpStartAnnouncement();

        // 회원가입 정보 입력받기
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String signUpInfo= in.readLine();
            String [] signUpInfoSubStr = signUpInfo.split("#"); // # 단위로 끊어서 받아오기

            ///////////////테스트 코드: 쿼리문 들어가야 됨.////////////////////////
            for(int i=0; i<signUpInfoSubStr.length; i++){
                System.out.println(signUpInfoSubStr[i]);
            }
            //////////////////////////////////////////////////////////////////

            // announcement
            ment.SignUpEndAnnouncement();
            Thread.sleep(1000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean login() {
        Scanner in = new Scanner(System.in);
        // Login format
        String userName= "";
        String password= "";

        // announcement
        ment.LoginStartAnnouncement();
        // 입력 받기
        System.out.print("유저이름 : ");
        userName= in.nextLine();
        System.out.print("비밀번호 : ");
        password= in.nextLine();

        ////////////쿼리문 회원인지 아닌지 검사///////////
        ///////////                        //////////
        /////////////////////////////////////////////

        // announcement
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