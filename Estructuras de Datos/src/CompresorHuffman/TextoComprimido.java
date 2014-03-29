package CompresorHuffman;

import ArbolBinario.ArbolBinario;
import ArbolBinario.NodoArbol;
import BitString.BitString;

public class TextoComprimido {
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * El mensaje codificado
	 */
	BitString mensaje;
	
	/**
	 * La tabla de caracteres
	 */
	DatosCaracter[] tabla;
	
	/**
	 * El numero de caracteres del mensaje
	 */
	int numeroCaracteres;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea un texto comprimido a partir de la cantidad de caracteres dada.
	 * @param num
	 */
	public TextoComprimido(int num){
		mensaje = new BitString();
		tabla = new DatosCaracter[num];
		numeroCaracteres = 0;
	}
	
	/**
	 * Crea un texto comprimido a partir de los datos dados por parametro
	 * @param num
	 * @param nMensaje
	 * @param datos
	 */
	public TextoComprimido(int num,byte[] nMensaje,DatosCaracter[] datos){
		numeroCaracteres = datos.length;
		tabla = datos;
		mensaje = new BitString(nMensaje,num);
	}
	//------------------------------------------
	// Metodos
	//-----------------------------------------
	/**
	 * Retorna el bitstring con el mensaje
	 * @return
	 */
	public BitString getMensaje() {
		return mensaje;
	}

	/**
	 * Retorna la tabla de caracteres
	 * @return
	 */
	public DatosCaracter[] getTabla() {
		return tabla;
	}

	/**
	 * Retorna el numero de caracteres del mensaje
	 * @return
	 */
	public int getNumeroCaracteres() {
		return numeroCaracteres;
	}
	
	/**
	 * Agrega el dato a la tabla de caracteres
	 * @param datos Los datos del caracter.
	 */
	public void agregarATabla(DatosCaracter datos){
		if(tabla[numeroCaracteres+1] == null){
			tabla[numeroCaracteres++] = datos;
		}
	}

	/**
	 * Agrega el bitstring al mensaje actual.
	 * @param nMensaje El contenido a concatenar al mensaje.
	 */
	public void agregarAMensaje(BitString nMensaje){
		mensaje.concatenar(nMensaje);
	}
	
	/**
	 * Descomprime el mensaje
	 * @return El mensaje descomprimido
	 */
	public String descomprimir(){
		//Crea la raiz de un arbol binario
		ArbolBinario<Character> arbol = new ArbolBinario<Character>();
		
		//Genera el arbol y se le asigna el contenido a la raiz
		NodoArbol<Character> raiz = generarArbol();
		arbol.setRaiz(raiz);
		String codificado = "";
		
		//Asigna al mensaje los caracteres a partir del recorrido del arbol generado.
		NodoArbol<Character> actual = raiz;
		int contadorBit = 0;
		while(contadorBit<mensaje.getLongitud()){
			actual = mensaje.consultarBit(contadorBit)? actual.getDerecho(): actual.getIzquierdo();
			contadorBit++;
			if(actual.esHoja()){
				codificado+=actual.getElemento();
				actual = raiz;
			}
		}
		return codificado;
	}
	
	/**
	 * Genera el arbol a partir de la tabla de caracteres.
	 * @return la raiz del arbol generado
	 */
	private NodoArbol<Character> generarArbol(){
		NodoArbol<Character> raiz = new NodoArbol<Character>('*');
		for(int i = 0; i<numeroCaracteres; i++){
			agregarARaiz(raiz,tabla[i],1);
		}
		return raiz;
	}

	/**
	 * Configura la raiz dada por parametro a partir de la secuencia de bits de cada caracter.
	 * @param raiz La raiz a configurar
	 * @param datosCaracter Los datos del caracter
	 * @param posicion La posicion del bit que se lee.
	 */
	private void agregarARaiz(NodoArbol<Character> raiz, DatosCaracter datosCaracter,int posicion) {
		if(posicion == datosCaracter.getCodigo().getLongitud()){
			if(datosCaracter.getCodigo().consultarBit(posicion-1)){
				raiz.setDerecho(new NodoArbol<Character>(datosCaracter.getCaracter()));
			}
			else{
				raiz.setIzquierdo(new NodoArbol<Character>(datosCaracter.getCaracter()));
			}
		}
		else{
			if(datosCaracter.getCodigo().consultarBit(posicion-1)){
				if(raiz.getDerecho() == null){
					raiz.setDerecho(new NodoArbol<Character>('*'));
				}
				agregarARaiz(raiz.getDerecho(),datosCaracter,posicion +1);
			}
			else{
				if(raiz.getIzquierdo() == null){
					raiz.setIzquierdo(new NodoArbol<Character>('*'));
				}
				agregarARaiz(raiz.getIzquierdo(),datosCaracter,posicion +1);
			}
		}
	}
}
