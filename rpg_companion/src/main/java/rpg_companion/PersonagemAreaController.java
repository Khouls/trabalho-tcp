package rpg_companion;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import seres.Atributo;
import seres.Pericia;
import seres.Ser;
import seres.personagens.Classe;
import seres.personagens.Personagem;

import gerenciador.GerenciadorSessao;

public class PersonagemAreaController extends Pane implements Initializable {
    private static final int ALTURA_CONTAINER = 43;

    private Personagem personagem;

    @FXML
    private TextField campoNome;

    @FXML
    private Spinner<Integer> spinnerNex;

    @FXML
    private ComboBox<Classe> comboBoxClasse;

    @FXML
    private ProgressBar barraVida;

    @FXML
    private Spinner<Integer> spinnerVidaMax;

    @FXML
    private Spinner<Integer> spinnerVidaAtual;

    @FXML
    private ProgressBar barraEsforço;

    @FXML
    private Spinner<Integer> spinnerEsforçoMax;

    @FXML
    private Spinner<Integer> spinnerEsforçoAtual;
    
    @FXML
    private ProgressBar barraSanidade;

    @FXML
    private Spinner<Integer> spinnerSanidadeMax;

    @FXML
    private Spinner<Integer> spinnerSanidadeAtual;

    @FXML
    private TextField campoForça;

    @FXML
    private TextField campoAgilidade;

    @FXML
    private TextField campoIntelecto;

    @FXML
    private TextField campoPresença;

    @FXML
    private TextField campoVigor;

    @FXML
    private TextField campoDefesa;

    @FXML
    private VBox areaPericias;
    
    @FXML
    private TextArea textareaRolagens;

    @FXML @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.personagem = new Personagem("John Paranormal", Classe.Ocultista);
        this.personagem.setAtributo(Atributo.Intelecto, 20);
        this.personagem.setPericia(Pericia.Ocultismo, 69);
        this.personagem.setPericia(Pericia.Ciências, -10);

        // Setar nome
        this.campoNome.setText(this.personagem.getNome());
        this.campoNome.setOnKeyTyped(event -> {
            this.personagem.setNome(this.campoNome.getText());
        });

        // Setar classe
        ArrayList<Classe> opcoesClasse = new ArrayList<Classe>();
        for (Classe classe : Classe.values()) {
            opcoesClasse.add(classe);
        }
        this.comboBoxClasse.setItems(FXCollections.observableList(opcoesClasse));
        this.comboBoxClasse.setValue(this.personagem.getClasse());
        this.comboBoxClasse.setOnAction(event -> {
            this.personagem.setClasse(this.comboBoxClasse.getValue());
            this.atualizarSpinnersRecursos();
        });

