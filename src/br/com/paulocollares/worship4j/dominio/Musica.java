package br.com.paulocollares.worship4j.dominio;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class Musica extends RecursoTextual {

    private String autor;

    public Musica(String nome) {
        super(nome);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public ItemRecurso getItemPrincipal() {
        return new ItemRecurso(getNome() + "\n" + getAutor());
    }

}
