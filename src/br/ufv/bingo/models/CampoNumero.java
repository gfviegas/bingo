package br.ufv.bingo.models;

public class CampoNumero {
    private final Integer valor;
    private boolean marcado;
    
    public CampoNumero(Integer valor) {
        this.marcado = false;
        this.valor = valor;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public Integer getValor() {
        return valor;
    }    
}
