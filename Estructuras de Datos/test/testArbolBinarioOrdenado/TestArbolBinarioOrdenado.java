package testArbolBinarioOrdenado;

import java.util.Iterator;

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
	
	public void testBuscarElemento(){
		setupScenario1();
		
		//Buscar la raiz
		assertEquals("Se debe encontrar el elemento","Karen", arbol.buscar("Karen"));
		
		//Buscar dos niveles mas abajo
		assertEquals("Se debe encontrar el elemento","Wendy", arbol.buscar("Wendy"));
		assertEquals("Se debe encontrar el elemento","Ellen", arbol.buscar("Ellen"));
		
		//Buscar arbol vacio
		setupScenario2();
		assertNull("No se debio encontrar el elemento agregado",arbol.buscar("Carlos"));
	}
	
	public void testDarPeso(){
		//Arbol vacio
		setupScenario2();
		assertEquals("El tamano es incorrecto",0, arbol.darPeso());
		
		//un solo elemento
		arbol.agregar("Felipe");		
		assertEquals("El tamano es incorrecto",1, arbol.darPeso());
		
		//varios elementos 
		arbol.agregar("Sebastian");
		arbol.agregar("Maria");
		arbol.agregar("Carlos");
		assertEquals("El tamano es incorrecto",4, arbol.darPeso());
		
		//Elementos eliminados
		arbol.eliminar("Sebastian");
		arbol.eliminar("Maria");
		assertEquals("El tamano es incorrecto",2, arbol.darPeso());
	}
	
	public void testEliminarElemento(){
		//un solo elemento
		setupScenario2();
		arbol.agregar("Juliana");
		assertTrue("Se debio eliminar el elemento", arbol.eliminar("Juliana"));
		assertNull("El elemento ya no debe estar en el arbol", arbol.buscar("Juliana"));
		
		//varios elementos
		arbol.agregar("Carlos");
		arbol.agregar("Jose");
		
		assertTrue("Se debio eliminar el elemento",arbol.eliminar("Jose"));
		assertNull("El elemento ya no debe estar en el arbol", arbol.buscar("Jose"));
		
		//Muchos elementos
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
		
		assertEquals("El tamanio debe ser 126", 126, arbol.darPeso());
		assertTrue("El elemento haber eliminado", arbol.eliminar("b0"));
		assertNull("No se debe encontrar el elemento eliminado",arbol.buscar("b0"));
		assertEquals("El tamanio debe ser 125", 125, arbol.darPeso());
		assertTrue("Se debio eliminar d15",arbol.eliminar("d15"));
		assertNull("No se debio encontrar d15",arbol.buscar("d15"));
		assertEquals("El tamanio debe ser 124", 124, arbol.darPeso());
	}
	
	public void testIteradorInorden(){
		setupScenario1();
		String[] nombres = {"Alan","Bob","Ellen","Karen","Tom","Wendy","Yandia"};
		Iterator<String> i = arbol.recorrerInorden();
		int j = 0;
		while(i.hasNext()){
			String elem = (String)i.next();
			assertEquals("El orden es incorrecto, deberia seguir: " + nombres[j], nombres[j] ,elem);
			j++;
		}
		
		//Se agregan elementos
		arbol.agregar("Carlos");
		arbol.agregar("Elsa");
		arbol.agregar("Juan");
		arbol.agregar("Maria");
		arbol.eliminar("Bob");
		String noms[] = {"Alan","Carlos","Ellen","Elsa","Juan","Karen","Maria","Tom","Wendy","Yandia"};
		int p = 0;
		Iterator<String> o = arbol.recorrerInorden();
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
		
		//Se agregan elementos
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
		Iterator<String> i = arbol.recorrerPosorden();
		int j = 0;
		while(i.hasNext()){
			String elem = (String)i.next();
			assertEquals("El orden es incorrecto, deberia seguir: " + nombres[j], nombres[j] ,elem);
			j++;
		}
		
		//Se agregan elementos
		arbol.agregar("Carlos");
		arbol.agregar("Elsa");
		arbol.agregar("Juan");
		arbol.agregar("Maria");
		arbol.eliminar("Bob");
		String noms[] = {"Carlos","Juan","Elsa","Ellen","Alan","Maria","Yandia","Wendy","Tom","Karen"};
		int p = 0;
		Iterator<String> o = arbol.recorrerPosorden();
		while (o.hasNext()) {
			String nomb = (String) o.next();
			assertEquals("El orden es incorrecto, deberia seguir: "+ noms[p], noms[p], nomb);
			p++;
		}
	}
	
	public void  testRecorrerNiveles(){
		setupScenario1();
		String[] nombres = {"Karen","Bob","Tom","Alan","Ellen","Wendy","Yandia"};
		Iterator<String> i = arbol.recorrerNiveles();
		int j = 0;
		while(i.hasNext()){
			String elem = i.next();
			assertEquals("El orden es incorrecto, deberia seguir: " + nombres[j], nombres[j] ,elem);
			j++;
		}
		//Se agregan elementos
		arbol.agregar("Carlos");
		arbol.agregar("Elsa");
		arbol.agregar("Juan");
		arbol.agregar("Maria");
		arbol.eliminar("Bob");
		String noms[] = {"Karen","Alan","Tom","Ellen","Maria","Wendy","Carlos","Elsa","Yandia","Juan"};
		int p = 0;
		Iterator<String> o = arbol.recorrerNiveles();
		while (o.hasNext()) {
			String nomb = (String) o.next();
			assertEquals("El orden es incorrecto, deberia seguir: "+ noms[p], noms[p], nomb);
			p++;
		}
	}
	public void testAltura(){
		setupScenario1();
		assertEquals("La altura debe ser 4",4,arbol.darAltura());
		
		//Se elimina elemento mas bajo
		arbol.eliminar("Yandia");
		assertEquals("La altura debe ser 3",3,arbol.darAltura());
		
		//Se debe mantener la altura
		arbol.eliminar("Wendy");
		assertEquals("La altura debe ser 3",3,arbol.darAltura());
		
		//Arbol vacio
		setupScenario2();
		assertEquals("La altura debe ser 0",0,arbol.darAltura());
	}
}
