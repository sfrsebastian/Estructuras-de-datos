package Arbol23;

import java.io.Serializable;
import java.util.Iterator;

import Lista.ILista;

public class Nodo23<T extends Comparable<T>> implements Serializable {
	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * Serial identificador de la clase.
	 */
	private static final long serialVersionUID = -2765440002159464767L;

	/**
	 * El nodo izquierdo que contiene los elementos menores a los elementos del nodo.
	 */
	private Nodo23<T> izquierda;
	
	/**
	 * El nodo de la mitad que contiene los elementos entre los elementos del nodo.
	 */
	private Nodo23<T> mitad;
	
	/**
	 * El nodo de la mitad que contiene los elementos mayores a los elementos del nodo.
	 */
	private Nodo23<T> derecha;
	
	/**
	 * El elemento menor del nodo
	 */
	private T elementoIzquierdo;
	
	/**
	 * El elemento mayor del nodo
	 */
	private T elementoDerecho;
	
	//--------------------
	//CONSTRUCTOR
	//--------------------
	/**
	 * Crea un nuevo nodo triario sin elementos
	 * @pos Los elementos se inicializan en null.
	 * @pos Los nodos izquierdo,mitad y derecho se inicializan en null.
	 */
	public Nodo23(T elemento){
		izquierda = null;
		mitad = null;
		derecha = null;
		elementoIzquierdo = elemento;
		elementoDerecho = null;
	}
	
	//--------------------
	//METODOS AUXILIARES
	//--------------------
	/**
	 * Indica si el nodo es hoja.
	 * @return TRUE si todos sus hijos son nulos, FALSE de lo contrario.
	 */
	private boolean esHoja() {
		return izquierda==null && mitad == null && derecha == null;
	}
	/**
	 * Indica si los dos elementos del nodo existen
	 * @return TRUE si ambos elementos existe, FALSE de lo contrario
	 */
	private boolean estaLleno() {
		return elementoDerecho != null;
	}
	/**
	 * Elimina el elemento dado por parametro del nodo
	 * @param elemento El elemento a eliminar.
	 * @return El elemento eliminado, NULL de lo contrario.
	 */
	private T eliminarElemento(T elemento) {
		T respuesta = null;
		if(elementoIzquierdo!= null && elementoDerecho != null){
			if(elementoIzquierdo.compareTo(elemento) == 0){
				elementoIzquierdo = elementoDerecho;
				elementoDerecho = null;
				respuesta = elemento;
			}
			else if(elementoDerecho.compareTo(elemento)==0){
				elementoDerecho = null;
				respuesta = elemento;
			}
		}
		return respuesta;
	}
	/**
	 * Agrega el elemento dado por parametro al nodo
	 * @param elemento El elemento a agregar
	 * @return El elemento agregado si agrego, NULL de lo contrario.
	 */
	private T agregarElemento(T elemento) {
		T respuesta = null;
		if(elementoDerecho == null){
			int comparacion = elementoIzquierdo.compareTo(elemento);
			if(comparacion>0){
				elementoDerecho = elementoIzquierdo;
				elementoIzquierdo = elemento;
				respuesta = elemento;
			}
			else if(comparacion<0){
				elementoDerecho = elemento;
				respuesta = elemento;
			}
		}
		return respuesta;
	}
	
