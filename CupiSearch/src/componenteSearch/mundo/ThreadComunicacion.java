package componenteSearch.mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

import co.edu.uniandes.cupi2.datos.cmpRegistro.compartido.Mensaje;
import co.edu.uniandes.cupi2.datos.cmpRegistro.compartido.Protocolo;
import CompresorHuffman.DatosCaracter;
import CompresorHuffman.TextoComprimido;
import ListaEncadenada.ListaEncadenada;

public class ThreadComunicacion extends Thread {
	private static final String NOMBRE_INTEGRANTE1 = "Sebastian Florez" ;
	private static final String NOMBRE_INTEGRANTE2 = "Felipe Otalora" ;
	private static final String CODIGO1 = "201313903";
	private static final String CODIGO2 = "201224949";
	private static final String IP = "157.253.236.58";
	private static final int PUERTO = 9898;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String UID;
	
	public ThreadComunicacion() throws Exception{
		socket = new Socket(IP,PUERTO);
		out = new ObjectOutputStream( socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream( ));
	
	}
	
	public ThreadComunicacion(String nUID) throws Exception{
		socket = new Socket(IP,PUERTO);
		out = new ObjectOutputStream( socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream( ));
		UID = nUID;
	}

	public boolean registrarCategorias(TextoComprimido comprimir) throws Exception {
		boolean respuesta = false;
		out.writeObject(Protocolo.REGISTRAR_CATEGORIAS+";"+UID);
		if(in.readObject().equals(Protocolo.OK))
			out.writeObject(crearMensaje(comprimir));
		
		if(in.readObject().equals(Protocolo.OK))
			respuesta = true;
		
		return respuesta;
	}

	private Mensaje crearMensaje(TextoComprimido comprimir) {
		Mensaje respuesta = new Mensaje();
		respuesta.asignarUID(UID);
		respuesta.asignarMensaje(comprimir.getMensaje().getBytes());
		respuesta.asignarNumeroDeBitsMensaje(comprimir.getMensaje().getLongitud());
		respuesta.asignarCodificacion(codigoAString(comprimir.getTabla()));
		return respuesta;
	}

	private String codigoAString(DatosCaracter[] tabla) {
		String respuesta = "";
		for (DatosCaracter datosCaracter : tabla) {
			respuesta+=datosCaracter.toString()+"_";
		}
		respuesta = respuesta.substring(0,respuesta.length()-1);
		return respuesta;
	}

	public Iterator<Categoria> recuperarCategorias() throws Exception{
		ListaEncadenada<Categoria> lista = new ListaEncadenada<Categoria>();
		out.writeObject(Protocolo.OBTENER_CATEGORIAS_USUARIO+";"+UID);
		try{
			Mensaje mensaje = (Mensaje) in.readObject();
			DatosCaracter[] datos = convertirADatos(mensaje.darCodificacion());
			TextoComprimido comprimido = new TextoComprimido(mensaje.darNumeroDeBitsMensaje(),mensaje.darMensaje(),datos);	
			String descomprimido = comprimido.descomprimir();
			//falta crear iterador de categorias
			String[] split = descomprimido.split("]");
			for (String actual : split) {
				int punto = actual.indexOf(":");
				int espacio = actual.indexOf("_");
				String nombre = actual.substring(punto,espacio);
				punto = actual.indexOf(":", punto);
				espacio = actual.indexOf("_", espacio);
				String descripcion =  actual.substring(punto,espacio);
				Categoria nueva = new Categoria(nombre,descripcion);
				int curlyA = actual.indexOf("{")+1;
				int curlyB = actual.indexOf("}");
				String recursos = actual.substring(curlyA,curlyB);
				String[] recs = actual.split("_");
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

	private DatosCaracter[] convertirADatos(String codigos) {
		String[] split = codigos.split("_");
		DatosCaracter[] respuesta = new DatosCaracter[split.length];
		for (int i = 0; i<split.length;i++) {
			String caracter = split[i];
			DatosCaracter nuevo = new DatosCaracter(caracter.charAt(0),caracter.substring(1,caracter.length()));
			respuesta[i] = nuevo;
		}
		return respuesta;
	}

	public String registrarAplicacion() throws Exception {
		out.writeObject(Protocolo.REGISTRAR_APLICACION+";"+CODIGO1+";"+NOMBRE_INTEGRANTE1+";"+CODIGO2+";"+NOMBRE_INTEGRANTE2);
		UID =  (String) in.readObject();
		System.out.println(UID);
		return UID;
	}

}
