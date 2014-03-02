package mundo;

import java.io.File;

import HashTable.TablaHashing;

public class Anio implements Comparable<Anio> {

	private int anio;
	private TablaHashing<Llave,Colegio> colegios;
	private TablaHashing<Llave,Departamento> departamentos;
	
	public Anio(File anio){
		
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
	public Object[] getColegios() {
		return colegios.darArreglo();
	}
	public Object[] getDepartamentos() {
		return departamentos.darArreglo();
	}

}
