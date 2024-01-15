package rpg_companion;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import java.security.InvalidKeyException;
import java.util.ArrayList;


import javafx.geometry.Pos;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import seres.Atributo;
import seres.Pericia;
import seres.Recurso;
import seres.Ser;
import seres.personagens.Classe;
import seres.personagens.Item;
import seres.personagens.Personagem;
import seres.personagens.Poder;

public class PersonagemAreaMini extends VBox {
    private static final int ALTURA_CONTAINER = 43;

    private Personagem personagem;

    @FXML
    private TextField nomePersonagemMiniLabel;

    @FXML
    private ProgressBar vidaMiniBarra;


    @FXML
    private ProgressBar esforcoMiniBarra;

    
    @FXML
    private ProgressBar sanidadeMiniBarra;





    public PersonagemAreaMini() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonagemAreaMini.fxml"));
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
        this.nomePersonagemMiniLabel.setText(this.personagem.getNome());

        // Setar Recursos
        //conectarSpinnersRecurso(spinnerVidaAtual, spinnerVidaMax, barraVida, this.personagem.getPontosVida());

        //conectarSpinnersRecurso(spinnerSanidadeAtual, spinnerSanidadeMax, barraSanidade, this.personagem.getPontosSanidade());
        
        //conectarSpinnersRecurso(spinnerEsforçoAtual, spinnerEsforçoMax, barraEsforço, this.personagem.getPontosEsforço());

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
            barraRecurso.setProgress(this.personagem.getPontosEsforço().getProporçao());
        });

        barraRecurso.setProgress(recurso.getProporçao());

    }
    

     /* 
    private void atualizarSpinnersRecursos() {
        this.spinnerVidaAtual.getValueFactory().setValue(this.personagem.getPontosVida().getValorAtual());

        this.spinnerVidaMax.getValueFactory().setValue(this.personagem.getPontosVida().getValorMaximo());
        
        this.spinnerSanidadeAtual.getValueFactory().setValue(this.personagem.getPontosSanidade().getValorAtual());
        
        this.spinnerSanidadeMax.getValueFactory().setValue(this.personagem.getPontosSanidade().getValorMaximo());

        this.spinnerEsforçoAtual.getValueFactory().setValue(this.personagem.getPontosEsforço().getValorAtual());
        
        this.spinnerEsforçoMax.getValueFactory().setValue(this.personagem.getPontosEsforço().getValorMaximo());

    } */







}