package mundo;

import HashTable.TablaHashing;

public class Departamento {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	private String nombre;
	
	private int codigo;
	
	private TablaHashing<Llave, Colegio> colegios;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public Departamento(String nNombre, int nCod){
		nombre = nNombre;
		codigo = nCod;
		colegios = new TablaHashing<Llave, Colegio>(1000,2);
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public TablaHashing<Llave, Colegio> getColegios() {
		return colegios;
	}

	public void setColegios(TablaHashing<Llave, Colegio> colegios) {
		this.colegios = colegios;
	}

}