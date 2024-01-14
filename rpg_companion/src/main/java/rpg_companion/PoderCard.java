package rpg_companion;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.function.Function;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import seres.personagens.Classe;
import seres.personagens.Elemento;
import seres.personagens.Poder;

public class PoderCard extends VBox{

    private Poder poder;

    private int index;

    @FXML
    private Button botãoDeletar;

    @FXML
    private TextField campoNome;

    @FXML
    private ComboBox<Elemento> comboBoxElemento;

    @FXML
    private TextArea campoDescrição;

    public PoderCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PoderCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void setup(Poder poder, int index, EventHandler<? super MouseEvent> funçãoDeletar) {
        this.poder = poder;
        this.index = index;

        // Setar nome
        this.campoNome.setText(this.poder.getNome());
        this.campoNome.setOnKeyTyped(event -> {
            this.poder.setNome(this.campoNome.getText());
        });

        // Setar Seletor de Elemento
        ArrayList<Elemento> opcoesElementos = new ArrayList<Elemento>();
        for (Elemento elemento : Elemento.values()) {
            opcoesElementos.add(elemento);
        }
        this.comboBoxElemento.setItems(FXCollections.observableList(opcoesElementos));
        this.comboBoxElemento.setValue(this.poder.getElemento());
        this.comboBoxElemento.setOnAction(event -> {
            this.poder.setElemento(this.comboBoxElemento.getValue());
        });

        // Setar descrição
        this.campoDescrição.setText(this.poder.getDescricao());
        this.campoDescrição.setOnKeyTyped(event -> {
            //this.habilidade.setDescrição(this.campoDescrição.getText());
        });


        this.botãoDeletar.setOnMouseClicked(funçãoDeletar);
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
