package edu.icet.ecom.repository.custom;

import edu.icet.ecom.entity.Book_entity;
import edu.icet.ecom.model.Book;
import edu.icet.ecom.repository.CrudDao;

public interface BookDao extends CrudDao <Book_entity, String > {
}
