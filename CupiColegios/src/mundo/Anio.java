package mundo;

import HashTable.TablaHashing;

public class Anio implements Comparable<Anio> {

	private int anio;
	private TablaHashing<Llave,Colegio> colegios;
	private TablaHashing<Llave,Departamento> departamentos;
	
	public Anio(int nAnio,TablaHashing<Llave,Colegio> nColegios, TablaHashing<Llave,Departamento> nDepartamentos){
		anio = nAnio;
		colegios = nColegios;
		departamentos = nDepartamentos;
		
	}
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
	public int getAnio() {
		return anio;
	}
	public void setAnio(int nAnio) {
		anio = nAnio;
	}
	public TablaHashing<Llave, Colegio> getColegios() {
		return colegios;
	}
	public TablaHashing<Llave,Departamento> getDepartamentos() {
		return departamentos;
	}

}
