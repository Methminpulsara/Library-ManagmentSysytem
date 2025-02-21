package edu.icet.ecom.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.ecom.db_connection.Db_Connection;
import edu.icet.ecom.entity.BorrowEntity;
import edu.icet.ecom.model.Borrow;
import edu.icet.ecom.repository.custom.Borrow_Dao;
import edu.icet.ecom.service.custom.Book_service;
import edu.icet.ecom.service.custom.Borrow_service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BorrowService_Impl implements Borrow_service {

    @Inject
    private Borrow_Dao dao ;

    @Inject
    Book_service bookService;


    private static BorrowService_Impl instence;

    public static BorrowService_Impl getInstance(){
        return instence == null? instence = new BorrowService_Impl(): instence;
    }
    ModelMapper mapper = new ModelMapper();

    @Override
    public int  getLastBarrowID() {
        int lastID = dao.getLastID();
        return lastID;

    }

    @Override
    public boolean placeOrder(Borrow borrow) throws SQLException {


        Connection connection = Db_Connection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            BorrowEntity entity = mapper.map(borrow,BorrowEntity.class);
            boolean isSave = dao.save(entity);
            if(isSave) {
                boolean isUpdate = bookService.updateStock(borrow.getBookID(),borrow.getAvelability());
                if (isUpdate){
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public List<Borrow> getAll() {
        List<BorrowEntity> all = dao.getAll();
        ArrayList<Borrow> borrowArrayList = new ArrayList<>();
        all.forEach(borrow -> {
            borrowArrayList.add(mapper.map(borrow,Borrow.class));
        });
        return borrowArrayList;
    }

    @Override
    public ObservableList<Integer> getRecordsIds() {
        List<Integer> all = dao.getIds();
        ObservableList<Integer> recordIds = FXCollections.observableArrayList();
        all.forEach(borrow -> {
            recordIds.add(borrow);
        });
        return recordIds;
    }

    @Override
    public BorrowEntity serchRecord(String id) {
        return dao.search(id);
    }

    @Override
    public boolean updateStatus(int returnedId, String status) {
        System.out.println(returnedId);
        System.out.println(status);
        return dao.updateStatus(returnedId,status);
    }
}
