package persistencia.opcional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {  // Implementación alternativa
    private static final String URL = "jdbc:mysql://localhost:3306/Cine_DB"
                                    + "?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver MySQL Connector/J", e);
        }
    }
}
