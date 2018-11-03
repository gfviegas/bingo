package br.ufv.bingo.views;

import br.ufv.bingo.controllers.BingoController;
import br.ufv.bingo.models.Cartela;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameView extends BaseView {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private GridPane gridPanePC;
    @FXML
    private GridPane gridPaneUser;

    @FXML
    public void initialize() {
        BingoController bingoController = BingoController.getInstance();
        bingoController.inicializarJogo();

//        Cartela cartelaPC = bingoController.
    }

    @FXML
    void returnToMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("templates/main.fxml"));
            Parent root = loader.load();
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
