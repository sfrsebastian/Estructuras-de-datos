package componenteSearch.mundo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;

import co.edu.uniandes.cupi2.datos.cmpRegistro.compartido.Mensaje;
import co.edu.uniandes.cupi2.datos.cmpRegistro.compartido.Protocolo;
import CompresorHuffman.DatosCaracter;
import CompresorHuffman.TextoComprimido;
import ListaEncadenada.ListaEncadenada;

public class ThreadComunicacion extends Thread {
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	/**
	 * Nombre integrante 1
	 */
	private static final String NOMBRE_INTEGRANTE1 = "Sebastian Florez";
	
	/**
	 * Nombre integrante 2
	 */
	private static final String NOMBRE_INTEGRANTE2 = "Felipe Otalora";
	
	/**
	 * Codigo del estudiante 1
	 */
	private static final String CODIGO1 = "201313903";
	
	/**
	 * Codigo del estudiante 2
	 */
	private static final String CODIGO2 = "201224949";
	
	/**
	 * Ip del servidor de persistencia
	 */
	private static final String IP = "157.253.236.58";
	
	/**
	 * Puerto del servidor
	 */
	private static final int PUERTO = 9898;
	
	/**
	 * Socket para conexion con el servidor
	 */
	private Socket socket;
	
	/**
	 * Flujo de entrada de informacion
	 */
	private ObjectInputStream in;
	
	/**
	 * Flujo de salida de informacion
	 */
	private ObjectOutputStream out;
	
	/**
	 * UID de la aplicacion
	 */
	private String UID;

	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	/**
	 * Crea un nuevo thread para conexion con el servidor
	 * @throws Exception
	 */
	public ThreadComunicacion() throws Exception{
		socket = new Socket(IP,PUERTO);
		out = new ObjectOutputStream( socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream( ));
		System.out.println("Conectado a servidor");
	}

	/**
	 * Crea un nuevo thread para conexion con el servidor
	 * @param nUID
	 * @throws Exception
	 */
	public ThreadComunicacion(String nUID) throws Exception{
		socket = new Socket(IP,PUERTO);
		out = new ObjectOutputStream( socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream( ));
		UID = nUID;
		System.out.println("Conectado a servidor");
	}

	/**
	 * Registra las categorias en el servidor a partir del protocolo establecido
	 * @param comprimir El texto comprimido con algoritmo Huffman
	 * @return true si envio correctamente la informacion
	 * @throws Exception
	 */
	public boolean registrarCategorias(TextoComprimido comprimir) throws Exception {
		boolean respuesta = false;
		out.writeObject(Protocolo.REGISTRAR_CATEGORIAS+Protocolo.SEPARATOR+UID);
		
		Object ob = in.readObject();
		System.out.println(ob);
		if(ob.equals(Protocolo.OK)){
			Mensaje mensaje = crearMensaje(comprimir);
			out.writeObject(mensaje);
			System.out.println("OK");
		}

		Object ob2 = in.readObject();
		System.out.println(ob2);
		if(ob2.equals(Protocolo.OK)){
			respuesta = true;
			System.out.println("OK");
		}	
		return respuesta;
	}

	/**
	 * Metodo auxiliar que crea el mensaje a partir del texto comprimido huffman
	 * @param comprimir
	 * @return
	 */
	private Mensaje crearMensaje(TextoComprimido comprimir) {
		Mensaje respuesta = new Mensaje();
		respuesta.asignarUID(UID);
		respuesta.asignarMensaje(comprimir.getMensaje().getBytes());
		respuesta.asignarNumeroDeBitsMensaje(comprimir.getMensaje().getLongitud());
		respuesta.asignarCodificacion(codigoAString(comprimir.getTabla()));
		return respuesta;
	}

	/**
	 * Metodo auxiliar que convierte los datos de los caracter a string acorde al protocolo
	 * @param tabla la tabla cond atos de caracter
	 * @return
	 */
	private String codigoAString(DatosCaracter[] tabla) {
		String respuesta = "";
		for (int i = 0; i<tabla.length;i++) {
			DatosCaracter datosCaracter = tabla[i];
			if(datosCaracter !=null ){
				respuesta+=datosCaracter.toString()+"~";
			}
		}
		respuesta = respuesta.substring(0,respuesta.length()-1);
		return respuesta;
	}

	/**
	 * Metodo que recupera las categorias del servidor
	 * @return Iterador con las categorias recuperadas
	 * @throws Exception
	 */
	public Iterator<Categoria> recuperarCategorias() throws Exception{
		ListaEncadenada<Categoria> lista = new ListaEncadenada<Categoria>();
		out.writeObject(Protocolo.OBTENER_CATEGORIAS_USUARIO+Protocolo.SEPARATOR+UID);
		try{
			Mensaje mensaje = (Mensaje) in.readObject();
			DatosCaracter[] datos = convertirADatos(mensaje.darCodificacion());
			TextoComprimido comprimido = new TextoComprimido(mensaje.darNumeroDeBitsMensaje(),mensaje.darMensaje(),datos);	
			String descomprimido = comprimido.descomprimir();
			//falta crear iterador de categorias
			String[] split = descomprimido.split("]");
			for (String actual : split) {
				int punto = actual.indexOf(":")+1;
				int espacio = actual.indexOf("~",2);
				String nombre = actual.substring(punto,espacio);
				punto = actual.indexOf(":", punto)+1;
				espacio = actual.indexOf("~", espacio+1);
				String descripcion =  actual.substring(punto,espacio);
				Categoria nueva = new Categoria(nombre,descripcion);
				int curlyA = actual.indexOf("{")+1;
				int curlyB = actual.indexOf("}");
				String recursos = actual.substring(curlyA,curlyB);
				String[] recs = recursos.split("~");
				for (String rec: recs) {
					Recurso nuevo = new Recurso(rec);
					nueva.agregarRecurso(nuevo);
				}
				lista.agregar(nueva);
			}
		}	
		catch(ClassCastException e){
			System.out.println("No hay categorias en el servidor");
		}
		return lista.iterator();
	}

	/**
	 *Convierte a Datos de caracter un string de caracteres
	 * @param codigos Los codigos obtenidos del servidor
	 * @return Los datos de caracter
	 */
	private DatosCaracter[] convertirADatos(String codigos) {
		String[] split = codigos.split("~");
		DatosCaracter[] respuesta = new DatosCaracter[split.length];
		for (int i = 0; i<split.length;i++) {
			String caracter = split[i];
			if(caracter.length() != 1){
				DatosCaracter nuevo = new DatosCaracter(caracter.charAt(0),caracter.substring(1,caracter.length()));
				respuesta[i] = nuevo;
			}
		}
		return respuesta;
	}

	/**
	 * Registra la aplicacion en el servidor
	 * @return El UID unico de el compoennte
	 * @throws Exception
	 */
	public String registrarAplicacion() throws Exception {
		out.writeObject(Protocolo.REGISTRAR_APLICACION+Protocolo.SEPARATOR+CODIGO1+Protocolo.SEPARATOR+NOMBRE_INTEGRANTE1+Protocolo.SEPARATOR+CODIGO2+Protocolo.SEPARATOR+NOMBRE_INTEGRANTE2);
		UID =  (String) in.readObject();
		System.out.println(UID);
		return UID;
	}

}
