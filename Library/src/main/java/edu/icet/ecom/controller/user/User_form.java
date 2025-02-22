package edu.icet.ecom.controller.user;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.model.User;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.custom.User_service;
import edu.icet.ecom.util.AppModule;
import edu.icet.ecom.util.Service_type;
import edu.icet.ecom.util.SetAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
                Tmembershipdat.getText(),
                0.0
        ))){
            SetAlert.getInstance().setAlert("User Is Added To Library ! ");
            return;
        }SetAlert.getInstance().setAlert("Can't Add user Check Date and User ID ");
    }


    public void btnbackOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void imgbackOnAction(MouseEvent mouseEvent)  {

    }


}
