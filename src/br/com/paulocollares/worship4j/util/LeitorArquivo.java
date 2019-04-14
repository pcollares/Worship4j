package br.com.paulocollares.worship4j.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Worship4j - http://collares.net.br/worship4j/
 *
 * @author Paulo Collares
 */
public class LeitorArquivo {

    private Map map = new HashMap();
    private String arquivo;
    List<String> linhas = new ArrayList<>();

    /**
     * No construtor da classe deve ser passado o caminho do arquivo a ser lido
     * O arquivo é lido apenas no construtor, evitando acesso ao disco, salvo se
     * o método lerArquivo() for chamado novamente
     *
     * @param arquivo
     */
    public LeitorArquivo(String arquivo) {
        lerArquivo(arquivo);
    }

    /**
     * Força a releitura do arquivo
     */
    public void lerArquivo() {
        leituraArquivo();
    }

    /**
     * Força a releitura do arquivo, passando como parâmetro um novo arquivo
     *
     * @param arquivo
     */
    public void lerArquivo(String arquivo) {
        this.arquivo = arquivo;
        leituraArquivo();
    }

    /**
     * Método que realiza a leitura do arquivo, verifica as linhas e salva na
     * lista
     */
    private void leituraArquivo() {

        try {
            FileReader fileReader = new FileReader(arquivo);
            BufferedReader leitor = new BufferedReader(fileReader);

            String linha = null;

            while (leitor.ready()) {
                linha = leitor.readLine();

                if (!linha.contains("=")) {
                    if (linhas.isEmpty() && linha.isEmpty()) {
                        continue;
                    }
                    linhas.add(linha);
                    continue;
                }

                if (linha.charAt(0) == '#' || linha.charAt(0) == '=') {
                    continue;
                }

                String temp[] = linha.split("=");

                map.put(formataChave(temp[0]), temp[1].trim());
            }

            fileReader.close();
            leitor.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

    }

    /**
     * Formata a chave para minúsculo e sem espaços
     */
    private String formataChave(String chave) {

        chave = chave.toLowerCase();
        chave = chave.trim();
        return chave;
    }

    /**
     * Recupera um valor, passando a chave como parâmetro
     *
     * @param chave
     * @return valor
     */
    public String get(String chave) {
        return (String) map.get(formataChave(chave));
    }

    /**
     * Recupera um valor, passando a chave como parâmetro Retorna um valor
     * padrão caso a chave não seja encontrada
     *
     * @param chave
     * @param valor padrão
     * @return valor
     */
    public String get(String chave, String padrao) {
        String r = get(chave);

        if (r == null) {
            r = padrao;
        }

        return r;
    }

    /*
     * Recupera a lista completa
     */
    public Map getList() {
        return map;
    }

    public List<String> getLinhas() {
        return linhas;
    }

}
