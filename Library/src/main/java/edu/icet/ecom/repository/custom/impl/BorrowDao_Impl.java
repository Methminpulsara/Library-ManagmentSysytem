package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.BorrowEntity;
import edu.icet.ecom.repository.custom.BookDao;
import edu.icet.ecom.repository.custom.Borrow_Dao;
import edu.icet.ecom.service.custom.impl.BookService_impl;
import edu.icet.ecom.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BorrowDao_Impl implements Borrow_Dao {

    private static BorrowDao_Impl instence ;
    public static BorrowDao_Impl getInstance(){
        return instence == null? instence = new BorrowDao_Impl(): instence;
    }




    @Override
    public String getLastID() {
        String sql = "Select * From Borrowing_Records ORDER BY  RecordID DESC LIMIT 1";
        try {
            ResultSet res = CrudUtil.execute(sql);
            return res.next() ? res.getString(1): null ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public List<BorrowEntity> getAll() {
        return List.of();
    }

    @Override
    public boolean save(BorrowEntity entity) {

        System.out.println(entity.getAvelability());
      String sql ="Insert Into borrowing_records values (?,?,?,?,?,?)";
        try {
            boolean isAdde = CrudUtil.execute(sql,
                    entity.getOrderID(),
                    entity.getUserID(),
                    entity.getBookID(),
                    entity.getDate(),
                    entity.getReturndate(),
                    entity.getAvelability());
            if (isAdde){
                return true;
            }
        } catch (SQLException ignored) {}
        return false;
    }

    @Override
    public boolean update(BorrowEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public BorrowEntity search(String s) {
        return null;
    }
}
