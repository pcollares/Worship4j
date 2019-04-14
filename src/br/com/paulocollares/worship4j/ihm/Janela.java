package br.com.paulocollares.worship4j.ihm;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class Janela {

    private final Cena cena;
    private final Stage stage;
    private final String titulo;

    public Janela(String titulo, String fxml, Tela tela) {
        this.titulo = titulo;
        cena = new Cena(fxml, tela);
        stage = tela.getStage();
    }

    public Janela(String titulo, String fxml) {
        this.titulo = titulo;
        cena = new Cena(fxml);
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
    }

    public Stage getStage() {
        return stage;
    }

    public Cena getCena() {
        return cena;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isVisivel() {
        return stage.isShowing();
    }

    public void exibirIHM() {
        Platform.runLater(() -> {
            stage.setTitle(titulo);
            stage.setScene(cena.getScene());
            stage.getIcons().add(new Image("br/com/paulocollares/worship4j/imagens/icone.png"));
            getCena().getControlador().exibirIHM();
            stage.show();
        });
    }

    public void ocultarIHM() {
        Platform.runLater(() -> {
            getCena().getControlador().ocultarIHM();
            stage.close();
        });
    }

}
