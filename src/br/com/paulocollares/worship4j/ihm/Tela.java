package br.com.paulocollares.worship4j.ihm;

import br.com.paulocollares.worship4j.Mediador;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class Tela {

    private final Stage stage;

    private final Map<String, Cena> cenas = new HashMap<>();

    private final int numTela;

    private double x;
    private double y;
    private double w;
    private double h;

    /**
     * Construtor da Tela principal
     *
     * @param stage
     * @param screen
     * @param numTela
     */
    public Tela(Stage stage, Screen screen, int numTela) {
        this.stage = stage;
        this.numTela = numTela;
        construir(screen);
    }

    /**
     * Construtor das demais Telas
     *
     * @param screen
     * @param numTela
     */
    public Tela(Screen screen, int numTela) {
        this(new Stage(), screen, numTela);
    }

    private void construir(Screen screen) {

        x = screen.getVisualBounds().getMinX();
        y = screen.getVisualBounds().getMinY();
        w = screen.getVisualBounds().getWidth();
        h = screen.getVisualBounds().getHeight();

        if (numTela == 1) {
            stage.setResizable(true);
        } else {
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setX(x);
            stage.setY(y);
            stage.setWidth(w);
            stage.setHeight(h);
            stage.setResizable(false);
        }

        System.out.println("Construindo tela " + numTela + " pos: " + stage.getX() + "," + stage.getY() + " tamanho: " + stage.getWidth() + "x" + stage.getHeight());

        stage.addEventFilter(KeyEvent.KEY_PRESSED, (final KeyEvent event) -> {
            teclaPressionada(event);
        });

        stage.addEventFilter(KeyEvent.KEY_RELEASED, (final KeyEvent event) -> {
            teclaSolta(event);
        });

    }

    /**
     * verifica se esta é a tela principal
     *
     * @return
     */
    public boolean isTelaPrincipal() {
        return numTela == 0;
    }

    /**
     * Trata os eventos de uma tecla solta do teclado
     *
     * @param event
     */
    private void teclaSolta(KeyEvent event) {
        Iterator<Janela> it = Mediador.getInstancia().getJanelas().values().iterator();
        while (it.hasNext()) {
            Janela janela = it.next();
            if (janela.isVisivel()) {
                janela.getCena().getControlador().teclaSolta(event);
            }
        }
    }

    /**
     * Trata os eventos de uma tecla pressionada do teclado
     *
     * @param event
     */
    private void teclaPressionada(KeyEvent event) {
        Iterator<Janela> it = Mediador.getInstancia().getJanelas().values().iterator();
        while (it.hasNext()) {
            Janela janela = it.next();
            if (janela.isVisivel()) {
                janela.getCena().getControlador().teclaPressionada(event);
            }
        }
    }

    public Stage getStage() {
        return stage;
    }

    public boolean isVisivel() {
        return stage.isShowing();
    }

    /**
     * Obtém uma cena criada préviamente
     *
     * @param fxml
     * @return
     */
    public Cena getCena(String fxml) {
        return cenas.get(fxml);
    }

    public Map<String, Cena> getCenas() {
        return cenas;
    }

    public int getNumTela() {
        return numTela;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

}
