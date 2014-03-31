package componenteSearch.mundo;

import ArbolBinOrdenado.ArbolBinarioOrdenado;
import ArbolBinOrdenado.IArbolBinarioOrdenado;

public class Categoria implements Comparable <Categoria> {

	private String nombre;

	private Recurso[] recurso;
	
	private String descripcion;

	private IArbolBinarioOrdenado recursos;

	public Categoria(String nNombre, String nDescrip) {
		nombre = nNombre;
		descripcion = nDescrip;
		recursos = new ArbolBinarioOrdenado<Recurso>();
	}

	public boolean agregarRecurso(Recurso nrec) {
		return recursos.agregar(nrec);
	}

	public boolean eliminarRecurso(Recurso recurso) {
		return false;
	}

	@Override
	public int compareTo(Categoria o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString(){
		return nombre + " : " + descripcion;
	}

}
