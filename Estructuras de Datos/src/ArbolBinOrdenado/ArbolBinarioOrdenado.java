package ArbolBinOrdenado;

import java.util.Comparator;
import java.util.Iterator;

public class ArbolBinarioOrdenado<T extends Comparable<T>> implements IArbolBinarioOrdenado<T> {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * La altura del arbol binario
	 */
	private int altura;
	
	/**
	 * El peso del arbol 
	 */
	private int peso;
	
	/**
	 * La raiz del arbol ordenado
	 */
	private NodoArbolBinario<T> raiz;
	
	/**
	 * El comparados del arbol binario
	 */
	private Comparator<T> comparador;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	/**
	 * Crea un nuevo arbol binario con altura y peso 0
	 */
	public ArbolBinarioOrdenado(){
		raiz = null;
		altura = 0;
		peso = 0;
		comparador = null;
	}
	
	public ArbolBinarioOrdenado(Comparator<T> comp){
		raiz = null;
		altura = 0;
		peso = 0;
		comparador = comp;
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	@Override
	public boolean agregar(T elemento) {
		if(elemento != null){
			if(raiz == null){
				raiz = new NodoArbolBinario<T>(elemento,comparador);
				return true;
			}
			else if(raiz.agregar(elemento)){
				peso++;
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	@Override
	public T buscar(T elemento) {
		if(raiz == null){
			return null;
		}
		else if (comparar(raiz.darElemento(), elemento) == 0){
			return raiz.darElemento();
		}
		else{
			return raiz.buscar(elemento);
		}	
	}

	@Override
	public boolean eliminar(T elemento){
		if(raiz == null){
			return false;
		}
		else{
			raiz = raiz.eliminar(elemento);
			return true;
		}
	}
//	public T eliminar(T elemento) {
//		if(raiz == null)
//			return null;
//		else if (raiz.esHoja()){
//			T elem = raiz.darElemento();
//			raiz = null;
//			peso--;
//			return elem;
//		}
//		else {
//			if(!raiz.esHoja() && (comparar(raiz.darElemento(), elemento)) == 0){
//				System.out.println("Intentando eliminar la raiz con hijos");
//				return null;
//			}else{
//				T elem = raiz.eliminar(elemento);
//				if(elem != null)
//					peso--;
//				return elem;
//			}
//			
//		}
//	}

	@Override
	public int darPeso() {
		return peso;
	}

	@Override
	public int darAltura() {
		return raiz.darAltura();
	}

	@Override
	public Iterator<T> recorrerPreorden() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> recorrerInorden() {
		return new IteradorInorden();
	}

	@Override
	public Iterator<T> recorrerPosorden() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> recorrerNiveles() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int comparar(T elem1, T elem2){
		if (comparador == null){
			return ((Comparable<T>)elem1).compareTo(elem2);
		}else{
			return comparador.compare(elem1, elem2);
		}
	}

}
