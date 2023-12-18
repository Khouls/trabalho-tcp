package seres;

public class Recurso {
    private int valorAtual;
    private int valorMaximo;
    private int modificador;

    public Recurso() {

    }

    public Recurso(int max, int modificador) {
        this.valorMaximo = max;
        this.modificador = modificador;
    }

    public void variaAtual(int variacao) {

    }

    public void zera() {
    }

    public void enche() {

    }
    
    public int getValorAtual() {
        return this.valorAtual;
    }

    public void setValorAtual(int valorAtual) {
        this.valorAtual = valorAtual;
    }

    public int getValorMaximo() {
        return this.valorMaximo;
    }

    public void setValorMaximo(int valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public int getModificador() {
        return this.modificador;
    }

    public void setModificador(int modificador) {
        this.modificador = modificador;
    }

}
