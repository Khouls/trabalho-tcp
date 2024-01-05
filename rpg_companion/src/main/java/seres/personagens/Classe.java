package seres.personagens;

import java.util.EnumMap;
import java.util.Map;

public enum Classe {
    Combatente, Especialista, Ocultista;

    // INFORMAÇÕES GERAIS SOBRE AS CLASSES DOS PERSONAGENS
    protected static final int EXPOSICAO_POR_NIVEL = 5;

    protected static final int DEFESA_BASE = 10;

    protected static final int NEX_INICIAL = 5;

    protected static final EnumMap<Classe, Integer> VALORES_VIDA_INICIAL = new EnumMap<>(Map.of(
        Combatente, 20,
        Especialista, 16,
        Ocultista, 12 ));
    
    protected static final EnumMap<Classe, Integer> VALORES_VIDA_NEX = new EnumMap<>(Map.of(
        Combatente, 4,
        Especialista, 3,
        Ocultista, 4 ));

    protected static final EnumMap<Classe, Integer> VALORES_ESFORÇO_INICIAL = new EnumMap<>(Map.of(
        Combatente, 2,
        Especialista, 3,
        Ocultista, 4 ));

    protected static final EnumMap<Classe, Integer> VALORES_ESFORÇO_NEX = new EnumMap<>(Map.of(
        Combatente, 2,
        Especialista, 3,
        Ocultista, 4 ));

    protected static final EnumMap<Classe, Integer> VALORES_SANIDADE_INICIAL = new EnumMap<>(Map.of(
        Combatente, 12,
        Especialista, 16,
        Ocultista, 20 ));

    protected static final EnumMap<Classe, Integer> VALORES_SANIDADE_NEX = new EnumMap<>(Map.of(
        Combatente, 3,
        Especialista, 4,
        Ocultista, 5 ));

}

