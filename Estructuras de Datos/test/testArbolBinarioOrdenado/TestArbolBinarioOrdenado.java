package testArbolBinarioOrdenado;

import ArbolBinOrdenado.ArbolBinarioOrdenado;
import junit.framework.TestCase;

public class TestArbolBinarioOrdenado extends TestCase{

	private ArbolBinarioOrdenado arbol;
	
	private void setupScenario1(){
		arbol = new ArbolBinarioOrdenado<String>();
		
		arbol.agregar("Karen");
		arbol.agregar("Bob");
		arbol.agregar("Tom");
		arbol.agregar("Alan");
		arbol.agregar("Ellen");
		arbol.agregar("Wendy");
		arbol.agregar("Yandia");
	}
	
	private void setupScenario2(){
		arbol = new ArbolBinarioOrdenado<String>();
	}
	
	public void testAgregarElemento(){
		setupScenario1();

		//Agregar en la raiz
		assertFalse("No se debe permitir agregar elementos repetidos",arbol.agregar("Karen"));
		
		//Agregar niveles mas abajo
		assertFalse("No se debe permitir agregar elementos repetidos",arbol.agregar("Ellen"));
		assertFalse("No se debe permitir agregar elementos repetidos",arbol.agregar("Wendy"));
		
		//Agregar null
		assertFalse("No se debe permitir agregar elementos nulos",arbol.agregar(null));
		
		assertTrue("Se debio agregar el elemento", arbol.agregar("Carlos"));
	}
	
	public void testBuscarElemento(){
		setupScenario1();
		
		//Buscar la raiz
		assertNotNull("Se debe encontrar el elemento", arbol.buscar("Karen"));
		
		//Buscar dos niveles mas abajo
		assertNotNull("Se debe encontrar el elemento", arbol.buscar("Wendy"));
		assertNotNull("Se debe encontrar el elemento", arbol.buscar("Ellen"));
		arbol.agregar("Carlos");
		assertNotNull("Se debe encontrar el elemento", arbol.buscar("Carlos"));
	}
	
	public void testDarPeso1(){
		setupScenario2();
		
		arbol.agregar("Felipe");		
		
		//un solo elemento
		assertEquals("El tamano es incorrecto",1, arbol.darPeso());
		
		//varios elementos 
		arbol.agregar("Sebastian");
		arbol.agregar("Maria");
		arbol.agregar("Carlos");
		
		assertEquals("El tamano es incorrecto",4, arbol.darPeso());
			
	}
	
	public void testEliminarElemento1(){
		setupScenario2();
		
		arbol.agregar("Juliana");
		
		//un solo elemento
		assertEquals("Se debio eliminar el elemento", "Juliana", arbol.eliminar("Juliana"));
		assertNull("El elemento ya no debe estar en el arbol", arbol.buscar("Juliana"));
		
		arbol.agregar("Carlos");
		arbol.agregar("Jose");
		
		assertNull("No se puede eliminar la raiz con hijos", arbol.eliminar("Carlos"));
		
	}
	
	public void testEliminarElemento2(){
		setupScenario1();
		
		//Eliminar con solo un sub arbol
		assertEquals("Se debio eliminar el elemento", "Yandia", arbol.eliminar("Yandia"));
		assertNull("No se debio encontrar el elemento", arbol.buscar("Yandia"));
		
		//Eliminar una hoja
		assertEquals("Se debio eliminar el elemento", "Wendy", arbol.eliminar("Wendy"));
		assertNull("No se debio encontrar el elemento", arbol.buscar("Wendy"));
		
		assertEquals("Se debio eliminar el elemento", "Alan", arbol.eliminar("Alan"));
		assertNull("No se debio encontrar el elemento", arbol.buscar("Alan"));
		
		arbol.agregar("Felipe");
		arbol.agregar("Sebastian");
		arbol.agregar("Camila");
		arbol.agregar("Maria");
		
		assertEquals("Se debio eliminar el elemento", "Sebastian", arbol.eliminar("Sebastian"));
		assertNull("No se debio encontrar el elemento", arbol.eliminar("Sebastian"));
		
		assertEquals("Se debio eliminar el elemento", "Felipe", arbol.eliminar("Felipe"));
		assertNull("No se debio encontrar el elemento", arbol.eliminar("Felipe"));
		
		arbol.agregar("Carlos");
		arbol.agregar("Jose");
		arbol.agregar("Laura");
		arbol.agregar("Alejandra");
		
		assertEquals("Se debio eliminar el elemento", "Camila", arbol.eliminar("Camila"));
		assertNull("No se debio encontrar el elemento", arbol.eliminar("Camila"));
		
	}
	
}
