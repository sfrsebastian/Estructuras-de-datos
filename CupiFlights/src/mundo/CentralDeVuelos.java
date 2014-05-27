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
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import Arbol23.Arbol23;
import ArbolTrie.ArbolTrie;
import Grafo.Arco;
import Grafo.Camino;
import Grafo.Grafo;
import HashTable.TablaHashing;
import Lista.Lista;
import ListaEncadenada.ListaEncadenada;
import ListaOrdenada.ListaOrdenada;

public class CentralDeVuelos implements ICentralDeVuelos{
	//--------------------
	//CONSTANTES
	//--------------------
	//	/**
	//	 * Listado de codigos (ICAO) aeropuertos mas visitados.
	//	 */
	//	private static final String[] CODIGOS = {"KATL","ZBAA","EGLL","RJTT","KORD","KLAX","OMDB","LFPG","KDFW","WIII"
	//														,"VHHH","EDDF","WSSS","EHAM","KDEN","ZGGG","VTBS","LTBA","KJFK","WMKK"
	//														,"ZSPD","KSFO","KCLT","KLAS","RKSI","KMIA","KPHX","KIAH","LEMD","EDDM"
	//														,"YSSY","VIDP","SBGR","LIRF","CYYZ","ZSSS","EGKK","RJAA","LEBL","KEWR"
	//														,"KMCO","KSEA","KMSP","ZUUU","RPLL","KDTW","ZGSZ","VABB","MMMX","UUDD"};

	/**
	 * Nombre del archivo para serializar la central
	 */
	public final static String RUTA_ARCHIVO_SERIALIZADO = "/CentralDeVuelos.dat/";

	/**
	 * Identificador unico para hacer uso del api de flightstats
	 */
	public static final String IDENTIFICADORES = "appId=ddb807c4&appKey=5712433a4c35dd349e361c6c2210f3d2&";

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

	/**
	 * La tabla de usuarios de la central
	 */
	private TablaHashing<String,Usuario>usuarios;
	
	/**
	 * El usuario actualmente activo en la central
	 */
	private Usuario usuarioActivo;
	
	/**
	 * El grafo de la central
	 */
	private Grafo<String,Aeropuerto,InfoCostos> grafo;
	
	/**
	 * Formateador fechas
	 */
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
	 * @throws Exception 
	 * @pos Se inicializan los atrbutos de la clase
	 */
	private CentralDeVuelos() throws Exception{
		aeropuertos = new Arbol23<Aeropuerto>();
		fechas = new ArbolTrie<Vuelo>();
		vuelos = new Arbol23<Vuelo>();
		aerolineas = new Arbol23<Aerolinea>();
		dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		usuarios = new TablaHashing<String,Usuario>(10,2);
		usuarioActivo = null;
		grafo = new Grafo<String,Aeropuerto,InfoCostos>(100);
		cargarAeropuertos();
	}

