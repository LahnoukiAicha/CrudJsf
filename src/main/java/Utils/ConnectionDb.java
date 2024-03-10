package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
    private static Connection conn;

    public static Connection connectionBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/idolDB", "root", "Jawda123..");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load MySQL JDBC driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to establish a connection to the database.");
            e.printStackTrace();
        }
        return conn;
    }
}
