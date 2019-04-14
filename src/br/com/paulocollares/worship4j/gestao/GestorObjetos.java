package br.com.paulocollares.worship4j.gestao;

import br.com.paulocollares.worship4j.Mediador;
import br.com.paulocollares.worship4j.dominio.CapituloBiblia;
import br.com.paulocollares.worship4j.dominio.Musica;
import br.com.paulocollares.worship4j.dominio.Recurso;
import br.com.paulocollares.worship4j.dominio.Video;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class GestorObjetos {

    private final ObservableList<Recurso> listaRecursosCulto;
    private final ObservableList<Musica> listaRecursosMusicas;
    private final ObservableList<Video> listaRecursosVideos;
    private final ObservableList<CapituloBiblia> listaRecursosCapitulosBiblia;

    private final Map<Integer, CapituloBiblia> mapaCapitulos;

    public GestorObjetos() {
        listaRecursosCulto = FXCollections.observableArrayList();
        listaRecursosMusicas = FXCollections.observableArrayList();
        listaRecursosCapitulosBiblia = FXCollections.observableArrayList();
        listaRecursosVideos = FXCollections.observableArrayList();

        mapaCapitulos = new HashMap<>();
    }

    public void addRecursoCulto(Recurso r) {
        listaRecursosCulto.add(r);
        System.out.println("Recurso adicionado ao culto: " + r.getNome());
    }

    public ObservableList<Recurso> getListaRecursosCulto() {
        return listaRecursosCulto;
    }

    public void addRecursosMusicas(Musica r) {
        listaRecursosMusicas.add(r);
        Mediador.getInstancia().getCarregador().atualizaSplashTexto("Música adicionada: " + r.getNome());
    }

    public ObservableList<Musica> getListaRecursosMusicas() {
        return listaRecursosMusicas;
    }

    public void addRecursosCapitulosBiblia(CapituloBiblia c) {
        listaRecursosCapitulosBiblia.add(c);
        mapaCapitulos.put(c.getNome().toLowerCase().hashCode(), c);
        Mediador.getInstancia().getCarregador().atualizaSplashTexto("Capítulo adicionado: " + c.getNome());
    }

    public ObservableList<CapituloBiblia> getListaRecursosCapitulosBiblia() {
        return listaRecursosCapitulosBiblia;
    }

    public Map<Integer, CapituloBiblia> getMapaCapitulos() {
        return mapaCapitulos;
    }

    public void addRecursosVideos(Video r) {
        listaRecursosVideos.add(r);
        Mediador.getInstancia().getCarregador().atualizaSplashTexto("Video adicionado: " + r.getNome());
    }

    public ObservableList<Video> getListaRecursosVideos() {
        return listaRecursosVideos;
    }

}
