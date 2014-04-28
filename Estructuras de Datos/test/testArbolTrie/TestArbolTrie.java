package testArbolTrie;

import java.util.Iterator;

import ArbolTrie.ArbolTrie;
import junit.framework.TestCase;

public class TestArbolTrie extends TestCase {
	
	private ArbolTrie<String> arbol;

	private void setupScenario1(){
		arbol = new ArbolTrie<String>();
	}
	
	private void setupScenario2(){
		arbol = new ArbolTrie<String>();
		arbol.agregar("casa", "Felipe");
		arbol.agregar("casa", "Maria");
		arbol.agregar("casa", "Rosa");
		arbol.agregar("cosa", "Life-Savers");
		arbol.agregar("cerro", "Flora");
		arbol.agregar("cerafin", "Fauna");
		arbol.agregar("avion", "a1");
		arbol.agregar("avioneta", "e1");
		arbol.agregar("avion", "a2");
		arbol.agregar("avioneta", "e2");
	}
	
	public void testAgregarFecha(){
		setupScenario1();
		
		arbol.agregar("2014/04/26", "Hola");
		Iterator i1 = arbol.buscar("2014/04/26");
		//System.out.println(i1.next());
		arbol.agregar("2014/04/26", "Perro");
		arbol.agregar("2014/04/23", "Casa");
		Iterator i2 = arbol.buscar("2014/04/23");
		//System.out.println(i2.next());
		arbol.agregar("2014/04/25", "Arbol");
		Iterator i3 = arbol.buscar("2014/04/25");
		//arbol.agregar("2014/04/28", "Llanta");
		Iterator i4 = arbol.buscar("2014/04/28");
		//System.out.println(i4.next());
		arbol.agregar("2014/04/30", "Mama");
		Iterator i5 = arbol.buscar("2014/04/30");
		//System.out.println(i5.next());
		
		Iterator d = arbol.buscarXPrefijo("");
		while(d.hasNext()){
			//System.out.println(d.next());
			d.next();
		}
	}
	
	public void testDarPalabras(){
		setupScenario1();
		
		String pals[] = {"ajiaco","arevalo","arreboles","caja","goma","otra","zapato"};
		
		arbol.agregar("ajiaco", "hola");
		arbol.agregar("ajiaco", "perro");
		arbol.agregar("goma", "mama");
		arbol.agregar("arevalo", "oso");
		arbol.agregar("arreboles", "casa");
		arbol.agregar("caja", "queso");
		arbol.agregar("zapato", "perri");
		arbol.agregar("otra", "nuevo");
		
		Iterator<String> i = arbol.darPalabras();
		int d = 0;
		while(i.hasNext()){
			assertEquals("Deberia ser otro valor", pals[d], i.next());
			d++;
		}
		
		arbol.eliminar("ajiaco");
		Iterator<String> f = arbol.darPalabras();
		int q = 1;
		while(f.hasNext()){
			assertEquals("Deberia ser otro valor", pals[q], f.next());
			q++;
		}	
	}
	
	public void testAgregar(){
		setupScenario1();
		
		//Caso: arbol, vacio, se agregan todas las letras
		arbol.agregar("casa", "Felipe");
		assertEquals("Se debe encontrar el elemento", "Felipe", (arbol.buscar("casa")).next());
		//System.out.println((arbol.buscar("casa")).next());
		//Caso: primera letra repetida, se agregan los elementos
		arbol.agregar("cosa", "Life-Savers");
		assertEquals("Se debe encontrar el elemento", "Life-Savers", (arbol.buscar("cosa")).next());
		//Caso: Primera letra repetida y elementos son menores que los mayores
		arbol.agregar("cerro", "Flora");
		//Caso: Primera letra repetida, se debe actualizar el menor
		arbol.agregar("cerafin", "Fauna");
		//Caso: Primera letra es menor e hijo
		arbol.agregar("avion", "camara");
		assertEquals("Se debe encontrar el elemento", "camara", (arbol.buscar("avion")).next());
		arbol.agregar("avioneta", "Dexter");
		assertEquals("Se debe encontrar el elemento", "Dexter", (arbol.buscar("avioneta")).next());
	}
	
