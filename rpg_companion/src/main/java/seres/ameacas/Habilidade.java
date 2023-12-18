package seres.ameacas;

public class Habilidade {
    private String nome;
    private String descricao;

    public Habilidade(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

}
