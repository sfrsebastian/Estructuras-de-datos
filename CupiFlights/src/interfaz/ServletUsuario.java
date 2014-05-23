package interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mundo.Aerolinea;
import mundo.Aeropuerto;
import mundo.CentralDeVuelos;
import mundo.Usuario;

public class ServletUsuario extends HttpServlet{
	
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
	
	/**
	 * El usuario de la pagina
	 */
	private Usuario usuario;
	
	//--------------------------------------------
	// Constructor
	//--------------------------------------------

	/**
	 * Inicializa el Servlet
	 */
	public void init( ) throws ServletException
	{
		tituloPagina = "Usuario";
		usuario = null;
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
		HttpSession sesion = request.getSession();
		if(sesion.getAttribute("usuario") == null){
			String error = request.getParameter("error");
			if(error == null || error.equals("")){
				imprimirEncabezado(request, response);
				imprimirAutenticacion(request, response, null);
				imprimirFooter(request, response);
			}else{
				//Usuario Ya Existente
				if(error.equals("uye")){
					imprimirEncabezado(request, response);
					imprimirAutenticacion(request, response, "El usuario ya existe");
					imprimirFooter(request, response);
				}
				//Contrasena No Concuerda
				else if(error.equals("cnc")){
					imprimirEncabezado(request, response);
					imprimirAutenticacion(request, response, "La contrasena ingresada no concuerda");
					imprimirFooter(request, response);
				}
				//Debe Llenar Todo
				else if(error.equals("dlt")){
					imprimirEncabezado(request, response);
					imprimirAutenticacion(request, response, "Debe llenar todos los campos");
					imprimirFooter(request, response);
				}
			}
		}else{		
			String nom = (String) sesion.getAttribute("usuario");
			System.out.println("Nombre del admin: " + nom);
			String pas = (String) sesion.getAttribute("pass");
			usuario = central.ingresar(nom, pas);
			if(nom.equals("rommy") || sesion.getAttribute("usuario").equals("rommy")){
				response.sendRedirect(ServletCupiFlights.RUTA + "/index.html");
			}else{
				if(usuario == null)
					throw new IOException("Error, sesion activa pero usuario no hallado");

				imprimirUsuario(request, response);
			}
		}
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		HttpSession sesion = request.getSession();
		if(sesion.getAttribute("usuario") != null){
			String pedido = request.getParameter("pedido");
			System.out.println("Pedido usuario autenticado: " + pedido);
			if(!pedido.equals("")){
				//Pedidos usuario autenticado
				if(pedido.equals("salir")){
					HttpSession session = request.getSession();
					sesion.invalidate();
					central.cerrarSesion();
					response.sendRedirect(ServletCupiFlights.RUTA + "/usuario.html");
				}else if(pedido.equals("agregarAeropuerto")){
					String cod = request.getParameter("codigo");
					System.out.println(cod);
					central.agregarAeropuertoUsuario(cod);
				}else if(pedido.equals("eliminarAeropuerto")){
					String cod = request.getParameter("codigo");
					System.out.println(cod);
					central.eliminarAeropuertoUsuario(cod);
				}else if(pedido.equals("agregarAerolinea")){
					String cod = request.getParameter("codigo");
					System.out.println(cod);
					central.agregarAerolineaUsuario(cod);
				}else if(pedido.equals("eliminarAerolinea")){
					String cod = request.getParameter("codigo");
					System.out.println(cod);
					central.eliminarAerolineaUsuario(cod);
				}else if(pedido.equals("agregarCiudad")){
					String nom = request.getParameter("nombre");
					System.out.println(nom);
					central.agregarCiudadUsuario(nom);
				}else if(pedido.equals("eliminarCiudad")){
					String nom = request.getParameter("nombre");
					System.out.println(nom);
					central.eliminarCiudadUsuario(nom);
				}else if(pedido.equals("cambiarDuracion")){
					String dur = request.getParameter("duracion");
					String[] durs = dur.split("-");
					usuario.setDuraciones(Integer.parseInt(durs[0]),Integer.parseInt(durs[1]));
				}
				//response.sendRedirect(ServletCupiFlights.RUTA + "/usuario.html");
			}
			imprimirUsuario(request, response);
		}else{
			String pedido = request.getParameter("pedido");
			System.out.println("Pedido no autenticado: " + pedido);
			if(pedido != null){
				//Pedidos no autenticados
				if(pedido.equals("ingresar")){
					String usuario = request.getParameter("usuario");
					String pass = request.getParameter("pass");
					System.out.println(usuario + ":" + pass);
					
					Usuario u = central.ingresar(usuario, pass);
					if(u == null){
						response.sendRedirect(ServletCupiFlights.RUTA + "/usuario.html?error=cnc");
					}else{
						HttpSession session = request.getSession();
						session.setAttribute("usuario", u.getUsuario());
						session.setAttribute("pass", u.getContrasenia());
						response.sendRedirect(ServletCupiFlights.RUTA + "/usuario.html");
					}
				}
			}else{
				String nombre = request.getParameter("nombre");
				String apellido = request.getParameter("apellido");
				String usuario = request.getParameter("usuario");
				String correo = request.getParameter("correo");
				String pass1 = request.getParameter("contrasena1");
				String pass2 = request.getParameter("contrasena2");
	
				if(usuario == null || correo == null || pass1 == null || pass2 == null || nombre == null || apellido == null
						|| usuario.equals("") || correo.equals("") || pass1.equals("") || pass2.equals(""))
					response.sendRedirect(ServletCupiFlights.RUTA + "/usuario.html?error=dlt");
				else if(!pass1.equals(pass2))
					response.sendRedirect(ServletCupiFlights.RUTA + "/usuario.html?error=cnc");
				else if(!central.registrarUsuario(nombre, apellido, usuario, correo, pass2))
					response.sendRedirect(ServletCupiFlights.RUTA + "/usuario.html?error=uye");
				else{
					HttpSession mySession = request.getSession();
					mySession.setAttribute("usuario", usuario);
					mySession.setAttribute("pass", pass1);
					
					this.usuario = central.ingresar(usuario, pass1);
					
					PrintWriter respuesta = response.getWriter();
					respuesta.println("<div class=\"alert alert-success\">Se ha registrado con exito! Disfrute de CupiFlights!</div>");	
					imprimirUsuario(request, response);
				}
			}
		}
	}

