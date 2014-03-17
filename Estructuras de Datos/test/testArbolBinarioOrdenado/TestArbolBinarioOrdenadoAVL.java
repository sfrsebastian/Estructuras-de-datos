package testArbolBinarioOrdenado;

import ArbolBinOrdenado.ArbolBinarioAVLOrdenado;
import ArbolBinOrdenado.ArbolBinarioOrdenado;
import junit.framework.TestCase;

public class TestArbolBinarioOrdenadoAVL extends TestCase {
	
	private ArbolBinarioAVLOrdenado<String> arbol;
	
	private void setupScenario1(){
		arbol = new ArbolBinarioAVLOrdenado<String>();
		
		arbol.agregar("Karen");
		arbol.agregar("Bob");
		arbol.agregar("Tom");
		arbol.agregar("Alan");
		arbol.agregar("Ellen");
		arbol.agregar("Wendy");
		arbol.agregar("Yandia");
	}
	
	private void setupScenario2(){
		arbol = new ArbolBinarioAVLOrdenado<String>();	
	}
	
	public void testArbolAVL(){
		setupScenario2();
		
		arbol.agregar("Carlos");
		assertEquals("Se debe encontrar el elemento", "Carlos", arbol.buscar("Carlos"));
		
		arbol.agregar("Karen");
		arbol.agregar("Laura");
		
	}
	
	public void testAgregarElemento(){
		setupScenario1();

		//Agregar raiz repetida
		assertFalse("No se debe permitir agregar elementos repetidos",arbol.agregar("Karen"));
		
		//Agregar niveles mas abajo
		assertFalse("No se debe permitir agregar elementos repetidos",arbol.agregar("Ellen"));
		
		//Agregar null
		assertFalse("No se debe permitir agregar elementos nulos",arbol.agregar(null));
		
		//Agregar correcto
		assertTrue("Se debio agregar el elemento", arbol.agregar("Carlos"));
		
		//Agregar arbol vacia
		setupScenario2();
		assertTrue("Se debio agregar el elemento", arbol.agregar("Carlos"));
		assertEquals("Se debio encontrar el elemento agregado","Carlos",arbol.buscar("Carlos"));
	}
}
