package seres;

public enum Pericia {
    Acrobacia(Atributo.Agilidade),
    Adestramento(Atributo.Presença),
    Artes(Atributo.Presença),
    Atletismo(Atributo.Força),
    Atualidades(Atributo.Intelecto),
    Ciências(Atributo.Intelecto),
    Crime(Atributo.Agilidade),
    Diplomacia(Atributo.Presença),
    Enganação(Atributo.Presença),
    Fortitude(Atributo.Vigor),
    Furtividade(Atributo.Agilidade),
    Iniciativa(Atributo.Agilidade),
    Intimidação(Atributo.Agilidade),
    Intuição(Atributo.Intelecto),
    Investigação(Atributo.Intelecto),
    Luta(Atributo.Força),
    Medicina(Atributo.Intelecto),
    Ocultismo(Atributo.Intelecto),
    Percepção(Atributo.Presença),
    Pilotagem(Atributo.Agilidade),
    Pontaria(Atributo.Agilidade),
    Profissão(Atributo.Intelecto),
    Reflexos(Atributo.Agilidade),
    Religião(Atributo.Presença),
    Sobrevivência(Atributo.Intelecto),
    Tática(Atributo.Intelecto),
    Tecnologia(Atributo.Intelecto),
    Vontade(Atributo.Presença);

    private final Atributo atributoBase;

    private Pericia(Atributo atributoBase) {
        this.atributoBase = atributoBase;
    }

    public Atributo atributoBase() {
        return this.atributoBase;
    }
}
