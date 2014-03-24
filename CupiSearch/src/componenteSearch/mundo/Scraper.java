package componenteSearch.mundo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.CookieHandler;
import java.util.ArrayList;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	private String url;
	
	private String terminoBusqueda ;
	
	private ArrayList<String> lista;
	
	public Scraper(String nUrl){
		url = nUrl;
		lista = new ArrayList<String>();
	}
	
	public Scraper(){
		
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
	
	public void recorrerFuentePorProfundidad(){
		
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
				lista.add(inner);
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
	
	public void recorrerImagenes(String url) throws IOException{
		Document doc = Jsoup.connect(url).get();
		Elements elems = doc.getElementsByTag("img");
		for (Element element : elems) {
			try{
			String imgLocation = element.attr("src");
			int i = 0;
			
			Response resultImageResponse = Jsoup.connect(imgLocation).cookie("hola","perro").ignoreContentType(true).execute();
			System.out.println(resultImageResponse.url());
	        // output here
	        FileOutputStream out = (new FileOutputStream(new File("./data/" + element.ownText() + "h.jpg")));
	        out.write(resultImageResponse.bodyAsBytes());           // resultImageResponse.body() is where the image's contents are.
	        out.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("Ended");
	}
	
	public boolean visitoUrl(String url){
		return lista.contains(url);
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static void main(String[] args) {
		Scraper escrapeador = new Scraper("http://www.wikipedia.org");
		try {
			//escrapeador.recorrerPorNiveles("http://cupi2.uniandes.edu.co");
			escrapeador.recorrerImagenes("http://reddit.com");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Exploracion explorarSitios() {
		// TODO Auto-generated method stub
		return null;
	}

	public void agregarURL(String path) {
		// TODO Auto-generated method stub
		
	}
}
