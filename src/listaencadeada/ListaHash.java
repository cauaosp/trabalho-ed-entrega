package listaencadeada;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import trabalho_unifor.entities.Palavra;

public class ListaHash {
  Map<String, ListaEncadeada<Palavra>> tabelaHash = new HashMap<>();

  public ListaHash() {
    for (char c = 'A'; c <= 'Z'; c++) {
      tabelaHash.put(String.valueOf(c).toUpperCase(), new ListaEncadeada<Palavra>());
    }
  }

  public void adicionarPalavra(String palavra, int linha) {
    palavra = palavra.replaceAll("[^a-zA-Z-]", "").trim();
    if (palavra == null || palavra.isEmpty()) {
      return;
    }

    String letraInicial = String.valueOf(palavra.charAt(0)).toUpperCase();

    if (tabelaHash.containsKey(letraInicial)) {
      tabelaHash.get(letraInicial).adicionarPalavra(palavra, linha);
    }
  }

  public void visualizarHash() {
    for (char c = 'A'; c <= 'Z'; c++) {
      String chave = String.valueOf(c);
      ListaEncadeada<Palavra> listaPalavras = tabelaHash.get(chave);

      System.out.print(chave.toLowerCase() + ": ");

      if (listaPalavras != null && !listaPalavras.imprimir().isEmpty()) {
        StringBuilder sb = new StringBuilder();
        
        for (Palavra palavra : listaPalavras.imprimir()) {
          sb.append(palavra.getPalavra()).append(" -> ");
        }

        System.out.println(sb.toString() + "null");
      } else {
        System.out.println();
      }
    }
  }

  public void escreverResultadosEmArquivo(List<String> listaParaBuscar) {
    File arquivoDeSaida = new File("../src/indice_remissivo.txt");
    List<Palavra> lista = new ArrayList<>();

    for (char c = 'A'; c <= 'Z'; c++) {
      tabelaHash.get(String.valueOf(c)).imprimir().forEach(p -> lista.add(p));
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoDeSaida))) {
      for (String palavraBuscada : listaParaBuscar) {
        for (Palavra palavra : lista) {
          if (palavra.getPalavra().equalsIgnoreCase(palavraBuscada)) {
            writer.write(palavra.getPalavra() + " " + palavra.getOcorrencias().toString().replaceAll("[\\[\\]]", ""));
            writer.newLine();
            break;
          }
        }
      }
      System.out.println("indice remissivo gerado!");

    } catch (IOException e) {
      System.out.println("Erro ao escrever no arquivo.");
      e.printStackTrace();
    }
  }
}
