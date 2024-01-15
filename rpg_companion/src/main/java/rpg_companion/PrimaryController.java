package rpg_companion;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seres.Atributo;
import seres.Pericia;
import seres.ameacas.Ameaca;
import seres.personagens.Classe;
import seres.personagens.Personagem;

import java.io.IOException;

import gerenciador.GerenciadorSessao;

public class PrimaryController implements Initializable {

    private GerenciadorSessao gerenciador = new GerenciadorSessao();

    @FXML
    private TabPane tabelaPrincipal; // Tabs no topo da tela

    @FXML
    private VBox personagemArea;

    @FXML
    private HBox personagensBox; // HBox para conter os botões dos personagens

    @FXML
    private VBox tabContent; // VBox para conter o conteúdo das abas dos personagens

    private String[] tiposDeSer = {"Personagem", "Criatura"};

    // Seleciona se vai ser criado personagem ou criatura
    @FXML
    private ChoiceBox<String> seletorTipoChoiceBox;

    @FXML
    private Label seletorTipoLabel;

        
    // Inputs de texto do criador de personagem

    @FXML
    private TextField nomePersonagemCriadorTextField;

    @FXML
    private ComboBox<Classe> classePersonagemCriadorComboBox;

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

        // Criador de persnagem ou criatura
        seletorTipoChoiceBox.getItems().addAll(tiposDeSer);
        seletorTipoChoiceBox.setOnAction(this::getTipoDeSer);
        seletorTipoChoiceBox.setValue(tiposDeSer[0]);

        seletorTipoChoiceBox.setOnAction(event -> {
            if (this.seletorTipoChoiceBox.getValue() == "Criatura") {
                this.classePersonagemCriadorComboBox.setVisible(false);
            } else {
                this.classePersonagemCriadorComboBox.setVisible(true);
            }
        });

        // Seletor de classe para o personagem
        ArrayList<Classe> opcoesClasse = new ArrayList<Classe>();
        for (Classe classe : Classe.values()) {
            opcoesClasse.add(classe);
        }
        this.classePersonagemCriadorComboBox.setItems(FXCollections.observableList(opcoesClasse));
        this.classePersonagemCriadorComboBox.setValue(Classe.Combatente);
    }
   


    @FXML
    private void adicionaSer() {
        // Cria um ser temporário, utilizando os valores nas caixas de texto, e insere no gerenciador
        // Então cria uma nova tab para o ser
        final boolean personagemSelecionado = this.seletorTipoChoiceBox.getValue() == "Personagem";
        // TODO: Refatorar isso para usar SerArea<Personagem> ou SerArea<Ameaca>
        if (personagemSelecionado) {
            Personagem personagemTemporario = new Personagem(nomePersonagemCriadorTextField.getText(), classePersonagemCriadorComboBox.getValue());
            PersonagemArea personagemTemporarioControler = new PersonagemArea();
            
            gerenciador.adicionaSer(personagemTemporario);
            personagemTemporarioControler.setup(personagemTemporario);

            this.personagemArea.getChildren().addAll(personagemTemporarioControler);
            Tab tab = new Tab(nomePersonagemCriadorTextField.getText(), personagemTemporarioControler);
            tab.setOnSelectionChanged(event -> {
                personagemTemporarioControler.atualizarTextoRolagens();
            });
            tabelaPrincipal.getTabs().add(tabelaPrincipal.getTabs().size() - 1, tab);
        } else {
            Ameaca ameacaTemporaria = new Ameaca(nomePersonagemCriadorTextField.getText());
            AmeacaArea ameacaTemporariaArea = new AmeacaArea();
            
            gerenciador.adicionaSer(ameacaTemporaria);
            ameacaTemporariaArea.setup(ameacaTemporaria);
            
            this.personagemArea.getChildren().addAll(ameacaTemporariaArea);
            Tab tab = new Tab(nomePersonagemCriadorTextField.getText(), ameacaTemporariaArea);
            tab.setOnSelectionChanged(event -> {
                ameacaTemporariaArea.atualizarTextoRolagens();
            });
            tabelaPrincipal.getTabs().add(tabelaPrincipal.getTabs().size() - 1, tab);

        }




    }

    public void getTipoDeSer(ActionEvent event){
        String tipoDeSer = seletorTipoChoiceBox.getValue();
        seletorTipoLabel.setText(tipoDeSer);
    }

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

}