	public void testBuscar(){
		setupScenario2();
		
		String[] noms = {"Felipe","Maria","Rosa","Fauna"};
		
		Iterator<String> i = arbol.buscar("casa");	
		assertEquals("Se debe encontrar el elemento", noms[0], i.next());
		assertEquals("Se debe encontrar el elemento", noms[1], i.next());
		assertEquals("Se debe encontrar el elemento", noms[2], i.next());
		
		assertNull("No se debe haber encontrado el iterador", arbol.buscar("hola"));
		try {
			String elem = (arbol.buscar("cas")).next();
			fail();
		} catch (Exception e) {
			// Deberia pasar por aqui
		}
		
		Iterator<String> e = arbol.buscar("avion");
		int d = 1;
		while(e.hasNext()){
			assertEquals("Se deberia encontrar el elemento","a"+d,e.next());
			d++;
		}
		Iterator<String> f = arbol.buscar("avioneta");
		int g = 1;
		while(e.hasNext()){
			assertEquals("Se deberia encontrar el elemento","e"+d,e.next());
			g++;
		}
	}
	
	public void testEliminar(){
		setupScenario2();
		
		arbol.eliminar("casa");
		try {
			String elem = (arbol.buscar("casa")).next();
			fail();
		} catch (Exception e) {
			// Deberia pasar por aqui
			assertFalse("No deberia tener el prefijo",arbol.contienePrefijo("casa"));
			assertTrue("Deberia tener el prefijo", arbol.contienePrefijo("ca"));
		}
		
		arbol.eliminar("cosa");
		try{
			String elem = (arbol.buscar("cosa")).next();
			fail();
		}catch(Exception e){
			// Deberia pasar por aqui
			assertFalse("No deberia tener el prefijo",arbol.contienePrefijo("cosa"));
			assertTrue("Deberia tener el prefijo", arbol.contienePrefijo("c"));
		}
		
		arbol.eliminar("avion");
		assertTrue("El prefijo deberia existir",arbol.contienePrefijo("avion"));
		arbol.eliminar("avioneta");
		assertFalse("No deberia existir", arbol.contienePrefijo("avioneta"));
	}
	
	public void testContienePrefijo(){
		setupScenario2();
		
		//Caso una sola letra
		assertFalse("No deberia tener el prefijo", arbol.contienePrefijo("x"));
		//Caso contiene el primer hijo
		assertTrue("Deberia tener el prefijo", arbol.contienePrefijo("a"));
		//Caso de una palabra incompleta
		assertTrue("Deberia tener el prefijo", arbol.contienePrefijo("avio"));
		
		assertTrue("Deberia tener el prefijo", arbol.contienePrefijo("ceraf"));
		
		arbol.eliminar("casa");
		//Se elimina casa, ca sigue existiendo
		assertFalse("No deberia tener el prefijo",arbol.contienePrefijo("casa"));
		assertTrue("Deberia tener el prefijo", arbol.contienePrefijo("ca"));
		
		arbol.eliminar("cosa");
		assertTrue("La letra c deberia seguir existiendo", arbol.contienePrefijo("c"));
		assertFalse("No deberia tener el prefijo",arbol.contienePrefijo("cosa"));
	}
	
	public void testBuscarXPrefijo(){
		setupScenario2();
		
		String noms[] = {"Fauna","Felipe","Flora","Life-Savers","Maria","Rosa"};
		
		Iterator<String> i = arbol.buscarXPrefijo("c");
		int d = 0;
		
		while(i.hasNext()){
			assertEquals("El elemento se debe encontrar", noms[d], i.next());
			d++;
		}
	}
	
	public void testBuscarElemento(){
		setupScenario2();
		
		assertEquals("Deberia encontrar el elemento","Felipe", arbol.buscarElemento("casa", "Felipe"));
	}
}
