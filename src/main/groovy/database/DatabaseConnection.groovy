package database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseConnection {
    private static DatabaseConnection instance
    private Connection connection

    private static final String URL = "jdbc:postgresql://localhost:5432/linketinder"
    private static final String USER = "Geek"
    private static final String PASSWORD = "university"

    private DatabaseConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD)
    }

    static synchronized DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection()
        }
        return instance
    }

    Connection getConnection() {
        return connection
    }
}
