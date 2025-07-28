import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public static Connection getConnection() {
        String dbUrl = "jdbc:mysql://localhost:3306/db1";
        String dbUser = "root";
        String dbPassword = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            return connection;
        } catch (Exception e) {
            return null;
        }
    }
}
