package gerenciador;

import seres.Ser;
import java.util.ArrayList;

public class GerenciadorSessao {

    private ArrayList<Ser> seres;
    private String anotacoes;
        
    public GerenciadorSessao() {
        this.seres = new ArrayList<Ser>();
        this.anotacoes = "";
    }

    public void adicionaSer(Ser ser) {
        this.seres.add(ser);
    }

    public void removeSer(int index) {
        this.seres.remove(index);
    }

    public String getAnotacoes() {
        return this.anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    public ArrayList<Ser> getSeres() {
        return new ArrayList<Ser>(this.seres);
    }
    
}
