package seres.personagens;

public class Item {
    private String nome;
    private String descricao;
    private int peso;
    private int categoria;

    private static final int PESO_INICIAL = 0;
    private static final int CATEGORIA_INICIAL = 1;
    
    public Item() {
        this.nome = "";
        this.descricao = "";
        this.peso = PESO_INICIAL;
        this.categoria = CATEGORIA_INICIAL;
    }

    public Item(String nome, String descricao, int categoria, int peso) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.peso = peso;
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

    public void setDescrição(String descricao) {
        this.descricao = descricao;
    }

    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getCategoria() {
        return this.categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    

}
