package componenteSearch.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.crypto.spec.OAEPParameterSpec;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

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
	 * La clase principal del mundo
	 */
	private ComponenteSearch componenteSearch;
	
	public int tiempoBusqueda;
	
	public int nivel;
	
	//-----------------------------------------------------------------
	// Constructor
	//-----------------------------------------------------------------
	
	/**
	 * 
	 * @param mundo
	 */
	public ComponenteSearchPanelCentral(ComponenteSearch mundo){
		componenteSearch = mundo;
		tiempoBusqueda = 0;
		nivel = 0;
		
		setLayout(new BorderLayout());
		//setBorder(new TitledBorder("Componente Search"));
		
		panelHome = new ComponenteSearchPanelHome(this, componenteSearch);
		add(panelHome,BorderLayout.CENTER);
		
		//JLabel lbl = new JLabel("hola");
		//add(lbl,BorderLayout.SOUTH);
		repaint();
	}
	
	//-----------------------------------------------------------------
	// Metodos
	//-----------------------------------------------------------------
	
	public void cargarPanelHome(JPanel panel){
		
		remove(panel);
		panelHome = new ComponenteSearchPanelHome(this, componenteSearch);
		panelHome.setSpinnerValue(tiempoBusqueda);
		panelHome.setSpinnerNivel(nivel);
		add(panelHome,BorderLayout.CENTER);
		revalidate();
	}
	
	public void cargarPanelCategoria(JPanel panel){
		remove(panel);
		panelCategoria = new ComponenteSearchPanelCategoria(this, componenteSearch);
		add(panelCategoria,BorderLayout.CENTER);
		revalidate();
	}
	
	public void cargarPanelBusqueda(JPanel panel){
		remove(panel);
		panelBusqueda = new ComponenteSearchPanelBusqueda(this, componenteSearch);
		add(panelBusqueda, BorderLayout.CENTER);
		revalidate();
	}
	
	public void agregarFuente(String fuente){
		try {
			componenteSearch.agregarSitiosFuente(fuente);
		} catch (Exception e) {
			mostrarError("No se pudo formar la url: ", e);
		}
	}
	
	public void cargarWebView(JPanel panel, Recurso rec){
		remove(panel);
		PanelWebView panelsito = new PanelWebView(rec, this);
		add(panelsito,BorderLayout.CENTER);
		revalidate();
	}
	
	public void mostrarError(String mensaje, Exception exception){
		String exceptionMessage = "";
		if(exception != null)
			exceptionMessage = exception.getMessage();
		JOptionPane.showMessageDialog(this, "Hola", mensaje + exceptionMessage, JOptionPane.ERROR_MESSAGE);
	}
	
	public String descargarImagen(Recurso r) throws IOException{
		return componenteSearch.visualizarImagen(r);
	}
	
	//-----------------------------------------------------------------
	// Main - Standalone
	//-----------------------------------------------------------------
	
	public static void main(String[] args) {
		JFrame n = new JFrame();
        n.setMinimumSize(new Dimension(450, 650));
        n.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        n.setVisible(true);
        ComponenteSearchPanelCentral nuevo = new ComponenteSearchPanelCentral( new ComponenteSearch(null) );
        //TODO core thingy
        n.add(nuevo);  
        n.repaint();
        nuevo.setVisible(true);
	}
	
	public void terminar() {
		// TODO Auto-generated method stub
		
	}

}
