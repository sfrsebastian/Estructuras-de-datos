package testLista;

import Lista.Lista;
import junit.framework.TestCase;

public class TestLista extends TestCase{

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * Es la lista que hace las pruebas
	 */
	private Lista listaPrueba;
	
	//------------------------------------------
	// Setup Scenarios
	//------------------------------------------
	
	private void setupScenario1(){
		listaPrueba = new Lista<Enfermera>();
		
		Enfermera e1 = new Enfermera("Laura", 20, "2.2");
		Enfermera e2 = new Enfermera("Carmen", 42, "7.8");
		Enfermera e3 = new Enfermera("Maria", 40, "3.5");
		Enfermera e4 = new Enfermera("Pedro", 45, "7.6");
		Enfermera e5 = new Enfermera("Jose", 32, "1.0");
		
		listaPrueba.agregar(e1);
		listaPrueba.agregar(e2);
		listaPrueba.agregar(e3);
		listaPrueba.agregar(e4);
		listaPrueba.agregar(e5);
	}
	
	private void setupScenario2(){
		listaPrueba = new Lista<Enfermera>();
		
		Enfermera e1 = new Enfermera("Laura", 20, "2.2");
		
		listaPrueba.agregar(e1);
	}
	
	//------------------------------------------
	// Test Scenarios
	//------------------------------------------
	
	public void testAgregarElemento(){
		setupScenario1();
		
		Enfermera t1 = new Enfermera("Laura", 20, "2.2");
		Enfermera t2 = new Enfermera("Maria", 40, "3.5");
		Enfermera t3 = new Enfermera("Pdero", 45, "7.6");
		
		//Prueba para agregar el primer elemento
		assertNotNull("No se agrego correctamente", listaPrueba.buscar(t1));
		
		//Prueba buscar elemento en la mitad
		assertNotNull("No se agrego correctamente", listaPrueba.buscar(t2));
		assertNotNull("No se agrego correctamente", listaPrueba.buscar(t3));
		
		listaPrueba.agregar(new Enfermera("a", 1, "hola"));
		assertNotNull("Se debio agregar un nuevo elemento", listaPrueba.buscar(new Enfermera("a", 1, "hola")));
	}
	
	public void testBuscarElemento(){
		setupScenario1();

		Enfermera t1 = new Enfermera("Laura", 20, "2.2");
		Enfermera t2 = new Enfermera("Maria", 40, "3.5");
		Enfermera t3 = new Enfermera("Pdero", 45, "7.6");
		
		//Prueba para buscar el primer elemento
		assertNotNull("No se agrego correctamente", listaPrueba.buscar(t1));
		
		//Prueba buscar elemento en la mitad
		assertNotNull("No se agrego correctamente", listaPrueba.buscar(t2));
		assertNotNull("No se agrego correctamente", listaPrueba.buscar(t3));
		
		//Prueba para no encontrar elemento
		assertNull("No se debió encontrar elemento", listaPrueba.buscar(new Enfermera("a", 1, "cedulaFalsa")));
	}
	
	public void testDarLongitud(){
		setupScenario1();
		
		assertEquals("La longitud de la lista es incorrecta", 5, listaPrueba.darLongitud());
		
		listaPrueba.agregar(new Enfermera("b", 1, "a"));
		listaPrueba.agregar(new Enfermera("b", 2, "c"));
		
		listaPrueba.eliminar(new Enfermera("b", 1, "a"));
		
		//Nueva longitud
		assertEquals("La longitud de la lista es incorrecta", 6, listaPrueba.darLongitud());
	}
	
	public void testEliminarElemento(){
		setupScenario1();

		Enfermera t1 = new Enfermera("Laura", 20, "2.2");
		Enfermera t2 = new Enfermera("Maria", 40, "3.5");
		Enfermera t3 = new Enfermera("Pdero", 45, "7.6");		
		
		//No hay elemento que eliminar
		assertNull("No se debio eliminar ningun nodo", listaPrueba.eliminar(new Enfermera("a", 2, "cedulaFalsa")));
		
		//Eliminar el primer elemento de la lista
		assertNotNull("Se debio eliminar un elemento", listaPrueba.eliminar(t1));
		assertNull("No se elimino correctamente el elemento", listaPrueba.buscar(t1));
		
		//Eliminar el segundo elemento de la lista
		assertNotNull("Se debio eliminar el elemento", listaPrueba.eliminar(t3));
		assertNull("No se elimino correctamente el elemento", listaPrueba.buscar(t3));

		//Eliminar en la mitad
		listaPrueba.agregar(new Enfermera("b", 1, "a"));
		listaPrueba.agregar(new Enfermera("b", 2, "c"));
		assertNotNull("se debio eliminar un elemento", listaPrueba.eliminar(new Enfermera("b", 1, "a")));
		
		Enfermera t5 = new Enfermera("b", 2, "c");
		Enfermera b1 = (Enfermera) listaPrueba.eliminar(t5);
		
		//Eliminar ultimo elemento
		assertEquals("Se debio eliminar el ultimo elemento", t5.getCedula(),b1.getCedula());
		assertNull("La enfermera no se elimino correctamente", listaPrueba.buscar(new Enfermera("b", 2, "c")));
	}
	
	public void testEliminarElemento2(){
		setupScenario2();
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
