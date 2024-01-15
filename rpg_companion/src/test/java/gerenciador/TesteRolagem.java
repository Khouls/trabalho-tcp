package gerenciador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import gerenciador.Rolagem;


public class TesteRolagem {

    @Test
    // Testa rolagens com todos os modificadores na faixa especificada
    void testeRolarD20() {
        final int modificadorFinal = 100;
        final int modificadorInicial = -100;

        final int numFaces = 20;

        for (int modificador = modificadorInicial; modificador < modificadorFinal; modificador++) {
            Rolagem rolagem = Rolagem.rolarDados(numFaces, 1, modificador);
            int resultadoFinal = rolagem.getResultadoFinal();

            boolean dentroAcima = resultadoFinal <= (numFaces + modificador);
            boolean dentroAbaixo = resultadoFinal >= (1 + modificador);

            assertTrue(dentroAcima && dentroAbaixo, "Fora do Intervalo: " + (resultadoFinal - modificador));
        }
    }

    @Test
    // Testa rodar varios 
    void testeRolarD6Somar() {
        final int qtInicial = 1;
        final int qtFinal = 10;

        final int numFaces = 6;

        for (int qtDados = qtInicial; qtDados < qtFinal; qtDados++) {
            Rolagem rolagem = Rolagem.rolarDados(numFaces, qtDados, 0);
            int resultadoFinal = rolagem.getResultadoFinal();
            int total = 0;
            for (int valor : rolagem.getResultados()) {
                total += valor;
            }

            boolean dentroAcima = resultadoFinal <= qtDados * numFaces;
            boolean dentroAbaixo = resultadoFinal >= qtDados * 1;

            // Garante que o resultado está na faixa esperada
            assertTrue(dentroAcima && dentroAbaixo, "Fora do Intervalo: " + resultadoFinal);

            // Garante que foram rodados dois dados
            assertEquals(rolagem.getResultados().size(), qtDados, "Quantidade errada: " + rolagem.getResultados().size() + "/" + qtDados);


            // Garante que foi pego o maior dos dois
            assertEquals(resultadoFinal, total);
        }
    }

    @Test
    // Testa rolagens sem modificador, rodando nd20s na faixa especificada e pegando o maior
    void testeRodarTesteVantagem() {
        final int qtInicial = 1;
        final int qtFinal = 10;

        for (int qtDados = qtInicial; qtDados < qtFinal; qtDados++) {
            Rolagem rolagem = Rolagem.rolarTeste(qtDados, 0);
            int resultadoFinal = rolagem.getResultadoFinal();
            ArrayList<Integer> resultadosOrdenados = rolagem.getResultados();
            Collections.sort(resultadosOrdenados, Collections.reverseOrder());

            boolean dentroAcima = resultadoFinal <= 20;
            boolean dentroAbaixo =  resultadoFinal >= 1;

            // Garante que o resultado está na faixa esperada
            assertTrue(dentroAcima && dentroAbaixo, "Fora do Intervalo: " + resultadoFinal);

            // Garante que foram rodados dois dados
            assertEquals(rolagem.getResultados().size(), qtDados, "Quantidade errada: " + rolagem.getResultados().size() + "/" + qtDados);

            // Garante que foi pego o maior dos dois
            assertEquals(resultadoFinal, resultadosOrdenados.get(0));
        }
    }

    @Test
    // Testa rolagens sem modificador, rodando nd20s na faixa especificada e pegando o menor
    void testeRodarTesteDesvantagem() {
        final int qtInicial = 0;
        final int qtFinal = -10;

        for (int qtDados = qtInicial; qtDados > qtFinal; qtDados--) {
            Rolagem rolagem = Rolagem.rolarTeste(qtDados, 0);
            int resultadoFinal = rolagem.getResultadoFinal();
            ArrayList<Integer> resultadosOrdenados = rolagem.getResultados();
            Collections.sort(resultadosOrdenados);

            boolean dentroAcima = resultadoFinal <= 20;
            boolean dentroAbaixo = resultadoFinal >= 1;

            // Garante que o resultado está na faixa esperada
            assertTrue(dentroAcima && dentroAbaixo, "Fora do Intervalo: " + resultadoFinal);

            // Garante que foram rodados dois dados
            assertEquals(rolagem.getResultados().size(), (2 - qtDados), "Quantidade errada: " + rolagem.getResultados().size() + "/" + qtDados);

            // Garante que foi pego o maior dos dois
            assertEquals(resultadoFinal, resultadosOrdenados.get(0));
        }
    }


}
