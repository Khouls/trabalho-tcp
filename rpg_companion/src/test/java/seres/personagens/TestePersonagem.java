package seres.personagens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.security.InvalidKeyException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gerenciador.Rolagem;
import seres.Atributo;
import seres.Pericia;


public class TestePersonagem {
    private Personagem personagem;

    @BeforeEach
    // Cria um personagem para testes
    public void inicializarPersonagem() {
        this.personagem = new Personagem("John Paranormal", Classe.Ocultista);

        // Define alguns atributos
        this.personagem.setAtributo(Atributo.Intelecto, 5);
        this.personagem.setAtributo(Atributo.Presença, 4);

        // Define algumas pericias
        this.personagem.setPericia(Pericia.Ocultismo, 20);
        this.personagem.setPericia(Pericia.Vontade, 15);

        // Adiciona um item
        this.personagem.adicionarItem(new Item("Componentes Ritualistos (Conhecimento)", "Compontentes para rituais de Conhecimento.", 0, 1));

        // Adiciona um Poder
        this.personagem.adicionarPoder(new Poder("Visão do Oculto", "Enxerga através do Conhecimento, recebendo +5 em testes de Percepção e enxerga no escuro.", Elemento.Conhecimento));
    }

    @Test
    // Testa fazer uma rolagem em uma pericia que o personagem possui atributo positivo
    void testeFazerTesteVantagem() {
        try {
            Pericia periciaTeste = Pericia.Ocultismo;
            // Teste com um numero "negativo" de dados
            Rolagem resultados = personagem.fazerTeste(periciaTeste);
            
            // Garante que foi feito um teste com o numero de dados certos
            assertEquals(resultados.getResultados().size(), this.personagem.getAtributos().get(periciaTeste.atributoBase()));
            
            // Garante que foi feito um teste com o modificador correto
            assertEquals(resultados.getModificador(), this.personagem.getPericias().get(periciaTeste));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Test
    // Testa fazer uma rolagem em uma pericia que o personagem possui atributo não positivo
    void testeFazerTesteDesvantagem() {
        try {
            Pericia periciaTeste = Pericia.Luta;
            // Teste com um numero "positivo" de dados
            Rolagem resultados = personagem.fazerTeste(periciaTeste);
            
            // Garante que foi feito um teste com o numero de dados certos
            assertEquals(resultados.getResultados().size(), 2 - this.personagem.getAtributos().get(periciaTeste.atributoBase()));
            
            // Garante que foi feito um teste com o modificador correto
            assertEquals(resultados.getModificador(), this.personagem.getPericias().get(periciaTeste));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Test
    // Testa fazer uma rolagem em uma pericia que o personagem possui atributo não positivo
    void testeFazerTesteAtributo() {
        Atributo atributoTeste = Atributo.Presença;
        Rolagem resultados = personagem.fazerTeste(atributoTeste);

        // Garante que foi feito um teste com o numero de dados certos
        assertEquals(resultados.getResultados().size(), this.personagem.getAtributos().get(atributoTeste));
            
        // Garante que foi feito um teste com o modificador correto (teste de Atributo tem modificador 0)
        assertEquals(resultados.getModificador(), 0);
    }


}
