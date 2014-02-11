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
	private String src;
	
	public Sample(File f, Categoria nCategoria){
		categorias = new Lista <Categoria>();
		categorias.agregar(nCategoria);
		archivo = f;
		nombre = archivo.getName();
		src = archivo.toURI().toString();
		verificarInvariante();
	}

	public String darSrc(){
		return src;
	}
	
	public String darNombre() {
		return nombre;
	}

	public String cambiarNombre(String nNombre){
		nombre = nNombre;
		verificarInvariante();
		return nNombre;
	}
	
	public Object[] darCategorias(){
		return categorias.darArreglo();
	}
	
	public Categoria agregarCategoria(Categoria nCategoria){
		if(categorias.darLongitud()==1 && ((Categoria)categorias.darArreglo()[0]).darNombre() == Categoria.SIN_CATEGORIA){
			categorias.eliminar((Categoria) categorias.darArreglo()[0]);
			return categorias.agregar(nCategoria);
		}
		return categorias.agregar(nCategoria); 
	}
	
	public Categoria eliminarCategoria(Categoria categoria) {
		Categoria c = categorias.eliminar(categoria);	
		if(categorias.darLongitud()==0){
			categorias.agregar(new Categoria());
		}
		return c;
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
		return  nombre;
	}
}
