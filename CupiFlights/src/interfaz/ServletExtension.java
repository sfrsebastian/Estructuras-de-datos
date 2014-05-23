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

public class ServletExtension extends HttpServlet{
	
	//TODO El Servlet ya tiene mapping, url = /extension.html
	
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
		imprimirEncabezado(request, response);
		imprimirContenido(request, response);
		imprimirFooter(request, response);
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		if(session == null){
			//La sesion es nula, si se quiere se puede redireccionar descomentando la siguiente linea
			//response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
		}else{
			
			/*
			 * Aqui puede recibir POST requests donde:
			 * Haya un <input type="hidden" name="pedido" value="NOMBRE_METODO"> dentro del formulario
			 * De modo que se compara pedido para realizar alguna accion
			 */
			String pedido = request.getParameter("pedido");
			if(pedido.equals("EJ: darTourMasComplejo")){
				imprimirEncabezado(request, response);
				//imprimirRequerimiento(request, response);
				imprimirFooter(request, response);
			}else if(pedido.equals("OTRA COSA")){
				imprimirEncabezado(request, response);
				imprimirContenido(request, response);
				imprimirFooter(request, response);
			}//Agregar otros pedidos
		}
	}
	
	private void imprimirEncabezado(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<html>"); 
		respuesta.println("<head>"); 
		respuesta.println("  <title>Usuario</title>"); 
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
		respuesta.println("  <script type=\"text/javascript\">"); 
		respuesta.println("    $(document).ready(function(){"); 
		respuesta.println("      $(\"#but\").click(function(){"); 
		respuesta.println("        $(\"#myModal\").modal();"); 
		respuesta.println("      });"); 
		respuesta.println("    });"); 
		respuesta.println("    "); 
		respuesta.println("  </script>"); 
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
		respuesta.println("            <li><a href=\"usuario.html\">Usuario</a></li>");
		respuesta.println("          </ul>"); 
		respuesta.println("        </div>"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>");
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
	
	/*
	 * FORMULARIOS DE AYUDA
	 * 
	 * SELECT:
	 * 
	 * FALTA ENCAPSULAR EN FORM--------------
	 * 		respuesta.println("          <div class=\"form-group\">"); 
		
		respuesta.println("            <label for=\"ag\">Agregar</label>");
		respuesta.println("                <select id=\"ag\" name=\"codigo\" class=\"form-control\">"); 
		Iterator<Aeropuerto> ia1 = central.darAeropuertos();
		while(ia1.hasNext()){
			Aeropuerto actual = ia1.next();
			respuesta.println("                  <option value=\""+ actual.getCodigo() +"\">"+actual.getNombre()+"</option>");
		} 
		respuesta.println("                </select>");
		 
		respuesta.println("            <input type=\"hidden\" name=\"pedido\" value=\"agregarAeropuerto\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-primary\" style=\"margin-top:10px;\">Agregar Aeropuerto</button>"); 
		respuesta.println("          </div>");
		
	 * CERRAR FORM---------------------------
	 *
	 * CAJA DE TEXTO:-------------------------------------------------------------------------
	 * 
	 * 		respuesta.println("      <form class=\"form-horizontal\" role=\"form\" method=\"POST\" action=\"resultado.html\">"); 
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
		respuesta.println("        <input type=\"hidden\" name=\"pedido\" value=\"vuelo\">"); PEDIDO!
		respuesta.println("      </form>"); 
	 *
	 *
	 * TABLA:-------------------------------------------------------------------------
	 * 
	 * 		respuesta.println("                  <div class=\"table-responsive\">"); 
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
	 */
}
