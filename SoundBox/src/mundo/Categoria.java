package mundo;

import java.io.Serializable;

import ListaOrdenada.ListaOrdenada;


/**
 * @author s.florez10
 * @version 1.0
 * @created 04-Feb-2014 11:09:59 PM
 */
public class Categoria implements Comparable<Categoria>,Serializable {

	private static final String SIN_CATEGORIA = "Sin Categoria";
	private String nombre;
	private ListaOrdenada<Sample> sonidos;

	public Categoria(String nNombre){	
		nombre = nNombre;
		sonidos = new ListaOrdenada<Sample>();
	}

	public Categoria(){
		nombre = SIN_CATEGORIA;
		sonidos = new ListaOrdenada<Sample>();
	}
	
	public String darNombre(){
		return nombre;
	}
	public Sample[] darSonidos(){
		return (Sample[]) sonidos.darElementos();
	}

	public Sample agregarSonido(Sample nSample){
		return sonidos.agregar(nSample);
	}
	
	public Sample eliminarSonido(Sample nSample){
		return sonidos.eliminar(nSample);
	}
	
	@Override
	public int compareTo(Categoria nCategoria) {
		if(nombre.compareTo(nCategoria.darNombre())<0){
			return -1;
		}
		else if(nombre.compareTo(nCategoria.darNombre())>0){
			return 1;
		}
		else{
			return 0;
		}
	}

	public void eliminarDeSonidos() {
		Sample[] lista = (Sample[]) sonidos.darElementos();
		for(int i = 0; i<lista.length;i++){
			lista[i].eliminarCategoria(this);
		}
	}
	
	public String toString(){
		return nombre;
	}

}