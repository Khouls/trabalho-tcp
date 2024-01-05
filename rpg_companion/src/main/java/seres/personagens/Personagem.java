package seres.personagens;

import seres.Ser;
import seres.Atributo;
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
        this.setDefesa(Classe.DEFESA_BASE + this.atributos.get(Atributo.Agilidade));
        this.nivelExposicao = Classe.NEX_INICIAL;
        this.pontosVida = new Recurso();
        this.pontosEsforco = new Recurso();
        this.pontosSanidade = new Recurso();

        this.atualizaValoresNoNivel();

        this.inventario = new ArrayList<Item>();
        this.poderes = new ArrayList<Poder>();
    }

    @Override
    protected void inicializarPericias() {


    }

    
    private int calculaRecursoNoNivel(int valorInicial, int valorPorNivel, int modificadorDeAtributo) {
        return valorInicial + modificadorDeAtributo + ((valorPorNivel + modificadorDeAtributo)* ((this.nivelExposicao / Classe.EXPOSICAO_POR_NIVEL) - 1));
    }

    private void atualizaValoresNoNivel() {
        // Vida é modificada por Vigor
        int vidaMaxima = calculaRecursoNoNivel(this.classe.vidaInicial(), this.classe.vidaNex(), this.atributos.get(Atributo.Vigor));
        this.pontosVida.setValorMaximo(vidaMaxima);
        this.pontosVida.enche();

        // Esforço é modificado por presença        
        int esforcoMaximo = calculaRecursoNoNivel(this.classe.esforcoInicial(), this.classe.esforcoNex(), this.atributos.get(Atributo.Presença));
        this.pontosEsforco.setValorMaximo(esforcoMaximo);
        this.pontosEsforco.enche();

        // Sanidade não é modificada por nada
        int sanidadeMaxima = calculaRecursoNoNivel(this.classe.sanidadeInicial(), this.classe.sanidadeNex(), 0);  
        this.pontosSanidade.setValorMaximo(sanidadeMaxima);
        this.pontosSanidade.enche();

    }

    public void adicionaItem(Item item) {
        this.inventario.add(item);
    }

    public void removeItem(int index) {
        this.inventario.remove(index);
    }

    public void adicionaPoder(Poder poder) {
        this.poderes.add(poder);

    }

    public void removePoder(int index) {
        this.poderes.remove(index);
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
        return new ArrayList<Item>(this.inventario);
    }

    public ArrayList<Poder> getPoderes() {
        return new ArrayList<Poder>(this.poderes);
    }


}
