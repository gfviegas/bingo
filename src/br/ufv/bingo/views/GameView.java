package br.ufv.bingo.views;

import br.ufv.bingo.controllers.BingoController;
import br.ufv.bingo.models.Cartela;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
    private AnchorPane logSorteadosPane;
    @FXML
    private Label logSorteados;
    @FXML
    private Label numSorteado;
    @FXML
    private Button sortearButton;

    private BingoController bingoController = BingoController.getInstance();
    private Cartela cartelaPC;
    private Cartela cartelaUser;

    @FXML
    private Label createLabel(String text) {
        Label label = new Label(text);

        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font("Kefa", 40));

        return label;
    }

    @FXML
    private void fillGridCartela(Cartela cartela, GridPane gridPane) {
        gridPane.getChildren().retainAll(gridPane.getChildren().get(0));

        ArrayList<Cartela.CampoNumero> numeros = cartela.getNumeros();
        for (int i = 0; i < numeros.size() / COLUNAS_CARTELA; i++) {
            for (int j = 0; j < COLUNAS_CARTELA; j++) {
                Cartela.CampoNumero num = numeros.get(COLUNAS_CARTELA * i + j);

                Label label = createLabel(num.getValor().toString());
                if (num.isMarcado()) {
                    label.setStyle("-fx-strikethrough: true;");
                    label.setTextFill(Color.web("#dd0000"));
                }

                gridPane.add(label, j, i);
            }
        }
    }

    @FXML
    private void limpaElementosJogo() {
        numSorteadoPane.setVisible(false);
        logSorteadosPane.setVisible(false);

        logSorteados.setText("");
        numSorteado.setText("");

        sortearButton.setDisable(false);
    }

    @FXML
    public void initialize() {
        limpaElementosJogo();
        bingoController.inicializarJogo();

        cartelaPC = bingoController.adicionaJogador("PC");
        cartelaUser = bingoController.adicionaJogador("Usuário");

        fillGridCartela(cartelaPC, gridPanePC);
        fillGridCartela(cartelaUser, gridPaneUser);
    }

    @FXML
    private void sortearNumero() {
        if (!numSorteadoPane.isVisible()) numSorteadoPane.setVisible(true);
        if (!logSorteadosPane.isVisible()) logSorteadosPane.setVisible(true);

        try {
            int numero = bingoController.sortearNumero();
            numSorteado.setText(String.valueOf(numero));
            logSorteados.setText(bingoController.getNumerosSorteados().stream().map(Object::toString).collect(Collectors.joining(", ")));

            fillGridCartela(cartelaPC, gridPanePC);
            fillGridCartela(cartelaUser, gridPaneUser);
            checarVitoria();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void checarVitoria() {
        ArrayList<Cartela> cartelasVitoriosas = bingoController.checaVitoria();
        if (cartelasVitoriosas.isEmpty()) return;

        ArrayList<String> vitoriosos = new ArrayList<String>();
        for (Cartela c: cartelasVitoriosas) {
            vitoriosos.add(c.getDono());
        }


        String texto = (vitoriosos.size() == 1) ? "O " + vitoriosos.get(0) + " é vitorioso!" : "O jogo resultou em um empate!";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado do Jogo");
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();

        sortearButton.setDisable(true);
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
    private void novoJogo() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText("Você deseja mesmo iniciar um novo jogo? Os números sorteados serão cancelados e novas cartelas serão destribuídas aos jogadores.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() != ButtonType.OK) return;

        initialize();
    }

    @FXML
    private void reiniciarJogo() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText("Você deseja mesmo reiniciar o jogo? Os números sorteados serão cancelados, os números das cartelas todos desmarcados e o jogo iniciará novamente com as mesmas cartelas.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() != ButtonType.OK) return;

        limpaElementosJogo();

        bingoController.reiniciarJogo();

        fillGridCartela(cartelaPC, gridPanePC);
        fillGridCartela(cartelaUser, gridPaneUser);
    }

}
