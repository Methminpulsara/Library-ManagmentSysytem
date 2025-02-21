package edu.icet.ecom.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.ecom.db_connection.Db_Connection;
import edu.icet.ecom.entity.Return_Book_Entity;
import edu.icet.ecom.model.Return_Book;
import edu.icet.ecom.repository.custom.ReturnBook_Dao;
import edu.icet.ecom.service.custom.Book_service;
import edu.icet.ecom.service.custom.Borrow_service;
import edu.icet.ecom.service.custom.ReturnBook_Service;
import edu.icet.ecom.service.custom.User_service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReturnBook_ServiceImpl implements ReturnBook_Service {

    private static ReturnBook_ServiceImpl instrnce;

    public static ReturnBook_ServiceImpl getInstance() {
        return instrnce == null ? instrnce = new ReturnBook_ServiceImpl() : instrnce;
    }

    @Inject
    ReturnBook_Dao returnBookDao;

    @Inject
    Book_service bookService;

    @Inject
    Borrow_service borrowService;

    @Inject
    User_service userService;


    ModelMapper mapper = new ModelMapper();


    @Override
    public int lastReturnID() {
        int id = returnBookDao.getlastRecordID();
        return id;
    }

    @Override
    public boolean save(Return_Book returnBook) throws SQLException {
        Connection connection = Db_Connection.getInstance().getConnection();

        try {

            connection.setAutoCommit(false);
            Return_Book_Entity entity = mapper.map(returnBook, Return_Book_Entity.class);
            boolean isSave = returnBookDao.save(entity);
            if (isSave) {
                boolean isUpdate =bookService.updateStock(returnBook.getBookID(),"Available");
                if (isUpdate){
                   boolean isUpdateStatus = borrowService.updateStatus(returnBook.getRecordID(),"returned");
                    if (isUpdateStatus){
                        boolean isUpdateFine = userService.updateFine(returnBook.getUserID(),returnBook.getFine());
                        if (isUpdateFine){
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public ObservableList<String> getFineDetails() {
        List<String> userIDs = returnBookDao.getFineDetil();
        ObservableList<String> observableArrayList = FXCollections.observableArrayList();
        userIDs.forEach(ids ->{
            observableArrayList.add(ids);
        });
        return observableArrayList;
    }

    @Override
    public String findUser(int retrunID) {
        return returnBookDao.findUser(retrunID);
    }

    @Override
    public boolean updateStatus(int returniD, String status) {
        return returnBookDao.udapetStatus(returniD,status);
    }
}
