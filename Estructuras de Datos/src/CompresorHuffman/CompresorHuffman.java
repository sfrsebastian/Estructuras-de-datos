package CompresorHuffman;

public class CompresorHuffman {
	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * El texto a comprimir
	 */
	private String texto;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea un nuevo compresor de Huffman
	 * @param nTexto el texto a comprimir
	 */
	public CompresorHuffman(String nTexto){
		texto = nTexto;
	}
	
	//------------------------------------------
	// Metodos
	//-----------------------------------------
	/**
	 * Comprime el mensaje a partir del algoritmo de Huffman
	 * @return TextoComprimido el texto comprimido.
	 */
	public TextoComprimido comprimir(){
		//Crea un listado general de los 256 characteres ascii
		NodoHuffman[] nodosAscii = new NodoHuffman[256];
		for(int i=0; i<256; i++){
			nodosAscii[i] = new NodoHuffman((char) i);
		}

		//Cuenta la frecuencia de cada caracter en el texto
		for(int i = 0;i<texto.length();i++){
			nodosAscii[texto.charAt(i)].aumentarFrecuencia();
		}

		//agrega a la listaHuffman los nodos con frecuencia mayor a 0
		ListaHuffman lista = new ListaHuffman();
		for(int i = 0; i<256; i++){
			NodoHuffman actual = nodosAscii[i];
			if(actual.getFrecuencia() > 0){
				lista.agregar(actual);
			}
		}

		//Genera el arbol y asigna codigos a cada caracter
		lista.generarArbol();
		
		//Crea el texto comprimido
		TextoComprimido comprimido = new TextoComprimido(texto.length());
		for (int i = 0; i < nodosAscii.length; i++) {
			NodoHuffman actual = nodosAscii[i];
			if(actual.getFrecuencia()>0){
				comprimido.agregarATabla(actual.getDatos());
			}
		}
		
		//agrega los datos al mensaje comprimido
		for (int i = 0; i < texto.length(); i++) {
			NodoHuffman actual = nodosAscii[texto.charAt(i)];
			comprimido.agregarAMensaje(actual.getDatos().getCodigo());
		}
		return comprimido;
	}
}
