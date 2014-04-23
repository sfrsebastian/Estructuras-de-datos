package interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mundo.CentralDeVuelos;

public class ServletAdminAgregar extends HttpServlet {

	//--------------------------------------------
	// Atributos
	//--------------------------------------------
	
	private CentralDeVuelos central;
	
	//--------------------------------------------
	// Constructor
	//--------------------------------------------
	
	/**
	 * Inicializa el Servlet
	 */
	public void init( ) throws ServletException
    {
        central = CentralDeVuelos.getInstance( );
    }
	
	//--------------------------------------------
	// Metodos
	//--------------------------------------------
	
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		//if no session, redirect
		HttpSession session = request.getSession();
		if(session.getAttribute("usuario") == null){
			System.out.println("No hay sesion para pagina: agregar");
			response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
		}else{
			imprimirEncabezado(request, response);
			imprimirContenido(request, response);
			imprimirFooter(request, response);
		}
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		//Maneja casos de post de vuelos
		HttpSession session = request.getSession();
		if(session.getAttribute("usuario") == null){
			//if no session, redirect
			response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
		}else{
			String tipo = request.getParameter("tipo");
			if(tipo.equals("agregar-aeropuerto")){
				String codigo = request.getParameter("codigo");
				//TODO agregar los vuelos del aeropuerto
				try {
					central.agregarAeropuerto(codigo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Mostrar mensaje de exito/error dependiendo del resultado
				//TODO mostrar mensaje de exito
				imprimirEncabezado(request, response);
				imprimirContenido(request, response);
				imprimirFooter(request, response);
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
	
	private void imprimirEncabezado(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<html>"); 
		respuesta.println("<head>"); 
		respuesta.println("	<title>Page Title</title>"); 
		respuesta.println("	<script src=\"http://code.jquery.com/jquery-1.11.0.min.js\"></script>"); 
		respuesta.println("	<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css\">"); 
		respuesta.println("	<script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js\"></script>"); 
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
	}
	
	private void imprimirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
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
		respuesta.println("          <form class=\"navbar-form navbar-right\" role=\"form\" action=\"admin.html\" method=\"POST\">"); 
		respuesta.println("            <input type=\"hidden\" name=\"cerrar\" value=\"si\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-success\">Cerrar sesion</button>"); 
		respuesta.println("          </form>"); 
		respuesta.println("        </div><!--/.navbar-collapse -->"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
		respuesta.println(""); 
		respuesta.println("    <div class=\"container-fluid\">"); 
		respuesta.println("      <div class=\"row\">"); 
		respuesta.println("        <div class=\"col-sm-3 col-md-2 sidebar\">"); 
		respuesta.println("          <ul class=\"nav nav-sidebar\">"); 
		respuesta.println("            <li><a href=\"admin.html\">General</a></li>"); 
		respuesta.println("            <li class=\"active\"><a href=\"agregar.html\">Agregar</a></li>"); 
		respuesta.println("            <li><a href=\"admin-aeropuerto.html\">Aeropuertos</a></li>"); 
		respuesta.println("            <li><a href=\"admin_vuelos.html\">Export</a></li>"); 
		respuesta.println("          </ul>"); 
		respuesta.println("          <ul class=\"nav nav-sidebar\">"); 
		respuesta.println("            <li><a href=\"\">Nav item</a></li>"); 
		respuesta.println("            <li><a href=\"\">Nav item again</a></li>"); 
		respuesta.println("            <li><a href=\"\">One more nav</a></li>"); 
		respuesta.println("            <li><a href=\"\">Another nav item</a></li>"); 
		respuesta.println("            <li><a href=\"\">More navigation</a></li>"); 
		respuesta.println("          </ul>"); 
		respuesta.println("          <ul class=\"nav nav-sidebar\">"); 
		respuesta.println("            <li><a href=\"\">Nav item again</a></li>"); 
		respuesta.println("            <li><a href=\"\">One more nav</a></li>"); 
		respuesta.println("            <li><a href=\"\">Another nav item</a></li>"); 
		respuesta.println("          </ul>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main\">"); 
		respuesta.println("          <h1 style=\"padding-bottom: 9px; margin: 0px 0px 0px 0px;\">Agregar aeropuertos</h1>"); 
		respuesta.println("          <p>Puede agregar aeropuertos seleccionado las opciones abajo</p>"); 
		respuesta.println("          <hr>"); 
		respuesta.println(""); 
		respuesta.println("          <!--<h3 class=\"sub-header\" style=\"margin-bottom:30px;\">Vuelos Agregados</h3>-->"); 
		respuesta.println("            <div>"); 
		respuesta.println("                <form class=\"form-horizontal\" role=\"form\" action=\"\" method=\"POST\">"); 
		respuesta.println("                  <input type=\"hidden\" name=\"tipo\" value=\"agregar-aeropuerto\">"); 
		respuesta.println("                  <div class=\"form-group\">"); 
		respuesta.println("                    <label for=\"inputEmail3\" class=\"col-sm-3 control-label\">Codigo del Aeropuerto</label>"); 
		respuesta.println("                    <div class=\"col-sm-9\">"); 
		respuesta.println("                      <input type=\"text\" class=\"form-control\" id=\"inputEmail3\" placeholder=\"Codigo\" name=\"codigo\">"); 
		respuesta.println("                    </div>"); 
		respuesta.println("                  </div>"); 
		respuesta.println("                  <div class=\"form-group\">"); 
		respuesta.println("                    <div class=\"col-sm-offset-3 col-sm-9\">"); 
		respuesta.println("                      <button type=\"submit\" class=\"btn btn-info\">Agregar</button>"); 
		respuesta.println("                    </div>"); 
		respuesta.println("                  </div>"); 
		respuesta.println("                </form>"); 
		respuesta.println("            </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
	}
	
	private void imprimirFooter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
 
		respuesta.println("</body>"); 
		respuesta.println("</html>"); 
	}
}
