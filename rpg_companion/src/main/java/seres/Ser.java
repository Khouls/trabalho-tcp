package seres;

import java.util.ArrayList;
import java.util.EnumMap;

import gerenciador.Rolagem;

public abstract class Ser {
    private String nome;
    private Recurso pontosVida;
    private int defesa;

    protected ArrayList<Pericia> pericias;
    protected EnumMap<Atributo, Integer> atributos;

    public static final int NUM_ATRIBUTOS = 5;

    public Ser(String nome) {
        this.nome = nome;
    };

    private Pericia encontrarPericia(String nomePericia) {
        for (Pericia pericia : this.pericias) {
            if (pericia.getNome().equals(nomePericia)) {
                return pericia;
            }
        }
        // Perícia não encontrada
        return null;
    }

    public Rolagem fazerTeste(String nomePericia) {
        Pericia periciaTeste = this.encontrarPericia(nomePericia);
        int qtDados = this.atributos.get(periciaTeste.getAtributoBase());
        int modificador = periciaTeste.getModificador();

        return Rolagem.rolarTeste(qtDados, modificador);

    }

    public Rolagem fazerTeste(Atributo atributo) {
        return Rolagem.rolarTeste(this.atributos.get(atributo), 0);
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

    public EnumMap<Atributo, Integer> getAtributos() {
        return new EnumMap<Atributo, Integer>(this.atributos);
    }    

}
