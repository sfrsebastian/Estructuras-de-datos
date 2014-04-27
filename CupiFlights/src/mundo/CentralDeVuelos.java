package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import Arbol23.Arbol23;
import ArbolTrie.ArbolTrie;
import ListaOrdenada.ListaOrdenada;

public class CentralDeVuelos implements ICentralDeVuelos{
	private static final String URLAEROPUERTOS = "https://api.flightstats.com/flex/airports/rest/v1/json/active?appId=3723b96f&appKey=d6e053700776ffb5b91cd46f8c88722b";
	public final static String RUTA_ARCHIVO_SERIALIZADO = "/Users/sfrsebastian/Desktop/CentralDeVuelos.dat/";
	public static final String IDENTIFICADORES = "appId=3723b96f&appKey=d6e053700776ffb5b91cd46f8c88722b&";
	
	private static final long serialVersionUID = -2434025803582670357L;
	private Arbol23<Aeropuerto> aeropuertos;
	private Arbol23<Fecha> fechas;
	private Arbol23<Vuelo> vuelos;
	private static CentralDeVuelos instancia = null;
	
	private CentralDeVuelos(){
		aeropuertos = new Arbol23<Aeropuerto>();
		fechas = new Arbol23<Fecha>();
		vuelos = new Arbol23<Vuelo>();
	}

	public static CentralDeVuelos getInstance(){
		System.out.println("getInstance");
		if( instancia == null )
		{
			System.out.println("Creando instancia");
			try
			{
//				ServerConfig config = ServerConfigLocator.locate( );
//				File dataDir = config.getServerDataDir();
				File tmp = new File(RUTA_ARCHIVO_SERIALIZADO);
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

	private void cargarVuelosActuales(String codigo, Aeropuerto aeropuerto, String tipo) throws Exception{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int mes = c.get(Calendar.MONTH) +1;
		int anio = c.get(Calendar.YEAR);
		int hora = c.get(Calendar.HOUR_OF_DAY);
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
			Vuelo nuevo = leerVuelo(actual,tipo);
			vuelos.agregar(nuevo);
			aeropuerto.agregarVueloEntrada(nuevo);
			System.out.println("cargado vuelo: " + nuevo.getNumero());
		}
	}

	private Vuelo leerVuelo(JSONObject objeto, String tipo) {
		String numero =  objeto.getString("flightNumber");
		String codigoSalida = objeto.getString("departureAirportFsCode");
		String codigoLlegada = objeto.getString("arrivalAirportFsCode");
		String codigoAereolinea = objeto.getString("carrierFsCode");
		Vuelo nuevo = new Vuelo(numero,codigoSalida,codigoLlegada,tipo,codigoAereolinea);
		return nuevo;
	}

	private Aeropuerto leerAeropuerto(JSONObject objeto) throws Exception {
		String nombre =  objeto.getString("name");
		String ciudad = objeto.getString("city");
		String pais = objeto.getString("countryName");
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
		Aeropuerto nuevo = new Aeropuerto(nombre,ciudad,pais,codigoCiudad,codigoPais,codigo,tardanza);
		return nuevo;
	}

	public void cargarAeropuertos() throws Exception{
		JSONObject principal = darJSON(URLAEROPUERTOS);
		JSONArray recibidos= (JSONArray) principal.getJSONArray("airports");
		for(int i = 0; i<50;i++){
			int random =(int) (Math.random() * recibidos.length());
			JSONObject actual = recibidos.getJSONObject(random);
			Aeropuerto nuevo = leerAeropuerto(actual);
			aeropuertos.agregar(nuevo);
			cargarVuelosActuales(nuevo.getCodigo(),nuevo,Vuelo.LLEGANDO);
			cargarVuelosActuales(nuevo.getCodigo(),nuevo,Vuelo.SALIENDO);
			System.out.println("agregado " + nuevo.getNombre() + "\n ------------------------");
		}
	}
	
	@Override
	public Aeropuerto agregarAeropuerto(String codigo) throws Exception {
		String url = "https://api.flightstats.com/flex/airports/rest/v1/json/cityCode/" + codigo + "?" + IDENTIFICADORES;
		System.out.println(url);
		JSONObject datos = darJSON(url);
		JSONArray arreglo = datos.getJSONArray("airports");
		try{
			Aeropuerto nuevo = leerAeropuerto((JSONObject) arreglo.get(0));
			aeropuertos.agregar(nuevo);
			System.out.println(nuevo);
			cargarVuelosActuales(nuevo.getCodigo(),nuevo,Vuelo.LLEGANDO);
			cargarVuelosActuales(nuevo.getCodigo(),nuevo,Vuelo.SALIENDO);
			return nuevo;
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public Iterator<Aeropuerto> darAeropuertos() {
		return aeropuertos.iterator();
	}

	public Iterator<Vuelo> darVuelos() {
		return vuelos.iterator();
	}

	@Override
	public Aeropuerto eliminarAeropuerto(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int consultarCalificacion(String codigo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean ActualizarCalificacion(int valor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Vuelo> consultarVuelosPorEstado(String constante,
			String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Vuelo> buscarVuelosPorCalificacion(int calificacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aeropuerto darMayorRetraso() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aeropuerto darMenorRetraso() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Aeropuerto> buscarPorIndice() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void guardarCentral() throws Exception{
		File f = new File(RUTA_ARCHIVO_SERIALIZADO);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(getInstance());
		oos.close();
		System.out.println("Guardo");
	}
	
	public Iterator<Vuelo> consultarVuelos(Calendar c, String codigo, String tipo,int nHora) throws IOException {
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
			Vuelo nuevo = leerVuelo(actual,tipo);
			listaVuelos.agregar(nuevo);
			System.out.println("cargado vuelo: " + nuevo.getNumero() + tipo);
		}
		
		return listaVuelos.iterator();
	}

	@Override
	//TODO
	public Iterator<Fecha> consultarFechas() {
		return null;
	}
	public static void main(String[] args) throws Exception {
		CentralDeVuelos central = getInstance();
		central.cargarAeropuertos();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		int hora = c.get(Calendar.HOUR_OF_DAY);
		System.out.println(day + "/" + month + "/" + year + "/" + hora);
	//	guardarCentral();
	}

}
