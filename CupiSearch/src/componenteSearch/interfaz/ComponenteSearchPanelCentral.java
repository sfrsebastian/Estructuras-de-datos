package componenteSearch.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import componenteSearch.mundo.ComponenteSearch;

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
	
	//-----------------------------------------------------------------
	// Constructor
	//-----------------------------------------------------------------
	
	/**
	 * 
	 * @param mundo
	 */
	public ComponenteSearchPanelCentral(ComponenteSearch mundo){
		componenteSearch = mundo;
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Componente Search"));
		
		panelHome = new ComponenteSearchPanelHome();
		add(panelHome,BorderLayout.CENTER);
	}
	
	//-----------------------------------------------------------------
	// Metodos
	//-----------------------------------------------------------------
	
	public void cargarPanelHome(){
		
	}
	
	public void cargarPanelCategoria(){
		
	}
	
	public void cargarPanelBusqeuda(){
		
	}
	
	//-----------------------------------------------------------------
	// Main - Standalone
	//-----------------------------------------------------------------
	
	public static void main(String[] args) {
		JFrame n = new JFrame();
        n.setMinimumSize(new Dimension(450, 650));
        n.setVisible(true);
        ComponenteSearchPanelCentral nuevo = new ComponenteSearchPanelCentral( new ComponenteSearch(null) );
        //TODO core thingy
        nuevo.setVisible(true);
        n.add(nuevo);  
	}

}
