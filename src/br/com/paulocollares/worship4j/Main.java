package br.com.paulocollares.worship4j;

import br.com.paulocollares.worship4j.carregador.Carregador;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class Main extends Application {

    public static final String VERSAO = "Worship4j - v 1.0";
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        new Carregador(this);

        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                ocultarIHM();
            }
        });
    }

    public void ocultarIHM() {
        stage.close();
        System.exit(0);
    }

    public Stage getStage() {
        return stage;
    }

}
