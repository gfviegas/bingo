package br.ufv.bingo.controllers;

import br.ufv.bingo.models.Cartela;

import java.util.ArrayList;
import java.util.Random;

public class BingoController {
    private static BingoController ourInstance = new BingoController();

    private ArrayList<Integer> numerosSorteados;
    private ArrayList<Cartela> cartelasEmJogo;

    public static BingoController getInstance() {
        return ourInstance;
    }

    private BingoController() {
        inicializarJogo();
    }

    public Cartela adicionaJogador(String nome) {
        Cartela cartela = new Cartela(nome);
        cartelasEmJogo.add(cartela);

        return cartela;
    }

    public void inicializarJogo() {
        numerosSorteados = new ArrayList<Integer>();
        cartelasEmJogo = new ArrayList<Cartela>();
    }

//    public void sortearNumero() {
//        Random rand = new Random();
//        Integer num = cartelaComputador.geraNumAleatorio();
//
//        while(numerosSorteados.contains(num)){
//            num = cartelaComputador.geraNumAleatorio();
//        }
//        numerosSorteados.add(num);
//        cartelaComputador.marca(num);
//        cartelaJogador.marca(num);
//        System.out.println("Numero sorteado : "+num);
//    }
//
//    public boolean checaVitoria() {
//        if (cartelaComputador.estaCorreto() && cartelaJogador.estaCorreto()) {
//            System.out.println("Empate!");
//            return true;
//        }
//
//        if (cartelaComputador.estaCorreto()) {
//            System.out.println("Máquina ganhou!");
//            return true;
//        }
//
//        if (cartelaJogador.estaCorreto()) {
//            System.out.println("Jogador ganhou!");
//            return true;
//        }
//
//        return false;
//    }
}
