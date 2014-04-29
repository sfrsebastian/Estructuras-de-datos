package interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.CentralDeVuelos;
import mundo.Vuelo;

public class ServletResultado extends HttpServlet {

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
			response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
		}
		
		protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
		{
			imprimirEncabezado(request, response);
			String pedido = request.getParameter("pedido");
			if(pedido == null){
				response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
			}else{
				if(pedido.equals("aeropuerto")){
					String codigo = request.getParameter("codigo");
					String fecha = request.getParameter("fecha");
					String string[] = fecha.split("/");
					
					Calendar c = Calendar.getInstance();
					c.set(Integer.parseInt(string[0]), Integer.parseInt(string[1]), Integer.parseInt(string[2]));
					
					
					Iterator<Vuelo> vuelos = null;
					try {
						vuelos = central.darVuelosAeropuertoFecha(codigo, c, Vuelo.LLEGANDO);
					} catch (Exception e) {
						
					}
					
					imprimirRequerimientoAeropuerto(request, response, vuelos);
				}else if(pedido.equals("vuelo")){
					String codigo = request.getParameter("codigo");
					Iterator<Vuelo> vuelos = central.darVuelos();
					//TODO 
				}else if(pedido.equals("calificacion")){
					String calificacion = request.getParameter("calificacion");
					
					String fechaInicial = request.getParameter("fechaInicial");
					String fechaFinal = request.getParameter("fechaFinal");
					
					String fechasi[] = fechaInicial.split("/");
					String fechasf[] = fechaFinal.split("/");
					
					Calendar c1 = Calendar.getInstance();
					c1.set(Integer.parseInt(fechasi[0]), Integer.parseInt(fechasi[1]), Integer.parseInt(fechasi[2]));
					
					Calendar c2 = Calendar.getInstance();
					c2.set(Integer.parseInt(fechasf[0]), Integer.parseInt(fechasf[1]), Integer.parseInt(fechasf[2]));
					
					Iterator<Vuelo> i = central.buscarVuelosPorCalificacion(Integer.parseInt(calificacion), c1, c2);
					imprimirRequerimientoVuelo(request, response, i);
				}
			}
			imprimirFooter(request, response);
		}
		
		@Override
		public void destroy() {
			try {
				central.guardarCentral();
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.destroy();
		}
		
		private void imprimirEncabezado(HttpServletRequest request, HttpServletResponse response) throws IOException{
			PrintWriter respuesta = response.getWriter();
			
			respuesta.println("<html>"); 
			respuesta.println("<head>"); 
			respuesta.println("  <title>General</title>"); 
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
			respuesta.println("            <li><a href=\"mapa.html\">Mapa</a></li>"); 
			respuesta.println("            <li><a href=\"general.html\">General</a></li>"); 
			respuesta.println("          </ul>"); 
			respuesta.println("        </div>"); 
			respuesta.println("<!--/.navbar-collapse -->"); 
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
			respuesta.println("        <p>&copy; Company 2014</p>"); 
			respuesta.println("      </footer>"); 
			respuesta.println("    </div> <!-- /container -->"); 
			respuesta.println("</body>"); 
			respuesta.println("</html>"); 
		}
		
		private void imprimirRequerimientoAeropuerto(HttpServletRequest request, HttpServletResponse response, Iterator<Vuelo> i) throws IOException {
			PrintWriter respuesta = response.getWriter();
			
			respuesta.println("<div class=\"container\">");
			
			respuesta.println("            <div class=\"table-responsive\">"); 
			respuesta.println("            <table class=\"table table-striped\">"); 
			respuesta.println("              <thead>"); 
			respuesta.println("                <tr>"); 
			respuesta.println("                  <th>Numero</th>"); 
			respuesta.println("                  <th>Salida</th>"); 
			respuesta.println("                  <th>Llegada</th>"); 
			respuesta.println("                  <th>Aereolinea</th>"); 
			respuesta.println("                  <th>Rating</th>");
			respuesta.println("                  <th>Tipo</th>");
			respuesta.println("                </tr>"); 
			respuesta.println("              </thead>"); 
			respuesta.println("              <tbody>"); 
			
			while(i.hasNext()){
				Vuelo actual = i.next();
				
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
			
			respuesta.println("</div>");
		}
		
		private void imprimirRequerimientoVuelo(HttpServletRequest request, HttpServletResponse response, Iterator<Vuelo> i) throws IOException{
			PrintWriter respuesta = response.getWriter();
			
			respuesta.println("<div class=\"container\">");
			
			respuesta.println("            <div class=\"table-responsive\">"); 
			respuesta.println("            <table class=\"table table-striped\">"); 
			respuesta.println("              <thead>"); 
			respuesta.println("                <tr>"); 
			respuesta.println("                  <th>Numero</th>"); 
			respuesta.println("                  <th>Salida</th>"); 
			respuesta.println("                  <th>Llegada</th>"); 
			respuesta.println("                  <th>Aereolinea</th>"); 
			respuesta.println("                  <th>Rating</th>");
			respuesta.println("                  <th>Tipo</th>");
			respuesta.println("                </tr>"); 
			respuesta.println("              </thead>"); 
			respuesta.println("              <tbody>"); 
			
			while(i.hasNext()){
				Vuelo actual = i.next();
				
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
			
			respuesta.println("</div>");
		}
		
}
