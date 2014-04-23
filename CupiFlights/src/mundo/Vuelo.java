package mundo;

import java.io.Serializable;

public class Vuelo implements Serializable,Comparable<Vuelo> {
	public final static String SALIENDO = "Saliendo";
	public final static String LLEGANDO = "Llegando";
	/**
	 * 
	 */
	private static final long serialVersionUID = 7370494462035777075L;
	private String numero;
	private String codigoSalida;
	private String codigoLlegada;
	private String tipo;
	public Vuelo(String nNumero, String nCodigoSalida, String nCodigoLlegada, String nTipo) {
		numero = nNumero;
		codigoSalida = nCodigoSalida;
		codigoLlegada = nCodigoLlegada;
		tipo = nTipo;
	}
	public String getNumero() {
		return numero;
	}
	public String getCodigoSalida() {
		return codigoSalida;
	}
	public String getCodigoLlegada() {
		return codigoLlegada;
	}
	@Override
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
