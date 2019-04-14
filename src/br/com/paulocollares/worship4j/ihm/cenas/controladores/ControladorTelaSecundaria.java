package br.com.paulocollares.worship4j.ihm.cenas.controladores;

import br.com.paulocollares.worship4j.dominio.ItemRecurso;
import br.com.paulocollares.worship4j.dominio.Recurso;
import br.com.paulocollares.worship4j.dominio.RecursoTextual;
import br.com.paulocollares.worship4j.dominio.Video;
import br.com.paulocollares.worship4j.ihm.Controlador;
import br.com.paulocollares.worship4j.util.Ambiente;
import br.com.paulocollares.worship4j.util.Diretorios;
import java.io.File;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class ControladorTelaSecundaria extends Controlador {

    @FXML
    private Label labelItem;

    private MediaPlayer mediaPlayerFundo;
    private MediaPlayer mediaPlayerLogo;
    private MediaPlayer mediaPlayerVideo;

    private WritableImage snapshot;
    @FXML
    private MediaView mediaViewLogo;
    @FXML
    private AnchorPane anchorPaneGeral;

    private Recurso recursoAtual;
    private ItemRecurso itemRecursoAtual;
    @FXML
    private Label labelReferencia;

    @FXML
    private MediaView mediaViewVideo;
    @FXML
    private AnchorPane anchorPaneTextual;
    @FXML
    private MediaView mediaViewFundo;
    @FXML
    private AnchorPane anchorPaneLabels;

    @Override
    public void teclaSolta(KeyEvent event) {
    }

    @Override
    public void teclaPressionada(KeyEvent event) {
    }

    @Override
    public void exibirIHM() {
        playerFundo(null);
    }

    @Override
    public void ocultarIHM() {
    }

    @Override
    public void configurar() {
        labelItem.setWrapText(true);
    }

    public void trocaItem(Video video) {
        anchorPaneTextual.setVisible(false);
        mediaViewVideo.setVisible(true);
        playerVideo(video);
    }

    public void trocaItem(ItemRecurso itemRecurso, RecursoTextual recurso) {

        if (mediaPlayerVideo != null) {
            mediaPlayerVideo.stop();
        }

        anchorPaneTextual.setVisible(true);
        mediaViewVideo.setVisible(false);

        recursoAtual = recurso;
        itemRecursoAtual = itemRecurso;

        if (!mediaPlayerFundo.getMedia().getSource().contains(recurso.getFundo())) {
            playerFundo(new File(Diretorios.getDiretorioFundos() + recurso.getFundo()));
        }

        String novoTexto = "";
        String novoTextoReferencia = "";

        if (itemRecurso != null && itemRecurso.getItem() != null) {
            novoTexto = itemRecurso.getItem();
            if (itemRecurso.getReferencia() != null) {
                novoTextoReferencia = itemRecurso.getReferencia();
            }
        }

        final String textoFinal = novoTexto;
        final String novoTextoReferenciaFinal = novoTextoReferencia;

        int tamanhoFonte = 75;

        System.out.println(textoFinal.length());

        FadeTransition ft = new FadeTransition(Duration.millis(100), labelItem);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished((ActionEvent arg0) -> {
            labelItem.setStyle(" -fx-font-size: " + tamanhoFonte + "px;");
            labelItem.setText(textoFinal);

            labelReferencia.setText(novoTextoReferenciaFinal);
            FadeTransition ft2 = new FadeTransition(Duration.millis(200), labelItem);
            ft2.setFromValue(0);
            ft2.setToValue(1);
            ft2.play();
        });
        ft.play();
    }

    public WritableImage obtemTela() {
        return snapshot;
    }

    private void playerVideo(Video video) {
        if (mediaPlayerVideo != null) {
            mediaPlayerVideo.stop();
        }

        mediaPlayerVideo = new MediaPlayer(video.getMedia());

        Platform.runLater(() -> {
            mediaViewVideo.setFitWidth(getTela().getW());
            mediaViewVideo.setFitHeight(getTela().getH());
            mediaViewVideo.setPreserveRatio(false);
            mediaViewVideo.setMediaPlayer(mediaPlayerVideo);
        });

    }

    public MediaPlayer getMediaPlayerVideo() {
        return mediaPlayerVideo;
    }

    private void playerFundo(File file) {

        if (mediaPlayerFundo != null) {
            mediaPlayerFundo.stop();
        }

        if (file == null || !file.exists()) {
            file = new File(Diretorios.getDiretorioFundos() + Ambiente.getInstance().obterValorVariavelAmbiente("FUNDO_PADRAO"));
        }

        Media media = new Media(file.toURI().toString());
        mediaPlayerFundo = new MediaPlayer(media);

        Platform.runLater(() -> {
            mediaViewFundo.setFitWidth(getTela().getW());
            mediaViewFundo.setFitHeight(getTela().getH());
            mediaViewFundo.setPreserveRatio(false);
            mediaViewFundo.setMediaPlayer(mediaPlayerFundo);
        });

        mediaPlayerFundo.setMute(true);
        mediaPlayerFundo.setAutoPlay(true);
        mediaPlayerFundo.setCycleCount(Integer.MAX_VALUE);

    }

    private void playerLogo(File file) {

        if (mediaPlayerLogo != null) {
            mediaPlayerLogo.stop();
        }

        if (file == null || !file.exists()) {
            return;
        }

        Media media = new Media(file.toURI().toString());
        mediaPlayerLogo = new MediaPlayer(media);

        Platform.runLater(() -> {
            mediaViewLogo.setFitWidth(getTela().getW());
            mediaViewLogo.setFitHeight(getTela().getH());
            mediaViewLogo.setPreserveRatio(false);
            mediaViewLogo.setMediaPlayer(mediaPlayerLogo);
        });

        mediaPlayerLogo.setMute(true);
        mediaPlayerLogo.setAutoPlay(true);
        mediaPlayerLogo.setCycleCount(Integer.MAX_VALUE);
    }

    @Override
    public void animar() {
        Platform.runLater(() -> {
            snapshot = getTela().getStage().getScene().snapshot(new WritableImage((int) getTela().getW(), (int) getTela().getH()));
        });
    }

    public void acaoBotaoLogo(boolean exibir) {
        acaoBotaoBlack(exibir);
        if (exibir) {
            mediaViewLogo.setVisible(true);
            playerLogo(new File(Diretorios.getDiretorioLogos() + Ambiente.getInstance().obterValorVariavelAmbiente("LOGO")));
            FadeTransition ft = new FadeTransition(Duration.millis(500), mediaViewLogo);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
        } else {
            FadeTransition ft = new FadeTransition(Duration.millis(500), mediaViewLogo);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();
            ft.setOnFinished((ActionEvent arg0) -> {
                mediaViewLogo.setVisible(false);
            });

        }
    }

    public void acaoBotaoBlack(boolean exibir) {
        if (exibir) {
            FadeTransition ft = new FadeTransition(Duration.millis(500), anchorPaneGeral);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();
        } else {
            FadeTransition ft = new FadeTransition(Duration.millis(500), anchorPaneGeral);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
        }
    }

    public void acaoBotaoClear(boolean exibir) {
        if (exibir) {
            FadeTransition ft = new FadeTransition(Duration.millis(500), anchorPaneLabels);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();
        } else {
            FadeTransition ft = new FadeTransition(Duration.millis(500), anchorPaneLabels);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
        }
    }

}
