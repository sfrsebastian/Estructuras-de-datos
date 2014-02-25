package estructuras;

public class NodoTabla<K,V extends Comparable <?super V>> implements Comparable<NodoTabla<K,V>> {

	private K llave;

	private V elemento;

	public NodoTabla(K nLlave, V nElemento) {
		llave = nLlave;
		elemento=nElemento;		
	}

	@Override
	public int compareTo(NodoTabla<K,V> otro) {
		return elemento.compareTo(otro.darElemento());
	}
	public V darElemento() {
		return elemento;
	}

	public K darLlave(){
		return llave;
	}

}
