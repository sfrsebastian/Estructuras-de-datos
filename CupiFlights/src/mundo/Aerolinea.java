package mundo;

import java.io.Serializable;

import Arbol23.Arbol23;

public class Aerolinea implements Comparable<Aerolinea>, Serializable {
	//--------------------
	//CONSTANTES
	//--------------------
	/**
	 * Serial identificador de la clase
	 */
	private static final long serialVersionUID = -1987549154387168173L;

	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * Los vuelos de la aerolinea
	 */
	private Arbol23<Vuelo> vuelos;

	/**
	 * Cantidad de vuelos tardios 15 minutos
	 */
	private int vuelos15;

	/**
	 * Cantidad de vuelos tardios 30 minutos.
	 */
	private int vuelos30;

	/**
	 * Cantidad de vuelos tardios 45 minutos
	 */
	private int vuelos45;

	/**
	 * Cantidad de vuelos cancelados
	 */
	private int vuelosCancelados;

	/**
	 * Codigo identificador de la aerolinea
	 */
	private String codigo;

	/**
	 * Nombre de la aerolinea
	 */
	private String nombre;

	//--------------------
	//CONSTRUCTORES
	//--------------------
	/**
	 * Crea una nueva aerolinea con el codigo y nombre dados
	 * @param nCodigo El codigo de la aerolinea
	 * @param nNombre El nombre de la aerolinea
	 */
	public Aerolinea(String nCodigo,String nNombre){
		codigo = nCodigo;
		nombre = nNombre;
		vuelos15 = 0;
		vuelos30 = 0;
		vuelos45 = 0;
		vuelosCancelados = 0;
		vuelos = new Arbol23<Vuelo>();
	}

	/**
	 * Crea una nueva aerolinea vacia con el codigo dado
	 * @param nCodigo
	 */
	public Aerolinea(String nCodigo){
		codigo = nCodigo;
	}

	//--------------------
	//METODOS
	//--------------------
	/**
	 * Agrega el vuelo dado por parametro al arbol de vuelos<br>
	 * Aumenta los indicadores de tardanza.
	 * @param vuelo El vuelo a agregar
	 * @throws Exception
	 */
	public void agregarVuelo(Vuelo vuelo) throws Exception{
		boolean agregado = vuelos.agregar(vuelo);
		if(agregado){
			vuelos15 += vuelo.getL15();
			vuelos30 += vuelo.getL30();
			vuelos45 += vuelo.getL45();
			vuelosCancelados+=vuelo.getCancelados();
		}
	}

	/**
	 * Elimina el vuelo dado por parametro del arbol de vuelos<br>
	 * Disminuye los indicadores de tardanza.
	 * @param vuelo El vuelo a agregar
	 * @throws Exception
	 */
	public void eliminarVuelo(Vuelo actual) throws Exception {
		Vuelo vuelo = vuelos.eliminar(actual); 
		if(vuelo != null){
			vuelos15 -= vuelo.getL15();
			vuelos30 -= vuelo.getL30();
			vuelos45 -= vuelo.getL45();
			vuelosCancelados-=vuelo.getCancelados();
		}
	}

	/**
	 * Retorna el codigo de la aerolinea
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Retorna el nombre de la aerolinea
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Retorna la cantidad de vuelos con tardanza de 15 minutos.
	 * @return
	 */
	public int getVuelos15() {
		return vuelos15;
	}

	/**
	 * Retorna la cantidad de vuelos con tardanza de 30 minutos
	 * @return
	 */
	public int getVuelos30() {
		return vuelos30;
	}

	/**
	 * Retorna la cantidad de vuelos con tardanza de 45 minutos
	 * @return
	 */
	public int getVuelos45() {
		return vuelos45;
	}

	/**
	 * Retorna la cantidad de vuelos cancelados
	 * @return
	 */
	public int getVuelosCancelados() {
		return vuelosCancelados;
	}

	/**
	 * Retorna el indice de retraso total de la aerolinea.
	 * @return
	 */
	public int getRetrasoTotal(){
		return vuelos15+vuelos30+vuelos45;
	}

	/**
	 * Metodo toString de la aerolinea<br>
	 * Retorna el nombre de la aerolinea.
	 */
	public String toString(){
		return nombre;
	}

	/**
	 * Metodo de comparacion por codigos entre dos aerolineas<br>
	 */
	public int compareTo(Aerolinea o) {
		int comparacion = codigo.compareTo(o.codigo);
		if(comparacion>0){
			return 1;
		}
		else if(comparacion<0){
			return -1;
		}
		else{
			return 0;
		}
	}
}
