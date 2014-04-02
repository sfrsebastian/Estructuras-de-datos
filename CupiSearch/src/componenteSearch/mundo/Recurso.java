package componenteSearch.mundo;

import java.io.Serializable;

public class Recurso implements Comparable<Recurso>, Serializable{

	//-----------------------------------------------------------------
	// Constantes
	//-----------------------------------------------------------------
	/**
	 * Constante que indica si el recurso es imagen
	 */
	public final static String IMAGEN = "Imagen";
	
	/**
	 * Constante que indica si el recurso es de texto
	 */
	public final static String TEXTO = "Texto";
	
	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------
	
	/**
	 * La url donde se encuentra el recurso
	 */
	private String url;
	
	/**
	 * La descripcion del recurso
	 */
	private String descripcion;
	
	/**
	 * El tipo del recurso
	 */
	private String tipo;
	
	/**
	 * El url de el recursos en caso de ser imagen
	 */
	private String imgUrl;
	
	/**
	 * El nivel donde se encontro el recruso
	 */
	private int nivel;

	/**
	 * Identificador unico del recurso
	 */
	private String id;
	
	//-----------------------------------------------------------------
	// Constructores
	//-----------------------------------------------------------------

	/**
	 * Crea un nuevo recurso de TEXTO a partir del nivel, descripcion y url dado
	 * @param nNivel el nivel donde se encuentra
	 * @param nDescripcion La descripcion del recurso
	 * @param nurl El url donde se encuentra el recurso
	 */
	public Recurso(int nNivel, String nDescripcion, String nurl) {
		tipo = TEXTO;
		descripcion = nDescripcion;
		url = nurl;
		nivel = nNivel;
		id = descripcion;
	}
	
	/**
	 * Crea un nuevo recurso de IMAGEN a partir del nivel, descripcion y url dado
	 * @param nNivel el nivel donde se encuentra
	 * @param nDescripcion La descripcion del recurso
	 * @param nurl El url donde se encuentra el recurso
	 * @param nimgUrl El url de la imagen
	 */
	public Recurso(int nNivel, String nDescripcion, String nUrl, String nimgUrl){
		tipo = IMAGEN;
		descripcion = nDescripcion;
		url = nUrl;
		imgUrl = nimgUrl;
		nivel = nNivel;
		id = url;
	}
	
	/**
	 * Crea un recurso a partir de su ruta o texto enviados
	 * @param ruta
	 */
	public Recurso(String ruta){
		tipo = ruta.contains("http://")?IMAGEN:TEXTO;
		if(tipo.equals(IMAGEN)){
			imgUrl = ruta;
			descripcion = "";
		}
		else{
			descripcion = ruta;
			imgUrl="";
		}
		id = ruta;
		nivel = 0;
		url = "Recurso obtenido de servidor se perdio url en persistencia de datos";
	}
	
	//-----------------------------------------------------------------
	// Metodos
	//-----------------------------------------------------------------
	/**
	 * Retorna el Url donde se encontro el recruso
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Retorna la descripcion del recruso
	 * @return la descripcion del recurso
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Retorna el tipo del recurso
	 * @return el tipo del recurso
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Retorna el url de la imagen
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * Retorna el nivel donde se encuentra el recruso
	 * @return
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Indica si la descripcion del recurso o si su tipo contienen el criterio dado por parametro
	 * @param busqueda
	 * @return TRUE si contiene el criterio, FALSE de lo contrario
	 */
	public boolean contiene(String busqueda) {
		return descripcion.contains(busqueda) || tipo.compareTo(busqueda)==0;
	}

	/**
	 * Indica si la descripcion o el tipo son iguales al criterio dado
	 * @param criterio
	 * @return TRUE si son iguales, FALSE de lo contrario
	 */
	public boolean igual(String criterio) {
		return descripcion.compareTo(criterio)==0 || tipo.equals(criterio);
	}

	/**
	 * Metodo que compara dos recursos a partir de su id
	 */
	public int compareTo(Recurso o) {
		return id.compareTo(o.id);
	}

	/**
	 * Metodo to String del recurso
	 */
	public String toString() {
		return tipo + " : " + descripcion;
	}
} 
