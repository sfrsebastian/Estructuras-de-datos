package testHashTable;

import java.util.Iterator;

import HashTable.TablaHashing;
import junit.framework.TestCase;

public class testHashTable extends TestCase {
	TablaHashing<LlaveString,String> tabla;
	private void setupScenario1(){
		tabla = new TablaHashing(7,2);
	}
	private void setupScenario2(){
		tabla = new TablaHashing(7,2);
		
	}

	public void testAgregar(){
		//Tabla vacia
		setupScenario1();
		tabla.agregar(new LlaveString("Hola"), "Hola");
		Iterator iterador = tabla.iterator();
		assertTrue(iterador.hasNext());
		assertEquals("El elemento debe ser el agregado", iterador.next(),"Hola");
		tabla.agregar(new LlaveString("Hola"), "Chao");
		iterador = tabla.iterator();
		assertEquals("El tamaño debe ser 2", iterarTamaño(iterador),2);
		iterador = tabla.iterator();
		assertEquals("El elemento debe ser el segundo agregado", iterador.next(),"Chao");
		
		//Tabla agregar en tabla con valores.
		
		
	}
	
	public void testEliminar(){
		
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
