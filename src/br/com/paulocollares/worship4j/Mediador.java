package br.com.paulocollares.worship4j;

import br.com.paulocollares.worship4j.gestao.GestorArquivos;
import br.com.paulocollares.worship4j.gestao.GestorObjetos;
import br.com.paulocollares.worship4j.ihm.Janela;
import br.com.paulocollares.worship4j.ihm.Tela;
import br.com.paulocollares.worship4j.carregador.Carregador;
import br.com.paulocollares.worship4j.ihm.cenas.controladores.ControladorTelaPrincipal;
import br.com.paulocollares.worship4j.util.Ambiente;
import br.com.paulocollares.worship4j.util.Diretorios;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class Mediador {

    private static Mediador instancia;
    private Carregador carregador;
    private Main main;

    private GestorObjetos gestorObjetos;
    private GestorArquivos gestorArquivos;

    private final Map<Integer, Tela> telas = new HashMap<>();
    private ControladorTelaPrincipal controladorTelaPrincipal;
    private final Map<String, Janela> janelas = new HashMap<>();

    private Mediador() {

    }

    public static Mediador getInstancia() {
        if (instancia == null) {
            instancia = new Mediador();
        }
        return instancia;
    }

    public void iniciar(Carregador carregador, Main main) {
        this.carregador = carregador;
        this.main = main;
        carregador.atualizaSplash("Criando diretórios dos recursos, caso não existam...");
        Diretorios.criarDiretorios();
        new Thread(new IniciarMediador()).start();
    }

    private void configurarGestores() {
        carregador.atualizaSplash("Iniciando Gestor de Objetos");
        gestorObjetos = new GestorObjetos();

        carregador.atualizaSplash("Iniciando Gestor de Arquivos");
        gestorArquivos = new GestorArquivos();

        carregador.atualizaSplash("Carregando Músicas");
        gestorArquivos.carregarMusicas();
        carregador.atualizaSplash("Carregando Bíblias");
        gestorArquivos.carregarBiblia();
        carregador.atualizaSplash("Carregando Vídeos");
        gestorArquivos.carregarVideos();
    }

    public GestorObjetos getGestorObjetos() {
        return gestorObjetos;
    }

    public ControladorTelaPrincipal getControladorTelaPrincipal() {
        return controladorTelaPrincipal;
    }

    public void setControladorTelaPrincipal(ControladorTelaPrincipal controladorTelaPrincipal) {
        this.controladorTelaPrincipal = controladorTelaPrincipal;
    }

    public Janela getJanela(String fxml) {
        return janelas.get(fxml);
    }

    public Map<String, Janela> getJanelas() {
        return janelas;
    }

    public Map<Integer, Tela> getTelas() {
        return telas;
    }

    public Tela getTela(int pos) {
        return telas.get(pos);
    }

    public Tela getTelaPrincipal() {
        return getTela(1);
    }

    public Main getMain() {
        return main;
    }

    public Carregador getCarregador() {
        return carregador;
    }

    private class IniciarMediador implements Runnable {

        @Override
        public void run() {
            carregador.atualizaSplash("Iniciando Mediador");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            }
            carregador.atualizaSplash("Iniciando Gestores");
            configurarGestores();

            carregador.atualizaSplash("Iniciando animador");
            int intervalo = Integer.valueOf(Ambiente.getInstance().obterValorVariavelAmbiente("INTERVALO_ATUALIZACAO"));
            new Thread(new Animador(intervalo)).start();

            carregador.atualizaSplash("Iniciando Sistema");
            carregador.iniciar();

        }

    }

    private class Animador implements Runnable {

        private int intervalo;

        public Animador(int intervalo) {
            this.intervalo = intervalo;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(intervalo);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

                Iterator<Janela> it = Mediador.getInstancia().getJanelas().values().iterator();
                while (it.hasNext()) {
                    Janela janela = it.next();
                    if (janela.isVisivel()) {
                        janela.getCena().getControlador().animar();
                    }
                }

            }
        }

    }

}
