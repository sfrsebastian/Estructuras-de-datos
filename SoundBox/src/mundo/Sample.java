package mundo;

import java.io.File;
import java.io.Serializable;

import Lista.Lista;
import javafx.scene.media.Media;
import javafx.util.Duration;


/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Sample implements Comparable<Sample>,Serializable {//,ISonido{
	private String nombre;
	private File archivo;
	private Lista <Categoria> categorias;
	private Media media;
	
	public Sample(File f, Categoria nCategoria){
		categorias = new Lista <Categoria>();
		categorias.agregar(nCategoria);
		archivo = f;
		nombre = archivo.getName();
		media = new Media(archivo.toURI().toString());
		verificarInvariante();
	}

	public Media darMedia(){
		return media;
	}
	
	public Duration darDuracion(){
		return media.getDuration();
	}
	
	public String darNombre() {
		return nombre;
	}

	public String cambiarNombre(String nNombre){
		nombre = nNombre;
		verificarInvariante();
		return nNombre;
	}
	
	public Categoria[] darCategorias(){
		return (Categoria[]) categorias.darArreglo();
	}
	
	public Categoria agregarCategoria(Categoria nCategoria){
		return categorias.agregar(nCategoria);
	}
	
	public Categoria eliminarCategoria(Categoria categoria) {
		return categorias.eliminar(categoria);	
	}
	
	@Override
	public int compareTo(Sample aComparar) {
		if(nombre.compareTo(aComparar.darNombre())<0){
			return -1;
		}
		else if(nombre.compareTo(aComparar.darNombre())>0){
			return 1;
		}
		else{
			return 0;
		}	
	}
	private void verificarInvariante(){
		assert nombre != null && nombre != "":"Nombre incorrecto";
	}
	
	public String toString(){
		return  nombre + " - " + media.getDuration().toString();
	}
}