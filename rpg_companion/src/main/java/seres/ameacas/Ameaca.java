package seres.ameacas;

import java.util.ArrayList;
import seres.Ser;

public class Ameaca extends Ser {
    private int valorDificuldade;

    private ArrayList<Habilidade> habilidades;

    public Ameaca(String nome) {
        super(nome);
    }

    public void adicionaHabilidade(Habilidade habilidade) {

    }

    public void removeHabilidade(int index) {

    }

    public ArrayList<Habilidade> getHabilidades() {
        return this.habilidades;
    }
    
    public int getValorDificuldade() {
        return this.valorDificuldade;
    }

    public void setValorDificuldade(int valorDificuldade) {
        this.valorDificuldade = valorDificuldade;
    }



}
