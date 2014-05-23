package interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ArbolTrie.ArbolTrie;
import mundo.Aerolinea;
import mundo.Aeropuerto;
import mundo.CentralDeVuelos;
import mundo.Vuelo;

public class ServletGeneral extends HttpServlet {

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
		tituloPagina = "General";
		try {
			central = CentralDeVuelos.getInstance( );
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

	private void imprimirEncabezado(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<html>"); 
		respuesta.println("<head>"); 
		respuesta.println("  <title>"+tituloPagina+"</title>"); 
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
		respuesta.println("            <li><a href=\"usuario.html\">Usuario</a></li>");
		respuesta.println("          </ul>"); 
		respuesta.println("        </div>"); 
		respuesta.println("<!--/.navbar-collapse -->"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
	}

	private void imprimirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<!-- Main jumbotron for a primary marketing message or call to action -->"); 
		respuesta.println("<div class=\"container\">"); 
		respuesta.println("      <div class=\"row row-offcanvas row-offcanvas-right\" style=\"margin-top: 30px;\">"); 
		respuesta.println(""); 
		respuesta.println("        <div class=\"col-xs-12 col-sm-9\">"); 
		respuesta.println("          <p class=\"pull-right visible-xs\">"); 
		respuesta.println("            <button type=\"button\" class=\"btn btn-primary btn-xs\" data-toggle=\"offcanvas\">Toggle nav</button>"); 
		respuesta.println("          </p>"); 
		respuesta.println("          <div class=\"jumbotron\">"); 
		respuesta.println("            <h1>CupiFlights General</h1>"); 
		respuesta.println("            <p>Permite mostrar toda la informaci&oacute;n que tiene el servicio CupiFlights: fechas, vuelos, aeropuertos y el mapa general. Utilice la barra de navegaci&oacute;n a la derecha para continuar.</p>"); 
		respuesta.println("          </div>"); 
		respuesta.println(""); 
		respuesta.println("          <div id=\"aeropuertos\">"); 
		respuesta.println("            <h2>Aeropuertos</h2>"); 
		respuesta.println("            <hr>"); 
		respuesta.println("            <div class=\"table-responsive\">"); 
		respuesta.println("            <table class=\"table table-striped\">"); 
		respuesta.println("              <thead>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <th>Codigo</th>");
		respuesta.println("                  <th>Nombre</th>"); 
		respuesta.println("                  <th>Ciudad</th>"); 
		respuesta.println("                  <th>Pais</th>");
		respuesta.println("                  <th>Bandera</th>");
		respuesta.println("                  <th>Calificacion</th>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("              </thead>"); 
		respuesta.println("              <tbody>"); 
		
		Iterator<Aeropuerto> aeropItera = central.darAeropuertos();
		while(aeropItera.hasNext()){
			Aeropuerto actual = aeropItera.next();
			
			respuesta.println("                <tr>"); 
			respuesta.println("                  <td><a href=\"aeropuerto.html?codigo="+actual.getCodigo()+"\">"+actual.getCodigo()+"</a></td>"); 
			respuesta.println("                  <td>"+actual.getNombre()+"</td>"); 
			respuesta.println("                  <td>"+actual.getCiudad()+"</td>"); 
			respuesta.println("                  <td>"+actual.getPais()+"</td>");
			respuesta.println("                  <td><img src=\"http://flagpedia.net/data/flags/mini/" + (actual.getCodigoPais()).toLowerCase() + ".png\"></td>");
			respuesta.println("                  <td>"+actual.getCalificacion()+"</td>"); 
			respuesta.println("                </tr>"); 
		}
		
		respuesta.println("              </tbody>"); 
		respuesta.println("            </table>"); 
		respuesta.println("          </div>"); 
		respuesta.println("          </div><!--/aeropuertos-->"); 
		respuesta.println(""); 
		respuesta.println("          <div id=\"vuelos\">"); 
		respuesta.println("            <h2>Vuelos</h2>"); 
		respuesta.println("            <hr>"); 
		respuesta.println(""); 
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
		respuesta.println("                  <th>Fecha</th>");
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
			respuesta.println("                  <td>"+actual.getFechaString()+"</td>");
			respuesta.println("                </tr>"); 
		}
		
		respuesta.println("              </tbody>"); 
		respuesta.println("            </table>"); 
		respuesta.println("          </div>"); 
		respuesta.println(""); 
		respuesta.println("          </div><!--/vuelos-->"); 
		respuesta.println(""); 
		respuesta.println("          <div id=\"mapa\">"); 
		respuesta.println("            <h2>Mapa</h2>"); 
		respuesta.println("            <hr>"); 
		
		respuesta.println("            <div class=\"table-responsive\">"); 
		respuesta.println("            <table class=\"table table-striped\">"); 
		respuesta.println("              <thead>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <th>Letra</th>"); 
		respuesta.println("                  <th>Nombre</th>"); 
		respuesta.println("                  <th>Ciudad</th>"); 
		respuesta.println("                  <th>Pais</th>"); 
		respuesta.println("                  <th>Indice de tardanza</th>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("              </thead>"); 
		respuesta.println("              <tbody>"); 
		
		Object[] aer = null;//central.darURLMapa(); TODO
		String url = (String) aer[0];
		ArbolTrie<Aeropuerto> indices = (ArbolTrie<Aeropuerto>)aer[1]; 
		
		respuesta.println("            <img style=\"-webkit-user-select: none\" src=\""+url+"\" class=\"img-responsive\">");
		//Va la tabla con los indices
		for (char j = 65; j < 92; j++) {
			try{
				Iterator<Aeropuerto> ite = indices.buscar(""+j);
				if(ite.hasNext()){
					Aeropuerto actual = ite.next();
					respuesta.println("                <tr>"); 
					respuesta.println("                  <td>" + j +"</td>"); 
					respuesta.println("                  <td>"+actual.getNombre()+"</td>"); 
					respuesta.println("                  <td>"+actual.getCiudad()+"</td>"); 
					respuesta.println("                  <td>"+actual.getPais()+"</td>"); 
					respuesta.println("                  <td>"+actual.getTardanza()+"</td>");  
					respuesta.println("                </tr>");
				}
			}catch(Exception e){

			}
		}
		
		respuesta.println("              </tbody>"); 
		respuesta.println("            </table>"); 
		respuesta.println("          </div>");
		
		respuesta.println("          </div><!--/mapa-->");  
		respuesta.println("          <div id=\"aerolineas\">"); 
		respuesta.println("            <h2>Aerolineas por Tardanza</h2>"); 
		respuesta.println("            <hr>"); 
		respuesta.println("            <div class=\"table-responsive\">"); 
		respuesta.println("            <table class=\"table table-striped\">"); 
		respuesta.println("              <thead>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <th></th>"); 
		respuesta.println("                  <th>Nombre</th>"); 
		respuesta.println("                  <th>Tardanza 15-min</th>"); 
		respuesta.println("                  <th>Tardanza 30-min</th>"); 
		respuesta.println("                  <th>Tardanza 45-min</th>"); 
		respuesta.println("                  <th>Cancelados</th>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("              </thead>"); 
		respuesta.println("              <tbody>"); 
		
		Aerolinea mayRet = central.darMayorRetraso();
		Aerolinea menRet = central.darMenorRetraso();
		
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>Mayor Tardanza</td>"); 
		respuesta.println("                  <td>"+mayRet.getNombre()+"</td>"); 
		respuesta.println("                  <td>"+mayRet.getVuelos15()+"</td>"); 
		respuesta.println("                  <td>"+mayRet.getVuelos30()+"</td>"); 
		respuesta.println("                  <td>"+mayRet.getVuelos45()+"</td>"); 
		respuesta.println("                  <td>"+mayRet.getVuelosCancelados()+"</td>"); 
		respuesta.println("                </tr>"); 
		respuesta.println("                <tr>"); 
		respuesta.println("                  <td>Menor Tardanza</td>"); 
		respuesta.println("                  <td>"+menRet.getNombre()+"</td>"); 
		respuesta.println("                  <td>"+menRet.getVuelos15()+"</td>"); 
		respuesta.println("                  <td>"+menRet.getVuelos30()+"</td>"); 
		respuesta.println("                  <td>"+menRet.getVuelos45()+"</td>"); 
		respuesta.println("                  <td>"+menRet.getVuelosCancelados()+"</td>");
		respuesta.println("                </tr>");
		
		respuesta.println("              </tbody>"); 
		respuesta.println("            </table>"); 
		respuesta.println("          </div>"); 
		respuesta.println("          </div>"); 
		
		respuesta.println("      "); 
		respuesta.println("        </div><!--/cierra todo!-->"); 
		respuesta.println(""); 
		respuesta.println("        <div class=\"col-xs-6 col-sm-3 sidebar-offcanvas\" id=\"sidebar\" role=\"navigation\">"); 
		respuesta.println("          <div class=\"list-group\">"); 
		respuesta.println("            <a href=\"#aeropuertos\" class=\"list-group-item active\">Aeropuertos</a>"); 
		respuesta.println("            <a href=\"#vuelos\" class=\"list-group-item\">Vuelos</a>");  
		respuesta.println("            <a href=\"#mapa\" class=\"list-group-item\">Mapa de Aeropuertos</a>"); 
		respuesta.println("            <a href=\"#aerolineas\" class=\"list-group-item\">Aerolineas</a>"); 
		respuesta.println("          </div>"); 
		
		respuesta.println("          <div>");
		respuesta.println("          <h4 style=\"text-align:center;\">Fechas disponibles</h4>");
		respuesta.println("          <ul class=\"list-group\">");
		
		Iterator<String> fechas = central.darFechas();
		while(fechas.hasNext()){
			String actual = fechas.next();
			respuesta.println("          <li style=\"text-align:center;list-style-type: none;\">"+actual+"</li>");
		}
		
		respuesta.println("          </ul>");
		respuesta.println("          </div>");
		
		respuesta.println("        </div><!--/span-->"); 
		respuesta.println("      </div><!--/row-->"); 
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
