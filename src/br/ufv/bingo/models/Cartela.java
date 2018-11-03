package br.ufv.bingo.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static br.ufv.bingo.models.Random.*;

public class Cartela {
    public static final int TAM_CARTELA = 10;
    public static final int MAX_NUM_CARTELA = 50;
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

        Collections.sort(numeros, new Comparator<CampoNumero>() {
            @Override
            public int compare(CampoNumero o1, CampoNumero o2) {
                return o1.valor.compareTo(o2.valor);
            }
        });
    }

    public class CampoNumero {
        private final Integer valor;
        private boolean marcado;

        private CampoNumero(Integer valor) {
            this.marcado = false;
            this.valor = valor;
        }

        public boolean isMarcado() {
            return marcado;
        }

        public Integer getValor() {
            return valor;
        }

        private void setMarcado(boolean marcado) {
            this.marcado = marcado;
        }
    }

    public ArrayList<CampoNumero> getNumeros() {
        return numeros;
    }

    public String getDono() {
        return dono;
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
    

    
    public boolean estaCorreto() {
        for (CampoNumero c : this.numeros) {
            if (!c.isMarcado()) {
                return false;
            }
        }

        return true;
    }
    
}
