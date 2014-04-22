package interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletCupiFlights extends HttpServlet {
	
	public final static String RUTA = "http://localhost:8080/cupiflights";

	//--------------------------------------------
	// Atributos
	//--------------------------------------------
	
	//private CentralCupiFlights central;
	
	//--------------------------------------------
	// Constructor
	//--------------------------------------------
	
	/**
	 * Inicializa el Servlet
	 */
	public void init( ) throws ServletException
    {
        //central = CentralCupiFlights.getInstance( );
    }
	
	//--------------------------------------------
	// Metodos
	//--------------------------------------------
	
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        // Maneja el GET y el POST de la misma manera
		imprimirEncabezado(request, response);	
        imprimirContenido( request, response );
        imprimirFooter(request, response);
    }
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        // Maneja el GET y el POST de la misma manera
		imprimirEncabezado(request, response);
        imprimirContenido( request, response );    
        imprimirFooter( request, response );
    }
	
	private void imprimirFooter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("      <footer>"); 
		respuesta.println("        <p>&copy; Company 2014</p>"); 
		respuesta.println("      </footer>"); 
		respuesta.println("    </div> <!-- /container -->"); 
		respuesta.println("</body>"); 
		respuesta.println("</html>"); 
	}

	private void imprimirEncabezado(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<html>"); 
		respuesta.println("<head>"); 
		respuesta.println("  <title>Page Title</title>"); 
		respuesta.println("  <script src=\"http://code.jquery.com/jquery-1.11.0.min.js\"></script>"); 
		respuesta.println("  <link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css\">"); 
		respuesta.println("  <script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js\"></script>"); 
		respuesta.println("  <script type=\"text/javascript\">"); 
		respuesta.println("      $( document ).ready(function() {"); 
		respuesta.println("          $(\"#pr\").click(function(){"); 
		respuesta.println("          alert(\"Hello human!\");"); 
		respuesta.println("        });"); 
		respuesta.println("      });"); 
		respuesta.println("  </script>"); 
		respuesta.println("  <style type=\"text/css\">"); 
		respuesta.println("    body{"); 
		respuesta.println("      padding-top: 50px;"); 
		respuesta.println("      padding-bottom: 20px;"); 
		respuesta.println("    }"); 
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
		respuesta.println("            <li><a href=\"consulta.html\">Consulta</a></li>"); 
		respuesta.println("            <li><a href=\"#contact\">Contact</a></li>"); 
		respuesta.println("          </ul>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"navbar-collapse collapse\">"); 
		respuesta.println("          <form class=\"navbar-form navbar-right\" role=\"form\">"); 
		respuesta.println("            <div class=\"form-group\">"); 
		respuesta.println("              <input type=\"text\" placeholder=\"Email\" class=\"form-control\">"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"form-group\">"); 
		respuesta.println("              <input type=\"password\" placeholder=\"Password\" class=\"form-control\">"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-success\">Sign in</button>"); 
		respuesta.println("          </form>"); 
		respuesta.println("        </div><!--/.navbar-collapse -->"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
	}

	private void imprimirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<!-- Main jumbotron for a primary marketing message or call to action -->"); 
		respuesta.println("  <div class=\"jumbotron\">"); 
		respuesta.println("    <div class=\"container\">"); 
		respuesta.println("      <h1>Bienvenido a CupiFlights!</h1>"); 
		respuesta.println("      <p>CupiFlights es un servicio de consulta de vuelos, aeropuertos y estad&iacute;sticas de viajes. Proveemos un servicio r&aacute;pido y optimizado, de modo que puedas ahorrar tiempo y dinero para tu pr&oacute;ximo viaje. Para empezar, selecciona la opci&oacute;n de <b>consulta</b> y <i>Bon Voyage!</i></p>"); 
		respuesta.println("      <p><a class=\"btn btn-primary btn-lg\" role=\"button\" href=\"consulta.html\">Empezar a consultar!</a></p>"); 
		respuesta.println("    </div>"); 
		respuesta.println("  </div>"); 
		respuesta.println(""); 
		respuesta.println("  <div class=\"container\">"); 
		respuesta.println("    <!-- Example row of columns -->"); 
		respuesta.println("    <div class=\"row\">"); 
		respuesta.println("      <div class=\"col-md-4\">"); 
		respuesta.println("        <h2>Heading</h2>"); 
		respuesta.println("        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>"); 
		respuesta.println("        <p><a class=\"btn btn-default\" href=\"#\" role=\"button\">View details &raquo;</a></p>"); 
		respuesta.println("      </div>"); 
		respuesta.println("      <div class=\"col-md-4\">"); 
		respuesta.println("        <h2>Heading</h2>"); 
		respuesta.println("        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>"); 
		respuesta.println("        <p><a class=\"btn btn-default\" href=\"#\" role=\"button\">View details &raquo;</a></p>"); 
		respuesta.println("     </div>"); 
		respuesta.println("      <div class=\"col-md-4\">"); 
		respuesta.println("        <h2>Heading</h2>"); 
		respuesta.println("        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>"); 
		respuesta.println("        <p><a class=\"btn btn-default\" href=\"#\" role=\"button\">View details &raquo;</a></p>"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
		respuesta.println(""); 
		respuesta.println("    <h3 class=\"sub-header\" style=\"margin-bottom:0px;\">Vuelos Recientes</h3>"); 
		respuesta.println("    <hr>"); 
		respuesta.println(""); 
		respuesta.println("    <div class=\"table-responsive\">"); 
		respuesta.println("            <table class=\"table table-striped\">"); 
		respuesta.println("              <thead>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <th>#</th>"); 
		respuesta.println("                  <th>Codigo</th>"); 
		respuesta.println("                  <th>Vuelo</th>"); 
		respuesta.println("                  <th>Aereolinea</th>"); 
		respuesta.println("                  <th>Estado</th>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("              </thead>"); 
		respuesta.println("              <tbody>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1</td>"); 
		respuesta.println("                  <td>MH370</td>"); 
		respuesta.println("                  <td>Kuala Lumpur-Beijing</td>"); 
		respuesta.println("                  <td>Malasya Airlines</td>"); 
		respuesta.println("                  <td>MIA</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>2</td>"); 
		respuesta.println("                  <td>CH234</td>"); 
		respuesta.println("                  <td>Bogota-Cancun</td>"); 
		respuesta.println("                  <td>Copa Airlines</td>"); 
		respuesta.println("                  <td>DEPARTED</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>3</td>"); 
		respuesta.println("                  <td>DS211</td>"); 
		respuesta.println("                  <td>London-Madrid</td>"); 
		respuesta.println("                  <td>Avianca</td>"); 
		respuesta.println("                  <td>DEPARTED</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1,003</td>"); 
		respuesta.println("                  <td>libero</td>"); 
		respuesta.println("                  <td>Sed</td>"); 
		respuesta.println("                  <td>cursus</td>"); 
		respuesta.println("                  <td>ante</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1,004</td>"); 
		respuesta.println("                  <td>dapibus</td>"); 
		respuesta.println("                  <td>diam</td>"); 
		respuesta.println("                  <td>Sed</td>"); 
		respuesta.println("                  <td>nisi</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1,005</td>"); 
		respuesta.println("                  <td>Nulla</td>"); 
		respuesta.println("                  <td>quis</td>"); 
		respuesta.println("                  <td>sem</td>"); 
		respuesta.println("                  <td>at</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1,006</td>"); 
		respuesta.println("                  <td>nibh</td>"); 
		respuesta.println("                  <td>elementum</td>"); 
		respuesta.println("                  <td>imperdiet</td>"); 
		respuesta.println("                  <td>Duis</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1,007</td>"); 
		respuesta.println("                  <td>sagittis</td>"); 
		respuesta.println("                  <td>ipsum</td>"); 
		respuesta.println("                  <td>Praesent</td>"); 
		respuesta.println("                  <td>mauris</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1,008</td>"); 
		respuesta.println("                  <td>Fusce</td>"); 
		respuesta.println("                  <td>nec</td>"); 
		respuesta.println("                  <td>tellus</td>"); 
		respuesta.println("                  <td>sed</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1,009</td>"); 
		respuesta.println("                  <td>augue</td>"); 
		respuesta.println("                  <td>semper</td>"); 
		respuesta.println("                  <td>porta</td>"); 
		respuesta.println("                  <td>Mauris</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1,010</td>"); 
		respuesta.println("                  <td>massa</td>"); 
		respuesta.println("                  <td>Vestibulum</td>"); 
		respuesta.println("                  <td>lacinia</td>"); 
		respuesta.println("                  <td>arcu</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1,011</td>"); 
		respuesta.println("                  <td>eget</td>"); 
		respuesta.println("                  <td>nulla</td>"); 
		respuesta.println("                  <td>Class</td>"); 
		respuesta.println("                  <td>aptent</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1,012</td>"); 
		respuesta.println("                  <td>taciti</td>"); 
		respuesta.println("                  <td>sociosqu</td>"); 
		respuesta.println("                  <td>ad</td>"); 
		respuesta.println("                  <td>litora</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1,013</td>"); 
		respuesta.println("                  <td>torquent</td>"); 
		respuesta.println("                  <td>per</td>"); 
		respuesta.println("                  <td>conubia</td>"); 
		respuesta.println("                  <td>nostra</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1,014</td>"); 
		respuesta.println("                  <td>per</td>"); 
		respuesta.println("                  <td>inceptos</td>"); 
		respuesta.println("                  <td>himenaeos</td>"); 
		respuesta.println("                  <td>Curabitur</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>1,015</td>"); 
		respuesta.println("                  <td>sodales</td>"); 
		respuesta.println("                  <td>ligula</td>"); 
		respuesta.println("                  <td>in</td>"); 
		respuesta.println("                  <td>libero</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("              </tbody>"); 
		respuesta.println("            </table>"); 
		respuesta.println("          </div>");
	}
}
