package testGrafo;

import java.util.Iterator;

import junit.framework.TestCase;
import Grafo.Camino;
import Grafo.Grafo;
import Grafo.NodoDijkstra;
import Grafo.NodoNivel;
import Grafo.NodoProfundidad;

public class TestGrafo extends TestCase {

	//---------------------------------------
	// Atributos
	//---------------------------------------
	
	/**
	 * El grafo para probar
	 */
	private Grafo<Character,Character,Informacion> grafo;
	
	//---------------------------------------
	// Escenarios
	//---------------------------------------
	
	private void setupScenario1(){
		grafo = new Grafo<Character,Character,Informacion>(9);
		for(char i = 65; i<74;i++){
			grafo.agregarVertice(i, i);
		}
		grafo.agregarArco('A', 'E', new Informacion(3));
		grafo.agregarArco('B', 'A', new Informacion(2));
		grafo.agregarArco('B', 'C', new Informacion(4));
		grafo.agregarArco('C', 'E', new Informacion(10));
		grafo.agregarArco('D', 'A', new Informacion(9));
		grafo.agregarArco('D', 'F', new Informacion(5));
		grafo.agregarArco('D', 'G', new Informacion(7));
		grafo.agregarArco('E', 'D', new Informacion(4));
		grafo.agregarArco('E', 'H', new Informacion(6));
		grafo.agregarArco('F', 'H', new Informacion(1));
		grafo.agregarArco('G', 'A', new Informacion(7));
		grafo.agregarArco('G', 'B', new Informacion(9));
		grafo.agregarArco('G', 'I', new Informacion(14));
		grafo.agregarArco('H', 'C', new Informacion(12));
		grafo.agregarArco('H', 'I', new Informacion(1));
		grafo.agregarArco('I', 'D', new Informacion(2));
		grafo.agregarArco('I', 'H', new Informacion(10));
	}
	
//	private void setupScenario2(){
//		grafo = new Grafo<String,String, Informacion>(6);
//		grafo.agregarVertice("Santa Marta", "Santa Marta");
//		grafo.agregarVertice("Cartagena", "Cartagena");
//		grafo.agregarVertice("Medellin", "Medellin");
//		grafo.agregarVertice("Bogota","Bogota");
//		grafo.agregarVertice("Cali", "Cali");
//		grafo.agregarVertice("Leticia", "Leticia");
//		
//		grafo.agregarArco("Santa Marta", "Cartagena", new Informacion(1000));
//		grafo.agregarArco("Cartagena","Santa Marta", new Informacion(8000));
//		grafo.agregarArco("Cartagena","Bogota", new Informacion(7000));
//		grafo.agregarArco("Cali","Cartagena", new Informacion(10000));
//		grafo.agregarArco("Cali","Medellin", new Informacion(7000));
//		grafo.agregarArco("Cali","Leticia", new Informacion(15000));
//		grafo.agregarArco("Leticia","Bogota", new Informacion(1000));
//		grafo.agregarArco("Bogota","Leticia", new Informacion(25000));
//		grafo.agregarArco("Bogota","Medellin", new Informacion(7000));
//		grafo.agregarArco("Bogota","Santa Marta", new Informacion(20000));
//		grafo.agregarArco("Medellin","Bogota", new Informacion(10000));
//		grafo.agregarArco("Medellin","Cartagena", new Informacion(4000));
//		grafo.agregarArco("Medellin","Cali", new Informacion(1000));
//	}
	//---------------------------------------
	// Metodos
	//---------------------------------------
	
	public void testDarOrden(){
		setupScenario1();
		assertEquals("El numero de vertices debe ser 9",9,grafo.darOrden());
		grafo.eliminarVertice('A');
		assertEquals("El numero de vertices debe ser 8", 8 , grafo.darOrden());
	}
	
	public void testDarElemento(){
		setupScenario1();
		for(char i = 65; i<74;i++){
			assertEquals("El elemento debe ser el mismo",(Character)i,(Character)grafo.darElemento(i));
		}
		assertNull("El elemento no se debe encontrar",grafo.darElemento('J'));
	}
	
	public void testDarCosto(){
		setupScenario1();
		assertEquals("El costo debe ser 3",(float)3,grafo.darCosto('A', 'E'));
		grafo.eliminarArco('A', 'E');
		assertEquals("El costo debe ser -1",(float)-1,grafo.darCosto('A', 'E'));
	}
	
	public void testAgregarVertice(){
		setupScenario1();
		for(char i = 65; i<74;i++){
			assertTrue(grafo.agregarVertice(i, i));
			assertEquals("El elemento debe ser el mismo",(Character)i,(Character)grafo.darElemento(i));
		}
	}
	
	public void testEliminarVertice(){
		setupScenario1();
		for(char i = 65; i<74;i++){
			assertEquals("El elemento eliminado debe ser " + i, (Character)i,(Character)grafo.eliminarVertice(i));
			assertNull("El elemento eliminado no se debe encontrar",grafo.darElemento(i));
		}
	}
	
