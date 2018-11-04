package br.ufv.bingo.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static br.ufv.bingo.models.Random.*;

public class Cartela {
    // Constantes
    public static final int TAM_CARTELA = 10;
    public static final int MAX_NUM_CARTELA = 50;

    // Atributos
    private final ArrayList<CampoNumero> numeros;
    private final String dono;

    /**
     * Cria uma cartela nova com números aleatórios e distintos e no final a ordena.
     * @param dono Nome do dono da cartela
     */
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
    /**
     * Possui um número e um boolean que representa se está ou não marcado
     */
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
    //gets and sets
    public ArrayList<CampoNumero> getNumeros() {
        return numeros;
    }

    public String getDono() {
        return dono;
    }

    /**
     * Retorna todos os números da cartela em forma de String
     * @return String de retorno
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < TAM_CARTELA; i++) {
            str = str.concat(numeros.get(i).getValor().toString()+" ");
        }
        return str;
    }

    /**
     * Marca um número na lista se houver
     * @param num número que precisa ser marcado
     */
    public void marca(int num){
        for (CampoNumero c : this.numeros) {
            if (c.getValor().equals(num)) {
                c.setMarcado(true);
                return;
            }
        }
    }

    /**
     * Função privada para verificar se um número está na lista no meio de sua criação
     * @param  num   Número a ser verficado
     * @param  index Índice até onde foi criado a cartela
     * @return     Boolean que representa se é repetido ou não
     */
    private boolean ehNumRepetido(Integer num, int index) {
        for (int i = 0; i < index; i++) {
            if (this.numeros.get(i).getValor().equals(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Percorre todos os campos da cartela verificando se está toda marcada
     * @return True se está correta
     */
    public boolean estaCorreto() {
        for (CampoNumero c : this.numeros) {
            if (!c.isMarcado()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Percorre todos os campos desmarcando-os
     */
    public void limpaMarcados() {
        for (CampoNumero cn: numeros) {
            cn.setMarcado(false);
        }
    }
}
