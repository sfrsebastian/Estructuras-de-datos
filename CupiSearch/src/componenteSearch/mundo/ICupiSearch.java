package componenteSearch.mundo;

import java.io.IOException;
import java.util.Iterator;

public interface ICupiSearch {

	/**
	 * Agrega el url dado por parametro al explorador
	 */
	public void agregarSitiosFuente(String url);

	/**
	 * Explora los sitios agregados en el explorador.<br>
	 * Asigna a la exploracion actual los resultados de busqeuda.
	 * @param El tiempo de busqueda
	 * @param niveles Los niveles de busqueda
	 */
	public void explorarSitios(long l, int niveles);

	/**
	 * Retorna un iterador con el historial de exploraciones del componente
	 * @return Iterador con las exploraciones
	 */
	public Iterator<Exploracion> darHistorialExploraciones();

	/**
	 * Busca los resultados a partir de los criterios y el valor especificados.
	 * @param criterios Los criterios de busqueda
	 * @param arreglo El arreglo de recursos
	 * @param valor El valor de busqueda. CONTIENE,NOCONTIENE;IGUAL
	 * @return Arreglo con el resultado de busqueda
	 */
	public Object[] buscarResultados(String[] criterios, String valor);

	/**
	 * Crea una nueva categoria a partir del nombre y descripcion dados de parametro
	 * @param nombre El nombre de la categoria
	 * @param La descripcion de la categoria
	 */
	public void crearCategoria(String nombre, String descripcion);

	/**
	 * Elimina la categoria dada por parametro
	 * @param La categoria a eliminar
	 */
	public boolean eliminarCategoria(Categoria categoria);

	/**
	 * Agregar el recurso dado por parametro a la categoria
	 * @param La categoria a la cual agregar el recurso
	 * @param El recurso a agregar
	 */
	public void agregarRecursoACategoria(Categoria categoria, Recurso recurso);

	/**
	 * Elimina el recurso dado por parametro de la categoria dada por parametro
	 */
	public void eliminarRecursoDeCategoria(Categoria categoria, Recurso recurso);

	/**
	 * Comprime las categorias acorde al protocolo del servidor de persistencia
	 * @throws Exception
	 */
	public void comprimirCategorias() throws Exception;

	/**
	 * Recupera las categorias del servidor de persistencia
	 * @throws Exception
	 */
	public void recuperarCategorias() throws Exception;

	/**
	 * Descarga la imagen del recurso
	 * @throws IOException
	 */
	public String visualizarImagen(Recurso recurso) throws IOException;

}
