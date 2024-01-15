package seres;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.InvalidKeyException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gerenciador.Rolagem;
import seres.Atributo;
import seres.Pericia;


public class TesteRecurso {
    private Recurso recurso;

    @BeforeEach
    // Cria um recurso para testes
    public void inicializarRecurso() {
        this.recurso = new Recurso(10);        
    }

    @Test
    // Testa a função de encher o recurso e verifica se esta igual ao maximo
    public void testeEnche() {
        this.recurso.enche();

        assertEquals(this.recurso.getValorAtual(), this.recurso.getValorMaximo());
    }

    @Test
    // Testa a função de zerar e verifica se for igual a zero
    public void testeZera() {
        this.recurso.zera();

        assertEquals(this.recurso.getValorAtual(), 0);
    }

    @Test
    // Testa definir dois valores e verificar se a porporcao esta correta (menor que 1)
    public void testeProporçao() {
        this.recurso.setValorAtual(1);
        this.recurso.setValorMaximo(2);

        assertEquals(this.recurso.getProporçao(), ((double) 1) / 2);
    }

    @Test
    // Testa definir dois valores e verificar se a porporcao esta correta (menor que 1)
    public void testeProporcaoMaiorQue1() {
        this.recurso.setValorAtual(2);
        this.recurso.setValorMaximo(1);

        assertEquals(this.recurso.getProporçao(), 1);
    }


}
