package rpg_companion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import java.security.InvalidKeyException;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import seres.Atributo;
import seres.Pericia;
import seres.Recurso;
import seres.Ser;
import seres.ameacas.Ameaca;
import seres.ameacas.Habilidade;
import seres.personagens.Classe;

public class AmeacaArea extends VBox {

    private Ameaca ameaca;

    @FXML
    private TextField campoNome;

    @FXML
    private ProgressBar barraVida;

    @FXML
    private Spinner<Integer> spinnerVidaMax;

    @FXML
    private Spinner<Integer> spinnerVidaAtual;

    @FXML
    private TextField campoForça;

    @FXML
    private Button botaoTesteForça;

    @FXML
    private TextField campoAgilidade;

    @FXML
    private Button botaoTesteAgilidade;

    @FXML
    private TextField campoIntelecto;

    @FXML
    private Button botaoTesteIntelecto;

    @FXML
    private TextField campoPresença;

    @FXML
    private Button botaoTestePresença;

    @FXML
    private TextField campoVigor;

    @FXML
    private Button botaoTesteVigor;

    @FXML
    private TextField campoDefesa;

    @FXML TextField campoValorDificuldade;

    @FXML
    private VBox areaPericias;

    @FXML
    private FlowPane areaHabilidades;

    @FXML
    private Button botaoNovaHabilidade;

    @FXML
    private TextArea textareaRolagens;

    @FXML 
    private TextField campoRolagemCustom;

    @FXML
    private Button botaoRolagemCustom;

    public AmeacaArea() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AmeacaArea.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            this.setVgrow(this, Priority.ALWAYS);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void setup(Ameaca ameaca) {
        this.ameaca = ameaca;
        // Setar nome
        this.campoNome.setText(this.ameaca.getNome());
        this.campoNome.setOnKeyTyped(event -> {
            this.ameaca.setNome(this.campoNome.getText());
        });

        // Setar classe
        ArrayList<Classe> opcoesClasse = new ArrayList<Classe>();
        for (Classe classe : Classe.values()) {
            opcoesClasse.add(classe);
        }
  
        // Setar Recursos
        conectarSpinnersRecurso(spinnerVidaAtual, spinnerVidaMax, barraVida, this.ameaca.getPontosVida());
        this.atualizarSpinnersRecursos();

        // Setar Defesa
        this.campoDefesa.setText(String.valueOf(this.ameaca.getDefesa()));
        this.campoDefesa.setOnKeyTyped(event -> {
            int newValor;
            try {
                newValor = Integer.parseInt(this.campoDefesa.getText());
                this.ameaca.setDefesa(newValor);
                this.atualizarSpinnersRecursos();
            } catch (Exception e) {
                campoDefesa.setText(String.valueOf(this.ameaca.getDefesa()));
            }
        });

        // Setar Defesa
        this.campoValorDificuldade.setText(String.valueOf(this.ameaca.getValorDificuldade()));
        this.campoValorDificuldade.setOnKeyTyped(event -> {
            int newValor;
            try {
                newValor = Integer.parseInt(this.campoValorDificuldade.getText());
                this.ameaca.setValorDificuldade(newValor);
                this.atualizarSpinnersRecursos();
            } catch (Exception e) {
                campoDefesa.setText(String.valueOf(this.ameaca.getValorDificuldade()));
            }
        });

        //Setar os atributos
        conectarCampoAtributo(this.campoForça, Atributo.Força);
        conectarCampoAtributo(this.campoAgilidade, Atributo.Agilidade);
        conectarCampoAtributo(this.campoIntelecto, Atributo.Intelecto);
        conectarCampoAtributo(this.campoPresença, Atributo.Presença);
        conectarCampoAtributo(this.campoVigor, Atributo.Vigor);
        configurarBotoesTestesAtributos();

        // Construir a area de pericias
        for (Pericia pericia : this.ameaca.getPericias().keySet()) {
           this.areaPericias.getChildren().add(criarContainerPericia(pericia));
        }

        // Setar o botão de adicionar Item
        this.botaoNovaHabilidade.setOnMouseClicked(event -> {
            adicionarHabilidade(new Habilidade());
        });


        // Setar area de rolagem customizada
        // Adicionar o icone ao botão
        Image imagemDado = new Image(getClass().getResource("icons/d20.png").toExternalForm());
        ImageView iconeBotao = new ImageView(imagemDado);
        iconeBotao.setFitHeight(17.0);
        iconeBotao.setPreserveRatio(true);
        this.botaoRolagemCustom.setGraphic(iconeBotao);

        this.botaoRolagemCustom.setOnMouseClicked(event -> {
            // Parserar rolagem
            boolean rolagemOK = true;
            String textoRolagem = this.campoRolagemCustom.getText();

            try {
                String[] separacaoQtDados = textoRolagem.split("d");
                // Verificar se so foi colocado um numero de dados
                rolagemOK = separacaoQtDados.length == 2;

                // Ex: 2d10+2
                // [2]d10+20
    
                int qtDados = Integer.parseInt(separacaoQtDados[0]);
                String[] separacaoModificadores = separacaoQtDados[1].split("\\x2B");

                // Primeiro valor vai ser a quantidade de faces
                // 2d[10]+20
                int numFaces = Integer.parseInt(separacaoModificadores[0]);

                // O proximo vai ser o modificador
                // 2d10+[20]
                int modificador = Integer.parseInt(separacaoModificadores[1]);

                if (rolagemOK) {
                    this.ameaca.rodarDados(numFaces, qtDados, modificador);
                    this.atualizarTextoRolagens();
                } else {
                    System.out.println("Rolagem mal formada: " + textoRolagem);
                }
                
            } catch (Exception e) {
                System.out.println("Rolagem mal formada: " + textoRolagem);
                e.printStackTrace();
            }
        });
    }

