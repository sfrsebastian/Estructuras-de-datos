package mundo;

import java.io.Serializable;
import java.util.Iterator;

import HashTable.TablaHashing;

public class Departamento implements Comparable<Departamento>, Serializable {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * El nombre del departamentos.
	 */
	private String nombre;
	
	/**
	 * El codigo unico del departamento.
	 */
	private int codigo;
	
	/**
	 * La lista de colegios del departamento
	 */
	private TablaHashing<Llave, Colegio> colegios;
	
	/**
	 * La lista de municipios del departamento.
	 */
	private TablaHashing<Llave, Municipio> municipios;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	/**
	 * Metodo que crea un nuevo departamento.
	 * @param nNombre
	 * @param nCod
	 */
	public Departamento(String nNombre, int nCod){
		nombre = nNombre;
		codigo = nCod;
		colegios = new TablaHashing<Llave, Colegio>(103,2);
		municipios = new TablaHashing<Llave,Municipio>(1000,2);
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	/**
	 * El nombre del departamento.
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * El codigo del departamento.
	 * @return
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Los colegios del departamento
	 * @return
	 */
	public TablaHashing<Llave, Colegio> getColegios() {
		return colegios;
	}
	
	/**
	 * Los municipios del Departamento
	 * @return
	 */
	public TablaHashing<Llave, Municipio> getMunicipios() {
		return municipios;
	}

	/**
	 * Agrega el colegio dado por parametro a la lista de colegios.
	 * @param llave
	 * @param colegio
	 */
	public void agregarColegio(Llave llave,Colegio colegio){
		colegios.agregar(llave, colegio);
	}
	
	/**
	 * Agrega el municipio dado por parametro a la lista de municipios.
	 * @param llave
	 * @param municipio
	 */
	public void agregarMunicipio(Llave llave,Municipio municipio){
		if(municipios.buscar(llave) == null){
			municipios.agregar(llave, municipio);
		}
	}
	
	/**
	 * Busca el municipio dada su llave.
	 * @param llave
	 * @return
	 */
	public Municipio buscarMunicipio(Llave llave){
		return municipios.buscar(llave);
	}
	
	/**
	 * Metodo que compara dos departamentos.
	 */
	@Override
	public int compareTo(Departamento otro) {
		if(nombre.compareTo(otro.getNombre())==0){
			return 0;
		}
		else if(nombre.compareTo(otro.getNombre())>0){
			return 1;
		}
		else{
			return -1;
		}
	}
	
	/**
	 * Metodo to string del departamento. Incluye unicamente su nombre.
	 */
	@Override
	public String toString(){
		return nombre;
	}

	/**
	 * Retorna su cantidad de colegios muy superiores.
	 * @return
	 */
	public int darTotalMuySuperiores() {
		int total = 0;
		Iterator<Colegio> iterador = colegios.iterator();
		while(iterador.hasNext()){
			Colegio actual = iterador.next();
			if(actual.getNivel().equals(Criterio.MUY_SUPERIOR)){
				total++;
			}
		}
		return total;
	}

	/**
	 * Retorna el promedio de los colegios del departamento.
	 */
	public double darPromedio() {
		double suma = 0;
		Iterator<Colegio> iterador = colegios.iterator();
		while(iterador.hasNext()){
			Colegio actual = iterador.next();
			suma+=actual.darPromedio();
		}
		return suma/colegios.darLongitud();
	}

	/**
	 * Retorna el promedio de matemataicas del departamento.
	 * @return
	 */
	public double darPromedioMatematicas() {
		double suma = 0;
		Iterator<Colegio> iterador = colegios.iterator();
		while(iterador.hasNext()){
			Colegio actual = iterador.next();
			suma+=actual.getNotas().getMatematicas();
		}
		return suma/colegios.darLongitud();
	}

}
