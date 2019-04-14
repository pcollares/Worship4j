package br.com.paulocollares.worship4j.ihm.cenas.controladores;

import br.com.paulocollares.worship4j.Main;
import br.com.paulocollares.worship4j.ihm.Controlador;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class ControladorSplash extends Controlador {

    @FXML
    private ProgressBar barra;
    @FXML
    private Label labelTextoSecundario;
    @FXML
    private Label labelPorcento;
    @FXML
    private Label labelRegistrado;

    private static final int TOTAL_PASSOS = 10;

    private int passoAtual;

    @Override
    public void teclaSolta(KeyEvent event) {

    }

    @Override
    public void teclaPressionada(KeyEvent event) {

    }

    @Override
    public void exibirIHM() {
        labelRegistrado.setText("Versão: " + Main.VERSAO);
    }

    @Override
    public void ocultarIHM() {

    }

    @Override
    public void configurar() {

    }

    @Override
    public void animar() {

    }

    public void atualizaTextoSecundario(String texto) {
        Platform.runLater(() -> {
            labelTextoSecundario.setText(texto);
        });
    }

    public void atualizaTexto(String texto) {
        passoAtual++;
        double passo = (double) passoAtual / (double) TOTAL_PASSOS;
        int porcento = (int) (passo * 100);
        Platform.runLater(() -> {

            barra.setProgress(passo);
            labelPorcento.setText(texto + " (" + porcento + "%)");
        });
    }

}
