package br.com.paulocollares.worship4j.dominio;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class ItemRecurso {

    private final StringProperty item;
    private String referencia;

    public ItemRecurso(String item) {
        this.item = new SimpleStringProperty(item);
    }

    public String getItem() {
        return item.get();
    }

    public void setItem(String nome) {
        this.item.set(nome);
    }

    public StringProperty getItemProperty() {
        return item;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Override
    public String toString() {
        return getItem();
    }

}
