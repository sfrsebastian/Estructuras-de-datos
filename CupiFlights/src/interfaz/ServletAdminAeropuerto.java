package interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mundo.Aeropuerto;
import mundo.CentralDeVuelos;
import mundo.Vuelo;

public class ServletAdminAeropuerto extends HttpServlet {

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
		HttpSession sesion = request.getSession();
		if(sesion.getAttribute("usuario") == null){
		response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
		}else{
			
			imprimirEncabezado(request, response);
			imprimirContenidoPost(request, response);
			imprimirFooter(request, response);
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
	
	private void imprimirContenidoPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<div class=\"container-fluid\">"); 
		respuesta.println("      <div class=\"row\">"); 
		respuesta.println("        <div class=\"col-sm-3 col-md-2 sidebar\">"); 
		respuesta.println("          <ul class=\"nav nav-sidebar\">"); 
		respuesta.println("            <li><a href=\"admin.html\">General</a></li>"); 
		respuesta.println("            <li><a href=\"agregar.html\">Agregar</a></li>"); 
		respuesta.println("            <li class=\"active\"><a href=\"admin-aeropuerto.html\">Aeropuertos</a></li>"); 
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
		respuesta.println(""); 
		respuesta.println(""); 
		respuesta.println("        <div class=\"col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main\">"); 
		respuesta.println("          <h1 style=\"padding-bottom: 9px; margin: 0px 0px 0px 0px;\">Aeropuertos</h1>"); 
		respuesta.println("          <p>Permite visualizar la lista total de los aeropuertos agregados y la lista con respecto a un rango de tiempo.</p>"); 
		respuesta.println("          <hr>"); 
		respuesta.println(""); 
		respuesta.println("          <!--<h3 class=\"sub-header\" style=\"margin-bottom:30px;\">Vuelos Agregados</h3>-->"); 
		respuesta.println(""); 
		respuesta.println("          <div class=\"row\" style=\"margin-bottom:15px;\">"); 
		respuesta.println("              <form class=\"form-inline\" role=\"form\" method=\"POST\" action=\"\">"); 
		respuesta.println("              <div class=\"col-md-2\">"); 
		respuesta.println("                <p>Buscar por fecha:</p>"); 
		respuesta.println("              </div>"); 
		respuesta.println("            <div class=\"form-group col-md-3\">"); 
		respuesta.println("                <label class=\"sr-only\" for=\"fi\">Fecha inicial</label>"); 
		respuesta.println("                <input type=\"text\" class=\"form-control\" id=\"fi\" placeholder=\"dd/mm/aaaa\" style=\"width:100%\" name=\"fecha\">"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"form-group col-md-2\">"); 
		respuesta.println("              <p>Buscar por codigo:</p>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"form-group col-md-3\">"); 
		respuesta.println("                <label class=\"sr-only\" for=\"fi\">Fecha inicial</label>"); 
		respuesta.println("                <input type=\"text\" class=\"form-control\" id=\"fi\" placeholder=\"codigo aeropuerto\" style=\"width:100%\" name=\"codigo\">"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"col-md-2\">"); 
		respuesta.println("              <button type=\"submit\" class=\"btn btn-info\">Buscar info</button>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            </form>"); 
		respuesta.println("          </div>"); 
		respuesta.println(""); 
		respuesta.println("           <div class=\"panel panel-default\">"); 
		respuesta.println("            <div class=\"panel-heading\">"); 
		respuesta.println("              <h3 class=\"panel-title\">Vuelos basados en fecha y codigo del aeropuerto</h3>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"panel-body\">"); 
		respuesta.println("          <div class=\"table-responsive\">"); 
		respuesta.println("            <table class=\"table table-striped\">"); 
		respuesta.println("              <thead>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <th>#</th>"); 
		respuesta.println("                  <th>Codigo</th>"); 
		respuesta.println("                  <th>Nombre</th>"); 
		respuesta.println("                  <th>Aereolinea</th>"); 
		respuesta.println("                  <th>Estado</th>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("              </thead>"); 
		respuesta.println("              <tbody>");  
		
		//imprimir aqui los resultados POST
		
		String posted = request.getParameter("fecha");
		String codigo = request.getParameter("codigo");
				
		String fechas[] = posted.split("/");
		//TODO fix
		if(posted == null || codigo == null)
			response.sendRedirect(ServletCupiFlights.RUTA + "./index.html");
				
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(fechas[2]), Integer.parseInt(fechas[1]), Integer.parseInt(fechas[0]));
		String tipo = request.getParameter("tipo");
		
