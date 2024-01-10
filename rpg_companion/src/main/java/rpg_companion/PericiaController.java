package rpg_companion;

import java.security.InvalidKeyException;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.Spinner;

import seres.Pericia;
import seres.Ser;


public class PericiaController extends Pane {

    @FXML
    private Spinner<Integer> periciaSpinner;

    private Ser serPai;
    private Pericia pericia;

    public PericiaController(Ser serPai, Pericia pericia) {
        this.pericia = pericia;
        this.serPai = serPai;

        System.out.println("Criado:" + serPai.getNome() + "|" + pericia);
    }

    @FXML
    public void rolar() {
        try {
           serPai.fazerTeste(pericia);
           System.out.println(Ser.getHistoricoRolagens());
        } catch (InvalidKeyException e) {
            System.out.println("Pericia invalida: " + pericia);
        }
    }

    @FXML
    public void atualizarPericia() {
        this.serPai.setPericia(pericia, getModificador());
    }

    public int getModificador() {
        return periciaSpinner.getValue();
    }

    public void setModificador(int modificador) {
        this.periciaSpinner.getValueFactory().setValue(modificador);
    }

    public Ser getSer() {
        return this.serPai;
    }

    public Pericia getPericia() {
        return this.pericia;
    }

}
