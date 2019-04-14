package br.com.paulocollares.worship4j.dominio;

import br.com.paulocollares.worship4j.util.Ambiente;
import java.util.ArrayList;
import java.util.List;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public abstract class RecursoTextual extends Recurso {

    private final List<ItemRecurso> itens;
    private String fundo;

    public RecursoTextual(String nome) {
        super(nome);
        itens = new ArrayList<>();
        fundo = Ambiente.getInstance().obterValorVariavelAmbiente("FUNDO_PADRAO");
    }

    public List<ItemRecurso> getItens() {
        return itens;
    }

    public void addItem(String item) {
        addItem(item, null);
    }

    public void addItem(String item, String referencia) {
        ItemRecurso r = new ItemRecurso(item);
        r.setReferencia(referencia);
        itens.add(r);
    }

    public ItemRecurso getItemPrincipal() {
        return new ItemRecurso(getNome());
    }

    public String getFundo() {
        return fundo;
    }

    public void setFundo(String fundo) {
        this.fundo = fundo;
    }

}
