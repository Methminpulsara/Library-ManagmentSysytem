package edu.icet.ecom.service.custom;

import edu.icet.ecom.model.Return_Book;
import edu.icet.ecom.service.SuperService;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ReturnBook_Service extends SuperService {

    public int lastReturnID();
    boolean save(Return_Book returnBook) throws SQLException;
    ObservableList<String> getFineDetails();
    String findUser(int retrunID);
    boolean updateStatus(int returniD , String status);

}
