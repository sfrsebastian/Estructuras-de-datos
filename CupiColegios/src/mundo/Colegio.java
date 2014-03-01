package mundo;

import java.io.Serializable;

import Lista.Lista;
import ListaEncadenada.ListaEncadenada;

public class Colegio implements Comparable<Colegio>, Serializable{
	
	private static final long serialVersionUID = 1921796038531766464L;
	
	private String jornada;
	
	private String icfes;
	
	private String genero;

	private String calendario;
	
	private String tipo;
	
	private String nombre;
	
	private String codigo;
	
	private Certificado[] certificados;
	
	/**
	 * 
	 * @param nCodigo
	 * @param nNombre
	 * @param nCalendario
	 * @param nGenero
	 * @param nTipo
	 */
	public Colegio(String nCodigo, String nNombre, String nCalendario, String nGenero, String nTipo){
		genero = nGenero;
		calendario = nCalendario;
		tipo = nTipo;
		nombre = nNombre;
		codigo = nCodigo;
	}
	
	@Override
	public int compareTo(Colegio o) {
		// TODO Auto-generated method stub
		return 0;
	}

	private Lista<String> darListaAtributos(){
		Lista <String> atributos = new ListaEncadenada <String>();
		atributos.agregar(jornada);
		atributos.agregar(icfes);
		atributos.agregar(genero);
		atributos.agregar(calendario);
		atributos.agregar(tipo);
		return atributos;
	}
	public boolean cumpleCriterio(Criterio criterio) {
		Object[] subcriterios = criterio.darSubcriterios();
		Lista lista = darListaAtributos();
		String cumple = null;
		for(int i = 0;i<subcriterios.length && cumple == null;i++){
			cumple = (String) lista.buscar((String)subcriterios[i]);
		}
		return cumple==null?false:true;
	}

	public String getJornada() {
		return jornada;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	public String getIcfes() {
		return icfes;
	}

	public void setIcfes(String icfes) {
		this.icfes = icfes;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCalendario() {
		return calendario;
	}

	public void setCalendario(String calendario) {
		this.calendario = calendario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
