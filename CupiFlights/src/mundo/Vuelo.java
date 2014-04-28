package mundo;

import java.io.Serializable;
import java.util.Calendar;

public class Vuelo implements Serializable,Comparable<Vuelo> {
	public final static String SALIENDO = "Saliendo";
	public final static String LLEGANDO = "Llegando";
	/**
	 * 
	 */
	private static final long serialVersionUID = 7370494462035777075L;
	private String numero;
	private String tipo;
	private double rating;
	private Aeropuerto salida;
	private Aeropuerto llegada;
	private Aerolinea aerolinea;
	private int l15;
	private int l30;
	private int l45;
	private int cancelados;
	private Calendar fecha;
	
	public Vuelo(String nNumero, Aeropuerto nSalida, Aeropuerto nLlegada, Aerolinea nAereolinea, String nTipo, Object[] nRating, Calendar c) {
		fecha = c;
		numero = nNumero;
		salida = nSalida;
		llegada = nLlegada;
		tipo = nTipo;
		aerolinea = nAereolinea;
		rating = (double) nRating[0];
		l15 = (int) nRating[1];
		l30 = (int) nRating[2];
		l45 = (int) nRating[3];
		cancelados = (int) nRating[4];
		
	}
	
	public Vuelo(String nNumero){
		numero = nNumero;
	}
	public Aeropuerto getSalida() {
		return salida;
	}
	public Aeropuerto getLlegada() {
		return llegada;
	}
	public Aerolinea getAereolinea() {
		return aerolinea;
	}
	public String getNumero() {
		return numero;
	}
	public String getTipo() {
		return tipo;
	}
	public double getRating(){
		return rating;
	}
	public int getL15() {
		return l15;
	}
	public int getL30() {
		return l30;
	}
	public int getL45() {
		return l45;
	}
	public int getCancelados() {
		return cancelados;
	}
	
	public String toString(){
		return numero + " " + aerolinea.toString();
	}
	public int compareTo(Vuelo o) {
		int comparacion = numero.compareTo(o.numero);
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
