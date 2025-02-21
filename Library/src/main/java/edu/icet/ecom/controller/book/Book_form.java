package edu.icet.ecom.controller.book;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.model.Book;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.SuperService;
import edu.icet.ecom.service.custom.Book_service;
import edu.icet.ecom.service.custom.impl.BookService_impl;
import edu.icet.ecom.util.Service_type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
            new Alert(Alert.AlertType.INFORMATION,"Book is Added ! ").show();return;
        }new Alert(Alert.AlertType.INFORMATION,"can't Add !").show();



    }
    @FXML
    void btnbookmanegmnetOnAction(ActionEvent event) throws IOException {

        URL resourse = this.getClass().getResource("/books/BookManegment_form.fxml");
        assert resourse!= null;
        Parent load = FXMLLoader.load(resourse);
        this.mainancherpane.getChildren().clear();
        this.mainancherpane.getChildren().add(load);
    }
    public void btnbackOnAction(ActionEvent actionEvent) throws IOException {
//        URL resource = this.getClass().getResource("/home/Home_form .fxml");
//        assert resource!=null;
//        Parent load = FXMLLoader.load(resource);
//        this.mainancherpane.getChildren().clear();
//        this.mainancherpane.getChildren().add(load);
    }

    public void imgbackOnAction(MouseEvent mouseEvent) {

    }
}
