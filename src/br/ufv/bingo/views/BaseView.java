package br.ufv.bingo.views;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.control.Button;

public class BaseView {
    @FXML
    private Button closeButton;

    @FXML
    void closeApplication() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
