package edu.icet.ecom.controller.user.user_manegment;

import com.google.inject.Inject;
import edu.icet.ecom.entity.User_entity;
import edu.icet.ecom.model.User;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.custom.User_service;
import edu.icet.ecom.service.custom.impl.UserService_impl;
import edu.icet.ecom.util.Service_type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class user_manegmentForm implements Initializable {

    public AnchorPane mainancherpane;
    @FXML
    private TextField Tcontactnumber;

    @FXML
    private TextField Tmembershipdate;

    @FXML
    private TextField Tuserid;

    @FXML
    private TextField Tusername;

    @FXML
    private TableColumn<?, ?> colcontactnumber;

    @FXML
    private TableColumn<?, ?> colmembershipdate;

    @FXML
    private TableColumn<?, ?> coluserid;

    @FXML
    private TableColumn<?, ?> colusername;

    @FXML
    private TableView<User> tbluser;

    @Inject
    private User_service service;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(service.deleteUser(Tuserid.getText())){
            new Alert(Alert.AlertType.INFORMATION,"User Deleted ! ").show();
            return;
        }
        new Alert(Alert.AlertType.INFORMATION,"User Can't delete  ! ").show();

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        User_entity user = service.serchUser(Tuserid.getText());
        if (user!=null){
            Tusername.setText(user.getName());
            Tcontactnumber.setText(user.getContactinformation());
            Tmembershipdate.setText(user.getMembershipdate());
            return;
        }
        new Alert(Alert.AlertType.INFORMATION,"User can't find ").show();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
       if (service.updateUesr(new User(Tuserid.getText(),Tusername.getText(),Tcontactnumber.getText(),Tmembershipdate.getText(),0.0))){
           new Alert(Alert.AlertType.INFORMATION,"User is Updated ! ").show();
           return;
       } new Alert(Alert.AlertType.INFORMATION,"User can't Update ! ").show();


    }


    ObservableList<User>userObservableList = FXCollections.observableArrayList();
    private void loadTable (){

        List <User> userList = service.getAll();
        userList.forEach(user -> {
            userObservableList.add(user);
        });
        tbluser.setItems(userObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coluserid.setCellValueFactory(new PropertyValueFactory<>("userid"));
        colusername.setCellValueFactory(new PropertyValueFactory<>("name"));
        colcontactnumber.setCellValueFactory(new PropertyValueFactory<>("contactinformation"));
        colmembershipdate.setCellValueFactory(new PropertyValueFactory<>("membershipdate"));
        loadTable();
    }






    public void imgbackOnAction(MouseEvent mouseEvent) {
    }

    @FXML
    void btnbackOnAction(ActionEvent event) throws IOException {
    }
}
