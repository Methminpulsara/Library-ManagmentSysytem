package edu.icet.ecom.util;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

import java.util.Set;

public class SetAlert {


    private   static SetAlert instence ;

    public static SetAlert getInstance() {
        return instence == null ? instence = new SetAlert(): instence;
    }


    public  void setAlert (String words){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, words);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
        alert.show();
    }
}
