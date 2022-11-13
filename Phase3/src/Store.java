import java.sql.*;

public class Store {
    private final Connection conn;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO STORE VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";

    public Store(Oracle database, Users users) throws SQLException {
        this.conn = database.getConnection();
        this.announcement = new Announcement();
    }
}
