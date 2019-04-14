package br.com.paulocollares.worship4j.util;

import java.io.File;

/**
 * Worship4j - http://collares.net.br/worship4j/
 * Esta classe define os diretorios usados pelo sistema, todos estes diretórios
 * são relativos ao diretório padrão
 *
 * @author Paulo Collares
 */
public class Diretorios {

    public enum Diretorio {

        BIBLIAS("biblias"),
        FUNDOS("fundos"),
        LOGOS("logos"),
        MUSICAS("musicas"),
        VIDEOS("videos"),
        IMAGENS("imagens");

        private final String path;

        private Diretorio(String path) {
            this.path = path;
        }

        @Override
        public String toString() {
            return path + File.separator;
        }

    };

    private static String diretorioPadrao;

    public static String getDiretorioPadrao() {
        if (diretorioPadrao == null) {
            diretorioPadrao = new File(".").getAbsolutePath() + File.separator + "recursos" + File.separator;
        }
        return diretorioPadrao;
    }

    public static String getDiretorioBiblias() {
        return getDiretorioPadrao() + Diretorio.BIBLIAS;
    }

    public static String getDiretorioFundos() {
        return getDiretorioPadrao() + Diretorio.FUNDOS;
    }

    public static String getDiretorioImagens() {
        return getDiretorioPadrao() + Diretorio.IMAGENS;
    }

    public static String getDiretorioLogos() {
        return getDiretorioPadrao() + Diretorio.LOGOS;
    }

    public static String getDiretorioMusicas() {
        return getDiretorioPadrao() + Diretorio.MUSICAS;
    }

    public static String getDiretorioVideos() {
        return getDiretorioPadrao() + Diretorio.VIDEOS;
    }

    public static void criarDiretorios() {
        new File(getDiretorioBiblias()).mkdirs();
        new File(getDiretorioFundos()).mkdirs();
        new File(getDiretorioImagens()).mkdirs();
        new File(getDiretorioLogos()).mkdirs();
        new File(getDiretorioMusicas()).mkdirs();
        new File(getDiretorioVideos()).mkdirs();
    }

}
