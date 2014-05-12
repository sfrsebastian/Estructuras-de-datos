package ListaEncadenada;

import java.io.Serializable;
import Lista.Lista;
import Lista.NodoLista;

public class ListaEncadenada<T extends Comparable<?super T>> extends Lista<T> implements Serializable {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * El ultimo nodo de la lista
	 */
	private NodoLista<T> ultimo;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------	
	
	/**
	 * Construye una nueva Lista de tipo T 
	 */
	public ListaEncadenada() {
		super();
		ultimo = null;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------
	 
	public T agregar(T elemento) {
		if(elemento == null){
			return null;
		}		
		else if(primero == null){
			NodoLista<T> aAgregar = new NodoLista<T>(elemento);
			primero = aAgregar;
			ultimo = primero;
			longitud++;
		}
		else{
			NodoLista<T> nuevo = new NodoLista<T>(elemento);
			ultimo.cambiarSiguiente(nuevo);
			nuevo.cambiarAnterior(ultimo);
			ultimo = nuevo;
			longitud++;
		}
		return elemento;
	}
	
	@Override
	public T eliminar(T elemento) {
		if(ultimo!=null && ultimo.darElemento() == elemento){
			NodoLista<T> anterior = ultimo.darAnterior();
			if(anterior!=null){
				anterior.cambiarSiguiente(null);
				ultimo.cambiarAnterior(null);
				ultimo = anterior;
			}
			else{
				primero = null;
				ultimo = null;
			}
			longitud--;
			return elemento;
		}
		else{
			return super.eliminar(elemento);
		}
		
	}
	
	/**
	 * Asigna un nuevo nodo al inicio de la lista
	 * @param elemento El elemento a agregar
	 */
	public void agregarInicio(T elemento){
		NodoLista<T> nuevo = new NodoLista<T>(elemento);
		nuevo.cambiarSiguiente(primero);
		if(primero!=null){
			primero.cambiarAnterior(nuevo);	
		}
		primero = nuevo;
	}
	
	public T darUltimo(){
		return ultimo.darElemento();
	}
}
