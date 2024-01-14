package seres.personagens;

public class Poder {
    private String nome;
    private String descricao;
    private Elemento elemento;

    public Poder() {
        this.nome = "";
        this.descricao = "";
        this.elemento = Elemento.Nenhum;
    }

    public Poder(String nome, String descricao, Elemento elemento) {
        this.nome = nome;
        this.descricao = descricao;
        this.elemento = elemento;
    }
   
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Elemento getElemento() {
        return this.elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

}
