import java.sql.*;

public class Department {
    private final Connection conn;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO DEPARTMENT VALUES ( ? )";

    public Department(Oracle database, Users users) throws SQLException {
        this.conn = database.getConnection();
        this.announcement = new Announcement();
    }
}
