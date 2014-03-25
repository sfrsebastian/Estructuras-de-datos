package componenteSearch.mundo;

import ArbolAVl.ArbolBinarioAVLOrdenado;
import ArbolBinOrdenado.IArbolBinarioOrdenado;

public class Exploracion implements Comparable<Exploracion> {

	private int recursosExplorados;

	private long tiempoTotal;

	private int busquedasRealizadas;

	private IArbolBinarioOrdenado<Recurso> recursos;
	
	public Exploracion(ArbolBinarioAVLOrdenado<Recurso> arbolRecursos, long tiempo) {
		recursos = arbolRecursos;
		tiempoTotal = tiempo;
		recursosExplorados = arbolRecursos.darPeso();
	}

	@Override
	public int compareTo(Exploracion o) {
		if(darCantidadRecursos() > o.darCantidadRecursos())
			return 1;
		else if (darCantidadRecursos() < o.darCantidadRecursos())
			return -1;
		else
			return 0;
	}
	
	public int darCantidadRecursos(){
		return recursos.darPeso();
	}
	
	public void aumentarBusqueda(){
		busquedasRealizadas++;
	}

	public IArbolBinarioOrdenado<Recurso> getRecursos() {
		return recursos;
	}

	public String darEstadistica() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString(){
		return null;
	}
}
