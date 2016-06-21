package root.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static root.utils.ResourceManager.DATABASE;

/**
 * Created by user on 19.06.2016.
 */
public class ConnectorDB {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        String url = DATABASE.getString("url");
        String driver =  DATABASE.getString("driver");
        String user =  DATABASE.getString("user");
        String password =  DATABASE.getString("password");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url,user, password);
        return connection;
    }
}
