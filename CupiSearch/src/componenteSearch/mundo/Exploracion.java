package componenteSearch.mundo;

import java.sql.Time;

import ArbolBinOrdenado.IArbolBinarioOrdenado;

public class Exploracion implements Comparable<Exploracion> {

	private int recursosExplorados;

	private Time tiempoTotal;

	private int busquedasRealizadas;

	private IArbolBinarioOrdenado<Recurso> recursos;
	
	public Exploracion() {

	}

	@Override
	public int compareTo(Exploracion o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public IArbolBinarioOrdenado<Recurso> getRecursos() {
		return recursos;
	}

	public String darEstadistica() {
		// TODO Auto-generated method stub
		return null;
	}

}
