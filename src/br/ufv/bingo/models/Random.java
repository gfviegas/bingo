package br.ufv.bingo.models;

public class Random {
    private static final java.util.Random rand = new java.util.Random();

    private Random() {
    }

    public static Integer geraNumAleatorio(){
        return rand.nextInt(Cartela.MAX_NUM_CARTELA) + 1;
    }
}
