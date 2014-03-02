package mundo;

import java.io.Serializable;
import java.util.Iterator;

import ListaEncadenada.ListaEncadenada;


public class Notas implements Serializable {
	public final static int NO_APLICA = -1;
	private int sociales;
	private int quimica;
	private int fisica;
	private int biologia;
	private int filosofia;
	private int matematicas;
	private int lenguaje;
	private int ingles;
	private int geografia;
	private int historia;
	ListaEncadenada <Integer> lista;
	
	public Notas(int nSociales,int nQuimica,int nFisica, int nBiologia, int nFilosofia,int nMatematicas,int nLenguaje, int nIngles, int nGeografia, int nHistoria){
		lista = new ListaEncadenada<Integer>();
		sociales = nSociales;
		quimica = nQuimica;
		fisica = nQuimica;
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

	public int getSociales() {
		return sociales;
	}

	public int getQuimica() {
		return quimica;
	}

	public int getFisica() {
		return fisica;
	}

	public int getBiologia() {
		return biologia;
	}

	public int getFilosofia() {
		return filosofia;
	}

	public int getMatematicas() {
		return matematicas;
	}

	public int getLenguaje() {
		return lenguaje;
	}

	public int getIngles() {
		return ingles;
	}

	public int getGeografia() {
		return geografia;
	}

	public int getHistoria() {
		return historia;
	}
	
	public double darPromedio(){
		int total = lista.darLongitud();
		int suma = 0;
		Iterator<Integer> iterador = lista.iterator();
		while(iterador.hasNext()){
			int materia = iterador.next();
			if(materia != NO_APLICA){
				suma += materia;
			}
			else{
				total--;
			}
		}
		return (double)suma/total;
	}
}
