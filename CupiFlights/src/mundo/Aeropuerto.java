package mundo;

import java.io.Serializable;

public class Aeropuerto implements Comparable<Aeropuerto>,Serializable{

	public static final String NOTIENE = "No tiene";
	private String codigoCiudad;
	private String codigoPais;
	private String nombre;
	private String ciudad;
	private String pais;
	private double tardanza;
	
	/**
	 * Codigo flightstats
	 */
	private String codigo;
	public Aeropuerto(String nNombre, String nCiudad,String nPais, String nCodigoCiudad,String nCodigoPais,String nCodigo, double nTardanza){
		nombre = nNombre;
		ciudad = nCiudad;
		pais = nPais;
		codigoCiudad = nCodigoCiudad;
		codigoPais = nCodigoPais;
		codigo = nCodigo;
		tardanza = nTardanza;
	}
	
	
	public Aeropuerto(String nCodigo) {
		codigo = nCodigo;
	}


	@Override
	public int compareTo(Aeropuerto o) {
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
