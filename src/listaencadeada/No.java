package listaencadeada;
import trabalho_unifor.entities.Palavra;

public class No <T> {
	
	private Palavra elemento;
	private No proximo;
    
	public No(Palavra elemento, No proximo) {
		super();
		this.elemento = elemento;
		this.proximo = proximo;
	}
	
	public No(Palavra elemento) {
		super();
		this.elemento = elemento;
		this.proximo = null;
	}

	public Palavra getElemento() {
		return elemento;
	}

	public void setElemento(Palavra elemento) {
		this.elemento = elemento;
	}

	public No getProximo() {
		return proximo;
	}

	public void setProximo(No proximo) {
		this.proximo = proximo;
	}

	@Override
	public String toString() {
		return "No [elemento=" + elemento + ", proximo=" + proximo + "]";
	}
}
