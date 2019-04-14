package br.com.paulocollares.worship4j.ihm;

import javafx.scene.control.Control;
import javafx.scene.input.KeyEvent;

/**
 * Worship4j - http://collares.net.br/worship4j/. 
 * Controlador padrão, deve ser herdado pelos demais controladores associados aos FXMLs.
 *
 * @author Paulo Collares
 */
public abstract class Controlador extends Control {

    private Cena cena;
    public Controlador pai;
    private Tela tela;

    /**
     * Trata os eventos recebidos do teclado, quando uma tecla é solta
     *
     * @param event
     */
    public abstract void teclaSolta(KeyEvent event);

    /**
     * Trata os eventos recebidos do teclado, quando uma tecla é pressionada
     *
     * @param event
     */
    public abstract void teclaPressionada(KeyEvent event);

    public Cena getCena() {
        return cena;
    }

    public void setCena(Cena cena) {
        this.cena = cena;
    }

    public void setTela(Tela tela) {
        this.tela = tela;
    }

    public Tela getTela() {
        return tela;
    }

    public abstract void exibirIHM();

    public abstract void ocultarIHM();

    public abstract void configurar();

    public abstract void animar();

    /**
     * @return the pai
     */
    public Controlador getPai() {
        return pai;
    }

    /**
     * @param pai the pai to set
     */
    public void setPai(Controlador pai) {
        this.pai = pai;
    }

}
