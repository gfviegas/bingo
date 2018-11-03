package br.ufv.bingo.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView extends BaseView {
    @FXML
    private AnchorPane mainPane;

    @FXML
    void startGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("templates/game.fxml"));
            Parent root = loader.load();
//            MainView controller = loader.<MainView>getController();

            Scene scene = new Scene(root);
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
