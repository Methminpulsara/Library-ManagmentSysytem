package edu.icet.ecom.repository;

import java.util.List;

public interface CrudDao <T,ID> extends SuperDao {

    List <T> getAll();
    boolean save (T entity);
    boolean update(T entity);
    boolean delete (ID id);
    T search (ID id);

}
