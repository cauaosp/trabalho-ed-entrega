package trabalho_unifor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import listaencadeada.ListaHash;

public class Program {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String texto_leitura = "texto_para_leitura.txt";
		String texto_input = "texto_input.txt";
		List<String> lista_palavras_chave = new ArrayList<>();
		ListaHash listaAlfabetica = new ListaHash();

		try (BufferedReader br = new BufferedReader(new FileReader(texto_leitura))) {
			String line = br.readLine();

			int linha = 0;

			while (line != null) {
				linha++;
				String[] field = line.split("\\s+");
				for (String palavra : field) {
					listaAlfabetica.adicionarPalavra(palavra.replaceAll("[^a-zA-Z-]", ""), linha);
				}

				line = br.readLine();
			}

			listaAlfabetica.visualizarHash();
		}

		try (BufferedReader br = new BufferedReader(new FileReader(texto_input))) {
			String line = br.readLine();

			while (line != null) {
				String[] field = line.replaceAll("[,.]", "").toLowerCase().split("\\s+");
				lista_palavras_chave.addAll(Arrays.asList(field));
				line = br.readLine();
			}

			Collections.sort(lista_palavras_chave);

		}

		System.out.println(lista_palavras_chave);

		listaAlfabetica.escreverResultadosEmArquivo(lista_palavras_chave);
	}
}