		Iterator<Vuelo> i = central.consultarVuelos(c, codigo, tipo,Integer.parseInt(fechas[3]));
		int d = 1;
		while(i.hasNext() && d < 51){
			Vuelo actual = i.next();
			respuesta.println("                <tr>"); 
			respuesta.println("                  <td>" + d + "</td>"); 
			respuesta.println("                  <td>" + actual.getNumero() + "</td>"); 
			respuesta.println("                  <td>"+ actual.getCodigoLlegada() +"</td>"); 
			respuesta.println("                  <td>"+ actual.getAerolinea() +"</td>"); 
			respuesta.println("                  <td>"+ actual.getTipo() +"</td>");
			respuesta.println("                </tr>");
			d++;
		}
		
		respuesta.println("              </tbody>"); 
		respuesta.println("            </table>"); 
		respuesta.println("          </div>");
		respuesta.println("      	</div>");
		 
		respuesta.println(""); 
		respuesta.println("           <div class=\"panel panel-default\">"); 
		respuesta.println("        <div class=\"panel-heading\">"); 
		respuesta.println("          <h3 class=\"panel-title\">Aeropuertos Totales</h3>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"panel-body\">"); 
		respuesta.println("          <div class=\"table-responsive\">"); 
		respuesta.println("            <table class=\"table table-striped\">"); 
		respuesta.println("              <thead>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <th>#</th>"); 
		respuesta.println("                  <th>Codigo</th>"); 
		respuesta.println("                  <th>Nombre</th>"); 
		respuesta.println("                  <th>Aereolinea</th>"); 
		respuesta.println("                  <th>Estado</th>");
		respuesta.println("                  <th>Bandera</th>");
		respuesta.println("                </tr>"); 
		respuesta.println("              </thead>"); 
		respuesta.println("              <tbody>"); 	
		
		Iterator<Aeropuerto> i1 = central.darAeropuertos();
		int d1 = 1;
		while(i1.hasNext() && d1 < 51){
			Aeropuerto actual = i1.next();
			respuesta.println("                <tr>"); 
			respuesta.println("                  <td>" + d1 + "</td>"); 
			respuesta.println("                  <td>" + actual.getCodigo() + "</td>"); 
			respuesta.println("                  <td>"+ actual.getNombre() +"</td>"); 
			respuesta.println("                  <td>"+ actual.getCodigoPais() +"</td>"); 
			respuesta.println("                  <td>"+ actual.getTardanza() +"</td>");
			respuesta.println("                  <td><img src='http://flagpedia.net/data/flags/mini/" + (actual.getCodigoPais()).toLowerCase() + ".png'></td>");
			respuesta.println("                </tr>");
			d1++;
		}	
		respuesta.println("              </tbody>"); 
		respuesta.println("            </table>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
		respuesta.println("  </div>"); 
		respuesta.println("</div>");
	}

	private void imprimirEncabezado(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<html>"); 
		respuesta.println("<head>"); 
		respuesta.println("	<title>Admin-Aeropuertos</title>"); 
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
		respuesta.println("          <form class=\"navbar-form navbar-right\" role=\"form\" action=\"admin.html\" method=\"POST\">"); 
		respuesta.println("            <input type=\"hidden\" name=\"cerrar\" value=\"si\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-success\">Cerrar sesion</button>"); 
		respuesta.println("          </form>"); 
		respuesta.println("        </div><!--/.navbar-collapse -->"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
	}

	private void imprimirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<div class=\"container-fluid\">"); 
		respuesta.println("      <div class=\"row\">"); 
		respuesta.println("        <div class=\"col-sm-3 col-md-2 sidebar\">"); 
		respuesta.println("          <ul class=\"nav nav-sidebar\">"); 
		respuesta.println("            <li><a href=\"admin.html\">General</a></li>"); 
		respuesta.println("            <li><a href=\"agregar.html\">Agregar</a></li>"); 
		respuesta.println("            <li class=\"active\"><a href=\"admin-aeropuerto.html\">Aeropuertos</a></li>"); 
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
		respuesta.println(""); 
		respuesta.println(""); 
		respuesta.println("        <div class=\"col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main\">"); 
		respuesta.println("          <h1 style=\"padding-bottom: 9px; margin: 0px 0px 0px 0px;\">Aeropuertos</h1>"); 
		respuesta.println("          <p>Permite visualizar la lista total de los aeropuertos agregados y la lista con respecto a un rango de tiempo.</p>"); 
		respuesta.println("          <hr>"); 
		respuesta.println(""); 
		respuesta.println("          <!--<h3 class=\"sub-header\" style=\"margin-bottom:30px;\">Vuelos Agregados</h3>-->"); 
		respuesta.println(""); 
		respuesta.println("          <div class=\"row\" style=\"margin-bottom:15px;\">"); 
		respuesta.println("              <form class=\"form-inline\" role=\"form\" method=\"POST\" action=\"\">"); 
		respuesta.println("              <div class=\"col-md-2\">"); 
		respuesta.println("                <p>Buscar por fecha:</p>"); 
		respuesta.println("              </div>"); 
		respuesta.println("            <div class=\"form-group col-md-2\">"); 
		respuesta.println("                <label class=\"sr-only\" for=\"fi\">Fecha inicial</label>"); 
		respuesta.println("                <input type=\"text\" class=\"form-control\" id=\"fi\" placeholder=\"dd/mm/aaaa\" style=\"width:100%\" name=\"fecha\">"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"form-group col-md-2\">"); 
		respuesta.println("              <p>Buscar por codigo:</p>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"form-group col-md-2\">"); 
		respuesta.println("                <label class=\"sr-only\" for=\"fi\">Fecha inicial</label>"); 
		respuesta.println("                <input type=\"text\" class=\"form-control\" id=\"fi\" placeholder=\"codigo aeropuerto\" style=\"width:100%\" name=\"codigo\">"); 
		respuesta.println("            </div>"); 
		//
		respuesta.println("            <div class=\"form-group col-md-2\">"); 
		respuesta.println("                <label class=\"sr-only\" for=\"tipo\">Tipo</label>"); 
		respuesta.println("                <select name='tipo'>"); 
		respuesta.println("                	<option value='" + Vuelo.LLEGANDO + "'>" + Vuelo.LLEGANDO + "</option>");
		respuesta.println("                	<option value='" + Vuelo.SALIENDO + "'>" + Vuelo.SALIENDO + "</option>");
		respuesta.println("                </select>"); 
		respuesta.println("            </div>"); 
		//
		
