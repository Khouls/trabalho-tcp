package seres.personagens;

public class Poder {
    private String nome;
    private String descricao;
    private Elemento elemento;

    public Poder(String nome, String descricao, Elemento elemento) {
        this.nome = nome;
        this.descricao = descricao;
        this.elemento = elemento;
    }
   
    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public Elemento getElemento() {
        return this.elemento;
    }



}
