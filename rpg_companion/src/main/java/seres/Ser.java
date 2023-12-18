package seres;

import java.util.ArrayList;

public abstract class Ser {
    private String nome;
    private Recurso pontosVida;
    private int defesa;

    protected ArrayList<Pericia> pericias;
    protected int[] atributos;

    public static final int NUM_ATRIBUTOS = 5;

    public Ser(String nome) {
        this.nome = nome;
    };

    public ArrayList<Integer> fazerTeste(String nomePericia) {
        // TODO: implementar
        return new ArrayList<>();

    }

    public ArrayList<Integer> fazerTeste(Atributo atributo) {
         // TODO: implementar
        return new ArrayList<>();

    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Recurso getPontosVida() {
        return this.pontosVida;
    }

    public int getDefesa() {
        return this.defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public ArrayList<Pericia> getPericias() {
        return this.pericias;
    }

    public int[] getAtributos() {
        return this.atributos;
    }    

}
