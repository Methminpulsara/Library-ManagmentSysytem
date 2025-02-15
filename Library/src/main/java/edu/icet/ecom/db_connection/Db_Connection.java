package edu.icet.ecom.db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db_Connection {

    private  static Db_Connection dbConnection;
    private Connection connection;

    private Db_Connection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/library","root","methmin123");

    }

    public  static Db_Connection getInstance() throws SQLException {
        return dbConnection==null ? dbConnection = new Db_Connection(): dbConnection;
    }

    public Connection getConnection (){
        return connection;
    }
}