        // Setar NEX
        this.spinnerNex.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99, 5));
        this.spinnerNex.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            this.personagem.setNivelExposicao(Integer.parseInt(newValue));
            this.atualizarSpinnersRecursos();
        });

        // Setar Vida
        this.spinnerVidaAtual.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-999, 999, 0));

        this.spinnerVidaAtual.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            this.personagem.getPontosVida().setValorAtual(Integer.parseInt(newValue));
            this.barraVida.setProgress(this.personagem.getPontosVida().getProporçao());
        });

        this.spinnerVidaMax.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-999, 999, 0));

        this.spinnerVidaMax.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            this.personagem.getPontosVida().setValorMaximo(Integer.parseInt(newValue));
            this.barraVida.setProgress(this.personagem.getPontosVida().getProporçao());
        });

        this.barraVida.setProgress(this.personagem.getPontosVida().getProporçao());

        // Setar Sanidade
        this.spinnerSanidadeAtual.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-999, 999, 0));

        this.spinnerSanidadeAtual.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            this.personagem.getPontosSanidade().setValorAtual(Integer.parseInt(newValue));
            this.barraSanidade.setProgress(this.personagem.getPontosSanidade().getProporçao());
        });

        this.spinnerSanidadeMax.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-999, 999, 0));

        this.spinnerSanidadeMax.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            this.personagem.getPontosSanidade().setValorMaximo(Integer.parseInt(newValue));
            this.barraSanidade.setProgress(this.personagem.getPontosSanidade().getProporçao());
        });

        this.barraSanidade.setProgress(this.personagem.getPontosSanidade().getProporçao());

        // Setar Esforco
        this.spinnerEsforçoAtual.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-999, 999, 0));

        this.spinnerEsforçoAtual.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            this.personagem.getPontosEsforço().setValorAtual(Integer.parseInt(newValue));
            this.barraEsforço.setProgress(this.personagem.getPontosEsforço().getProporçao());
        });

        this.spinnerEsforçoMax.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-999, 999, 0));

        this.spinnerEsforçoMax.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            this.personagem.getPontosEsforço().setValorMaximo(Integer.parseInt(newValue));
            this.barraEsforço.setProgress(this.personagem.getPontosEsforço().getProporçao());
        });

        this.barraEsforço.setProgress(this.personagem.getPontosEsforço().getProporçao());

        this.atualizarSpinnersRecursos();
        
        // Construir a area de pericias
        for (Pericia pericia : this.personagem.getPericias().keySet()) {
            this.areaPericias.getChildren().add(criarContainerPericia(pericia));
        }
    }

    private void atualizarSpinnersRecursos() {
        this.spinnerVidaAtual.getValueFactory().setValue(this.personagem.getPontosVida().getValorAtual());

        this.spinnerVidaMax.getValueFactory().setValue(this.personagem.getPontosVida().getValorMaximo());
        
        this.spinnerSanidadeAtual.getValueFactory().setValue(this.personagem.getPontosSanidade().getValorAtual());
        
        this.spinnerSanidadeMax.getValueFactory().setValue(this.personagem.getPontosSanidade().getValorMaximo());

        this.spinnerEsforçoAtual.getValueFactory().setValue(this.personagem.getPontosEsforço().getValorAtual());
        
        this.spinnerEsforçoMax.getValueFactory().setValue(this.personagem.getPontosEsforço().getValorMaximo());

    }

    private HBox criarContainerPericia(Pericia pericia) {
        HBox container = new HBox();
        container.setPrefHeight(ALTURA_CONTAINER);
        container.setPrefWidth(200.0);
        container.setSpacing(10.0);
        container.getStylesheets().add("file:stylesheet.css");
        container.getStyleClass().add("background");
        container.getStyleClass().add("text-foreground");
        container.setAlignment(Pos.CENTER_LEFT);

        Text nome = new Text(pericia.toString());
        nome.setFont(new Font(14.0));
        nome.setFill(Color.valueOf("#f0f0f0"));

        Spinner<Integer> spinnerModificador = new Spinner<Integer>(-100, 100, this.personagem.getPericias().get(pericia));
        spinnerModificador.setEditable(true);
        spinnerModificador.setPrefHeight(25.0);
        spinnerModificador.setPrefWidth(68.0);
        spinnerModificador.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            this.personagem.setPericia(pericia, Integer.parseInt(newValue));
        });

        Button botaoFazerRolagem = new Button();
        botaoFazerRolagem.setPrefHeight(34.0);
        botaoFazerRolagem.setPrefWidth(32.0);
        botaoFazerRolagem.getStylesheets().add("file:stylesheet.css");
        botaoFazerRolagem.getStyleClass().add("background");
        botaoFazerRolagem.setOnMouseClicked(event -> {
            try {
                this.personagem.fazerTeste(pericia);
                textareaRolagens.setText(Ser.getHistoricoRolagens());
            } catch (InvalidKeyException e) {
                System.out.println("Pericia Inválida:" + pericia);
            }
        });
        Image imagemDado = new Image("file:./src/main/resources/icons/d20.png");
        ImageView iconeBotao = new ImageView(imagemDado);
        iconeBotao.setFitHeight(36.0);
        iconeBotao.setPreserveRatio(true);
        botaoFazerRolagem.setGraphic(iconeBotao);
        
        container.getChildren().addAll(nome, spinnerModificador, botaoFazerRolagem);

        return container;
    }  
}