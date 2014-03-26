package componenteSearch.mundo;

import java.net.MalformedURLException;
import java.net.URL;

public class Recurso implements Comparable<Recurso>{

	public final static String IMAGEN = "Imagen";
	
	public final static String TEXTO = "Texto";
	
	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------
	
	/**
	 * La url del recurso imagen
	 */
	private String url;
	
	private String descripcion;
	
	private String tipo;
	
	private String imgUrl;
	
	private int nivel;
	
	//-----------------------------------------------------------------
	// Constructores
	//-----------------------------------------------------------------

	public Recurso(int nNivel, String nDescripcion, String nurl) {
		tipo = TEXTO;
		descripcion = nDescripcion;
		url = nurl;
		nivel = nNivel;
		//System.out.println(descripcion);
	}
	
	public Recurso(int nNivel, String nDescripcion, String nUrl, String nimgUrl) throws MalformedURLException{
		tipo = IMAGEN;
		descripcion = nDescripcion;
		url = nUrl;
		imgUrl = nimgUrl;
		nivel = nNivel;
	}
	
	//-----------------------------------------------------------------
	// Metodos
	//-----------------------------------------------------------------

	public String getUrl() {
		return url;
	}

	public boolean contiene(String busqueda) {
		return descripcion.contains(busqueda) || tipo.compareTo(busqueda)==0;
	}

	@Override
	public int compareTo(Recurso o) {
		return descripcion.compareTo(o.descripcion);
	}
	
	@Override
	public String toString() {
		return tipo + " : " + descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public int getNivel() {
		return nivel;
	}

	public boolean igual(String criterio) {
		return descripcion.compareTo(criterio)==0 || tipo.equals(criterio);
	}
} 
