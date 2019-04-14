package br.com.paulocollares.worship4j.dominio;

import javafx.scene.media.Media;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class Video extends Recurso {

    private Media media;

    public Video(String nome) {
        super(nome);
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

}
