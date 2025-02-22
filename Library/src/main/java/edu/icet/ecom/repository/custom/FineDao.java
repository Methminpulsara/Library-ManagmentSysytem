package edu.icet.ecom.repository.custom;

import edu.icet.ecom.entity.FineDetail_Entity;

import java.util.List;

public interface FineDao {

    boolean save (FineDetail_Entity entity);
    List <FineDetail_Entity> getAll ();
}
