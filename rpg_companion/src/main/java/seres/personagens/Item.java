package seres.personagens;

public class Item {
    private String nome;
    private String descricao;
    private int peso;
    private int categoria;

    public Item(String nome, String descricao, int peso, int categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.peso = peso;
        this.categoria = categoria;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public int getPeso() {
        return this.peso;
    }

    public int getCategoria() {
        return this.categoria;
    }
    

}
