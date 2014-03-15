package Cola;

import java.util.NoSuchElementException;

public interface ICola<T> {
	/**
	 * Retorna el primer elemento de la cola
	 * @return el primer elemento de la cola
	 * @throws NoSuchElementException En caso de que no haya elementos por retornar
	 */
	public T dar() throws NoSuchElementException;
	
	/**
	 * Agrega un nuevo elemento al final de la cola
	 * @param pElemento el elemento a agregar a la cola
	 */
	public void agregar(T pElemento);
}