		respuesta.println("            <div class=\"col-md-2\">"); 
		respuesta.println("              <button type=\"submit\" class=\"btn btn-info\">Buscar info</button>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            </form>"); 
		respuesta.println("          </div>"); 
		respuesta.println(""); 
		respuesta.println("           <div class=\"panel panel-default\">"); 
		respuesta.println("            <div class=\"panel-heading\">"); 
		respuesta.println("              <h3 class=\"panel-title\">Vuelos basados en fecha y codigo del aeropuerto</h3>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"panel-body\">"); 
		respuesta.println("              Seleccione una fecha para buscar aeropuertos. Despu&eacute;s, seleccione el codigo del aeropuerto. Se seleccionaran los vuelos 4 dias antes y despu&eacute;s de la fecha indicada del aeropuerto indicado."); 
		respuesta.println("            </div>"); 
		respuesta.println("          </div>"); 
		respuesta.println(""); 
		respuesta.println("           <div class=\"panel panel-default\">"); 
		respuesta.println("        <div class=\"panel-heading\">"); 
		respuesta.println("          <h3 class=\"panel-title\">Aeropuertos Totales</h3>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"panel-body\">"); 
		respuesta.println("          <div class=\"table-responsive\">"); 
		respuesta.println("            <table class=\"table table-striped\">"); 
		respuesta.println("              <thead>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <th>#</th>"); 
		respuesta.println("                  <th>Codigo</th>"); 
		respuesta.println("                  <th>Nombre</th>"); 
		respuesta.println("                  <th>Aereolinea</th>"); 
		respuesta.println("                  <th>Tardanza</th>");
		respuesta.println("                  <th>Bandera</th>");
		respuesta.println("                </tr>"); 
		respuesta.println("              </thead>"); 
		respuesta.println("              <tbody>");		
		
		Iterator<Aeropuerto> i = central.darAeropuertos();
		int d = 1;
		while(i.hasNext() && d < 51){
			Aeropuerto actual = i.next();
			respuesta.println("                <tr>"); 
			respuesta.println("                  <td>" + d + "</td>"); 
			respuesta.println("                  <td>" + actual.getCodigo() + "</td>"); 
			respuesta.println("                  <td>"+ actual.getNombre() +"</td>"); 
			respuesta.println("                  <td>"+ actual.getCodigoPais() +"</td>"); 
			respuesta.println("                  <td>"+ actual.getTardanza() +"</td>");
			respuesta.println("                  <td><img src='http://flagpedia.net/data/flags/mini/" + (actual.getCodigoPais()).toLowerCase() + ".png'></td>");
			respuesta.println("                </tr>");
			d++;
		}
		
		respuesta.println("              </tbody>"); 
		respuesta.println("            </table>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
		respuesta.println("  </div>"); 
		respuesta.println("</div>");
	}

	private void imprimirFooter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("</body>"); 
		respuesta.println("</html>");
	}
}
