package edu.icet.ecom.util;

import edu.icet.ecom.db_connection.Db_Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {

    public static  <T>T execute (String sql , Object... args) throws SQLException {
        PreparedStatement stm = Db_Connection.getInstance().getConnection().prepareStatement(sql);
        for (int i=0 ; i< args.length ; i++){
            stm.setObject((i+1), args[i]);
        }
        if (sql.startsWith("SELECT")||sql.startsWith("select") || sql.startsWith("Select")){
            return (T) stm.executeQuery();
        }
           return (T) (Boolean) (stm.executeUpdate()>0);
    }
}
