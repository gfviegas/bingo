package br.ufv.bingo.controllers;

import br.ufv.bingo.models.Cartela;

import java.util.ArrayList;
import java.util.Random;

import static br.ufv.bingo.models.Random.*;

public class BingoController {
    private static BingoController ourInstance = new BingoController();

    private Boolean jogoFinalizado;
    private ArrayList<Integer> numerosSorteados;
    private ArrayList<Cartela> cartelasEmJogo;

    public static BingoController getInstance() {
        return ourInstance;
    }

    private BingoController() {
        inicializarJogo();
    }

    public ArrayList<Cartela> getCartelasEmJogo() {
        return cartelasEmJogo;
    }

    public ArrayList<Integer> getNumerosSorteados() {
        return numerosSorteados;
    }

    public Cartela adicionaJogador(String nome) {
        Cartela cartela = new Cartela(nome);
        cartelasEmJogo.add(cartela);

        return cartela;
    }

    public void inicializarJogo() {
        jogoFinalizado = false;
        numerosSorteados = new ArrayList<Integer>();
        cartelasEmJogo = new ArrayList<Cartela>();
    }

    public int sortearNumero() throws Exception {
        if (numerosSorteados.size() == Cartela.MAX_NUM_CARTELA) throw new Exception("Todos os números já foram sorteados");
        if (jogoFinalizado) throw new Exception("O jogo já foi finalizado!");


        Integer num;
        do {
            num = geraNumAleatorio();
        } while (numerosSorteados.contains(num));


        numerosSorteados.add(num);
        for (Cartela c: cartelasEmJogo) {
            c.marca(num);

        }

        return num;
    }

    public void reiniciarJogo() {
        jogoFinalizado = false;
        numerosSorteados = new ArrayList<Integer>();


        for (Cartela c: cartelasEmJogo) {
            c.limpaMarcados();
        }
    }

    public ArrayList<Cartela> checaVitoria() {
        ArrayList<Cartela> cartelasVitoriosas = new ArrayList<Cartela>();

        for (Cartela c : cartelasEmJogo) {
            if (c.estaCorreto()) cartelasVitoriosas.add(c);
        }

        jogoFinalizado = !(cartelasVitoriosas.isEmpty());

        return cartelasVitoriosas;
    }
}
