package Model;

import java.sql.*;

public class Oracle {
    private final Connection conn;

    public Oracle(String url, String id, String password) throws SQLException {
        this.conn = DriverManager.getConnection(url, id, password);
    }

    public Statement getStatement() throws SQLException {
        return this.conn.createStatement();
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return this.conn.prepareStatement(sql);
    }

    public void closeConnection() throws SQLException {
        this.conn.close();
    }
}
