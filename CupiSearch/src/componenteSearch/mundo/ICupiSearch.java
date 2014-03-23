package componenteSearch.mundo;

import java.net.URL;
import java.util.Iterator;
import java.util.Timer;

public interface ICupiSearch {

	public void agregarSitiosFuente(URL url);

	public void explorarSitios();

	public String mostrarEstadistica(Exploracion nExploracion);

	public Iterator<Exploracion> darHistorialExploraciones();

	public Object[] buscarResultados(Categoria[] criterios, int segundos, int nivel);

	public void crearCategoria(String nombre);

	public boolean eliminarCategoria(Categoria categoria);

	public void agregarRecursoACategoria(Categoria categoria, Recurso recurso);

	public void eliminarRecursoDeCategoria(Categoria categoria, Recurso recurso);

	public void comprimirCategorias();

	public void recuperarCategorias();

	public void visualizarResultado(Recurso recurso);

}
