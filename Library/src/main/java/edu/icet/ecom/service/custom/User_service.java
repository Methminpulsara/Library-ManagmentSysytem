package edu.icet.ecom.service.custom;

import edu.icet.ecom.entity.User_entity;
import edu.icet.ecom.model.User;
import edu.icet.ecom.service.SuperService;
import javafx.collections.ObservableList;

import java.util.List;

public interface User_service extends SuperService {


    List<User> getAll ();
    ObservableList<String> get_UserIDS();
    boolean saveUser(User user);
    boolean deleteUser(String user_ID);
    boolean updateUesr(User user);
    User_entity serchUser(String user_ID );
    boolean updateFine(String userID,Double fine);
    boolean updatePayment(String userID, Double fine);
    ObservableList<String> getUsersHaveToPay();
}
