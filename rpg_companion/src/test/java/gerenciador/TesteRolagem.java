package gerenciador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gerenciador.Rolagem;


public class TesteRolagem {

    @Test
    // Testa rolagens com todos os modificadores na faixa especificada
    void testeRolarD20() {
        final int modificadorFinal = 100;
        final int modificadorInicial = -100;

        for (int modificador = modificadorInicial; modificador < modificadorFinal; modificador++) {
            Rolagem d20 = Rolagem.rolarDados(20, 1, modificador);
            int resultadoFinal = d20.getResultadoFinal();

            boolean dentroAcima = resultadoFinal <= (20 + modificador);
            boolean dentroAbaixo = resultadoFinal >= (1 + modificador);

            assertTrue(dentroAcima && dentroAbaixo, "Fora do Intervalo: " + (resultadoFinal - modificador));
        }
    }

    @Test
    // Testa rolagens com todos os modificadores na faixa especificada
    void testeRolarD20Vantagem() {
        final int modificadorFinal = 100;
        final int modificadorInicial = -100;

        for (int modificador = modificadorInicial; modificador < modificadorFinal; modificador++) {
            Rolagem d20 = Rolagem.rolarDados(20, 2, modificador);
            int resultadoFinal = d20.getResultadoFinal();

            boolean dentroAcima = resultadoFinal <= (20 + modificador);
            boolean dentroAbaixo = resultadoFinal >= (1 + modificador);

            // Garante que o resultado está na faixa esperada
            assertTrue(dentroAcima && dentroAbaixo, "Fora do Intervalo: " + (resultadoFinal - modificador));

            //             
        }
    }


}
