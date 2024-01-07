package rpg_companion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seres.personagens.Personagem;

import java.io.IOException;

public class PrimaryController {


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