package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.db_connection.Db_Connection;
import edu.icet.ecom.model.Admin;
import edu.icet.ecom.util.CrudUtil;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_formController {

    private  static Login_formController instance;
    private Login_formController(){}
    public static Login_formController getInstance(){
        return instance == null ? instance = new Login_formController():instance;
    }

    public boolean login(Admin admin) throws SQLException, IOException {
        PreparedStatement stm = Db_Connection.getInstance().getConnection().prepareStatement("Select * from admin where email = ? ");
        stm.setObject(1,admin.getEmail());
        ResultSet res = stm.executeQuery();
        if (res.next()){
            if (res.getString(2).equals(admin.getPassword())){
               return true;
            }
        }
        return false;
    }

    public boolean save (Admin admin) throws SQLException {

        if (CrudUtil.execute("Insert INTO admin Values (?,?)",admin.getEmail(),admin.getPassword())){
            return  true;
        }
        return false;
    }


}
