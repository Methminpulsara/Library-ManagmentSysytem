package edu.icet.ecom.controller.login;

import edu.icet.ecom.model.Admin;
import edu.icet.ecom.service.custom.impl.Login_formController;
import edu.icet.ecom.util.SetAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

            String email = adminemail.getText();
            String password = abminpassword.getText();
            if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                SetAlert.getInstance().setAlert("Email and Password cannot be empty!");
            }else{
                if (email.endsWith("@gmail.com")) {
                    if (Login_formController.getInstance().login(new Admin(email, password))) {
                        URL resourse = this.getClass().getResource("/home/Home_form .fxml");
                        assert resourse != null;
                        Parent load = FXMLLoader.load(resourse);
                        this.mainancherpane.getChildren().clear();
                        this.mainancherpane.getChildren().add(load);
                        return;
                    }
                } SetAlert.getInstance().setAlert("Email must be a Gmail address!");
                }
        } catch (Exception e) {
        }
    }

    @FXML
    void lblforgetOnAction(MouseEvent event) {

    }

    public void singinonaction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/login/SingIn_form.fxml"))));
        stage.show();
    }
}
