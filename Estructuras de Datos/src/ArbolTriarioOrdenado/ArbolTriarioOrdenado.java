package ArbolTriarioOrdenado;

import java.util.Iterator;

import ListaEncadenada.ListaEncadenada;

public class ArbolTriarioOrdenado<T extends Comparable<T>> implements IArbolTriario<T> {

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
	private NodoTriario<T> raiz;
	
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
	public ArbolTriarioOrdenado(){
		raiz = null;
		peso = 0;
	}
	
	//--------------------
	//METODOS
	//--------------------
	public boolean agregar(T elemento) {
		boolean respuesta = true;
		if(raiz == null)
			raiz = new NodoTriario<T>(elemento);
		else
			respuesta = raiz.agregar(elemento);
		
		if(respuesta)
			peso++;
		
		return respuesta;
	}

	public T buscar(T elemento) {
		T respuesta = null;
		if(raiz != null)
			respuesta=raiz.buscar(elemento);
		
		return respuesta;
	}

	public boolean eliminar(T elemento) {
		boolean respuesta = false;
		if(raiz != null)
			respuesta=raiz.eliminar(elemento);
		
		if(respuesta)
			peso--;
		
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
