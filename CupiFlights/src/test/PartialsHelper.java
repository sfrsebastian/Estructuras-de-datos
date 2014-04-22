package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class PartialsHelper {
	
	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------
	
	private String ruta;
	
	//-----------------------------------------------------------------
	// Constructos
	//-----------------------------------------------------------------

	public PartialsHelper(String nruta){
		ruta = nruta;
	}
	
	//-----------------------------------------------------------------
	// Metodos
	//-----------------------------------------------------------------
	
	public String leer() throws IOException{
		File f = new File(ruta);
		BufferedReader lector = new BufferedReader(new FileReader(f));
		
		String htmlFinal = "";
		
		String linea = "";
		while(linea != null){
			linea = lector.readLine();
			if(linea != null){
				char caracters[] = linea.toCharArray();
				String toPrint = "";
				for (int i = 0; i < caracters.length; i++) {
					if(caracters[i] == '\"'){
						toPrint += '\\';
						toPrint += caracters[i];
					}else{
						toPrint += caracters[i];
					}
				}
				//System.out.println(toPrint);
				htmlFinal += toPrint;
			}
		}
		lector.close();
		
		return htmlFinal;
	}
	
	public void leerManual() throws IOException{
		File f = new File(ruta);
		BufferedReader lector = new BufferedReader(new FileReader(f));
		
		String htmlFinal = "";
		
		String linea = "";
		while(linea != null){
			linea = lector.readLine();
			if(linea != null){
				char caracters[] = linea.toCharArray();
				String toPrint = "";
				for (int i = 0; i < caracters.length; i++) {
					if(caracters[i] == '\"'){
						toPrint += '\\';
						toPrint += caracters[i];
					}else{
						toPrint += caracters[i];
					}
				}
				//System.out.println(toPrint);
				htmlFinal += "respuesta.println(\"" + toPrint + "\"); \n";
			}
		}
		System.out.println(htmlFinal);
		
		lector.close();
	}
	
	public static void main(String[] args) {
		PartialsHelper p = new PartialsHelper("./data/html/admin/admin-contenido.html");
		try {
			p.leerManual();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
