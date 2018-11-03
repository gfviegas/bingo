package br.ufv.bingo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/templates/main.fxml"));

        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Bingozinator");
        primaryStage.centerOnScreen();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
