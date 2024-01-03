module rpg_companion {
    requires javafx.controls;
    requires javafx.fxml;

    opens rpg_companion to javafx.fxml;
    exports rpg_companion;
}
