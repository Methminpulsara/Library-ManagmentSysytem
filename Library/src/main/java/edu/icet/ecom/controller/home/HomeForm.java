package edu.icet.ecom.controller.home;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jfoenix.controls.JFXButton;
import edu.icet.ecom.util.AppModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class HomeForm {

    public AnchorPane setancherpane;
    @FXML
    private JFXButton btn;

    @FXML
    private ImageView btnadduserONACTION;

    @FXML
    void btnbookONACTION(MouseEvent event) throws IOException {


        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader =new FXMLLoader(this.getClass().getResource("/books/Book_form.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(loader.load());




    }

    @FXML
    void btnbroowONACTION(MouseEvent event) {

    }

    @FXML
    void btnexitOnAction(ActionEvent event) {

    }

    @FXML
    void btnookmanegmentONACTION(MouseEvent event) throws IOException {

        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader lodder = new FXMLLoader(getClass().getResource("/books/BookManegment_form.fxml"));
        lodder.setControllerFactory(injector::getInstance);
        this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(lodder.load());
    }

    @FXML
    void btnreturnONACTION(MouseEvent event) {

    }

    @FXML
    void btnuserOnAction(MouseEvent event) throws IOException {

        Injector injector = Guice.createInjector(new AppModule());
       FXMLLoader loader=new FXMLLoader( this.getClass().getResource("/user/User_form.fxml"));
       loader.setControllerFactory(injector::getInstance);
       this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(loader.load());
    }

    @FXML
    void btnusermanegmentOnAction(MouseEvent event) throws IOException {

        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/user/User_manegmentForm.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(loader.load());
    }


}
