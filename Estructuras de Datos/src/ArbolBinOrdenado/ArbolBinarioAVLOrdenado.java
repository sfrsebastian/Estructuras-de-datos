package ArbolBinOrdenado;

import java.util.Comparator;

public class ArbolBinarioAVLOrdenado<T> extends ArbolBinarioOrdenado<T> {
	
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * La raiz del arbol AVL 
	 */
	private NodoArbolBinarioAVL<T> raiz;
	
	/**
	 * El peso del arbol binario AVL
	 */
	private int peso;
	
	/**
	 * La altura del arbol binario AVL
	 */
	private int altura;
	
	//------------------------------------------
	// Constructores
	//------------------------------------------
	
	public ArbolBinarioAVLOrdenado(Comparator c){
		super(c);
		peso = 0;
		altura = 0;
	}
	
	public ArbolBinarioAVLOrdenado(){
		super();
		peso = 0;
		altura = 0;
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	public void balancear(){
		raiz.balancearXAltura();
	}
}
