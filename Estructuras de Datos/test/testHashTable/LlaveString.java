package testHashTable;

public class LlaveString {
	String valor;
	public LlaveString(String nValor){
		valor = nValor;
	}
	
	public int hashCode(){
		return Math.abs(valor.hashCode());
	}
}
