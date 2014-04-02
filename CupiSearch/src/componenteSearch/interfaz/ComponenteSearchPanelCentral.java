package componenteSearch.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.crypto.spec.OAEPParameterSpec;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import componenteSearch.mundo.Categoria;
import componenteSearch.mundo.ComponenteSearch;
import componenteSearch.mundo.Recurso;

public class ComponenteSearchPanelCentral extends JPanel {
	
	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------
	
	/**
	 * El panel de las categorias
	 */
	private ComponenteSearchPanelCategoria panelCategoria;
	
	/**
	 * El panel del home
	 */
	private ComponenteSearchPanelHome panelHome;
	
	/**
	 * El panel de busqueda de la informacion
	 */
	private ComponenteSearchPanelBusqueda panelBusqueda;
	
	/**
	 * El panel con la vista de las categorias
	 */
	private PanelVistaCategoria vistaCategoria;
	
	/**
	 * El panel con el historial de operaciones y exploraciones
	 */
	private PanelHistorial panelHistorial;
	
	/**
	 * La clase principal del mundo
	 */
	private static ComponenteSearch componenteSearch;
	
	/**
	 * El tiempo de busqueda inicial
	 */
	public int tiempoBusqueda;
	
	/**
	 * El nivel de la exploracion
	 */
	public int nivel;
	
	//-----------------------------------------------------------------
	// Constructor
	//-----------------------------------------------------------------
	
	/**
	 * Crea un nuevo panel central que "aloja" a los otros paneles del mundo
	 * @param mundo La clase principal del mundo
	 */
	public ComponenteSearchPanelCentral(ComponenteSearch mundo){
		componenteSearch = mundo;
		tiempoBusqueda = 0;
		nivel = 0;
		
		setLayout(new BorderLayout());
		panelHome = new ComponenteSearchPanelHome(this, componenteSearch);
		add(panelHome,BorderLayout.CENTER);
		repaint();
	}
	
	//-----------------------------------------------------------------
	// Metodos
	//-----------------------------------------------------------------
	
	/**
	 * Carga el panel principal, el panel home
	 * @param panel El panel que se quiere desmontar
	 * POST: Se ha cargado el nuevo panel
	 */
	public void cargarPanelHome(JPanel panel){
		remove(panel);
		panelHome = new ComponenteSearchPanelHome(this, componenteSearch);
		panelHome.setSpinnerValue(tiempoBusqueda);
		panelHome.setSpinnerNivel(nivel);
		add(panelHome,BorderLayout.CENTER);
		revalidate();
	}
	
	/**
	 * Carga el panel de las categorias
	 * @param panel El panel que se quiere desmontar
	 * POST: Se ha cargado el nuevo panel
	 */
	public void cargarPanelCategoria(JPanel panel){
		remove(panel);
		panelCategoria = new ComponenteSearchPanelCategoria(this, componenteSearch);
		add(panelCategoria,BorderLayout.CENTER);
		revalidate();
	}
	
	/**
	 * Carga el panel de busqueda
	 * @param panel El panel que se quiere desmontar
	 * POST: Se ha cargado el nuevo panel
	 */
	public void cargarPanelBusqueda(JPanel panel){
		remove(panel);
		panelBusqueda = new ComponenteSearchPanelBusqueda(this, componenteSearch);
		add(panelBusqueda, BorderLayout.CENTER);
		revalidate();
	}
	
	/**
	 * Carga el panel de vista y edicion de las categorias
	 * @param panel El panel que se quiere desmontar
	 * @param cat La categoria que se quiere mostrar
	 * POST: Se ha cargado el nuevo panel
	 */
	public void cargarPanelVistaCategoria(JPanel panel, Categoria cat){
		remove(panel);
		vistaCategoria = new PanelVistaCategoria(componenteSearch, cat, this);
		add(vistaCategoria, BorderLayout.CENTER);
		revalidate();
	}
	
	/**
	 * Carga el panel del historial de operaciones
	 * @param panel El panel que se va a desmontar
	 * POST: Se ha cargado el nuevo panel
	 */
	public void cargarPanelVistaHistorial(JPanel panel){
		remove(panel);
		panelHistorial = new PanelHistorial(componenteSearch, this);
		add(panelHistorial,BorderLayout.CENTER);
		revalidate();
	}
	
	/**
	 * Agrega una fuente al scraper y la exploracion actual
	 * @param fuente
	 */
	public void agregarFuente(String fuente){
		try {
			componenteSearch.agregarSitiosFuente(fuente);
		} catch (Exception e) {
		}
	}
	
	/**
	 * Carga el panel con la vista del recurso web
	 * @param panel El panel que se va a desmontar de la aplicacion
	 * @param rec El recurso que se va a visualizar
	 * @param retorno El nombre del panel al que va a retornar despues del avance
	 */
	public void cargarWebView(JPanel panel, Recurso rec, String retorno){
		remove(panel);
		PanelWebView panelsito = new PanelWebView(rec, this,retorno);
		add(panelsito,BorderLayout.CENTER);
		revalidate();
	}
	
	/**
	 * Muestra un mensaje de error al usuario
	 * @param mensaje El mensaje que se quiere mostrar
	 * @param exception El tipo de excepcion
	 */
	public void mostrarError(String mensaje, Exception exception){
		String exceptionMessage = "";
		if(exception != null)
			exceptionMessage = exception.getMessage();
		JOptionPane.showMessageDialog(this, "Hola", mensaje + exceptionMessage, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Descarga la imagen para visualizarla mediante el componente search
	 * @param r El recurso
	 * @return Un string con la ruta de visualizacion
	 * @throws IOException
	 */
	public String descargarImagen(Recurso r) throws IOException{
		return componenteSearch.visualizarImagen(r);
	}
	
	//-----------------------------------------------------------------
	// Main - Standalone
	//-----------------------------------------------------------------
	
	/**
	 * Ejecuta la aplicacion de forma standalone
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		JFrame n = new JFrame();
        n.setMinimumSize(new Dimension(450, 650));
        n.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        n.setVisible(true);
        ComponenteSearchPanelCentral nuevo = new ComponenteSearchPanelCentral( new ComponenteSearch(null) );
        //TODO core thingy
        n.add(nuevo);  
        n.repaint();
        nuevo.setVisible(true);
        n.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evt) {
            	try {
					componenteSearch.guardar();
				} catch (Exception e) {
					e.printStackTrace();
				}

            }
        });
        
	}

}
