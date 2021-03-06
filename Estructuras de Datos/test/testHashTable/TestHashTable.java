package testHashTable;

import java.util.Iterator;

import HashTable.TablaHashing;
import junit.framework.TestCase;

public class TestHashTable extends TestCase {
	TablaHashing<Llave,String> tabla;
	private int iterarTamano(Iterator<String> iterator){
		int contador = 0;
		while(iterator.hasNext()){
			iterator.next();
			contador++;
		}
		return contador;
	}
	private void setupScenario1(){
		tabla = new TablaHashing<Llave, String>(7,2);
	}

	private void setupScenario2(){
		tabla = new TablaHashing<Llave,String>(7,2);
		String e1 = "Laura";
		String e2 = "Carmen";
		String e3 = "Maria";
		String e4 = "Pedro";
		String e5 = "Jose";
		String e6 = "Sebastian";
		String e7 = "Felipe";
		
		tabla.agregar(new Llave(e1),e1);
		tabla.agregar(new Llave(e2),e2);
		tabla.agregar(new Llave(e3),e3);
		tabla.agregar(new Llave(e4),e4);
		tabla.agregar(new Llave(e5),e5);
		tabla.agregar(new Llave(e6),e6);
		tabla.agregar(new Llave(e7),e7);//requiere rehash
	}
	
	public void testAgregar(){
		//Tabla vacia
		setupScenario1();
		tabla.agregar(new Llave("Hola"), "Hola");
		Iterator<String> iterador = tabla.iterator();
		assertTrue(iterador.hasNext());
		assertEquals("El elemento debe ser el agregado", iterador.next(),"Hola");
		
		//agregar colision intencional
		tabla.agregar(new Llave("Hola"), "Chao");
		assertEquals("El tamano debe ser 2", tabla.darLongitud(),2);
		iterador = tabla.iterator();
		assertEquals("El elemento debe ser el segundo agregado", iterador.next(),"Chao");
		assertEquals("El elemento debe ser el primer agregado", iterador.next(),"Hola");
		
		//agregar sin colision
		tabla.agregar(new Llave("Sebastian"), "Sebastian");
		assertEquals("El tamano debe ser 3", tabla.darLongitud(),3);
		iterador = tabla.iterator();
		assertEquals("Se debe iterar por tres elementos", iterarTamano(iterador),3);
		iterador = tabla.iterator();
		assertEquals("El primer elemento debe ser el ultimo agregado", iterador.next(),"Sebastian");
		
		//Tabla agregar en tabla con multiples valores.
		setupScenario2();
		assertEquals("La longitud debe ser de 7",tabla.darLongitud(),7);
		tabla.agregar(new Llave("Camilo"), "Camilo");
		assertEquals("La longitud debe ser de 8",tabla.darLongitud(),8);
		iterador = tabla.iterator();
		assertEquals("Se debe iterar 8 veces", iterarTamano(iterador),8);
	}
	
	public void testBuscar(){
		//Buscar en tabla vacia
		setupScenario1();
		Llave llave = new Llave("Carlos");
		assertNull("No existe ningun elemento a buscar",tabla.eliminar(llave));
		
		tabla.agregar(llave, "Carlos");
		
		//Busca en la tabla el unico elemento
		assertEquals("Debe retornarse el elemento","Carlos", tabla.buscar(llave));
		
		//Tabla eliminar en tabla con multiples valores.
		setupScenario2();
		assertEquals("Debe retornarse el elemento por buscar","Sebastian", tabla.buscar(new Llave("Sebastian")));

	}
	
	public void testDarArreglo(){
		//Arreglo de lista vacia
		setupScenario1();
		assertEquals("El arreglo debe ser de longitud 0",tabla.darArreglo().length,0);
		
		Llave llave = new Llave("Carlos");
		tabla.agregar(llave, "Carlos");
		
		//Arreglo de lista 1 elemento
		assertEquals("El arreglo debe ser de longitud 1",tabla.darArreglo().length,1);
		
		//Tabla arreglo multiples elementos.
		setupScenario2();
		assertEquals("El arreglo debe ser de longitud 7", tabla.darArreglo().length,7);
	}
	public void testEliminar(){
		//Borrar en tabla vacia
		setupScenario1();
		Llave llave = new Llave("Carlos");
		assertNull("No existe ningun elemento a eliminar",tabla.eliminar(llave));
		
		tabla.agregar(llave, "Carlos");
		
		//Borrar tabla unico elemento
		assertEquals("Debe retornarse el elemento eliminado","Carlos", tabla.eliminar(llave));
		assertEquals("La longitud debe ser de 0",tabla.darLongitud(),0);
		
		//Tabla eliminar en tabla con multiples valores.
		setupScenario2();
		assertEquals("Debe retornarse el elemento eliminado","Sebastian", tabla.eliminar(new Llave("Sebastian")));
		assertEquals("La longitud debe ser de 6",tabla.darLongitud(),6);
		Iterator<String> iterador = tabla.iterator();
		assertEquals("Se debe iterar 6 veces", iterarTamano(iterador),6);
	}
	
	public void testEliminarIterador(){
		setupScenario2();
		Iterator<String> iterador = tabla.iterator();
		iterador.next();
		iterador.remove();//elimina primer elemento del iterador
		assertEquals("La longitud debe disminuir", 6, tabla.darLongitud());
	}
}
