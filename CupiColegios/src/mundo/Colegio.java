package mundo;

import java.io.Serializable;

import Lista.Lista;
import ListaEncadenada.ListaEncadenada;

public class Colegio implements Comparable<Colegio>, Serializable{
	
	private static final long serialVersionUID = 1921796038531766464L;
	
	private String jornada;
	
	private String nivel;
	
	private String genero;

	private String calendario;
	
	private String tipo;
	
	private String nombre;
	
	private String codigo;
	
	private Notas notas;
	
	private int codigoMunicipio;
	
	private int codigoDepartamento;
	
	private Certificado[] certificados;
	
	/**
	 * 
	 * @param nCodigo
	 * @param nNombre
	 * @param nCalendario
	 * @param nGenero
	 * @param nTipo
	 * @param nNivel 
	 */
	public Colegio(String nCodigo, String nNombre, String nCalendario, String nGenero, String nTipo, String nNivel, Notas nNotas, int nCM, int nCD){
		genero = nGenero;
		calendario = nCalendario;
		tipo = nTipo;
		nombre = nNombre;
		codigo = nCodigo;
		nivel = nNivel;
		notas = nNotas;
		codigoMunicipio = nCM;
		codigoDepartamento = nCD;
	}
	
	@Override
	public int compareTo(Colegio o) {
		// TODO Auto-generated method stub
		return 0;
	}

	private Lista<String> darListaAtributos(){
		Lista <String> atributos = new ListaEncadenada <String>();
		atributos.agregar(jornada);
		atributos.agregar(nivel);
		atributos.agregar(genero);
		atributos.agregar(calendario);
		atributos.agregar(tipo);
		return atributos;
	}
	public boolean cumpleCriterio(Criterio criterio) {
		Object[] subcriterios = criterio.darSubcriterios();
		Lista<String> lista = darListaAtributos();
		String cumple = null;
		for(int i = 0;i<subcriterios.length && cumple == null;i++){
			cumple = (String) lista.buscar((String)subcriterios[i]);
		}
		return cumple==null?false:true;
	}

	public String getJornada() {
		return jornada;
	}

	public String getNivel() {
		return nivel;
	}

	public String getGenero() {
		return genero;
	}

	public String getCalendario() {
		return calendario;
	}

	public String getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public Notas getNotas(){
		return notas;
	}
	
	public double darPromedio(){
		return notas.darPromedio();
	}
	
	public String toString(){
		return nombre + " - " + codigo + " - " + tipo + " - " + calendario;
	}

}
