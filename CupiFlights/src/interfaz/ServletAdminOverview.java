package interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mundo.Aeropuerto;
import mundo.CentralDeVuelos;
import mundo.Vuelo;

public class ServletAdminOverview extends HttpServlet {

	//--------------------------------------------
	// Atributos
	//--------------------------------------------
	
	private CentralDeVuelos central;
	
	private String[] paises;
	
	//--------------------------------------------
	// Constructor
	//--------------------------------------------
	
	/**
	 * Inicializa el Servlet
	 */
	public void init( ) throws ServletException
    {
        central = CentralDeVuelos.getInstance( );
        try {
        	Iterator<Aeropuerto> i = central.darAeropuertos();
        	if(i.hasNext()){      		
        	}else{
        		central.cargarAeropuertos();
        	}
        } catch (Exception e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
        paises = new String[8];
        
        paises[0] = "co";
        paises[1] = "us";
        paises[2] = "be";
        paises[3] = "fr";
        paises[4] = "nz";
        paises[5] = "mx";
        paises[6] = "ar";
        paises[7] = "br";
    }
	
	//--------------------------------------------
	// Metodos
	//--------------------------------------------
	
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		//TODO Fix this type of redirect
		HttpSession sesion = request.getSession();
		if(sesion.getAttribute("usuario") == null){
		response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
		}else{
			imprimirEncabezado(request, response);
			imprimirContenido(request, response);
			imprimirFooter(request, response);
		}
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		String cerrar = request.getParameter("cerrar");
		if(cerrar != null){
			//Cerrar sesion y redireccionar
			HttpSession session = request.getSession();
			session.removeAttribute("usuario");
			System.out.println("Sesion cerrada");
			response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
		}else{
			String usuario = request.getParameter( "usuario" );
			String contrasenia = request.getParameter( "contrasena" );
			if(usuario != null && contrasenia != null){
				if(!usuario.equals("romsearcher") && !contrasenia.equals("12345")){
					response.sendRedirect(ServletCupiFlights.RUTA + "/login.html?error=si");
				}else{
					//Usuario autenticado
					
					imprimirEncabezado(request, response);
					imprimirContenido(request, response);
					imprimirFooter(request, response);
					
					HttpSession session = request.getSession();
					session.setAttribute("usuario", "rommy");
					System.out.println("Sesion establecida, usuario autenticado");
				}
			}else{
				System.out.println("NO");
			}
		}
    }
	
