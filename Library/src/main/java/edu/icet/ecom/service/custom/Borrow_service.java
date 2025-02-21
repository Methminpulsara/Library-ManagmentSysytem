package edu.icet.ecom.service.custom;

import edu.icet.ecom.entity.BorrowEntity;
import edu.icet.ecom.model.Borrow;
import edu.icet.ecom.service.SuperService;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface Borrow_service extends  SuperService{
    int getLastBarrowID();
    boolean placeOrder(Borrow borrow) throws SQLException;
    List<Borrow> getAll();
    ObservableList<Integer> getRecordsIds();
    BorrowEntity serchRecord(String id );
    boolean updateStatus(int returnedId , String status);
}
