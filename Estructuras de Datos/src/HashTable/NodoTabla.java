package HashTable;

public class NodoTabla<K,V> {

	private K llave;

	private V elemento;

	public NodoTabla(K nLlave, V nElemento) {
		llave = nLlave;
		elemento=nElemento;		
	}

	public V darElemento(K k) {
		return elemento;
	}
	public K darLlave(){
		return llave;
	}

}
