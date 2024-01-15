package rpg_companion;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import java.security.InvalidKeyException;
import java.util.ArrayList;

import gerenciador.Rolagem;
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
import javafx.scene.control.Label;
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
    private Label nomePersonagemMiniLabel;

    @FXML
    private ProgressBar vidaMiniBarra;


    @FXML
    private ProgressBar sanidadeMiniBarra;

    @FXML
    private ProgressBar esforcoMiniBarra;





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
        this.vidaMiniBarra.setProgress(personagem.getPontosVida().getProporçao());

    }


    public void atualizaMini(){
        this.nomePersonagemMiniLabel.setText(this.personagem.getNome());
        this.vidaMiniBarra.setProgress(this.personagem.getPontosVida().getProporçao());
        this.sanidadeMiniBarra.setProgress(this.personagem.getPontosSanidade().getProporçao());
        this.esforcoMiniBarra.setProgress(this.personagem.getPontosEsforço().getProporçao());
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