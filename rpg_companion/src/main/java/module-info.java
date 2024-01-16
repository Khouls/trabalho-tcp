module rpg.companion {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires javafx.baseEmpty;
    requires javafx.controlsEmpty;
    requires javafx.fxmlEmpty;
    requires javafx.graphicsEmpty;

    exports gerenciador;
    exports rpg_companion;
    exports seres;
    exports seres.ameacas;
    exports seres.personagens;

    opens rpg_companion;
}
