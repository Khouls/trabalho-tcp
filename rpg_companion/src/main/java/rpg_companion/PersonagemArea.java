package rpg_companion;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import java.security.InvalidKeyException;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import seres.personagens.Classe;
import seres.personagens.Item;
import seres.personagens.Personagem;
import seres.personagens.Poder;

public class PersonagemArea extends VBox {
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

    @FXML
    private VBox areaPericias;

    @FXML
    private FlowPane areaItens;

    @FXML
    private Button botaoNovoItem;

    @FXML
    private FlowPane areaPoderes; 

    @FXML
    private Button botaoNovoPoder;
    
    @FXML
    private TextArea textareaRolagens;

    @FXML
    private TextField campoRolagemCustom;

    @FXML 
    private Button botaoRolagemCustom;

    public PersonagemArea() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonagemArea.fxml"));
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
    public void setup(Personagem personagem) {
        this.personagem = personagem;
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

  
        // Setar Recursos
        conectarSpinnersRecurso(spinnerVidaAtual, spinnerVidaMax, barraVida, this.personagem.getPontosVida());

        conectarSpinnersRecurso(spinnerSanidadeAtual, spinnerSanidadeMax, barraSanidade, this.personagem.getPontosSanidade());
        
        conectarSpinnersRecurso(spinnerEsforçoAtual, spinnerEsforçoMax, barraEsforço, this.personagem.getPontosEsforço());

        this.atualizarSpinnersRecursos();

        // Setar Defesa
        this.campoDefesa.setText(String.valueOf(this.personagem.getDefesa()));
        this.campoDefesa.setOnKeyTyped(event -> {
            int newValor;
            try {
                newValor = Integer.parseInt(this.campoDefesa.getText());
                this.personagem.setDefesa(newValor);
                this.atualizarSpinnersRecursos();
            } catch (Exception e) {
                campoDefesa.setText(String.valueOf(this.personagem.getDefesa()));
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
        for (Pericia pericia : this.personagem.getPericias().keySet()) {
           this.areaPericias.getChildren().add(criarContainerPericia(pericia));
        }

        // Setar o botão de adicionar Item
        this.botaoNovoItem.setOnMouseClicked(event -> {
            adicionarItem(new Item());
        });

        // Setar o botão de adicionar Item
        this.botaoNovoPoder.setOnMouseClicked(event -> {
            adicionarPoder(new Poder());
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
                    this.personagem.rodarDados(numFaces, qtDados, modificador);
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
        campo.setText(this.personagem.getAtributos().get(atributo).toString());
        campo.setOnKeyTyped(event -> {
            int newValor;
            try {
                newValor = Integer.parseInt(campo.getText());
                this.personagem.setAtributo(atributo, newValor);
                this.atualizarSpinnersRecursos();
            } catch (Exception e) {
                campo.setText(this.personagem.getAtributos().get(atributo).toString());
            }

        });
    }

    private void atualizarSpinnersRecursos() {
        this.spinnerVidaAtual.getValueFactory().setValue(this.personagem.getPontosVida().getValorAtual());

        this.spinnerVidaMax.getValueFactory().setValue(this.personagem.getPontosVida().getValorMaximo());
        
        this.spinnerSanidadeAtual.getValueFactory().setValue(this.personagem.getPontosSanidade().getValorAtual());
        
        this.spinnerSanidadeMax.getValueFactory().setValue(this.personagem.getPontosSanidade().getValorMaximo());

        this.spinnerEsforçoAtual.getValueFactory().setValue(this.personagem.getPontosEsforço().getValorAtual());
        
        this.spinnerEsforçoMax.getValueFactory().setValue(this.personagem.getPontosEsforço().getValorMaximo());

    }

    private PericiaBox criarContainerPericia(Pericia pericia) {
        PericiaBox boxPericia = new PericiaBox();
        boxPericia.setup(this.personagem, pericia, event -> {
            try {
                this.personagem.fazerTeste(pericia);
                textareaRolagens.setText(Ser.getHistoricoRolagens());
            } catch (InvalidKeyException e) {
                System.out.println("Pericia Inválida:" + pericia);
            }
        });

        return boxPericia;
    }

    private void adicionarItem (Item item) {
        ItemCard cardItem = new ItemCard();
        // Adicionar o item no penultimo local
        int index = this.areaItens.getChildren().size() - 1;

        this.personagem.adicionarItem(item);

        cardItem.setup(item, index, event -> {
            this.areaItens.getChildren().remove(cardItem.getIndex());
            this.atualizarIndicesItens();
            this.personagem.removerItem(cardItem.getIndex());
        });

        this.areaItens.getChildren().add(index, cardItem);        
    }

    private void adicionarPoder (Poder poder) {
        PoderCard cardPoder = new PoderCard();
        // Adicionar o item no penultimo local
        int index = this.areaPoderes.getChildren().size() - 1;

        this.personagem.adicionarPoder(poder);

        cardPoder.setup(poder, index, event -> {
            this.areaPoderes.getChildren().remove(cardPoder.getIndex());
            this.atualizarIndicesPoderes();
            this.personagem.removerPoder(cardPoder.getIndex());
        });

        this.areaPoderes.getChildren().add(index, cardPoder);        
    }

    private void atualizarIndicesItens() {
        // Passar até o penúltimo atualizando os indices
        for (int index = 0; index < areaItens.getChildren().size() - 1; index++) {
            ItemCard cardItem = (ItemCard) areaItens.getChildren().get(index);
            cardItem.setIndex(index);
        }
    }

    private void atualizarIndicesPoderes() {
        // Passar até o penúltimo atualizando os indices
        for (int index = 0; index < areaPoderes.getChildren().size() - 1; index++) {
            PoderCard cardPoder = (PoderCard) areaPoderes.getChildren().get(index);
            cardPoder.setIndex(index);
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
            this.personagem.fazerTeste(Atributo.Força);
            atualizarTextoRolagens();
        });

        this.botaoTesteAgilidade.setOnMouseClicked(event -> {
            this.personagem.fazerTeste(Atributo.Agilidade);
            atualizarTextoRolagens();
        });

        this.botaoTesteIntelecto.setOnMouseClicked(event -> {
            this.personagem.fazerTeste(Atributo.Intelecto);
            atualizarTextoRolagens();
        });

        this.botaoTestePresença.setOnMouseClicked(event -> {
            this.personagem.fazerTeste(Atributo.Presença);
            atualizarTextoRolagens();
        });

        this.botaoTesteVigor.setOnMouseClicked(event -> {
            this.personagem.fazerTeste(Atributo.Vigor);
            atualizarTextoRolagens();
        });
    }
}