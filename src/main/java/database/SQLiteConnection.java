package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteConnection {
    private static final String URL = "jdbc:sqlite:/home/edgaras/Desktop/BankingApplication/BankingProject/src/main/java/database/database.db";
    private Connection connection;

    public SQLiteConnection() {
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Connection established");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                System.out.println("Failed to close connection: " + e.getMessage());
            }
        }
    }

    public void selectAll(String tableName) {
        String sql = "SELECT * FROM " + tableName;

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Assuming your table has an 'id', 'name', and 'balance' column
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double balance = rs.getDouble("balance");
                System.out.println(id + "\t" + name + "\t" + balance);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        SQLiteConnection sqliteConnect = new SQLiteConnection();
        // Replace 'your_table_name' with your actual table name
        sqliteConnect.selectAll("BankAccounts");
        sqliteConnect.closeConnection();
    }
}
