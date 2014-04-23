package interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mundo.CentralDeVuelos;

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
		
		}
		
		protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
		{
			
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
		}
		
		private void imprimirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
			PrintWriter respuesta = response.getWriter();
		}
		
		private void imprimirFooter(HttpServletRequest request, HttpServletResponse response) throws IOException {
			PrintWriter respuesta = response.getWriter();
		}
}
