package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletPrueba extends HttpServlet {
	
	public void init( ) throws ServletException
    {
       //TODO
    }
	
	/**
     * Maneja un pedido GET de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        // Maneja el GET y el POST de la misma manera
        procesarSolicitud( request, response );
    }

	private void procesarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter respuesta = response.getWriter( );
		
		
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
		respuesta.println("		body{"); 
		respuesta.println("			padding-top: 50px;"); 
		respuesta.println("			padding-bottom: 20px;"); 
		respuesta.println("		}"); 
		respuesta.println("	</style>"); 
		respuesta.println("</head>");
		
		respuesta.println("<body>");
		respuesta.println("<h1>Hello World!</h1>");
		respuesta.println("</body>");
		respuesta.println("</html>");
	}
}
