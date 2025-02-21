package edu.icet.ecom.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.ecom.entity.User_entity;
import edu.icet.ecom.model.User;
import edu.icet.ecom.repository.custom.UserDao;
import edu.icet.ecom.service.custom.User_service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UserService_impl implements User_service {



    @Inject
    private UserDao dao ;

    ModelMapper mapper = new ModelMapper();

    private static UserService_impl instence;

    public static UserService_impl getInstance(){return instence==null? instence= new UserService_impl():instence;}

    @Override
    public List<User> getAll() {
        List<User_entity> alluser = dao.getAll();
        ArrayList<User>  userArrayList = new ArrayList<>();
        alluser.forEach(userEntity -> {
            userArrayList.add(mapper.map(userEntity, User.class));
        });
        return userArrayList;
    }

    @Override
    public ObservableList<String> get_UserIDS() {
        List<User> all = getAll();
        ObservableList<String> observableArrayList = FXCollections.observableArrayList();
        all.forEach(user->{
            observableArrayList.add(user.getUserid());
        });
        return observableArrayList;
    }

    @Override
    public boolean saveUser(User user) {

        User_entity entity = mapper.map(user, User_entity.class);
        return dao.save(entity);
    }

    @Override
    public boolean deleteUser(String user_ID) {
        return dao.delete(user_ID);
    }

    @Override
    public boolean updateUesr(User user) {
        User_entity entity = mapper.map(user, User_entity.class);
        return dao.update(entity) ;

    }

    @Override
    public User_entity serchUser(String user_ID) {
        return dao.search(user_ID);

    }

    @Override
    public boolean updateFine(String userID, Double fine) {
        return dao.updateFine(userID,fine);
    }

    @Override
    public boolean updatePayment(String userID, Double fine) {
        return dao.updatePayment(userID,fine);
    }
}
