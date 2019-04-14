package br.com.paulocollares.worship4j.ihm.cenas.controladores;

import br.com.paulocollares.worship4j.Main;
import br.com.paulocollares.worship4j.Mediador;
import br.com.paulocollares.worship4j.dominio.CapituloBiblia;
import br.com.paulocollares.worship4j.dominio.ItemRecurso;
import br.com.paulocollares.worship4j.dominio.Musica;
import br.com.paulocollares.worship4j.dominio.Recurso;
import br.com.paulocollares.worship4j.dominio.RecursoTextual;
import br.com.paulocollares.worship4j.dominio.Video;
import br.com.paulocollares.worship4j.ihm.Controlador;
import br.com.paulocollares.worship4j.ihm.VariaveisInterface;
import br.com.paulocollares.worship4j.util.Util;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class ControladorTelaPrincipal extends Controlador {

    private Recurso recursoAovivo;
    @FXML
    private ListView<Recurso> listViewCulto;
    @FXML
    private ListView<ItemRecurso> listViewAovivo;
    @FXML
    private ImageView imagemAovivo;

    ObservableList<ItemRecurso> listaItemAovivo;
    @FXML
    private AnchorPane anchorPaneImagemAovivo;
    @FXML
    private ToggleButton botaoLogo;
    @FXML
    private ToggleButton botaoBlack;
    @FXML
    private ToggleButton botaoClear;
    @FXML
    private ToggleButton botaoLive;
    @FXML
    private TextField textFielBuscaMusica;
    @FXML
    private ListView<Musica> listViewMusicas;
    @FXML
    private TextField textFielBuscaBiblia;
    @FXML
    private ListView<CapituloBiblia> listViewBiblia;
    @FXML
    private ListView<Video> listViewVideos;
    @FXML
    private ToggleButton botaoPlayPause;
    @FXML
    private ToggleButton botaoRepetir;
    @FXML
    private AnchorPane anchorPaneControlesVideoAovivo;
    @FXML
    private Label labelInfoVideoAovivo;
    @FXML
    private ToggleButton botaoMute;
    @FXML
    private Button botaoParar;

    @Override
    public void teclaSolta(KeyEvent event) {
    }

    @Override
    public void teclaPressionada(KeyEvent event) {

        switch (event.getCode()) {
            case L:
                if (event.isControlDown()) {
                    botaoLogo.fire();
                }
                break;
            case B:
                if (event.isControlDown()) {
                    botaoBlack.fire();
                }
                break;
            case C:
                if (event.isControlDown()) {
                    botaoClear.fire();
                }
                break;
        }

    }

    @Override
    public void exibirIHM() {
        getTela().getStage().centerOnScreen();
        botaoLogo.fire();
    }

    @Override
    public void ocultarIHM() {
    }

    @Override
    public void configurar() {

        listaItemAovivo = FXCollections.observableArrayList();

        listViewCulto.setItems(Mediador.getInstancia().getGestorObjetos().getListaRecursosCulto());
        listViewMusicas.setItems(Mediador.getInstancia().getGestorObjetos().getListaRecursosMusicas());
        listViewBiblia.setItems(Mediador.getInstancia().getGestorObjetos().getListaRecursosCapitulosBiblia());
        listViewVideos.setItems(Mediador.getInstancia().getGestorObjetos().getListaRecursosVideos());
        listViewAovivo.setItems(listaItemAovivo);

        listViewCulto.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
                    setRecursoAovivo(listViewCulto.getSelectionModel().getSelectedItem());
                }

                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    Mediador.getInstancia().getGestorObjetos().getListaRecursosCulto().remove(listViewCulto.getSelectionModel().getSelectedItem());
                }
            }
        });

        listViewMusicas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
                    setRecursoAovivo(listViewMusicas.getSelectionModel().getSelectedItem());
                }
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    Mediador.getInstancia().getGestorObjetos().addRecursoCulto(listViewMusicas.getSelectionModel().getSelectedItem());
                }
            }
        });

        listViewBiblia.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
                    setRecursoAovivo(listViewBiblia.getSelectionModel().getSelectedItem());
                }
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    Mediador.getInstancia().getGestorObjetos().addRecursoCulto(listViewBiblia.getSelectionModel().getSelectedItem());
                }
            }
        });

        listViewVideos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
                    setRecursoAovivo(listViewVideos.getSelectionModel().getSelectedItem());
                }
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    Mediador.getInstancia().getGestorObjetos().addRecursoCulto(listViewVideos.getSelectionModel().getSelectedItem());
                }
            }
        });

        listViewAovivo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (recursoAovivo instanceof RecursoTextual) {
                    ((ControladorTelaSecundaria) Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).getCena().getControlador()).trocaItem(listViewAovivo.getSelectionModel().getSelectedItem(), (RecursoTextual) recursoAovivo);
                }
            }
        });

        textFielBuscaBiblia.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    buscaBiblia();
                }
            }
        });

    }

    public Recurso getRecursoAovivo() {
        return recursoAovivo;
    }

    public void setRecursoAovivo(Recurso recursoAovivo, int linha) {
        this.recursoAovivo = recursoAovivo;
        listaItemAovivo.clear();

        if (recursoAovivo instanceof RecursoTextual) {

            listViewAovivo.setVisible(true);
            anchorPaneControlesVideoAovivo.setVisible(false);

            RecursoTextual recursoTextual = (RecursoTextual) recursoAovivo;

            if (recursoTextual.getItemPrincipal() != null) {
                listaItemAovivo.add(recursoTextual.getItemPrincipal());
                listaItemAovivo.add(new ItemRecurso(null));
            }

            listaItemAovivo.addAll(recursoTextual.getItens());
            listViewAovivo.getSelectionModel().select(linha);
            listViewAovivo.scrollTo(linha);
            listViewAovivo.requestFocus();
        } else if (recursoAovivo instanceof Video) {

            ((ControladorTelaSecundaria) Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).getCena().getControlador()).trocaItem((Video) recursoAovivo);

            listViewAovivo.setVisible(false);
            anchorPaneControlesVideoAovivo.setVisible(true);

            if (!botaoBlack.isSelected() && !botaoLogo.isSelected()) {
                botaoPlayPause.setSelected(true);
                acaoBotaoPlayPause(null);
            }

        }

        System.out.println("Exibindo recurso ao vivo: " + recursoAovivo.getNome());
    }

    public void setRecursoAovivo(Recurso recursoAovivo) {
        setRecursoAovivo(recursoAovivo, 0);
    }

    @Override
    public void animar() {
        if (Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).isVisivel()) {

            WritableImage imagem = ((ControladorTelaSecundaria) Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).getCena().getControlador()).obtemTela();
            if (imagem != null) {
                Platform.runLater(() -> {
                    imagemAovivo.setFitWidth(anchorPaneImagemAovivo.getWidth());
                    imagemAovivo.setFitHeight(anchorPaneImagemAovivo.getHeight());
                    imagemAovivo.setLayoutX(0);
                    imagemAovivo.setVisible(true);
                    imagemAovivo.setImage(imagem);
                });
            }
            if (recursoAovivo instanceof Video) {
                MediaPlayer mp = ((ControladorTelaSecundaria) Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).getCena().getControlador()).getMediaPlayerVideo();
                if (mp != null) {
                    Platform.runLater(() -> {
                        labelInfoVideoAovivo.setText(recursoAovivo.getNome() + " - " + Util.formatTime(mp.getCurrentTime(), mp.getTotalDuration()));
                    });
                }
                if (botaoBlack.isSelected() || botaoLogo.isSelected()) {
                    if (!botaoPlayPause.isDisable()) {
                        Platform.runLater(() -> {
                            if (botaoPlayPause.isSelected()) {
                                botaoPlayPause.fire();
                            }
                            botaoPlayPause.setDisable(true);
                            botaoParar.setDisable(true);
                            botaoRepetir.setDisable(true);
                        });
                    }
                } else {
                    Platform.runLater(() -> {
                        if (botaoPlayPause.isDisable()) {
                            botaoPlayPause.setDisable(false);
                            botaoParar.setDisable(false);
                            botaoRepetir.setDisable(false);
                            if (!botaoPlayPause.isSelected()) {
                                botaoPlayPause.fire();
                            }
                        }
                    });
                }
            }

        } else {
            Platform.runLater(() -> {
                imagemAovivo.setVisible(false);
            });
        }
    }

    @FXML
    private void acaoBotaoLive(ActionEvent event) {
        if (botaoLive.isSelected()) {
            Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).exibirIHM();
        } else {
            Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).ocultarIHM();
        }
    }

    @FXML
    private void acaoBotaoLogo(ActionEvent event) {
        if (Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).isVisivel()) {
            ((ControladorTelaSecundaria) Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).getCena().getControlador()).acaoBotaoLogo(botaoLogo.isSelected());
        }
    }

    @FXML
    private void acaoBotaoBlack(ActionEvent event) {
        if (Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).isVisivel()) {
            ((ControladorTelaSecundaria) Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).getCena().getControlador()).acaoBotaoBlack(botaoBlack.isSelected());
        }
    }

    @FXML
    private void acaoBotaoClear(ActionEvent event) {
        if (Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).isVisivel()) {
            ((ControladorTelaSecundaria) Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).getCena().getControlador()).acaoBotaoClear(botaoClear.isSelected());
        }
    }

    @FXML
    private void acaoMenuSair(ActionEvent event) {
        Mediador.getInstancia().getMain().ocultarIHM();
    }

    private void buscaBiblia() {
        String texto = textFielBuscaBiblia.getText();

        if (texto == null || texto.isEmpty()) {
            return;
        }

        String[] partes = texto.split(":");

        Integer busca = partes[0].toLowerCase().hashCode();
        CapituloBiblia cap = Mediador.getInstancia().getGestorObjetos().getMapaCapitulos().get(busca);

        if (cap != null) {
            if (partes.length == 2) {
                int vers = Integer.parseInt(partes[1]);
                setRecursoAovivo(cap, vers - 1);
            } else {
                setRecursoAovivo(cap);
            }
        }
    }

    @FXML
    private void acaoMenuAjuda(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajuda");
        alert.setHeaderText(null);
        alert.setContentText("Atalhos:\nL - Exibe o logo\nB - Exibe uma tela preta\nC - Limpa a tela");

        alert.showAndWait();
    }

    @FXML
    private void acaoMenuSobre(ActionEvent event) {

        String conteudo = "Sistema desenvolvido por Paulo Collares, livre para uso e modificações\nMais informações em http://collares.net.br/worship4j/";

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sobre");
        alert.setHeaderText(Main.VERSAO);
        alert.setContentText(conteudo);

        alert.show();

        alert.setWidth(500);
        alert.setHeight(300);
        alert.setX((getTela().getW() / 2) - (alert.getWidth() / 2));
        alert.setY((getTela().getH() / 2) - (alert.getHeight() / 2));
    }

    @FXML
    private void acaoBotaoPlayPause(ActionEvent event) {
        MediaPlayer mp = ((ControladorTelaSecundaria) Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).getCena().getControlador()).getMediaPlayerVideo();
        if (botaoPlayPause.isSelected()) {
            mp.play();
        } else {
            mp.pause();
        }
    }

    @FXML
    private void acaoBotaoRepetir(ActionEvent event) {
        MediaPlayer mp = ((ControladorTelaSecundaria) Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).getCena().getControlador()).getMediaPlayerVideo();
        if (botaoRepetir.isSelected()) {
            mp.setAutoPlay(true);
            mp.setCycleCount(Integer.MAX_VALUE);
        } else {
            mp.setAutoPlay(false);
            mp.setCycleCount(1);
        }
    }

    @FXML
    private void acaoBotaoMute(ActionEvent event) {
        MediaPlayer mp = ((ControladorTelaSecundaria) Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).getCena().getControlador()).getMediaPlayerVideo();
        mp.setMute(botaoMute.isSelected());
    }

    @FXML
    private void acaoBotaoParar(ActionEvent event) {
        MediaPlayer mp = ((ControladorTelaSecundaria) Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).getCena().getControlador()).getMediaPlayerVideo();
        mp.stop();
        botaoPlayPause.setSelected(false);
    }

}
