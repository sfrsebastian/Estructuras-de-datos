package componenteSearch.mundo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ArbolAVl.ArbolBinarioAVLOrdenado;
import Cola.Cola;
import ListaEncadenada.ListaEncadenada;

public class Scraper {

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	private String url;
	
	private ListaEncadenada<String> fuentes;
	
	private ListaEncadenada<String> urlsVisitadas;
	
	private ListaEncadenada<String> urlsPorVisitar;
	
	private ArbolBinarioAVLOrdenado<Recurso> arbolRecursos;
	
	public Scraper(){
		arbolRecursos = new ArbolBinarioAVLOrdenado<Recurso>();
		fuentes = new ListaEncadenada<String>();
		urlsVisitadas = new ListaEncadenada<String>();
		urlsPorVisitar = new ListaEncadenada<String>();
	}
	
    //-----------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------
	
	public void scraperEjemploBuscar(String busqueda) throws IOException{
		Document doc = Jsoup.connect(url).get();
		Elements elementos = doc.getElementsByTag(busqueda);
		
		for (Element element : elementos) {
			System.out.println(element.html());
		}
	}
	
	public Exploracion generarExploracion(long maxTime){
		long tempOr = System.currentTimeMillis(); 
		Object[] fuentesUrl = fuentes.darArreglo();
		
		for (int i = 0; i < fuentesUrl.length; i++) {
			long temp = System.currentTimeMillis();
			String url = (String)fuentesUrl[i];
			recorrerFuentePorProfundidad(url, arbolRecursos, 0, temp, maxTime);
			System.out.println("Exploracion de la url: " + url + " finalizada");
			urlsPorVisitar = null;
			urlsPorVisitar = new ListaEncadenada<String>();
		}
		Exploracion e = new Exploracion(arbolRecursos, System.currentTimeMillis() - tempOr);
		
		System.out.println(arbolRecursos.darPeso());
		return e;
	}
	
	private void recorrerFuentePorProfundidad(String url, ArbolBinarioAVLOrdenado<Recurso> arbol,int nivel,long initialTime, long finalTime){
		try {
			nivel++;
			
			if(System.currentTimeMillis() - initialTime > finalTime)
				return;
			
			Document doc = Jsoup.connect(url).get();
			urlsVisitadas.agregar(url);
			System.out.println("Estoy en: " + url);
			
			Elements elementsP = doc.getAllElements();
			for (Element element : elementsP) {
				if(element.tagName().equals("p")){
					Recurso rec = new Recurso(nivel, element.text(), url);
					arbol.agregar(rec);
				}
				else if (element.tagName().equals("img")){
					String patternRelative = "/(.)*";
					String patternAbsolute = "//(.)*";
					String patternAbsoluteTotal = "http://(.)*";
					
					String partial = element.attr("src");
					if(partial.matches(patternAbsoluteTotal)){
						String alter = element.attr("alt");
						if(alter.equals(""))
							alter = "NO TIENE";

						//System.out.println("Es super total: " + partial);
						Recurso rec = new Recurso(nivel, alter,url,partial);
						arbol.agregar(rec);
					}
					else if (partial.matches(patternAbsolute)){
						String alter = element.attr("alt");
						if(alter.equals(""))
							alter = "NO TIENE";

						//System.out.println("Es total: " + "http:" + partial);
						Recurso rec = new Recurso(nivel, alter,url, "http:" + partial);
						arbol.agregar(rec);
					}
					else if(partial.matches(patternRelative)){
						String alter = element.attr("alt");
						if(alter.equals(""))
							alter = "NO TIENE";

						//System.out.println("Es parcial: " + url + partial);
						Recurso rec = new Recurso(nivel, alter, url, url + partial);
						arbol.agregar(rec);
					}
				}
				else if (element.tagName().equals("li")){
					Recurso rec = new Recurso(nivel, element.text(), url);
					arbol.agregar(rec);
				}		
				else if(element.tagName().matches("h[1-7]")){
					Recurso rec = new Recurso(nivel, element.text(), url);
					arbol.agregar(rec);
				}
				else if(element.tagName().equals("a")){
					String patternRelative = "/(.)*";
					String patternAbsolute1 = "(//).*";
					String patternAbsolute2 = "(http://).*";

					String partial = element.attr("href");
					if(partial.matches(patternAbsolute2)){
						urlsPorVisitar.agregar(partial);
						//System.out.println("entra absolute: "+ partial);
					}
					else if(partial.matches(patternAbsolute1)){
						urlsPorVisitar.agregar("http:" + partial);
						//recorrerFuentePorProfundidad("http:" + partial, arbol, nivel);
						//System.out.println("entra absolute: "+ partial);
					}
					else if(partial.matches(patternRelative)){
						urlsPorVisitar.agregar(url + partial);
						//recorrerFuentePorProfundidad(url + partial, arbol, nivel);
						//System.out.println("entra relative: "+ partial);
					}
				}
			}
			
			for (String aVisitar : urlsPorVisitar) {
				if(urlsVisitadas.buscar(aVisitar) == null)
					recorrerFuentePorProfundidad(aVisitar, arbol, nivel, initialTime, finalTime);
			}
		} catch (Exception e) {
			System.out.println("Failed to parse url: " + url + " exploration level: " + nivel);
			//e.printStackTrace();
		}
	}
	
	public void recorrerPorNiveles(String url) throws IOException{
		System.out.println("Soy: " + url);
		Document doc = Jsoup.connect(url).get();
		Elements elems = doc.getElementsByTag("a");
		
		for (Element element : elems) {
			String pattern = "(http://).*";
			String patternInner = "(/wiki/).*";
			
			String inner = element.attr("href");
			if(inner.matches(pattern) && !inner.equals(url) && !visitoUrl(url)){
				//lista.add(inner);
				try{
				recorrerPorNiveles(inner);
				}catch(Exception e){
					
				}
			}else if (inner.matches(patternInner)){
//				String newUrl = "http://www.wikipedia.org" + inner;
//				recorrerPorNiveles(inner);
				//hola!
			}
		}
	}
	
	public String descargarImagen(String url,String ruta) throws IOException{	
			Response resultImageResponse = Jsoup.connect(url).cookie("hola","perro").ignoreContentType(true).execute();
			System.out.println(resultImageResponse.url());
	        // output here
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
	
	public boolean visitoUrl(String url){
		//return lista.contains(url);
		return false;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static void main(String[] args) {
		Scraper escrapeador = new Scraper();
		escrapeador.agregarURL("http://reddit.com");
		Exploracion e = escrapeador.generarExploracion(10000);
		System.out.println(e.darCantidadRecursos());
	}

	public Exploracion explorarSitios(long l) {
		return generarExploracion(l);
	}

	
	public void agregarURL(String path) {
		fuentes.agregar(path);
	}
}
