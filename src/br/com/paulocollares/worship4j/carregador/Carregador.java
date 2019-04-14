package br.com.paulocollares.worship4j.carregador;

import br.com.paulocollares.worship4j.ihm.cenas.controladores.ControladorSplash;
import br.com.paulocollares.worship4j.Main;
import static br.com.paulocollares.worship4j.Main.VERSAO;
import br.com.paulocollares.worship4j.Mediador;
import br.com.paulocollares.worship4j.ihm.Janela;
import br.com.paulocollares.worship4j.ihm.Tela;
import br.com.paulocollares.worship4j.ihm.VariaveisInterface;
import javafx.stage.Screen;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class Carregador {

    private Main main;

    public Carregador(Main main) {
        this.main = main;
        configurarIHM();

        Mediador.getInstancia().getJanelas().put(VariaveisInterface.SPLASH, new Janela("Carregando", VariaveisInterface.SPLASH));
        Mediador.getInstancia().getJanela(VariaveisInterface.SPLASH).exibirIHM();

        Mediador.getInstancia().iniciar(this, main);

    }

    private void configurarIHM() {

        //Adicionando tela principal
        Mediador.getInstancia().getTelas().put(1, new Tela(main.getStage(), Screen.getPrimary(), 1));

        //Forçar a inserir a segunda tela, quando há a penas uma
        if (Screen.getScreens().size() == 1) {
            Mediador.getInstancia().getTelas().put(2, new Tela(Screen.getScreens().get(0), 2));
        } else {
            Mediador.getInstancia().getTelas().put(2, new Tela(Screen.getScreens().get(1), 2));
        }
    }

    public void iniciar() {
        Mediador.getInstancia().getJanelas().put(VariaveisInterface.TELA_PRINCIPAL, new Janela(VERSAO + " - collares.net.br/worship4j", VariaveisInterface.TELA_PRINCIPAL, Mediador.getInstancia().getTelaPrincipal()));
        Mediador.getInstancia().getJanelas().put(VariaveisInterface.TELA_SECUNDARIA, new Janela("Apresentador " + VERSAO, VariaveisInterface.TELA_SECUNDARIA, Mediador.getInstancia().getTela(2)));

        Mediador.getInstancia().getJanela(VariaveisInterface.TELA_SECUNDARIA).exibirIHM();
        Mediador.getInstancia().getJanela(VariaveisInterface.TELA_PRINCIPAL).exibirIHM();
        Mediador.getInstancia().getJanela(VariaveisInterface.SPLASH).ocultarIHM();
    }

    public void atualizaSplash(String texto) {
        if (Mediador.getInstancia().getJanelas().get(VariaveisInterface.SPLASH) != null) {
            ((ControladorSplash) Mediador.getInstancia().getJanelas().get(VariaveisInterface.SPLASH).getCena().getControlador()).atualizaTexto(texto);
        }
    }

    public void atualizaSplashTexto(String texto) {
        if (Mediador.getInstancia().getJanelas().get(VariaveisInterface.SPLASH) != null) {
            ((ControladorSplash) Mediador.getInstancia().getJanelas().get(VariaveisInterface.SPLASH).getCena().getControlador()).atualizaTextoSecundario(texto);
        }
    }

}
