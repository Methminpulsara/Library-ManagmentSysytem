package edu.icet.ecom.repository.custom;

import edu.icet.ecom.entity.BorrowEntity;
import edu.icet.ecom.repository.CrudDao;

public interface Borrow_Dao  extends CrudDao<BorrowEntity,String> {

    String getLastID();



}
