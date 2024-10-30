package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//code --------


public class OwnMySQLConnection {
        static String url = "jdbc:mysql://localhost:3306/crowdfunding";
        static String user = "root";
        static String password = "vir@t18sql";
        private static Connection connection;

        public static Connection getConnection() {
            if (connection == null) {
                try {
                    connection = DriverManager.getConnection(url, user, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return connection;
        }
}
