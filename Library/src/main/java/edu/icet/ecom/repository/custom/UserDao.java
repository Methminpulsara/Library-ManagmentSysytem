package edu.icet.ecom.repository.custom;

import edu.icet.ecom.entity.User_entity;
import edu.icet.ecom.model.User;
import edu.icet.ecom.repository.CrudDao;

public interface UserDao extends CrudDao <User_entity,String > {
}
