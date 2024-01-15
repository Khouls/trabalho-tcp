package seres.ameacas;

import java.util.ArrayList;

import seres.Pericia;
import seres.Ser;

public class Ameaca extends Ser {
    private int valorDificuldade;

    private ArrayList<Habilidade> habilidades;

    public Ameaca(String nome) {
        super(nome);
        this.valorDificuldade = 0;
        this.habilidades = new ArrayList<Habilidade>();

    }
    
    @Override
    protected void inicializarPericias() {
        // Ameaças apenas possuem Percepção, Iniciativa, Fortitude, Reflexos e Vontade
        this.pericias.put(Pericia.Percepção, 0);
        this.pericias.put(Pericia.Iniciativa, 0);
        this.pericias.put(Pericia.Fortitude, 0);
        this.pericias.put(Pericia.Reflexos, 0);
        this.pericias.put(Pericia.Vontade, 0);
    }

    public void adicionarHabilidade(Habilidade habilidade) {
        this.habilidades.add(habilidade);
    }

    public void adicionarHabilidade(Habilidade habilidade, int index) {
        this.habilidades.add(index, habilidade);
    }

    public void removerHabilidade(int index) {
        this.habilidades.remove(index);
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
