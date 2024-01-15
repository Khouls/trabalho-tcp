package gerenciador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seres.ameacas.Ameaca;
import seres.personagens.Classe;
import seres.personagens.Personagem;


public class TesteGerenciador {
    private GerenciadorSessao gerenciador;

    @BeforeEach
    // Cria um gerenciador vazio
    public void inicializarGerenciador() {
        this.gerenciador = new GerenciadorSessao();
    }

    @Test
    // Testa adicionar um personagem, mudar seu nome e classe e verificar se a mudança foi propagada
    void testePropagarMudancasPorDentro() {
        Personagem personagem = new Personagem("John Paranormal", Classe.Ocultista);
        this.gerenciador.adicionaSer(personagem);

        personagem.setNome("John Lutador");
        personagem.setClasse(Classe.Combatente);

        // Garante que o personagem foi adicionado
        assertEquals(this.gerenciador.getSeres().size(), 1);

        // Garante que as mudanças foram propagadas
        assertEquals(this.gerenciador.getSer(0).getNome(), personagem.getNome());
        assertEquals(((Personagem)this.gerenciador.getSer(0)).getClasse(), personagem.getClasse());
    }

    @Test
    // Adiciona um personagem ao gerenciador, copia ele externamente e verifica se as mudancas foram propagadas
    void testePropagarMudancasPorFora() {
        Personagem personagem = new Personagem("John Paranormal", Classe.Ocultista);

        this.gerenciador.adicionaSer(personagem);

        Personagem personagemCopia = (Personagem) this.gerenciador.getSer(0);

        personagemCopia.setNome("John Lutador");
        personagemCopia.setClasse(Classe.Combatente);


        // Garante que o personagem foi adicionado
        assertEquals(this.gerenciador.getSeres().size(), 1);

        // Garante que as mudanças foram propagadas
        assertEquals(this.gerenciador.getSer(0).getNome(), personagemCopia.getNome());
        assertEquals(((Personagem)this.gerenciador.getSer(0)).getClasse(), personagemCopia.getClasse());
    }

    @Test
    // Testa adicionar um personagens e ameacas, remover algumas e verificar se as outras estao intactas
    void testeAdicionarERemover() {
        Personagem personagem1 = new Personagem("John Paranormal", Classe.Ocultista);
        this.gerenciador.adicionaSer(personagem1);

        Ameaca ameaca1 = new Ameaca("Zumbi de Sangue");
        this.gerenciador.adicionaSer(ameaca1);

        Personagem personagem2 = new Personagem("John Lutador", Classe.Ocultista);
        this.gerenciador.adicionaSer(personagem2);

        Ameaca ameaca2 = new Ameaca("Aniquilação");
        this.gerenciador.adicionaSer(ameaca2);
        
        // Garante que os seres foram adicionados
        assertEquals(this.gerenciador.getSeres().size(), 4);

        // Remove alguns seres
        this.gerenciador.removeSer(1);
        this.gerenciador.removeSer(1);

        // Garante que os outros estao intactos
        assertEquals(this.gerenciador.getSeres().size(), 2);
        assertEquals(this.gerenciador.getSer(0).getNome(), personagem1.getNome());
        assertEquals(this.gerenciador.getSer(1).getNome(), ameaca2.getNome());

    }
}
