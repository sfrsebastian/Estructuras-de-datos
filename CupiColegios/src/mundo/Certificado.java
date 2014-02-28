package mundo;

import java.util.Date;

public class Certificado {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * 
	 */
	private String tipo;
	
	/**
	 * 
	 */
	private Date fechaOtorgamiento;
	
	/**
	 * 
	 */
	private Date fechaValidez;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public Certificado(String nTipo, Date nFechaO, Date nFechaV){
		tipo = nTipo;
		fechaOtorgamiento = nFechaO;
		fechaValidez = nFechaV;
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFechaOtorgamiento() {
		return fechaOtorgamiento;
	}

	public void setFechaOtorgamiento(Date fechaOtorgamiento) {
		this.fechaOtorgamiento = fechaOtorgamiento;
	}

	public Date getFechaValidez() {
		return fechaValidez;
	}

	public void setFechaValidez(Date fechaValidez) {
		this.fechaValidez = fechaValidez;
	}
	
	
	
}
