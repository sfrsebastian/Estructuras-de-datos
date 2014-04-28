package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import Arbol23.Arbol23;
import ArbolTrie.ArbolTrie;
import ListaOrdenada.ListaOrdenada;

public class CentralDeVuelos implements ICentralDeVuelos{
	//--------------------
	//CONSTANTES
	//--------------------
	/**
	 * Listado de codigos (ICAO) aeropuertos mas visitados.
	 */
	private static final String[] CODIGOS = {"KATL","ZBAA","EGLL","RJTT","KORD","KLAX","OMDB","LFPG","KDFW","WIII"
														,"VHHH","EDDF","WSSS","EHAM","KDEN","ZGGG","VTBS","LTBA","KJFK","WMKK"
														,"ZSPD","KSFO","KCLT","KLAS","RKSI","KMIA","KPHX","KIAH","LEMD","EDDM"
														,"YSSY","VIDP","SBGR","LIRF","CYYZ","ZSSS","EGKK","RJAA","LEBL","KEWR"
														,"KMCO","KSEA","KMSP","ZUUU","RPLL","KDTW","ZGSZ","VABB","MMMX","UUDD"};
	
	/**
	 * Nombre del archivo para serializar la central
	 */
	public final static String RUTA_ARCHIVO_SERIALIZADO = "/CentralDeVuelos.dat/";
	
	/**
	 * Identificador unico para hacer uso del api de flightstats
	 */
	public static final String IDENTIFICADORES = "appId=3723b96f&appKey=d6e053700776ffb5b91cd46f8c88722b&";
	
	/**
	 * Identificador de la clase
	 */
	private static final long serialVersionUID = -2434025803582670357L;
	
	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * Los aeropuertos de la central
	 */
	private Arbol23<Aeropuerto> aeropuertos;
	
	/**
	 * Arbol con clasificacion de vuelos por fecha.
	 */
	private ArbolTrie<Vuelo> fechas;
	
	/**
	 * Los vuelos de la central.
	 */
	private Arbol23<Vuelo> vuelos;
	
	/**
	 * Las aerolineas de la central
	 */
	private Arbol23<Aerolinea> aerolineas;
	
	private SimpleDateFormat dateFormat;
	/**
	 * La instancia de la clase
	 */
	private static CentralDeVuelos instancia = null;
	
	//--------------------
	//Constructor
	//--------------------
	/**
	 * Construye una nueva central de vuelos
	 * @pos Se inicializan los atrbutos de la clase
	 */
	private CentralDeVuelos(){
		aeropuertos = new Arbol23<Aeropuerto>();
		fechas = new ArbolTrie<Vuelo>();
		vuelos = new Arbol23<Vuelo>();
		aerolineas = new Arbol23<Aerolinea>();
		dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	}

	/**
	 * Retorna la instancia actual de la central
	 * @return La instancia actual, de lo contrario una instancia nueva.
	 */
	public static CentralDeVuelos getInstance(){
		System.out.println("getInstance");
		if( instancia == null )
		{
			System.out.println("Creando instancia");
			try
			{
				File dataDir = darRuta();
				File tmp = new File(dataDir + RUTA_ARCHIVO_SERIALIZADO);
				System.out.println("Nombre=" + tmp.getName( ));
				System.out.println("Path=" + tmp.getPath( ));
				System.out.println("Abs. Path=" + tmp.getAbsolutePath( ));
				if ( tmp.exists( ) )
				{
					FileInputStream fis = new FileInputStream( tmp );
					ObjectInputStream ois = new ObjectInputStream( fis );
					instancia = (CentralDeVuelos) ois.readObject( );
					ois.close();
					fis.close();
					System.out.println("Central de vuelos deserializada");
				}
				else
				{
					instancia = new CentralDeVuelos( );                    
					System.out.println("Central Nueva");
				}
			}
			catch( Exception e )
			{
				instancia = new CentralDeVuelos( );
				System.out.println("Central Nueva");
			}
		}
		return instancia;
	}
	
