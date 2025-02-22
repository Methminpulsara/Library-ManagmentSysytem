package edu.icet.ecom.controller.borrow;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.model.Borrow;
import edu.icet.ecom.model.Cart;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.custom.Book_service;
import edu.icet.ecom.service.custom.Borrow_service;
import edu.icet.ecom.service.custom.User_service;
import edu.icet.ecom.util.Service_type;
import edu.icet.ecom.util.SetAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.checkerframework.checker.units.qual.C;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class Borrow_form implements Initializable {

    public Label lbldate;
    public DatePicker datereturn;
    public TableView tblBorrwDetails;
    public TableColumn colOrderID;
    public TableColumn colBookID;
    public TableColumn colBorrowdate;
    public TableColumn colreturndate;
    public TableColumn colFine;
    public TextField Tfine;
    public JFXTextField returndate;
    public DatePicker datepiker;
    public Label Torderid;
    public JFXButton btnplace;
    @FXML
    private JFXComboBox book_cmb;

    @FXML
    private JFXComboBox user_cmb;


    @Inject
    private User_service userService;
    @Inject
    private Book_service bookService;
    @Inject
    private Borrow_service borrowService;


    private void setUser_IDS() {
        ObservableList<String> userIDS = userService.get_UserIDS();
        user_cmb.setItems(userIDS);
    }

    private void setBook_IDS() {
        ObservableList<String> bookIds = bookService.getBook_ids();
        book_cmb.setItems(bookIds);
    }

    private void setDate() {
        lbldate.setText(LocalDate.now().toString());
    }


    @FXML
    void book_cmb_OnAction(ActionEvent event) {
    }

    @FXML
    void user_cmb_OnAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadTabele();
        setUser_IDS();
        setBook_IDS();
        setDate();
        setORder_ID();
        btnplace.setDisable(false);
    }

    private void setORder_ID() {
//        int lastBarrowID = borrowService.getLastBarrowID();
//        String id = String.valueOf(lastBarrowID);
//        if (id!= null){
//            Torderid.setText(id);
//        }else {
//            Torderid.setText("1");
//        }
    }


    private void loadTabele() {
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        colBorrowdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colreturndate.setCellValueFactory(new PropertyValueFactory<>("returndate"));
    }

    private ObservableList<Cart> carts = FXCollections.observableArrayList();

    public void btnaddOnAction(ActionEvent actionEvent) {
        carts.add(new Cart(
                user_cmb.getValue().toString(),
                book_cmb.getValue().toString(),
                lbldate.getText(),
                datepiker.getValue().toString())
        );
        tblBorrwDetails.setItems(carts);
        loadTabele();
        setORder_ID();
        setBook_IDS();
    }


    public void btnPlaceOnAction(ActionEvent actionEvent) {
        boolean place = true;
        for (Cart cart : carts) {
            try {
                place = borrowService.placeOrder(new Borrow(
                        1,
                        cart.getUserID(),
                        cart.getBookid(),
                        cart.getDate(),
                        cart.getReturndate(),
                        "Place"
                ));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (place) {
            SetAlert.getInstance().setAlert("\"Your Order Is Done ! \"");
            setORder_ID();
            setBook_IDS();
            removetabel();

        } else {
            SetAlert.getInstance().setAlert("Your Order can't Place !");
        }
    }


    void removetabel() {
        carts.clear();
        tblBorrwDetails.refresh();
    }


}
