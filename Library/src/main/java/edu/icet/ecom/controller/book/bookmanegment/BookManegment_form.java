package edu.icet.ecom.controller.book.bookmanegment;

import com.google.inject.Inject;
import edu.icet.ecom.entity.Book_entity;
import edu.icet.ecom.model.Book;
import edu.icet.ecom.service.custom.Book_service;
import edu.icet.ecom.util.SetAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookManegment_form implements Initializable {

    public AnchorPane mainancherpane;
    public TableColumn colauthor;
    public TableColumn colid;
    public TableColumn colisbn;
    public TableColumn coltitel;
    public TableColumn colavelibility;
    @FXML
    private TextField Tauthor;

    @FXML
    private TextField Tavelability;

    @FXML
    private TextField Tbookid;

    @FXML
    private TextField Tisbn;

    @FXML
    private TextField Ttitel;
    @FXML
    private TableView<Book> tblbook;

    @Inject
    private Book_service service ;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        if (service.deleteBook(Tbookid.getText())){
            SetAlert.getInstance().setAlert("Book is Deleted !");
            loadTable();
            return;
        }SetAlert.getInstance().setAlert("Book can't delete please try again ! ");

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
       Book_entity book = service.searchbook(Tbookid.getText());
       if (book!=null){
           Tisbn.setText(book.getISBN());Ttitel.setText(book.getTitel());Tauthor.setText(book.getAuthor());Tavelability.setText(book.getAvelebility());
        return;
       }
        SetAlert.getInstance().setAlert("Can't Find book!");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
       if (service.updateBook( new Book(
               Tbookid.getText(),
               Tisbn.getText(),
               Ttitel.getText(),
               Tauthor.getText(),
              Tavelability.getText()))){

          SetAlert.getInstance().setAlert("Book IS Updated !");
           return;
       }SetAlert.getInstance().setAlert("Book Can't Update please try again !");

    }
    @FXML
    void btnbackOnAction(ActionEvent event) throws IOException {
//        URL resource = this.getClass().getResource("/home/Home_form .fxml");
//        assert resource!=null;
//        Parent load = FXMLLoader.load(resource);
//        this.mainancherpane.getChildren().clear();
//        this.mainancherpane.getChildren().add(load);

    }

    @FXML
    private void imgbackOnAction(MouseEvent event) {
    }

    private void loadTable (){
        List<Book> booklist = service.getAll_books();
        ObservableList<Book> bookObservableList = FXCollections.observableArrayList();
            booklist.forEach(book -> {
                bookObservableList.add(book);
            });
        tblbook.setItems(bookObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colid.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colisbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        coltitel.setCellValueFactory(new PropertyValueFactory<>("Titel"));
        colauthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colavelibility.setCellValueFactory(new PropertyValueFactory<>("avelebility"));
        loadTable();

    }


}
