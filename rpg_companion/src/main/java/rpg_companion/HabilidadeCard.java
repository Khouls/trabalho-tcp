package rpg_companion;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import seres.ameacas.Habilidade;

public class HabilidadeCard extends VBox{

    private Habilidade habilidade;

    private int index;

    @FXML
    private Button botãoDeletar;

    @FXML
    private TextField campoNome;

    @FXML
    private TextArea campoDescrição;

    public HabilidadeCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HabilidadeCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void setup(Habilidade habilidade, int index, EventHandler<? super MouseEvent> funçãoDeletar) {
        this.habilidade = habilidade;
        this.index = index;

        // Setar nome
        this.campoNome.setText(this.habilidade.getNome());
        this.campoNome.setOnKeyTyped(event -> {
            this.habilidade.setNome(this.campoNome.getText());
        });

        // Setar descrição
        this.campoDescrição.setText(this.habilidade.getDescricao());
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
