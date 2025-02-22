package edu.icet.ecom.controller.home;
import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.icet.ecom.controller.book.Book_form;
import edu.icet.ecom.util.AppModule;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeForm {

    @FXML
    private AnchorPane mainancherpane;

    @FXML
    private AnchorPane setancherpane;


    @FXML
    void btnFineOnActions(ActionEvent event) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/fine/Fine_form.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(loader.load());
    }

    @FXML
    void btnbookONACTION(ActionEvent event) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/books/Book_form.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(loader.load());

    }

    @FXML
    void btnbroowONACTION(ActionEvent event) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/borrowbook/Borrow_form.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(loader.load());
    }

    @FXML
    void btnexitOnAction(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    void btnookmanegmentONACTION(ActionEvent event) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/books/BookManegment_form.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(loader.load());
    }

    @FXML
    void btnreturnONACTION(ActionEvent event) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/returnbook/returnbookform.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(loader.load());

    }

    @FXML
    void btnuserOnAction(ActionEvent event) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/user/User_form.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(loader.load());
    }

    @FXML
    void btnusermanegmentOnAction(ActionEvent event) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/user/User_manegmentForm.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(loader.load());
    }



    @FXML
     void btnbackOnActio(ActionEvent event) throws IOException {

        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/home/Home_form .fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(loader.load());

    }


    public void btnreportOnAcion(ActionEvent actionEvent) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/report_Form/userReport.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(loader.load());
    }

    public void  hohome() throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/home/ButtonHome_fom.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.setancherpane.getChildren().clear();
        this.setancherpane.getChildren().add(loader.load());
    }
}
