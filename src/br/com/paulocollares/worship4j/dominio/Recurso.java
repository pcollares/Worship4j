package br.com.paulocollares.worship4j.dominio;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public abstract class Recurso {

    private final StringProperty nome;
    private String arquivo;

    public Recurso(String nome) {
        this.nome = new SimpleStringProperty(nome);
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty getNomeProperty() {
        return nome;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public String toString() {
        return getNome();
    }

}
