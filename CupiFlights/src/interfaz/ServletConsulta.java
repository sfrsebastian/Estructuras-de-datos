package interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.CentralDeVuelos;

public class ServletConsulta extends HttpServlet { 
	
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
		imprimirEncabezado(request, response);
		imprimirContenido(request, response);
		imprimirFooter(request, response);
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		imprimirEncabezado(request, response);
		imprimirContenido(request, response);
		imprimirFooter(request, response);
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
		respuesta.println("  <title>Page Title</title>"); 
		respuesta.println("  <script src=\"http://code.jquery.com/jquery-1.11.0.min.js\"></script>"); 
		respuesta.println("  <link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css\">"); 
		respuesta.println("  <script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js\"></script>"); 
		respuesta.println("  <script type=\"text/javascript\">"); 
		respuesta.println("      $( document ).ready(function() {"); 
		respuesta.println("          $(\"h2\").click(function(){"); 
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
		respuesta.println("            <li class=\"active\"><a href=\"consulta.html\">Consulta</a></li>"); 
		respuesta.println("            <li><a href=\"#contact\">Contact</a></li>"); 
		respuesta.println("          </ul>"); 
		respuesta.println("        </div>"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>");
	}
	
	private void imprimirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<div class=\"container\">"); 
		respuesta.println("      <h2>Consultas</h2>"); 
		respuesta.println("      <hr>"); 
		respuesta.println("      <h4 style=\"margin-bottom:30px;\">Consultar por Aeropuerto</h4>"); 
		respuesta.println("      <form class=\"form-horizontal\" role=\"form\" action=\"resultado.html\" method=\"POST\">"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <label for=\"codigoAeropuerto\" class=\"col-sm-2 control-label\">Codigo</label>"); 
		respuesta.println("          <div class=\"col-sm-10\">"); 
		respuesta.println("            <input type=\"email\" class=\"form-control\" id=\"codigoAeropuerto\" placeholder=\"Codigo del Aeropuerto\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <label for=\"fechaInicialA\" class=\"col-sm-2 control-label\">Fecha inicial</label>"); 
		respuesta.println("          <div class=\"col-sm-4\">"); 
		respuesta.println("            <input type=\"text\" class=\"form-control\" id=\"fechaInicialA\" placeholder=\"Fecha inicial\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("          <label for=\"fechaFinalA\" class=\"col-sm-2 control-label\">Fecha final</label>"); 
		respuesta.println("          <div class=\"col-sm-4\">"); 
		respuesta.println("            <input type=\"text\" class=\"form-control\" id=\"fechaFinalA\" placeholder=\"Fecha final\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <div class=\"col-sm-offset-2 col-sm-10\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-info\">Consultar!</button>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <input type=\"hidden\" name=\"pedido\" value=\"aeropuerto\">"); 
		respuesta.println("      </form>"); 
		respuesta.println(""); 
		respuesta.println("      <hr>"); 
		respuesta.println("      <h4 style=\"margin-bottom:30px;\">Consultar por Vuelo</h4>"); 
		respuesta.println("      <form class=\"form-horizontal\" role=\"form\">"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">Codigo</label>"); 
		respuesta.println("          <div class=\"col-sm-10\">"); 
		respuesta.println("            <input type=\"text\" class=\"form-control\" id=\"inputEmail3\" placeholder=\"Codigo del vuelo\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <div class=\"col-sm-offset-2 col-sm-10\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-info\">Consultar!</button>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <input type=\"hidden\" name=\"pedido\" value=\"vuelo\">"); 
		respuesta.println("      </form>"); 
		respuesta.println(""); 
		respuesta.println("      <hr>"); 
		respuesta.println("      <h4 style=\"margin-bottom:30px;\">Fechas Disponibles</h4>"); 
		respuesta.println("        <table class=\"table table-bordered\">"); 
		respuesta.println("          <tr>"); 
		respuesta.println("            <th>Lunes</th>"); 
		respuesta.println("            <th>Martes</th>"); 
		respuesta.println("            <th>Miercoles</th>"); 
		respuesta.println("            <th>Jueves</th>"); 
		respuesta.println("            <th>Viernes</th>"); 
		respuesta.println("            <th>Sabado</th>"); 
		respuesta.println("            <th>Domingo</th>"); 
		respuesta.println("          </tr>"); 
		respuesta.println("          <tr>"); 
		respuesta.println("            <td class=\"success\">21</td>"); 
		respuesta.println("            <td>22</td>"); 
		respuesta.println("            <td>23</td>"); 
		respuesta.println("            <td>24</td>"); 
		respuesta.println("            <td class=\"success\">25</td>"); 
		respuesta.println("            <td>26</td>"); 
		respuesta.println("            <td>27</td>"); 
		respuesta.println(""); 
		respuesta.println("          </tr>"); 
		respuesta.println("          <tr>"); 
		respuesta.println("            <td>28</td>"); 
		respuesta.println("            <td>29</td>"); 
		respuesta.println("            <td class=\"success\">30</td>"); 
		respuesta.println("            <td>1</td>"); 
		respuesta.println("            <td class=\"success\">2</td>"); 
		respuesta.println("            <td class=\"success\">3</td>"); 
		respuesta.println("            <td>4</td>"); 
		respuesta.println("          </tr>"); 
		respuesta.println("          <tr>"); 
		respuesta.println("            <td class=\"success\">5</td>"); 
		respuesta.println("            <td>6</td>"); 
		respuesta.println("            <td>7</td>"); 
		respuesta.println("            <td>8</td>"); 
		respuesta.println("            <td class=\"success\">9</td>"); 
		respuesta.println("            <td>10</td>"); 
		respuesta.println("            <td>11</td>"); 
		respuesta.println("          </tr>"); 
		respuesta.println("          <tr>"); 
		respuesta.println("            <td>12</td>"); 
		respuesta.println("            <td class=\"success\">13</td>"); 
		respuesta.println("            <td>14</td>"); 
		respuesta.println("            <td>15</td>"); 
		respuesta.println("            <td>16</td>"); 
		respuesta.println("            <td class=\"success\">17</td>"); 
		respuesta.println("            <td class=\"success\">18</td>"); 
		respuesta.println("          </tr>"); 
		respuesta.println("        </table>"); 
		respuesta.println("      </form>"); 
	}
	
	private void imprimirFooter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<hr>"); 
		respuesta.println(""); 
		respuesta.println("    <footer>"); 
		respuesta.println("        <p>&copy; Company 2014</p>"); 
		respuesta.println("      </footer>"); 
		respuesta.println("    </div> <!-- /container -->"); 
		respuesta.println("</body>"); 
		respuesta.println("</html>"); 
	}
}
