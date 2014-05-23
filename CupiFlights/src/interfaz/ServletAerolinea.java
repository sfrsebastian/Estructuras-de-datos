package interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.Aerolinea;
import mundo.Aeropuerto;
import mundo.CentralDeVuelos;
import mundo.Vuelo;

public class ServletAerolinea extends HttpServlet{
	
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
	
	/**
	 * La aerolinea actual
	 */
	private Aerolinea aerolinea;
	
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
		String codigo = request.getParameter("codigo");
		if(codigo == null){
			response.sendRedirect(ServletCupiFlights.RUTA + "/general.html");
		}else{
			Aerolinea buscado = null;
			try {
				//buscado = central.darAerolinea(codigo);
			} catch (Exception e) {
				buscado = null;
			}
			if(buscado != null){
				aerolinea = buscado;
				imprimirEncabezado(request, response);
				imprimirContenido(request, response);
				imprimirFooter(request, response);
			}
		}
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
	}
	
	private void imprimirEncabezado(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<html>"); 
		respuesta.println("<head>"); 
		respuesta.println("  <title>Aerolinea</title>"); 
		respuesta.println("  <script src=\"http://code.jquery.com/jquery-1.11.0.min.js\"></script>"); 
		respuesta.println("  <link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css\">"); 
		respuesta.println("  <script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js\"></script>"); 
		respuesta.println("  <style type=\"text/css\">"); 
		respuesta.println("    body{"); 
		respuesta.println("      padding-top: 50px;"); 
		respuesta.println("      padding-bottom: 20px;"); 
		respuesta.println("    }"); 
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
		respuesta.println(""); 
		respuesta.println("  </style>"); 
		respuesta.println("</head>"); 
		respuesta.println("<body>"); 
		respuesta.println("  <div class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">"); 
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
		respuesta.println("            <li><a href=\"consutla.html\">Consulta</a></li>"); 
		respuesta.println("            <li><a href=\"mapa.html\">Mapa</a></li>"); 
		respuesta.println("            <li><a href=\"aeropuerto.html\">Mapa</a></li>"); 
		respuesta.println("            <li><a href=\"usuario.html\">Usuario</a></li>"); 
		respuesta.println("            <li><a href=\"aerolinea.html\">Aerolinea</a></li>"); 
		respuesta.println("          </ul>"); 
		respuesta.println("        </div>"); 
		respuesta.println("<!--/.navbar-collapse -->"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
	}
	
	private void imprimirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("    <!-- Main jumbotron for a primary marketing message or call to action -->"); 
		respuesta.println("    <div class=\"container\">"); 
		respuesta.println("      <div class=\"row\">"); 
		respuesta.println("        <div class=\"col-md-3\">"); 
		respuesta.println("          <div class=\"sidebar\">"); 
		respuesta.println("            <h2>Avianca</h2>"); 
		respuesta.println("            <hr>"); 
		respuesta.println("            <img src=\"http://www.avianca.com/es/Style%20Library/Images/logo-avianca.png\" class=\"img-responsive\" style=\"background-color:white; padding-top:50px; padding-bottom:50px\">"); 
		respuesta.println("            <h3><b>Codigo: </b>AVV</h3>"); 
		respuesta.println("            <hr>"); 
		respuesta.println("            <div class=\"btn-group\" style=\"width:100%\">"); 
		respuesta.println("              <button type=\"button\" class=\"btn btn-default\"> Calificacion: </button>"); 
		respuesta.println("              <button type=\"button\" class=\"btn btn-default\">"); 
		respuesta.println("                <span class='glyphicon glyphicon-star'></span>"); 
		respuesta.println("                <span class='glyphicon glyphicon-star'></span>"); 
		respuesta.println("                <span class='glyphicon glyphicon-star'></span>"); 
		respuesta.println("                <span class='glyphicon glyphicon-star-empty'></span>"); 
		respuesta.println("                <span class='glyphicon glyphicon-star-empty'></span>"); 
		respuesta.println("              </button>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <h4>Mas Informacion</h4>"); 
		respuesta.println("            <ul class=\"list-group\">"); 
		respuesta.println("              <!--<li class=\"list-group-item\">Indice de tardanza: 5</li>-->"); 
		respuesta.println("              <!--<li class=\"list-group-item\">Ciudad: Bogot&aacute;</li>-->"); 
		respuesta.println("              <li class=\"list-group-item\">Nacionalidad: Colombia</li>"); 
		respuesta.println("            </ul>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"col-md-9\">"); 
		respuesta.println("          <div class=\"main content\">"); 
		respuesta.println("            <h2>Vuelos de la Aerolinea</h2>"); 
		respuesta.println("            <hr>"); 
		respuesta.println("              <div class=\"table-responsive\">"); 
		respuesta.println("              <table class=\"table table-striped\">"); 
		respuesta.println("                <thead>"); 
		respuesta.println("                  <tr>"); 
		respuesta.println("                    <th>#</th>"); 
		respuesta.println("                    <th>Salida</th>"); 
		respuesta.println("                    <th>Llegada</th>");
		respuesta.println("                    <th>Rating</th>"); 
		respuesta.println("                    <th>Tipo</th>");
		respuesta.println("                    <th>Fecha</th>");
		respuesta.println("                  </tr>"); 
		respuesta.println("                </thead>"); 
		respuesta.println("                <tbody>");
	
		//Iterator<Vuelo> vuelos = aerolinea.darVuelos();
		Iterator<Vuelo> vuelos = null;
		while(vuelos.hasNext()){
			Vuelo actual = vuelos.next();
			
			respuesta.println("                <tr>"); 
			respuesta.println("                  <td><a href=\"vuelo.html?codigo="+actual.getNumero()+"\">"+actual.getNumero()+"</a></td>"); 
			respuesta.println("                  <td>"+actual.getSalida().getNombre()+"</td>"); 
			respuesta.println("                  <td>"+actual.getLlegada().getNombre()+"</td>"); 
			respuesta.println("                  <td>"+actual.getRating()+"</td>");
			respuesta.println("                  <td>"+actual.getTipo()+"</td>");
			respuesta.println("                  <td>"+actual.getFechaString()+"</td>");
			respuesta.println("                </tr>"); 
		}
		
		respuesta.println("                </tbody>"); 
		respuesta.println("              </table>"); 
		respuesta.println("          </div>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("      </div>"); 
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
