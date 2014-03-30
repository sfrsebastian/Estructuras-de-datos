package ArbolAVl;

import java.io.Serializable;
import java.util.Comparator;

import ListaEncadenada.ListaEncadenada;

public class NodoArbolBinarioAVL<T extends Comparable <T>> implements Serializable{

	//------------------------------------------
	// Atributos
	//------------------------------------------

	/**
	 * El indicador del balanceo del sub-arbol del nodo
	 */
	private int indBalanceo;

	/**
	 * El nodo izquierdo que tiene el nodo
	 */
	public NodoArbolBinarioAVL<T> izquierdo;

	/**
	 * El nodo derecho que tiene el nodo
	 */
	public NodoArbolBinarioAVL<T> derecho;

	/**
	 * El comparador del nodo
	 */
	private Comparator<T> comparador;

	/**
	 * El elemento del nodo
	 */
	private T elemento;

	//------------------------------------------
	// Constructor
	//------------------------------------------

	public NodoArbolBinarioAVL(T nElemento, Comparator<T> c) {
		elemento = nElemento;
		indBalanceo = 0;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------

	/**
	 * Agrega un nuevo elemento al nodo actual
	 * @param pElemento El elemento que se quiere agregar
	 * @return boolean TRUE si se pudo agregar, FALSE en caso contrario
	 */
	public boolean agregar(T pElemento){
		if(comparar(elemento,pElemento) == 0){
			return false;
		}
		else if(comparar(elemento,pElemento) < 0){
			if(derecho != null){
				return derecho.agregar(pElemento);
			}
			else{
				derecho = new NodoArbolBinarioAVL<T>(pElemento, comparador);
				return true;
			}
		}
		else{
			if(izquierdo != null){
				return izquierdo.agregar(pElemento);
			}
			else{
				izquierdo = new NodoArbolBinarioAVL<T>(pElemento, comparador);
				return true;
			}
		}
	}

	/**
	 * Busca un elemento T en el arbol binario
	 * @param pElemento El elemento que se quiere buscar
	 * @return T el elemento buscado, null en caso contrario
	 */
	public T buscar(T pElemento){
		if(comparar(elemento,pElemento) == 0){
			return darElemento();
		}
		else if(comparar(elemento,pElemento) < 0){
			if(derecho != null){
				return derecho.buscar(pElemento);
			}
		}
		else {
			if(izquierdo != null){
				return izquierdo.buscar(pElemento);
			}
		}
		return null;
	}

	/**
	 * Elimina el elemento dado por parametro
	 * @param pElemento El elemento a eliminar
	 * @return El nodoArbol reorganizado con el elemento eliminado.
	 */
	public NodoArbolBinarioAVL <T> eliminar(T pElemento){
		if(comparar(elemento,pElemento) == 0){
			if(esHoja()){
				return null;
			}
			else if(derecho == null){
				return izquierdo;
			}
			else if(izquierdo == null){
				return derecho;
			}
			else{
				NodoArbolBinarioAVL<T> mayor = izquierdo.darMayor();
				izquierdo = izquierdo.eliminar(mayor.darElemento());
				mayor.derecho = derecho;
				mayor.izquierdo = izquierdo;
				return mayor;
			}
		}
		else if(comparar(elemento,pElemento) < 0){
			if(derecho != null){
				derecho = derecho.eliminar(pElemento);
			}
		}
		else{
			if(izquierdo != null){
				izquierdo = izquierdo.eliminar(pElemento);
			}
		}
		return this;
	}

	/**
	 * Da el elemento que tiene el nodo
	 * @return T el elemento del nodo
	 */
	public T darElemento(){
		return elemento;
	}

	/**
	 * Retorna la altura del arbol
	 * Compara las alturas de los nodos izquierdo y derechos.
	 * @return La altura del arbol
	 */
	public int darAltura() {
		int izq = 0;
		int der = 0;
		if(esHoja()){
			return 1;
		}
		if(izquierdo != null){
			izq = izquierdo.darAltura();
		}
		if(derecho != null){
			der = derecho.darAltura();
		}
		return Math.max(izq, der)+1;
	}

	/**
	 * Agrega a la lista dada por parametro los elementos INORDEN (izquierdo-raiz-derecho)
	 * @param La lista para acumular los valores.
	 */
	public void agregarElementosInorden(ListaEncadenada<T> lista){
		if(izquierdo != null){
			izquierdo.agregarElementosInorden(lista);
		}
		lista.agregar(this.darElemento());
		if(derecho != null){
			derecho.agregarElementosInorden(lista);
		}
	}

	/**
	 *Agrega a la lista dada por parametro los elementos PREORDEN (raiz-izquierdo-derecho)
	 * @param La lista para acumular los valores.
	 */
	public void agregarElementosPreorden(ListaEncadenada<T> lista) {
		lista.agregar(darElemento());
		if(izquierdo != null){
			izquierdo.agregarElementosPreorden(lista);
		}
		if(derecho != null){
			derecho.agregarElementosPreorden(lista);
		}
	}

	/**
	 * Agrega a la lista dada por parametro los elemento POSORDEN (izquierdo-derecho-raiz)
	 * @param La lista para acumular los valores.
	 */
	public void agregarElementosPosorden(ListaEncadenada<T> lista) {
		if(izquierdo != null){
			izquierdo.agregarElementosPosorden(lista);
		}
		if(derecho != null){
			derecho.agregarElementosPosorden(lista);
		}
		lista.agregar(this.darElemento());
	}

	public NodoArbolBinarioAVL<T> rotarDerecha(){
		NodoArbolBinarioAVL<T> k1 = izquierdo;
		izquierdo = k1.derecho;
		k1.derecho = this;

		return k1;
	}

	public NodoArbolBinarioAVL<T> rotarIzquierda(){
		NodoArbolBinarioAVL<T> k1 = derecho;
		derecho = k1.izquierdo;
		k1.izquierdo = this;

		return k1;
	}

	public NodoArbolBinarioAVL<T> rotarIzquierdaDerecha(){
		NodoArbolBinarioAVL<T> k1 = izquierdo;
		izquierdo = k1.rotarIzquierda();
		return rotarDerecha();
	}

	public NodoArbolBinarioAVL<T> rotarDerechaIzquierda(){
		NodoArbolBinarioAVL<T> k1 = derecho;
		derecho = k1.rotarDerecha();
		return rotarIzquierda();
	}

	public boolean esHoja(){
		if(derecho == null && izquierdo == null)
			return true;
		else
			return false;
	}

	public NodoArbolBinarioAVL<T> balancearXAltura(){
		if (izquierdo != null){ 
			izquierdo = izquierdo.balancearXAltura(); 
		}
		if (derecho != null){ 
			derecho = derecho.balancearXAltura(); 
		}
		calcularAltura_e_Indicador( );
		if (-1 <= indBalanceo && indBalanceo <= 1 ){ 
			return this; 
		}
		else if ( indBalanceo == 2 ){
			if ( izquierdo.indBalanceo == 1 ){ 
				//FIJO
				return this.rotarDerecha(); 
			}
			else if ( izquierdo.indBalanceo == -1 ){ 
				//FIJO
				return this.rotarIzquierdaDerecha();
			}
			else { 
				return this.rotarIzquierdaDerecha(); 
			}
		}
		else if ( indBalanceo == -2 ) {
			if ( derecho.indBalanceo == 1 ){ 
				//FIJO
				return this.rotarDerechaIzquierda();
			}
			else if ( derecho.indBalanceo == -1 ){ 
				//FIJO
				return this.rotarIzquierda();
			}
			else { 
				return this.rotarDerechaIzquierda(); 
			}
		}
		return null;
	}

	private int calcularAltura_e_Indicador() {
		int der = 0;
		int izq = 0;

		if(izquierdo != null){
			izq = izquierdo.darAltura();
		}
		if(derecho != null){
			der = derecho.darAltura();
		}

		return indBalanceo = izq-der;
	}

	public boolean esAVL(){
		boolean der = true; 
		boolean izq = true;

		if(izquierdo != null){
			int indicador = izquierdo.calcularAltura_e_Indicador();
			izq = Math.abs(indicador) <= 1;
		}
		if(derecho != null){
			int indicador = derecho.calcularAltura_e_Indicador();
			der = Math.abs(indicador) <= 1;
		}

		return (izq & der);
	}

	/**
	 * Retorna el nodo con el mayor elemento del nodo
	 * @return El mayor nodo.
	 */
	private NodoArbolBinarioAVL<T> darMayor() {
		if(esHoja()){
			return this;
		}
		else{
			if(derecho!=null)
				return derecho.darMayor();
			else
				return this;
		}
	}

	/**
	 * Compara dos elementos por su comparador, si este existe
	 * De lo contrario los compara por su metodo de compareTo
	 * @param elem1 El elemento visitante
	 * @param elem2 El elemento local
	 * @return int El valor de la comparacion 
	 */
	private int comparar(T elem1, T elem2){
		if (comparador == null){
			return elem1.compareTo(elem2);
		}
		else{
			return comparador.compare(elem1, elem2);
		}
	}

	/**
	 * Metodo to string del nodo. Indica quien es y cuales son sus nodos izquierdo y derecho.
	 * @return toString del nodo
	 */
	public String toString(){
		String der = "NO TIENE";
		String izq = "NO TIENE";
		if(derecho != null){
			try {
				der = derecho.darElemento().toString();
			} catch (Exception e) {
				System.out.println("Agregar el metodo toString() del elemento");
				der = "TIENE";
			}
		}
		if(izquierdo != null){
			try {
				izq = izquierdo.darElemento().toString();
			} catch (Exception e) {
				System.out.println("Agregar el metodo toString() del elemento");
				izq = "TIENE";
			}
		}
		return "Soy: " + elemento.toString() + ", derecho: " + der + " izquierdo: " + izq;
	}
}