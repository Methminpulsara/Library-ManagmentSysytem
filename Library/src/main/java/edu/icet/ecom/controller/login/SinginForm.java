package edu.icet.ecom.controller.login;

import edu.icet.ecom.model.Admin;
import edu.icet.ecom.service.custom.impl.Login_formController;
import edu.icet.ecom.util.SetAlert;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SinginForm {

    public TextField txtEmail;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        try {
            String email = txtEmail.getText();
            String password = txtPassword.getText();
            if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                SetAlert.getInstance().setAlert("Email and Password cannot be empty!");
            } else {
                if (email.endsWith("@gmail.com")) {
                    boolean isSave = Login_formController.getInstance().save(new Admin(email, password));
                    if (isSave) {
                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        currentStage.close();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/login/Login_form .fxml"))));
                        stage.show();
                    }
                }else {
                    SetAlert.getInstance().setAlert("Email must be a Gmail address!");
                }

            }
        }catch (Exception e){

        }

    }

    @FXML
    void btnOtherLoginOnAction(ActionEvent event) {

    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {

    }

}
