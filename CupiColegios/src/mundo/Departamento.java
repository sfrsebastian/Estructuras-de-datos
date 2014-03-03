package mundo;

import java.io.Serializable;

import HashTable.TablaHashing;

public class Departamento implements Comparable<Departamento>, Serializable {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	private String nombre;
	
	private int codigo;
	
	private TablaHashing<Llave, Colegio> colegios;
	private TablaHashing<Llave, Municipio> municipios;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public Departamento(String nNombre, int nCod){
		nombre = nNombre;
		codigo = nCod;
		colegios = new TablaHashing<Llave, Colegio>(7,2);
		municipios = new TablaHashing<Llave,Municipio>(7,2);
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	public String getNombre() {
		return nombre;
	}

	public int getCodigo() {
		return codigo;
	}

	public TablaHashing<Llave, Colegio> getColegios() {
		return colegios;
	}
	
	public TablaHashing<Llave, Municipio> getMunicipios() {
		return municipios;
	}

	public void agregarColegio(Llave llave,Colegio colegio){
		colegios.agregar(llave, colegio);
	}
	
	public void agregarMunicipio(Llave llave,Municipio municipio){
		if(municipios.buscar(llave) == null){
			municipios.agregar(llave, municipio);
		}
	}
	
	public Municipio buscarMunicipio(Llave llave){
		return municipios.buscar(llave);
	}
	@Override
	public int compareTo(Departamento o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString(){
		return nombre;
	}

}