	@Override
	public void destroy() {
		try {
			central.guardarCentral();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.destroy();
	}
	
	private void imprimirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println(" <div class=\"container-fluid\">"); 
		respuesta.println("      <div class=\"row\">"); 
		respuesta.println("        <div class=\"col-sm-3 col-md-2 sidebar\">"); 
		respuesta.println("          <ul class=\"nav nav-sidebar\">"); 
		respuesta.println("            <li class=\"active\"><a href=\"#\">General</a></li>"); 
		respuesta.println("            <li><a href=\"agregar.html\">Agregar</a></li>"); 
		respuesta.println("            <li><a href=\"admin-aeropuerto.html\">Aeropuertos</a></li>"); 
		respuesta.println("            <li><a href=\"#\">Export</a></li>"); 
		respuesta.println("          </ul>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main\">"); 
		respuesta.println("          <h1 class=\"page-header\">Cupi-Admin</h1>"); 
		respuesta.println(""); 
		respuesta.println("          <div class=\"row placeholders\">"); 
		respuesta.println("            <div class=\"col-xs-6 col-sm-3 placeholder\">"); 
		respuesta.println("              <img src='http://flagpedia.net/data/flags/normal/"+ paises[(int)(Math.random()*7)] + ".png' style='height:200px;' width='200px' class=\"img-responsive\" alt=\"Generic placeholder thumbnail\">"); 
		respuesta.println("              <h4>Label</h4>"); 
		respuesta.println("              <span class=\"text-muted\">Something else</span>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"col-xs-6 col-sm-3 placeholder\">"); 
		respuesta.println("              <img src='http://flagpedia.net/data/flags/normal/"+ paises[(int)(Math.random()*7)] + ".png' style='height:200px;' width='200px' class=\"img-responsive\" alt=\"Generic placeholder thumbnail\">"); 
		respuesta.println("              <h4>Label</h4>"); 
		respuesta.println("              <span class=\"text-muted\">Something else</span>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"col-xs-6 col-sm-3 placeholder\">"); 
		respuesta.println("              <img src='http://flagpedia.net/data/flags/normal/"+ paises[(int)(Math.random()*7)] + ".png' style='height:200px;' width='200px' class=\"img-responsive\" alt=\"Generic placeholder thumbnail\">"); 
		respuesta.println("              <h4>Label</h4>"); 
		respuesta.println("              <span class=\"text-muted\">Something else</span>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"col-xs-6 col-sm-3 placeholder\">"); 
		respuesta.println("              <img src='http://flagpedia.net/data/flags/normal/"+ paises[(int)(Math.random()*7)] + ".png' style='height:200px;' width='200px' class=\"img-responsive\" alt=\"Generic placeholder thumbnail\">"); 
		respuesta.println("              <h4>Label</h4>"); 
		respuesta.println("              <span class=\"text-muted\">Something else</span>"); 
		respuesta.println("            </div>"); 
		respuesta.println("          </div>"); 
		respuesta.println(""); 
		respuesta.println("          <h2 class=\"sub-header\">Vuelos Agregados</h2>"); 
		respuesta.println("          <div class=\"table-responsive\">"); 
		respuesta.println("            <table class=\"table table-striped\">"); 
		respuesta.println("              <thead>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <th>#</th>"); 
		respuesta.println("                  <th>Salida</th>"); 
		respuesta.println("                  <th>Llegada</th>"); 
		respuesta.println("                  <th>Aereolinea</th>"); 
		respuesta.println("                  <th>Rating</th>"); 
		respuesta.println("                  <th>Tipo</th>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("              </thead>"); 
		respuesta.println("              <tbody>"); 
			
		Iterator<Vuelo> vuelos = central.darVuelos();
		while(vuelos.hasNext()){
			Vuelo actual = vuelos.next();
			
			respuesta.println("                <tr>"); 
			respuesta.println("                  <td><a href=\"vuelo.html?codigo="+actual.getNumero()+"\">"+actual.getNumero()+"</a></td>"); 
			respuesta.println("                  <td>"+actual.getSalida().getNombre()+"</td>"); 
			respuesta.println("                  <td>"+actual.getLlegada().getNombre()+"</td>"); 
			respuesta.println("                  <td>"+actual.getAereolinea()+"</td>");
			respuesta.println("                  <td>"+actual.getRating()+"</td>");
			respuesta.println("                  <td>"+actual.getTipo()+"</td>"); 
			respuesta.println("                </tr>"); 
		}
		
		respuesta.println("              </tbody>"); 
		respuesta.println("            </table>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>");
	}

	private void imprimirPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		imprimirEncabezado(request, response); 
		
		String usuario = request.getParameter( "usuario" );
        String contrasenia = request.getParameter( "constrasena" );
		respuesta.println("<h1>" + usuario + "</h1>");
		respuesta.println("<h2>" + contrasenia + "</h2>");
		
		respuesta.println("</body>"); 
		respuesta.println("</html>");
	}
	
