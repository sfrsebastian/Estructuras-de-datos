package mundo;

import java.io.Serializable;
import java.util.Iterator;

import ListaEncadenada.ListaEncadenada;


public class Notas implements Serializable {
	//Atributos
	private Area sociales;
	private Area quimica;
	private Area fisica;
	private Area biologia;
	private Area filosofia;
	private Area matematicas;
	private Area lenguaje;
	private Area ingles;
	private Area geografia;
	private Area historia;
	
	/**
	 * Lista que almacena las distintas areas
	 */
	ListaEncadenada <Area> lista;
	
	/**
	 * Metodo que crea unas nuevas Notas
	 * @param nSociales
	 * @param nQuimica
	 * @param nFisica
	 * @param nBiologia
	 * @param nFilosofia
	 * @param nMatematicas
	 * @param nLenguaje
	 * @param nIngles
	 * @param nGeografia
	 * @param nHistoria
	 */
	public Notas(Area nSociales,Area nQuimica,Area nFisica, Area nBiologia, Area nFilosofia,Area nMatematicas,Area nLenguaje, Area nIngles, Area nGeografia, Area nHistoria){
		lista = new ListaEncadenada<Area>();
		sociales = nSociales;
		quimica = nQuimica;
		fisica = nFisica;
		biologia = nBiologia;
		filosofia = nFilosofia;
		matematicas = nMatematicas;
		lenguaje = nLenguaje;
		ingles = nIngles;
		geografia = nGeografia;
		historia = nHistoria;
		
		lista.agregar(sociales);
		lista.agregar(quimica);
		lista.agregar(fisica);
		lista.agregar(biologia);
		lista.agregar(filosofia);
		lista.agregar(matematicas);
		lista.agregar(lenguaje);
		lista.agregar(ingles);
		lista.agregar(geografia);
		lista.agregar(historia);
	}

	// Getters de los puntajes de las distintas areas.
	public int getSociales() {
		return sociales.getPuntaje();
	}

	public int getQuimica() {
		return quimica.getPuntaje();
	}

	public int getFisica() {
		return fisica.getPuntaje();
	}

	public int getBiologia() {
		return biologia.getPuntaje();
	}

	public int getFilosofia() {
		return filosofia.getPuntaje();
	}

	public int getMatematicas() {
		return matematicas.getPuntaje();
	}

	public int getLenguaje() {
		return lenguaje.getPuntaje();
	}

	public int getIngles() {
		return ingles.getPuntaje();
	}

	public int getGeografia() {
		return geografia.getPuntaje();
	}

	public int getHistoria() {
		return historia.getPuntaje();
	}
	
	public ListaEncadenada<Area> getLista(){
		return lista;
	}
	
	/**
	 * Retorna el promedio de las materias.
	 * @return
	 */
	public double darPromedio(){
		int total = lista.darLongitud();
		int suma = 0;
		Iterator<Area> iterador = lista.iterator();
		while(iterador.hasNext()){
			Area materia = iterador.next();
			if(materia.getPuntaje() != Area.NO_APLICA){
				suma += materia.getPuntaje();
			}
			else{
				total--;
			}
		}
		return (double)suma/total;
	}

	/**
	 * Metodo que retorna una matriz con el area y su puntaje.
	 * @return
	 */
	public String[][] darMatriz() {
		String[][] datos = new String[lista.darLongitud()][2];
		Iterator i = lista.iterator();
		
		int j = 0;
		
		while(i.hasNext()){
			Area n = (Area) i.next();
			datos[j][0] = n.getArea();
			String puntaje = (n.getPuntaje() == Area.NO_APLICA) ? "No aplica" : "" + n.getPuntaje();
			datos[j][1] = puntaje;
			j++;
		}
		
		return datos;
	}
}
