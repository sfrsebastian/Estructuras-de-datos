package componenteSearch.mundo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;

import ListaEncadenada.ListaEncadenada;
import co.edu.uniandes.cupi2.datos.cmpRegistro.compartido.Protocolo;

public class test {
	private static final String NOMBRE_INTEGRANTE1 = "Sebastian Florez" ;
	private static final String NOMBRE_INTEGRANTE2 = "Felipe Otalora" ;
	private static final String CODIGO1 = "201313903";
	private static final String CODIGO2 = "201224949";
	private static final String IP = "157.253.236.58";
	private static final int PUERTO = 9898;
	private static Socket socket;
	private static ObjectInputStream in;
	private static ObjectOutputStream out;
	private static String UID;
	
	public static void main(String[] args) throws Exception{
//		UID = "4622312e-7c98-4d73-b969-53fb409c170c";
//		socket = new Socket(IP,PUERTO);
//		out = new ObjectOutputStream( socket.getOutputStream());
//		in = new ObjectInputStream(socket.getInputStream( ));
//		//registrarAplicacion();
//		out.writeObject(Protocolo.OBTENER_CATEGORIAS_USUARIO+Protocolo.SEPARATOR+UID);
//		Object object = in.readObject();
//		System.out.println(object);
		Iterator<Categoria> i = recuperarCategorias();
	}
	public void registrarAplicacion() throws Exception {
		out.writeObject(Protocolo.REGISTRAR_APLICACION+Protocolo.SEPARATOR+CODIGO1+"Protocolo.SEPARATOR"+NOMBRE_INTEGRANTE1+"Protocolo.SEPARATOR"+CODIGO2+Protocolo.SEPARATOR+NOMBRE_INTEGRANTE2);
		UID =  (String) in.readObject();
		System.out.println(UID);
		//return UID;
	}
	public static Iterator<Categoria> recuperarCategorias() throws Exception{
		ListaEncadenada<Categoria> lista = new ListaEncadenada<Categoria>();
		String descomprimido = "[nombre:hello wrold~descripcion:TODO DESC~recursos{http://a.thumbs.redditmedia.com/pquIEanzD-YD8WE6.png~http://www.redditstatic.com/kill.png}]~[nombre:hola chao~descripcion:TODO DESC~recursos{http://c.thumbs.redditmedia.com/48U3QomNs-DW43DV.png~http://c.thumbs.redditmedia.com/ckbE4tDmf6xcPaVz.png}]";
		try{
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
					boolean b = nueva.agregarRecurso(nuevo);
					System.out.println(b);
				}
				lista.agregar(nueva);
			}
		}	
			catch(ClassCastException e){
				System.out.println("No hay categorias en el servidor");
			}
			return lista.iterator();
	}

}
