package Arbol23;

import java.util.Iterator;

import ListaEncadenada.ListaEncadenada;

public class Arbol23<T extends Comparable<T>> implements IArbol23<T> {

	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * Serial identficador de la clase.
	 */
	private static final long serialVersionUID = -7284027477869632087L;

	/**
	 * La raiz del arbol triario
	 */
	private Nodo23<T> raiz;

	/**
	 * La cantidad de elementos en el arbol
	 */
	private int peso;

	//--------------------
	//CONSTRUCTOR
	//--------------------
	/**
	 * Crea un nuevo arbol triario ordenado (2-3)
	 * @pos El peso se inicia en 0
	 * @pos La raiz es nula.
	 */
	public Arbol23(){
		raiz = null;
		peso = 0;
	}

	//--------------------
	//METODOS
	//--------------------
	public boolean agregar(T elemento) throws Exception{
		boolean respuesta = false;
		if(buscar(elemento) == null){
			respuesta = true;
		}
		if(respuesta){
			if(raiz == null)
				raiz = new Nodo23<T>(elemento);
			else
				raiz = raiz.agregar(elemento);
			peso++;
		}
		return respuesta;
	}

	public T buscar(T elemento) {
		T respuesta = null;
		if(raiz != null)
			respuesta=raiz.buscar(elemento);

		return respuesta;
	}

	public T eliminar(T elemento) throws Exception{
		T respuesta = null;
		if(buscar(elemento) != null){
			respuesta = elemento;
		}
		if(respuesta != null && raiz != null){
			raiz = raiz.eliminar(elemento);
			peso--;
		}
		return respuesta;
	}

	public int darPeso() {
		return peso;
	}

	public int darAltura() {
		int respuesta = 0;
		if(raiz != null)
			respuesta=raiz.darAltura();

		return respuesta;
	}

	public Iterator<T> recorrerInorden() {
		ListaEncadenada<T> lista = new ListaEncadenada<T>();
		if(raiz != null)
			raiz.recorrerInorden(lista);

		return lista.iterator();
	}

	public Iterator<T> iterator() {
		return recorrerInorden();
	}
}
