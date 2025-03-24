package listaencadeada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trabalho_unifor.entities.Palavra;

public class ListaEncadeada<T> {
	private No inicio;
	private No fim;
	Map<String, Palavra> mp = new HashMap<>();

	public void adicionarNaLista(Palavra palavra) {
		No celula = new No(palavra);

		if (inicio == null) {
			inicio = celula;
		} else {
			fim.setProximo(celula);
		}
		fim = celula;

		mp.put(palavra.getPalavra(), palavra);
	}

	public void adicionarPalavra(String palavraTexto, int linha) {
		Palavra palavra = mp.get(palavraTexto);

		if (palavra == null) {
			palavra = new Palavra(palavraTexto);
			palavra.adicionarOcorrencia(linha);
			adicionarNaLista(palavra);
		} else {
			palavra.adicionarOcorrencia(linha);
		}
	}

	public List<Palavra> imprimir() {
		List<Palavra> lista = new ArrayList<>();
		No atual = inicio;
		while (atual != null) {
				// System.out.println(atual.getElemento().getPalavra() + " " + atual.getElemento().getOcorrencias());
				lista.add(atual.getElemento());
				atual = atual.getProximo();
		}
		return lista;
	}

}