	private void imprimirAutenticacion(HttpServletRequest request, HttpServletResponse response, String error) throws IOException{
		PrintWriter respuesta = response.getWriter();
		 
		respuesta.println(""); 
		respuesta.println("<!--Contenido pagina web-->"); 
		respuesta.println("    <div class=\"container\">"); 
		respuesta.println("      <h2>Ingresa a tu cuenta de CupiFlights!</h2>"); 
		respuesta.println("      <hr>"); 
		respuesta.println("      <div style=\"margin-top:20px\">"); 
		respuesta.println("        <form action=\"\" method=\"post\" role=\"form\">"); 
		respuesta.println("          <div class=\"form-group\">"); 
		respuesta.println("            <label for=\"user\">Usuario</label>"); 
		respuesta.println("            <input type=\"text\" class=\"form-control\" id=\"user\" placeholder=\"Usuario\" name=\"usuario\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("          <div class=\"form-group\">"); 
		respuesta.println("            <label for=\"pass\">Password</label>"); 
		respuesta.println("            <input type=\"password\" class=\"form-control\" id=\"pass\" placeholder=\"Password\" name=\"pass\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("          <div class=\"checkbox\">"); 
		respuesta.println("            <p>No tienes cuenta? Crea una <a id=\"but\">ahora mismo!</a></p>"); 
		respuesta.println("          </div>"); 
		respuesta.println("          <button type=\"submit\" class=\"btn btn-default\">Ingresar</button>"); 
		respuesta.println("        <input type=\"hidden\" name=\"pedido\" value=\"ingresar\">");
		respuesta.println("        </form>"); 
		respuesta.println("      </div>"); 
		respuesta.println("      "); 
		respuesta.println(""); 
		respuesta.println("<!--/Cerrar contenido-->"); 
		
		if(error!= null){
			respuesta.println("<div class=\"alert alert-danger\">"+ error +"</div>");
		}
		
		respuesta.println("<!--Modal Starts-->"); 
		respuesta.println("<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">"); 
		respuesta.println("  <div class=\"modal-dialog\">"); 
		respuesta.println("    <div class=\"modal-content\">"); 
		respuesta.println("      <div class=\"modal-header\">"); 
		respuesta.println("        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>"); 
		respuesta.println("        <h3 class=\"modal-title\" id=\"myModalLabel\">Crea tu cuenta ahora!</h3>"); 
		respuesta.println("        <p>Asi de facil es crear una cuenta con <b>CupiFlights!</b></p>"); 
		respuesta.println("      </div>"); 
		respuesta.println("      <div class=\"modal-body\">"); 
		respuesta.println("        <form action=\"\" method=\"post\" role=\"form\"><!--form opens-->"); 
		respuesta.println("          <div class=\"form-group\">"); 
		respuesta.println("            <label for=\"nombre\">Nombre</label>"); 
		respuesta.println("            <input type=\"test\" class=\"form-control\" id=\"nombre\" placeholder=\"Nombre\" name=\"nombre\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("          <div class=\"form-group\">"); 
		respuesta.println("            <label for=\"apellido\">Apellido</label>"); 
		respuesta.println("            <input type=\"test\" class=\"form-control\" id=\"apellido\" placeholder=\"Apellido\" name=\"apellido\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("          <div class=\"form-group\">"); 
		respuesta.println("            <label for=\"usuario\">Usuario</label>"); 
		respuesta.println("            <input type=\"test\" class=\"form-control\" id=\"usuario\" placeholder=\"Usuario\" name=\"usuario\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("          <div class=\"form-group\">"); 
		respuesta.println("            <label for=\"correo\">Correo</label>"); 
		respuesta.println("            <input type=\"text\" class=\"form-control\" id=\"correo\" placeholder=\"Correo\" name=\"correo\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("          <div class=\"form-group\">"); 
		respuesta.println("            <label for=\"contrasena1\">Contrasena</label>"); 
		respuesta.println("            <input type=\"password\" class=\"form-control\" id=\"contrasena1\" placeholder=\"Contrasena\" name=\"contrasena1\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("          <div class=\"form-group\">"); 
		respuesta.println("            <label for=\"contrasena2\">Confirmar contrasena</label>"); 
		respuesta.println("            <input type=\"password\" class=\"form-control\" id=\"contrasena2\" placeholder=\"Contrasena\" name=\"contrasena2\">"); 
		respuesta.println("          </div>"); 
		respuesta.println("      </div>"); 
		respuesta.println("      <div class=\"modal-footer\">"); 
		respuesta.println("        <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>"); 
		respuesta.println("        <button type=\"submit\" class=\"btn btn-primary\">Enviar</button>"); 
		respuesta.println("        <input type=\"hidden\" name=\"pedido\" value=\"registrar\"");
		respuesta.println("      </form><!--form closes-->"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
		respuesta.println("  </div>"); 
		respuesta.println("</div>"); 
		respuesta.println("<!--Modal ends-->");
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

	private void imprimirUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		respuesta.println("  <script type=\"text/javascript\">"); 
		respuesta.println("    $(document).ready(function(){"); 
		respuesta.println("      $(\"#agregarAeropuerto\").click(function(){"); 
		respuesta.println("        $(\"#modalAgregarAeropuerto\").modal();"); 
		respuesta.println("      });"); 
		respuesta.println("      $(\"#agregarAerolinea\").click(function(){"); 
		respuesta.println("        $(\"#modalAgregarAerolinea\").modal();"); 
		respuesta.println("      });"); 
		respuesta.println("      $(\"#agregarCiudad\").click(function(){"); 
		respuesta.println("        $(\"#modalAgregarCiudad\").modal();"); 
		respuesta.println("      });"); 
		respuesta.println("    });"); 
		respuesta.println("  </script>"); 
		respuesta.println("</head>"); 
		respuesta.println("<body>"); 
		respuesta.println("  <div class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">"); 
		respuesta.println("      <div class=\"container\">"); 
		respuesta.println("      <div class=\"container-fluid\">"); 
		respuesta.println("        <div class=\"navbar-header\">"); 
		respuesta.println("          <div class=\"navbar-collapse collapse\">"); 
		respuesta.println("            <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">"); 
		respuesta.println("              <span class=\"sr-only\">Toggle navigation</span>"); 
		respuesta.println("              <span class=\"icon-bar\"></span>"); 
		respuesta.println("              <span class=\"icon-bar\"></span>"); 
		respuesta.println("              <span class=\"icon-bar\"></span>"); 
		respuesta.println("            </button>"); 
		respuesta.println("            <a class=\"navbar-brand\" href=\"index.html\">CupiFlighs</a>"); 
		respuesta.println("            <ul class=\"nav navbar-nav\">"); 
		respuesta.println("              <li><a href=\"login.html\">Admin</a></li><!--class=\"active\" for the active link page!-->"); 
		respuesta.println("              <li><a href=\"consulta.html\">Consulta</a></li>"); 
		respuesta.println("              <li><a href=\"general.html\">General</a></li>"); 
		respuesta.println("              <li><a href=\"usuario.html\">Usuario</a></li>"); 
		respuesta.println("            </ul>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("    </div>"); 
		respuesta.println("    </div>"); 
		respuesta.println("  </div>"); 
		respuesta.println(""); 
		respuesta.println("<!--Modals-->"); 
		respuesta.println("<div class=\"modal fade\" id=\"modalAgregarAeropuerto\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">"); 
		respuesta.println("  <div class=\"modal-dialog\">"); 
		respuesta.println("    <div class=\"modal-content\">"); 
		respuesta.println("      <div class=\"modal-header\">"); 
		respuesta.println("        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>"); 
		respuesta.println("        <h3 class=\"modal-title\" id=\"myModalLabel\">Agregar/Eliminar Aeropuerto</h3>"); 
		respuesta.println("        <p>Elimine o agregue un aeropuerto por su codigo</p>"); 
		respuesta.println("      </div>"); 
		respuesta.println("      <div class=\"modal-body\">"); 
		respuesta.println("        <form action=\"\" method=\"post\" role=\"form\"><!--form opens-->"); 
		respuesta.println("          <div class=\"form-group\">"); 
		
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
		respuesta.println("        </form>"); 
		respuesta.println("        <hr>"); 
		respuesta.println("        <form action=\"\" method=\"post\" role=\"form\"><!--form opens-->"); 
		respuesta.println("          <div class=\"form-group\">"); 

		respuesta.println("            <label for=\"el\">Agregar</label>");
		respuesta.println("                <select id=\"el\" name=\"codigo\" class=\"form-control\">"); 
		Iterator<Aeropuerto> ia2 = usuario.getAeropuertos();
		while(ia2.hasNext()){
			Aeropuerto actual = ia2.next();
			respuesta.println("                  <option value=\""+ actual.getCodigo() +"\">"+actual.getNombre()+"</option>");
		} 
		respuesta.println("                </select>");
		
		respuesta.println("            <input type=\"hidden\" name=\"pedido\" value=\"eliminarAeropuerto\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-danger\" style=\"margin-top:10px;\">Eliminar Aeropuerto</button>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </form>"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
		respuesta.println("  </div>"); 
		respuesta.println("</div>"); 
		respuesta.println(""); 
		respuesta.println("<div class=\"modal fade\" id=\"modalAgregarAerolinea\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">"); 
		respuesta.println("  <div class=\"modal-dialog\">"); 
		respuesta.println("    <div class=\"modal-content\">"); 
		respuesta.println("      <div class=\"modal-header\">"); 
		respuesta.println("        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>"); 
		respuesta.println("        <h3 class=\"modal-title\" id=\"myModalLabel\">Agregar/Eliminar Aerolinea</h3>"); 
		respuesta.println("        <p>Elimine o agregue una aerolinea por su codigo</p>"); 
		respuesta.println("      </div>"); 
		respuesta.println("      <div class=\"modal-body\">"); 
		respuesta.println("        <form action=\"\" method=\"post\" role=\"form\"><!--form opens-->"); 
		respuesta.println("          <div class=\"form-group\">"); 

		respuesta.println("            <label for=\"aga\">Agregar</label>");
		respuesta.println("                <select id=\"aga\" name=\"codigo\" class=\"form-control\">"); 
		Iterator<Aerolinea> ia3 = central.getAerolineas();
		while(ia3.hasNext()){
			Aerolinea actual = ia3.next();
			respuesta.println("                  <option value=\""+ actual.getCodigo() +"\">"+actual.getNombre()+"</option>");
		} 
		respuesta.println("                </select>");
		
		respuesta.println("            <input type=\"hidden\" name=\"pedido\" value=\"agregarAerolinea\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-primary\" style=\"margin-top:10px;\">Agregar Aerolinea</button>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </form>"); 
		respuesta.println("        <hr>"); 
		respuesta.println("        <form action=\"\" method=\"post\" role=\"form\"><!--form opens-->"); 
		respuesta.println("          <div class=\"form-group\">"); 

		respuesta.println("            <label for=\"ela\">Agregar</label>");
		respuesta.println("                <select id=\"ela\" name=\"codigo\" class=\"form-control\">"); 
		Iterator<Aerolinea> ia4 = usuario.getAerolineas();
		while(ia4.hasNext()){
			Aerolinea actual = ia4.next();
			respuesta.println("                  <option value=\""+ actual.getCodigo() +"\">"+actual.getNombre()+"</option>");
		} 
		respuesta.println("                </select>");
		
		respuesta.println("            <input type=\"hidden\" name=\"pedido\" value=\"eliminarAerolinea\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-danger\" style=\"margin-top:10px;\">Eliminar Aerolinea</button>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </form>"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
		respuesta.println("  </div>"); 
		respuesta.println("</div>"); 
		respuesta.println(""); 
		respuesta.println("<div class=\"modal fade\" id=\"modalAgregarCiudad\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">"); 
		respuesta.println("  <div class=\"modal-dialog\">"); 
		respuesta.println("    <div class=\"modal-content\">"); 
		respuesta.println("      <div class=\"modal-header\">"); 
		respuesta.println("        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>"); 
		respuesta.println("        <h3 class=\"modal-title\" id=\"myModalLabel\">Agregar/Eliminar Ciudad</h3>"); 
		respuesta.println("        <p>Elimine o agregue una ciudad por su nombre</p>"); 
		respuesta.println("      </div>"); 
		respuesta.println("      <div class=\"modal-body\">"); 
		respuesta.println("        <form action=\"\" method=\"post\" role=\"form\"><!--form opens-->"); 
		respuesta.println("          <div class=\"form-group\">"); 
		respuesta.println("            <label for=\"agregar\">Agregar</label>"); 
		respuesta.println("            <input type=\"text\" class=\"form-control\" id=\"agregar\" placeholder=\"Nombre\" name=\"nombre\">"); 
		respuesta.println("            <input type=\"hidden\" name=\"pedido\" value=\"agregarCiudad\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-primary\" style=\"margin-top:10px;\">Agregar Ciudad</button>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </form>"); 
		respuesta.println("        <hr>"); 
		respuesta.println("        <form action=\"\" method=\"post\" role=\"form\"><!--form opens-->"); 
		respuesta.println("          <div class=\"form-group\">"); 
		respuesta.println("            <label for=\"eliminar\">Eliminar</label>"); 
		respuesta.println("            <input type=\"text\" class=\"form-control\" id=\"eliminar\" placeholder=\"Nombre\" name=\"nombre\">"); 
		respuesta.println("            <input type=\"hidden\" name=\"pedido\" value=\"eliminarCiudad\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-danger\" style=\"margin-top:10px;\">Eliminar Ciudad</button>"); 
		respuesta.println("          </div>"); 
		respuesta.println("        </form>"); 
		respuesta.println("      </div>"); 
		respuesta.println("    </div>"); 
		respuesta.println("  </div>"); 
		respuesta.println("</div>"); 
		respuesta.println("<!--/Modals-->"); 
		respuesta.println(""); 
		respuesta.println("<!--Contenido pagina web-->"); 
		respuesta.println("    <div class=\"container\">"); 
		respuesta.println("      <h2>Bienvenido "+usuario.getNombre()+"!</h2>"); 
		respuesta.println("      <form action=\"\" method=\"post\" role=\"form\">"); 
		respuesta.println("        <button type=\"submit\" class=\"btn btn-info\">Cerrar Sesion</button>"); 
		respuesta.println("        <input type=\"hidden\" name=\"pedido\" value=\"salir\">"); 
		respuesta.println("      </form>"); 
		respuesta.println("      <hr>"); 
		respuesta.println("      <div class=\"panel panel-primary\">"); 
		respuesta.println("        <div class=\"panel-heading\">"); 
		respuesta.println("          <h2 class=\"panel-title\">Preferencias</h2>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"panel-body\">"); 
		respuesta.println("          <div class=\"row\">"); 
		respuesta.println("            <div class=\"col-md-4\"><!--Panel Aeropuertos-->"); 
		respuesta.println("              <div class=\"panel panel-success\">"); 
		respuesta.println("                <div class=\"panel-heading\">"); 
		respuesta.println("                  <h3 class=\"panel-title\">Aeropuertos agregados</h3>"); 
		respuesta.println("                </div>"); 
		respuesta.println("                <div class=\"panel-body\">"); 
		respuesta.println("                  <div class=\"table-responsive\">"); 
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
		
		Iterator<Aeropuerto> aeropItera = usuario.getAeropuertos();
		int con = 1;
		while(aeropItera.hasNext()){
			Aeropuerto actual = aeropItera.next();
			
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
		
		respuesta.println("              </tbody>"); 
		respuesta.println("            </table>");
		respuesta.println("                  </div>"); 
		respuesta.println("                </div>"); 
		respuesta.println("                <div class=\"panel-footer\">"); 
		respuesta.println("                    <button type=\"button\" class=\"btn btn-info\" id=\"agregarAeropuerto\">Agregar/Eliminar Aeropuerto</button>"); 
		respuesta.println("                </div>"); 
		respuesta.println("              </div>"); 
		respuesta.println("            </div><!--/Panel Aeropuertos-->"); 
		respuesta.println(""); 
		respuesta.println("            <div class=\"col-md-4\"><!--Panel Vuelos-->"); 
		respuesta.println("              <div class=\"panel panel-warning\">"); 
		respuesta.println("                <div class=\"panel-heading\">"); 
		respuesta.println("                  <h3 class=\"panel-title\">Aerolineas agregadas</h3>"); 
		respuesta.println("                </div>"); 
		respuesta.println("                <div class=\"panel-body\">"); 
		respuesta.println("                  <div class=\"table-responsive\">"); 
		respuesta.println("            <table class=\"table table-striped\">"); 
		respuesta.println("              <thead>"); 
		respuesta.println("                <tr>");
		respuesta.println("                  <th>#</th>");
		respuesta.println("                  <th>Codigo</th>");
		respuesta.println("                  <th>Nombre</th>");  
		respuesta.println("                </tr>"); 
		respuesta.println("              </thead>"); 
		respuesta.println("              <tbody>"); 
		
		Iterator<Aerolinea> aeroIterator = usuario.getAerolineas();
		int bcont = 1;
		while(aeroIterator.hasNext()){
			Aerolinea actual = aeroIterator.next();
			
			respuesta.println("                <tr>");
			respuesta.println("                  <td>"+bcont+"</td>");
			respuesta.println("                  <td>"+actual.getCodigo()+"</td>"); 
			respuesta.println("                  <td>"+actual.getNombre()+"</td>");  
			respuesta.println("                </tr>"); 
			bcont++;
		}
		
		respuesta.println("              </tbody>"); 
		respuesta.println("            </table>"); 
		respuesta.println("                  </div>"); 
		respuesta.println("                </div>"); 
		respuesta.println("                <div class=\"panel-footer\">"); 
		respuesta.println("                    <button type=\"button\" class=\"btn btn-info\" id=\"agregarAerolinea\">Agregar/Eliminar Aerolinea</button>"); 
		respuesta.println("                </div>"); 
		respuesta.println("              </div>"); 
		respuesta.println("            </div><!--/Panel Vuelos-->"); 
		respuesta.println(""); 
		respuesta.println("            <div class=\"col-md-4\"><!--Panel Ciudades-->"); 
		respuesta.println("              <div class=\"panel panel-danger\">"); 
		respuesta.println("                <div class=\"panel-heading\">"); 
		respuesta.println("                  <h3 class=\"panel-title\">Ciudades agregadas</h3>"); 
		respuesta.println("                </div>"); 
		respuesta.println("                <div class=\"panel-body\">"); 
		respuesta.println("                  <div class=\"table-responsive\">"); 
		respuesta.println("            <table class=\"table table-striped\">"); 
		respuesta.println("              <thead>"); 
		respuesta.println("                <tr>");
		respuesta.println("                  <th>#</th>");
		respuesta.println("                  <th>Nombre</th>");  
		respuesta.println("                </tr>"); 
		respuesta.println("              </thead>"); 
		respuesta.println("              <tbody>"); 
		
		Iterator<String> ciudIterator = usuario.getCiudades();
		int ciud = 1;
		while(aeropItera.hasNext()){
			String actual = ciudIterator.next();
			
			respuesta.println("                <tr>");
			respuesta.println("                  <td>"+ciud+"</td>");
			respuesta.println("                  <td>"+actual+"</td>");  
			respuesta.println("                </tr>"); 
			ciud++;
		}
		
		respuesta.println("              </tbody>"); 
		respuesta.println("            </table>");  
		respuesta.println("                  </div>"); 
		respuesta.println("                </div>"); 
		respuesta.println("                <div class=\"panel-footer\">"); 
		respuesta.println("                    <button type=\"button\" class=\"btn btn-info\" id=\"agregarCiudad\">Agregar/Eliminar Ciudad</button>"); 
		respuesta.println("                </div>"); 
		respuesta.println("              </div>"); 
		respuesta.println("            </div><!--/Panel Ciudades-->"); 
		respuesta.println(""); 
		respuesta.println("          </div>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"panel-footer\">"); 
		respuesta.println("          <h3>Duracion de vuelos preferida: <b>"+usuario.getDuracionMin()+ "-" + usuario.getDuracionMax() +" min</b></h3>"); 
		respuesta.println("          <form action=\"\" method=\"post\" role=\"form\">"); 
		respuesta.println("            <div class=\"form-group\">"); 
		respuesta.println("            <label for=\"dur\">Cambiar Duracion</label>"); 
		respuesta.println("            <input type=\"text\" class=\"form-control\" id=\"dur\" placeholder=\"Duracion\" name=\"duracion\">"); 
		respuesta.println("            <input type=\"hidden\" name=\"pedido\" value=\"cambiarDuracion\">"); 
		respuesta.println("            <button type=\"submit\" class=\"btn btn-danger\" style=\"margin-top:10px;\">Cambiar Duracion</button>"); 
		respuesta.println("          </div>"); 
		respuesta.println("          </form>"); 
		respuesta.println("        </div>"); 
		respuesta.println("      </div>"); 
		respuesta.println(""); 
		respuesta.println("      <!--Consultas del usuario-->"); 
		respuesta.println("      <div class=\"panel panel-primary\">"); 
		respuesta.println("        <div class=\"panel-heading\">"); 
		respuesta.println("          <h3 class=\"panel-title\">Consultas</h3>"); 
		respuesta.println("        </div>"); 
		respuesta.println("        <div class=\"panel-body\">"); 
		respuesta.println("          "); 
		respuesta.println("          <!--"); 
		respuesta.println("            Pedido: calcularGradoSeparacion"); 
		respuesta.println("            - origen (codigo aeropuerto)"); 
		respuesta.println("            - destino (codigo aeropuerto)"); 
		respuesta.println("          -->"); 
		respuesta.println("          <h4 style=\"margin-bottom:30px;\">Consulta aqui la informacion que necesites</h4>"); 
		respuesta.println("          <form class=\"form-horizontal\" role=\"form\" method=\"POST\" action=\"consultas_usuario.html\">"); 
		respuesta.println("            <div class=\"form-group\">"); 
		respuesta.println("              <label for=\"ped\" class=\"col-sm-2 control-label\">Consulta:</label>"); 
		respuesta.println("              <div class=\"col-sm-4\">"); 
		respuesta.println("                <select id=\"ped\" placeholder=\"Pedido\" name=\"pedido\" class=\"form-control\">"); 
		respuesta.println("                  <option value=\"calcularGradoSeparacion\">Calcular el grado de separacion</option>"); 
		respuesta.println("                  <option value=\"rutaMasCorta\">Ruta mas corta</option>"); 
		respuesta.println("                  <option value=\"rutaMasCortaTiempo\">Ruta mas corta por tiempo</option>"); 
		respuesta.println("                  <option value=\"rutaMejorRating\">Ruta con mejor rating</option>");
		respuesta.println("                  <option value=\"rutaMenorTardios\">Ruta con mejor rating</option>");
		respuesta.println("                </select>"); 
		respuesta.println("              </div>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"form-group\">"); 
		respuesta.println("              <label for=\"og\" class=\"col-sm-2 control-label\">Origen:</label>"); 
		respuesta.println("              <div class=\"col-sm-10\">"); 

		respuesta.println("                <select id=\"og\" name=\"origen\" class=\"form-control\">"); 
		Iterator<Aeropuerto> ib1 = central.darAeropuertos();
		while(ib1.hasNext()){
			Aeropuerto actual = ib1.next();
			respuesta.println("                  <option value=\""+ actual.getCodigo() +"\">"+actual.getNombre()+"</option>");
		} 
		respuesta.println("                </select>");
		
		respuesta.println("              </div>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"form-group\">"); 
		respuesta.println("              <label for=\"aeDest\" class=\"col-sm-2 control-label\">Destino:</label>"); 
		respuesta.println("              <div class=\"col-sm-10\">"); 
		
		respuesta.println("                <select id=\"aeDest\" name=\"destino\" class=\"form-control\">"); 
		Iterator<Aeropuerto> ib2 = central.darAeropuertos();
		while(ib2.hasNext()){
			Aeropuerto actual = ib2.next();
			respuesta.println("                  <option value=\""+ actual.getCodigo() +"\">"+actual.getNombre()+"</option>");
		} 
		respuesta.println("                </select>");
		
		respuesta.println("              </div>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"form-group\">"); 
		respuesta.println("              <div class=\"col-sm-offset-2 col-sm-10\">"); 
		respuesta.println("                <button type=\"submit\" class=\"btn btn-info\">Consultar!</button>"); 
		respuesta.println("              </div>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <input type=\"hidden\" name=\"pedido\" value=\"calcularGradoSeparacion\">"); 
		respuesta.println("          </form>"); 
		respuesta.println("          <hr> "); 
		respuesta.println(""); 
		respuesta.println("          <!--"); 
		respuesta.println("            Pedido: tourMasLargo"); 
		respuesta.println("            - origen (codigo aeropuerto)"); 
		respuesta.println("          -->"); 
		respuesta.println("          <h4 style=\"margin-bottom:30px;\">Consultar tour mas largo</h4>"); 
		respuesta.println("          <form class=\"form-horizontal\" role=\"form\" method=\"POST\" action=\"consultas_usuario.html\">"); 
		respuesta.println("            <div class=\"form-group\">"); 
		respuesta.println("              <label for=\"aeOr\" class=\"col-sm-2 control-label\">Origen:</label>"); 
		respuesta.println("              <div class=\"col-sm-10\">"); 
		
		respuesta.println("                <select id=\"ae0r\" name=\"origen\" class=\"form-control\">"); 
		Iterator<Aeropuerto> ib3 = central.darAeropuertos();
		while(ib3.hasNext()){
			Aeropuerto actual = ib3.next();
			respuesta.println("                  <option value=\""+ actual.getCodigo() +"\">"+actual.getNombre()+"</option>");
		} 
		respuesta.println("                </select>");
		
		respuesta.println("              </div>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"form-group\">"); 
		respuesta.println("              <div class=\"col-sm-offset-2 col-sm-10\">"); 
		respuesta.println("                <button type=\"submit\" class=\"btn btn-info\">Consultar!</button>"); 
		respuesta.println("              </div>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <input type=\"hidden\" name=\"pedido\" value=\"tourMasLargo\">"); 
		respuesta.println("          </form>"); 
		respuesta.println("          <hr>"); 
		respuesta.println(""); 
		respuesta.println("          <!--"); 
		respuesta.println("            Pedido: verRecomendaciones"); 
		respuesta.println("          -->"); 
		respuesta.println("          <h4 style=\"margin-bottom:30px;\">Ver Recomendaciones</h4>"); 
		respuesta.println("          <form class=\"form-horizontal\" role=\"form\" method=\"POST\" action=\"consultas_usuario.html\">"); 
		respuesta.println("            <div class=\"form-group\">"); 
		respuesta.println("              <label for=\"ae0r1\" class=\"col-sm-2 control-label\">Origen:</label>"); 
		respuesta.println("              <div class=\"col-sm-10\">"); 
		
		respuesta.println("                <select id=\"ae0r1\" name=\"origen\" class=\"form-control\">"); 
		Iterator<Aeropuerto> ib4 = usuario.getAeropuertos();
		while(ib4.hasNext()){
			Aeropuerto actual = ib4.next();
			respuesta.println("                  <option value=\""+ actual.getCodigo() +"\">"+actual.getNombre()+"</option>");
		} 
		respuesta.println("                </select>");
		
		respuesta.println("              </div>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"form-group\">"); 
		respuesta.println("              <div class=\"col-sm-offset-2 col-sm-10\">"); 
		respuesta.println("                <button type=\"submit\" class=\"btn btn-info\">Consultar!</button>"); 
		respuesta.println("              </div>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <input type=\"hidden\" name=\"pedido\" value=\"verRecomendaciones\">"); 
		respuesta.println("          </form>"); 
		respuesta.println("          <hr>   "); 
		respuesta.println(""); 
		respuesta.println("        </div>"); 
		respuesta.println("      </div>"); 
		respuesta.println(""); 
		respuesta.println(""); 
		respuesta.println("<!--/Cerrar contenido-->"); 
		respuesta.println("<hr>"); 
		respuesta.println(""); 
		respuesta.println("    <footer>"); 
		respuesta.println("        <p>&copy; Felipe Ot&aacute;lora - Sebasti&aacute;n Florez</p>"); 
		respuesta.println("      </footer>"); 
		respuesta.println("    </div> <!-- /container -->"); 
		respuesta.println("</body>"); 
		respuesta.println("</html>"); 
		
		//FORMULARIO PARA COPIAR, SOLO REEMPLAZAR VALUE DEL HIDDEN Y NOMBRE DE LAS VARIABLES
		
		/*
		 * 		respuesta.println("          <h4 style=\"margin-bottom:30px;\">Ver Recomendaciones</h4>"); 
		respuesta.println("          <form class=\"form-horizontal\" role=\"form\" method=\"POST\" action=\"consultas_usuario.html\">"); 
		respuesta.println("            <div class=\"form-group\">"); 
		respuesta.println("              <label for=\"ae0r1\" class=\"col-sm-2 control-label\">Origen:</label>"); 
		respuesta.println("              <div class=\"col-sm-10\">"); 
		
		respuesta.println("                <select id=\"ae0r1\" name=\"origen\" class=\"form-control\">"); 
		Iterator<Aeropuerto> ib4 = usuario.getAeropuertos();
		while(ib4.hasNext()){
			Aeropuerto actual = ib4.next();
			respuesta.println("                  <option value=\""+ actual.getCodigo() +"\">"+actual.getNombre()+"</option>");
		} 
		respuesta.println("                </select>");
		
		respuesta.println("              </div>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <div class=\"form-group\">"); 
		respuesta.println("              <div class=\"col-sm-offset-2 col-sm-10\">"); 
		respuesta.println("                <button type=\"submit\" class=\"btn btn-info\">Consultar!</button>"); 
		respuesta.println("              </div>"); 
		respuesta.println("            </div>"); 
		respuesta.println("            <input type=\"hidden\" name=\"pedido\" value=\"verRecomendaciones\">"); 
		respuesta.println("          </form>"); 
		respuesta.println("          <hr>   "); 
		 */
	}
}