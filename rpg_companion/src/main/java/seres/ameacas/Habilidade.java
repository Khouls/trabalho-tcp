package seres.ameacas;

public class Habilidade {
    private String nome;
    private String descricao;

    public Habilidade() {
        this.nome = "";
        this.descricao = "";
    }

    public Habilidade(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
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


}
