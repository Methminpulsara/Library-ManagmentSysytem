package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.User_entity;
import edu.icet.ecom.model.User;
import edu.icet.ecom.repository.custom.UserDao;
import edu.icet.ecom.util.CrudUtil;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao_impl implements UserDao {
    private static UserDao_impl instence;
    public static UserDao_impl getInstance() {
        return instence == null ? instence = new UserDao_impl() : instence;
    }


    @Override
    public List<User_entity> getAll() {
        List<User_entity> userList = new ArrayList<>();
        try {
            ResultSet res = CrudUtil.execute("Select * from User ");
            while (res.next()) {
                userList.add(new User_entity(res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4)));
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
        return userList;
    }

    @Override
    public boolean save(User_entity entity) {
        String sql = "Insert into user values (?,?,?,?)";
        try {
            boolean isAdded = CrudUtil.execute(sql, entity.getUserid(), entity.getName(), entity.getContactinformation(), entity.getMembershipdate());
            if (isAdded) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(User_entity entity) {
        String sql = "Update User set Name = ? ,ContactInformation=? , MembershipDate =? where UserID =? ";
        try {
            if (CrudUtil.execute(sql, entity.getName(), entity.getContactinformation(), entity.getMembershipdate(), entity.getUserid())) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {

        try {
            if (CrudUtil.execute("Delete from User where UserID = ?", id)) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public User_entity search(String id) {

        try {
            ResultSet res = CrudUtil.execute("Select * from User Where Userid =  ? ",id);
            if (res.next()) {
                return new User_entity(res.getString(1), res.getString(2), res.getString(3), res.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
