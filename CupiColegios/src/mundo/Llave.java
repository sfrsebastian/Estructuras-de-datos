package mundo;

import java.io.Serializable;

public class Llave implements Serializable {
	String string;
	int numero;
	public Llave(String nString){
		string = nString;
		numero = -1000;
	}
	
	//public Llave(String nom, String )
	
	public Llave(int nNumero){
		numero = nNumero;
		string = null;
	}
	@Override
	public String toString(){
		if(string == null){
			return numero+"";
		}
		else{
			return string;
		}
	}
	
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
