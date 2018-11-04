package br.ufv.bingo.controllers;

import br.ufv.bingo.models.Cartela;

import java.util.ArrayList;
import java.util.Random;

import static br.ufv.bingo.models.Random.*;

public class BingoController {
    private static BingoController ourInstance = new BingoController();

    // Atributos
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

    /**
     * Adiciona um jogador ao jogo
     * @param  nome nome do jogador
     * @return      Cartela desse jogador
     */
    public Cartela adicionaJogador(String nome) {
        Cartela cartela = new Cartela(nome);
        cartelasEmJogo.add(cartela);

        return cartela;
    }

    /**
     * Reseta o jogo
     */
    public void inicializarJogo() {
        jogoFinalizado = false;
        numerosSorteados = new ArrayList<Integer>();
        cartelasEmJogo = new ArrayList<Cartela>();
    }

    /**
     * Sorteia um número aleatório e marca nas cartelas dos jogadores
     * @return número sorteado
     * @throws Exception     caso todos os números já tenham sido sorteados lança uma exceção
     */
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

    /**
     * Reinicia o jogo sem alterar as cartelas
     */
    public void reiniciarJogo() {
        jogoFinalizado = false;
        numerosSorteados = new ArrayList<Integer>();


        for (Cartela c: cartelasEmJogo) {
            c.limpaMarcados();
        }
    }

    /**
     * Checa estado das cartelas em jogo
     * @return todas as cartelas vitoriosas
     */
    public ArrayList<Cartela> checaVitoria() {
        ArrayList<Cartela> cartelasVitoriosas = new ArrayList<Cartela>();

        for (Cartela c : cartelasEmJogo) {
            if (c.estaCorreto()) cartelasVitoriosas.add(c);
        }

        jogoFinalizado = !(cartelasVitoriosas.isEmpty());

        return cartelasVitoriosas;
    }
}