    private void conectarSpinnersRecurso(Spinner<Integer> spinnerValorAtual, Spinner<Integer> spinnerValorMaximo, ProgressBar barraRecurso, Recurso recurso) {
        spinnerValorAtual.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-999, 999, 0));

        spinnerValorAtual.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            recurso.setValorAtual(Integer.parseInt(newValue));
            barraRecurso.setProgress(recurso.getProporçao());
        });

        spinnerValorMaximo.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-999, 999, 0));

        spinnerValorMaximo.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            recurso.setValorMaximo(Integer.parseInt(newValue));
            barraRecurso.setProgress(recurso.getProporçao());
        });

        barraRecurso.setProgress(recurso.getProporçao());

    }

    private void conectarCampoAtributo(TextField campo, Atributo atributo) {
        campo.setText(this.ameaca.getAtributos().get(atributo).toString());
        campo.setOnKeyTyped(event -> {
            int newValor;
            try {
                newValor = Integer.parseInt(campo.getText());
                this.ameaca.setAtributo(atributo, newValor);
                this.atualizarSpinnersRecursos();
            } catch (Exception e) {
                campo.setText(this.ameaca.getAtributos().get(atributo).toString());
            }

        });
    }

    private void atualizarSpinnersRecursos() {
        this.spinnerVidaAtual.getValueFactory().setValue(this.ameaca.getPontosVida().getValorAtual());

        this.spinnerVidaMax.getValueFactory().setValue(this.ameaca.getPontosVida().getValorMaximo());
    }

    private PericiaBox criarContainerPericia(Pericia pericia) {
        PericiaBox boxPericia = new PericiaBox();
        boxPericia.setup(this.ameaca, pericia, event -> {
            try {
                this.ameaca.fazerTeste(pericia);
                textareaRolagens.setText(Ser.getHistoricoRolagens());
            } catch (InvalidKeyException e) {
                System.out.println("Pericia Inválida:" + pericia);
            }
        });

        return boxPericia;
    }

    private void adicionarHabilidade (Habilidade habilidade) {
        HabilidadeCard cardHabilidade = new HabilidadeCard();
        // Adicionar a habilidade penultimo local
        int index = this.areaHabilidades.getChildren().size() - 1;

        this.ameaca.adicionarHabilidade(habilidade);

        cardHabilidade.setup(habilidade, index, event -> {
            this.areaHabilidades.getChildren().remove(cardHabilidade.getIndex());
            this.atualizarIndicesHabilidades();
            this.ameaca.removerHabilidade(cardHabilidade.getIndex());
        });

        this.areaHabilidades.getChildren().add(index, cardHabilidade);        
    }

    private void atualizarIndicesHabilidades() {
        // Passar até o penúltimo atualizando os indices
        for (int index = 0; index < areaHabilidades.getChildren().size() - 1; index++) {
            ItemCard cardItem = (ItemCard) areaHabilidades.getChildren().get(index);
            cardItem.setIndex(index);
        }
    }

    public void atualizarTextoRolagens() {
        this.textareaRolagens.setText(Ser.getHistoricoRolagens());
    }

    private ImageView importarIconeBotao(double tamanho) {
        Image imagemDado = new Image(getClass().getResource("icons/d20.png").toExternalForm());
        ImageView iconeBotao = new ImageView(imagemDado);
        iconeBotao.setFitHeight(tamanho);
        iconeBotao.setPreserveRatio(true);

        return iconeBotao;
    }

    public void configurarBotoesTestesAtributos() {
        final double tamanhoIcone = 17.0;
        
        this.botaoTesteForça.setGraphic(importarIconeBotao(tamanhoIcone));
        this.botaoTesteAgilidade.setGraphic(importarIconeBotao(tamanhoIcone));
        this.botaoTesteIntelecto.setGraphic(importarIconeBotao(tamanhoIcone));
        this.botaoTestePresença.setGraphic(importarIconeBotao(tamanhoIcone));
        this.botaoTesteVigor.setGraphic(importarIconeBotao(tamanhoIcone));

        this.botaoTesteForça.setOnMouseClicked(event -> {
            this.ameaca.fazerTeste(Atributo.Força);
            atualizarTextoRolagens();
        });

        this.botaoTesteAgilidade.setOnMouseClicked(event -> {
            this.ameaca.fazerTeste(Atributo.Agilidade);
            atualizarTextoRolagens();
        });

        this.botaoTesteIntelecto.setOnMouseClicked(event -> {
            this.ameaca.fazerTeste(Atributo.Intelecto);
            atualizarTextoRolagens();
        });

        this.botaoTestePresença.setOnMouseClicked(event -> {
            this.ameaca.fazerTeste(Atributo.Presença);
            atualizarTextoRolagens();
        });

        this.botaoTesteVigor.setOnMouseClicked(event -> {
            this.ameaca.fazerTeste(Atributo.Vigor);
            atualizarTextoRolagens();
        });
    }
}