	/**
	 * Retorna la instancia actual de la central
	 * @return La instancia actual, de lo contrario una instancia nueva.
	 * @throws Exception 
	 */
	public static CentralDeVuelos getInstance() throws Exception{
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
	 * Retorna la ruta de guardado segun el caso<br>
	 * Puede ser la ruta de datos del servidor o la ruta de data local.
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
	 * Arma el objeto JSON del url dado.<br>
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
	 * Crea un nuevo vuelo a partir de el objeto JSON recibido y el tipo de vuelo<br>
	 * Agrega a la aerolinea el vuelo creado
	 * @param objeto El objeto JSON para la lectura de atributos
	 * @param tipo El tipo de vuelo Vuelo.LLEGANDO || Vuelo.SALIENDO
	 * @return El vuelo creado.
	 * @throws Exception
	 */
	private Vuelo leerVuelo(Calendar c,JSONObject objeto, String tipo, float duracion) throws Exception{
		String numero =  objeto.getString("flightNumber");
		String codigoSalida = objeto.getString("departureAirportFsCode");
		Aeropuerto salida = manejarAeropuerto(codigoSalida);
		String codigoLlegada = objeto.getString("arrivalAirportFsCode");
		Aeropuerto llegada = manejarAeropuerto(codigoLlegada);
		String codigoAereolinea = objeto.getString("carrierFsCode");
		Aerolinea aerolinea = manejarAereolinea(codigoAereolinea);
		Object[] rating = darRating(codigoAereolinea,numero,codigoSalida);
		Vuelo nuevo = new Vuelo(numero,salida,llegada,aerolinea,tipo,rating,duracion, c);
		aerolinea.agregarVuelo(nuevo);
		return nuevo;
	}

	/**
	 * Crea un nuevo aeropuerto a partir de el objeto JSON recibido<br>
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
		String date = dateFormat.format(c.getTime());
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int mes = c.get(Calendar.MONTH)+1;
		int anio = c.get(Calendar.YEAR);
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
			Vuelo nuevo;
			try{
				JSONObject obd = actual.getJSONObject("flightDurations");
				nuevo = leerVuelo(c,actual,tipo,(float)obd.getDouble("scheduledBlockMinutes"));
			}
			catch(Exception e){
				nuevo = leerVuelo(c,actual,tipo,0);
			}
			if(tipo.equals(Vuelo.LLEGANDO)){
				if(a1.getVuelosEntrada().buscarElemento(date, nuevo)==null){
					a1.agregarVueloEntrada(date, nuevo);
				}
				if(a2.getVuelosSalida().buscarElemento(date, nuevo) == null){
					a2.agregarVueloSalida(date,nuevo);
				}
			}
			else if(tipo.equals(Vuelo.SALIENDO)){
				if(a1.getVuelosSalida().buscarElemento(date, nuevo)==null){
					a1.agregarVueloSalida(date,nuevo);
				}
				if(a2.getVuelosEntrada().buscarElemento(date, nuevo) == null){
					a2.agregarVueloEntrada(date,nuevo);
				}
			}
			Vuelo encontrado;
			if((encontrado = vuelos.buscar(nuevo))==null){
				System.out.println("La fecha es: " + date );
				vuelos.agregar(nuevo);
				fechas.agregar(date, nuevo);
				if(tipo.equals(Vuelo.SALIENDO)){
					grafo.agregarArco(a1.getCodigo(),a2.getCodigo(), new InfoCostos(nuevo));
					System.out.println("Agregado vuelo # " +  nuevo.getNumero() + " fecha:" + dateFormat.format(c.getTime()) +  " - " + a1.getNombre() + " - "+ a2.getNombre() + "\n-------------------");
				}
				else{
					grafo.agregarArco(a2.getCodigo(),a1.getCodigo(), new InfoCostos(nuevo));
					System.out.println("Agregado vuelo # " +  nuevo.getNumero() + " fecha:" + dateFormat.format(c.getTime()) +  " - " + a2.getNombre() + " - "+ a1.getNombre() + "\n-------------------");
				}	
			}	
			else{
				int comp = nuevo.compareTo(encontrado);
				System.out.println("No agrego");
			}
		}
	}

	/**
	 * Retorna la aereolinea del codigo dado por parametro<br>
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
	 * Retorna el aeropuerto del codigo dado por parametro<br>
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
			fechas.eliminarElemento(actual.getFechaString(), actual);
			actual.getAereolinea().eliminarVuelo(actual);
		}
		while(entradas.hasNext()){
			Vuelo actual = entradas.next();
			vuelos.eliminar(actual);
			fechas.eliminarElemento(actual.getFechaString(), actual);
			actual.getAereolinea().eliminarVuelo(actual);
		}
	}

	private void buscarVuelosComun(Aeropuerto vuelo) throws Exception {
		Iterator<Aeropuerto> it2 = aeropuertos.iterator();
//		int i = 0;
//		Random r = new Random();
		while(it2.hasNext()){
			Aeropuerto a2 = it2.next();
			if(!vuelo.equals(a2)){
				//Fecha actual
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(System.currentTimeMillis());
				cargarVuelosPorFecha(c, vuelo, a2, Vuelo.LLEGANDO);
				cargarVuelosPorFecha(c, vuelo, a2, Vuelo.SALIENDO);

				//Hace 4 dias
				if(it2.hasNext()){
					a2 = it2.next();
					c = Calendar.getInstance();
					c.setTimeInMillis(System.currentTimeMillis());
					if(c.get(Calendar.DAY_OF_MONTH)<=4){
						c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH)-1, 30-(c.get(Calendar.DAY_OF_MONTH)-3));
					}
					else{
						c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), (c.get(Calendar.DAY_OF_MONTH)-3));
					}
					cargarVuelosPorFecha(c, vuelo, a2, Vuelo.LLEGANDO);
					cargarVuelosPorFecha(c, vuelo, a2, Vuelo.SALIENDO);
				}
				

				//Cuatro dias adelante
				if(it2.hasNext()){
					a2 = it2.next();
					c = Calendar.getInstance();
					c.setTimeInMillis(System.currentTimeMillis());
					if(c.get(Calendar.DAY_OF_MONTH)>=27){
						c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, 1+(c.get(Calendar.DAY_OF_MONTH)-28));
					}
					else{
						c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), (c.get(Calendar.DAY_OF_MONTH)+3));
					}
					cargarVuelosPorFecha(c, vuelo, a2, Vuelo.LLEGANDO);
					cargarVuelosPorFecha(c, vuelo, a2, Vuelo.SALIENDO);
				}
//				i++;
			}
		}
	}

	//--------------------
	//METODOS
	//--------------------
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
		for(int i = 0; i<10;i++){
			JSONObject actual = recibidos.getJSONObject(i);
			Aeropuerto nuevo = leerAeropuerto(actual);
			if(aeropuertos.agregar(nuevo)){
				grafo.agregarVertice(nuevo.getCodigo(), nuevo);
				System.out.println("Aeropuerto agregado: " + nuevo.getNombre() + "\n------------------------");
			}
		}
		Iterator<Aeropuerto> i = aeropuertos.iterator();
//		Random r = new Random();
//		Aeropuerto actual = i.next();
		while(i.hasNext()){
			Aeropuerto actual = i.next();
			buscarVuelosComun(actual);
		}
//		buscarVuelosComun(actual);
//		while(((r.nextBoolean() && r.nextBoolean()&& r.nextBoolean()) == false) && i.hasNext()){
//			actual = i.next();
//		}
	}

	public Aeropuerto agregarAeropuerto(String codigo) throws Exception {
		String url = "https://api.flightstats.com/flex/airports/rest/v1/json/fs/" + codigo + "?" + IDENTIFICADORES;
		System.out.println(url);
		JSONObject datos = darJSON(url);
		JSONObject arreglo = datos.getJSONObject("airport");
		try{
			Aeropuerto nuevo = leerAeropuerto((JSONObject) arreglo);
			aeropuertos.agregar(nuevo);
			grafo.agregarVertice(nuevo.getCodigo(), nuevo);
			buscarVuelosComun(nuevo);
			System.out.println("Aeropuerto agregado: " + nuevo.getNombre() + "\n------------------------");
			return nuevo;
		}
		catch(Exception e){
			return null;
		}
	}

	public Aeropuerto eliminarAeropuerto(String codigo) throws Exception {
		Aeropuerto eliminar = new Aeropuerto(codigo);
		eliminar = aeropuertos.buscar(eliminar);
		if(eliminar == null){
			throw new Exception("El aeropuerto no existe");
		}
		grafo.eliminarVertice(eliminar.getCodigo());
		eliminarVuelos(eliminar);//Se eliminan los vuelos de la clase principal
		System.out.println("Aeropuerto eliminado: " + eliminar.getNombre() + "\n------------------------");
		return aeropuertos.eliminar(eliminar);//se elimina el aeropuerto
	}

	public double consultarCalificacion(String codigo) {
		Vuelo vuelo = new Vuelo(codigo);
		vuelo = vuelos.buscar(vuelo);
		return vuelo.getRating();
	}

	public double actualizarCalificacion(String codigo, double valor) {
		Vuelo vuelo = new Vuelo(codigo);
		vuelo = vuelos.buscar(vuelo);
		return vuelo.actualizarRating(valor);
	}

	public double consultarCalificacionAeropuerto(String codigo) {
		Aeropuerto aeropuerto = new Aeropuerto(codigo);
		aeropuerto = aeropuertos.buscar(aeropuerto);
		return aeropuerto.getCalificacion();
	}

	public double ActualizarCalificacionAeropuerto(String codigo, double calificacion) {
		Aeropuerto aeropuerto = new Aeropuerto(codigo);
		aeropuerto = aeropuertos.buscar(aeropuerto);
		return aeropuerto.setCalificacion(calificacion);
	}
	
	public Iterator<String> darFechas() {
		return fechas.darPalabras();
	}

	public Iterator<Aeropuerto> darAeropuertos() {
		return aeropuertos.iterator();
	}

	public Iterator<Vuelo> darVuelosAeropuertoFecha(String codigo, Calendar c, String tipo) throws Exception{
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH)-1, c.get(Calendar.DAY_OF_MONTH));
		Aeropuerto aeropuerto = manejarAeropuerto(codigo);
		String date = dateFormat.format(c.getTime());
		if(tipo.equals(Vuelo.LLEGANDO)){
			return aeropuerto.getVuelosEntrada().buscar(date);
		}
		else{
			return aeropuerto.getVuelosSalida().buscar(date);
		}
	}

	public int[] darTardanzasAeropuertoPorFecha(String codigo, Calendar c1, Calendar c2) throws Exception{
		c1.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH)-1, c1.get(Calendar.DAY_OF_MONTH));
		c2.set(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH)-1, c2.get(Calendar.DAY_OF_MONTH));
		Aeropuerto aeropuerto = manejarAeropuerto(codigo);
		return aeropuerto.darTardanzasPorFecha(c1,c2);
	}

	public Iterator<Vuelo> buscarVuelosPorCalificacion(int calificacion, Calendar c1, Calendar c2) {
		c1.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH)-1, c1.get(Calendar.DAY_OF_MONTH));
		c2.set(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH)-1, c2.get(Calendar.DAY_OF_MONTH));
		ListaOrdenada<Vuelo> respuesta = new ListaOrdenada<Vuelo>();
		Iterator<Vuelo> i = vuelos.iterator();
		while(i.hasNext()){
			Vuelo actual = i.next();
			boolean mayor = actual.getFecha().get(Calendar.DAY_OF_MONTH)>=c1.get(Calendar.DAY_OF_MONTH) && actual.getFecha().get(Calendar.MONTH) == c1.get(Calendar.MONTH) || actual.getFecha().get(Calendar.MONTH) > c1.get(Calendar.MONTH);
			boolean menor = actual.getFecha().get(Calendar.DAY_OF_MONTH)<=c2.get(Calendar.DAY_OF_MONTH) && actual.getFecha().get(Calendar.MONTH) == c2.get(Calendar.MONTH) || actual.getFecha().get(Calendar.MONTH) < c2.get(Calendar.MONTH);
			if((int)actual.getRating() == calificacion && mayor && menor){
				respuesta.agregar(actual);
			}
		}
		return respuesta.iterator();
	}

	public Aerolinea darMayorRetraso() {
		Aerolinea respuesta = null;
		Iterator<Aerolinea> i = aerolineas.iterator();
		while(i.hasNext()){
			Aerolinea actual = i.next();
			if(respuesta == null){
				respuesta = actual;
			}
			else if(actual.getRetrasoTotal()>respuesta.getRetrasoTotal()){
				respuesta = actual;
			}
		}
		return respuesta;
	}

	public Aerolinea darMenorRetraso() {
		Aerolinea respuesta = null;
		Iterator<Aerolinea> i = aerolineas.iterator();
		while(i.hasNext()){
			Aerolinea actual = i.next();
			if(respuesta == null){
				respuesta = actual;
			}
			else if(actual.getRetrasoTotal()<respuesta.getRetrasoTotal()){
				respuesta = actual;
			}
		}
		return respuesta;
	}

	public String darURLMapa(){
		String respuesta = "";
		Iterator<Aeropuerto> i = aeropuertos.iterator();
		while(i.hasNext()){
			Aeropuerto actual = i.next();
			respuesta += "var " + actual.getCodigo() + "= new google.maps.LatLng(" + actual.getLatitud() + "," + actual.getLongitud()+");\n";
			respuesta += "var a"+actual.getCodigo() + "= new google.maps.Marker({\n";
			respuesta += "	position:" + actual.getCodigo() + ",\n";
			respuesta += "	map: map,\n";
			respuesta += "	title: " + " ' " + actual.getNombre() + ": " + actual.getTardanza() + " ' \n";
			respuesta += "});\n";
		}
		return respuesta.toString();
//		ArbolTrie<Aeropuerto> arbol = new ArbolTrie<Aeropuerto>();
//		String url = "http://maps.googleapis.com/maps/api/staticmap?size=700x430";
//		Iterator<Aeropuerto> i = aeropuertos.iterator();
//		char indice = 65;
//		Random r = new Random();
//		int j = 0;
//		while(i.hasNext() && j<30){
//			boolean next = r.nextBoolean();
//			Aeropuerto actual = i.next();
//			if(next){
//				String armado = "&markers=color:red%7Clabel:" + indice + "%7C" + actual.getLatitud()+ ","+actual.getLongitud();
//				url += armado;
//				arbol.agregar(indice+"", actual);
//				indice++;
//			}
//			j++;
//		}
//		url+= "&sensor=false";
//		Object[] respuesta = new Object[2];
//		respuesta[0] = url;
//		respuesta[1] = arbol;
//		return respuesta;
	}

	/**
	 * 
	 * @return
	 */
	public Iterator<Aerolinea> getAerolineas() {
		return aerolineas.iterator();
	}

	/**
	 * Serializa la central en la ruta dada
	 * @throws Exception
	 */
	public void guardarCentral() throws Exception{
		File f = new File(darRuta() + RUTA_ARCHIVO_SERIALIZADO);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(getInstance());
		oos.close();
		System.out.println("Guardo");
	}

	/**
	 * Retorna un iterador de los vuelos de la central
	 * @return iterador de vuelos
	 */
	public Iterator<Vuelo> darVuelos() {
		return vuelos.iterator();
	}
	
	/**
	 * Retorna el aeropuerto con el codigo dado.
	 * @param codigo El codigo del aeropuerto
	 * @return el Aeropuerto encontrado
	 * @throws Exception
	 */
	public Aeropuerto darAeropuerto(String codigo) throws Exception{
		return manejarAeropuerto(codigo);
	}
	
	/**
	 * Retorna el vuelo con el codigo dado.
	 * @param codigo El codigo del vuelo
	 * @return el vuelo encontrado
	 */
	public Vuelo darVuelo(String numero){
		Vuelo vuelo = new Vuelo(numero);
		return vuelos.buscar(vuelo);
	}
	
	/**
	 * Retorna el url de imagen entre dos vuelos
	 * @param codigo El codigo del vuelo
	 * @return
	 */
	public String darUrlVuelo(String codigo){
		Vuelo vuelo = new Vuelo(codigo);
		vuelo = vuelos.buscar(vuelo);
		String url = "http://maps.googleapis.com/maps/api/staticmap?path=color:0xff0000ff%7Cweight:5%7C" + vuelo.getSalida().getLatitud() + "," + vuelo.getSalida().getLongitud() + "%7C" + vuelo.getLlegada().getLatitud() + "," + vuelo.getLlegada().getLongitud() +"&size=512x512&sensor=false";
		return url;
	}

	///////METODO PARA CONSULTAR VUELO DEL API
	/**
	 * Consulta los vuelos en la fecha y hora dada del aeropuerto con codigo y tipo dados.
	 * @param c Contenedor de la fecha.
	 * @param codigo El codigo del aereopuerto
	 * @param tipo El tipo de vuelos a consultar. Vuelo.LLEGANDO || Vuelo.SALIENDO.
	 * @param nHora La hora de la busqueda.
	 * @return Iterador de vuelos que cumplen con los criterios
	 * @throws Exception
	 */
	@Deprecated
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
			JSONObject obd = principal.getJSONObject("flightDurations");
			Vuelo nuevo = leerVuelo(c,actual,tipo,(float)obd.getDouble("scheduledAirMinutes"));
			listaVuelos.agregar(nuevo);
			System.out.println("cargado vuelo: " + nuevo.getNumero() + tipo);
		}
		return listaVuelos.iterator();
	}

	//2.0
	@Override
	public boolean registrarUsuario(String nombre, String apellido,String usuario, String correo, String constrasenia) {
		if(usuarios.buscar(usuario)==null){
			Usuario nuevo = new Usuario(nombre, apellido, usuario, correo, constrasenia);
			usuarios.agregar(usuario, nuevo);
			usuarioActivo = nuevo;
			System.out.println(nuevo);
			return true;
		}
		return false;
	}

	@Override
	public Usuario ingresar(String usuario, String contrasenia) {
		Usuario respuesta = null;
		if((respuesta = usuarios.buscar(usuario))!=null){
			if(respuesta.autenticarUsuario(usuario, contrasenia)){
				usuarioActivo = respuesta;
				return respuesta;
			}
		}
		return null;
	}

	@Override
	public void cerrarSesion() {
		usuarioActivo = null;	
	}
	
	@Override
	public Aeropuerto agregarAeropuertoUsuario(String codigoAeropuerto) {
		Aeropuerto agregar = new Aeropuerto(codigoAeropuerto);
		agregar = aeropuertos.buscar(agregar);
		if(usuarioActivo!=null){
			usuarioActivo.agregarAeropuerto(agregar);
			usuarioActivo.agregarCiudad(agregar.getCiudad());
			return agregar;
		}
		return null;
	}

	@Override
	public Aeropuerto eliminarAeropuertoUsuario(String codigoAeropuerto) {
		Aeropuerto eliminar = new Aeropuerto(codigoAeropuerto);
		eliminar = aeropuertos.buscar(eliminar);
		if(usuarioActivo!=null){
			usuarioActivo.eliminarAeropuerto(eliminar);
			usuarioActivo.eliminarCiudad(eliminar.getCiudad());
			return eliminar;
		}
		return null;
	}

	@Override
	public Aerolinea agregarAerolineaUsuario(String codigoAerolinea) {
		Aerolinea agregar = new Aerolinea(codigoAerolinea);
		agregar = aerolineas.buscar(agregar);
		if(usuarioActivo!=null){
			System.out.println(usuarioActivo);
			usuarioActivo.agregarAerolinea(agregar);
			return agregar;
		}
		return null;
	}

	@Override
	public Aerolinea eliminarAerolineaUsuario(String codigoAerolinea) {
		Aerolinea eliminar = new Aerolinea(codigoAerolinea);
		eliminar = aerolineas.buscar(eliminar);
		if(usuarioActivo!=null){
			usuarioActivo.eliminarAerolinea(eliminar);
			return eliminar;
		}
		return null;
	}

	@Override
	public String agregarCiudadUsuario(String codigoCiudad) {
		Aeropuerto agregar = new Aeropuerto(codigoCiudad);
		agregar = aeropuertos.buscar(agregar);
		String ciudad = agregar.getCiudad();
		if(usuarioActivo!=null){
			usuarioActivo.agregarCiudad(ciudad);
			usuarioActivo.agregarAeropuerto(agregar);
			return ciudad;
		}
		return null;
	}

	@Override
	public String eliminarCiudadUsuario(String codigoCiudad) {
		Aeropuerto eliminar = new Aeropuerto(codigoCiudad);
		eliminar = aeropuertos.buscar(eliminar);
		String ciudad = eliminar.getCiudad();
		if(usuarioActivo!=null){
			usuarioActivo.eliminarCiudad(ciudad);
			usuarioActivo.eliminarAeropuerto(eliminar);
			return ciudad;
		}
		return null;
	}

	@Override
	public void definirRangoTiempoUsuario(int t1, int t2) {
		if(usuarioActivo != null){
			usuarioActivo.setDuraciones(t1, t2);
		}
	}

	@Override
	public Camino<String,Aeropuerto,InfoCostos> darGrado(String codigo1, String codigo2) {
		Camino<String,Aeropuerto,InfoCostos> respuesta = grafo.darCaminoSimpleMasCorto(codigo1, codigo2);
		return respuesta;
	}

	@Override
	public Camino<String,Aeropuerto,InfoCostos> darRutaMenorLongitud(String codigo1, String codigo2) {
		Camino<String,Aeropuerto,InfoCostos> respuesta = grafo.darCaminoMasBarato(codigo1, codigo2, InfoCostos.DISTANCIA);
		return respuesta;
	}

	@Override
	public Iterator<Aeropuerto> darRutaMenorLongitudConParada(String codigo1,String codigo2) {
		Camino<String,Aeropuerto,InfoCostos> respuesta = grafo.darCaminoMasBarato(codigo1, codigo2, InfoCostos.DISTANCIA);
		while(respuesta.getLongitud() == 1){
			Arco<String,Aeropuerto,InfoCostos> arco = grafo.darArco(codigo1, codigo2);
			grafo.eliminarArco(codigo1, codigo2);
			respuesta = grafo.darCaminoMasBarato(codigo1, codigo2, InfoCostos.DISTANCIA);
			grafo.agregarArco(codigo1, codigo2, arco.getInfo());
		}
		return respuesta.darVertices();
	}

	@Override
	public Iterator<Aeropuerto> darRutaMenorTiempo(String codigo1,String codigo2) {
		Camino<String,Aeropuerto,InfoCostos> respuesta = grafo.darCaminoMasBarato(codigo1, codigo2, InfoCostos.TIEMPO);
		return respuesta.darVertices();
	}

	@Override
	public Camino<String,Aeropuerto,InfoCostos> darRutaMenorTiempoConParada(String codigo1,String codigo2) {
		Camino<String,Aeropuerto,InfoCostos> respuesta = grafo.darCaminoMasBarato(codigo1, codigo2, InfoCostos.DISTANCIA);
		while(respuesta.getLongitud() == 1){
			Arco<String,Aeropuerto,InfoCostos> arco = grafo.darArco(codigo1, codigo2);
			grafo.eliminarArco(codigo1, codigo2);
			respuesta = grafo.darCaminoMasBarato(codigo1, codigo2, InfoCostos.TIEMPO);
			grafo.agregarArco(codigo1, codigo2, arco.getInfo());
		}
		return respuesta;
	}

	@Override
	public Camino<String,Aeropuerto,InfoCostos> darRutaMayorRating(String codigo1,String codigo2) {
		Camino<String,Aeropuerto,InfoCostos> respuesta = grafo.darCaminoMasBarato(codigo1, codigo2, InfoCostos.TIEMPO);
		return respuesta;
	}

	@Override
	public Camino<String,Aeropuerto,InfoCostos> darRutaMenorTardios(String codigo1,String codigo2) {
		Camino<String,Aeropuerto,InfoCostos> respuesta = grafo.darCaminoMasBarato(codigo1, codigo2, InfoCostos.TARDIOS);
		return respuesta;
	}

	@Override
	public Camino<String,Aeropuerto,InfoCostos> darTourMasLargo(String codigo1) {
		Camino<String,Aeropuerto,InfoCostos> respuesta = grafo.darCicloMasLargo(codigo1, InfoCostos.DISTANCIA);
		return respuesta!=null?respuesta:null;
	}

	@Override 
	public Iterator<Camino<String,Aeropuerto,InfoCostos>> darToursDisponibles(Lista lista) {
		ListaEncadenada<Camino<String,Aeropuerto,InfoCostos>>respuesta = new ListaEncadenada<Camino<String,Aeropuerto,InfoCostos>>();
		Iterator<String> it1 = lista.iterator();
		String codigo = it1.next();
		Camino<String,Aeropuerto,InfoCostos>caminoParcial = new Camino<String,Aeropuerto,InfoCostos>(grafo.darVertice(codigo),true," ");
		while(it1.hasNext()){
			Aeropuerto n = new Aeropuerto(it1.next());
			Aeropuerto llegada = aeropuertos.buscar(n);
			Aeropuerto a = caminoParcial.getDestino() != null? caminoParcial.getDestino():caminoParcial.getOrigen();
			Arco<String,Aeropuerto,InfoCostos> nuevo;
			if((nuevo = grafo.darArco(a.getCodigo(),llegada.getCodigo()))!=null){
				if(grafo.darArco(llegada.getCodigo(),codigo)!=null){
					caminoParcial.agregarArcoFinal(nuevo);
					agregarCamino(respuesta,caminoParcial,codigo);
				}
			}
		}
		return respuesta.iterator();
	}

	/**
	 * Agrega a la lista el camino cerrado
	 * @param respuesta
	 * @param caminoParcial
	 * @param codigo
	 */
	private void agregarCamino(ListaEncadenada<Camino<String, Aeropuerto, InfoCostos>> respuesta,Camino<String, Aeropuerto, InfoCostos> caminoParcial, String codigo) {
		if(caminoParcial.getLongitud() > 0){
			Arco<String,Aeropuerto,InfoCostos> nuevo = grafo.darArco(caminoParcial.getDestino().getCodigo(),codigo);
			caminoParcial.agregarArcoFinal(nuevo);
			respuesta.agregar(caminoParcial);
		}
	}

	@Override
	public Camino<String,Aeropuerto,InfoCostos> darTourDesde(String codigo) {
		Iterator<Aeropuerto> it1 = usuarioActivo.getAeropuertos();
		Camino<String,Aeropuerto,InfoCostos>caminoParcial = new Camino<String,Aeropuerto,InfoCostos>(grafo.darVertice(codigo),true," ");
		while(it1.hasNext()){
			Aeropuerto llegada = it1.next();
			Aeropuerto a = caminoParcial.getDestino() != null? caminoParcial.getDestino():caminoParcial.getOrigen();
			Arco<String,Aeropuerto,InfoCostos> nuevo;
			if((nuevo = grafo.darArco(a.getCodigo(),llegada.getCodigo()))!=null){
				if(grafo.darArco(llegada.getCodigo(),codigo)!=null){
					caminoParcial.agregarArcoFinal(nuevo);
				}
			}
		}
		if(caminoParcial.getLongitud() > 0){
			Arco<String,Aeropuerto,InfoCostos> nuevo = grafo.darArco(caminoParcial.getDestino().getCodigo(),codigo);
			caminoParcial.agregarArcoFinal(nuevo);
			return caminoParcial;
		}
		else
			return null;
		
//		if(respuesta.getLongitud() == 2){
//			return null;
//		}
//		else{
//			return respuesta;
//		}
	}
	
	public String darJSRuta (Camino<String,Aeropuerto,InfoCostos> ruta){
		Iterator<Aeropuerto> iterador = ruta.darVertices();
		Iterator<Arco<String,Aeropuerto,InfoCostos>> arcos = ruta.darArcos();
		String respuesta = "";
		String rutas = "";
		while(arcos.hasNext()){
			Arco<String,Aeropuerto,InfoCostos> actual = arcos.next();
			Aeropuerto destino = actual.getDestino().getElemento();
			String desCiudad = destino.getCiudad();
			desCiudad = desCiudad.replaceAll("\\s+","");
			
			Aeropuerto origen = actual.getOrigen().getElemento();
			String desOrig = origen.toString();
			desOrig = desOrig.replaceAll("\\s+","");
			
			respuesta += "var contentString" + desCiudad + destino.getCodigo()  +"= '<b>Origen: </b> " + desOrig + "<br><b>Destino: </b>" + destino.toString() + "<br><b>Vuelo: </b>" + actual.getInfo().getVuelo().toString()+"';\n";	
			respuesta += "var infowindow"+ desCiudad+destino.getCodigo() + "= new google.maps.InfoWindow({\n";
			respuesta += "	content: contentString" + desCiudad+destino.getCodigo() + "\n";
			respuesta +=" });\n";  
			
			respuesta += "var latLng" + desCiudad+destino.getCodigo() + "= new google.maps.LatLng(" + destino.getLatitud() + "," + destino.getLongitud()+");\n";
			respuesta += "var marker"+desCiudad+destino.getCodigo() + "= new google.maps.Marker({\n";
			respuesta += "	position: latLng" + desCiudad+destino.getCodigo() + ",\n";
			respuesta += "	map: map,\n";
			respuesta += "	title: '<b>Vuelo:</b>" + actual.getInfo().getVuelo().toString() + "'\n";
			respuesta += "});\n";
			
			respuesta += "google.maps.event.addListener(marker"+desCiudad+destino.getCodigo()+", 'click', function() {\n";
			respuesta += "	infowindow"+desCiudad+destino.getCodigo()+".open(map,marker"+desCiudad+destino.getCodigo()+");\n";
			respuesta += "});\n";
		}
		rutas += "var flightPlanCoordinates = [";
		while(iterador.hasNext()){
			Aeropuerto actual = iterador.next();
			rutas+="new google.maps.LatLng(" + actual.getLatitud() + "," + actual.getLongitud()+"),\n";
		}
		rutas = rutas.substring(0, rutas.length()-2);
		rutas+="];";
		respuesta +=rutas;
		return respuesta;
	}
