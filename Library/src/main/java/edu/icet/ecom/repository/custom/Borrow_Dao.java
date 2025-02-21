package edu.icet.ecom.repository.custom;

import edu.icet.ecom.entity.BorrowEntity;
import edu.icet.ecom.repository.CrudDao;

import java.util.List;

public interface Borrow_Dao  extends CrudDao<BorrowEntity,String> {

    int getLastID();
    List<Integer>getIds();
    boolean updateStatus(int returnedId , String status);



}
