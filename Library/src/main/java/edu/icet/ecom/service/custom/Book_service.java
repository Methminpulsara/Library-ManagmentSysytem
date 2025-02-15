package edu.icet.ecom.service.custom;

import edu.icet.ecom.entity.Book_entity;
import edu.icet.ecom.model.Book;
import edu.icet.ecom.service.SuperService;
import javafx.collections.ObservableList;

import java.util.List;

 public interface Book_service extends SuperService {
    List<Book> getAll_books();
    ObservableList<String> getBook_ids();
    boolean saveBook (Book book);
    boolean updateBook (Book book);
    boolean deleteBook (String bookid);
    Book_entity searchbook (String bookid);


}
