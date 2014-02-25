package mundo;

public class CentralColegios implements ICentralColegios {
	
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * El usuario actual de la central
	 */
	private Usuario usuarioActual;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public CentralColegios(){
		usuarioActual = null;
	}

	@Override
	public void agregarNuevoUsuario(String usuario, String contrasena) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean registrarHijoUsuario(Usuario usuario, Hijo hijo) {
		usuario.agregarHijo(hijo);
		return true;
	}

	@Override
	public boolean anadirColegioHijo(Colegio colegio, Hijo hijo)
			throws GeneroIncompatibleException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] buscarPorCriterio(String[] criterios) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Colegio[] buscarPorArea(Area area, AnioAcademico anio, int puntaje)
			throws RangoInvalidoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Colegio mostrarInfoColegio(Colegio colegio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] mostrarRecomendados(Hijo hijo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mostrarEstadisticas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mostrarEstadisticasNacionales() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] mostrarColegiosPorUbicacion(String ubicacion,
			boolean esDepto) {
		// TODO Auto-generated method stub
		return null;
	}

}
