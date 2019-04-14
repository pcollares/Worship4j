package br.com.paulocollares.worship4j.ihm;

import br.com.paulocollares.worship4j.Main;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class Cena {

    private final Scene scene;
    private final String fxml;
    private final Controlador controlador;

    public Cena(String fxml, Tela tela) {
        this.fxml = fxml;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        AnchorPane root = null;
        try {
            root = (AnchorPane) fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(Cena.class.getName()).log(Level.SEVERE, null, ex);
        }

        controlador = fxmlLoader.getController();
        controlador.setCena(Cena.this);
        controlador.setTela(tela);

        controlador.configurar();

        scene = new Scene(root);
    }

    public Cena(String fxml) {
        this(fxml, null);
    }

    public Scene getScene() {
        return scene;
    }

    public String getFxml() {
        return fxml;
    }

    public Controlador getControlador() {
        return controlador;
    }

}
