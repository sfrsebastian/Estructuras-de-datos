package componenteSearch.mundo;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ArbolAVl.ArbolBinarioAVLOrdenado;
import ArbolBinOrdenado.IArbolBinarioOrdenado;

public class Exploracion implements Comparable<Exploracion>, Serializable {

	private int recursosExplorados;

	private long tiempoTotal;

	private int busquedasRealizadas;

	private IArbolBinarioOrdenado<Recurso> recursos;
	
	private ArbolBinarioAVLOrdenado<String> fuentes;
	
	private Date fecha;
	
	private static final long serialVersionUID = 7403297819295143360L;
	
	public Exploracion(ArbolBinarioAVLOrdenado<Recurso> arbolRecursos, long tiempo) {
		recursos = arbolRecursos;
		tiempoTotal = tiempo;
		recursosExplorados = arbolRecursos.darPeso();
		busquedasRealizadas = 0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		fecha = new Date();
		dateFormat.format(fecha);
		fuentes = new ArbolBinarioAVLOrdenado<String>();
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
	
	public void agregarFuente(String url){
		fuentes.agregar(url);
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
		return null;
	}

	public String toString(){
		return fecha + "";
	}

	public long getTiempoTotal() {
		return tiempoTotal/1000;
	}

	public int getBusquedasRealizadas() {
		return busquedasRealizadas;
	}

	public void setBusquedasRealizadas(int busquedasRealizadas) {
		this.busquedasRealizadas = busquedasRealizadas;
	}

	public void setTiempoTotal(long tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	
	public ArbolBinarioAVLOrdenado<String> getFuentes(){
		return fuentes;
	}
}

