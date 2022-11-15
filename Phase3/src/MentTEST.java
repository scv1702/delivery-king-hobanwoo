import Model.Oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MentTEST {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1600:xe";
    private static final String DB_ID = "delivery_king_hobanwoo";
    private static final String DB_PASSWORD = "comp322";

    public static void main(String[] args) throws SQLException {
        Oracle database = new Oracle(DB_URL, DB_ID, DB_PASSWORD);
        Statement stmt = database.getStatement();
        String sql = "SELECT Mname FROM MENU WHERE Mname = '매콤갈비'";
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1));
        }
    }
}
