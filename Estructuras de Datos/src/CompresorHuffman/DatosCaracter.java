package CompresorHuffman;

import BitString.BitString;

public class DatosCaracter {
	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * El caracter representativo
	 */
	private char caracter;
	
	/**
	 * El codigo representativo del caracter.
	 */
	private BitString codigo;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea un nuevo objeto con el caracter y sus datos
	 * @param c El caracter representativo.
	 */
	public DatosCaracter(char c){
		caracter = c;
		codigo = null;
	}
	
	/**
	 * Crea un nuevo Dato caracter a partir del char y el codigo dado
	 * @param c
	 * @param nCodigo
	 */
	public DatosCaracter(char c,String nCodigo){
		caracter = c;
		codigo = new BitString(nCodigo);
	}
	//------------------------------------------
	// Metodos
	//------------------------------------------
	/**
	 * Retorna el caracter representativo
	 * @return el caracter.
	 */
	public char getCaracter() {
		return caracter;
	}

	/**
	 * Retorna el codigo representativo
	 * @return el codigo
	 */
	public BitString getCodigo() {
		return codigo;
	}

	/**
	 * Agrega el bit dado por parametro al codigo del caracter.
	 * @param datos Los datos base para concatenacion
	 * @param bit el bit a agregar
	 */
	public void agregarBit(DatosCaracter datos,boolean bit){
		if( datos.getCodigo() != null ){
			codigo = new BitString(datos.codigo );
		}
		else if( codigo == null ){
			codigo = new BitString( );
		}
		codigo.agregarBit(bit);
	}
	
	public String toString(){
		return caracter+codigo.toString();
	}
}
