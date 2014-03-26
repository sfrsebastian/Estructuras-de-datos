package componenteSearch.mundo;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Timer;

public interface ICupiSearch {

	public void agregarSitiosFuente(String url);

	public void explorarSitios(long l);

	public String mostrarEstadistica(Exploracion nExploracion);

	public Iterator<Exploracion> darHistorialExploraciones();

	public Object[] buscarResultados(String[] criterios, int segundos, int nivel);

	public void crearCategoria(String nombre);

	public boolean eliminarCategoria(Categoria categoria);

	public void agregarRecursoACategoria(Categoria categoria, Recurso recurso);

	public void eliminarRecursoDeCategoria(Categoria categoria, Recurso recurso);

	public void comprimirCategorias();

	public void recuperarCategorias();

	public String visualizarImagen(Recurso recurso) throws IOException;

}
