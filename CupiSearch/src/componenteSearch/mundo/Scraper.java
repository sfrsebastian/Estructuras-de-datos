package componenteSearch.mundo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ArbolAVl.ArbolBinarioAVLOrdenado;
import ListaEncadenada.ListaEncadenada;

public class Scraper {

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * Las fuentes de la exploracion
	 */
	private ArbolBinarioAVLOrdenado<String> fuentes;
	
	/**
	 * Las urls ya visitadas por la exploracion
	 */
	private ArbolBinarioAVLOrdenado<String> urlsVisitadas;
	
	/**
	 * Las urls por visitar
	 */
	private ListaEncadenada<String> urlsPorVisitar;
	
	/**
	 * Los recursos encontrados en la exploracion
	 */
	private ArbolBinarioAVLOrdenado<Recurso> arbolRecursos;
	
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	/**
	 * Crea un nuevo explorador
	 */
	public Scraper(){
		arbolRecursos = new ArbolBinarioAVLOrdenado<Recurso>();
		fuentes = new ArbolBinarioAVLOrdenado<String>();
		urlsVisitadas = new ArbolBinarioAVLOrdenado<String>();
		urlsPorVisitar = new ListaEncadenada<String>();
	}
	
    //-----------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------
	
	/**
	 * Retorna las fuentes de la exploracion
	 * @return las fuentes de exploracion
	 */
	public ArbolBinarioAVLOrdenado<String> getFuentes(){
		return fuentes;
	}

	/**
	 * Genera la exploracion con las fuentes agregadas
	 * @param maxTime El tiempo maximo de busqueda
	 * @param niveles Los niveles que se quieren recorrer
	 * @return La exploracion realizada
	 */
	public Exploracion generarExploracion(long maxTime, int niveles){
		//obtiene el tiempo actual
		long tempOr = System.currentTimeMillis(); 
		Object[] fuentesUrl = fuentes.darArreglo();
		
		//recorre cada url agregado
		for (int i = 0; i < fuentesUrl.length; i++) {
			long temp = System.currentTimeMillis();
			String url = (String)fuentesUrl[i];
			recorrerFuentePorProfundidad(url, arbolRecursos, 1, temp, maxTime,niveles);
			System.out.println("Exploracion de la url: " + url + " finalizada");
			urlsPorVisitar = null;
			urlsPorVisitar = new ListaEncadenada<String>();
		}
		Exploracion e = new Exploracion(arbolRecursos, System.currentTimeMillis() - tempOr);
		Iterator<String> i = fuentes.recorrerInorden();
		while(i.hasNext()){
			String tempUrl = i.next();
			e.agregarFuente(tempUrl);
		}
		
		System.out.println(arbolRecursos.darPeso());
		return e;
	}
	
