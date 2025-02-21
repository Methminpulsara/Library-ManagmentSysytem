package edu.icet.ecom.repository.custom;

import edu.icet.ecom.entity.Book_entity;
import edu.icet.ecom.model.Book;
import edu.icet.ecom.repository.CrudDao;

import java.util.List;

public interface BookDao extends CrudDao <Book_entity, String > {
    boolean updateStock(String orderID, String avelability);
    List <String> getBookids();
}
