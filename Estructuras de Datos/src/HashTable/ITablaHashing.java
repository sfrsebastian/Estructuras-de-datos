package HashTable;

public interface ITablaHashing<K,V> extends Iterable<V> {

	public boolean agregar(K nLlave, V nElemento);

	public V eliminar(K nLlave);

	public V buscar(K nLlave);

}
