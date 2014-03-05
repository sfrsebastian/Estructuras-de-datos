package mundo;

import java.io.Serializable;

import Lista.Lista;
import ListaEncadenada.ListaEncadenada;

public class Colegio implements Comparable<Colegio>, Serializable{
	
	//Atributos.
	private static final long serialVersionUID = 1921796038531766464L;
	
	private String jornada;
	
	private String nivel;
	
	private String genero;

	private String calendario;
	
	private String tipo;
	
	private String nombre;
	
	private String codigo;
	
	private Notas notas;
	
	private String municipio;
	
	private String departamento;
	
	private Certificado[] certificados;
	
	/**
	 * Metodo constructor de un colegio.
	 * 
	 * @param nCodigo
	 * @param nNombre
	 * @param nCalendario
	 * @param nGenero
	 * @param nTipo
	 * @param nNivel 
	 */
	public Colegio(String nCodigo, String nNombre, String nJornada, String nCalendario, String nGenero, String nTipo, String nNivel, Notas nNotas, String nCM, String nCD){
		genero = nGenero;
		calendario = nCalendario;
		tipo = nTipo;
		nombre = nNombre;
		codigo = nCodigo;
		nivel = nNivel;
		notas = nNotas;
		municipio = nCM;
		departamento = nCD;
		jornada = nJornada;
	}

	/**
	 * Metodo que compara dos colegios a partir de su nombre.
	 */
	@Override
	public int compareTo(Colegio otro) {
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
	 * Metodo que retorna una lista con los atributos del colegio.
	 * @return
	 */
	private Lista<String> darListaAtributos(){
		Lista <String> atributos = new ListaEncadenada <String>();
		atributos.agregar(jornada);
		atributos.agregar(nivel);
		atributos.agregar(genero);
		atributos.agregar(calendario);
		atributos.agregar(tipo);
		return atributos;
	}
	
	/**
	 * Metodo que determina si el colegio cumple el criterio dado por parametro.
	 */
	public boolean cumpleCriterio(Criterio criterio) {
		Object[] subcriterios = criterio.darSubcriterios();
		Lista<String> lista = darListaAtributos();
		String cumple = null;
		for(int i = 0;i<subcriterios.length && cumple == null;i++){
			cumple = lista.buscar((String)subcriterios[i]);
		}
		return cumple==null?false:true;
	}

	/**
	 * La jornada del colegio.
	 * @return
	 */
	public String getJornada() {
		return jornada;
	}

	/**
	 * El nivel del colegio
	 * @return
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * El genero del colegio
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * El calendario del colegio.
	 * @return
	 */
	public String getCalendario() {
		return calendario;
	}

	/**
	 * El tipo del colegio.
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * El nombre del colegio
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * El codigo del colegio
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Las notas del colegio
	 * @return
	 */
	public Notas getNotas(){
		return notas;
	}
	
	/**
	 * El promedio del colegios.
	 * @return
	 */
	public double darPromedio(){
		return notas.darPromedio();
	}
	
	/**
	 * Metodo to string del colegio, incluye su nombre, codigo, tipo y calendario.
	 */
	@Override
	public String toString(){
		return nombre + " - " + codigo + " - " + tipo + " - " + calendario;
	}
	
	/**
	 * El nombre del departamento al que pertence el colegio.
	 * @return
	 */
	public String getNombreDepartamento() {
		return departamento;
	}

	/**
	 * Los certificados que posee el colegio.
	 * @return
	 */
	public Certificado[] getCertificados() {
		return certificados;
	}

	/**
	 * El municipio al que pertence el colegio.
	 * @return
	 */
	public String getMunicipio() {
		return municipio;
	}

}
