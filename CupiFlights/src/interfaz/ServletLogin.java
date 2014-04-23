package interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mundo.CentralDeVuelos;

public class ServletLogin extends HttpServlet {

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
		HttpSession session = request.getSession();
		if(session.getAttribute("usuario") != null)
			response.sendRedirect(ServletCupiFlights.RUTA + "/admin.html");
		
		imprimirEncabezado(request, response);
		imprimirContenido(request, response);
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
		imprimirEncabezado(request, response);
		imprimirContenido(request, response);
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
		respuesta.println("	<head>"); 
		respuesta.println("	<title>Page Title</title>"); 
		respuesta.println("	<script src=\"http://code.jquery.com/jquery-1.11.0.min.js\"></script>"); 
		respuesta.println("	<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css\">"); 
		respuesta.println("	<script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js\"></script>"); 
		respuesta.println("	<style type=\"text/css\">"); 
		respuesta.println("		body {"); 
		respuesta.println("		  padding-top: 40px;"); 
		respuesta.println("		  padding-bottom: 40px;"); 
		respuesta.println("		  background-color: #eee;"); 
		respuesta.println("		}"); 
		respuesta.println(""); 
		respuesta.println("		.form-signin {"); 
		respuesta.println("		  max-width: 330px;"); 
		respuesta.println("		  padding: 15px;"); 
		respuesta.println("		  margin: 0 auto;"); 
		respuesta.println("		}"); 
		respuesta.println("		.form-signin .form-signin-heading,"); 
		respuesta.println("		.form-signin .checkbox {"); 
		respuesta.println("		  margin-bottom: 10px;"); 
		respuesta.println("		}"); 
		respuesta.println("		.form-signin .checkbox {"); 
		respuesta.println("		  font-weight: normal;"); 
		respuesta.println("		}"); 
		respuesta.println("		.form-signin .form-control {"); 
		respuesta.println("		  position: relative;"); 
		respuesta.println("		  height: auto;"); 
		respuesta.println("		  -webkit-box-sizing: border-box;"); 
		respuesta.println("		     -moz-box-sizing: border-box;"); 
		respuesta.println("		          box-sizing: border-box;"); 
		respuesta.println("		  padding: 10px;"); 
		respuesta.println("		  font-size: 16px;"); 
		respuesta.println("		}"); 
		respuesta.println("		.form-signin .form-control:focus {"); 
		respuesta.println("		  z-index: 2;"); 
		respuesta.println("		}"); 
		respuesta.println("		.form-signin input[type=\"email\"] {"); 
		respuesta.println("		  margin-bottom: -1px;"); 
		respuesta.println("		  border-bottom-right-radius: 0;"); 
		respuesta.println("		  border-bottom-left-radius: 0;"); 
		respuesta.println("		}"); 
		respuesta.println("		.form-signin input[type=\"password\"] {"); 
		respuesta.println("		  margin-bottom: 10px;"); 
		respuesta.println("		  border-top-left-radius: 0;"); 
		respuesta.println("		  border-top-right-radius: 0;"); 
		respuesta.println("		}"); 
		respuesta.println("	</style>"); 
		respuesta.println("</head>");
	}

	private void imprimirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		
		respuesta.println("<body>"); 
		respuesta.println("	 <div class=\"container\">"); 
		respuesta.println(""); 
		String error = request.getParameter("error");
		if(error != null && error.equals("si")){
			respuesta.println("<h3 style=\"color:red;\">Ha ingresado los datos incorrectamente</h3>");
		}
		respuesta.println("      <form class=\"form-signin\" role=\"form\" action=\"admin.html\" method=\"POST\">"); 
		respuesta.println("        <h2 class=\"form-signin-heading\">Iniciar Sesi&oacute;n</h2>"); 
		respuesta.println("        <input type=\"text\" class=\"form-control\" placeholder=\"Usuario\" required autofocus name=\"usuario\">"); 
		respuesta.println("        <input type=\"password\" class=\"form-control\" placeholder=\"Contrasena\" required name=\"contrasena\">"); 
		respuesta.println("        <label class=\"checkbox\">"); 
		respuesta.println("          <input type=\"checkbox\" value=\"remember-me\"> Remember me"); 
		respuesta.println("        </label>"); 
		respuesta.println("        <button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Sign in</button>"); 
		respuesta.println("      </form>"); 
		respuesta.println(""); 
		respuesta.println("    </div> <!-- /container -->"); 
		respuesta.println(""); 
		respuesta.println("</body>"); 
		respuesta.println("</html>"); 
	}
}
