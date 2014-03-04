package mundo;

public interface ICentralColegios {
	
	/**
	 * Agrega un nuevo usuario a la lista de usuarios
	 * @param usuario El usuario que se quiere agregar
	 * @param contrasena La constrasena del nuevo usuario
	 */
	public Usuario agregarNuevoUsuario(String usuario, String contrasena);
	
	/**
	 * Registra un hijo dado un usuario
	 * @param usuario El usuario al que se le quiere registrar el hijo
	 * @param hijo El hijo que se quiere registrar
	 * @return TRUE si se pudo registrar un hijo, FALSE en caso contrario
	 */
	public boolean registrarHijoUsuario(Usuario usuario, Hijo hijo);
	
	/**
	 * Agrega un nuevo colegio a los colegios favoritos del hijo
	 * @param colegio El colegio que se quiere agregar
	 * @param hijo El hijo al que se le van a agregar el colegio
	 * @return TRUE si se pudo agregar un colegio, FALSE en caso contrario
	 * @throws GeneroIncompatibleException para el caso de genero incompatible
	 */
	public boolean anadirColegioHijo(Colegio colegio, Hijo hijo) throws GeneroIncompatibleException;
	
	/**
	 * Busca dado el criterio o criterios que se mandan por parametro
	 * @param criterios Los criterios que se quieren buscar
	 */
	public Object[] buscarPorCriterio(Criterio[] criterios);
	
	/**
	 * Busca los colegios dada una area, un anio academico y un rango de puntajes
	 * @param area El area por la que se quiere buscar
	 * @param anio El anio por el que se quiere buscar
	 * @param puntaje El rango del puntaje que se quiere filtrar
	 * @return Un arreglo de colegios que correspondan al parametro de busqueda
	 * @throws RangoInvalidoException En caso de que el rango se salgade 0 - 10
	 */
	public Object[] buscarPorArea(Area area, int anio, int puntajeA, int puntajeB);
	
	/**
	 * Muestra la informacion del colegio desde la interfaz y retorna el colegio
	 * @param colegio El colegio por el cual se quiere mostrar la informacion
	 * @return El colegio que se quiere mostrar
	 */
	public Colegio mostrarInfoColegio(Colegio colegio);
	
	/**
	 * Muestra lo colegio recomendados entre los colegios favoritos del hijo
	 * @param hijo El hijo del que se muestran los colegios
	 * @return Un arreglo de colegios recomendados
	 */
	public Object[] mostrarRecomendados(Hijo hijo);
	
	/**
	 * Muestra las estaditicas nacionales
	 */
	public void mostrarEstadisticasNacionales();
	
	/**
	 * Muestra los colegios dados una ubicacion
	 * @param ubicacion La ubicacion que se quiere filtrar
	 * @param esDepto Si es un departamento TRUE sino es un municipio
	 * @return Arreglo de Colegios con la informacion buscada
	 */
	public Object[] mostrarColegiosPorUbicacion(int codigoDepartamento,int codigoMunicipio);

	public String[][] darDatosDepartamentos();
}
