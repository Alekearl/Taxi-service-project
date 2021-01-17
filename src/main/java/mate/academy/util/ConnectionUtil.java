package mate.academy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "root");
        dbProperties.put("password", "Alexandr_24101987");
        String url = "jdbc:mysql://localhost:3306/manufacturer?serverTimezone=UTC";

        try (Connection connection = DriverManager.getConnection(url, dbProperties)) {
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Can't establish connection to DB", e);
        }
    }
}