	/**
	 * Metodo auxiliar que recorre los urls recursivamente
	 * @param url El url en el que se busca
	 * @param arbol El arbol de recrusos acumulados
	 * @param nivel El nivel en el que se encuentra la busqueda
	 * @param initialTime El tiempo inicial de busqueda
	 * @param finalTime El tiempo final de busqueda
	 * @param maxNivelesm El maximo de niveles a recorrer
	 */
	private void recorrerFuentePorProfundidad(String url, ArbolBinarioAVLOrdenado<Recurso> arbol,int nivel,long initialTime, long finalTime,int maxNiveles){
		try {
			//Verifica si ya se cumplio el tiempo maximo
			if(System.currentTimeMillis() - initialTime > finalTime)
				return;
			if(nivel == maxNiveles+1)
				return;
			
			Document doc = Jsoup.connect(url).get();
			urlsVisitadas.agregar(url);
			System.out.println("Estoy en: " + url);
			
			//Recorre todos los elementos de la pagina
			Elements elementsP = doc.getAllElements();
			for (Element element : elementsP) {
				//Pregunta si es un parrafo
				if(element.tagName().equals("p")){
					Recurso rec = new Recurso(nivel, element.text(), url);
					arbol.agregar(rec);
				}
				//Pregunta si es una imagen. Interpreta el url.
				else if (element.tagName().equals("img")){
					String patternRelative = "/(.)*";
					String patternAbsolute = "//(.)*";
					String patternAbsoluteTotal = "http://(.)*";
					
					String partial = element.attr("src");
					if(partial.matches(patternAbsoluteTotal)){
						String alter = element.attr("alt");
						if(alter.equals(""))
							alter = "NO TIENE";

						Recurso rec = new Recurso(nivel, alter,url,partial);
						arbol.agregar(rec);
					}
					else if (partial.matches(patternAbsolute)){
						String alter = element.attr("alt");
						if(alter.equals(""))
							alter = "NO TIENE";

						Recurso rec = new Recurso(nivel, alter,url, "http:" + partial);
						arbol.agregar(rec);
					}
					else if(partial.matches(patternRelative)){
						String alter = element.attr("alt");
						if(alter.equals(""))
							alter = "NO TIENE";

						Recurso rec = new Recurso(nivel, alter, url, url + partial);
						arbol.agregar(rec);
					}
				}
				//verifica si es el elemento de una lista
				else if (element.tagName().equals("li")){
					Recurso rec = new Recurso(nivel, element.text(), url);
					arbol.agregar(rec);
				}
				//verifica si es algun titulo de pagina
				else if(element.tagName().matches("h[1-7]")){
					Recurso rec = new Recurso(nivel, element.text(), url);
					arbol.agregar(rec);
				}
				//Agrega los urls encontrados a la lista por visitar
				else if(element.tagName().equals("a")){
					String patternRelative = "/(.)*";
					String patternAbsolute1 = "(//).*";
					String patternAbsolute2 = "(http://).*";

					String partial = element.attr("href");
					if(partial.matches(patternAbsolute2)){
						urlsPorVisitar.agregar(partial);
					}
					else if(partial.matches(patternAbsolute1)){
						urlsPorVisitar.agregar("http:" + partial);
					}
					else if(partial.matches(patternRelative)){
						urlsPorVisitar.agregar(url + partial);
					}
				}
			}
			//Recorre recursivamente por todos los urls por visitar
			for (String aVisitar : urlsPorVisitar) {
				if(urlsVisitadas.buscar(aVisitar) == null)
					recorrerFuentePorProfundidad(aVisitar, arbol, nivel+1, initialTime, finalTime,maxNiveles);
			}
		} 
		catch (Exception e) {
			System.out.println("Failed to parse url: " + url + " exploration level: " + nivel);
		}
	}
	
	/**
	 * Descarga la imagen y la almacena en la carpeta de recursos
	 * @param url el url de la imagen
	 * @param ruta la ruta de guardado
	 * @return La ruta donde se almaceno la imagen
	 * @throws IOException
	 */
	public String descargarImagen(String url,String ruta) throws IOException{	
			Response resultImageResponse = Jsoup.connect(url).cookie("hola","perro").ignoreContentType(true).execute();
			System.out.println(resultImageResponse.url());
			String[] e = url.split(Pattern.quote("."));
			String extension = e[e.length-1];
			System.out.println("La ruta de guardado es: "+ ruta);
			File f = new File(ruta + "/resources");
			f.delete();
			f.mkdir();
			String salida = ruta + "/resources/image"+ Math.random()*1000 + "." + extension;
	        FileOutputStream out = (new FileOutputStream(new File(salida)));
	        out.write(resultImageResponse.bodyAsBytes());           // resultImageResponse.body() is where the image's contents are.
	        out.close();
	        
	        return salida;
	}
	
	/**
	 * Genera la exploracion
	 * @param l el tiempo de exploracion
	 * @param niveles Los niveles de exploracion
	 * @return La exploracion realizada
	 */
	public Exploracion explorarSitios(long l, int niveles) {
		return generarExploracion(l,niveles);
	}

	/**
	 * Agrega el url dado a el arbol de fuentes de la exploracion
	 * @param path
	 */
	public void agregarURL(String path) {
		fuentes.agregar(path);
	}
}
