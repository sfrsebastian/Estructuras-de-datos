package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;

import javafx.util.Duration;
import Lista.Lista;
import ListaOrdenada.ListaOrdenada;



/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Reproductor implements ISoundBox {

	private String autor;
	private String nombre;
	private double bpm;
	private Lista <Canal> canales;
	private ListaOrdenada <Categoria> categorias;
	private Duration duracion;
	private Date fechaCreacion;
	private Reproductor proyectoAbierto;
	private ListaOrdenada<Sample> sonidos;
	private String rutaGuardado;

	public Reproductor(String nAutor, String nNombre, int numCanales){
		autor = nAutor;
		nombre = nNombre;
		bpm = 1.0;
		canales = new Lista <Canal>();
		for(int i = 0; i<numCanales;i++){
			canales.agregar(new Canal());
		}
		categorias = new ListaOrdenada<Categoria>();
		sonidos = new ListaOrdenada <Sample>();
		duracion = new Duration(0.0);
		Calendar c = Calendar.getInstance();
    	Date fechaCreacion = c.getTime();
    	rutaGuardado = "./data/";
    	proyectoAbierto = this;
	}
	
	@Override
	public Reproductor crearProyecto(String autor, String nombre, int numCanales) {
		return new Reproductor(autor, nombre, numCanales);
	}
	
	@Override
	public Sample[] darSonidos(){
		return (Sample[]) sonidos.darElementos();
	}

	/**
	 * 
	 * @param nCanal    c
	 */
	@Override
	public Canal eliminarCanal(Canal nCanal){
		canales.eliminar(nCanal);
		return nCanal;
	}

	/**
	 * 
	 * @param nCategoria    s
	 */
	@Override
	public Categoria eliminarCategoria(Categoria nCategoria){
		categorias.eliminar(nCategoria);
		return nCategoria;
	}

	/**
	 * @param String
	 * 
	 * @param nCategoria
	 */
	@Override
	public ListaOrdenada<Sample> filtrarSonidosPorCategoria(Categoria nCategoria){
		Categoria[] categ = (Categoria[]) categorias.darElementos();
		ListaOrdenada <Sample> filtrados = new ListaOrdenada <Sample>();
		for(int i = 0; i<categ.length;i++){
			Categoria categoriaActual = categ[i];
			Sample[] sonidosCategoria = categoriaActual.darSonidos();
			for(int j = 0; j < sonidosCategoria.length;j++){
				filtrados.agregar(sonidosCategoria[j]);
			}
		}
		return filtrados;
	}

	/**
	 * 
	 * @param nProyecto    p
	 */
	@Override
	public boolean guardarProyecto(){
		File f = new File(rutaGuardado + nombre + ".pr");
		try {
			ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream(f));
			out.writeObject(autor);
			out.writeObject(nombre);
			out.writeObject(bpm);
			out.writeObject(canales);
			out.writeObject(categorias);
			out.writeObject(sonidos);
			out.writeObject(duracion);
			out.writeObject(fechaCreacion);
			out.close();
			return true;
		} 
		catch (IOException e) {
			return false;
		}
	}
	@Override
	public void pausar(){
		
	}

	/**
	 * 
	 * @param nDuracion    d
	 */
	@Override
	public void reproducir(Duration nDuracion){
		
	}
	@Override
	public void stop(){

	}

	/**
	 * 
	 * @param nReproductor    El proyecto a abrir != null
	 */
	@Override
	public void abrirProyecto(File f){
		try{
			ObjectInputStream ois = new ObjectInputStream( new FileInputStream( f ) );
			autor = (String) ois.readObject( );
			nombre = (String) ois.readObject( );
			bpm = (Integer)ois.readObject( );
			canales = (Lista<Canal>) ois.readObject( );
			categorias = (ListaOrdenada<Categoria>) ois.readObject( );
			sonidos = (ListaOrdenada<Sample>) ois.readObject( );
			duracion = (Duration) ois.readObject( );
			fechaCreacion = (Date) ois.readObject( );
	        ois.close( );
		}
		catch(Exception e){
			
		}
        cambiarProyectoActual(this);
	}

	private void cambiarProyectoActual(Reproductor r){
		proyectoAbierto = r;
	}
	/**
	 * 
	 * @param nCanal    c
	 */
	@Override
	public Canal agregarCanal(Canal nCanal){
		canales.agregar(nCanal);
		return nCanal;
	}

	/**
	 * 
	 * @param nCategoria    s
	 */
	@Override
	public Categoria agregarCategoria(Categoria nCategoria){
		categorias.agregar(nCategoria);
		return nCategoria;
	}

	/**
	 * 
	 * @param nSonidos
	 * @param nCanal    c
	 */
	@Override
	public Canal agregarSonidosACanal(Sample[] nSonidos, Canal nCanal){
		for(int i = 0; i<nSonidos.length;i++){
			nCanal.agregarSonido(nSonidos[i]);
		}
		return nCanal;
	}
	@Override
	public Sample agregarSonidoACanal(Sample nSonido, Canal nCanal){
		return nCanal.agregarSonido(nSonido);
	}
	@Override
	public Sample agregarSonidoALibreria(Sample nSonido){
		return sonidos.agregar(nSonido);
	}
	/**
	 * 
	 * @param nCategoria
	 * @param nSonido    sa
	 */
	@Override
	public Sample asignarCategoria(Categoria nCategoria, Sample nSonido){
		return nCategoria.agregarSonido(nSonido);
	}

	/**
	 * 
	 * @param nBpm    d
	 */
	@Override
	public void aumentarBpm(){
		
	}

	public void disminuirBpm(){
		
	}
	/**
	 * 
	 * @param nAutor    autor
	 */
	@Override
	public File buscarProyectoPorAutor(String nAutor){
		File folder = new File(rutaGuardado);
		File[] files = folder.listFiles();
		
		for(int i = 0; i<files.length;i++){
			try{
				ObjectInputStream ois = new ObjectInputStream( new FileInputStream( files[i] ) );
				if(nAutor == (String) ois.readObject( )){
					ois.close();
					return files[i];
				}
			}
			catch(Exception e){
				
			}
			
		}
		return null;
	}

	/**
	 * 
	 * @param nNombre    nombre
	 */
	@Override
	public File buscarProyectoPorNombre(String nNombre){
		File folder = new File("./data/");
		File[] files = folder.listFiles();
		for(int i = 0; i<files.length;i++){
			try{
				ObjectInputStream ois = new ObjectInputStream( new FileInputStream( files[i] ) );
				ois.readObject( );
				if(nNombre == (String) ois.readObject( ) ){
					ois.close();
					return files[i];
				}
			}
			catch(Exception e){
				
			}
			
		}
		return null;
	}

	@Override
	public void editarProyecto(String nNombre, String nAutor) {
		nombre = nNombre;
		autor = nAutor;
	}
}