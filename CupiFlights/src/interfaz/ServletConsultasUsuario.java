package interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Grafo.Camino;
import mundo.Aeropuerto;
import mundo.CentralDeVuelos;

public class ServletConsultasUsuario extends HttpServlet{
	
	//--------------------------------------------
	// Atributos
	//--------------------------------------------
	
	/**
	 * La central que contiene el mundo
	 */
	private CentralDeVuelos central;
	
	/**
	 * El titulo de esta pagina
	 */
	private String tituloPagina;
	
	//--------------------------------------------
	// Constructor
	//--------------------------------------------
	
	/**
	 * Inicializa el Servlet
	 */
	public void init( ) throws ServletException
    {
         try {
				central = CentralDeVuelos.getInstance( );
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
	
	//--------------------------------------------
	// Metodos
	//--------------------------------------------

	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		if(session == null){
			response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
			System.out.println("Session is nulled");
		}
		else{
			if(session.getAttribute("usuario").equals("rommy"))
				response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
			else{
				String pedido = request.getParameter("pedido");
				System.out.println("Pedido en ServletConsultasUsuario: " + pedido);
				if(pedido != null){
					if(pedido.equals("calcularGradoSeparacion")){
						String origen = request.getParameter("origen");
						String destino = request.getParameter("destino");
						Camino c = central.darGrado(origen, origen);
						imprimirRuta(request, response, c, "Grado de Separacion");
					}else if(pedido.equals("rutaMasCorta")){
						String origen = request.getParameter("origen");
						String destino = request.getParameter("destino");
						System.out.println(origen+"-"+destino);
						Camino c = central.darRutaMenorLongitud(origen, destino);
						imprimirRuta(request, response, c, "Ruta mas corta distancia");
					}else if(pedido.equals("rutaMasCortaTiempo")){
						String origen = request.getParameter("origen");
						String destino = request.getParameter("destino");
						Camino c= central.darRutaMenorTiempoConParada(origen, destino);
						imprimirRuta(request, response, c, "Ruta mas corta tiempo");
					}else if(pedido.equals("rutaMejorRating")){
						String origen = request.getParameter("origen");
						String destino = request.getParameter("destino");
						Camino c= central.darRutaMayorRating(origen, destino);
						imprimirRuta(request, response, c, "Ruta mejor rating");
					}else if(pedido.equals("rutaMenorTardios")){
						String origen = request.getParameter("origen");
						String destino = request.getParameter("destino");
						Camino c= central.darRutaMenorTardios(origen, destino);
						imprimirRuta(request, response, c, "Ruta menor tardanza");
					}else if(pedido.equals("tourMasLargo")){
						String origen = request.getParameter("codigo");
						Camino c = central.darTourMasLargo(origen);
						imprimirRuta(request, response, c, "Tour mas largo");
					}else if(pedido.equals("verRecomendaciones")){
						String origen = request.getParameter("codigo");
						Camino c = central.darTourDesde(origen);
						imprimirRuta(request, response, c, "Tour Recomendado");
					}
				}else{
					response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
				}
			}
		}
	}
	
	private void imprimirEncabezado(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter respuesta = response.getWriter(); 
		
	}
	
	private void imprimirRuta(HttpServletRequest request, HttpServletResponse response, Camino c, String pedido) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<html>"); 
		respuesta.println("<head>"); 
		respuesta.println("  <meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\">"); 
		respuesta.println("  <meta charset=\"utf-8\">"); 
		respuesta.println("  <title>"+pedido+"</title>"); 
		respuesta.println("  <style>"); 
		respuesta.println("    #map-canvas {"); 
		respuesta.println("      height: 100%;"); 
		respuesta.println("      margin: 0px;"); 
		respuesta.println("      padding: 0px"); 
		respuesta.println("    }"); 
		respuesta.println("  </style>"); 
		respuesta.println("  <script src=\"https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false\"></script>"); 
		respuesta.println(""); 
		respuesta.println("  <script>"); 
		respuesta.println("    // This example displays a marker at the center of Australia."); 
		respuesta.println("    // When the user clicks the marker, an info window opens."); 
		respuesta.println("    function initialize() {"); 
		respuesta.println("      var mapOptions = {"); 
		respuesta.println("        zoom: 4,"); 
		respuesta.println("        center: new google.maps.LatLng(0, -180)"); 
		respuesta.println("      };"); 
		respuesta.println(""); 
		respuesta.println("      var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);"); 
		respuesta.println(""); 	
		
		int ca = 0;
		if(c!= null){
			ca = c.getLongitud();
			if(ca == 0)
				System.out.println("No hay camino");

			respuesta.println("          //Aca va lo que imprime el metodo que tiene markers y lineas de ruta"); 
			String s = central.darJSRuta(c);
			if(!s.equals("var flightPlanCoordinates =];")){
				respuesta.println(central.darJSRuta(c));
			}
		}
		respuesta.println("     "); 
		respuesta.println(""); 
		respuesta.println(""); 
		respuesta.println("      //Esto debe ser incluido si tiene lineas. Se puede omitir si son solo markers"); 
		respuesta.println("      var flightPath = new google.maps.Polyline({"); 
		respuesta.println("        path: flightPlanCoordinates,"); 
		respuesta.println("        geodesic: true,"); 
		respuesta.println("        strokeColor: '#FF0000',"); 
		respuesta.println("        strokeOpacity: 1.0,"); 
		respuesta.println("        strokeWeight: 2"); 
		respuesta.println("      });"); 
		respuesta.println("      flightPath.setMap(map)"); 
		respuesta.println("      //hasta aca"); 
		respuesta.println("    }"); 
		respuesta.println("    google.maps.event.addDomListener(window, 'load', initialize);"); 
		respuesta.println("  </script>"); 
		respuesta.println(""); 
		respuesta.println("  <!--Google API on top-->"); 
		
		
		
		respuesta.println("	<script src=\"http://code.jquery.com/jquery-1.11.0.min.js\"></script>"); 
		respuesta.println("	<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css\">"); 
		respuesta.println("	<script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js\"></script>"); 
		respuesta.println("	<style type=\"text/css\">"); 
		respuesta.println("		body{"); 
		respuesta.println("			padding-top: 50px;"); 
		respuesta.println("			padding-bottom: 20px;"); 
		respuesta.println("		}"); 
		respuesta.println("    .sidebar{"); 
		respuesta.println("      background-color: gray;"); 
		respuesta.println("      padding: 20px;"); 
		respuesta.println("    }"); 
		respuesta.println("    .sidebar h2{"); 
		respuesta.println("      color: white;"); 
		respuesta.println("    }"); 
		respuesta.println("    .sidebar h4{"); 
		respuesta.println("      color: white;"); 
		respuesta.println("    }"); 
		respuesta.println("    .menor{"); 
		respuesta.println("      color: white;"); 
		respuesta.println("      border-bottom: 1px solid;"); 
		respuesta.println("      text-align: center;"); 
		respuesta.println("    }"); 
		respuesta.println(""); 
		respuesta.println("	</style>"); 
		respuesta.println("</head>"); 
		
		
		
		respuesta.println("<body>"); 
		respuesta.println("	<div class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">"); 
		respuesta.println("      <div class=\"container\">"); 
		respuesta.println("        <div class=\"navbar-header\">"); 
		respuesta.println("          <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">"); 
		respuesta.println("            <span class=\"sr-only\">Toggle navigation</span>"); 
		respuesta.println("            <span class=\"icon-bar\"></span>"); 
		respuesta.println("            <span class=\"icon-bar\"></span>"); 
		respuesta.println("            <span class=\"icon-bar\"></span>"); 
		respuesta.println("          </button>"); 
		respuesta.println("          <a class=\"navbar-brand\" href=\"index.html\">CupiFlighs</a>"); 
		respuesta.println("          <ul class=\"nav navbar-nav\">"); 
		respuesta.println("            <li><a href=\"login.html\">Admin</a></li><!--class=\"active\" for the active link page!-->"); 
		respuesta.println("            <li><a href=\"consulta.html\">Consulta</a></li>"); 
		respuesta.println("            <li><a href=\"mapa.html\">Mapa</a></li>"); 
		respuesta.println("            <li><a href=\"general.html\">General</a></li>"); 
		respuesta.println("            <li><a href=\"usuario.html\">Usuario</a></li>"); 
		respuesta.println("          </ul>"); 
		respuesta.println("        </div>"); 
		respuesta.println("<!--/.navbar-collapse -->"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
		respuesta.println(""); 
		respuesta.println(""); 
		respuesta.println("    <!-- Main jumbotron for a primary marketing message or call to action -->"); 
		respuesta.println("    <div class=\"container\">"); 
		respuesta.println("      <div class=\"row\">"); 
		respuesta.println("        <div class=\"col-md-5\">"); 
		respuesta.println("          <div class=\"sidebar\">"); 
		respuesta.println("            <h2>"+pedido+"</h2>"); 
		respuesta.println("            <hr>"); 
		
		if(ca == 0){
			respuesta.println("<div class=\"alert alert-danger\">Atenci&oacute;n! No hay ruta disponible</div>");
		}
		
		respuesta.println("            <div class=\"row\">"); 
		respuesta.println("");  
		respuesta.println(""); 
		respuesta.println("              <div class=\"col-md-12\">"); 
		respuesta.println("                <div class=\"panel panel-success\">"); 
		respuesta.println("                  <div class=\"panel-heading\">Ruta</div>"); 
		respuesta.println("                  <div class=\"panel-body\">"); 
		
		respuesta.println("                  <div class=\"table-responsive\">"); 
		respuesta.println("            <table class=\"table table-striped\">"); 
		respuesta.println("              <thead>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <th>#</th>");
		respuesta.println("                  <th>Codigo</th>");
		respuesta.println("                  <th>Nombre</th>"); 
//		respuesta.println("                  <th>Ciudad</th>"); 
//		respuesta.println("                  <th>Pais</th>");
//		respuesta.println("                  <th>Bandera</th>");
//		respuesta.println("                  <th>Calificacion</th>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("              </thead>"); 
		respuesta.println("              <tbody>"); 
		
		if(c != null){

			Iterator<Aeropuerto> ia = c.darVertices();
			int con = 1;
			while(ia.hasNext()){
				Aeropuerto actual = ia.next();

				respuesta.println("                <tr>"); 
				respuesta.println("                  <td>"+con+"</td>");
				respuesta.println("                  <td><a href=\"aeropuerto.html?codigo="+actual.getCodigo()+"\">"+actual.getCodigo()+"</a></td>"); 
				respuesta.println("                  <td>"+actual.getNombre()+"</td>"); 
				//			respuesta.println("                  <td>"+actual.getCiudad()+"</td>"); 
				//			respuesta.println("                  <td>"+actual.getPais()+"</td>");
				//			respuesta.println("                  <td><img src=\"http://flagpedia.net/data/flags/mini/" + (actual.getCodigoPais()).toLowerCase() + ".png\"></td>");
				//			respuesta.println("                  <td>"+actual.getCalificacion()+"</td>"); 
				respuesta.println("                </tr>"); 
				con++;
			}
		}

		respuesta.println("              </tbody>"); 
		respuesta.println("            </table>");
		respuesta.println("                  </div>");
		
		respuesta.println("                  </div>");  
		respuesta.println("                </div>"); 
		respuesta.println("              </div>"); 
		respuesta.println("              </div>"); 
		respuesta.println("            <hr>");  
		respuesta.println("            <h4>Mas Informacion</h4>"); 
		respuesta.println("            <ul class=\"list-group\">"); 
		respuesta.println("              <li class=\"list-group-item\">Numero vuelos: "+ca+"</li>");
		
		
		int lo = 0;
		if(c != null){
			lo = c.getLongitud();
		}
		respuesta.println("              <li class=\"list-group-item\">Distancia total: "+lo+"</li>");
		//respuesta.println("              <li class=\"list-group-item\">Rating: 4.5</li>"); 
		respuesta.println("            </ul>"); 
		respuesta.println("            </div>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        <div class=\"col-md-7\">"); 
		respuesta.println("          <div class=\"main content\">"); 
		respuesta.println("            <h2>Mapa del Vuelo</h2>"); 
		respuesta.println("            <hr>"); 
		respuesta.println("              <div id=\"map-canvas\"></div>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("      </div>");
		
		imprimirFooter(request, response);
	}
	
	private void imprimirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		
	}
	
	private void imprimirFooter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<hr>"); 
		respuesta.println(""); 
		respuesta.println("<footer>"); 
		respuesta.println("        <p>&copy; Felipe Ot&aacute;lora - Sebasti&aacute;n Florez</p>"); 
		respuesta.println("      </footer>"); 
		respuesta.println("    </div> <!-- /container -->"); 
		respuesta.println("</body>"); 
		respuesta.println("</html>"); 
	}
}
