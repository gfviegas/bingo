package br.ufv.bingo.controllers;

import br.ufv.bingo.models.Cartela;

import java.util.ArrayList;
import java.util.Random;

import static br.ufv.bingo.models.Random.*;

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

    public ArrayList<Cartela> getCartelasEmJogo() {
        return cartelasEmJogo;
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

    public int sortearNumero() {
        Random rand = new Random();
        Integer num;

        do {
            num = geraNumAleatorio();
        } while (numerosSorteados.contains(num));


        numerosSorteados.add(num);
        for (Cartela c : cartelasEmJogo) {
            c.marca(num);
        }

        System.out.println("Numero sorteado : " + num);

        return num;
    }

    public ArrayList<Cartela> checaVitoria() {
        ArrayList<Cartela> cartelasVitoriosas = new ArrayList<Cartela>();

        for (Cartela c : cartelasEmJogo) {
            if (c.estaCorreto()) cartelasVitoriosas.add(c);
        }

        return cartelasVitoriosas;
    }
}
