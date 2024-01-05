package seres;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.EnumMap;

import gerenciador.Rolagem;

public abstract class Ser {
    private String nome;
    protected Recurso pontosVida;
    private int defesa;

    protected EnumMap<Pericia, Integer> pericias;
    protected EnumMap<Atributo, Integer> atributos;

    protected static String historicoRolagens = "";

    private final int VALOR_INICIAL_ATRIBUTOS = 0;

    public Ser(String nome) {
        this.nome = nome;
        this.defesa = 0;
        this.pericias = new EnumMap<>(Pericia.class);
        this.atributos = new EnumMap<>(Atributo.class);

        // Inicializar todos os atributos
        for (Atributo atributo : Atributo.values()) {
            this.atributos.put(atributo, VALOR_INICIAL_ATRIBUTOS);
        }

        // Inicializar as pericias
        inicializarPericias();

    };

    protected abstract void inicializarPericias();


    private void adicionaAoHistorico(String nomeRolado, int modificador, Rolagem rolagem) {
        // [Personagem|Pericia] : 20 [10, 20, 4] (+2)
        historicoRolagens += String.format("[%s|%s]", this.nome, nomeRolado);
        historicoRolagens += String.format(" : %d %s", rolagem.getResultadoFinal(), rolagem.getResultados());
        historicoRolagens += String.format("(%s%d)\n", modificador >= 0 ? "+" : "", modificador);

    }

    public Rolagem fazerTeste(Pericia pericia) throws InvalidKeyException {
        int qtDados = this.atributos.get(pericia.atributoBase());
        int modificador = pericias.get(pericia);

        Rolagem rolagem = Rolagem.rolarTeste(qtDados, modificador);
        adicionaAoHistorico(pericia.name(), modificador, rolagem);

        return rolagem;

    }

    public Rolagem fazerTeste(Atributo atributo) {
        Rolagem rolagem = Rolagem.rolarTeste(this.atributos.get(atributo), 0);
        adicionaAoHistorico(atributo.name(), 0, rolagem);

        return rolagem;
        
    }

    public Rolagem rodarDados(int numFaces, int qtDados,  int modificador) {
        Rolagem rolagem = Rolagem.rolarDados(numFaces, qtDados, modificador);
        adicionaAoHistorico(String.format("%dd%d+%d", qtDados, numFaces, modificador), 0, rolagem);

        return rolagem;
        
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Recurso getPontosVida() {
        return this.pontosVida;
    }

    public int getDefesa() {
        return this.defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public EnumMap<Pericia, Integer> getPericias() {
        return new EnumMap<>(this.pericias);
    }

    public EnumMap<Atributo, Integer> getAtributos() {
        return new EnumMap<Atributo, Integer>(this.atributos);
    }    

}
