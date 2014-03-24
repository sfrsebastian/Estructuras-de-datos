package testArbolAVL;

import ArbolAVl.ArbolBinarioAVLOrdenado;
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

	public void testAVL(){
		setupScenario2();

		arbol.agregar("Bob");
		assertTrue("Debe ser AVL", arbol.esAVL());
		arbol.agregar("Karen");
		assertTrue("Debe ser AVL", arbol.esAVL());
		arbol.agregar("Alan");
		assertTrue("Debe ser AVL", arbol.esAVL());

		arbol.agregar("Laura");
		assertTrue("Debe ser AVL", arbol.esAVL());
		//Test caso rotar a la izquierda
		arbol.agregar("Manuel");

		assertTrue("Debe ser AVL", arbol.esAVL());

		arbol.eliminar("Manuel");
		assertTrue("Debe ser AVL", arbol.esAVL());
		//Test caso rotar a la derecha
		arbol.agregar("Camila");
		assertTrue("Debe ser AVL", arbol.esAVL());

		//Test caso rotar a la derecha-izquierda
		arbol.agregar("Caliche");
		assertTrue("Debe ser AVL", arbol.esAVL());

		setupScenario2();

		arbol.agregar("Bob");
		assertTrue("Debe ser AVL", arbol.esAVL());
		arbol.agregar("Karen");
		assertTrue("Debe ser AVL", arbol.esAVL());
		arbol.agregar("Alan");
		assertTrue("Debe ser AVL", arbol.esAVL());

		arbol.agregar("Abe");

		//Caso rotar derecha
		arbol.agregar("Aaron");
		assertTrue("Debe ser AVL", arbol.esAVL());

		arbol.eliminar("Aaron");
		assertTrue("Debe ser AVL", arbol.esAVL());
		//Rotacion izquierda
		arbol.agregar("Ajiaco");
		assertTrue("Debe ser AVL", arbol.esAVL());

		//Rotacion izquierda derecha
		arbol.agregar("Aslan");
		assertTrue("Debe ser AVL", arbol.esAVL());

	}
	
}