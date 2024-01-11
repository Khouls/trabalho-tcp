package seres;

public class Recurso {
    private int valorAtual;
    private int valorMaximo;
    private int modificadorMaximo;

    public Recurso() {
        this.valorMaximo = 0;
        this.modificadorMaximo = 0;

        this.zera();

    }

    public Recurso(int max, int modificador) {
        this.valorMaximo = max;
        this.modificadorMaximo = modificador;

        this.enche();
    }

    public void variaAtual(int variacao) {
        this.valorAtual += variacao;
        // Impdede de ficar abaixo de 0
        if (this.valorAtual < 0) {
            this.valorAtual = 0;
        }

    }

    public void zera() {
        this.valorAtual = 0;
    }

    public void enche() {
        this.valorAtual = this.valorMaximo + this.modificadorMaximo;

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

    public int getModificadorMaximo() {
        return this.modificadorMaximo;
    }

    public void setModificadorMaximo(int modificador) {
        this.modificadorMaximo = modificador;
    }

    public double getProporçao() {
        double propoçao = ((double) this.valorAtual) / this.valorMaximo;
        if (propoçao > 1) {
            return 1;
        }
        return propoçao;
    }

}
