package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.FineDetail_Entity;
import edu.icet.ecom.repository.custom.FineDao;
import edu.icet.ecom.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FineDao_Impl implements FineDao {

    private static FineDao_Impl instence;

    public  static FineDao_Impl getInstance(){
        return  instence == null ? instence = new FineDao_Impl(): instence;
    }


    @Override
    public boolean save(FineDetail_Entity entity) {

        String sql = "INSERT INTO Fines ( UserID, FineAmount, Status) VALUES ( ?, ?, ?)";
        try {
            if (CrudUtil.execute(sql,entity.getUserID(),entity.getFine(),entity.getStatus())){
                return true;            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<FineDetail_Entity> getAll() {
        List <FineDetail_Entity>fineDetailEntities = new ArrayList<>();
        try {
            ResultSet res = CrudUtil.execute("Select * from fines");
            while (res.next()){
                fineDetailEntities.add(new FineDetail_Entity(
                        res.getInt(1),res.getString(2),res.getDouble(3),res.getString(4)
                ));
            }
        } catch (SQLException e) {
        }
        return fineDetailEntities;
    }
}
