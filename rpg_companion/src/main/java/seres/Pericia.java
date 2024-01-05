package seres;

public class Pericia {
    private String nome;
    private String grauTreinamento;
    private int modificador;
    private Atributo atributoBase;

    protected enum PericiasValidas {
        Acrobacia


    };


    public Pericia(String nome, Atributo atributoBase) {
        this.nome = nome;
        this.atributoBase = atributoBase;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGrauTreinamento() {
        return this.grauTreinamento;
    }

    public void setGrauTreinamento(String grauTreinamento) {
        this.grauTreinamento = grauTreinamento;
    }

    public int getModificador() {
        return this.modificador;
    }

    public void setModificador(int modificador) {
        this.modificador = modificador;
    }

    public Atributo getAtributoBase() {
        return this.atributoBase;
    }

    public void setAtributoBase(Atributo atributoBase) {
        this.atributoBase = atributoBase;
    }


}
