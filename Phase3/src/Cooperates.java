import java.sql.*;

public class Cooperates {
    private final Connection conn;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO COOPERATES VALUES ( ?, ? )";

    public Cooperates(Oracle database, Users users) throws SQLException {
        this.conn = database.getConnection();
        this.announcement = new Announcement();
    }
}