package edu.icet.ecom.controller.login;

import edu.icet.ecom.model.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class Login_form {

    public  AnchorPane mainancherpane;
    @FXML
    private PasswordField abminpassword;

    @FXML
    private TextField adminemail;

    @FXML
    void btnloginOnAction(ActionEvent event) {

        try {
                if( Login_formController.getInstance().login(new Admin(adminemail.getText(),abminpassword.getText()))){
                    URL resourse = this.getClass().getResource("/dhashboard/Dashboardform.fxml");
                    assert resourse!=null;
                    Parent load = FXMLLoader.load(resourse);
                  this.mainancherpane.getChildren().clear();
                    this.mainancherpane.getChildren().add(load);return;
                }new Alert(Alert.AlertType.CONFIRMATION,"Check your password and try again ! ").show();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {

        }


    }

    @FXML
    void lblforgetOnAction(MouseEvent event) {

    }

}
