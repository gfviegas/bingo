package br.ufv.bingo.views;

import br.ufv.bingo.controllers.BingoController;
import br.ufv.bingo.models.Cartela;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GameView extends BaseView {
    private static final int COLUNAS_CARTELA = 2;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private GridPane gridPanePC;
    @FXML
    private GridPane gridPaneUser;
    @FXML
    private AnchorPane numSorteadoPane;
    @FXML
    private Label numSorteado;

    private BingoController bingoController = BingoController.getInstance();

    @FXML
    private Label createLabel(String text) {
        Label label = new Label(text);

        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font("Kefa", 40));

        return label;
    }

    @FXML
    private void fillGridCartela(Cartela cartela, GridPane gridPane) {
        ArrayList<Cartela.CampoNumero> numeros = cartela.getNumeros();
        for (int i = 0; i < numeros.size() / COLUNAS_CARTELA; i++) {
            for (int j = 0; j < COLUNAS_CARTELA; j++) {
                Label label = createLabel(numeros.get(COLUNAS_CARTELA * i + j).getValor().toString());
                gridPane.add(label, j, i);
            }
        }
    }

    @FXML
    public void initialize() {
        numSorteadoPane.setVisible(false);
        bingoController.inicializarJogo();

        Cartela cartelaPC = bingoController.adicionaJogador("PC");
        Cartela cartelaUser = bingoController.adicionaJogador("Usuário");

        fillGridCartela(cartelaPC, gridPanePC);
        fillGridCartela(cartelaUser, gridPaneUser);
    }

    @FXML
    private void sortearNumero() {
        if (!numSorteadoPane.isVisible()) numSorteadoPane.setVisible(true);
        int numero = bingoController.sortearNumero();

        numSorteado.setText(String.valueOf(numero));
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

    @FXML
    private void markNumbers() {
//        ArrayList<Cartela> cartelas = bingoController.getCartelasEmJogo();
//
//        for (Cartela c: cartelas) {
//            for (Cartela.CampoNumero campoNumero: c.getNumeros()) {
//
//            }
//        }
    }

}
