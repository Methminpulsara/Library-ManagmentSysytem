package edu.icet.ecom.controller.fine;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import edu.icet.ecom.entity.User_entity;
import edu.icet.ecom.model.FineDetail;
import edu.icet.ecom.service.custom.Fine_service;
import edu.icet.ecom.service.custom.ReturnBook_Service;
import edu.icet.ecom.service.custom.User_service;
import edu.icet.ecom.util.SetAlert;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class Fine_Form_controller implements Initializable {

    public Label lblusername;
    public TableColumn colstatus;
    public TableColumn colamount;
    public TableColumn colfineID;
    public TableColumn coluserid;
    public TableView tblfine;
    @FXML
    private TextField Tpayment;
    @FXML
    private Label lbldate;

    @FXML
    private Label lblfine;

    @FXML
    private Label lbluserID;

    @FXML
    private JFXComboBox<String> returnid;


    @Inject
    ReturnBook_Service returnBookService;

    @Inject
    User_service userService;
    @Inject
    Fine_service fineService;


    @FXML
    void btnpaymentOnActi0on(ActionEvent event) {

        try {

            if (Double.parseDouble(Tpayment.getText()) == Double.parseDouble(lblfine.getText())) {
                if (fineService.save(new FineDetail(
                        1,
                        lbluserID.getText(),
                        Double.parseDouble(Tpayment.getText()),
                        "paid "
                ))) {
                    SetAlert.getInstance().setAlert("Payment is sussesfull ! ");
                    returnid.getItems().clear();
                    loadTable();
                    setReturnid();
                    return;
                }
            }
        } catch (SQLException e) {
        }
        SetAlert.getInstance().setAlert("You need to pay the full fine.");
    }

    @FXML
    void cmb_returnOnAction(ActionEvent event) {

        User_entity entity = userService.serchUser(returnid.getValue().toString());
        if (entity != null) {
            lbluserID.setText(entity.getUserid());
            lblusername.setText(entity.getName());
            lblfine.setText(String.valueOf(entity.getFine()));
        }
    }


    private void setReturnid() {

        List<String> userIds = returnBookService.getUserIDs();
        returnid.getItems().clear();
        returnid.getItems().addAll(userIds);

    }

    private void loadTable() {
        colfineID.setCellValueFactory(new PropertyValueFactory<>("fineID"));
        colamount.setCellValueFactory(new PropertyValueFactory<>("fine"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        coluserid.setCellValueFactory(new PropertyValueFactory<>("UserID"));

        ObservableList<FineDetail> all = fineService.getAll();
        tblfine.setItems(all);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setReturnid();
        loadTable();
        lbldate.setText(LocalDate.now().toString());
    }
}
