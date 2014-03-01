package testHashTable;

public class Llave {
	String valor;
	public Llave(String nValor){
		valor = nValor;
	}
	
	public int hashCode(){
		return Math.abs(valor.hashCode());
	}
	
	@Override
	public String toString() {
		return valor;
	}
}