	//--------------------
	//METODOS
	//--------------------
	/**
	 * Agrega el elemento dado por parametro.
	 * @param elemento El elemento a agregar.
	 * @return El nodo resultante de el procedimiento.
	 */
	public Nodo23<T> agregar (T elemento){
		Nodo23<T> respuesta = this;
		int comparacionIzquierda = elementoIzquierdo.compareTo(elemento);
		int comparacionDerecha = 0;
		if(elementoDerecho != null){
			comparacionDerecha = elementoDerecho.compareTo(elemento);
		}
		if(esHoja()){
			if(!estaLleno()){
				agregarElemento(elemento);
				respuesta = this;
			}
			else{
				if(comparacionIzquierda>0){
					//Rotacion con mitad en elemento izquierdo
					Nodo23<T> nuevo = new Nodo23<T>(eliminarElemento(elementoIzquierdo));
					Nodo23<T> izq = new Nodo23<T>(elemento);
					nuevo.izquierda = izq;
					nuevo.derecha = this;
					respuesta = nuevo;
				}
				else if(comparacionDerecha<0){
					//Rotacion con mitad en elemento derecho
					Nodo23<T> nuevo = new Nodo23<T>(eliminarElemento(elementoDerecho));
					Nodo23<T> der = new Nodo23<T>(elemento);
					nuevo.derecha = der;
					nuevo.izquierda = this;
					respuesta = nuevo;
				}
				else if(comparacionDerecha>0){
					//Rotacion con mitad en elemento agregado
					Nodo23<T> nuevo = new Nodo23<T>(elemento);
					Nodo23<T> izq = new Nodo23<T>(eliminarElemento(elementoIzquierdo));
					nuevo.derecha = this;
					nuevo.izquierda = izq;
					respuesta = nuevo;
				}
				
			}
		}
		else if(comparacionIzquierda>0){
			if(izquierda != null){
				Nodo23<T> recibido = izquierda.agregar(elemento);
				int h1 = derecha.darAltura();
				int h2 = recibido.darAltura();
				if(!estaLleno() && !recibido.estaLleno() && h1+1 == h2 ){
					//this No esta lleno, recibido no esta lleno y altura de recibido es mayor en 1 que derecha
					agregarElemento(recibido.elementoIzquierdo);
					izquierda = recibido.izquierda;
					mitad = recibido.derecha;
					respuesta = this;
				}
				else if(estaLleno() && !recibido.estaLleno() && h1+1 == h2 ){
					//esta lleno, recibido no esta lleno y altura de recibido supera altura de 
					izquierda = recibido;
					Nodo23<T> nuevo = new Nodo23<T>(eliminarElemento(elementoDerecho));
					nuevo.izquierda = mitad;
					mitad = null;
					nuevo.derecha = derecha;
					derecha = nuevo;
					respuesta = this;
				}
				else if(h1 == h2){
					//Caso1: this no esta lleno ,recibido es hoja llena , izquierda.altura == derecha.altura == recibido.altura -- Caso2: this no esta lleno, recibido es arbol sin llenar, recibido.altura==derecha.altura -- Caso3: this no esta lleno, recibido no es hoja pero esta lleno y recibido.altura == derecha.altura -- Caso 4: this esta lleno recibido es hoja llena y derecha.altura == recibido.altura
					izquierda = recibido;
					respuesta = this;
				}
				else{
					System.out.println("Caso no aplica: " + elemento);
				}
				
//				else if(estaLleno() && !recibido.esHoja() && recibido.estaLleno() && recibido.derecha == null && recibido.izquierda== null && recibido.mitad == null){//estaLleno() && !recibido.esHoja()){
//				
//				Nodo23<T> nuevo = new Nodo23<T>(eliminarElemento(elementoIzquierdo));
//				izquierda = mitad;
//				mitad = null;
//				nuevo.izquierda= recibido;
//				nuevo.derecha = this;
//				respuesta = nuevo;
//			}
//			else if(!estaLleno() && recibido.estaLleno() && recibido.derecha != null && recibido.izquierda!=null && recibido.mitad == null &&!recibido.derecha.estaLleno() && !recibido.izquierda.estaLleno()){
//				
//				mitad = izquierda;
//				agregarElemento(recibido.eliminarElemento(recibido.elementoDerecho));
//				izquierda = recibido;
//				respuesta = this;
//			}
//			else if(estaLleno() && !recibido.estaLleno() && recibido.esHoja()){
//				
//				izquierda = recibido;
//				Nodo23<T> der = new Nodo23<T>(eliminarElemento(elementoDerecho));
//				der.derecha = derecha;
//				der.izquierda = mitad;
//				mitad = null;
//				derecha = der;
//				respuesta = this;
//			}
			}
		}
		else if(comparacionDerecha<0 || (comparacionDerecha == 0 && elementoDerecho == null)){
			if(derecha != null){
				Nodo23<T> recibido = derecha.agregar(elemento);
				int h1 = izquierda.darAltura();
				int h2 = recibido.darAltura();
				if(!estaLleno() && !recibido.estaLleno() && h1+1 == h2){
					//this no esta lleno, recibido.altura es mayor en 1 que altura de izquierda
					agregarElemento(recibido.elementoIzquierdo);
					derecha = recibido.derecha;
					mitad = recibido.izquierda;
					respuesta = this;
				}
				else if(estaLleno() && !recibido.estaLleno() && h1+1 == h2){
					//Caso 1: Izquierda y mitad tienen un elemento, this esta lleno,recibido no esta lleno, recibido.altura es mayor en 1 que izquierda y mitad
					derecha = recibido;
					Nodo23<T> izq = new Nodo23<T>(eliminarElemento(elementoIzquierdo));
					izq.izquierda = izquierda;
					izq.derecha = mitad;
					mitad = null;
					izquierda = izq;
					respuesta = this;
				}
				else if(h1==h2){
					//Caso1: this no esta lleno ,recibido es hoja llena , izquierda.altura == derecha.altura == recibido.altura -- Caso2: this no esta lleno, recibido es arbol sin llenar, recibido.altura==izquierda.altura Caso3: this no esta lleno, recibido no es hoja pero esta lleno y recibido.altura == izquierdo.altura -- Caso 4: this esta lleno recibido es hoja llena y derecha.altura == recibido.altura
					derecha = recibido;
					respuesta = this;
				}
				else{
					System.out.println("Caso no aplica " + elemento);
				}
				
//				else if(estaLleno() && !recibido.esHoja() && recibido.estaLleno() && recibido.izquierda == null && recibido.derecha== null && recibido.mitad == null){
//				
//				Nodo23<T> nuevo = new Nodo23<T>(eliminarElemento(elementoDerecho));
//				derecha = mitad;
//				mitad = null;
//				nuevo.derecha= recibido;
//				nuevo.izquierda = this;
//				respuesta = nuevo;
//			}
//			else if(!estaLleno() && recibido.estaLleno() && recibido.izquierda != null && recibido.derecha!=null && recibido.mitad == null &&!recibido.izquierda.estaLleno() && !recibido.derecha.estaLleno()){
//				
//				mitad = derecha;
//				agregarElemento(recibido.eliminarElemento(recibido.elementoIzquierdo));
//				derecha = recibido;
//				respuesta = this;
//			}
			}
		}
		else if(comparacionIzquierda<0 && comparacionDerecha>0){
			if(mitad != null){
				Nodo23<T> recibido = mitad.agregar(elemento);
				int h1 = izquierda.darAltura();
				int h2 = derecha.darAltura();
				int h3 = recibido.darAltura();
				if(estaLleno() && !recibido.estaLleno() && h1+1==h3 && h2+1 == h3){
					Nodo23<T> izq = new Nodo23<T>(eliminarElemento(elementoIzquierdo));
					izq.izquierda = izquierda;
					izq.derecha = recibido.izquierda;
					izquierda = recibido.derecha;
					recibido.izquierda = izq;
					mitad = null;
					recibido.derecha = this;
					return recibido;
				}
				else if(h1 == h2 && h1 == h3){
					mitad = recibido;
					respuesta = this;
				}
				else{
					System.out.println("Caso no aplica "+ elemento);
				}
//				if(estaLleno() && !recibido.esHoja() && recibido.estaLleno() && recibido.izquierda != null && !recibido.izquierda.esHoja() ){
//				System.out.println("entro");
//				Nodo23<T> nuevo = new Nodo23<T>(eliminarElemento(elementoDerecho));
//				nuevo.derecha = derecha;
//				nuevo.izquierda= recibido.derecha;
//				nuevo.agregarElemento(recibido.elementoIzquierdo);
//				derecha = recibido.izquierda;
//				mitad = null;
//				respuesta = nuevo;
//			}
			}
		}
		return respuesta;
	}
	
