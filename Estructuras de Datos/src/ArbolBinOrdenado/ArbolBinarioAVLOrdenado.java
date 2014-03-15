package ArbolBinOrdenado;

import java.util.Comparator;

public class ArbolBinarioAVLOrdenado<T extends Comparable<T>> extends ArbolBinarioOrdenado<T> {
	
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * La raiz del arbol AVL 
	 */
	private NodoArbolBinarioAVL<T> raiz;
	
	//------------------------------------------
	// Constructores
	//------------------------------------------
	
	public ArbolBinarioAVLOrdenado(Comparator<T> c){
		super(c);
		raiz = null;
		super.raiz = raiz;
	}
	
	public ArbolBinarioAVLOrdenado(){
		super();
		raiz = null;
		super.raiz = raiz;
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	public void balancear(){
		raiz.balancearXAltura();
	}
	
	public boolean eliminar(T elemento){
		boolean b = super.eliminar(elemento);
		balancear();
		return b;
	}
	
	public boolean agregar(T elemento){
		boolean b = super.agregar(elemento);
		balancear();
		return b;
				
	}
}
