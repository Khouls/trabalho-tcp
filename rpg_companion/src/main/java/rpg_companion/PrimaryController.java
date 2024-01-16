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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import seres.Pericia;
import seres.Ser;
import seres.ameacas.Ameaca;
import seres.personagens.Classe;
import seres.personagens.Personagem;

import java.io.IOException;

import gerenciador.GerenciadorSessao;

public class PrimaryController implements Initializable {

    private GerenciadorSessao gerenciador;

    @FXML
    private TextArea textAreaHistorico;

    @FXML
    private TextArea textAreaAnotacoes;

    @FXML
    private TabPane tabelaPrincipal; // Tabs no topo da tela

    private enum tiposDeSer {Personagem, Ameaça};

    // Seleciona se vai ser criado personagem ou criatura
    @FXML
    private ChoiceBox<tiposDeSer> seletorTipoChoiceBox;

    @FXML
    private Label seletorTipoLabel;

    // Inputs de texto do criador de personagem

    @FXML
    private TextField nomePersonagemCriadorTextField;

    @FXML
    private ComboBox<Classe> classePersonagemCriadorComboBox;

     @FXML
    private FlowPane cardMiniFlowPane;

    @FXML @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gerenciador = new GerenciadorSessao();
        // Criador de persnagem ou criatura
        seletorTipoChoiceBox.getItems().addAll(tiposDeSer.values());
        seletorTipoChoiceBox.setOnAction(this::getTipoDeSer);
        seletorTipoChoiceBox.setValue(tiposDeSer.Personagem);

        seletorTipoChoiceBox.setOnAction(event -> {
            if (this.seletorTipoChoiceBox.getValue().equals(tiposDeSer.Ameaça)) {
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
        final boolean personagemSelecionado = this.seletorTipoChoiceBox.getValue().equals(tiposDeSer.Personagem);
        // TODO: Refatorar isso para usar SerArea<Personagem> ou SerArea<Ameaca>
        Ser serTemporario;

        if (personagemSelecionado) {
            serTemporario = new Personagem(nomePersonagemCriadorTextField.getText(), classePersonagemCriadorComboBox.getValue());
            PersonagemArea personagemTemporarioControler = new PersonagemArea();
            
            personagemTemporarioControler.setup((Personagem)serTemporario);

            Tab tab = new Tab(nomePersonagemCriadorTextField.getText(), personagemTemporarioControler);
            tab.setOnSelectionChanged(event -> {
                personagemTemporarioControler.atualizarTextoRolagens();
            });
            tabelaPrincipal.getTabs().add(tabelaPrincipal.getTabs().size() - 1, tab);

        } else {
            serTemporario = new Ameaca(nomePersonagemCriadorTextField.getText());
            AmeacaArea ameacaTemporariaArea = new AmeacaArea();
            
            ameacaTemporariaArea.setup((Ameaca) serTemporario);
            
            Tab tab = new Tab(nomePersonagemCriadorTextField.getText(), ameacaTemporariaArea);
            tab.setOnSelectionChanged(event -> {
                ameacaTemporariaArea.atualizarTextoRolagens();
            });
            tabelaPrincipal.getTabs().add(tabelaPrincipal.getTabs().size() - 1, tab);
        }
        
        SerAreaMini tempMiniController = new SerAreaMini();
        tempMiniController.setup(serTemporario, event -> {
            int index = tempMiniController.getIndex();
            // Remover do gerenciador
            this.gerenciador.removeSer(index);

            // Remover das visao geral
            this.cardMiniFlowPane.getChildren().remove(index);

            // Remover das abas (+1 por causa do main)
            tabelaPrincipal.getTabs().remove(index+1);
    
            atualizarIndicesFlowPane();
        });
        
        this.cardMiniFlowPane.getChildren().addAll(tempMiniController);
        gerenciador.adicionaSer(serTemporario);
        atualizarIndicesFlowPane();
    }


    public void atualizarIndicesFlowPane() {
        for (int index = 0; index < cardMiniFlowPane.getChildren().size(); index++) {
            SerAreaMini cardMini = (SerAreaMini) cardMiniFlowPane.getChildren().get(index);
            cardMini.setIndex(index);
        }
    }

    @FXML
    public void updateMainPage(){
        for (Node node : cardMiniFlowPane.getChildren()) {
            SerAreaMini cardMini = (SerAreaMini) node;
            cardMini.atualizaMini();
        }
        this.textAreaHistorico.setText(Ser.getHistoricoRolagens());
    }

    public void getTipoDeSer(ActionEvent event){
        String tipoDeSer = seletorTipoChoiceBox.getValue().toString();
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

    @FXML
    private void atualizarAnotacoes() {
        this.gerenciador.setAnotacoes(textAreaAnotacoes.getText());
    }

}