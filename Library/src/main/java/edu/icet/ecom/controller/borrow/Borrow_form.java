package edu.icet.ecom.controller.borrow;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.model.Borrow;
import edu.icet.ecom.model.Cart;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.custom.Book_service;
import edu.icet.ecom.service.custom.Borrow_service;
import edu.icet.ecom.service.custom.User_service;
import edu.icet.ecom.util.Service_type;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class Borrow_form  implements Initializable {

    public Label lbldate;
    public DatePicker datereturn;
    public TableView tblBorrwDetails;
    public TableColumn colOrderID;
    public TableColumn colBookID;
    public TableColumn colBorrowdate;
    public TableColumn colreturndate;
    public TableColumn colFine;
    public JFXTextField Torderid;
    public TextField Tfine;
    public JFXTextField returndate;
    public DatePicker datepiker;
    @FXML
    private JFXComboBox book_cmb;

    @FXML
    private JFXComboBox user_cmb;



    @Inject
    private User_service userService ;
    @Inject
    private Book_service bookService ;
    @Inject
    private Borrow_service borrowService ;




    private void setUser_IDS(){
        ObservableList<String> userIDS = userService.get_UserIDS();
        user_cmb.setItems(userIDS);
    }

    private void setBook_IDS(){
        ObservableList<String> bookIds = bookService.getBook_ids();
        book_cmb.setItems(bookIds);
    }

    private void setDate(){
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
    }

    private void setORder_ID (){
        String lastBarrowID = borrowService.getLastBarrowID();

        int lastDigit=0;

        if (lastBarrowID!=null){
             String   substring = lastBarrowID.substring(1);
            lastDigit = Integer.parseInt(substring);
            Torderid.setText(String.format("O%03d",lastDigit+1));
        }else {
           Torderid.setText("0001");
        }
    }


    private void loadTabele(){ colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        colBorrowdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colreturndate.setCellValueFactory(new PropertyValueFactory<>("returndate"));
      //  colFine.setCellValueFactory(new PropertyValueFactory<>("avelability"));
    }

    private  ObservableList<Cart> carts = FXCollections.observableArrayList();

    public void btnaddOnAction(ActionEvent actionEvent) {

        carts.add(new Cart(
                Torderid.getText(),
                book_cmb.getValue().toString(),
                lbldate.getText(),
                datepiker.getValue().toString())
        );
        tblBorrwDetails.setItems(carts);


        loadTabele();
        setORder_ID();
    }



    public void btnPlaceOnAction(ActionEvent actionEvent) {
        boolean place =true;
        for (Cart cart : carts) {
            place = borrowService.placeOrder(new Borrow(
                    cart.getOrderid(),
                    user_cmb.getValue().toString(),
                    cart.getBookid(),
                    cart.getDate(),
                    cart.getReturndate(),
                    "Place"
            ));
        }


//    boolean isAdded= borrowService.placeOrder(
//           new Borrow(Torderid.getText(),
//                   user_cmb.getValue().toString(),
//                   book_cmb.getValue().toString(),
//                   lbldate.getText(),
//                   datereturn.getValue().toString(),
//                   "Place"));
   if (place){
       new Alert(Alert.AlertType.INFORMATION,"Your Order Is Done ! ").show();
       setORder_ID();
   }else {
       new Alert(Alert.AlertType.INFORMATION,"Your Order can't Place ! ").show();
   }







    }
}
