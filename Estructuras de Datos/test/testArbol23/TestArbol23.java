package testArbol23;

import java.util.Iterator;

import Arbol23.Arbol23;
import ListaEncadenada.ListaEncadenada;
import junit.framework.TestCase;

public class TestArbol23 extends TestCase {
	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * El arbol de la prueba
	 */
	private Arbol23<Integer> arbol;

	/**
	 * Lista con los elementos agregados al arbol
	 */
	private ListaEncadenada<Integer>agregados;

	//--------------------
	//ESCENARIOS DE PRUEBA
	//--------------------
	/**
	 * Inicializa el arbol y la lista
	 */
	private void setupScenario1(){
		arbol = new Arbol23<Integer>();
		agregados = new ListaEncadenada<Integer>();
	}

	/**
	 * Inicializa el arbol con 1000 valores al azar
	 * @throws Exception
	 */
	private void setupScenario2() throws Exception{
		arbol = new Arbol23<Integer>();
		agregados = new ListaEncadenada<Integer>();
		for(int i = 0; i <1000;i++){
			int generado = (int)(Math.random()*1000);
			if(arbol.buscar(generado) == null){
				agregados.agregar(generado);
				arbol.agregar(generado);
			}
		}
	}

	//--------------------
	//PRUEBAS
	//--------------------
	/**
	 * Verifica que se agregan correctamente 1000 elementos al azar <b>
	 * Verifica que el peso del arbol aumenta.
	 * @throws Exception
	 */
	public void testAgregar()throws Exception{
		setupScenario1();
		for(int i = 0; i <1000;i++){
			int generado = (int)(Math.random()*1000);
			int peso = arbol.darPeso();
			if(arbol.buscar(generado) == null){
				assertTrue("El elemento se debio haber agregado",arbol.agregar(generado));
				assertEquals("El elemento se debio haber encontrado", (Integer)generado,arbol.buscar(generado));
				assertEquals("El peso del arbol debio aumentar",peso+1,arbol.darPeso());
			}
		}
	}

	/**
	 * Verifica que se eliminan correctamente 1000 elementos al azar <b>
	 * Verifica que el peso del arbol diminuyo.
	 * @throws Exception
	 */
	public void testEliminar()throws Exception{
		setupScenario2();
		Iterator<Integer> i = agregados.iterator();
		while(i.hasNext()){
			int siguiente = i.next();
			int peso = arbol.darPeso();
			assertEquals("El elemento debio haber sido el mismo",(Integer)siguiente, arbol.eliminar(siguiente));
			assertNull("El elemento no se debio haber encontrado", arbol.buscar(siguiente));
			assertEquals("El peso del arbol debio disminuir",peso-1,arbol.darPeso());
		}
	}

	/**
	 * Agrega y elimina distintos elementos al azar <b>
	 * Verifica que los elementos resultantes se encuentren
	 * @throws Exception
	 */
	public void testBuscar()throws Exception{
		setupScenario1();
		for(int i = 0; i <1000;i++){
			int generado = (int)(Math.random()*1000);
			if(arbol.buscar(generado) == null){
				agregados.agregar(generado);
				assertTrue("Se debio agregar el elemento al arbol",arbol.agregar(generado));
				assertEquals("El elemento se debio haber encontrado", (Integer)generado,arbol.buscar(generado));
			}
		}
		for(int i = 0; i < 500; i++){
			int generado = (int)(Math.random()*1000);
			if(arbol.buscar(generado) != null){
				agregados.eliminar(generado);
				assertEquals("El elemento eliminado debio ser el mismo",(Integer)generado,arbol.eliminar(generado));
			}
		}
		Iterator<Integer> i = agregados.iterator();
		while(i.hasNext()){
			int siguiente = i.next();
			assertEquals("El elemento debio haberse encontrado",(Integer)siguiente, arbol.buscar(siguiente));
		}
	}

	/**
	 * Verifica que al buscar un elemento que no existe el resultado es null
	 * @throws Exception
	 */
	public void testBuscar2() throws Exception{
		setupScenario1();
		setupScenario1();
		for(int i = 1; i<=1000;i++){
			arbol.agregar(i);
		}
		assertNull("El elemento no se debio haber encontrado", arbol.buscar(1001));
	}

	/**
	 * Verifica que el peso del arbol aumenta y  disminuye en la medida que se agregan y eliminan elementos
	 * @throws Exception
	 */
	public void testPeso() throws Exception{
		setupScenario1();
		for(int i = 1; i<=1000;i++){
			arbol.agregar(i);
		}
		assertEquals("El peso debe ser 1000", 1000, arbol.darPeso());
		for(int i = 0; i<=700;i++){
			arbol.eliminar(i);
		}
		assertEquals("El peso debe ser 300", 300, arbol.darPeso());
	}	

	/**
	 * Verifica que los elementos agregado sean retornados en orden.
	 * @throws Exception
	 */
	public void testInorden() throws Exception{
		setupScenario1();
		for(int i = 0; i<=1000;i++){
			arbol.agregar(i);
		}
		Iterator<Integer> i = arbol.recorrerInorden();
		int j = 0;
		while(i.hasNext()){
			int actual = i.next();
			assertEquals("El elemento debe ser el mismo", j, actual);
			j++;
		}
	}
}
