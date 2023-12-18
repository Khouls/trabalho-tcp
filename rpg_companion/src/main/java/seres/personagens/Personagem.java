package seres.personagens;

import seres.Ser;
import seres.Recurso;
import java.util.ArrayList;

public class Personagem extends Ser {
    private Classe classe;
    private int nivelExposicao;
    private Recurso pontosEsforco;
    private Recurso pontosSanidade;

    private ArrayList<Item> inventario;
    private ArrayList<Poder> poderes;

    public Personagem(String nome, Classe classe) {
        super(nome);
    }

    public void adicionaItem(Item item) {

    }

    public void removeItem(int index) {

    }

    public void adicionaPoder() {

    }

    public void removePoder(int index) {

    }

    public Classe getClasse() {
        return this.classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }


    public int getNivelExposicao() {
        return this.nivelExposicao;
    }

    public void setNivelExposicao(int nivelExposicao) {
        this.nivelExposicao = nivelExposicao;
    }

    public Recurso getPontosEsforco() {
        return this.pontosEsforco;
    }

    public Recurso getPontosSanidade() {
        return this.pontosSanidade;
    }

    public ArrayList<Item> getInventario() {
        return this.inventario;
    }

    public ArrayList<Poder> getPoderes() {
        return this.poderes;
    }


}
