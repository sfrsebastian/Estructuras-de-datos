package mundo;

public class Llave {
	String string;
	int numero;
	public Llave(String nString){
		string = nString;
		numero = -1000;
	}
	
	public Llave(int nNumero){
		numero = nNumero;
		string = null;
	}
	public String toString(){
		if(string == null){
			return numero+"";
		}
		else{
			return string;
		}
	}
	
	public int hashCode(){
		if(string == null){
			return Math.abs(numero);
		}
		else{
			return Math.abs(string.hashCode());
		}
	}
	
}