	private void imprimirEncabezado(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<html>"); 
		respuesta.println("<head>"); 
		respuesta.println("	<title>Page Title</title>"); 
		respuesta.println("	<script src=\"http://code.jquery.com/jquery-1.11.0.min.js\"></script>"); 
		respuesta.println("	<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css\">"); 
		respuesta.println("	<script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js\"></script>"); 
		respuesta.println("	<script type=\"text/javascript\">"); 
		respuesta.println("			$( document ).ready(function() {"); 
		respuesta.println("    			$(\"#pr\").click(function(){"); 
		respuesta.println("					alert(\"Hello human!\");"); 
		respuesta.println("				});"); 
		respuesta.println("			});"); 
		respuesta.println("	</script>"); 
		respuesta.println("	<style type=\"text/css\">"); 
		respuesta.println("		body {"); 
		respuesta.println("		  padding-top: 50px;"); 
		respuesta.println("		}"); 
		respuesta.println(""); 
		respuesta.println("		/*"); 
		respuesta.println("		 * Global add-ons"); 
		respuesta.println("		 */"); 
		respuesta.println(""); 
		respuesta.println("		.sub-header {"); 
		respuesta.println("		  padding-bottom: 10px;"); 
		respuesta.println("		  border-bottom: 1px solid #eee;"); 
		respuesta.println("		}"); 
		respuesta.println(""); 
		respuesta.println("		/*"); 
		respuesta.println("		 * Sidebar"); 
		respuesta.println("		 */"); 
		respuesta.println(""); 
		respuesta.println("		/* Hide for mobile, show later */"); 
		respuesta.println("		.sidebar {"); 
		respuesta.println("		  display: none;"); 
		respuesta.println("		}"); 
		respuesta.println("		@media (min-width: 768px) {"); 
		respuesta.println("		  .sidebar {"); 
		respuesta.println("		    position: fixed;"); 
		respuesta.println("		    top: 51px;"); 
		respuesta.println("		    bottom: 0;"); 
		respuesta.println("		    left: 0;"); 
		respuesta.println("		    z-index: 1000;"); 
		respuesta.println("		    display: block;"); 
		respuesta.println("		    padding: 20px;"); 
		respuesta.println("		    overflow-x: hidden;"); 
		respuesta.println("		    overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */"); 
		respuesta.println("		    background-color: #f5f5f5;"); 
		respuesta.println("		    border-right: 1px solid #eee;"); 
		respuesta.println("		  }"); 
		respuesta.println("		}"); 
		respuesta.println(""); 
		respuesta.println("		/* Sidebar navigation */"); 
		respuesta.println("		.nav-sidebar {"); 
		respuesta.println("		  margin-right: -21px; /* 20px padding + 1px border */"); 
		respuesta.println("		  margin-bottom: 20px;"); 
		respuesta.println("		  margin-left: -20px;"); 
		respuesta.println("		}"); 
		respuesta.println("		.nav-sidebar > li > a {"); 
		respuesta.println("		  padding-right: 20px;"); 
		respuesta.println("		  padding-left: 20px;"); 
		respuesta.println("		}"); 
		respuesta.println("		.nav-sidebar > .active > a {"); 
		respuesta.println("		  color: #fff;"); 
		respuesta.println("		  background-color: #428bca;"); 
		respuesta.println("		}"); 
		respuesta.println(""); 
		respuesta.println(""); 
		respuesta.println("		/*"); 
		respuesta.println("		 * Main content"); 
		respuesta.println("		 */"); 
		respuesta.println(""); 
		respuesta.println("		.main {"); 
		respuesta.println("		  padding: 20px;"); 
		respuesta.println("		}"); 
		respuesta.println("		@media (min-width: 768px) {"); 
		respuesta.println("		  .main {"); 
		respuesta.println("		    padding-right: 40px;"); 
		respuesta.println("		    padding-left: 40px;"); 
		respuesta.println("		  }"); 
		respuesta.println("		}"); 
		respuesta.println("		.main .page-header {"); 
		respuesta.println("		  margin-top: 0;"); 
		respuesta.println("		}"); 
		respuesta.println(""); 
		respuesta.println(""); 
		respuesta.println("		/*"); 
		respuesta.println("		 * Placeholder dashboard ideas"); 
		respuesta.println("		 */"); 
		respuesta.println(""); 
		respuesta.println("		.placeholders {"); 
		respuesta.println("		  margin-bottom: 30px;"); 
		respuesta.println("		  text-align: center;"); 
		respuesta.println("		}"); 
		respuesta.println("		.placeholders h4 {"); 
		respuesta.println("		  margin-bottom: 0;"); 
		respuesta.println("		}"); 
		respuesta.println("		.placeholder {"); 
		respuesta.println("		  margin-bottom: 20px;"); 
		respuesta.println("		}"); 
		respuesta.println("		.placeholder img {"); 
		respuesta.println("		  display: inline-block;"); 
		respuesta.println("		  border-radius: 50%;"); 
		respuesta.println("		}"); 
		respuesta.println(""); 
		respuesta.println("	</style>"); 
		respuesta.println("</head>"); 
		respuesta.println(""); 
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
		respuesta.println("            <li><a href=\"#contact\">Contact</a></li>"); 
		respuesta.println("          </ul>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"navbar-collapse collapse\">"); 
		respuesta.println("          <form class=\"navbar-form navbar-right\" role=\"form\" action=\"\" method=\"POST\">"); 
		respuesta.println("            <input type=\"hidden\" name=\"cerrar\" value=\"si\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-success\">Cerrar sesion</button>"); 
		respuesta.println("          </form>"); 
		respuesta.println("        </div><!--/.navbar-collapse -->"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
	}
	
	private void imprimirFooter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
 
		respuesta.println("</body>"); 
		respuesta.println("</html>"); 
	}
}
