package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.db_connection.Db_Connection;
import edu.icet.ecom.entity.Book_entity;
import edu.icet.ecom.model.Book;
import edu.icet.ecom.repository.custom.BookDao;
import edu.icet.ecom.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao_impl implements BookDao {

    private static BookDao_impl instence;
    public static BookDao_impl getInstance() {
        return instence == null ? instence = new BookDao_impl() : instence;
    }


    @Override
    public List<Book_entity> getAll() {

        List<Book_entity> bookList = new ArrayList<>();
        try {
            ResultSet res = CrudUtil.execute("Select * from book");
            while (res.next()) {
                bookList.add(new Book_entity(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    @Override
    public boolean save(Book_entity entity) {
        try {
            String sql = "Insert into book values (?,?,?,?,?)";
            if (CrudUtil.execute(sql, entity.getBookID(), entity.getISBN(), entity.getTitel(), entity.getAuthor(), entity.getAvelebility())) {
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Book_entity entity) {

        String sql = "Update Book set ISBN = ? , Titel = ? ,Author = ?, Availability =? where BookId = ?";
        try {
            if (CrudUtil.execute(sql, entity.getISBN(), entity.getTitel(), entity.getAuthor(), entity.getAvelebility(), entity.getBookID())) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = "Delete from Book where bookid = ?";
        try {
            if (CrudUtil.execute(sql, id)) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Book_entity search(String id) {
        String sql = "Select * from Book where bookid =" + "'" + id + "'";
        try {
            ResultSet res = CrudUtil.execute(sql);
            if (res.next()) {
                return new Book_entity(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean updateStock(String bookid, String avelability) {

        try {
            PreparedStatement stm = Db_Connection.getInstance().getConnection().prepareStatement("Update Book set Availability = ? where BookID = ?");
            stm.setObject(1,avelability );
            stm.setObject(2, bookid);
            return stm.executeUpdate()>0;
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public List<String> getBookids() {
        List<String> bookList = new ArrayList<>();
        try {
            ResultSet res = CrudUtil.execute("SELECT BookID FROM Book WHERE Availability <> 'Place'");
            while (res.next()) {
                bookList.add(res.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookList;
    }
}
