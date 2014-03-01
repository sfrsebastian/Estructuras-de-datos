package testListaEncadenada;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ListaEncadenada.ListaEncadenada;
import junit.framework.TestCase;

public class TestListaEncadenada extends TestCase{

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * Es la lista que hace las pruebas
	 */
	private ListaEncadenada<String> listaPrueba;
	
	//------------------------------------------
	// Setup Scenarios
	//------------------------------------------
	
	private void setupScenario1(){
		listaPrueba = new ListaEncadenada<String>();
		
		String e1 = "Laura";
		String e2 = "Carmen";
		String e3 = "Maria";
		String e4 = "Pedro";
		String e5 = "Jose";
		
		listaPrueba.agregar(e1);
		listaPrueba.agregar(e2);
		listaPrueba.agregar(e3);
		listaPrueba.agregar(e4);
		listaPrueba.agregar(e5);
	}
	
	private void setupScenario2(){
		listaPrueba = new ListaEncadenada<String>();
		
		String e1 = "Laura";
		
		listaPrueba.agregar(e1);
	}
	
	private void setupScenario3(){
		listaPrueba = new ListaEncadenada<String>();
	}
	
	//------------------------------------------
	// Test Scenarios
	//------------------------------------------
	
	public void testAgregarElemento(){
		setupScenario1();
		
		String t1 = "Laura";
		String t2 ="Maria";
		String t3 = "Pedro";
		
		//Prueba para agregar el primer elemento
		assertNotNull("No se agrego correctamente", listaPrueba.buscar(t1));
		
		//Prueba buscar elemento en la mitad
		assertNotNull("No se agrego correctamente", listaPrueba.buscar(t2));
		assertNotNull("No se agrego correctamente", listaPrueba.buscar(t3));
		
		listaPrueba.agregar("hola");
		assertNotNull("Se debio agregar un nuevo elemento", listaPrueba.buscar("hola"));
		
		assertNull("No se debio poder anadir null", listaPrueba.agregar(null));
	}
	
	public void testAgregarElemento2(){
		setupScenario2();
		
		String t1 = "Laura";
		String t2 ="Maria";
		String t3 = "Pedro";
		
		listaPrueba.agregar(t1);
		listaPrueba.agregar(t2);
		listaPrueba.agregar(t3);
		
		assertEquals("No se agrego bien el ultimo elemento", t3,listaPrueba.darUltimo());
		listaPrueba.eliminar(t3);
		
		String d = "a";
		
		listaPrueba.agregar(d);
		assertEquals("Se debio anadir el ultimo elemento", d, listaPrueba.darUltimo());
		
		listaPrueba.agregar("q");
		listaPrueba.agregar("r");
		
		assertNotNull("El elemento se debe encontrar", listaPrueba.buscar("r"));
		assertNotNull("El elemento se debe encontrar", listaPrueba.buscar("a"));
		
	}
	
	public void testBuscarElemento(){
		setupScenario1();

		String t1 = "Laura";
		String t2 ="Maria";
		String t3 = "Pedro";
		
		//Prueba para buscar el primer elemento
		assertNotNull("No se agrego correctamente", listaPrueba.buscar(t1));
		
		//Prueba buscar elemento en la mitad
		assertNotNull("No se agrego correctamente", listaPrueba.buscar(t2));
		assertNotNull("No se agrego correctamente", listaPrueba.buscar(t3));
		
		//Prueba para no encontrar elemento
		assertNull("No se debio encontrar elemento", listaPrueba.buscar("a"));
	}
	
	public void testDarArreglo(){
		setupScenario1();
		
		Object[] arreglo = listaPrueba.darArreglo();
		String e1 = (String) arreglo[0];
		String e2 = (String) arreglo[2];
		
		//Primera posicion del arreglo
		assertEquals("El arreglo no esta correctamente configurado", "Laura", e1);
		
		//Mitad
		assertEquals("El arreglo no esta correctamente configurado", "Maria", e2);
		
		listaPrueba.agregar("b");
		listaPrueba.agregar("b");
		
		Object[] enfermeras = listaPrueba.darArreglo();
		
		String e3 = (String)enfermeras[5];
		//Final del arreglo
		assertEquals("El arreglo no esta correctamente configurado", "b", e3);		
	}
	
