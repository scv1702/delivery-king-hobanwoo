import java.sql.*;

public class Coupon {
    private final Connection conn;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO COUPON VALUES ( ?, ?, ?, TO_DATE(?, 'yyyy-mm-dd'), ?, ?)";

    public Coupon(Oracle database, Users users) throws SQLException {
        this.conn = database.getConnection();
        this.announcement = new Announcement();
    }
}
