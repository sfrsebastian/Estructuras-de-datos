package testCola;

import java.util.NoSuchElementException;

import Cola.Cola;
import junit.framework.TestCase;

public class TestCola extends TestCase {
	private Cola<String> cola;
	private void setupScenario1(){
		cola = new Cola<String>();
	}
	
	private void setupScenario2(){
		cola = new Cola<String>();
		cola.agregar("Juan");
		cola.agregar("Sebastian");
		cola.agregar("Felipe");
		cola.agregar("Andres");
	}

	public void testCola(){
		//Cola vacia
		setupScenario1();
		try{
			cola.dar();
			fail("No debe pasar por aca");
		}
		catch(NoSuchElementException e){
		}
		
		//Cola llena
		setupScenario2();
		assertEquals("Debe retorna el primero agregado","Juan",cola.dar());
		assertEquals("Debe retorna el segundo agregado","Sebastian",cola.dar());
		assertEquals("Debe retorna el tercero agregado","Felipe",cola.dar());
		assertEquals("Debe retorna el cuarto agregado","Andres",cola.dar());
		try{
			cola.dar();
			fail("No debe pasar por aca");
		}
		catch(NoSuchElementException e){
			
		}
		
		//Agregar nuevo
		cola.agregar("Camilo");
		assertEquals("Debe retorna el agregado","Camilo",cola.dar());
	}
}
