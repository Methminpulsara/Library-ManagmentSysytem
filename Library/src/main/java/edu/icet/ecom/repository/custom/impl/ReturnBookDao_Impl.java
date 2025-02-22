package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.db_connection.Db_Connection;
import edu.icet.ecom.entity.Return_Book_Entity;
import edu.icet.ecom.repository.custom.ReturnBook_Dao;
import edu.icet.ecom.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReturnBookDao_Impl implements ReturnBook_Dao {

    private static ReturnBookDao_Impl instence;

    public static ReturnBookDao_Impl getInstance(){
        return  instence == null ? instence = new ReturnBookDao_Impl() : instence;
    }




    @Override
    public List<Return_Book_Entity> getAll() {
        List <Return_Book_Entity> returnBookEntities = new ArrayList<>();
        String sql = "select * from return_books";
        try {
            ResultSet res = CrudUtil.execute(sql);
            while(res.next()){
                returnBookEntities.add(new Return_Book_Entity(
                        res.getInt(1), res.getInt(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getDouble(8), res.getString(9)
                ));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return returnBookEntities;
    }

    @Override
    public boolean save(Return_Book_Entity entity) {

        String sql = "INSERT INTO Return_Books (RecordID, UserID, BookID, BorrowDate, ReturnDate, ActualReturnDate, FineAmount, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {

            if (CrudUtil.execute(sql,
                    entity.getRecordID(),
                    entity.getUserID(),
                    entity.getBookID(),
                    entity.getBorrowDate(),
                    entity.getReturnDate(),
                    entity.getActualDate(),
                    entity.getFine(),
                    entity.getStatus())){
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }








    @Override
    public boolean update(Return_Book_Entity entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Return_Book_Entity search(String s) {
        return null;
    }

    @Override
    public int getlastRecordID() {
        String sql = "Select * From return_books ORDER BY  ReturnID DESC LIMIT 1";
        try {
            ResultSet res = CrudUtil.execute(sql);

                return res.next() ? res.getInt(1): null ;

        } catch (SQLException e) {
        }
        return 1;
    }

    @Override
    public List<String> getFineDetil() {

        List <String> findetails= new ArrayList<>();

        String sql = "SELECT ReturnID  FROM return_books WHERE Status <> 'returned'";
        try {
            ResultSet res =CrudUtil.execute(sql);
            while (res.next()){
               findetails.add(res.getString("ReturnID"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return findetails;
    }

    @Override
    public String findUser(int returnID) {

        String sql = "select UserId from return_books where ReturnID = ?";
        try {
            ResultSet res =CrudUtil.execute(sql,returnID);
            if(res.next()){
               return res.getString("UserID");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public boolean udapetStatus(String UserId, String status) {

        try {
            PreparedStatement stm = Db_Connection.getInstance().getConnection().prepareStatement("Update return_books set Status = ? where UserID = ?");
            stm.setObject(1, status);
            stm.setObject(2, UserId);
            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<String> getUserid() {

        List <String> userIds = new ArrayList<>();
        try {
            ResultSet res = CrudUtil.execute("SELECT UserId  AS total FROM return_books GROUP BY UserId HAVING SUM(FineAmount) > 0");
            while(res.next()){
                userIds.add(res.getString("total"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userIds;
    }
}

