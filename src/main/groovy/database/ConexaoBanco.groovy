package database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/linketinder"
    private static final String USER = "Geek"
    private static final String PASSWORD = "university"

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD)
    }
}
