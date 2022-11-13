package Model;

import java.sql.*;

public class Oracle {
    private final String url;
    private final String id;
    private final String password;
    private final Connection conn;

    public Oracle(String url, String id, String password) throws SQLException {
        this.url = url;
        this.id = id;
        this.password = password;
        this.conn = DriverManager.getConnection(url, id, password);
    }

    public Statement getStatement() throws SQLException {
        return this.conn.createStatement();
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return this.conn.prepareStatement(sql);
    }
}
