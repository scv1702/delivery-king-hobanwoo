import java.sql.*;

public class Oracle {
    private final String url;
    private final String id;
    private final String password;

    public Oracle(String url, String id, String password) {
        this.url = url;
        this.id = id;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, id, password);
    }

    public Statement getStatement () throws SQLException {
        return getConnection().createStatement();
    }
}
