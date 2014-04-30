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

public class ServletVuelo extends HttpServlet {

	//--------------------------------------------
	// Atributosfrfr[]
	//--------------------------------------------

	private CentralDeVuelos central;
	
	private Vuelo vuelo;

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
		vuelo = null;
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
			Vuelo buscado = central.darVuelo(codigo);
			if(buscado != null){
				vuelo = buscado;
				imprimirEncabezado(request, response);
				imprimirContenido(request, response);
				imprimirFooter(request, response);
			}else{
				response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
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
		respuesta.println("  <title>Vuelo</title>"); 
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
		respuesta.println("    .menor{"); 
		respuesta.println("      color: white;"); 
		respuesta.println("      border-bottom: 1px solid;"); 
		respuesta.println("      text-align: center;"); 
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
		respuesta.println("        <div class=\"col-md-5\">"); 
		respuesta.println("          <div class=\"sidebar\">"); 
		respuesta.println("            <h2>"+vuelo.getSalida().getCiudad() + " - " + vuelo.getLlegada().getCiudad() +"</h2>"); 
		respuesta.println("            <hr>"); 
		respuesta.println("            <div class=\"row\">"); 
		respuesta.println(""); 
		respuesta.println("              <div class=\"col-md-6\">"); 
		respuesta.println("                <div class=\"panel panel-warning\">"); 
		respuesta.println("                  <div class=\"panel-heading\">Salida</div>"); 
		respuesta.println("                  <div class=\"panel-body\">"); 
		respuesta.println("                    <b><a href=\"aeropuerto.html?codigo="+vuelo.getSalida().getCodigo()+"\">"+vuelo.getSalida().getCodigo()+"</a></b><p>"+vuelo.getSalida().getNombre()+"</p>"); 
		respuesta.println("                  </div>"); 
		respuesta.println("                  <div class=\"panel-footer\">"); 
		respuesta.println("                    <img src=\"http://flagpedia.net/data/flags/normal/"+(vuelo.getSalida().getCodigoPais()).toLowerCase()+".png\" class=\"img-responsive\">"); 
		respuesta.println("                  </div>"); 
		respuesta.println("                </div>"); 
		respuesta.println("              </div>"); 
		respuesta.println(""); 
		respuesta.println("              <div class=\"col-md-6\">"); 
		respuesta.println("                <div class=\"panel panel-success\">"); 
		respuesta.println("                  <div class=\"panel-heading\">Llegada</div>"); 
		respuesta.println("                  <div class=\"panel-body\">"); 
		respuesta.println("                    <b><a href=\"aeropuerto.html?codigo="+vuelo.getLlegada().getCodigo()+"\">"+vuelo.getLlegada().getCodigo()+"</a></b><p>"+vuelo.getLlegada().getNombre()+"</p>"); 
		respuesta.println("                  </div>"); 
		respuesta.println("                  <div class=\"panel-footer\">"); 
		respuesta.println("                    <img src=\"http://flagpedia.net/data/flags/normal/"+(vuelo.getLlegada().getCodigoPais()).toLowerCase()+".png\" class=\"img-responsive\">"); 
		respuesta.println("                  </div>"); 
		respuesta.println("                </div>"); 
		respuesta.println("              </div>"); 
		respuesta.println("              </div>"); 
		respuesta.println("              <h3><b>Codigo: </b>"+vuelo.getNumero()+"</h3>"); 
		respuesta.println("            <hr>"); 
		respuesta.println("            <div class=\"btn-group\" style=\"width:100%\">"); 
		respuesta.println("              <button type=\"button\" class=\"btn btn-default\"> Fecha: </button>"); 
		respuesta.println("              <button type=\"button\" class=\"btn btn-success\"> "+vuelo.getFechaString()+" </button>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <h4>Mas Informacion</h4>"); 
		respuesta.println("            <ul class=\"list-group\">"); 
		respuesta.println("              <li class=\"list-group-item\">Aerolinea: "+vuelo.getAereolinea().getNombre()+"</li>"); 
		respuesta.println("              <li class=\"list-group-item\">Rating: "+vuelo.getRating()+"</li>"); 
		respuesta.println("            </ul>"); 
		respuesta.println("            </div>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        <div class=\"col-md-7\">"); 
		respuesta.println("          <div class=\"main content\">"); 
		respuesta.println("            <h2>Mapa del Vuelo</h2>"); 
		respuesta.println("            <hr>"); 
		respuesta.println("             <img style=\"-webkit-user-select: none; cursor: -webkit-zoom-in;\" src=\""+central.darUrlVuelo(vuelo.getNumero())+"\" width=\"550\" height=\"550\" class=\"img-responsive\">"); 
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
