package seres;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.EnumMap;

import gerenciador.Rolagem;

public abstract class Ser {
    private String nome;
    protected Recurso pontosVida;
    private int defesa;

    protected ArrayList<Pericia> pericias;
    protected EnumMap<Atributo, Integer> atributos;

    protected static String historicoRolagens = "";

    private final int VALOR_INICIAL_ATRIBUTOS = 0;

    public Ser(String nome) {
        this.nome = nome;
        this.defesa = 0;
        this.pericias = new ArrayList<Pericia>();
        this.atributos = new EnumMap<>(Atributo.class);

        // Inicializar todos os atributos
        for (Atributo atributo : Atributo.values()) {
            this.atributos.put(atributo, VALOR_INICIAL_ATRIBUTOS);
        }

    };

    private Pericia encontrarPericia(String nomePericia) throws InvalidKeyException {
        for (Pericia pericia : this.pericias) {
            if (pericia.getNome().equals(nomePericia)) {
                return pericia;
            }
        }
        // Se não foi achada
        throw new InvalidKeyException("Pericia não encontrada!");
    }

    private void adicionaAoHistorico(String nomeRolado, int modificador, Rolagem rolagem) {
        // [Personagem|Pericia] : 20 [10, 20, 4] (+2)
        historicoRolagens += String.format("[%s|%s]", this.nome, nomeRolado);
        historicoRolagens += String.format(" : %d %s", rolagem.getResultadoFinal(), rolagem.getResultados());
        historicoRolagens += String.format("(%s%d)\n", modificador >= 0 ? "+" : "", modificador);

    }

    public Rolagem fazerTeste(String nomePericia) throws InvalidKeyException {
        Pericia periciaTeste = this.encontrarPericia(nomePericia);
        int qtDados = this.atributos.get(periciaTeste.getAtributoBase());
        int modificador = periciaTeste.getModificador();

        Rolagem rolagem = Rolagem.rolarTeste(qtDados, modificador);
        adicionaAoHistorico(nomePericia, modificador, rolagem);

        return rolagem;

    }

    public Rolagem fazerTeste(Atributo atributo) {
        Rolagem rolagem = Rolagem.rolarTeste(this.atributos.get(atributo), 0);
        adicionaAoHistorico(atributo.toString(), 0, rolagem);

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

    public ArrayList<Pericia> getPericias() {
        return this.pericias;
    }

    public EnumMap<Atributo, Integer> getAtributos() {
        return new EnumMap<Atributo, Integer>(this.atributos);
    }    

}
