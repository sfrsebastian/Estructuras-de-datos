package CompresorHuffman;

public class NodoHuffman implements Comparable<NodoHuffman> {
	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * El caracter del nodo.
	 */
	private char caracter;
	
	/**
	 * La frecuencia del caracter
	 */
	private int frecuencia;
	
	/**
	 * El nodo siguiente para la lista huffman
	 */
	private NodoHuffman siguiente;
	
	/**
	 * El nodo anterior para la lista huffman
	 */
	private NodoHuffman anterior;
	
	/**
	 * El nodo izquierdo para el arbol huffman
	 */
	private NodoHuffman izquierdo;
	
	/**
	 * El nodo derecho para el arbol huffman
	 */
	private NodoHuffman derecho;
	
	/**
	 * Los datos del caracter.
	 */
	private DatosCaracter datos;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea un nuevo nodo a partir del caracter dado
	 * @param c
	 */
	public NodoHuffman(char c){
		caracter = c;
		frecuencia = 0;
		datos = new DatosCaracter(c);
		siguiente = null;
		anterior = null;
		izquierdo= null;
		derecho = null;
	}
	
	/**
	 * Crea un nuevo nodo a partir de los nodos izquierdos y derechos dados por parametro
	 * @param izq el NodoHuffman izquierdo
	 * @param der el NodoHuffman derecho
	 */
	public NodoHuffman(NodoHuffman izq, NodoHuffman der){
		caracter = '*';
		frecuencia = izq.getFrecuencia() + der.getFrecuencia();
		izquierdo = izq;
		derecho = der;
		siguiente = null;
		anterior = null;
		datos = new DatosCaracter('*');
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	/**
	 * Aumenta la frecuencia del caracter del nodo
	 */
	public void aumentarFrecuencia(){
		frecuencia++;
	}
	
	/**
	 * Retorna el caracter del nodo
	 * @return el caracter
	 */
	public char getCaracter() {
		return caracter;
	}
	
	/**
	 * retorna la frecuencia del caracter
	 * @return la frecuencia del caracter
	 */
	public int getFrecuencia() {
		return frecuencia;
	}
	
	/**
	 * Retorna le nodo siguiente
	 * @return siguiente
	 */
	public NodoHuffman getSiguiente() {
		return siguiente;
	}
	
	/**
	 * Retorna el nodo anterior
	 * @return anterior
	 */
	public NodoHuffman getAnterior() {
		return anterior;
	}
	
	/**
	 * Retorna el nodo izquierdo
	 * @return izquierdo
	 */
	public NodoHuffman getIzquierdo() {
		return izquierdo;
	}
	
	/**
	 * Retorna el nodo derecho
	 * @return derecho
	 */
	public NodoHuffman getDerecho() {
		return derecho;
	}
	
	/**
	 * Retorna los datos del caracter
	 * @return
	 */
	public DatosCaracter getDatos(){
		return datos;
	}
	
	/**
	 * Cambia el nodo anterior
	 * @param ant el nuevo nodo
	 */
	public void cambiarAnterior(NodoHuffman ant){
		anterior = ant;
	}
	
	/**
	 * Cambia el nodo siguiente
	 * @param sig el nuevo nodo
	 */
	public void cambiarSiguiente(NodoHuffman sig){
		siguiente = sig;
	}
	
	/**
	 * Asigna el codigo a los nodos izquierdo y derecho.
	 */
	public void asignarCodigos() {
		if(izquierdo != null){
			izquierdo.getDatos().agregarBit(datos,false);
			izquierdo.asignarCodigos();
		}
		if(derecho != null){
			derecho.getDatos().agregarBit(datos,true);
			derecho.asignarCodigos();
		}
	}

	/**
	 * Metodo que compara dos NodosHuffman
	 */
	public int compareTo(NodoHuffman o) {
		if(frecuencia == o.getFrecuencia()){
			if(caracter<o.getCaracter()){
				return -1;
			}
			else{
				return 1;
			}
		}
		else if(frecuencia < o.getFrecuencia()){
			return -1;
		}
		else{
			return 1;
		}
	}

	/**
	 * Metodo toString del nodo
	 */
	public String toString(){
		String der = "NO TIENE";
		String izq = "NO TIENE";
		if(derecho != null){
			try {
				der = derecho.getCaracter()+"";
			} catch (Exception e) {
				System.out.println("Agregar el metodo toString() del elemento");
				der = "TIENE";
			}
		}
		if(izquierdo != null){
			try {
				izq = izquierdo.getCaracter()+"";
			} catch (Exception e) {
				System.out.println("Agregar el metodo toString() del elemento");
				izq = "TIENE";
			}
		}
		return "Soy: " + caracter + ", izquierdo: " + izq + " derecho: " + der ;
	}
	
}
