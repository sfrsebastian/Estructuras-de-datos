package componenteSearch.interfaz;

import java.awt.Dimension;

import javax.swing.JPanel;

import componenteSearch.mundo.ComponenteSearch;
import javax.swing.border.TitledBorder;

public class ComponenteSearchPanel extends JPanel {

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	/**
	 * Clase principal del mundo
	 */
	private ComponenteSearch componenteContactos;
	
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	
	public ComponenteSearchPanel(ComponenteSearch mundo) {
		setBorder(new TitledBorder(null, "Componente Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		componenteContactos = mundo;
	}
	
    //-----------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------

	public void terminar() {
		// TODO Auto-generated method stub
		
	}

}
