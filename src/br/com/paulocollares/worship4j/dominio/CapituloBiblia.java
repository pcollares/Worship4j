package br.com.paulocollares.worship4j.dominio;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class CapituloBiblia extends RecursoTextual {

    private String versao;

    public CapituloBiblia(String nome) {
        super(nome);
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    @Override
    public ItemRecurso getItemPrincipal() {
        return null;
    }

}
