package gerenciador;

import java.util.ArrayList;
import java.util.Random;

public class Rolagem {
    private int resultadoFinal;
    private ArrayList<Integer> resultados;

    // *Consultar o professor
    private Rolagem() {
        this.resultados = new ArrayList<Integer>();
        this.resultadoFinal = 0;
    }

    // Rodar uma quantidade de dados, somar seus resultados e adicionar o modificador no final.
    public static Rolagem rolarDados(int numFaces, int qtDados, int modificador) {
        Random randomGen = new Random();
        Rolagem rolagemCompleta = new Rolagem();

        for (int  i = 0; i < qtDados; i++) {
            // Gera um numero de 1 ate numFaces
            int resultado = randomGen.nextInt(numFaces) + 1;

            // Coloca-o na lista de resultados
            rolagemCompleta.resultados.add(resultado);

            //Soma-o no resultado final
            rolagemCompleta.resultadoFinal += resultado;
        }

        // Soma o modificador no final
        rolagemCompleta.resultadoFinal += modificador;
        
        return rolagemCompleta;
    }

    public static Rolagem rolarTeste(int qtDados, int modificador) {
        Random randomGen = new Random();
        Rolagem rolagemCompleta = new Rolagem();
        int valorFinal;

        // Se forem mais de "0" dados, jogar a quantidade e pegar o maior
        if (qtDados > 0) {
            valorFinal = Integer.MIN_VALUE;
            for (int  i = 0; i < qtDados; i++) {
                // Gera um numero de 1 ate 20
                int resultado = randomGen.nextInt(20) + 1;

                // Coloca-o na lista de resultados
                rolagemCompleta.resultados.add(resultado);

                // Atualiza o maior
                if (resultado > valorFinal) {
                    valorFinal = resultado;
                }
            }

            // Seleciona o maior valor para o resultado final
            rolagemCompleta.resultadoFinal = valorFinal;
        } else {
            // Se não, jogar 2+qtDados e pegar o menor
            valorFinal = Integer.MAX_VALUE;

            for (int  i = 0; i < qtDados+2; i++) {
                // Gera um numero de 1 ate 20
                int resultado = randomGen.nextInt(20) + 1;

                // Coloca-o na lista de resultados
                rolagemCompleta.resultados.add(resultado);

                // Atualiza o menor
                if (resultado < valorFinal) {
                    valorFinal = resultado;
                }
            }

            // Seleciona o maior valor para o resultado final
            rolagemCompleta.resultadoFinal = valorFinal;
        }
                
        // Soma o modificador no final
        rolagemCompleta.resultadoFinal += modificador;
        return rolagemCompleta;
    }


    public ArrayList<Integer> getResultados() {
        // Retorna uma copia dos resultados
        return new ArrayList<Integer>(this.resultados);
    }

    public int getResultadoFinal() {
        return this.resultadoFinal;
    }


}
