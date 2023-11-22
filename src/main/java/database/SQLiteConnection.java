package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class SQLiteConnection {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:src/main/java/database/database.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
