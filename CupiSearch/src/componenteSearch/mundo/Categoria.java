package componenteSearch.mundo;

import ArbolBinOrdenado.IArbolBinarioOrdenado;

public class Categoria implements Comparable <Categoria> {

	private String nombre;

	private Recurso[] recurso;

	private IArbolBinarioOrdenado recursos;

	public Categoria(String nombre) {

	}

	public boolean agregarRecurso(Recurso recurso) {
		return false;
	}

	public boolean eliminarRecurso(Recurso recurso) {
		return false;
	}

	@Override
	public int compareTo(Categoria o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
