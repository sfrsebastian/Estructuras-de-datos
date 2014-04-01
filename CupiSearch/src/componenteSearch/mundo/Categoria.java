package componenteSearch.mundo;

import ArbolBinOrdenado.ArbolBinarioOrdenado;
import ArbolBinOrdenado.IArbolBinarioOrdenado;

public class Categoria implements Comparable <Categoria> {

	private String nombre;

	private Recurso[] recurso;
	
	private String descripcion;

	private ArbolBinarioOrdenado recursos;

	public Categoria(String nNombre, String nDescrip) {
		nombre = nNombre;
		descripcion = nDescrip;
		recursos = new ArbolBinarioOrdenado<Recurso>();
	}

	public boolean agregarRecurso(Recurso nrec) {
		return recursos.agregar(nrec);
	}

	public boolean eliminarRecurso(Recurso nrecurso) {
		return recursos.eliminar(nrecurso);
	}
	
	public ArbolBinarioOrdenado<Recurso> getRecursos(){
		return recursos;
	}

	@Override
	public int compareTo(Categoria o) {
		return nombre.compareTo(o.nombre);
	}
	
	public String toString(){
		return nombre + " : " + descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion(){
		return descripcion;
	}
	
	public void setDescripcion(String nDeString){
		descripcion = nDeString;
	}
	
	public void setNombre(String nNombre){
		nombre = nNombre;
	}
}
