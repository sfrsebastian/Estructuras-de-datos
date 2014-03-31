package componenteSearch.mundo;


import ArbolAVl.ArbolBinarioAVLOrdenado;
import ArbolBinOrdenado.IArbolBinarioOrdenado;

public class Categoria implements Comparable <Categoria> {

	private String nombre;
	private String descripcion;
	private IArbolBinarioOrdenado<Recurso> recursos;



	public Categoria(String nNombre, String nDescripcion){
		nombre = nNombre;
		descripcion = nDescripcion;
		recursos = new ArbolBinarioAVLOrdenado<Recurso>();
	}
	public boolean agregarRecurso(Recurso nrec) {
		return recursos.agregar(nrec);
	}

	public boolean eliminarRecurso(Recurso recurso) {
		return false;
	}

	@Override
	public int compareTo(Categoria o) {
		return nombre.compareTo(o.nombre);
		//return 0;
	}
	public String toString(){
		String respuesta = "[nombre:" + nombre + "_"+ "descripcion:"+descripcion+"_"+"recursos{";
		for (Recurso actual : recursos) {
			respuesta+= actual.getTipo()==Recurso.IMAGEN?actual.getImgUrl():actual.getDescripcion();
			respuesta+="_";
		}
		respuesta = respuesta.substring(0,respuesta.length()-1);
		respuesta+="}]";
		return respuesta;
	}

}
