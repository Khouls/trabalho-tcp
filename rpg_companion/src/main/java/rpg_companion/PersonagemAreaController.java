package rpg_companion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    private Ser ser;

    @FXML
    private VBox areaPericias;
    
    @FXML
    private TextArea textareaRolagens;

    @FXML @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ser = new Personagem("John Paranormal", Classe.Ocultista);
        this.ser.setAtributo(Atributo.Intelecto, 20);
        this.ser.setPericia(Pericia.Ocultismo, 69);
        this.ser.setPericia(Pericia.Ciências, -10);


        for (Pericia pericia : this.ser.getPericias().keySet()) {
            this.areaPericias.getChildren().add(criarContainerPericia(pericia));
        }
    }

    private HBox criarContainerPericia(Pericia pericia) {
        HBox container = new HBox();
        container.setPrefHeight(ALTURA_CONTAINER);
        container.setPrefWidth(200.0);
        container.setSpacing(10.0);
        container.getStyleClass().add("background");
        container.setAlignment(Pos.CENTER_LEFT);

        Text nome = new Text(pericia.toString());
        nome.getStyleClass().add("text-foreground");
        nome.setFont(new Font(14.0));

        Spinner<Integer> spinnerModificador = new Spinner<Integer>(-100, 100, this.ser.getPericias().get(pericia));
        spinnerModificador.setEditable(true);
        spinnerModificador.setPrefHeight(25.0);
        spinnerModificador.setPrefWidth(68.0);
        spinnerModificador.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            this.ser.setPericia(pericia, Integer.parseInt(newValue));
        });

        Button botaoFazerRolagem = new Button("Rodar!");
        botaoFazerRolagem.setPrefHeight(25.0);
        botaoFazerRolagem.setPrefWidth(68.0);
        botaoFazerRolagem.setOnMouseClicked((arg) -> {
            try {
                this.ser.fazerTeste(pericia);
                textareaRolagens.setText(Ser.getHistoricoRolagens());
            } catch (InvalidKeyException e) {
                System.out.println("Pericia Inválida:" + pericia);
            }
        });
        
        container.getChildren().addAll(nome, spinnerModificador, botaoFazerRolagem);

        return container;
    }
}