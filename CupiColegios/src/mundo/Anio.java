package mundo;

import java.util.Iterator;

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
	
	public String toString(){
		return "" + anio;
	}
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
	public double darPromedioIcfes() {
		double suma = 0;
		Iterator i = departamentos.iterator();
		while(i.hasNext()){
			Departamento d = (Departamento)i.next();
			suma += d.darPromedio();
		}
		return suma/departamentos.darLongitud();
	}
	public double darPromedioMatematicas() {
		double suma = 0;
		Iterator i = departamentos.iterator();
		while(i.hasNext()){
			Departamento d = (Departamento)i.next();
			suma += d.darPromedioMatematicas();
		}
		return suma/departamentos.darLongitud();
	}
	
	

}
