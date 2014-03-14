package testArbolBinarioOrdenado;

import java.util.Iterator;

import ArbolBinOrdenado.ArbolBinarioAVLOrdenado;
import ArbolBinOrdenado.ArbolBinarioOrdenado;
import junit.framework.TestCase;

public class TestArbolBinarioOrdenado extends TestCase{

	private ArbolBinarioOrdenado<String> arbol;
	
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
	
	public void testDarPeso(){
		setupScenario2();
		
		arbol.agregar("Felipe");		
		
		//un solo elemento
		assertEquals("El tamano es incorrecto",1, arbol.darPeso());
		
		//varios elementos 
		arbol.agregar("Sebastian");
		arbol.agregar("Maria");
		arbol.agregar("Carlos");
		
		assertEquals("El tamano es incorrecto",4, arbol.darPeso());
		
		arbol.eliminar("Sebastian");
		arbol.eliminar("Maria");
		
		assertEquals("El tamano es incorrecto",2, arbol.darPeso());
		
		arbol.agregar("Juan");
		//arbol.agregar("Felipe");
		arbol.agregar("Elsa");
		
		assertEquals("El tamano es incorrecto",4, arbol.darPeso());
			
	}
	
	public void testEliminarElemento1(){
		setupScenario2();
		
		arbol.agregar("Juliana");
		
		//un solo elemento
		assertTrue("Se debio eliminar el elemento", arbol.eliminar("Juliana"));
		assertNull("El elemento ya no debe estar en el arbol", arbol.buscar("Juliana"));
		
		arbol.agregar("Carlos");
		arbol.agregar("Jose");
		
		assertTrue("Se debio eliminar el elemento",arbol.eliminar("Jose"));
		assertNull("El elemento ya no debe estar en el arbol", arbol.buscar("Jose"));
		
		setupScenario2();
		for (int i = 0; i < 21; i++) {
			arbol.agregar("b" + i);
			arbol.agregar("c" + i);
			arbol.agregar("a" + i);
		}
		
		for (int i = 0; i < 21; i++) {
			arbol.agregar("e" + i);
			arbol.agregar("d" + i);
			arbol.agregar("g" + i);
		}
		
		assertEquals("El tamaño debe ser 126", 126, arbol.darPeso());
		//Eliminar raiz
		assertTrue("La raiz del arbol se debio haber eliminado", arbol.eliminar("b0"));
		assertNull("No se debe encontrar la raiz del arbol",arbol.buscar("b0"));
		
		
	}
	
//	public void testEliminarElemento2(){
//		setupScenario1();
//		
//		//Eliminar con solo un sub arbol
//		assertTrue("Se debio eliminar el elemento",arbol.eliminar("Yandia"));
//		assertNull("No se debio encontrar el elemento", arbol.buscar("Yandia"));
//		
//		//Eliminar una hoja
//		assertTrue("Se debio eliminar el elemento",arbol.eliminar("Wendy"));
//		assertNull("No se debio encontrar el elemento", arbol.buscar("Wendy"));
//		
//		assertTrue("Se debio eliminar el elemento", arbol.eliminar("Alan"));
//		assertNull("No se debio encontrar el elemento", arbol.buscar("Alan"));
//		
//		arbol.agregar("Felipe");
//		arbol.agregar("Sebastian");
//		arbol.agregar("Camila");
//		arbol.agregar("Maria");
//		
//		assertTrue("Se debio eliminar el elemento", arbol.eliminar("Sebastian"));
//		assertNull("No se debio encontrar el elemento", arbol.buscar("Sebastian"));
//		
//		assertTrue("Se debio eliminar el elemento",arbol.eliminar("Felipe"));
//		assertNull("No se debio encontrar el elemento", arbol.buscar("Felipe"));
//		
//		arbol.agregar("Carlos");
//		arbol.agregar("Jose");
//		arbol.agregar("Laura");
//		arbol.agregar("Alejandra");
//		
//		assertTrue("Se debio eliminar el elemento",arbol.eliminar("Camila"));
//		assertNull("No se debio encontrar el elemento", arbol.buscar("Camila"));
//		
//		//Eliminar elementos con dos hijos
//		assertEquals("Se debio eliminar el elemento", "Bob", arbol.eliminar("Bob"));
//		assertNull("No se debio encontrar el elemento", arbol.eliminar("Bob"));
//		
//		//Elementos sobrantes: Karen, Tom, Ellen, Maria, Carlos, Jose, Laura, Alejandra
//		//Busquemos si todos siguen en el arbol
//		assertNotNull("Se debe encontrar el elemento", arbol.buscar("Karen"));
//		assertNotNull("Se debe encontrar el elemento", arbol.buscar("Tom"));
//		assertNotNull("Se debe encontrar el elemento", arbol.buscar("Ellen"));
//		assertNotNull("Se debe encontrar el elemento", arbol.buscar("Maria"));
//		assertNotNull("Se debe encontrar el elemento", arbol.buscar("Carlos"));
//		assertNotNull("Se debe encontrar el elemento", arbol.buscar("Jose"));
//		assertNotNull("Se debe encontrar el elemento", arbol.buscar("Laura"));
//		assertNotNull("Se debe encontrar el elemento", arbol.buscar("Alejandra"));
//
//		assertEquals("El tamano del arbol es incorrecto", 8, arbol.darPeso());
//		
//		assertEquals("Se debio eliminar el elemento", "Ellen", arbol.eliminar("Ellen"));
//		assertNull("No se debio encontrar el elemento", arbol.eliminar("Ellen"));
//		
//		assertEquals("Se debio eliminar el elemento", "Tom", arbol.eliminar("Tom"));
//		assertNull("No se debio encontrar el elemento", arbol.eliminar("Tom"));
//		
//		assertEquals("Se debio eliminar el elemento", "Carlos", arbol.eliminar("Carlos"));
//		assertNull("No se debio encontrar el elemento", arbol.eliminar("Carlos"));
//	}
	
