package testGrafo;

import junit.framework.TestCase;
import Grafo.Grafo;

public class TestGrafo extends TestCase {

	//---------------------------------------
	// Atributos
	//---------------------------------------
	
	/**
	 * El grafo para probar
	 */
	private Grafo<String, String, String> grafo;
	
	//---------------------------------------
	// Escenarios
	//---------------------------------------
	
	private void setupScenario1(){
		grafo = new Grafo<String,String,String>(0);
	}
	
	private void setupScenario2(){
		grafo = new Grafo<String,String,String>(0);
	}
	
	//---------------------------------------
	// Metodos
	//---------------------------------------
	
	public void testDarVertice(){
		setupScenario1();
		
		assertNull("No deberia encontrar un vertice",grafo.darVertice(""));
	}
}
