package interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.Aeropuerto;
import mundo.CentralDeVuelos;
import mundo.Vuelo;

public class ServletAeropuerto extends HttpServlet {

	//--------------------------------------------
	// Atributos
	//--------------------------------------------

	/**
	 * La central de vuelos del aeropuerto
	 */
	private CentralDeVuelos central;
	
	/**
	 * El aeropuerto actual de la pagina
	 */
	private Aeropuerto aeropuerto;

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
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		aeropuerto = null;
        try {
        	Iterator<Aeropuerto> i = central.darAeropuertos();
        	if(i.hasNext()){      		
        	}else{
        		central.cargarAeropuertos();
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

	//--------------------------------------------
	// Metodos
	//--------------------------------------------
	
	@Override
	public void destroy() {
		try {
			central.guardarCentral();
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.destroy();
	}

	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		String codigo = request.getParameter("codigo");
		if(codigo == null){
			response.sendRedirect(ServletCupiFlights.RUTA + "/general.html");
		}else{
			Aeropuerto buscado = null;
			try {
				buscado = central.darAeropuerto(codigo);
			} catch (Exception e) {
				buscado = null;
			}
			if(buscado != null){
				aeropuerto = buscado;
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
		respuesta.println("  <title>Aeropuerto</title>"); 
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
		respuesta.println("            <li><a href=\"consulta.html\">Consulta</a></li>"); 
		respuesta.println("            <li><a href=\"general.html\">General</a></li>");
		respuesta.println("          </ul>"); 
		respuesta.println("        </div>"); 
		respuesta.println("<!--/.navbar-collapse -->"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
	}

	private void imprimirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<!-- Main jumbotron for a primary marketing message or call to action -->"); 
		respuesta.println("    <div class=\"container\">"); 
		respuesta.println("      <div class=\"row\">"); 
		respuesta.println("        <div class=\"col-md-3\">"); 
		respuesta.println("          <div class=\"sidebar\">"); 
		respuesta.println("            <h2>" + aeropuerto.getNombre() + "</h2>"); 
		respuesta.println("            <hr>"); 
		respuesta.println("            <img src=\"http://flagpedia.net/data/flags/normal/" + (aeropuerto.getCodigoPais()).toLowerCase() + ".png\" class=\"img-responsive\">"); 
		respuesta.println("            <h3><b>Codigo: </b>" + aeropuerto.getCodigo() +"</h3>"); 
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
		respuesta.println("              <li class=\"list-group-item\">Indice de tardanza: " + aeropuerto.getTardanza() + "</li>"); 
		respuesta.println("              <li class=\"list-group-item\">Ciudad: " + aeropuerto.getCiudad() + "</li>"); 
		respuesta.println("              <li class=\"list-group-item\">Pais: " + aeropuerto.getPais() + "</li>"); 
		respuesta.println("              <li class=\"list-group-item\">Califiacion: " + aeropuerto.getCalificacion() + "</li>"); 
		respuesta.println("            </ul>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"col-md-9\">"); 
		respuesta.println("          <div class=\"main content\">"); 
		respuesta.println("            <h2>Vuelos del aeropuerto</h2>"); 
		respuesta.println("            <hr>"); 
		respuesta.println("              <div class=\"table-responsive\">"); 
		respuesta.println("              <table class=\"table table-striped\">"); 
		respuesta.println("                <thead>"); 
		respuesta.println("                  <tr>"); 
		respuesta.println("                    <th>#</th>"); 
		respuesta.println("                    <th>Salida</th>"); 
		respuesta.println("                    <th>Llegada</th>");
		respuesta.println("                    <th>Aerolinea</th>");
		respuesta.println("                    <th>Rating</th>"); 
		respuesta.println("                    <th>Tipo</th>");
		respuesta.println("                    <th>Fecha</th>");
		respuesta.println("                  </tr>"); 
		respuesta.println("                </thead>"); 
		respuesta.println("                <tbody>");
	
		Iterator<Vuelo> vuelos = aeropuerto.darVuelosEntrada();
		while(vuelos.hasNext()){
			Vuelo actual = vuelos.next();
			
			respuesta.println("                <tr>"); 
			respuesta.println("                  <td><a href=\"vuelo.html?codigo="+actual.getNumero()+"\">"+actual.getNumero()+"</a></td>"); 
			respuesta.println("                  <td>"+actual.getSalida().getNombre()+"</td>"); 
			respuesta.println("                  <td>"+actual.getLlegada().getNombre()+"</td>"); 
			respuesta.println("                  <td>"+actual.getAereolinea()+"</td>");
			respuesta.println("                  <td>"+actual.getRating()+"</td>");
			respuesta.println("                  <td>"+actual.getTipo()+"</td>");
			respuesta.println("                  <td>"+actual.getFechaString()+"</td>");
			respuesta.println("                </tr>"); 
		}
		Iterator<Vuelo> vuelosSalida = aeropuerto.darVuelosSalida();
		while(vuelosSalida.hasNext()){
			Vuelo actual = vuelosSalida.next();
			
			respuesta.println("                <tr>"); 
			respuesta.println("                  <td><a href=\"vuelo.html?codigo="+actual.getNumero()+"\">"+actual.getNumero()+"</a></td>"); 
			respuesta.println("                  <td>"+actual.getSalida().getNombre()+"</td>"); 
			respuesta.println("                  <td>"+actual.getLlegada().getNombre()+"</td>"); 
			respuesta.println("                  <td>"+actual.getAereolinea()+"</td>");
			respuesta.println("                  <td>"+actual.getRating()+"</td>");
			respuesta.println("                  <td>"+actual.getTipo()+"</td>"); 
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
