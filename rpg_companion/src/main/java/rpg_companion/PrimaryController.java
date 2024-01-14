package rpg_companion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import seres.Atributo;
import seres.Pericia;
import seres.personagens.Classe;
import seres.personagens.Personagem;
import seres.Ser;

import java.io.IOException;

import gerenciador.GerenciadorSessao;

public class PrimaryController implements Initializable {

    private GerenciadorSessao gerenciador = new GerenciadorSessao();

    @FXML @Override
    public void initialize(URL location, ResourceBundle resources) {
        Personagem JohnParanormal = new Personagem("John Paranormal", Classe.Ocultista);
        JohnParanormal.setAtributo(Atributo.Intelecto, 20);
        JohnParanormal.setPericia(Pericia.Ocultismo, 69);
        JohnParanormal.setPericia(Pericia.Ciências, -10);
        gerenciador.adicionaSer(JohnParanormal);

        PersonagemArea paranormalController = new PersonagemArea();

        paranormalController.setup(JohnParanormal);
        
        this.personagemArea.getChildren().addAll(paranormalController);
    }

    @FXML
    private VBox personagemArea;

    @FXML
    private HBox personagensBox; // HBox para conter os botões dos personagens

    @FXML
    private VBox tabContent; // VBox para conter o conteúdo das abas dos personagens

    @FXML
    private TextField nomePersonagemField;

    @FXML
    private TextField classePersonagemField;

    private int contadorPersonagens = 1; // Contador para identificar cada personagem


    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    public void testeBotao() {
        try {
            this.gerenciador.getSeres().get(0).fazerTeste(Pericia.Ocultismo);
            this.gerenciador.getSeres().get(0).fazerTeste(Pericia.Ciências);
        } catch (InvalidKeyException exception) {
            System.out.println("Pericia Invalida");
        }        
    }

    @FXML
    private void secondAction(ActionEvent event) {
        // Lógica a ser executada quando o segundo botão for pressionado
        System.out.println("Segundo botão pressionado!");
        // Adicione aqui o código que deseja executar quando o segundo botão for clicado
    }

    @FXML
    private void adicionarPersonagem(ActionEvent event) {
    /*     // Lógica para adicionar um novo botão de personagem
        String nomeNovoPersonagem = "Novo Personagem " + contadorPersonagens; // Nome do novo personagem (substitua por uma entrada real)

        Button novoBotaoPersonagem = new Button(nomeNovoPersonagem);
        novoBotaoPersonagem.setOnAction(e -> abrirPaginaPersonagem(nomeNovoPersonagem)); // Define a ação do botão

        personagensBox.getChildren().add(novoBotaoPersonagem); // Adiciona o botão ao HBox

        contadorPersonagens++; // Incrementa o contador de personagens*/

        String nomeNovoPersonagem = nomePersonagemField.getText(); // Obter o nome do campo de entrada
        String classeNovoPersonagem = classePersonagemField.getText(); // Obter a classe do campo de entrada
    
        if (!nomeNovoPersonagem.isEmpty() && !classeNovoPersonagem.isEmpty()) {
            // Lógica para adicionar um novo botão de personagem
            Button novoBotaoPersonagem = new Button(nomeNovoPersonagem);
            novoBotaoPersonagem.setOnAction(e -> abrirPaginaPersonagem(nomeNovoPersonagem, classeNovoPersonagem));
    
            personagensBox.getChildren().add(novoBotaoPersonagem); // Adicionar o botão ao HBox
        } else {
            // Exibir uma mensagem de erro se os campos estiverem vazios
            // Você pode exibir uma mensagem de erro ou solicitar novamente ao usuário
            // que insira as informações necessárias
        }
    }
    

    @FXML
    private void abrirPaginaPersonagem(String nomePersonagem, String classePersonagem){
         // Lógica para abrir a página do personagem com base no nome
        
        // Criar um novo conteúdo para a aba do personagem
        Label conteudoAba = new Label("Conteúdo da página do personagem: " + nomePersonagem);

        // Adicionar o conteúdo ao VBox (simulando a troca de abas)
        tabContent.getChildren().clear(); // Limpa o VBox
        tabContent.getChildren().add(conteudoAba); // Adiciona o conteúdo à VBox
    
    }

}