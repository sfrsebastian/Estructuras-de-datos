package ArbolTrie;

import java.util.Iterator;

import ListaOrdenada.ListaOrdenada;

public class NodoTrie<T extends Comparable<T>> {

	//------------------------------------------------------------
	// Atributos
	//------------------------------------------------------------
	
	/**
	 * La lista de elementos que contiene el nodo
	 */
	private ListaOrdenada listaElementos;
	
	/**
	 * El nodo hermano 
	 */
	private NodoTrie hermano;
	
	/**
	 * El nodo hijo
	 */
	private NodoTrie hijo;
	
	/**
	 * El caracte que contiene el nodo
	 */
	private char caracter;
	
	//------------------------------------------------------------
	// Constructor
	//------------------------------------------------------------
	
	/**
	 * Construye un nuevo nodo con una lista vacia de elements
	 * @param ncaracter El caracter del nodo
	 */
	public NodoTrie(char ncaracter){
		listaElementos = new ListaOrdenada<T>();
		hermano = null;
		hijo = null;
		caracter = ncaracter;
	}
	
	//------------------------------------------------------------
	// Metodos
	//------------------------------------------------------------
	
	public boolean agregar(String palabra, T elemento){
		if(palabra.equals("")){
			listaElementos.agregar(elemento);
			return true;
		}
		char letra = palabra.charAt(0);
		if(hijo != null){
			//Preguntar a el hijo y sus hermanos si contienen la letra
			NodoTrie<T> hermanoTemp;		
			if((hermanoTemp=hijo.tieneLetra(letra)) != null){
				hermanoTemp.agregar(palabra.substring(1), elemento);
			}else{
				//Caso2: La letra es la mayor, referencia a hijo se mantiene
				if(letra > hijo.getCaracter()){
					NodoTrie<T> mayor = hijo.acomodarLetraMayor(letra);
					mayor.agregar(palabra.substring(1),elemento);
				}
				//Caso3: La letra es menor, referencia a hijo se actualiza
				if(letra < hijo.getCaracter()){
					NodoTrie<T> menor = hijo.acomodarLetraMenor(letra);
					this.hijo = menor;
					menor.agregar(palabra.substring(1), elemento);	
				}
			}
		}else{
			NodoTrie<T> hijo = new NodoTrie<T>(letra);
			this.hijo = hijo;
			return hijo.agregar(palabra.substring(1), elemento);
		}
		return true;
	}
	
	public boolean agregarMultiples(String palabra, Iterator<T> i){
		if(palabra.equals("")){
			while(i.hasNext()){
				T elem = i.next();
				listaElementos.agregar(elem);
			}
			return true;
		}
		char letra = palabra.charAt(0);
		if(hijo != null){
			//Preguntar a el hijo y sus hermanos si contienen la letra
			NodoTrie<T> hermanoTemp;		
			if((hermanoTemp=hijo.tieneLetra(letra)) != null){
				hermanoTemp.agregarMultiples(palabra.substring(1), i);
			}else{
				//Caso2: La letra es la mayor, referencia a hijo se mantiene
				if(letra > hijo.getCaracter()){
					NodoTrie<T> mayor = hijo.acomodarLetraMayor(letra);
					mayor.agregarMultiples(palabra.substring(1),i);
				}
				//Caso3: La letra es menor, referencia a hijo se actualiza
				if(letra < hijo.getCaracter()){
					NodoTrie<T> menor = hijo.acomodarLetraMenor(letra);
					this.hijo = menor;
					menor.agregarMultiples(palabra.substring(1), i);	
				}
			}
		}else{
			NodoTrie<T> hijo = new NodoTrie<T>(letra);
			this.hijo = hijo;
			return hijo.agregarMultiples(palabra.substring(1), i);
		}	
		return true;
	}
	
	private NodoTrie<T> acomodarLetraMenor(char letra) {
		NodoTrie<T> nuevoMenor = new NodoTrie<T>(letra);
		nuevoMenor.hermano = this;
		return nuevoMenor;
	}

	private NodoTrie<T> acomodarLetraMayor(char letra) {
		if(this.hermano == null){
			NodoTrie<T> nuevoHermano = new NodoTrie<T>(letra);
			this.hermano = nuevoHermano;
			return nuevoHermano;
		}else{
			if(this.hermano.caracter > letra){
				NodoTrie<T> nuevoHermano = new NodoTrie<T>(letra);
				nuevoHermano.hermano = this.hermano;
				this.hermano = nuevoHermano;
				return nuevoHermano;
			}else{
				this.hermano.acomodarLetraMayor(letra);
			}
		}
		return null;
	}

	private NodoTrie<T> tieneLetra(char c){
		if(this.getCaracter() == c)
			return this;
		else{
			if(this.hermano != null)
				return this.hermano.tieneLetra(c);
		}
		return null;
	}
	
	public char getCaracter(){
		return caracter;
	}

	public Iterator<T> buscar(String palabra) {
		if(palabra.equals("")){
			return listaElementos.iterator();
		}
		char letra = palabra.charAt(0);
		if(hijo != null){
			NodoTrie<T> nodo;
			if((nodo=hijo.tieneLetra(letra)) != null){
				return nodo.buscar(palabra.substring(1));
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public T buscarElemento(String palabra, T elemento){
		if(palabra.equals("")){
			return (T) listaElementos.buscar(elemento);
		}
		char letra = palabra.charAt(0);
		if(hijo != null){
			NodoTrie<T> nodo;
			if((nodo=hijo.tieneLetra(letra)) != null){
				return nodo.buscarElemento(palabra.substring(1), elemento);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

	public boolean eliminar(String palabra) {
		if(palabra.equals("")){
			listaElementos = new ListaOrdenada<T>();
			if(hijo == null && hermano == null)
				return false;//return eliminarIndepenientes("");
			else
				return true;
		}
		char letra = palabra.charAt(0);
		if(hijo != null){
			NodoTrie<T> nodo;
			if((nodo=hijo.tieneLetra(letra)) != null){
				return nodo.eliminar(palabra.substring(1));
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	/*
	private boolean eliminarIndepenientes(String palabra) {
		char letra = palabra.charAt(0);
		//NodoTrie<T> nodo = hijo.tieneLetra(letra);
		
		return false;
	}
 	*/

	public Iterator<T> buscarXPrefijo(String palabra, ListaOrdenada<T> elems) {
		if(palabra.equals("")){
			this.agregarTodosElementos(elems);
			return elems.iterator();
		}
		char letra = palabra.charAt(0);
		if(hijo != null){
			NodoTrie<T> nodo;
			if((nodo=hijo.tieneLetra(letra)) != null){
				return nodo.buscarXPrefijo(palabra.substring(1), elems);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

	private void agregarTodosElementos(ListaOrdenada<T> elems) {
		Iterator<T> i = listaElementos.iterator();
		while(i.hasNext()){
			elems.agregar(i.next());
		}
		if(hijo != null)
			hijo.agregarTodosElementos(elems);
		if(hermano != null)
			hermano.agregarTodosElementos(elems);
	}

	public String toString(){
		String herma = "NO TIENE";
		if(hermano != null)
			herma= "" + hermano.getCaracter();
		
		String hij = "NO TIENE";
		if(hijo != null)
			hij = "" + hijo.getCaracter();
		
		return "Soy letra: " + caracter + ", hermano: " + herma + ", hijo: " + hij;
	}
}
