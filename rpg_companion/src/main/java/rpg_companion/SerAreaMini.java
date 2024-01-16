package rpg_companion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import seres.Ser;
import seres.ameacas.Ameaca;
import seres.personagens.Personagem;

public class SerAreaMini extends VBox {

    private int index;

    private Ser ser;

    @FXML
    private Label nomePersonagemMiniLabel;

    @FXML
    private ProgressBar vidaMiniBarra;

    @FXML
    private ProgressBar sanidadeMiniBarra;

    @FXML
    private ProgressBar esforcoMiniBarra;

    @FXML
    private VBox areaSanidadeEsforco;

    @FXML
    private MenuItem excluirOpcao;

    public SerAreaMini() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonagemAreaMini.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            setVgrow(this, Priority.ALWAYS);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void setup(Ser ser, EventHandler<ActionEvent> acaoExcluir) {
        this.ser = ser;
        this.nomePersonagemMiniLabel.setText(this.ser.getNome());
        this.vidaMiniBarra.setProgress(ser.getPontosVida().getProporçao());
        this.excluirOpcao.setOnAction(acaoExcluir);
        if (this.ser instanceof Personagem) {
            this.sanidadeMiniBarra.setProgress(((Personagem)this.ser).getPontosSanidade().getProporçao());
            this.esforcoMiniBarra.setProgress(((Personagem)this.ser).getPontosEsforço().getProporçao());
        } else if (this.ser instanceof Ameaca) {
            this.areaSanidadeEsforco.setVisible(false);
        }
    }

    public void atualizaMini(){
        this.nomePersonagemMiniLabel.setText(this.ser.getNome());
        this.vidaMiniBarra.setProgress(this.ser.getPontosVida().getProporçao());
        if (this.ser instanceof Personagem) {
            this.sanidadeMiniBarra.setProgress(((Personagem)this.ser).getPontosSanidade().getProporçao());
            this.esforcoMiniBarra.setProgress(((Personagem)this.ser).getPontosEsforço().getProporçao());
        }
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}