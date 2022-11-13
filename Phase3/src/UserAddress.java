import java.sql.*;

public class UserAddress {
    private final Connection conn;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO USER_ADDRESS VALUES ( ?, ? )";

    public UserAddress(Oracle database, Users users) throws SQLException {
        this.conn = database.getConnection();
        this.announcement = new Announcement();
    }
}
