package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import Arbol23.Arbol23;
import ArbolTrie.ArbolTrie;

public class CentralDeVuelos implements ICentralDeVuelos{
	public final static String SALIENDO = "Saliendo";
	public final static String LLEGANDO = "Llegando";
	private static final String URLAEROPUERTOS = "https://api.flightstats.com/flex/airports/rest/v1/json/active?appId=3723b96f&appKey=d6e053700776ffb5b91cd46f8c88722b";
	public final static String RUTA_ARCHIVO_SERIALIZADO = "/Users/FelipeOtalora/Desktop/CentralDeVuelos.dat";
	public static final String IDENTIFICADORES = "appId=3723b96f&appKey=d6e053700776ffb5b91cd46f8c88722b&";
	/**
	 * 
	 */
	private static final long serialVersionUID = -2434025803582670357L;

	private Arbol23<Aeropuerto> aeropuertos;
	private ArbolTrie<Aerolinea> aerolineas;
	private Arbol23<Fecha> fechas;
	private Arbol23<Vuelo> vuelos;
	private static CentralDeVuelos instancia = null;
	private CentralDeVuelos(){
		aeropuertos = new Arbol23<Aeropuerto>();
		aerolineas = new ArbolTrie<Aerolinea>();
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
				//ServerConfig config = ServerConfigLocator.locate( );
				//File dataDir = config.getServerDataDir();
				File tmp = new File("RUTA_ARCHIVO_SERIALIZADO");
//				System.out.println("Nombre=" + tmp.getName( ));
//				System.out.println("Path=" + tmp.getPath( ));
//				System.out.println("Abs. Path=" + tmp.getAbsolutePath( ));
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
	
	public void actualizarDatos(){
		
	}
	
	public JSONObject darJSON(String url) throws IOException {
		//Informacion de aeropuertos
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

	public void cargarAeropuertos() throws Exception{
		JSONObject principal = darJSON(URLAEROPUERTOS);
		JSONArray recibidos= (JSONArray) principal.getJSONArray("airports");
		for(int i = 0; i<50;i++){
			JSONObject actual = recibidos.getJSONObject(i);
			String nombre =  actual.getString("name");
			String ciudad = actual.getString("city");
			String pais = actual.getString("countryName");
			String codigoCiudad = Aeropuerto.NOTIENE;
			try{
				codigoCiudad = actual.getString("cityCode");
			}
			catch(Exception e){
			}
			
			String codigoPais = actual.getString("countryCode");
			String codigo = actual.getString("fs");
			String urlTardanza = actual.getString("delayIndexUrl");
			String[] split = urlTardanza.split("\\?");
			String url = split[0] + "?" + IDENTIFICADORES + split[1];
			JSONObject cargada = darJSON(url);
			JSONArray delay = cargada.getJSONArray("delayIndexes");
			JSONObject solicitar = delay.getJSONObject(0);
			double tardanza = solicitar.getDouble("normalizedScore");
			Aeropuerto nuevo = new Aeropuerto(nombre,ciudad,pais,codigoCiudad,codigoPais,codigo,tardanza);
			aeropuertos.agregar(nuevo);
			System.out.println("agregado " + nombre);
		}
	}
	
	private void cargarVuelos(Aeropuerto a1, Aeropuerto a2){
		
	}
	@Override
	public Aeropuerto agregarAeropuerto(String codigo) {
		// TODO Auto-generated method stub
		return null;
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
	public Iterator<Fecha> consultarFechas() {
		return null;
	}

	@Override
	public Iterator<Aeropuerto> consultarAeropuertos() {
		return aeropuertos.iterator();
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
	public Aerolinea darMayorRetraso() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aerolinea darMenorRetraso() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Aeropuerto> buscarPorIndice() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void guardarCentral() throws Exception{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO_SERIALIZADO));
		oos.writeObject(getInstance());
		oos.close();
		System.out.println("Guardo");
		
	}
	public static void main(String[] args) throws Exception {
		CentralDeVuelos central = getInstance();
		central.cargarAeropuertos();
//		if(central.getInstance().aeropuertos.darPeso() == 0){
//			central.cargarAeropuertos();
//		}
//		guardarCentral();
	}

	
	public Iterator<Vuelo> consultarVuelos(Calendar cal, int horaDia,String codigo, String tipo) {
		Aeropuerto aeropuerto = new Aeropuerto(codigo);
		aeropuerto = aeropuertos.buscar(aeropuerto);
		return null;
		
	}

	public Iterator<Vuelo> darVuelos() {
		return vuelos.iterator();
	}
}
