package componenteSearch.mundo;


import ArbolAVl.ArbolBinarioAVLOrdenado;
import ArbolBinOrdenado.IArbolBinarioOrdenado;

public class Categoria implements Comparable <Categoria> {

	private String nombre;
	private String descripcion;
	private ArbolBinarioAVLOrdenado<Recurso> recursos;


	public Categoria(String nNombre, String nDescripcion){
		nombre = nNombre;
		descripcion = nDescripcion;
		recursos = new ArbolBinarioAVLOrdenado<Recurso>();
	}
	public boolean agregarRecurso(Recurso nrec) {
		return recursos.agregar(nrec);
	}

	public boolean eliminarRecurso(Recurso nrecurso) {
		return recursos.eliminar(nrecurso);
	}
	
	public ArbolBinarioAVLOrdenado<Recurso> getRecursos(){
		return recursos;
	}

	@Override
	public int compareTo(Categoria o) {
		return nombre.compareTo(o.nombre);
	}
	
	public String toString(){
		return nombre;
	}
	public String comprimir(){
		String respuesta = "[nombre:" + nombre + "~"+ "descripcion:"+descripcion+"~"+"recursos{";
		if(recursos.darPeso() != 0){
			for (Recurso actual : recursos) {
				respuesta+= actual.getTipo()==Recurso.IMAGEN?actual.getImgUrl():actual.getDescripcion();
				respuesta+="~";
			}
			respuesta = respuesta.substring(0,respuesta.length()-1);
		}
		respuesta+="}]";
		return respuesta;
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
