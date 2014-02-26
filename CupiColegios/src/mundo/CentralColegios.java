package mundo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;

import HashTable.TablaHashing;
import Lista.Lista;

public class CentralColegios implements ICentralColegios {
	
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * El usuario actual de la central
	 */
	private Usuario usuarioActual;
	
	/**
	 * 
	 */
	private TablaHashing<Llave,Colegio> colegios;
	
	/**
	 * 
	 */
	private TablaHashing<Llave, Usuario> usuarios;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public CentralColegios() throws FileNotFoundException, IOException, ClassNotFoundException{
		usuarioActual = null;
		ObjectInputStream ois =  new ObjectInputStream(new FileInputStream("./data/colegiosSerializados.col"));
		colegios = (TablaHashing<Llave, Colegio>) ois.readObject();
		usuarios = new TablaHashing<Llave, Usuario>(7,2);
	}

	@Override
	public Usuario agregarNuevoUsuario(String usuario, String contrasena) {
		Usuario usu = new Usuario(usuario, contrasena);
		usuarios.agregar(new Llave(usu.getContrasena()), usu);
		usuarioActual = usu;
		System.out.println("Usuario: " + usu.getUsuairo() + " conectado!");
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
	public Object[] buscarPorCriterio(Criterio[] criterios){	
		return auxiliar(criterios, colegios.darLista());
	}	
	
	private Object[] auxiliar(Criterio[] criterios, Lista nColegios){
		if(criterios.length == 0){
			return nColegios.darArreglo();
		}
		else{
			Criterio criterio = criterios[0];
			if(criterio.darSubcriterios().length == 0){
				return auxiliar(nuevosCriterios(criterios),nColegios);
			}
			Lista<Colegio> nueva = new Lista<Colegio>();
			Iterator<Colegio> iterador = nColegios.iterator();
			while(iterador.hasNext()){
				Colegio actual = iterador.next();
				if(actual.cumpleCriterio(criterio)){
					nueva.agregar(actual);
				}
			}
			return auxiliar(nuevosCriterios(criterios),nueva);
		}
	}
	private Criterio[] nuevosCriterios(Criterio[] criterios) {
		Criterio[] nueva = new Criterio[criterios.length-1];
		for(int i = 1; i<criterios.length;i++){
			nueva[i-1] = criterios[i];
		}
		return nueva;
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
	
	public Object[] darColegios(){
		return colegios.darArreglo();
	}

}
