package seres.personagens;

public enum Classe {
    Combatente(20, 4, 2, 2, 12, 3),
    Especialista(16, 3, 3, 3, 16, 4),
    Ocultista(12, 4, 4, 4, 20, 5);

    private final int vidaInicial;
    private final int vidaNex;

    private final int esforcoInicial;
    private final int esforcoNex;

    private final int sanidadeInicial;
    private final int sanidadeNex;

    private Classe(int vidaInicial, int vidaNex, int esforcoInicial, int esforcoNex, int sanidadeInicial, int sanidadeNex) {
        this.vidaInicial = vidaInicial;
        this.vidaNex = vidaNex;
        this.esforcoInicial = esforcoInicial;
        this.esforcoNex = esforcoNex;
        this.sanidadeInicial = sanidadeInicial;
        this.sanidadeNex = sanidadeNex;
    }

    // INFORMAÇÕES GERAIS SOBRE AS CLASSES DOS PERSONAGENS
    protected static final int EXPOSICAO_POR_NIVEL = 5;

    protected static final int DEFESA_BASE = 10;

    protected static final int NEX_INICIAL = 5;

    public int vidaInicial() {
        return this.vidaInicial;
    }

    public int vidaNex() {
        return this.vidaNex;
    }

    public int esforcoInicial() {
        return this.esforcoInicial;
    }

    public int esforcoNex() {
        return this.esforcoNex;
    }

    public int sanidadeInicial() {
        return this.sanidadeInicial;
    }

    public int sanidadeNex() {
        return this.sanidadeNex;
    }

}

