package mundo;

import java.io.Serializable;

public class Llave implements Serializable {
	/**
	 * String para la llave
	 */
	String string;
	
	/**
	 * int para la llave
	 */
	int numero;
	
	/**
	 * Constructor de llave a partir de string
	 * @param nString
	 */
	public Llave(String nString){
		string = nString;
		numero = -1000;
	}
	
	/**
	 * Constructor de Llave a partir de numero
	 * @param nNumero
	 */
	public Llave(int nNumero){
		numero = nNumero;
		string = null;
	}
	
	/**
	 * Metodo to string. varia segun string o int.
	 */
	@Override
	public String toString(){
		if(string == null){
			return numero+"";
		}
		else{
			return string;
		}
	}
	
	/**
	 * Hashcode de la llave. Varia segun string o int.
	 */
	@Override
	public int hashCode(){
		if(string == null){
			return Math.abs(numero);
		}
		else{
			return Math.abs(string.hashCode());
		}
	}
	
}
