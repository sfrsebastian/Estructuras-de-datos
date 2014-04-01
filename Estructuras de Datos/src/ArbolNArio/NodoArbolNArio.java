package ArbolNArio;

import java.util.Comparator;
import java.util.Iterator;

public class NodoArbolNArio<T extends Comparable<T>> {

	//-------------------------------------------------
	// Atributos
	//-------------------------------------------------
	
	/**
	 * El hijo que tiene el nodo
	 */
	private NodoArbolNArio<T> hijo;
	
	/**
	 * El elemento que tiene el nodo
	 */
	private T elemento;
	
	/**
	 * El padre del nodo
	 */
	private NodoArbolNArio<T> padre;
	
	/**
	 * El hermano que tiene el nodo
	 */
	private NodoArbolNArio<T> hermano;
	
	/**
	 * El comparador del nodo
	 */
	private Comparator<T> comparador;
	
	//-------------------------------------------------
	// Constructores
	//-------------------------------------------------
	
	public NodoArbolNArio(T nelemento, Comparator<T> c){
		elemento = nelemento;
		comparador = c;
	}
	
	//-------------------------------------------------
	// Metodos
	//-------------------------------------------------
	
	/**
	 * 
	 * @return
	 */
	public int darAltura() {
		int mayorAlturaHijos = 0; 
		int mayorAlturaHermanos = 0;
		
		if(hijo != null){
			mayorAlturaHijos = hijo.darAltura();
		}
		if(hermano != null){
			mayorAlturaHermanos = hermano.darAltura();
		}
		if(mayorAlturaHijos + 1 > mayorAlturaHermanos){
			return mayorAlturaHijos + 1;
		}else{
			return mayorAlturaHermanos;
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public T darMayor(){
		T mayor = elemento;
		T mayorHijo = null;
		T mayorHermanos = null;
		
		if(hijo != null){
			mayorHijo = hijo.darMayor();
			if(mayor.compareTo(mayorHijo) < 0){
				mayor = mayorHijo;
			}
		}
		if(hermano != null){
			mayorHermanos = hermano.darMayor();
			if(mayor.compareTo(mayorHermanos) < 0){
				mayor = mayorHermanos;
			}
		}
		
		return mayor;
	}

}

/*
 * public int darAltura() {
		int mayorAlturaHijo = 0;
		Iterator<NodoArbolNArio<T>> iterador = hijos.iterador();
		while(iterador.hasNext()){
			int alturaHijo = iterador.next().darAltura();
			if(mayorAlturaHijo < alturaHijo){
				mayorAlturaHijo = alturaHijo;
			}
		}
		
		return mayorAlturaHijo+1;
	}
*/
