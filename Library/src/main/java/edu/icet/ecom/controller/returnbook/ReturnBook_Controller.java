package edu.icet.ecom.controller.returnbook;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import edu.icet.ecom.entity.BorrowEntity;
import edu.icet.ecom.model.Return_Book;
import edu.icet.ecom.service.custom.Book_service;
import edu.icet.ecom.service.custom.Borrow_service;
import edu.icet.ecom.service.custom.ReturnBook_Service;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class ReturnBook_Controller implements Initializable {

    public Label lblRecordID;
    @FXML
    private TextField Tamount;

    @FXML
    private Label Torderid;

    @FXML
    private DatePicker actualdate;

    @FXML
    private Label lblbookid;

    @FXML
    private Label lblborrowdate;

    @FXML
    private Label lbldate;

    @FXML
    private Label lblreturndate;

    @FXML
    private Label lbluser;

    @FXML
    private JFXComboBox <Integer> record_cmb;

    private int fine;
    private  String status ;

    @Inject
    private Borrow_service borrowService ;

    @Inject
    private ReturnBook_Service returnBookService;


     public void serRecordIDS(){
        ObservableList<Integer> recordsIds = borrowService.getRecordsIds();
        record_cmb.setItems(recordsIds);
     }





    @FXML
    void btnaddOnAction(ActionEvent event) {



    }

    @FXML
    void record_cmb_OnAction(ActionEvent event) {

        BorrowEntity entity = borrowService.serchRecord(record_cmb.getSelectionModel().getSelectedItem().toString());
        if(entity!= null){
            lblbookid.setText(entity.getBookID());
            lbluser.setText(entity.getUserID());
            lblborrowdate.setText(entity.getDate());
            lblreturndate.setText(entity.getReturndate());
        }
    }

    public void serReturnID(){
//        int id = returnBookService.lastReturnID();
//      lblRecordID.setText(String.valueOf(id));
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serRecordIDS();
        serReturnID();
    }


    public void btnRetureenONAction(ActionEvent actionEvent) {

        String returnDateStr = lblreturndate.getText();
        String actualReturnDateStr = actualdate.getValue().toString();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate returnDate = LocalDate.parse(returnDateStr, formatter);
        LocalDate actualReturnDate = LocalDate.parse(actualReturnDateStr, formatter);

        long daysLate = ChronoUnit.DAYS.between(returnDate, actualReturnDate);

        fine = (daysLate > 0) ? (int) daysLate * 10 : 0;
        if (actualReturnDate.isAfter(returnDate)){
            status = "returned late";
        }else{
            status = "returned" ;
        }


        try {
            if (returnBookService.save(new Return_Book(
                    Integer.parseInt(lblRecordID.getText()),
                    record_cmb.getValue(),
                    lbluser.getText(),
                    lblbookid.getText(),
                    lblborrowdate.getText(),
                    lblreturndate.getText(),
                    actualdate.getValue().toString(),
                    (double) fine,
                    status
            ))){
                new Alert(Alert.AlertType.INFORMATION,"Your Book is Returend ! ").show();
                //serRecordIDS();
                return;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        new Alert(Alert.AlertType.ERROR,"Can't Return your book !").show();


    }
}
