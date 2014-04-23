public class blablabla extends HTTPServlet{
	//--------------------------------------------
	// Atributos
	//--------------------------------------------
	
	//private CentralCupiFlights central;
	
	//--------------------------------------------
	// Constructor
	//--------------------------------------------
	
	/**
	 * Inicializa el Servlet
	 */
	public void init( ) throws ServletException
    {
        //central = CentralCupiFlights.getInstance( );
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