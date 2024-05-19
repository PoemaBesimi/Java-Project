package com.bankapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Create Account table
            String createAccountTable = "CREATE TABLE Account (" +
                                        "accountId VARCHAR(255) PRIMARY KEY," +
                                        "userName VARCHAR(255)," +
                                        "balance DOUBLE)";
            stmt.execute(createAccountTable);

            // Create Transaction table
            String createTransactionTable = "CREATE TABLE Transaction (" +
                                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                                            "amount DOUBLE," +
                                            "originatingAccountId VARCHAR(255)," +
                                            "resultingAccountId VARCHAR(255)," +
                                            "reason VARCHAR(255)," +
                                            "FOREIGN KEY (originatingAccountId) REFERENCES Account(accountId)," +
                                            "FOREIGN KEY (resultingAccountId) REFERENCES Account(accountId))";
            stmt.execute(createTransactionTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
