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

	/**
	 * Balancea el arbol<br>
	 * Aplica para casos especiales para eliminacion de elementos que no se encuentran en nodos hoja.
	 */
	private void balancearArbol(){
		if(!esHoja() && derecha.darAltura()<izquierda.darAltura() && !estaLleno() && !izquierda.estaLleno()){
			//Agrega el elemento de la izquierda al nodo.
			//La derecha de la izquierda se convierte en mitad
			//La izquierda de la izquierda se vuelve la izquierda.
			agregarElemento(izquierda.elementoIzquierdo);
			mitad = izquierda.derecha;
			izquierda = izquierda.izquierda;
		}
		else if(!esHoja() && derecha.darAltura()<izquierda.darAltura() && !estaLleno() && izquierda.estaLleno()){
			//Crea un nuevo nodo derecho a partir del elemento existente.
			//El nodo actual tendra como elemento el elemento derecho de la izquierda
			//El nodo derecho se arma a partir de la reconfiguracion del nodo izquierdo
			agregarElemento(izquierda.eliminarElemento(izquierda.elementoDerecho));
			Nodo23<T> der = new Nodo23<T>(eliminarElemento(elementoDerecho));
			der.derecha = derecha;
			der.izquierda = izquierda.derecha;
			izquierda.derecha = izquierda.mitad;
			izquierda.mitad = null;
			derecha = der;
		}
		else if(!esHoja() && derecha.darAltura()<izquierda.darAltura() && estaLleno() && mitad.estaLleno()){
			//Crea un nuevo nodo derecho a partir de su menor elemento
			//Su derecha sera la eliminacion del menor elemento derecho y su izquierda la derecha de la mitad
			//La derecha de la mitad sera su mitad y su mitad se volvera null.
			//Aplica para nodo terminal
			Nodo23<T> der = new Nodo23<T>(eliminarElemento(elementoDerecho));
			agregarElemento(mitad.eliminarElemento(mitad.elementoDerecho));
			der.derecha = derecha;
			der.izquierda = mitad.derecha;
			mitad.derecha = mitad.mitad;
			mitad.mitad = null;
			derecha = der;
		}
		else if(!esHoja() && mitad != null && mitad.darAltura()<derecha.darAltura() && !derecha.estaLleno()){
			//Se agrega el elemento derecho a la derecha
			//La izquierda de la derecha se vuelve su mitad
			//La izquierda de la derecha se convierte en la mitad del nodo
			//La mitad del nodo se vuelve null.
			derecha.agregarElemento(eliminarElemento(elementoDerecho));
			derecha.mitad = derecha.izquierda;
			derecha.izquierda = mitad;
			mitad= null;
		}
		else if(!esHoja() && estaLleno() && mitad.darAltura()<derecha.darAltura() && derecha.estaLleno()){
			//Reorganiza el arbol creando un nuevo nodo de la mitad que tiene como raiz el elemento derecho original.
			//El nuevo nodo de la mitad tiene como izquierda lo recibido por la eliminacion del elemento menor de la mitad y como derecha el nodo izquierdo de la derecha
			//La derecha se reorganiza poniendo como izquierda su mitad y volviendo su mitad null.
			//Aplica para nodo terminal
			Nodo23<T> mit = new Nodo23<T>(eliminarElemento(elementoDerecho));
			agregarElemento(derecha.eliminarElemento(derecha.elementoIzquierdo));
			mit.izquierda = mitad;
			mit.derecha = derecha.izquierda;
			derecha.izquierda = derecha.mitad;
			derecha.mitad = null;
			mitad = mit;
		}
		else if(!esHoja() && estaLleno() && mitad.darAltura()>derecha.darAltura() && derecha.estaLleno() && !mitad.estaLleno()){
			//El elemento derecho se convierte en un nuevo nodo
			//La derecha del nuevo nodo sera la derecha del nodo
			//La mitad del nuevo nodo se vuelve la derecha de la mitad
			//La izquierda del nuevo nodo se vuelve la izquierda de la mitad
			//La mitad se vuelve null y la derecha el nuevo nodo creado.
			Nodo23<T> der = new Nodo23<T>(eliminarElemento(elementoDerecho));
			der.agregarElemento(mitad.elementoIzquierdo);
			der.derecha = derecha;
			der.mitad = mitad.derecha;
			der.izquierda = mitad.izquierda;
			mitad= null;
			derecha = der;
		}
	}

	//--------------------
	//METODOS
	//--------------------
	/**
	 * Agrega el elemento dado por parametro.
	 * @param elemento El elemento a agregar.
	 * @return El nodo resultante de el procedimiento.
	 */
	public Nodo23<T> agregar (T elemento)throws Exception{
		Nodo23<T> respuesta = this;
		int comparacionIzquierda = elementoIzquierdo.compareTo(elemento);
		int comparacionDerecha = 0;
		if(elementoDerecho != null){
			comparacionDerecha = elementoDerecho.compareTo(elemento);
		}
		//Caso base: El elemento se agrega en una hoja
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
		//Caso 1: El elemento a agregar es menor que el izquierdo del nodo.
		//Caso recursivo
		else if(comparacionIzquierda>0){
			if(izquierda != null){
				Nodo23<T> recibido = izquierda.agregar(elemento);
				int h1 = derecha.darAltura();
				int h2 = recibido.darAltura();
				if(!estaLleno() && !recibido.estaLleno() && h1+1 == h2 ){
					//Agrega al nodo el elemento del nodo recibido.
					//La izquierda del nodo se vuelve la izquierda del recibido
					//La mitad del nodo se vuelve la derecha del recibido.
					agregarElemento(recibido.elementoIzquierdo);
					izquierda = recibido.izquierda;
					mitad = recibido.derecha;
					respuesta = this;
				}
				else if(estaLleno() && !recibido.estaLleno() && h1+1 == h2 ){
					//Crea un nuevo nodo derecho con el elemento derecho del nodo.
					//La derecha del nuevo nodo sera la derecha actual.
					//La izquierda del nuevo nodo sera la mitad actual.
					izquierda = recibido;
					Nodo23<T> der = new Nodo23<T>(eliminarElemento(elementoDerecho));
					der.izquierda = mitad;
					mitad = null;
					der.derecha = derecha;
					derecha = der;
					respuesta = this;
				}
				else if(h1 == h2){
					//Asigna a la izquierda el nodo recibido
					izquierda = recibido;
					respuesta = this;
				}
				else{
					throw new Exception("No aplica agregar lado izquierdo");
				}
			}
		}
		//Caso 2: El elemento a agregar es mayor que el/los elementos del nodo
		//Caso recursivo
		else if(comparacionDerecha<0 || (comparacionDerecha == 0 && elementoDerecho == null)){
			if(derecha != null){
				Nodo23<T> recibido = derecha.agregar(elemento);
				int h1 = izquierda.darAltura();
				int h2 = recibido.darAltura();
				if(!estaLleno() && !recibido.estaLleno() && h1+1 == h2){
					//Agrega al nodo el elemento del nodo recibido.
					//La derecha del nodo se vuelve la derecha del recibido
					//La mitad del nodo se vuelve la izquierda del recibido.
					agregarElemento(recibido.elementoIzquierdo);
					derecha = recibido.derecha;
					mitad = recibido.izquierda;
					respuesta = this;
				}
				else if(estaLleno() && !recibido.estaLleno() && h1+1 == h2){
					//Crea un nuevo nodo izquierdo con el elemento izquierdo del nodo.
					//La izquierda del nuevo nodo sera la izquierda actual.
					//La derecha del nuevo nodo sera la mitad actual.
					derecha = recibido;
					Nodo23<T> izq = new Nodo23<T>(eliminarElemento(elementoIzquierdo));
					izq.izquierda = izquierda;
					izq.derecha = mitad;
					mitad = null;
					izquierda = izq;
					respuesta = this;
				}
				else if(h1==h2){
					//Asigna a la derecha el nodo recibido
					derecha = recibido;
					respuesta = this;
				}
				else{
					throw new Exception("No aplica agregar lado derecho");
				}
			}
		}
		//Caso 3: El elemento a agregar se encuentra entre el elemento izquierdo y el derecho.
		//Caso recursivo
		else if(comparacionIzquierda<0 && comparacionDerecha>0){
			if(mitad != null){
				Nodo23<T> recibido = mitad.agregar(elemento);
				int h1 = izquierda.darAltura();
				int h2 = derecha.darAltura();
				int h3 = recibido.darAltura();
				if(estaLleno() && !recibido.estaLleno() && h1+1==h3 && h2+1 == h3){
					//Se parte el nodo actual
					//Se configura un nuevo nodo a partir del nodo recibido
					//La izquierda del nuevo sera el nodo creado con el elemento izquierdo del nodo actual.
					//La derecha del nuevo nodo sera el nodo con el elemento derecho del nodo actual.
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
					//Asigna a la mitad el nodo recibido
					mitad = recibido;
					respuesta = this;
				}
				else{
					throw new Exception("No aplica agregar mitad");
				}
			}
		}
		respuesta.verificarInvariante();
		return respuesta;
	}

	/**
	 * Elimina el elemento dado por parametro
	 * @param elemento El elemento a eliminar
	 * @return El nodo resultante de el procedimiento.
	 */
	public Nodo23<T> eliminar(T elemento) throws Exception{
		Nodo23<T> respuesta = this;
		int comparacionIzquierda = elementoIzquierdo.compareTo(elemento);
		int comparacionDerecha = 0;
		if(elementoDerecho != null){
			comparacionDerecha = elementoDerecho.compareTo(elemento);
		}
		//Caso base: El elemento a eliminar esta en una hoja
		if(esHoja()){
			if(estaLleno()){
				eliminarElemento(elemento);
				respuesta = this; 		
			}
			else if(comparacionIzquierda == 0){
				respuesta = null;
			}
		}
		//Caso 1: El elemento izquierdo del nodo es el que se quiere eliminar
		else if(comparacionIzquierda == 0){
			if(!estaLleno()){
				if(!derecha.estaLleno() && !izquierda.estaLleno()&&izquierda.esHoja()) {
					//Caso 1a Terminal
					//Junta en un nodo los elementos de la izquierda y derecha
					agregarElemento(izquierda.elementoIzquierdo);
					eliminarElemento(elemento);
					izquierda = null;
					agregarElemento(derecha.elementoIzquierdo);
					derecha = null;
				}
				//Caso 1b Terminal
				//Pone como raiz el elemento derecho de la izquierda.
				else if(izquierda.estaLleno() && !derecha.estaLleno() && izquierda.esHoja()){
					agregarElemento(izquierda.eliminarElemento(izquierda.elementoDerecho));
					eliminarElemento(elemento);
				}
				else{
					//Busca el menor elemento de la derecha y lo ubica como raiz.#1
					T menorDer = derecha.darMenor();
					agregarElemento(menorDer);
					eliminarElemento(elemento);
					derecha = derecha.eliminar(menorDer);
				}
			}
			else{
				//Busca el menor elemento de la mitad y lo ubica de raiz junto a el elemento izquierdo de la derecha.
				eliminarElemento(elemento);
				T menorMit = mitad.darMenor();
				agregarElemento(menorMit);
				mitad = mitad.eliminar(menorMit);
				//Caso 1c terminal
				//Agrega a la derecha el elemento derecho del nodo.
				if(!derecha.estaLleno() && mitad == null && derecha.esHoja()){
					agregarElemento(menorMit);
					derecha.agregarElemento(eliminarElemento(elementoDerecho));
					mitad = null;
				}
				else if(derecha.estaLleno() && mitad == null && derecha.esHoja()){
					Nodo23<T> mit = new Nodo23<T>(eliminarElemento(elementoDerecho));
					mitad = mit;
					agregarElemento(derecha.eliminarElemento(derecha.elementoIzquierdo));
				}
			}
			respuesta = this;
		}
		//Caso 2: El elemento derecho del nodo es el que se quiere eliminar.
		else if(comparacionDerecha == 0 && elementoDerecho!=null){
			//Caso 2a terminal
			//El elemento de la mitad se agrega al elemento de la derecha y la mitad se vuelve null
			if(!derecha.estaLleno() && !mitad.estaLleno() && mitad.esHoja()){
				eliminarElemento(elemento);
				derecha.agregarElemento(mitad.elementoIzquierdo);
				mitad = null;
			}
			//Caso 2b terminal
			//Se reemplaza el eliminado por el elemento derecho de la mitad.
			else if(!derecha.estaLleno() && mitad.estaLleno() && mitad.esHoja()){
				eliminarElemento(elemento);
				agregarElemento(mitad.eliminarElemento(mitad.elementoDerecho));
			}
			else{
				//Reemplaza el eliminado por el menor elemento de la derecha.
				//Aplica para casos terminales
				eliminarElemento(elemento);
				T menorDer = derecha.darMenor();
				agregarElemento(menorDer);
				derecha = derecha.eliminar(menorDer);
			}
			respuesta = this;
		}
		//Caso 3: El elemento a eliminar es menor que el elemento izquierdo
		//Caso Recursivo
		else if(comparacionIzquierda>0){
			if(izquierda != null){
				Nodo23<T> recibido = izquierda.eliminar(elemento);
				int h1 = derecha.darAltura();
				int h2 = recibido!=null?recibido.darAltura():0;
				if(!estaLleno() && h1 == h2+1 && !derecha.estaLleno()){
					//Disminuye la altura del nodo derecho agregando al nodo actual su elemento
					//La mitad del nodo actual se vuelve la izquierda del nodo derecho.
					//La derecha del nodo actual se vuelve la derecha del nodo derecho
					agregarElemento(derecha.elementoIzquierdo);
					izquierda = recibido;
					mitad = derecha.izquierda;
					derecha = derecha.derecha;
					respuesta = this;
				}
				else if(!estaLleno() && h1 == h2+1 && derecha.estaLleno()){
					//Crea un nuevo nodo izquierdo con el elemento del nodo actual
					//El elemento derecho de la derecha se vuelve el elemento actual del nodo
					//Se reorganiza el nodo derecho para la construccion del nuevo nodo izquierdo.
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
					//Crea un nuevo nodo con el elemento derecho del nodo actual
					//La configuracion de la mitad se le asigna al nodo actual
					//Se asigna el nodo actual a la izquierda del nodo nuevo
					Nodo23<T> nuevo = new Nodo23<T>(eliminarElemento(elementoDerecho));
					nuevo.derecha = derecha;
					agregarElemento(mitad.elementoIzquierdo);
					derecha = mitad.derecha;
					mitad = mitad.izquierda;
					izquierda = recibido;
					nuevo.izquierda = this;
					respuesta = nuevo;
				}
				else if(estaLleno() && h1 == h2+1 && !derecha.estaLleno() && !mitad.estaLleno()){
					//Crea un nuevo nodo izquierdo con el elemento izquierdo del nodo y el elemento de la mitad
					//Configura el nuevo nodo con los nodos izquierdo y derecho de la mitad.
					Nodo23<T> izq = new Nodo23<T>(eliminarElemento(elementoIzquierdo));
					izq.agregarElemento(mitad.elementoIzquierdo);
					izq.izquierda = recibido;
					izq.mitad = mitad.izquierda;
					izq.derecha = mitad.derecha;
					mitad = null;
					izquierda = izq;
					respuesta = this;
				}
				else if(estaLleno() && mitad.estaLleno() && h1 == h2+1 ){
					//Crea un nuevo nodo izquierdo con el elemento izquierdo del nodo
					//Configura el nodo izquierdo con nodos del nodo de la mitad
					//El elemento izquierdo de la mitad se agrega al nodo actual
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
					//Asigna a la izquierda el nodo recibido
					izquierda = recibido;
					respuesta = this;
				}
				else{
					throw new Exception("No aplica eliminar lado izquierdo");
				}
			}
		}
		//Caso 4: El elemento a eliminar es mayor que el elemento derecho o mayor que el elemento izquierdo si el derecho no existe.
		//Caso Recursivo
		else if(comparacionDerecha<0 || (comparacionDerecha == 0 && elementoDerecho == null)){
			if(derecha != null){
				Nodo23<T> recibido = derecha.eliminar(elemento);
				int h1 = izquierda.darAltura();
				int h2 = recibido!=null?recibido.darAltura():0;
				if(!estaLleno() && h1 == h2+1 && !izquierda.estaLleno()){
					//Disminuye la altura del nodo izquierdo agregando al nodo actual su elemento
					//La mitad del nodo actual se vuelve la derecha del nodo izquierdo.
					//La izquierda del nodo actul se vuelve la izquierda del nodo izquierdo
					agregarElemento(izquierda.elementoIzquierdo);
					derecha = recibido;
					mitad = izquierda.derecha;
					izquierda = izquierda.izquierda;
					respuesta = this;
				}
				else if(!estaLleno() && h1 == h2+1 && izquierda.estaLleno()){
					//Crea un nuevo nodo derecho con el elemento del nodo actual
					//El elemento derecho de la izquierda se vuelve el elemento actual del nodo
					//Se reorganiza el nodo izquierdo para la construccion del nuevo nodo derecho.
					agregarElemento(izquierda.eliminarElemento(izquierda.elementoDerecho));
					Nodo23<T> der = new Nodo23<T>(eliminarElemento(elementoDerecho));
					der.derecha = recibido;
					der.izquierda = izquierda.derecha;
					izquierda.derecha = izquierda.mitad;
					izquierda.mitad = null;
					derecha = der;
					respuesta = this;
				}
				else if(estaLleno() && h1 == h2+1 && !izquierda.estaLleno() &&!mitad.estaLleno()){
					//Crea un nuevo nodo derecho con el elemento derecho del nodo y el elemento de la mitad
					//Configura el nuevo nodo con los nodos izquierdo y derecho de la mitad.
					Nodo23<T> der = new Nodo23<T>(eliminarElemento(elementoDerecho));
					der.agregarElemento(mitad.elementoIzquierdo);
					der.derecha = recibido;
					der.mitad = mitad.derecha;
					der.izquierda = mitad.izquierda;
					mitad = null;
					derecha = der;
					respuesta = this;
				}
				else if(estaLleno() && h1 == h2+1 && izquierda.estaLleno() && !mitad.estaLleno()){
					//Caso no simetrico pero necesario
					//Crea un nuevo nodo con el elemento izquierdo del nodo actual
					//Configura el nodo actual a partir de el nodo de la mitad
					//Se asigna el nodo actual a la derecha del nuevo nodo.
					Nodo23<T> nuevo = new Nodo23<T>(eliminarElemento(elementoIzquierdo));
					nuevo.izquierda = izquierda;
					agregarElemento(mitad.elementoIzquierdo);
					izquierda = mitad.izquierda;
					mitad = mitad.derecha;
					derecha = recibido;
					nuevo.derecha = this;
					respuesta = nuevo;
				}	
				else if(mitad != null && mitad.estaLleno() && h1 == h2+1){
					//Crea un nuevo nodo derecho con el elemento derecho del nodo
					//Configura el nuevo nodo con nodos del nodo de la mitad
					//El elemento derecho de la mitad se agrega al nodo actual
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
					//Asigna a la derecha el nodo recibido
					derecha = recibido;
					respuesta = this;
				}
				else{
					throw new Exception("No aplica eliminar lado derecho");
				}
			}
		}
		//Caso 5: El elemento a eliminar esta entre el elemento izquierdo y el elemento derecho
		//Caso Recursivo
		else if(comparacionIzquierda<0 && comparacionDerecha>0){
			if(mitad != null){
				Nodo23<T> recibido = mitad.eliminar(elemento);
				int h1 = izquierda.darAltura();
				int h2 = derecha.darAltura();
				int h3 = recibido!=null?recibido.darAltura():0;
				if(h1==h3+1 && h2 == h3+1 && !derecha.estaLleno()){
					//Configura la derecha con los elementos del nodo actual
					//Se agrega el elemento derecho a la derecha
					//El nodo de la mitad se vuelve null
					derecha.agregarElemento(eliminarElemento(elementoDerecho));
					derecha.mitad = derecha.izquierda;
					derecha.izquierda = recibido;
					mitad = null;
					respuesta = this;
				}
				else if(h1==h3+1 && h2 == h3+1 && derecha.estaLleno()){
					//Crea un nuevo nodo de mitad con el elemento derecho del nodo actual
					//Reconfigura el nodo derecho y configura el nuevo nodo con informacion de la derecha
					Nodo23<T> mit = new Nodo23<T>(eliminarElemento(elementoDerecho));
					agregarElemento(derecha.eliminarElemento(derecha.elementoIzquierdo));
					mit.izquierda=recibido;
					mit.derecha = derecha.izquierda;
					derecha.izquierda = derecha.mitad;
					derecha.mitad = null;
					mitad = mit;
					respuesta = this;
				}
				else if(h1 == h2 && h1 == h3){
					//Asigna a la mitad el nodo recibido
					mitad= recibido;
					respuesta = this;
				}
				else{
					throw new Exception("No aplica eliminar mitad");
				}
			}
		}
		else{
			throw new Exception("No aplica eliminar general");
		}
		//Verifica que el arbol este balanceado.
		if(respuesta != null){
			respuesta.balancearArbol();
			respuesta.verificarInvariante();
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
		int comparacionIzq = elementoIzquierdo.compareTo(elemento);
		int comparacionDer = 0;

		if(elementoDerecho != null){
			comparacionDer = elementoDerecho.compareTo(elemento);
		}

		if(comparacionIzq == 0){
			respuesta = elementoIzquierdo;
		}
		else if(comparacionIzq>0 ){
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
		else if(comparacionDer==0 && elementoDerecho != null){
			respuesta = elementoDerecho;
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

	//--------------------
	//INVARIANTE
	//--------------------	
	/**
	 * Verifica que el nodo se encuentre balanceado
	 */
	private void verificarInvariante(){
		int izq = 0;
		int centro = 0;
		int der = 0;
		if(izquierda != null)
			izq = izquierda.darAltura();

		if(mitad != null)
			centro = mitad.darAltura();

		if(derecha != null)
			der = derecha.darAltura();

		if(mitad == null){
			assert izq == der:"El nodo debe ser balanceado";
		}
		else{
			assert izq == der && izq == centro: "El nodo debe ser balanceado";
		}

	}
}
