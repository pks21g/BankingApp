package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static Connection con = null;

    static {
        final String url = "jdbc:mysql://localhost:3306/banking_app";
        final String user = "root";
        final String password = "password";


        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return con;
    }
    public static void closeConnection() throws SQLException {
        con.close();
    }
}