	public void testIteradorInorden(){
		setupScenario1();
		
		String[] nombres = {"Alan","Bob","Ellen","Karen","Tom","Wendy","Yandia"};
		
		Iterator i = arbol.recorrerInorden();
		int j = 0;
		
		while(i.hasNext()){
			String elem = (String)i.next();
			assertEquals("El orden es incorrecto, deberia seguir: " + nombres[j], nombres[j] ,elem);
			j++;
		}
		
		arbol.agregar("Carlos");
		arbol.agregar("Elsa");
		arbol.agregar("Juan");
		arbol.agregar("Maria");
		
		arbol.eliminar("Bob");
		
		String noms[] = {"Alan","Carlos","Ellen","Elsa","Juan","Karen","Maria","Tom","Wendy","Yandia"};
		int p = 0;
		Iterator o = arbol.recorrerInorden();
		while (o.hasNext()) {
			String nomb = (String) o.next();
			assertEquals("El orden es incorrecto, deberia seguir: "+ noms[p], noms[p], nomb);
			p++;
		}
	}
	
	public void testIteradorPreorden(){
		setupScenario1();
		
		String[] nombres = {"Karen","Bob","Alan","Ellen","Tom","Wendy","Yandia"};
		
		Iterator<String> i = arbol.recorrerPreorden();
		int j = 0;
		
		while(i.hasNext()){
			String elem = i.next();
			assertEquals("El orden es incorrecto, deberia seguir: " + nombres[j], nombres[j] ,elem);
			j++;
		}
		
		arbol.agregar("Carlos");
		arbol.agregar("Elsa");
		arbol.agregar("Juan");
		arbol.agregar("Maria");
		
		arbol.eliminar("Bob");
		
		String noms[] = {"Karen","Alan","Ellen","Carlos","Elsa","Juan","Tom","Maria","Wendy","Yandia"};
		int p = 0;
		Iterator<String> o = arbol.recorrerPreorden();
		while (o.hasNext()) {
			String nomb = (String) o.next();
			assertEquals("El orden es incorrecto, deberia seguir: "+ noms[p], noms[p], nomb);
			p++;
		}
	}
	
	public void testIteradorPosorden(){
		setupScenario1();
		
		String[] nombres = {"Alan","Ellen","Bob","Yandia","Wendy","Tom","Karen"};
		
		Iterator i = arbol.recorrerPosorden();
		int j = 0;
		
		while(i.hasNext()){
			String elem = (String)i.next();
			assertEquals("El orden es incorrecto, deberia seguir: " + nombres[j], nombres[j] ,elem);
			j++;
		}
		
		arbol.agregar("Carlos");
		arbol.agregar("Elsa");
		arbol.agregar("Juan");
		arbol.agregar("Maria");
		
		arbol.eliminar("Bob");
		
		String noms[] = {"Carlos","Juan","Elsa","Ellen","Alan","Maria","Yandia","Wendy","Tom","Karen"};
		int p = 0;
		Iterator o = arbol.recorrerPosorden();
		while (o.hasNext()) {
			String nomb = (String) o.next();
			assertEquals("El orden es incorrecto, deberia seguir: "+ noms[p], noms[p], nomb);
			p++;
		}
	}
	
}
