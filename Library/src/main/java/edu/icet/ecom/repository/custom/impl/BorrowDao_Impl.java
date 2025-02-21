package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.db_connection.Db_Connection;
import edu.icet.ecom.entity.BorrowEntity;
import edu.icet.ecom.repository.custom.Borrow_Dao;
import edu.icet.ecom.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowDao_Impl implements Borrow_Dao {

    private static BorrowDao_Impl instence ;
    public static BorrowDao_Impl getInstance(){
        return instence == null? instence = new BorrowDao_Impl(): instence;
    }




    @Override
    public int getLastID() {
        String sql = "Select * From Borrowing_Records ORDER BY  RecordID DESC LIMIT 1";
        try {
            ResultSet res = CrudUtil.execute(sql);
            return res.next() ? res.getInt(1): null ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Integer> getIds() {
        List<Integer> bookList = new ArrayList<>();
        try {
            ResultSet res = CrudUtil.execute("SELECT RecordID FROM  borrowing_records WHERE Status <> 'Returned'");
            while (res.next()) {
                bookList.add(res.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookList;
    }

    @Override
    public boolean updateStatus(int returnedId, String status) {

        try {
            PreparedStatement stm = Db_Connection.getInstance().getConnection().prepareStatement("Update borrowing_records set  Status = ? where RecordID = ?");
            stm.setObject(1,status );
            stm.setObject(2, returnedId);
            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public List<BorrowEntity> getAll() {

       List<BorrowEntity>  borrowEntities = new ArrayList<>();
        try {
            ResultSet res = CrudUtil.execute("select * from borrowing_records");
            while (res.next()){
                borrowEntities.add(new BorrowEntity(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6)
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return borrowEntities;
    }

    @Override
    public boolean save(BorrowEntity entity) {

      String sql ="INSERT INTO Borrowing_Records (UserID, BookID, BorrowDate, ReturnDate, Status) VALUES (?, ?, ?, ?, ?)";
                     try {
            boolean isAdde = CrudUtil.execute(sql,
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
        try {
            ResultSet res = CrudUtil.execute("Select * From  borrowing_records Where RecordID=? ",s);
            if (res.next()){
                return new BorrowEntity(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6)
                        );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

}
