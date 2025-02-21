package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.FineDetail_Entity;
import edu.icet.ecom.repository.custom.FineDao;
import edu.icet.ecom.util.CrudUtil;

import java.sql.SQLException;

public class FineDao_Impl implements FineDao {

    private static FineDao_Impl instence;

    public  static FineDao_Impl getInstance(){
        return  instence == null ? instence = new FineDao_Impl(): instence;
    }


    @Override
    public boolean save(FineDetail_Entity entity) {

        String sql = "INSERT INTO Fines (ReturnID, UserID, FineAmount, Status) VALUES (?, ?, ?, ?)";
        try {
            if (CrudUtil.execute(sql,entity.getReturnID(),entity.getUserID(),entity.getFine(),entity.getStatus())){
                return true;            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
