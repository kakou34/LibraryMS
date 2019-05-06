package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Controller.LoginController;
import sample.Controller.UserController;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("View/login.fxml"));
        Pane myPane = myLoader.load();
        LoginController controller = myLoader.getController();
        controller.setPrevStage(primaryStage);
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(myPane));
        primaryStage.show();
    }




}
