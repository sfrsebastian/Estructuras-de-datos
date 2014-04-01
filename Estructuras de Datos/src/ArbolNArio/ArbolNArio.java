package ArbolNArio;

import java.util.Comparator;
import java.util.Iterator;

import ArbolBinOrdenado.IArbolBinarioOrdenado;

public class ArbolNArio<T extends Comparable<T>> implements IArbolNArio<T> {

	//-------------------------------------------------
	// Atributos
	//-------------------------------------------------
	
	/**
	 * La raiz del arbol n-ario
	 */
	private NodoArbolNArio<T> raiz;
	
	/**
	 * El comparador del arbol n-ario
	 */
	private Comparator<T> comparador;
	
	/**
	 * El peso del arbol-n-ario
	 */
	private int peso;
	
	//-------------------------------------------------
	// Constructor
	//-------------------------------------------------	
	
	public ArbolNArio(){
		raiz = null;
		peso = 0;
		comparador = null;
	}
	
	public ArbolNArio(Comparator c){
		raiz = null;
		peso = 0;
		comparador = c;
	}
	
	@Override
	public boolean agregar(T elemento, T padre) {
		// TODO Auto-generated method stub
		return false;
	}

	//-------------------------------------------------
	// Metodos
	//-------------------------------------------------
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T buscar(T elemento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminar(T elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int darPeso() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int darAltura() {
		return raiz.darAltura();
	}

	@Override
	public Object[] darArreglo() {
		// TODO Auto-generated method stub
		return null;
	}

}
