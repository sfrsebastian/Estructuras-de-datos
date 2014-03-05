package mundo;

import java.util.Date;

public class Certificado {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * El tipo del certificado
	 */
	private String tipo;
	
	/**
	 * La fecha de otorgamiento del certificado
	 */
	private Date fechaOtorgamiento;
	
	/**
	 * La fechad de validez del certificado
	 */
	private Date fechaValidez;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	/**
	 * Crea un nuevo certificado a parti de los parametros dados.
	 * @param nTipo
	 * @param nFechaO
	 * @param nFechaV
	 */
	public Certificado(String nTipo, Date nFechaO, Date nFechaV){
		tipo = nTipo;
		fechaOtorgamiento = nFechaO;
		fechaValidez = nFechaV;
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------

	/**
	 * Retorna le tipo del certificado
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Retorna la fecha de otorgamiento del certificado
	 * @return
	 */
	public Date getFechaOtorgamiento() {
		return fechaOtorgamiento;
	}

	/**
	 * Retorna la fecha de validez del certificado
	 * @return
	 */
	public Date getFechaValidez() {
		return fechaValidez;
	}
}
