package edu.icet.ecom.controller.book;

import com.google.inject.Inject;
import com.google.inject.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.controller.home.HomeForm;
import edu.icet.ecom.model.Book;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.SuperService;
import edu.icet.ecom.service.custom.Book_service;
import edu.icet.ecom.service.custom.impl.BookService_impl;
import edu.icet.ecom.util.Service_type;
import edu.icet.ecom.util.SetAlert;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.rmi.AlreadyBoundException;

public class Book_form {

    public AnchorPane bookancherpane;
    public AnchorPane mainancherpane;
    public JFXTextField Tgenre;
    @FXML
    private JFXTextField Tauthor;

    @FXML
    private JFXTextField Tbookid;

    @FXML
    private JFXTextField Tisbn;

    @FXML
    private JFXTextField Tqty;

    @FXML
    private JFXTextField Ttitel;

    @FXML
    private JFXButton btnbackOnAction;

    @Inject
    private Book_service service ;
    @FXML


    void btnAddOnAction(ActionEvent event) {

        if (service.saveBook(new Book(Tbookid.getText(),
                Tisbn.getText(),
                Ttitel.getText(),
                Tauthor.getText(),
                "Available"))){
            SetAlert.getInstance().setAlert("Book is Added ! ");
            return;
        }SetAlert.getInstance().setAlert("Can't Add this Book ! please try againg ! ");



    }
    @FXML
    void btnbookmanegmnetOnAction(ActionEvent event) throws IOException {

    }
    public void btnbackOnAction(ActionEvent actionEvent) throws IOException {
//        Platform.runLater(() -> {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/home/Home_form.fxml"));
//                Scene scene = new Scene(loader.load());
//
//                Stage stage = (Stage) bookancherpane.getScene().getWindow();
//                stage.close(); // Close current stage
//
//                Stage newStage = new Stage();
//                newStage.setScene(scene);
//                newStage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

        Platform.runLater(() ->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/home/Home_form .fxml"));
                Scene scene = new Scene(loader.load());

                Stage stage = (Stage) bookancherpane.getScene().getWidth();


            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }





}
