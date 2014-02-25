package mundo;

import estructuras.Lista;
import estructuras.TablaHashing;

public class CentralColegios implements ICentralColegios {
	
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * El usuario actual de la central
	 */
	private Usuario usuarioActual;
	private TablaHashing<Llave,Colegio> colegios;
	
	/**
	 * 
	 */
	private TablaHashing<Llave, Colegio> colegios;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public CentralColegios(){
		usuarioActual = null;
		colegios = new TablaHashing<>(10000, 5000); 
	}

	@Override
	public Usuario agregarNuevoUsuario(String usuario, String contrasena) {
		Usuario usu = new Usuario(usuario, contrasena);
		//colegios.agregar(, usu);
		usuarioActual = usu;
		return usu;
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
	public Colegio[] buscarPorCriterio(Criterio[] criterios){	
		return auxiliar(criterios,colegios.darLista());
	}	
	
	private Colegio[] auxiliar(Criterio[] criterios, Lista colegios){
		if(criterios.length == 0){
			return colegios;
		}
		else{
			
		}
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

	public Usuario darUsuarioActual() {
		return usuarioActual;
	}

}
