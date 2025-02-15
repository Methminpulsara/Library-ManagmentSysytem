package edu.icet.ecom.controller.user;


import com.google.inject.Inject;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.model.User;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.custom.User_service;
import edu.icet.ecom.util.Service_type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;

public class User_form {

    public AnchorPane mainancherpane;
    @FXML
    private JFXTextField Tcontact;

    @FXML
    private JFXTextField Tmembershipdat;

    @FXML
    private JFXTextField Tuserid;

    @FXML
    private JFXTextField Tusername;



    @Inject
    private User_service service ;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if (service.saveUser(new User(
                Tuserid.getText(),
                Tusername.getText(),
                Tcontact.getText(),
                Tmembershipdat.getText()))){
            new Alert(Alert.AlertType.INFORMATION,"Added ! ").show();
        }
    }















    @FXML
    void btnusermangmentOnAction(ActionEvent event) throws IOException {

//        URL resource = this.getClass().getResource("/user/User_manegmentForm.fxml");
//        assert resource!=null;
//        Parent load = FXMLLoader.load(resource);
//        this.mainancherpane.getChildren().clear();
//        this.mainancherpane.getChildren().add(load);

    }

    public void btnbackOnAction(ActionEvent actionEvent) throws IOException {
//        URL resource = this.getClass().getResource("/home/Home_form .fxml");
//        assert resource!=null;
//        Parent load = FXMLLoader.load(resource);
//        this.mainancherpane.getChildren().clear();
//        this.mainancherpane.getChildren().add(load);
    }

    public void imgbackOnAction(MouseEvent mouseEvent)  {

    }


}
