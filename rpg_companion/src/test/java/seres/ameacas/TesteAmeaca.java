package seres.ameacas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.security.InvalidKeyException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gerenciador.Rolagem;
import seres.Atributo;
import seres.Pericia;
import seres.personagens.Classe;
import seres.personagens.Elemento;
import seres.personagens.Item;
import seres.personagens.Personagem;
import seres.personagens.Poder;


public class TesteAmeaca {
    private Ameaca ameaca;

    @BeforeEach
    // Cria um personagem para testes
    public void inicializarPersonagem() {
        this.ameaca = new Ameaca("Zumbi de Sangue");

        // Define alguns atributos
        this.ameaca.setAtributo(Atributo.Força, 2);
        this.ameaca.setAtributo(Atributo.Presença, -1);
        
        // Define algumas pericias
        this.ameaca.setPericia(Pericia.Luta, 5);
        this.ameaca.setPericia(Pericia.Percepção, -5);

        // Define o valor de dificuldade
        this.ameaca.setValorDificuldade(10);

        // Adiciona uma habilidade
        this.ameaca.adicionarHabilidade(new Habilidade("Arranhão", "Ataca com as garras, causando 2d6+4 de dano de Sangue."));
    }

    @Test
    // Testa fazer uma rolagem em uma pericia que o ameaça possui atributo positivo
    public void testeFazerTesteVantagem() {
        try {
            Pericia periciaTeste = Pericia.Luta;
            // Teste com um numero "negativo" de dados
            Rolagem resultados = ameaca.fazerTeste(periciaTeste);
            
            // Garante que foi feito um teste com o numero de dados certos
            assertEquals(resultados.getResultados().size(), this.ameaca.getAtributos().get(periciaTeste.atributoBase()));
            
            // Garante que foi feito um teste com o modificador correto
            assertEquals(resultados.getModificador(), this.ameaca.getPericias().get(periciaTeste));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Test
    // Testa fazer uma rolagem em uma pericia que o personagem possui atributo não positivo
    public void testeFazerTesteDesvantagem() {
        try {
            Pericia periciaTeste = Pericia.Percepção;
            // Teste com um numero "positivo" de dados
            Rolagem resultados = ameaca.fazerTeste(periciaTeste);
            
            // Garante que foi feito um teste com o numero de dados certos
            assertEquals(resultados.getResultados().size(), 2 - this.ameaca.getAtributos().get(periciaTeste.atributoBase()));
            
            // Garante que foi feito um teste com o modificador correto
            assertEquals(resultados.getModificador(), this.ameaca.getPericias().get(periciaTeste));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Test
    // Testa fazer uma rolagem em um atributo não positivo
    public void testeFazerTesteAtributoNaoPositivo() {
        Atributo atributoTeste = Atributo.Vigor;
        Rolagem resultados = ameaca.fazerTeste(atributoTeste);

        // Garante que foi feito um teste com o numero de dados certos
        assertEquals(resultados.getResultados().size(), 2 - this.ameaca.getAtributos().get(atributoTeste));
            
        // Garante que foi feito um teste com o modificador correto (teste de Atributo tem modificador 0)
        assertEquals(resultados.getModificador(), 0);
    }

    @Test
    // Testa fazer uma rolagem em um atributo positivo
    public void testeFazerTesteAtributoPositivo() {
        Atributo atributoTeste = Atributo.Força;
        Rolagem resultados = ameaca.fazerTeste(atributoTeste);

        // Garante que foi feito um teste com o numero de dados certos
        assertEquals(resultados.getResultados().size(), this.ameaca.getAtributos().get(atributoTeste));
            
        // Garante que foi feito um teste com o modificador correto (teste de Atributo tem modificador 0)
        assertEquals(resultados.getModificador(), 0);
    }


}
