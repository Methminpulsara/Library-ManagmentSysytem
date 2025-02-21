import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.icet.ecom.util.AppModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

//
//        Injector injector = Guice.createInjector(new AppModule());
//        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/fine/Fine_form.fxml"));
//        loader.setControllerFactory(injector::getInstance);
//        this.setancherpane.getChildren().clear();
//        this.setancherpane.getChildren().add(loader.load());


        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/Login_form .fxml"));
        loader.setControllerFactory(injector::getInstance);
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
