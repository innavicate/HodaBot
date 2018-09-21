package app;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
      MainController mainControllerInstance = new MainController();
      Parent root = mainControllerInstance;

        primaryStage.setTitle("Auto HoDa");
        primaryStage.setScene(new Scene(root, 360, 650));
        primaryStage.show();



    }
}
