package mundo;

import java.util.Iterator;

import HashTable.TablaHashing;

public class Anio implements Comparable<Anio> {
	/**
	 * El valor numerico del anio
	 */
	private int anio;
	
	/**
	 * La lista de colegios del anio
	 */
	private TablaHashing<Llave,Colegio> colegios;
	
	/**
	 * La lista de departamentos del anio
	 */
	private TablaHashing<Llave,Departamento> departamentos;
	
	/**
	 * Construye un nuevo año dados los parametros
	 * @param nAnio
	 * @param nColegios
	 * @param nDepartamentos
	 */
	public Anio(int nAnio,TablaHashing<Llave,Colegio> nColegios, TablaHashing<Llave,Departamento> nDepartamentos){
		anio = nAnio;
		colegios = nColegios;
		departamentos = nDepartamentos;
		
	}
	
	/**
	 * Retorna el valor del anio.
	 * @return el valor numerico del anio.
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * Retorna la lista de colegios del anio.
	 * @return HashTable de colegios.
	 */
	public TablaHashing<Llave, Colegio> getColegios() {
		return colegios;
	}
	
	/**
	 * Retorna la lista de departamentos del anio.
	 * @return HashTable de departamentos.
	 */
	public TablaHashing<Llave,Departamento> getDepartamentos() {
		return departamentos;
	}
	
	/**
	 * Metodo que retorna la cantidad de departamentos que superan los 50 colegios con nivel MUY SUPERIOR
	 * @return La cantidad de colegios que cumplen con el criterio.
	 */
	public int darCantidadDeptosSuperiores() {
		int r = 0;
		Iterator i = departamentos.iterator();
		while(i.hasNext()){
			Departamento d = (Departamento)i.next();
			if(d.darTotalMuySuperiores() > 50)
				r++;
		}
		return r;
	}
	
	/**
	 * Metodo que retorna el promedio de icfes de todos los departamentos.
	 * @return El promedio icfes de todos los departamentos.
	 */
	public double darPromedioIcfes() {
		double suma = 0;
		Iterator i = departamentos.iterator();
		while(i.hasNext()){
			Departamento d = (Departamento)i.next();
			suma += d.darPromedio();
		}
		return suma/departamentos.darLongitud();
	}
	/**
	 * Metodo que retorna el promedio de matematicas de los departamentos
	 * @return El promedio de matematicas de los departamentos.
	 */
	public double darPromedioMatematicas() {
		double suma = 0;
		Iterator i = departamentos.iterator();
		while(i.hasNext()){
			Departamento d = (Departamento)i.next();
			suma += d.darPromedioMatematicas();
		}
		return suma/departamentos.darLongitud();
	}

	/**
	 * Metodo que devuelve identificador string del anio.
	 */
	@Override
	public String toString(){
		return "" + anio;
	}

	/**
	 * Metodo de comparacion entre anios.
	 */
	@Override
	public int compareTo(Anio otro) {
		if(anio == otro.getAnio()){
			return 0;
		}
		else if(anio<otro.getAnio()){
			return -1;
		}
		else{
			return 1;
		}
	}
	
	

}