	//--------------------
	//METODOS AUXILIARES
	//--------------------
	/**
	 * Retorna la ruta de guardado segun el caso<b>
	 * Puede ser la ruta de datos del servidor o la ruta de data local en su defecto.
	 * @return
	 */
	private static File darRuta(){
		try{
			return new File(System.getProperty("jboss.server.data.dir"));
		}
		catch(Exception e){
			return new File("./data");
		}
	}
	/**
	 * Arma el objeto JSON del url dado.
	 * Retorna el objeto JSON obtenido.
	 * @param url El url de la solicitud.
	 * @return El objeto JSON obtenido.
	 * @throws IOException
	 */
	private JSONObject darJSON(String url) throws IOException {
		String json = "";
		URL consulta = new URL(url);
		URLConnection conexion = consulta.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream())); 
		StringBuilder sBuilder = new StringBuilder();
		String line = null;
		while ((line = in.readLine()) != null) {
			sBuilder.append(line + "\n");
		}
		in.close();
		json = sBuilder.toString();
		JSONObject principal = new JSONObject(json);
		return principal;
	}

	/**
	 * Crea un nuevo vuelo a partir de el objeto JSON recibido y el tipo de vuelo<b>
	 * Agrega a la aerolinea el vuelo creado
	 * @param objeto El objeto JSON para la lectura de atributos
	 * @param tipo El tipo de vuelo Vuelo.LLEGANDO || Vuelo.SALIENDO
	 * @return El vuelo creado.
	 * @throws Exception
	 */
	private Vuelo leerVuelo(Calendar c,JSONObject objeto, String tipo) throws Exception{
		String numero =  objeto.getString("flightNumber");
		String codigoSalida = objeto.getString("departureAirportFsCode");
		Aeropuerto salida = manejarAeropuerto(codigoSalida);
		String codigoLlegada = objeto.getString("arrivalAirportFsCode");
		Aeropuerto llegada = manejarAeropuerto(codigoLlegada);
		String codigoAereolinea = objeto.getString("carrierFsCode");
		Aerolinea aerolinea = manejarAereolinea(codigoAereolinea);
		Object[] rating = darRating(codigoAereolinea,numero,codigoSalida);
		Vuelo nuevo = new Vuelo(numero,salida,llegada,aerolinea,tipo,rating, c);
		aerolinea.agregarVuelo(nuevo);
		return nuevo;
	}

	/**
	 * Crea un nuevo aeropuerto a partir de el objeto JSON recibido<b>
	 * @param objeto El objeto JSON para la lectura de atributos
	 * @return El aeropuerto creado.
	 * @throws Exception
	 */
	private Aeropuerto leerAeropuerto(JSONObject objeto) throws Exception {
		String nombre =  objeto.getString("name");
		String ciudad = objeto.getString("city");
		String pais = objeto.getString("countryName");
		String latitud = objeto.get("latitude").toString();
		String longitud = objeto.get("longitude").toString();
		String codigoCiudad = Aeropuerto.NOTIENE;
		try{
			codigoCiudad = objeto.getString("cityCode");
		}
		catch(Exception e){
		}
		
		String codigoPais = objeto.getString("countryCode");
		String codigo = objeto.getString("fs");
		String urlTardanza = objeto.getString("delayIndexUrl");
		String[] split = urlTardanza.split("\\?");
		String url = split[0] + "?" + IDENTIFICADORES + split[1];
		JSONObject cargada = darJSON(url);
		JSONArray delay = cargada.getJSONArray("delayIndexes");
		JSONObject solicitar = delay.getJSONObject(0);
		double tardanza = solicitar.getDouble("normalizedScore");
		Aeropuerto nuevo = new Aeropuerto(nombre,ciudad,pais,codigoCiudad,codigoPais,codigo,tardanza,latitud,longitud);
		return nuevo;
	}

	/**
	 * Carga los vuelos entre los aeropeurtos dados por parametro y la fecha dada por parametro
	 * @param a1 El aeropuerto de salida
	 * @param Llegada El aeropuerto de llegada
	 * @param tipo El tipo de vuelo. Vuelo.LLEGADA || Vuelo.SALIDA
	 * @throws Exception
	 */
	private void cargarVuelosPorFecha(Calendar c, Aeropuerto a1, Aeropuerto a2, String tipo) throws Exception{
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int mes = c.get(Calendar.MONTH)+1;
		int anio = c.get(Calendar.YEAR);
		System.out.println(dateFormat.format(c.getTime()));
		String url = "";
		if(tipo.equals(Vuelo.LLEGANDO)){
			url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/route/status/" + a2.getCodigo() + "/" + a1.getCodigo() +"/arr/" + anio + "/" + mes + "/"+ dia + "?" + IDENTIFICADORES + "&utc=false&maxFlights=1";
		}
		else{
			url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/route/status/" + a1.getCodigo() + "/" + a2.getCodigo() +"/dep/" + anio + "/" + mes + "/"+ dia + "?" + IDENTIFICADORES + "&utc=false&maxFlights=1";
		}
		JSONObject principal = darJSON(url);
		JSONArray recibidos= (JSONArray) principal.getJSONArray("flightStatuses");
		for(int i = 0; i<recibidos.length();i++){
			JSONObject actual = recibidos.getJSONObject(i);
			Vuelo nuevo = leerVuelo(c,actual,tipo);
			if(tipo.equals(Vuelo.LLEGANDO)){
				if(a1.getVuelosEntrada().buscar(nuevo)==null){
					a1.agregarVueloEntrada(nuevo);
				}
				if(a2.getVuelosSalida().buscar(nuevo) == null){
					a2.agregarVueloSalida(nuevo);
				}
			}
			else if(tipo.equals(Vuelo.SALIENDO)){
				if(a1.getVuelosSalida().buscar(nuevo)==null){
					a1.agregarVueloSalida(nuevo);
				}
				if(a2.getVuelosEntrada().buscar(nuevo) == null){
					a2.agregarVueloEntrada(nuevo);
				}
			}
			if(vuelos.buscar(nuevo)==null){
				vuelos.agregar(nuevo);
				fechas.agregar(dateFormat.format(c.getTime()), nuevo);
				if(tipo.equals(Vuelo.SALIENDO)){
					System.out.println("Agregado vuelo # " +  nuevo.getNumero() + " fecha:" + dateFormat.format(c.getTime()) +  " - " + a1.getNombre() + " - "+ a2.getNombre() + "\n-------------------");
				}
				else{
					System.out.println("Agregado vuelo # " +  nuevo.getNumero() + " fecha:" + dateFormat.format(c.getTime()) +  " - " + a2.getNombre() + " - "+ a1.getNombre() + "\n-------------------");
				}	
			}	
		}
	}

	/**
	 * Retorna la aereolinea del codigo dado por parametro<b>
	 * Busca la aereolinea en el arbol, la crea de lo contrario
	 * @param codigo El codigo de la aereolinea
	 * @return La aereolinea del codigo dado por parametro
	 * @throws Exception
	 */
	private Aerolinea manejarAereolinea(String codigo) throws Exception {
		Aerolinea buscado = new Aerolinea(codigo);
		buscado = aerolineas.buscar(buscado);
		if(buscado == null){
			String nombreAereolinea = darNombreAerolinea(codigo);
			Aerolinea nueva = new Aerolinea(codigo,nombreAereolinea);
			aerolineas.agregar(nueva);
			return nueva;
		}
		else
			return buscado;	
	}

	/**
	 * Retorna el aeropuerto del codigo dado por parametro<b>
	 * Busca el aeropuerto en el arbol, la crea de lo contrario
	 * @param codigo El codigo del aeropuerto
	 * @return El aeropuerto del codigo dado por parametro
	 * @throws Exception
	 */
	private Aeropuerto manejarAeropuerto(String codigo) throws Exception{
		Aeropuerto buscado = new Aeropuerto(codigo);
		buscado = aeropuertos.buscar(buscado);
		if(buscado == null){
			String url = "https://api.flightstats.com/flex/airports/rest/v1/json/fs/" + codigo + "?" + IDENTIFICADORES;
			JSONObject aeropuerto = darJSON(url);
			JSONObject datos = aeropuerto.getJSONObject("airport");
			System.out.println("AEROPUERTO CREADO!!!!!!!!!!!!!!");
			return leerAeropuerto(datos);
		}
		else
			return buscado;	
	}
	
	/**
	 * Retorna un arreglo con la informacion de ratings a partir de la informacion recibida
	 * @param codigoAereolinea El codigo de la aereolinea
	 * @param numero El numero de vuelo
	 * @param codigoSalida El codigo del aeropuerto de salida.
	 * @return El arreglo de informacion de ratings. La primer entrada es el rating en estrellas, y las siguientes los valores de vuelos con tardanza 15,30,45 respectivamente; finalmente los vuelos cancelados.
	 * @throws Exception
	 */
	private Object[] darRating(String codigoAereolinea, String numero, String codigoSalida) throws Exception {
		Object[] valores = new Object[5];
		String url = "https://api.flightstats.com/flex/ratings/rest/v1/json/flight/"+codigoAereolinea + "/" + numero + "?" + IDENTIFICADORES+"&departureAirport=" + codigoSalida;
		JSONObject actual = darJSON(url);
		double rating = -1;
		int num15 = 0;
		int num30 = 0;
		int num45 = 0;
		int cancelados = 0;
		try{
			JSONArray array = actual.getJSONArray("ratings");
			JSONObject datos =  (JSONObject) array.get(0);
			rating = datos.getDouble("allStars");
			num15 = datos.getInt("late15");
			num30 = datos.getInt("late30");
			num45 = datos.getInt("late45");
			cancelados = datos.getInt("cancelled");
		}
		catch(Exception e){
			
		}
		valores[0] = rating;
		valores[1] = num15;
		valores[2] = num30;
		valores[3] = num45;
		valores[4] = cancelados;
		return valores;
	}

	/**
	 * Retorna el nombre de la aerolinea con codigo dado por parametro
	 * @param codigoAereolinea el codigo de la aereolinea a consultar
	 * @return El nombre de la aereolinea
	 * @throws Exception
	 */
	private String darNombreAerolinea(String codigoAereolinea) throws Exception{
		String url = "https://api.flightstats.com/flex/airlines/rest/v1/json/fs/" +codigoAereolinea+"?" + IDENTIFICADORES;
		JSONObject actual = darJSON(url);
		JSONObject datos = actual.getJSONObject("airline");
		String nombre = datos.getString("name");
		System.out.println("aereolinea leida: " + nombre);
		return nombre; 
	}
	
	/**
	 * Elimina los vuelos del aeropuerto dado por parametro
	 * @param eliminar
	 * @throws Exception
	 */
	private void eliminarVuelos(Aeropuerto eliminar) throws Exception {
		Iterator<Vuelo> salidas = eliminar.darVuelosSalida();
		Iterator<Vuelo> entradas = eliminar.darVuelosEntrada();
		while(salidas.hasNext()){
			Vuelo actual = salidas.next();
			vuelos.eliminar(actual);
			actual.getAereolinea().eliminarVuelo(actual);
		}
		while(entradas.hasNext()){
			Vuelo actual = entradas.next();
			vuelos.eliminar(actual);
			actual.getAereolinea().eliminarVuelo(actual);
		}
	}

	private void consultarVuelo(Aeropuerto vuelo) throws Exception {
		Iterator<Aeropuerto> it2 = aeropuertos.iterator();
		while(it2.hasNext()){
			Aeropuerto a2 = it2.next();
			if(!vuelo.equals(a2)){
				//Fecha actual
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(System.currentTimeMillis());
				//System.out.println(dateFormat.format(c.getTime()));
				cargarVuelosPorFecha(c, vuelo, a2, Vuelo.LLEGANDO);
				cargarVuelosPorFecha(c, vuelo, a2, Vuelo.SALIENDO);

				//Hace 4 dias
				if(c.get(Calendar.DAY_OF_MONTH)<=4){
					c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH)-1, 30-(c.get(Calendar.DAY_OF_MONTH)-4));
				}
				else{
					c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), (c.get(Calendar.DAY_OF_MONTH)-4));
				}
				//System.out.println(dateFormat.format(c.getTime()));
				cargarVuelosPorFecha(c, vuelo, a2, Vuelo.LLEGANDO);
				cargarVuelosPorFecha(c, vuelo, a2, Vuelo.SALIENDO);

				//Cuatro dias adelante
				c.setTimeInMillis(System.currentTimeMillis());
				if(c.get(Calendar.DAY_OF_MONTH)>=27){
					c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, 1+(c.get(Calendar.DAY_OF_MONTH)-27));
				}
				else{
					c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), (c.get(Calendar.DAY_OF_MONTH)+4));
				}
				//System.out.println(dateFormat.format(c.getTime()));
				cargarVuelosPorFecha(c, vuelo, a2, Vuelo.LLEGANDO);
				cargarVuelosPorFecha(c, vuelo, a2, Vuelo.SALIENDO);
			}
		}
	}

	//--------------------
	//METODOS
	//--------------------
	/**
	 * Carga 10 aeropuerto al azar de la lista general de aeropuertos
	 * @throws Exception
	 */
	public void cargarAeropuertos() throws Exception{
		String json = "";
		File f = new File(darRuta() + "/aeropuertos.json");
		BufferedReader in = new BufferedReader(new FileReader(f));
		StringBuilder sBuilder = new StringBuilder();
		String line = null;
		while ((line = in.readLine()) != null) {
			sBuilder.append(line + "\n");
		}
		in.close();
		json = sBuilder.toString();
		JSONObject principal = new JSONObject(json);
		JSONArray recibidos= (JSONArray) principal.getJSONArray("airports");
		for(int i = 0; i<recibidos.length();i++){
			JSONObject actual = recibidos.getJSONObject(i);
			Aeropuerto nuevo = leerAeropuerto(actual);
			aeropuertos.agregar(nuevo);
			System.out.println("Aeropuerto agregado: " + nuevo.getNombre() + "\n------------------------");
		}
		Iterator<Aeropuerto> i = aeropuertos.iterator();
		consultarVuelo(i.next());
	}
	
	
	public Aeropuerto agregarAeropuerto(String codigo) throws Exception {
		String url = "https://api.flightstats.com/flex/airports/rest/v1/json/cityCode/" + codigo + "?" + IDENTIFICADORES;
		System.out.println(url);
		JSONObject datos = darJSON(url);
		JSONArray arreglo = datos.getJSONArray("airports");
		try{
			Aeropuerto nuevo = leerAeropuerto((JSONObject) arreglo.get(0));
			aeropuertos.agregar(nuevo);
			System.out.println(nuevo);
			//cargarVuelosActuales(nuevo.getCodigo(),nuevo,Vuelo.LLEGANDO);
			//cargarVuelosActuales(nuevo.getCodigo(),nuevo,Vuelo.SALIENDO);
			return nuevo;
		}
		catch(Exception e){
			return null;
		}
	}

	public Iterator<Aeropuerto> darAeropuertos() {
		return aeropuertos.iterator();
	}

	public Iterator<Vuelo> darVuelos() {
		return vuelos.iterator();
	}

	public Aeropuerto eliminarAeropuerto(String codigo) throws Exception {
		Aeropuerto eliminar = new Aeropuerto(codigo);
		eliminar = aeropuertos.buscar(eliminar);
		if(eliminar == null){
			throw new Exception("El aeropuerto no existe");
		}
		eliminarVuelos(eliminar);//Se eliminan los vuelos de la clase principal
		return aeropuertos.eliminar(eliminar);//se elimina el aeropuerto
	}

	public static void guardarCentral() throws Exception{
		File dataDir = new File(System.getProperty("jboss.server.data.dir"));
		File f = new File(dataDir + RUTA_ARCHIVO_SERIALIZADO);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(getInstance());
		oos.close();
		System.out.println("Guardo");
	}
	
	public Iterator<Vuelo> consultarVuelos(Calendar c, String codigo, String tipo,int nHora) throws Exception {
		ListaOrdenada<Vuelo> listaVuelos = new ListaOrdenada<Vuelo>();
		
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int mes = c.get(Calendar.MONTH);
		int anio = c.get(Calendar.YEAR);
		int hora = nHora;
		String url = "";
		if(tipo.equals(Vuelo.LLEGANDO)){
			url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/airport/status/" + codigo + "/arr/" + anio +"/"+mes+"/"+dia+"/" + hora +"?"+IDENTIFICADORES+"utc=false&numHours=1&maxFlights=10";
		}
		else{
			url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/airport/status/" + codigo + "/dep/" + anio +"/"+mes+"/"+dia+"/" + hora+"?"+IDENTIFICADORES+"utc=false&numHours=1&maxFlights=10";
		}
		JSONObject principal = darJSON(url);
		JSONArray recibidos= (JSONArray) principal.getJSONArray("flightStatuses");
		for(int i = 0; i<recibidos.length();i++){
			JSONObject actual = recibidos.getJSONObject(i);
			Vuelo nuevo = leerVuelo(c, actual,tipo);
			listaVuelos.agregar(nuevo);
			System.out.println("cargado vuelo: " + nuevo.getNumero() + tipo);
		}
		
		return listaVuelos.iterator();
	}

	private void cargarVueloATL() throws Exception{
		Aeropuerto aeropuerto = new Aeropuerto("Hartsfield-Jackson Atlanta International Airport", "Atlanta", "United States", "ATL", "US", "ATL", 0.0, "33.640067", "-84.44403");
		aeropuertos.agregar(aeropuerto);
		String json = "";
		//File dataDir = new File(System.getProperty("jboss.server.data.dir"));
		File dataDir = new File("./data");
		File f = new File(dataDir + "/ATL.json");
		BufferedReader in = new BufferedReader(new FileReader(f));
		StringBuilder sBuilder = new StringBuilder();
		String line = null;
		while ((line = in.readLine()) != null) {
			sBuilder.append(line + "\n");
		}
		in.close();
		json = sBuilder.toString();
		JSONObject principal = new JSONObject(json);
		JSONArray recibidos= (JSONArray) principal.getJSONArray("flightStatuses");
		for(int i = 0; i<recibidos.length();i++){
			JSONObject actual = recibidos.getJSONObject(i);
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis());
			Vuelo nuevo = leerVuelo(c, actual,Vuelo.SALIENDO);
			vuelos.agregar(nuevo);
			aeropuerto.agregarVueloEntrada(nuevo);
			System.out.println("cargado vuelo: " + nuevo.getNumero()+ "\n------------------------");
		}
		System.out.println();
		
	}
	public String darURLMapa(){
		String url = "http://maps.googleapis.com/maps/api/staticmap?size=700x430";
		Iterator<Aeropuerto> i = aeropuertos.iterator();
		char indice = 65;
		while(i.hasNext()){
			Aeropuerto actual = i.next();
			String armado = "&markers=color:red%7Clabel:" + indice + "%7C" + actual.getLatitud()+ ","+actual.getLongitud();
			url += armado;
			indice++;
		}
		url+= "&sensor=false";
		System.out.println(url);
		return url;
		
	}
	
	public String darUrlVuelo(String codigo){
		Vuelo vuelo = new Vuelo(codigo);
		vuelo = vuelos.buscar(vuelo);
		String url = "http://maps.googleapis.com/maps/api/staticmap?path=color:0xff0000ff%7Cweight:5%7C" + vuelo.getSalida().getLatitud() + "," + vuelo.getSalida().getLongitud() + "%7C" + vuelo.getLlegada().getLatitud() + "," + vuelo.getLlegada().getLongitud() +"&size=512x512&sensor=false";
		return url;
	}
	
	public static void main(String[] args) throws Exception {
		CentralDeVuelos central = getInstance();
		central.cargarAeropuertos();
		central.darURLMapa();		
	//	guardarCentral();
	}

	@Override
	public double consultarCalificacion(String codigo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean ActualizarCalificacion(String codigo, double valor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Fecha> consultarFechas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Vuelo> consultarVuelosPorEstado(String tipo, String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Vuelo> buscarVuelosPorCalificacion(int calificacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aerolinea darMayorRetraso() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aerolinea darMenorRetraso() {
		// TODO Auto-generated method stub
		return null;
	}

}
