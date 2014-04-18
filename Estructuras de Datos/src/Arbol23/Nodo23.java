package Arbol23;

import java.io.Serializable;
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
	
	/**
	 * Retorna el menor elemento del nodo
	 * @return El menor elemento.
	 */
	private T darMenor() {
		T respuesta = null;;
		if(esHoja()){
			respuesta = elementoIzquierdo;
		}
		else{
			respuesta = izquierda.darMenor();
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
					System.out.println("Caso no aplica izquierda: " + elemento);
				}
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
					System.out.println("Caso no aplica derecha: " + elemento);
				}
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
					System.out.println("Caso no aplica mitad: "+ elemento);
				}
			}
		}
		else{
			System.out.println("Caso no aplica agregar: "+ elemento);
		}
		return respuesta;
	}
	
	/**
	 * Elimina el elemento dado por parametro
	 * @param elemento El elemento a eliminar
	 * @return TRUE si se elimina correctamente, FALSE de lo contrario
	 */
	public Nodo23<T> eliminar(T elemento){
		Nodo23<T> respuesta = this;
		int comparacionIzquierda = elementoIzquierdo.compareTo(elemento);
		int comparacionDerecha = 0;
		if(elementoDerecho != null){
			comparacionDerecha = elementoDerecho.compareTo(elemento);
		}
		if(esHoja()){
			if(estaLleno()){
				eliminarElemento(elemento);
				respuesta = this; 		
			}
			else if(comparacionIzquierda == 0){
				respuesta = null;
			}
		}
		else if(comparacionIzquierda == 0 && !estaLleno()){
			if(!derecha.estaLleno() && !izquierda.estaLleno()){
				//Revisado Twice
				T menorDer = derecha.darMenor();
				derecha.eliminar(menorDer);
				izquierda.agregarElemento(menorDer);
				izquierda.mitad = izquierda.derecha;
				izquierda.derecha = derecha;
				respuesta = izquierda;
			}
			else if(izquierda.estaLleno() && !derecha.estaLleno()){
				//Revisado Twice
				T menorDer = derecha.darMenor();
				derecha.eliminar(menorDer);
				Nodo23<T> der = new Nodo23<T>(menorDer);
				der.derecha = derecha;
				der.izquierda = izquierda.derecha;
				izquierda.derecha = izquierda.mitad;
				izquierda.mitad = null;
				Nodo23<T> nuevo = new Nodo23<T>(izquierda.eliminarElemento(izquierda.elementoDerecho));
				nuevo.izquierda = izquierda;
				nuevo.derecha = der;
				respuesta = nuevo;
			}
			else{
				//Revisado twice
				T menorDer = derecha.darMenor();
				derecha.eliminar(menorDer);
				Nodo23<T> nuevo = new Nodo23<T>(menorDer);
				nuevo.izquierda = izquierda;
				nuevo.derecha = derecha;
				respuesta = nuevo;
			}
		}
		else if(comparacionIzquierda == 0){
			if(derecha.estaLleno() && !mitad.estaLleno()){
				//Revisado twice
				eliminarElemento(elemento);
				T menorMit = mitad.darMenor();
				mitad.eliminar(menorMit);
				Nodo23<T> nuevo = new Nodo23<T>(menorMit);
				nuevo.agregarElemento(derecha.eliminarElemento(derecha.elementoIzquierdo));
				Nodo23<T>derizq = derecha.izquierda;
				derecha.izquierda = derecha.mitad;
				derecha.mitad = null;
				nuevo.derecha = derecha;
				nuevo.izquierda = izquierda;
				izquierda = mitad;
				derecha = derizq;
				mitad = null;
				nuevo.mitad = this;
				respuesta = nuevo;
			}
			else{
				//Revisado twice
				T menorMit = mitad.darMenor();
				mitad.eliminar(menorMit);
				eliminarElemento(elemento);
				agregarElemento(menorMit);
				respuesta = this;
			}
		}
		else if(comparacionDerecha == 0 && elementoDerecho!=null){
			//if(derecha.estaLleno() && !mitad.estaLleno()){
				//Revisado twice // guarda por mirar...
				T menorDer = derecha.darMenor();
				derecha.eliminar(menorDer);
				eliminarElemento(elemento);
				agregarElemento(menorDer);
				respuesta = this;
			//}
		}
		else if(comparacionIzquierda>0){
			if(izquierda != null){
				Nodo23<T> recibido = izquierda.eliminar(elemento);
				int h1 = derecha.darAltura();
				int h2 = recibido!=null?recibido.darAltura():0;
				if(!estaLleno() && h1 == h2+1 && !derecha.estaLleno()){
					//Revisado twice
					agregarElemento(derecha.elementoIzquierdo);
					izquierda = recibido;
					mitad = derecha.izquierda;
					derecha = derecha.derecha;
					respuesta = this;
				}
				else if(!estaLleno() && h1 == h2+1 && derecha.estaLleno()){
					//Revisado twice
					agregarElemento(derecha.eliminarElemento(derecha.elementoIzquierdo));
					Nodo23<T> izq = new Nodo23<T>(eliminarElemento(elementoIzquierdo));
					izq.izquierda = recibido;
					izq.derecha = derecha.izquierda;
					derecha.izquierda = derecha.mitad;
					derecha.mitad = null;
					izquierda = izq;
					respuesta = this;
				}
				else if(estaLleno() && h1 == h2+1 && derecha.estaLleno() && !mitad.estaLleno()){
					//Revisado Twice
					Nodo23<T> mit = new Nodo23<T>(eliminarElemento(elementoDerecho));
					agregarElemento(mitad.elementoIzquierdo);
					Nodo23<T> izq = new Nodo23<T>(eliminarElemento(elementoIzquierdo));
					agregarElemento(derecha.eliminarElemento(derecha.elementoIzquierdo));
					izq.izquierda = recibido;
					izq.derecha = mitad.izquierda;
					mit.izquierda = mitad.derecha;
					mit.derecha = derecha.izquierda;
					derecha.izquierda = derecha.mitad;
					derecha.mitad = null;
					izquierda = izq;
					mitad = mit;
					respuesta = this;
				}
				else if(estaLleno() && h1 == h2+1 && !derecha.estaLleno() && !mitad.estaLleno()){
					//Revisado Twice 
					Nodo23<T> izq = new Nodo23<T>(eliminarElemento(elementoIzquierdo));
					izq.izquierda = recibido;
					izq.derecha = mitad.izquierda;
					agregarElemento(mitad.elementoIzquierdo);
					derecha.agregarElemento(eliminarElemento(elementoDerecho));
					derecha.mitad = derecha.izquierda;
					derecha.izquierda = mitad.derecha;
					mitad = null;
					izquierda = izq;
					respuesta = this;
				}	
				else if(mitad != null && mitad.estaLleno() && h1 == h2+1 ){
					//revisado twice
					Nodo23<T> izq = new Nodo23<T>(eliminarElemento(elementoIzquierdo));
					izq.izquierda = recibido;
					izq.derecha = mitad.izquierda;
					mitad.izquierda = mitad.mitad;
					mitad.mitad = null;
					agregarElemento(mitad.eliminarElemento(mitad.elementoIzquierdo));
					izquierda = izq;
					respuesta = this;
				}
				else if(h1 == h2){
					//Revisado twice
					izquierda = recibido;
					respuesta = this;
				}
				else{
					System.out.println("Caso no aplica izquierda: " + elemento);
				}
			}
		}
		else if(comparacionDerecha<0 || (comparacionDerecha == 0 && elementoDerecho == null)){
			if(derecha != null){
				Nodo23<T> recibido = derecha.eliminar(elemento);
				int h1 = izquierda.darAltura();
				int h2 = recibido!=null?recibido.darAltura():0;
				if(!estaLleno() && h1 == h2+1 && !izquierda.estaLleno()){
					//Revisado Twice
					agregarElemento(izquierda.elementoIzquierdo);
					derecha = recibido;
					mitad = izquierda.derecha;
					izquierda = izquierda.izquierda;
					respuesta = this;
				}
				else if(!estaLleno() && h1 == h2+1 && izquierda.estaLleno()){
					//Revisado twice
					agregarElemento(izquierda.eliminarElemento(izquierda.elementoDerecho));
					Nodo23<T> der = new Nodo23<T>(eliminarElemento(elementoDerecho));
					der.derecha = recibido;
					der.izquierda = izquierda.derecha;
					izquierda.derecha = izquierda.mitad;
					izquierda.mitad = null;
					derecha = der;
					respuesta = this;
				}
				else if(estaLleno() && h1 == h2+1 && izquierda.estaLleno() && !mitad.estaLleno()){
					//Caso muy nuevo! revisado!!!
					Nodo23<T> nuevo = new Nodo23<T>(eliminarElemento(elementoIzquierdo));
					nuevo.izquierda = izquierda;
					agregarElemento(mitad.elementoIzquierdo);
					izquierda = mitad.izquierda;
					mitad = mitad.derecha;
					derecha = recibido;
					nuevo.derecha = this;
					respuesta = nuevo;
				}
				else if(estaLleno() && h1 == h2+1 && !izquierda.estaLleno() && !mitad.estaLleno()){
					//Revisado Twice
					Nodo23<T> der = new Nodo23<T>(eliminarElemento(elementoDerecho));
					der.agregarElemento(mitad.elementoIzquierdo);
					mitad = null;
					derecha = der;
					respuesta = this;
				}	
				else if(mitad != null && mitad.estaLleno() && h1 == h2+1){
					//Revisado Twice
					Nodo23<T> der = new Nodo23<T>(eliminarElemento(elementoDerecho));
					der.derecha = recibido;
					der.izquierda = mitad.derecha;
					mitad.derecha = mitad.mitad;
					mitad.mitad = null;
					agregarElemento(mitad.eliminarElemento(mitad.elementoDerecho));
					derecha = der;
					respuesta = this;
				}
				else if(h1 == h2){
					//Revisado Twice
					derecha = recibido;
					respuesta = this;
				}
				else{
					System.out.println("Caso no aplica derecha: " + elemento);
				}
			}
		}
		else if(comparacionIzquierda<0 && comparacionDerecha>0){
			if(mitad != null){
				Nodo23<T> recibido = mitad.eliminar(elemento);
				int h1 = izquierda.darAltura();
				int h2 = derecha.darAltura();
				int h3 = recibido!=null?recibido.darAltura():0;
				if(h1==h3+1 && h2 == h3+1 && !derecha.estaLleno()){
					//Revisado twice
					derecha.agregarElemento(eliminarElemento(elementoDerecho));
					mitad = recibido;
					respuesta = this;
				}
				else if(h1==h3+1 && h2 == h3+1 && derecha.estaLleno()){
					//Revisado twice
					Nodo23<T> mit = new Nodo23<T>(eliminarElemento(elementoDerecho));
					agregarElemento(derecha.eliminarElemento(derecha.elementoIzquierdo));
					mitad = mit;
					respuesta = this;
				}
				else if(h1 == h2 && h1 == h3){
					//Revisado twice
					mitad= recibido;
					respuesta = this;
				}
				else{
					System.out.println("Caso no aplica mitad: "+ elemento);
				}
			}
		}
		else{
			System.out.println("Caso no aplica eliminar: " + elemento);
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
		int comparacionIzq = 0;
		int comparacionDer = 0;
		
		if(elementoIzquierdo != null){
			 comparacionIzq = elementoIzquierdo.compareTo(elemento);
			 respuesta = comparacionIzq==0?elementoIzquierdo:null;
		}
		
		if(elementoDerecho != null){
			comparacionDer = elementoDerecho.compareTo(elemento);
			respuesta = comparacionDer==0?elementoDerecho:null;
		}
		
		if(comparacionIzq>0 ){
			if(izquierda != null)
				respuesta = izquierda.buscar(elemento);
		}
		else if(comparacionIzq<0 && comparacionDer>0){
			if(mitad != null)
				respuesta = mitad.buscar(elemento);
		}
		else if(comparacionDer<0 || (comparacionIzq<0 && elementoDerecho == null)){
			if(derecha!=null){
				respuesta = derecha.buscar(elemento);
			}	
		}
		return respuesta;
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
