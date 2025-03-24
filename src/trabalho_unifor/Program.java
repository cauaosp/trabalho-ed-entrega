package trabalho_unifor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import listaencadeada.ListaEncadeada;
import trabalho_unifor.entities.Palavra;

public class Program {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String texto_leitura = "texto_para_leitura.txt";
		String texto_input = "texto_input.txt";
		List<String> lista_para_buscar = new ArrayList<>();
		List<Palavra> lista_encadeada_palavras_adicionadas = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(texto_leitura))) {
			String line = br.readLine();

			ListaEncadeada<Palavra> lista = new ListaEncadeada<>();
			int linha = 0;

			while (line != null) {
				linha++;
				String[] field = line.split("\\s+");
				for (String palavra : field) {
					lista.adicionarPalavra(palavra.replaceAll("[^a-zA-Z-]", ""), linha);
				}

				line = br.readLine();
			}

			lista_encadeada_palavras_adicionadas = lista.imprimir();

		}

		try (BufferedReader br = new BufferedReader(new FileReader(texto_input))) {
			String line = br.readLine();

			while (line != null) {
				String[] field = line.replaceAll("[,.]", "").split("\\s+");
				lista_para_buscar.addAll(Arrays.asList(field));
				line = br.readLine();
			}
			Collections.sort(lista_para_buscar);

		}

		System.out.println(lista_para_buscar);
		System.out.println(lista_encadeada_palavras_adicionadas);

		escreverResultadosEmArquivo(lista_para_buscar, lista_encadeada_palavras_adicionadas);

	}

	public static void escreverResultadosEmArquivo(List<String> listaParaBuscar, List<Palavra> listaEncadeada) {
		File arquivoDeSaida = new File("../src/indice_remissivo.txt");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoDeSaida))) {
			for (String palavraBuscada : listaParaBuscar) {
				boolean encontrada = false;
				// Percorre a lista encadeada de palavras adicionadas
				for (Palavra palavra : listaEncadeada) {
					if (palavra.getPalavra().equalsIgnoreCase(palavraBuscada)) {
						writer.write(palavra.getPalavra() + " " + palavra.getOcorrencias().toString().replaceAll("[\\[\\]]", ""));
						writer.newLine(); // Nova linha após escrever cada palavra
						encontrada = true;
						break;
					}
				}
			}

			System.out.println("indice remissivo gerado");

		} catch (IOException e) {
			System.out.println("Erro ao escrever no arquivo.");
			e.printStackTrace();
		}
	}
}
