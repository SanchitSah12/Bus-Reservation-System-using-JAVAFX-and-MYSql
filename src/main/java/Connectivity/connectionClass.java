package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionClass {
    public Connection connection;
    public Connection getConnection() throws SQLException {


        connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/dbName","root","?");


        return connection;
    }
}