	public void testDarLongitud(){
		setupScenario1();
		
		assertEquals("La longitud de la lista es incorrecta", 5, listaPrueba.darLongitud());
		
		listaPrueba.agregar("b");
		listaPrueba.agregar("b");
		
		listaPrueba.eliminar("b");
		
		//Nueva longitud
		assertEquals("La longitud de la lista es incorrecta", 6, listaPrueba.darLongitud());
	}
	
	public void testDarLongitud2(){
		setupScenario2();
		
		String t1 = "Laura";
		assertNotNull("Se debio eliminar un elemento", listaPrueba.eliminar(t1));
		
		listaPrueba.agregar(null);
		listaPrueba.agregar(null);
		
		assertEquals("La longitud de la lista debe ser 0", 0, listaPrueba.darLongitud());
	}
	
	public void testEliminarElemento(){
		setupScenario1();

		String t1 = "Laura";
		String t2 ="Maria";
		String t3 = "Pedro";		
		
		//No hay elemento que eliminar
		assertNull("No se debio eliminar ningun nodo", listaPrueba.eliminar("a"));
		
		//Eliminar el primer elemento de la lista
		assertNotNull("Se debio eliminar un elemento", listaPrueba.eliminar(t1));
		assertNull("No se elimino correctamente el elemento", listaPrueba.buscar(t1));
		
		//Eliminar el segundo elemento de la lista
		assertNotNull("Se debio eliminar el elemento", listaPrueba.eliminar(t3));
		assertNull("No se elimino correctamente el elemento", listaPrueba.buscar(t3));

		//Eliminar en la mitad
		listaPrueba.agregar("b");
		listaPrueba.agregar("b");
		assertNotNull("se debio eliminar un elemento", listaPrueba.eliminar("b"));
		
		String t5 = "b";
		String b1 = (String) listaPrueba.eliminar(t5);
		
		//Eliminar ultimo elemento
		assertEquals("Se debio eliminar el ultimo elemento", t5,b1);
		assertNull("No se elimino correctamente el elemento", listaPrueba.buscar("b"));
	}
	
	public void testEliminarElemento2(){
		setupScenario2();
		
		String t1 = "Laura";
		
		//Eliminar cuando solo hay un elemento
		assertNotNull("Se debio eliminar un elemento", listaPrueba.eliminar(t1));
		assertEquals("La longitud debe ser 0", 0, listaPrueba.darLongitud());
		
		//Eliminar cuando no hay elemento
		assertNull("No se debio eliminar ningun elemento", listaPrueba.eliminar(t1));
		
	}
	public void testIterator(){
		//Prueba con iterador vacio
		setupScenario3();
		Iterator<String> iterator = listaPrueba.iterator();
		assertFalse(iterator.hasNext());
		try{
			iterator.next();
			fail("no debe pasar por aca");
		}
		catch(NoSuchElementException e){
			
		}
		try{
			iterator.remove();
			fail("no debe pasar por aca");
		}
		catch(NoSuchElementException e){
			
		}
		
		//Prueba iterador lleno
		setupScenario1();
		iterator = listaPrueba.iterator();
		assertEquals("Debe interarse la misma cantidad de elementos", iterarTamano(iterator),5);
		
		iterator = listaPrueba.iterator();
		iterator.next();
		iterator.remove();//Elimina el primer elemento de la lista
		assertNull("El elemento eliminado no debe existir",listaPrueba.buscar("Laura"));
		assertFalse("Los elementos no deben ser el mismo", "Laura"==listaPrueba.darArreglo()[0]);
		assertEquals("La longitud de la lista debe disminuir", listaPrueba.darLongitud(),4);
		iterator.next();
		iterator.next();
		iterator.remove();//Elimina el tercer elemento de la lista
		assertNull("El elemento eliminado no debe existir",listaPrueba.buscar("Maria"));
		assertFalse("Los elementos no deben ser el mismo", "Maria"==listaPrueba.darArreglo()[1]);
		assertEquals("La longitud de la lista debe disminuir", listaPrueba.darLongitud(),3);
		iterator.next();
		iterator.next();
		iterator.remove();//Elimina el ultimo elemento de la lista
		assertNull("El elemento eliminado no debe existir",listaPrueba.buscar("Jose"));
		assertFalse("Los elementos no deben ser el mismo", "Jose"==listaPrueba.darArreglo()[1]);
		assertEquals("La longitud de la lista debe disminuir", listaPrueba.darLongitud(),2);
		
	}
	private int iterarTamano(Iterator iterator){
		int contador = 0;
		while(iterator.hasNext()){
			iterator.next();
			contador++;
		}
		return contador;
	}
	
}
