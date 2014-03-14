package ArbolBinOrdenado;

import java.util.Comparator;

public class NodoArbolBinarioAVL<T> extends NodoArbolBinario<T> {

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
	private NodoArbolBinarioAVL<T> izquierdo;
	
	/**
	 * El nodo derecho que tiene el nodo
	 */
	private NodoArbolBinarioAVL<T> derecho;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public NodoArbolBinarioAVL(T nElemento, Comparator c) {
		super(nElemento, c);
		indBalanceo = 0;
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------

	public boolean agregar(T elemento){
		boolean resp = super.agregar(elemento);
		balancearXAltura();
		return resp;
	}
	
	public T eliminar(T elemento){
		T elem = super.eliminar(elemento);
		balancearXAltura();
		return elem;
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
	
	private void calcularAltura_e_Indicador() {
		// TODO Auto-generated method stub
	}

	public NodoArbolBinarioAVL balancearXAltura(){
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
				return this.rotarDerecha(); 
			}
			else if ( izquierdo.indBalanceo == -1 ){ 
				return this.rotarIzquierdaDerecha(); 
			}
			else { 
				return this.rotarIzquierdaDerecha(); 
			}
		}
		else if ( indBalanceo == -2 ) {
			if ( derecho.indBalanceo == 1 ){ 
				return this.rotarIzquierda(); 
			}
			else if ( derecho.indBalanceo == -1 ){ 
				return this.rotarDerechaIzquierda(); 
			}
			else { 
				return this.rotarDerechaIzquierda(); 
			}
		}
		return null;
	}
}

