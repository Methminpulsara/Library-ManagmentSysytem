package edu.icet.ecom.controller.fine;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.entity.User_entity;
import edu.icet.ecom.model.FineDetail;
import edu.icet.ecom.service.custom.Fine_service;
import edu.icet.ecom.service.custom.ReturnBook_Service;
import edu.icet.ecom.service.custom.User_service;
import edu.icet.ecom.service.custom.impl.FineService_Impl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class Fineforn_controller implements Initializable {

    public Label lblusername;
    public Label lblreturnID;
    @FXML
    private JFXTextField Tpayment;

    @FXML
    private ComboBox cmb_user;

    @FXML
    private Label lblfine;

    @Inject
    private ReturnBook_Service returnBookService;

    @Inject
    private User_service userService;

    @Inject
    Fine_service fineService;

    private String user;
    @FXML
    void cmbUserOnAction(ActionEvent event) {
        user = returnBookService.findUser(Integer.parseInt(cmb_user.getValue().toString()));
        if (user!= null){
            lblusername.setText(user);
            User_entity entity = userService.serchUser(user);
            lblusername.setText(entity.getName());
            lblfine.setText(entity.getFine().toString());
        }

    }



    private void setUserIds(){
        ObservableList<String> userIDs = returnBookService.getFineDetails();
        cmb_user.setItems(userIDs);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUserIds();
    }

    public void btnaddOnAction(ActionEvent actionEvent) {




        try {
            if (Double.parseDouble(Tpayment.getText()) == Double.parseDouble(lblfine.getText())){
                if(fineService.save(new FineDetail(
                        1,
                        Integer.parseInt(cmb_user.getValue().toString()),
                        user,
                        Double.parseDouble(Tpayment.getText()),
                        "paid "
                ))){
                    new Alert(Alert.AlertType.INFORMATION,"Payment is sussesfull ! ").show();
                    setUserIds();
                    return;
                }
            }
        } catch (SQLException e) {
        }

        new Alert(Alert.AlertType.ERROR,"Can't pay !").show();







    }



    private void clearText (){
//        lblfine.setText("");
//        lblusername.setText("");
//        lblreturnID.setText("");
    }
}
