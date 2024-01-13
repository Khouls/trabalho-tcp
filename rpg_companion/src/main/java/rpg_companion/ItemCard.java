package rpg_companion;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.function.Function;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import seres.personagens.Item;

public class ItemCard extends VBox{

    private Item item;

    private int index;

    @FXML
    private Spinner<Integer> spinnerPeso;

    @FXML
    private Spinner<Integer> spinnerCategoria;

    @FXML
    private Button botãoDeletar;

    @FXML
    private TextField campoNome;

    @FXML
    private TextArea campoDescrição;

    public ItemCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void setup(Item item, int index, EventHandler<? super MouseEvent> funçãoDeletar) {
        this.item = item;
        this.index = index;

        // Setar nome
        this.campoNome.setText(this.item.getNome());
        this.campoNome.setOnKeyTyped(event -> {
            this.item.setNome(this.campoNome.getText());
        });

        // Setar descrição
        this.campoDescrição.setText(this.item.getDescricao());
        this.campoDescrição.setOnKeyTyped(event -> {
            this.item.setDescrição(this.campoDescrição.getText());
        });
        
        // Setar categoria
        this.spinnerCategoria.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, this.item.getCategoria()));

        spinnerCategoria.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            this.item.setCategoria(Integer.parseInt(newValue));
        });
        

        // Setar peso
        this.spinnerPeso.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, this.item.getPeso()));

        spinnerCategoria.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            this.item.setPeso(Integer.parseInt(newValue));
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