	/**
	 * Busca el elemento dado por parametro
	 * @param elemento El elemento a buscar
	 * @return El elemento encontrado, null de lo contrario.
	 */
	public T buscar(T elemento){
		T respuesta = null;
		int comparacionIzq = -1000;
		int comparacionDer = -1000;
		
		if(elementoIzquierdo != null)
			 comparacionIzq = elemento.compareTo(elementoIzquierdo);
		
		if(elementoDerecho != null)
			comparacionDer = elemento.compareTo(elementoDerecho);
		
		if(comparacionIzq<0 && comparacionIzq != -1000){
			if(izquierda != null)
				respuesta = izquierda.buscar(elemento);
		}
		else if(comparacionIzq == 0){
			respuesta = elementoIzquierdo;
		}
		else if(comparacionDer<0 && comparacionDer != -1000){
			if(mitad != null)
				respuesta = mitad.buscar(elemento);
		}
		else if(comparacionDer == 0){
			respuesta = elementoDerecho;
		}
		else if(derecha != null && comparacionDer != -1000 && comparacionIzq != -1000){
			respuesta = derecha.buscar(elemento);	
		}
	
		return respuesta;
	}
	
	/**
	 * Elimina el elemento dado por parametro
	 * @param elemento El elemento a eliminar
	 * @return TRUE si se elimina correctamente, FALSE de lo contrario
	 */
	public boolean eliminar(T elemento){
		return true;
	}
	
	/**
	 * Recorre los elementos en orden ascendente.
	 * @param lista La lista donde agregar los elementos
	 */
	public void recorrerInorden(ILista<T> lista) {
		if(izquierda != null)
			izquierda.recorrerInorden(lista);
		
		if(elementoIzquierdo != null)
			lista.agregar(elementoIzquierdo);
		
		if(mitad != null)
			mitad.recorrerInorden(lista);
		
		if(elementoDerecho != null)
			lista.agregar(elementoDerecho);
		
		if(derecha != null)
			derecha.recorrerInorden(lista);
	}
	
	/**
	 * Retorna la altura del nodo.
	 * @return La altura del nodo.
	 */
	public int darAltura() {
		int izq = 0;
		int centro = 0;
		int der = 0;
		if(izquierda != null)
			izq = izquierda.darAltura();
		
		if(mitad != null)
			centro = mitad.darAltura();
		
		if(derecha != null)
			der = derecha.darAltura();
		
		return Math.max(Math.max(izq, centro), der)+1;
	}
	
	public String toString(){
		return "Elemento Izquierdo: " + elementoIzquierdo + " Elemento Derecho: "+ elementoDerecho;
	}
}
