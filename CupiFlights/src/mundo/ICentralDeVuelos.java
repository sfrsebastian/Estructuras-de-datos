package mundo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;

public interface ICentralDeVuelos extends Serializable{
	
	
	public Aeropuerto agregarAeropuerto(String codigo);
	
	public Aeropuerto eliminarAeropuerto(String codigo);
	
	//Para aeropuerto
	public int consultarCalificacion(String codigo);
	
	public boolean ActualizarCalificacion(int valor);
	
	public Iterator<Fecha> consultarFechas();
	
	public Iterator<Aeropuerto> consultarAeropuertos();
	
	public Iterator<Vuelo> consultarVuelos(Calendar cal, int horaDia, String codigo, String tipo);
	
	//Para aeropuertos
	public Iterator<Vuelo> consultarVuelosPorEstado(String constante,String codigo);
	
	public Iterator<Vuelo> buscarVuelosPorCalificacion(int calificacion);
	
	public Aerolinea darMayorRetraso();
	
	public Aerolinea darMenorRetraso();
	
	public Iterator<Aeropuerto> buscarPorIndice();
	
	
}
