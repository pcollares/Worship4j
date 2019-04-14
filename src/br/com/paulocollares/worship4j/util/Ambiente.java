package br.com.paulocollares.worship4j.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class Ambiente {

    /**
     * Pasta onde será gravado o timestamp.
     */
    public static final String PROPRIEDADE_PADRAO = "config.properties";

    private Properties p;
    private static Ambiente instance;
    private final File arquivo = new File(".");
    private final File[] arquivosProperties = arquivo.listFiles();
    private final Map<String, Properties> hashmap = new HashMap<>();

    private Ambiente() {
        carregarProperties(arquivosProperties);
    }

    public String obterValorVariavelAmbiente(final String variavelAmbiente) {
        try {
            return obterValorVariavelAmbiente(PROPRIEDADE_PADRAO, variavelAmbiente);
        } catch (NullPointerException ex) {
            Logger.getLogger(Ambiente.class.getName()).log(Level.SEVERE, "Variavel de ambiente inválida: " + variavelAmbiente, ex);
            return "";
        }
    }

    public String obterValorVariavelAmbiente(String propriedade, String variavelAmbiente) throws NullPointerException {
        if (variavelAmbiente == null) {
            Logger.getLogger(Ambiente.class.getName()).log(Level.SEVERE, "Variavel de ambiente inválida: " + variavelAmbiente);
        }

        String retorno;

        retorno = hashmap.get(propriedade).getProperty(variavelAmbiente);

        if (retorno == null) {
            Logger.getLogger(Ambiente.class.getName()).log(Level.SEVERE, "Variavel de ambiente inexistente: " + variavelAmbiente);
        }

        return retorno.trim();
    }

    private void carregarProperties(File[] arquivos) {
        try {
            if (arquivos.length > 0) {
                for (File f : arquivos) {
                    if (f.getName().endsWith(".properties")) {
                        try (FileInputStream fis = new FileInputStream(f.getName())) {
                            p = new Properties();
                            p.load(fis);
                            hashmap.put(f.getName(), p);
                        } catch (IOException ex) {
                            Logger.getLogger(Ambiente.class.getName()).log(Level.SEVERE, "Erro de leitura do properties", ex);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(Ambiente.class.getName()).log(Level.SEVERE, "Lista de arquivos vazia");
        }
    }

    public static Ambiente getInstance() {
        if (instance == null) {
            instance = new Ambiente();
        }
        return instance;
    }

}
