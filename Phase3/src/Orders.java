import java.sql.*;
import java.util.Scanner;

public class Orders {
    private final Connection conn;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO ORDERS VALUES ( ?, ?, ?, ?, ?, TO_DATE(?, 'yyyy-mm-dd'))";
    private int userId;
    private int orderCount;
    private Users users;

    public Orders(Oracle database, Users users) throws SQLException {
        this.conn = database.getConnection();
        this.announcement = new Announcement();
        this.users = users;
        String sql = "SELECT NVL(MAX(Order_ID) + 1,0) FROM ORDERS"; // 이렇게 하면 동시성 문제 있다는데 다른 방법도 알아봐야할듯?
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            this.orderCount = rs.getInt(1);
        }
    }

    public void order(String storeName, String menuName, int quantity, String payment) throws SQLException {
        // String createdAt = new Date(System.currentTimeMillis()).toString();
        announcement.orderEnd();
    }

    public void temp() {

    }

    public void myOrder() throws SQLException {
        String sql = "SELECT * FROM ORDERS WHERE USER_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(users.getUserId());
        ps.setInt(1, users.getUserId());
        ResultSet rs = ps.executeQuery();
        announcement.myOrderStart();
        if (rs.next()) {
            int orderId = rs.getInt(1);
            int userId = rs.getInt(2);
            int storeId = rs.getInt(3);
            String payment = rs.getString(4);
            String state = rs.getString(5);
            String orderDate = rs.getString(6);
            this.announcement.myOrder(
                orderId,
                userId,
                storeId,
                payment,
                state,
                orderDate
            );
        }
        announcement.myOrderEnd();
    }
}
