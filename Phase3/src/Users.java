import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class Users {
    private final Connection conn;
    private final Announcement announcement;
    private UserAddress userAddress;
    private int userId;
    private int userCount;

    public Users(Oracle database) throws SQLException {
        this.conn = database.getConnection();
        this.announcement = new Announcement();
        this.userId = -1;
        String sql = "SELECT NVL(MAX(USER_ID) + 1,0) FROM USERS"; // 이렇게 하면 동시성 문제 있다는데 다른 방법도 알아봐야할듯?
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            this.userCount = rs.getInt(1);
        }
    }

    public void signUp() throws SQLException {
        announcement.signUpStart();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String signUpInfo= in.readLine();
            String[] signUpInfoSubStr = signUpInfo.split("#");
            in.close();
            String insertTemplate = "INSERT INTO USERS VALUES ( ?, ?, ?, ?, ?, ? )";
            PreparedStatement ps = conn.prepareStatement(insertTemplate);
            userCount++;
            ps.setInt(1, userCount); // User_ID
            ps.setString(2, signUpInfoSubStr[0]); // USER_NAME
            ps.setString(4, signUpInfoSubStr[1]); // PASSWORD
            ps.setString(3, signUpInfoSubStr[2]); // DNAME
            ps.setString(5, signUpInfoSubStr[3]); // PHONE_NUMBER
            ps.setString(6, "고마워요"); // Membership_tier
            int resInsert = ps.executeUpdate();
            if (resInsert == 1) {
                announcement.signUpEnd();
            } else {
                System.out.println("양식을 다시 확인하세요.");
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("양식을 다시 확인하세요." + e.getMessage());
        }
    }

    public void login() throws SQLException {
        Scanner in = new Scanner(System.in);
        announcement.loginStart();
        System.out.print("사용자 이름: ");
        String userName = in.nextLine();
        System.out.print("비밀번호: ");
        String password = in.nextLine();
        String sql = "SELECT User_ID FROM USERS WHERE User_Name=? AND Password=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            this.userId = rs.getInt(1);
        } else {
            announcement.loginFail();
        }
        announcement.loginEnd();
    }

    public boolean isLogin() {
        return userId > 0;
    }

    public void profile() throws SQLException {
        String sql = "SELECT * from USERS WHERE USER_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, this.userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String userName = rs.getString(2);
            String dName = rs.getString(3);
            String password = rs.getString(4);
            String phoneNumber = rs.getString(5);
            String membershipTier = rs.getString(6);
            this.announcement.profile(
                userId,
                userName,
                dName,
                password,
                phoneNumber,
                membershipTier
            );
        }
    }

    public int getUserId() {
        return userId;
    }
}
