package testListaOrdenada;

import junit.framework.TestCase;
import ListaOrdenada.ListaOrdenada;

public class testListaOrdenada extends TestCase {
	//------------------------------------------
	// Atributos
	//------------------------------------------
		
	/**
	 * Es la lista que hace las pruebas
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
	}
	
	public void testBuscarElemento(){
		//Prueba busqueda lista vacia
		setupScenario2();
		assertNull("Se espera null en una lista vacia", listaPrueba.buscar("Juan"));
		
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
		//Prueba no hay elementos en la lista
		assertNull("No se debio eliminar ningun nodo", listaPrueba.eliminar("Pedro"));
		
		setupScenario1();
		//Prueba eliminar elemento no existente
		assertNull("No se elimino correctamente el elemento", listaPrueba.eliminar("t1"));
		
		//Prueba eliminar primer elemento de la lista
		String primero = (String) listaPrueba.darElementos()[0];
		System.out.println(listaPrueba.eliminar("Carmen"));;
		for(int i = 0 ; i<listaPrueba.darLongitud();i++){
			System.out.println(listaPrueba.darElementos()[i]);
		}
		assertFalse("No deben ser iguales los primeros elementos",primero==listaPrueba.darElementos()[0]);
		assertNull("No debe encontrar al elemento eliminado", listaPrueba.buscar("Carmen"));
		assertEquals("El primer elemento debe ser Jose", "Jose", listaPrueba.darElementos()[0]);
		assertEquals("La longitud de la lista debe disminuir", 4, listaPrueba.darLongitud());

//		//Eliminar en la mitad
//		listaPrueba.agregar(new Enfermera("b", 1, "a"));
//		listaPrueba.agregar(new Enfermera("b", 2, "c"));
//		assertNotNull("se debio eliminar un elemento", listaPrueba.eliminar(new Enfermera("b", 1, "a")));
//		
//		Enfermera t5 = new Enfermera("b", 2, "c");
//		Enfermera b1 = (Enfermera) listaPrueba.eliminar(t5);
//		
//		//Eliminar ultimo elemento
//		assertEquals("Se debio eliminar el ultimo elemento", t5.getCedula(),b1.getCedula());
//		assertNull("La enfermera no se elimino correctamente", listaPrueba.buscar(new Enfermera("b", 2, "c")));
	}
	
	public void testDarArreglo(){
		setupScenario1();
		
		Object[] arreglo = listaPrueba.darArreglo();
		Enfermera e1 = (Enfermera)arreglo[0];
		Enfermera e2 = (Enfermera)arreglo[2];
		
		//Primera posicion del arreglo
		assertEquals("El arreglo no esta correctamente configurado", "Laura", e1.getNombre());
		
		//Mitad
		assertEquals("El arreglo no esta correctamente configurado", "Maria", e2.getNombre());
		
		listaPrueba.agregar(new Enfermera("b", 1, "a"));
		listaPrueba.agregar(new Enfermera("b", 2, "c"));
		
		Object[] enfermeras = listaPrueba.darArreglo();
		
		Enfermera e3 = (Enfermera)enfermeras[5];
		//Final del arreglo
		assertEquals("El arreglo no esta correctamente configurado", "b", e3.getNombre());		
	}
}
