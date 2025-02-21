package edu.icet.ecom.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.ecom.entity.Book_entity;
import edu.icet.ecom.model.Book;
import edu.icet.ecom.model.Borrow;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.custom.BookDao;
import edu.icet.ecom.service.custom.Book_service;
import edu.icet.ecom.util.Dao_type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class BookService_impl implements Book_service {


     private static BookService_impl instence;
    ModelMapper mapper = new ModelMapper();

    @Inject
    private BookDao dao ;

    public static BookService_impl getInstance(){
        return instence ==null ? instence = new BookService_impl():instence;
    }

    @Override
    public List<Book> getAll_books() {
        List<Book_entity> allbooks = dao.getAll();
        ArrayList <Book> bookArrayList= new ArrayList<>();
        allbooks.forEach(bookEntity -> {
            bookArrayList.add(mapper.map(bookEntity,Book.class));
        });
        return bookArrayList;
    }

    @Override
    public ObservableList<String> getBook_ids() {
        List<String> bookids = dao.getBookids();
        ObservableList<String> observableArrayList = FXCollections.observableArrayList();
        bookids.forEach(book -> {
            observableArrayList.add(book);
        });
        return observableArrayList;
    }

    @Override
    public boolean saveBook(Book book) {
        Book_entity entity = mapper.map(book,Book_entity.class);
        return dao.save(entity);

    }

    @Override
    public boolean updateBook(Book book) {
        Book_entity entity = mapper.map(book, Book_entity.class);
        return dao.update(entity);
    }

    @Override
    public boolean deleteBook(String bookid) {
        return dao.delete(bookid);
    }

    @Override
    public Book_entity searchbook(String bookid) {
        return dao.search(bookid);
    }

    @Override
    public boolean updateStock(String orderID, String avelability) {
        return dao.updateStock(orderID,avelability);
    }

}
