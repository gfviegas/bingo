package br.ufv.bingo.models;

import br.ufv.bingo.models.CampoNumero;
import java.util.ArrayList;
import java.util.Random;

public class Cartela {
    private Random rand = new Random();
    static final int TAM_CARTELA = 10;
    static final int MAX_NUM_CARTELA = 50;
    private final ArrayList<CampoNumero> numeros;
    private final String dono;

    public Cartela(String dono) {
        this.dono = dono;
        this.numeros = new ArrayList<>();

        Integer num;
        for (int i = 0; i < TAM_CARTELA; i++) {
            do {
                num = geraNumAleatorio();
            } while(ehNumRepetido(num, i));

            numeros.add(new CampoNumero(num));
        }
    }
    
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < TAM_CARTELA; i++) {
            str = str.concat(numeros.get(i).getValor().toString()+" ");            
        }
        return str;
    }
    
    public void marca(int num){
        for (CampoNumero c : this.numeros) {
            if (c.getValor().equals(num)) {
                c.setMarcado(true);
                return;
            }
        }
    }

    private boolean ehNumRepetido(Integer num, int index) {
        for (int i = 0; i < index; i++) {
            if (this.numeros.get(i).getValor().equals(num)) {
                return true;
            }
        }
        return false;
    }
    
    public Integer geraNumAleatorio(){
        return rand.nextInt(MAX_NUM_CARTELA) + 1;
    }
    
    public boolean estaCorreto() {
        for (CampoNumero c : this.numeros) {
            if (!c.isMarcado()) {
                return false;
            }
        }

        return true;
    }
    
}