//	public static void main(String[] args) throws Exception {
//		CentralDeVuelos central = getInstance();
//		System.out.println(central.darURLMapa());
//		central.agregarAeropuerto("BOG");
//		central.guardarCentral();
//		Iterator<Aeropuerto> camino = central.darTourDesde("LHR");
//		while(camino.hasNext()){
//			Aeropuerto actual = camino.next();
//			System.out.println(actual.getCiudad());
//		}
//		System.out.println("---------------------darTourmaslargo");
//		Camino<String,Aeropuerto,InfoCostos> camino = central.darRutaMenorTiempoConParada("ATL","LHR");
//		System.out.println(central.darJSRuta(camino));
//		while(camino.hasNext()){
//			Aeropuerto actual = camino.next();
//			System.out.println(actual.getCiudad());
//		}
//		central.registrarUsuario("Sebastian", "njkcew", "sfrsebastian", "lkjdan", "1234");
//		central.agregarAeropuertoUsuario("CDG");
//		central.agregarAeropuertoUsuario("LHR");
//		central.agregarAeropuertoUsuario("PEK");
//		central.agregarAeropuertoUsuario("HND");
//		ListaEncadenada<String> lista = new ListaEncadenada<String>();
//		lista.agregar("CDG");
//		lista.agregar("ATL");
//		Iterator<Camino<String, Aeropuerto, InfoCostos>>camino = central.darToursDisponibles(lista);
//		Usuario p = central.ingresar("rom", "1234");
//		System.out.println(p);
//	}
}
