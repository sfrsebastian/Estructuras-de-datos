package mundo;

import java.io.File;
import java.io.Serializable;

import Lista.Lista;


/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Sample implements Comparable<Sample>,Serializable {
	/**
	 * El nombre del sonido
	 */
	private String nombre;
	
	/**
	 * La ruta del archivo.
	 */
	private File archivo;
	
	/**
	 * Las categorias a las que pertenece el archivo.
	 */
	private Lista <Categoria> categorias;
	
	/**
	 * La ruta de ubicacion del archivo
	 */
	private String src;
	
	/**
	 * Metodo que construye un nuevo sonido
	 * @param El archivo del sonido
	 * @param nCategoria La categoria a la que pertenece
	 */
	public Sample(File f, Categoria nCategoria){
		categorias = new Lista <Categoria>();
		categorias.agregar(nCategoria);
		archivo = f;
		nombre = archivo.getName();
		src = archivo.toURI().toString();
		verificarInvariante();
	}

	/**
	 * Retorna la ruta del sonido
	 * @return
	 */
	public String darSrc(){
		return src;
	}
	
	/**
	 * Retorna el nombre del sonido
	 * @return
	 */
	public String darNombre() {
		return nombre;
	}

	/**
	 * Cambia el nombre del sonido
	 * @param nNombre El nuevo nombre del sonidos
	 * @return El nuevo nombre del sonido
	 */
	public String cambiarNombre(String nNombre){
		nombre = nNombre;
		verificarInvariante();
		return nNombre;
	}
	
	/**
	 * Retorna las categorias a las que pertenece el sonido
	 * @return
	 */
	public Object[] darCategorias(){
		return categorias.darArreglo();
	}
	
	/**
	 * Agrega una categoria a la lista de categorias.
	 * @param nCategoria La categoria a agregar
	 * @return La categoria agregada.
	 */
	public Categoria agregarCategoria(Categoria nCategoria){
		if(categorias.darLongitud()==1 && ((Categoria)categorias.darArreglo()[0]).darNombre() == Categoria.SIN_CATEGORIA){
			categorias.eliminar((Categoria) categorias.darArreglo()[0]);
			return categorias.agregar(nCategoria);
		}
		return categorias.agregar(nCategoria); 
	}
	
	/**
	 * Elimina la categoria dada por parametro.
	 * @param categoria La categoria a eliminar
	 * @return La categoria eliminada
	 */
	public Categoria eliminarCategoria(Categoria categoria) {
		Categoria c = categorias.eliminar(categoria);	
		if(categorias.darLongitud()==0){
			categorias.agregar(new Categoria());
		}
		return c;
	}
	
	/**
	 * Metodo que compara dos sonidos
	 */
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
	
	/**
	 * Invariante para el sonido
	 */
	private void verificarInvariante(){
		assert nombre != null && nombre != "":"Nombre incorrecto";
	}
	
	/**
	 * Metodo to string del sonido. Retorna su nombre
	 */
	public String toString(){
		return  nombre;
	}
}
