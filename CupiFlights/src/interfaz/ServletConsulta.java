package interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.CentralDeVuelos;
import mundo.Vuelo;

public class ServletConsulta extends HttpServlet { 
	
	//--------------------------------------------
	// Atributos
	//--------------------------------------------
	
	private CentralDeVuelos central;
	
	private String tituloPagina;
	
	//--------------------------------------------
	// Constructor
	//--------------------------------------------
	
	/**
	 * Inicializa el Servlet
	 */
	public void init( ) throws ServletException
    {
		tituloPagina = "Consulta";
        try {
			central = CentralDeVuelos.getInstance( );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		respuesta.println("  <title>Consulta</title>"); 
		respuesta.println("  <script src=\"http://code.jquery.com/jquery-1.11.0.min.js\"></script>"); 
		respuesta.println("  <link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css\">"); 
		respuesta.println("  <script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js\"></script>"); 
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
		respuesta.println("            <li><a href=\"general.html\">General</a></li>"); 
		respuesta.println("          </ul>"); 
		respuesta.println("        </div>"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
	}
	
	private void imprimirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("    <div class=\"container\">"); 
		respuesta.println("      <h2>Consultas</h2>"); 
		respuesta.println("      <hr>"); 
		respuesta.println("      <h4 style=\"margin-bottom:30px;\">Consultar por Aeropuerto</h4>"); 
		respuesta.println("      <form class=\"form-horizontal\" role=\"form\" action=\"resultado.html\" method=\"POST\">"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <label for=\"codigoAeropuerto\" class=\"col-sm-2 control-label\">Codigo</label>"); 
		respuesta.println("          <div class=\"col-sm-10\">"); 
		respuesta.println("            <input type=\"text\" class=\"form-control\" name=\"codigoAeropuerto\" id=\"codigoAeropuerto\" placeholder=\"Codigo del Aeropuerto\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <label for=\"fechaInicialA\" class=\"col-sm-2 control-label\">Fecha</label>"); 
		respuesta.println("          <div class=\"col-sm-4\">"); 
		
		respuesta.println("            <select name=\"fecha\" class=\"form-control\">"); 
		Iterator<String> fechas = central.darFechas();
		while(fechas.hasNext()){
			String actual = fechas.next();
			respuesta.println("            <option value=\""+actual+"\">"+actual+"</option>");
		}
		respuesta.println("            </select>");
		
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <label for=\"tipo\" class=\"col-sm-2 control-label\">Tipo</label>"); 
		respuesta.println("          <div class=\"col-sm-4\">"); 
		respuesta.println("            <select name=\"tipo\" class=\"form-control\" id=\"tipo\">");
		respuesta.println("            <option value=\""+Vuelo.LLEGANDO+"\">"+Vuelo.LLEGANDO+"</option>");
		respuesta.println("            <option value=\""+Vuelo.SALIENDO+"\">"+Vuelo.SALIENDO+"</option>");
		respuesta.println("            </select>"); 
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
		respuesta.println("      <form class=\"form-horizontal\" role=\"form\" method=\"POST\" action=\"resultado.html\">"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">Codigo</label>"); 
		respuesta.println("          <div class=\"col-sm-10\">"); 
		respuesta.println("            <input type=\"text\" class=\"form-control\" id=\"inputEmail3\" name=\"codigo\" placeholder=\"Codigo del vuelo\">"); 
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
		respuesta.println("      <h4 style=\"margin-bottom:30px;\">Consultar vuelos por calificacion</h4>"); 
		respuesta.println("      <form class=\"form-horizontal\" role=\"form\" method=\"POST\" action=\"resultado.html\">"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">Calificacion:</label>"); 
		respuesta.println("          <div class=\"col-sm-10\">"); 
		respuesta.println("            <input type=\"text\" name=\"calificacion\" class=\"form-control\" id=\"inputEmail3\" placeholder=\"Rango de calificacion\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <label for=\"fechaInicialA\" class=\"col-sm-2 control-label\">Fecha Inicial</label>"); 
		respuesta.println("          <div class=\"col-sm-4\">");  
		
		respuesta.println("            <select name=\"fechaInicial\" class=\"form-control\">"); 
		fechas = central.darFechas();
		while(fechas.hasNext()){
			String actual = fechas.next();
			respuesta.println("            <option value=\""+actual+"\">"+actual+"</option>");
		}
		respuesta.println("            </select>"); 
		
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <label for=\"fechaInicialA\" class=\"col-sm-2 control-label\">Fecha Final</label>"); 
		respuesta.println("          <div class=\"col-sm-4\">"); 
		
		respuesta.println("            <select name=\"fechaFinal\" class=\"form-control\">"); 
		Iterator<String> i = central.darFechas();
		while(i.hasNext()){
			String actual = i.next();
			respuesta.println("            <option value=\""+actual+"\">"+actual+"</option>");
		}
		respuesta.println("            </select>"); 
		
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <div class=\"col-sm-offset-2 col-sm-10\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-info\">Consultar!</button>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>");
		respuesta.println("        <input type=\"hidden\" name=\"pedido\" value=\"calificacion\">"); 
		respuesta.println("      </form>"); 
		respuesta.println(""); 
		
		
		//Consulta de los vuelos tardios
		
		respuesta.println("      <hr>"); 
		respuesta.println("      <h4 style=\"margin-bottom:30px;\">Consultar vuelos tardios de un aeropuerto</h4>"); 
		respuesta.println("      <form class=\"form-horizontal\" role=\"form\" method=\"POST\" action=\"resultado.html\">"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">Codigo:</label>"); 
		respuesta.println("          <div class=\"col-sm-10\">"); 
		respuesta.println("            <input type=\"text\" name=\"codigo\" class=\"form-control\" id=\"inputEmail3\" placeholder=\"Codigo\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <label for=\"fechaInicialA\" class=\"col-sm-2 control-label\">Fecha Inicial</label>"); 
		respuesta.println("          <div class=\"col-sm-4\">");  
		
		respuesta.println("            <select name=\"fechaInicial\" class=\"form-control\">"); 
		fechas = central.darFechas();
		while(fechas.hasNext()){
			String actual = fechas.next();
			respuesta.println("            <option value=\""+actual+"\">"+actual+"</option>");
		}
		respuesta.println("            </select>"); 
		
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <label for=\"fechaInicialA\" class=\"col-sm-2 control-label\">Fecha Final</label>"); 
		respuesta.println("          <div class=\"col-sm-4\">"); 
		
		respuesta.println("            <select name=\"fechaFinal\" class=\"form-control\">"); 
		Iterator<String> i1 = central.darFechas();
		while(i1.hasNext()){
			String actual = i1.next();
			respuesta.println("            <option value=\""+actual+"\">"+actual+"</option>");
		}
		respuesta.println("            </select>"); 
		
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"form-group\">"); 
		respuesta.println("          <div class=\"col-sm-offset-2 col-sm-10\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-info\">Consultar!</button>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>");
		respuesta.println("        <input type=\"hidden\" name=\"pedido\" value=\"tardios\">"); 
		respuesta.println("      </form>"); 
		respuesta.println(""); 
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
