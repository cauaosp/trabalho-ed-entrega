package trabalho_unifor.entities;

import java.util.ArrayList;
import java.util.List;

public class Palavra {
	private String palavra;
	private List<Integer> ocorrencias;

	public Palavra(String palavra) {
		this.palavra = palavra;
		this.ocorrencias = new ArrayList<>();
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public void adicionarOcorrencia(int linha) {
		ocorrencias.add(linha);
	}

	public List<Integer> getOcorrencias() {
		return ocorrencias;
	}

	@Override
	public String toString() {
		return palavra + " - " + ocorrencias;
	}
}
