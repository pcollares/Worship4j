package br.com.paulocollares.worship4j.gestao;

import br.com.paulocollares.worship4j.Mediador;
import br.com.paulocollares.worship4j.dominio.CapituloBiblia;
import br.com.paulocollares.worship4j.dominio.Musica;
import br.com.paulocollares.worship4j.dominio.Video;
import br.com.paulocollares.worship4j.util.Ambiente;
import br.com.paulocollares.worship4j.util.Diretorios;
import br.com.paulocollares.worship4j.util.LeitorArquivo;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class GestorArquivos {

    public void carregarVideos() {

        String path = Diretorios.getDiretorioVideos();

        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Diretorio de videos não encontrado");
            return;
        }

        for (File f : file.listFiles()) {
            if (f.isFile() && !f.isHidden()) {
                Media media = null;
                try {
                    media = new Media(new File(f.getAbsolutePath()).toURI().toString());
                } catch (MediaException me) {
                    System.out.println("Vídeo não suportado: " + f.getAbsolutePath());
                }
                if (media != null) {
                    Video video = new Video(f.getName());
                    video.setArquivo(f.getAbsolutePath());
                    video.setMedia(media);
                    Mediador.getInstancia().getGestorObjetos().addRecursosVideos(video);
                }
            }
        }
    }

    public void carregarMusicas() {

        String path = Diretorios.getDiretorioMusicas();

        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Diretorio de musicas não encontrado");
            return;
        }

        for (File f : file.listFiles()) {
            if (f.isFile() && !f.isHidden() && f.getName().charAt(f.getName().length() - 1) != '~') {
                Musica m = extraiMusica(path + f.getName());
                Mediador.getInstancia().getGestorObjetos().addRecursosMusicas(m);
            }
        }
    }

    private Musica extraiMusica(String arquivo) {

        LeitorArquivo leitorArquivo = new LeitorArquivo(arquivo);

        Musica musica = new Musica(leitorArquivo.get("TITULO"));
        musica.setAutor(leitorArquivo.get("ARTISTA"));
        musica.setFundo(leitorArquivo.get("FUNDO"));
        musica.setArquivo(arquivo);

        String paragrafo = null;
        for (int i = 0; i < leitorArquivo.getLinhas().size(); i++) {
            if (leitorArquivo.getLinhas().get(i).isEmpty()) {
                musica.addItem(paragrafo);
                paragrafo = null;
            } else {
                if (paragrafo == null) {
                    paragrafo = leitorArquivo.getLinhas().get(i);
                } else {
                    paragrafo = paragrafo + "\n" + leitorArquivo.getLinhas().get(i);
                }
            }
        }
        if (paragrafo != null) {
            musica.addItem(paragrafo);
        }

        return musica;
    }

    public void carregarBiblia() {

        String path = Diretorios.getDiretorioBiblias();

        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Diretorio de biblias não encontrado");
            return;
        }

        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                String versao = f.getName();
                carregaLivros(path + versao + File.separator, versao);
            }
        }

    }

    private void carregaLivros(String caminho, String versao) {
        File file = new File(caminho);
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                String livro = f.getName();
                carregaCapitulos(caminho + livro + File.separator, versao, livro);
            }
        }
        System.out.println(file.listFiles().length + " livros da bíblia (" + versao + ") adicionados");
    }

    private void carregaCapitulos(String caminho, String versao, String livro) {
        File file = new File(caminho);
        for (File f : file.listFiles()) {
            if (f.isFile() && !f.isHidden()) {
                String capitulo = f.getName();
                String referencia = livro + " " + capitulo;
                CapituloBiblia c = extraiCapitulo(caminho + capitulo, versao, referencia);
                Mediador.getInstancia().getGestorObjetos().addRecursosCapitulosBiblia(c);
            }
        }
    }

    private CapituloBiblia extraiCapitulo(String arquivo, String versao, String referencia) {

        LeitorArquivo leitorArquivo = new LeitorArquivo(arquivo);

        CapituloBiblia capitulo = new CapituloBiblia(referencia);
        capitulo.setVersao(versao);
        capitulo.setFundo(Ambiente.getInstance().obterValorVariavelAmbiente("FUNDO_BIBLIA"));

        for (int i = 0; i < leitorArquivo.getLinhas().size(); i++) {
            if (!leitorArquivo.getLinhas().get(i).isEmpty()) {
                capitulo.addItem(leitorArquivo.getLinhas().get(i), referencia + ":" + (i + 1));
            }
        }
        return capitulo;
    }

}
