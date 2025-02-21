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
        return List.of();
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
            System.out.println(e.getMessage()); }
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
    public boolean udapetStatus(int returnID, String status) {

        try {
            PreparedStatement stm = Db_Connection.getInstance().getConnection().prepareStatement("Update return_books set Status = ? where ReturnID = ?");
            stm.setObject(1, status);
            stm.setObject(2,returnID);
            return stm.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}

