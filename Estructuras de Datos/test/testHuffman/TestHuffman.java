package testHuffman;
import CompresorHuffman.CompresorHuffman;
import CompresorHuffman.TextoComprimido;
import junit.framework.TestCase;

public class TestHuffman extends TestCase {
	CompresorHuffman huffman;
	String mensaje;
	public void setupScenario1(){
		//Inicializa el mensaje y el compresor
		mensaje = "Hello World";
		huffman = new CompresorHuffman(mensaje);
	}
	
	public void test(){
		setupScenario1();
		TextoComprimido comprimido = huffman.comprimir();
		//verifica que al descomprimir se obtiene el mismo mensaje
		assertEquals("El mensaje debe ser el mismo",mensaje,comprimido.descomprimir());
	}
	
}
