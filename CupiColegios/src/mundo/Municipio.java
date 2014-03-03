package mundo;

import java.io.Serializable;

import HashTable.TablaHashing;

public class Municipio implements Comparable <Municipio>,Serializable {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	private String nombre;
	
	private int codigo;
	
	private TablaHashing<Llave, Colegio> colegios;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public Municipio(String nNombre, int nCodigo){
		nombre = nNombre;
		codigo = nCodigo;
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

	public void agregarColegio(Llave llave, Colegio colegio){
		colegios.agregar(llave, colegio);
	}
	
	@Override
	public int compareTo(Municipio o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString(){
		return nombre;
	}
	
}
