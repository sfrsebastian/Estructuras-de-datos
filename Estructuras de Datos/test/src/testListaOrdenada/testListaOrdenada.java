package src.testListaOrdenada;

import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.TestCase;
import ListaOrdenada.ListaOrdenada;

public class testListaOrdenada extends TestCase {
	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * La lista que hace las pruebas
	 */
	private ListaOrdenada<String> listaPrueba;
	
	//------------------------------------------
	// Setup Scenarios
	//------------------------------------------
	private void setupScenario1(){
		listaPrueba = new ListaOrdenada<String>();
		
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
		listaPrueba = new ListaOrdenada<String>();
	}
	
	//------------------------------------------
	// Test Scenarios
	//------------------------------------------
	public void testAgregarElemento(){
		setupScenario2();
		
		String t1 = "Ana";
		String t2 = "Santiago";
		String t3 = "Juan";
		
		//Prueba agregar null
		assertNull("No se debio haber agregado null", listaPrueba.agregar(null));
		
		//Prueba para agregar el primer elemento
		assertEquals("No se agrego correctamente", t1, listaPrueba.agregar(t1));
		assertEquals("No se agrego correctamente", 1, listaPrueba.darLongitud());
		
		setupScenario1();
		//Prueba agregar de primeras
		listaPrueba.agregar(t1);
		assertEquals("No se agrego correctamente", t1, listaPrueba.darElementos()[0]);
		
		//Prueba agregar mitad
		listaPrueba.agregar(t3);
		assertEquals("No se agrego correctamente", t3, listaPrueba.darElementos()[3]);
		
		//Prueba agregar final
		listaPrueba.agregar(t2);
		assertEquals("No se agrego correctamente", t2, listaPrueba.darElementos()[7]);
		try{
			String bla = (String) listaPrueba.darElementos()[8];
			fail("No debe pasar por aca");
		}
		catch(Exception e){
			
		}
		
		//Prueba agregar repetido
		listaPrueba.agregar(t3);
		assertEquals("No se agrego correctamente", t3, listaPrueba.darElementos()[3]);
		assertEquals("No se agrego correctamente", t3, listaPrueba.darElementos()[4]);
	}
	
	public void testBuscarElemento(){		
		//Prueba busqueda lista vacia
		setupScenario2();
		assertNull("Se espera null en una lista vacia", listaPrueba.buscar("Juan"));
		
		//Prueba agregar null
		assertNull("No se debio haber agregado null", listaPrueba.buscar(null));
		
		//Prueba busqueda lista llena
		setupScenario1();
		assertEquals("Debio encontrar otra persona","Laura",listaPrueba.buscar("Laura"));
		
		//Prueba busqueda lista llena fallida
		assertNull("Se espera null", listaPrueba.buscar("Daniel"));
	}
	
	public void testDarLongitud(){
		//Prueba lista vacia
		setupScenario2();
		assertEquals("Se espera 0",0,listaPrueba.darLongitud());
		
		//Prueba lista llena
		setupScenario1();
		assertEquals("Se espera una longitud de 5", 5, listaPrueba.darLongitud());
		
		//Prueba eliminado
		listaPrueba.eliminar("Laura");
		assertEquals("Se espera una longitud de 4", 4, listaPrueba.darLongitud());
		
		//Prueba agregado
		listaPrueba.agregar("Laura");
		assertEquals("Se espera una longitud de 5", 5, listaPrueba.darLongitud());
	}
	
	public void testEliminar(){

		String t1 = "Ana";
		String t2 = "Santiago";
		String t3 = "Juan";	
		
		setupScenario2();
		//Prueba eliminar null
		assertNull("No se debio haber eliminado null", listaPrueba.eliminar(null));
		
		//Prueba no hay elementos en la lista
		assertNull("No se debio eliminar ningun nodo", listaPrueba.eliminar("Pedro"));
		
		setupScenario1();
		//Prueba eliminar elemento no existente
		assertNull("No se elimino correctamente el elemento", listaPrueba.eliminar("t1"));
		
		//Prueba eliminar primer elemento de la lista
		String primero = (String) listaPrueba.darElementos()[0];
		listaPrueba.eliminar("Carmen");
		assertFalse("No deben ser iguales los primeros elementos", primero==listaPrueba.darElementos()[0]);
		assertNull("No debe encontrar al elemento eliminado", listaPrueba.buscar("Carmen"));
		assertEquals("El primer elemento debe ser Jose", "Jose", listaPrueba.darElementos()[0]);
		assertEquals("La longitud de la lista debe disminuir", 4, listaPrueba.darLongitud());

		//Eliminar en la mitad
		listaPrueba.eliminar("Maria");
		assertTrue("El ultimo elemento debe ser diferente debe ser pedro", listaPrueba.darElementos()[2] == "Pedro");
		assertNull("La persona no debio haber sido encontrada", listaPrueba.buscar("Maria"));
		try{
			String bla = (String) listaPrueba.darElementos()[3];
			fail("No debe pasar por aca");
		}
		catch(Exception e){
			
		}
		//Eliminar ultimo elemento
		listaPrueba.eliminar("Pedro");
		assertEquals("El tamano de la lista debe ser de 2", 2, listaPrueba.darLongitud());
		assertNull("La persona eliminada no debio haber sido encontrada", listaPrueba.buscar("Pedro"));
		try{
			String bla = (String) listaPrueba.darElementos()[3];
			fail("No debe pasar por aca");
		}
		catch(Exception e){
			
		}
	}
	
	public void testDarElementos(){
		setupScenario1();
		assertEquals("El tamano de la lista no fue el mismo", 5, listaPrueba.darElementos().length);
		assertEquals("No se retorno a la persona esperada", "Carmen", listaPrueba.darElementos()[0]);
		assertEquals("No se retorno a la persona esperada", "Jose", listaPrueba.darElementos()[1]);
		assertEquals("No se retorno a la persona esperada", "Laura", listaPrueba.darElementos()[2]);
		assertEquals("No se retorno a la persona esperada", "Maria", listaPrueba.darElementos()[3]);
		assertEquals("No se retorno a la persona esperada", "Pedro", listaPrueba.darElementos()[4]);
	}
	
	public void testIterator(){
		//Prueba con iterador vacio
		setupScenario2();
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
		assertEquals("Debe interarse la misma cantidad de elementos", iterarTamaño(iterator),5);
		
		iterator = listaPrueba.iterator();
		iterator.next();
		try{
			iterator.remove();//intenta eliminar el primero
			fail("No debe pasar por aca");
		}
		catch(UnsupportedOperationException e){
			
		}
		
		iterator.next();//Elimina el segundo elemento de la lista
		iterator.remove();
		assertNull("El elemento eliminado no debe existir",listaPrueba.buscar("Jose"));
		assertFalse("Los elementos no deben ser el mismo", "Jose"==listaPrueba.darElementos()[1]);
		assertEquals("La longitud de la lista debe disminuir", listaPrueba.darLongitud(),4);
		iterator = listaPrueba.iterator();
		assertEquals("Debe interarse la misma cantidad de elementos", iterarTamaño(iterator),4);
		
		
	}
	private int iterarTamaño(Iterator iterator){
		int contador = 0;
		while(iterator.hasNext()){
			iterator.next();
			contador++;
		}
		return contador;
	}
	
}