	public void testAgregarArco(){
		setupScenario1();
		assertTrue("Se debio agregar correctamente el arco", grafo.agregarArco('F', 'E', new Informacion(2)));
		assertNotNull("El arco debe existir en el grafo", grafo.darArco('F', 'E'));
		assertFalse("No se debio agregar el arco", grafo.agregarArco('E', 'D', new Informacion(4)));
	}
	
	public void testEliminarArco(){
		setupScenario1();
		assertTrue("El arco se debio haber eliminado", grafo.eliminarArco('G', 'B'));
		assertFalse("El arco no existe",grafo.eliminarArco('I', 'G'));
	}
	
	public void testCaminoSimple(){
		setupScenario1();
		assertTrue("El camino debe existir", grafo.hayCaminoSimple('C', 'E'));
		grafo.eliminarArco('C', 'E');
		assertFalse("El camino no debe existir", grafo.hayCaminoSimple('C', 'E'));
	}
	
	public void testCicloSimple(){
		setupScenario1();
		assertTrue("Debe haber ciclo simple con la A",grafo.hayCicloSimpleCon('A'));
		grafo.agregarVertice('J', 'J');
		assertFalse("No debe haber ciclo simple con la J",grafo.hayCicloSimpleCon('J'));
	}
	
	public void testCadena(){
		setupScenario1();
		grafo.eliminarArco('C', 'E');
		assertTrue("Debe haber cadena entre C e I", grafo.hayCadena('C', 'I'));
	}
	
	public void testDarCaminoSimple(){
		setupScenario1();
		Camino<Character,Character,Informacion> camino = grafo.darCaminoSimple('B', 'H');
		assertTrue("Existe el camino simple entre el origen y el destino del camino", grafo.hayCaminoSimple(camino.getOrigen(), camino.getDestino()));
		grafo.eliminarVertice('E');
		assertNull("No existe camino entre B y H", grafo.darCaminoSimple('D', 'H'));
	}
	
	public void testDarCaminoMasCorto(){
		setupScenario1();
		Camino<Character,Character,Informacion> camino = grafo.darCaminoSimpleMasCorto('B', 'H');
		assertEquals("El camino mas corto debe ser de longitud 3", 3, camino.getLongitud());
		camino = grafo.darCaminoSimpleMasCorto('H', 'B');
		assertEquals("El camino mas corto debe ser de longitud 4", 4, camino.getLongitud());
	}
	
	public void testDarCaminoMasBarato(){
		setupScenario1();
		Camino<Character,Character,Informacion> camino = grafo.darCaminoSimpleMasBaratoA('B', 'H');
		assertEquals("El camino menos costoso debe ser de costo 11", (float)11, camino.getCosto());
		camino = grafo.darCaminoSimpleMasBaratoA('D', 'C');
		assertEquals("El camino menos costoso debe ser de costo 18", (float)18, camino.getCosto());
	}
	
	public void testDFS(){
		setupScenario1();
		Iterator<NodoProfundidad<Character,Character,Informacion>> it = grafo.recorridoXProfundidad('C');
		System.out.println("-------------------DFS-----------------------");
		while(it.hasNext()){
			NodoProfundidad<Character,Character,Informacion> actual = it.next();
			String padre ="null";
			if(actual.getArcoPredecesor() != null){
				padre = actual.getArcoPredecesor().getOrigen().getElemento()+"";
			}
			System.out.println(actual.getVertice().getElemento() + " - " + actual.getProfundidad() + " - padre: " + padre);
		}
		System.out.println("-------------------FIN DFS-----------------------");
	}
	
	public void testBFS(){
		setupScenario1();
		Iterator<NodoNivel<Character,Character,Informacion>> it = grafo.recorridoXNiveles('C');
		System.out.println("-------------------BFS-----------------------");
		while(it.hasNext()){
			NodoNivel<Character,Character,Informacion> actual = it.next();
			String padre ="null";
			if(actual.getArcoPredecesor() != null){
				padre = actual.getArcoPredecesor().getOrigen().getElemento()+"";
			}
			System.out.println(actual.getVertice().getElemento() + " - " + actual.getNivel() + " - padre: " + padre);
		}
		System.out.println("-------------------FIN BFS-----------------------");
	}
	
	public void testDijkstra(){
		setupScenario1();
		Iterator<NodoDijkstra<Character,Character,Informacion>> it = grafo.Dijkstra('D');
		System.out.println("-------------------Dijkstra-----------------------");
		while(it.hasNext()){
			NodoDijkstra<Character,Character,Informacion> actual = it.next();
			String  padre = "null";
			if(actual.getArcoPredecesor() != null){
				padre = actual.getArcoPredecesor().getOrigen().getElemento()+"";
			}
			System.out.println(actual.getVertice().getElemento() + " - " + actual.getCosto() + " - padre: " + padre);
		}
		System.out.println("-------------------Fin Dijkstra-----------------------");
	}
}
