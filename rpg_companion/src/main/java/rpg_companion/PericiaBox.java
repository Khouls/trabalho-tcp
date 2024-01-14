package rpg_companion;

import java.io.IOException;
import java.security.InvalidKeyException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import seres.Pericia;
import seres.Ser;


public class PericiaBox extends GridPane {

    @FXML
    private Spinner<Integer> spinnerModificador;

    @FXML
    private Button botaoRolar;

    @FXML
    private Text textoNome;

    private Ser ser;

    private Pericia pericia;

    public PericiaBox() {       
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PericiaBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void setup(Ser ser, Pericia pericia, EventHandler<? super MouseEvent> funçãoRolar) {
        this.ser = ser;
        this.pericia = pericia;

        // Setar o nome da pericia
        this.textoNome.setText(pericia.toString());

        // Setar o spinner do modificador
        this.spinnerModificador.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-100, 100, 0, 5));

        this.spinnerModificador.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            this.ser.setPericia(pericia, Integer.parseInt(newValue));
        });

        this.botaoRolar.setOnMouseClicked(funçãoRolar);
        
        // Adicionar o icone ao botão
       // Image imagemDado = new Image(getResource("../icons/d20.png").toExternalForm());
        //ImageView iconeBotao = new ImageView(imagemDado);
        //iconeBotao.setFitHeight(36.0);
        //iconeBotao.setPreserveRatio(true);
        //this.botaoRolar.setGraphic(iconeBotao);
        
    }
}
