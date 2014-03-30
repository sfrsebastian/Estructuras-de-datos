package BitString;

public class BitString {
	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * Arreglo de bytes
	 */
	private byte[] bytes;
	
	/**
	 * Cantidad de bits registrados
	 */
	private int longitud;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea un nuevo bistring vacio
	 */
	public BitString(){
		bytes = null;
		longitud = 0;
	}
	
	/**
	 * Crea un nuevo bistring a partir del bistring dado por parametro.
	 * @param codigo
	 */
	public BitString(BitString codigo) {
		if(codigo.getBytes() == null){
			bytes = null;
			longitud = 0;
		}
		else{
			longitud = codigo.getLongitud();
			bytes = new byte[codigo.getBytes().length];
			for(int i = 0; i<codigo.getBytes().length;i++){
				bytes[i] = codigo.getBytes()[i];
			}
		}
		
	}
	
	/**
	 * Crea un nuevo bitString a partir del codigo dado
	 * @param nCodigo
	 */
	public BitString(String nCodigo) {
		for(int i = 0;i<nCodigo.length();i++){
			boolean bit = nCodigo.charAt(i) == 1;
			agregarBit(bit);
		}
	}

	public BitString(byte[] nMensaje, int num) {
		longitud = num;
		bytes = nMensaje;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------
	/**
	 * Retorna el arreglo de bytes
	 * @return el arreglo de bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}
	
	/**
	 * Retorna la cantidad de bits
	 * @return
	 */
	public int getLongitud() {
		return longitud;
	}
	
	/**
	 * Agrega un bit en la posicion y valor dados.
	 * @param posBit La posicion donde se quiere agregar el bit
	 * @param bit El estado del bit
	 */
	public void agregarBit(int posBit, boolean bit) {
		int posByte = posBit / 8;
		//Recalcula el tamanio del arreglo segun la posicion del byte obtenida
		if( bytes == null || posBit >= bytes.length * 8 ){
			if( bytes == null ){
				bytes = new byte[posByte + 1];
				for(int i = 0; i < posByte + 1; i++){
					bytes[i] = 0;
				}
			}
			else{
				byte[] old = bytes;
				bytes = new byte[posByte + 1];
				for( int i = 0; i < posByte + 1; i++ ){
					bytes[ i ] = i < old.length ? old[ i ] : 0;
				}
			}
		}
		//modifica el bit en la posicion dada
		int posEnByte = posBit % 8;
		byte mascara = 1;
		for( int i = 0; i < posEnByte; i++ ){
			mascara *= 2;
		}
		//bit es true
		if( bit && posBit >= 0 ){
			bytes[ posByte ] = ( byte ) ( bytes[ posByte ] | mascara );
		}
		//bit es false
		else if( posBit >= 0 ){
			mascara = ( byte )~mascara;
			bytes[ posByte ] = ( byte ) ( bytes[ posByte ] & mascara );
		}
		//recalcula longitud
		if( posBit >= longitud){
			longitud = posBit + 1;
		}
	}
	
	/**
	 * Retorna el estado del bit dado por parametro
	 * @param posicion La posicion del bit a consultar
	 * @return True si esta prendido, false de lo contrario.
	 */
	public boolean consultarBit(int posicion){
		int posByte = posicion / 8;
		int posEnByte = posicion % 8;
		byte mascara = 1;
		for( int i = 0; i < posEnByte; i++ ){
			mascara *= 2;
		}
		int respuesta = bytes[ posByte ] & mascara;
		return !(respuesta == 0);
	}
	
	/**
	 * Agrega el bit al final de la secuencia actual.
	 * @param bit el bit a agregar
	 */
	public void agregarBit(boolean bit){
		agregarBit(longitud,bit);
	}
	
	/**
	 * Concatena la cadena de bits con la dada por parametro<br>
	 * El mensaje dado por parametro es agregado despues del actual.
	 * @param mensaje
	 */
	public void concatenar(BitString mensaje) {
		if(mensaje.getLongitud() != 0){
			if(bytes == null){
				bytes = mensaje.getBytes();
				longitud = mensaje.getLongitud();
			}
			else{
				for(int i = 0; i<mensaje.getLongitud() ; i++){
					boolean bit = mensaje.consultarBit(i);
					agregarBit(bit);
				}
			}
		}
	}
	
	public String toString(){
		String respuesta = "";
		for(int i = 0; i<longitud;i++){
			respuesta+= consultarBit(i)?1:0;
		}
		return respuesta;
	}
}
