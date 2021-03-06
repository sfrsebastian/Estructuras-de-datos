package componenteSearch.app;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;

import componenteSearch.interfaz.ComponenteSearchPanelCentral;
import componenteSearch.mundo.ComponenteSearch;
import uniandes.cupi2.cupIphone.componentes.IAplicacion;
import uniandes.cupi2.cupIphone.core.ICore;

/**
 * @author FelipeOtalora
 * Aplicacion para el manejo de busquedas del CupIphone
 */
public class ComponenteSearchAplicacion implements IAplicacion {

	/**
	 * Referencia al core del cupiPhone, para localizar otros componentes y acceder al directorio de datos
	 */
	private ICore core;
	
	/**
	 * Panel principal del componente
	 */
	private ComponenteSearchPanelCentral panel;
	
	/**
	 * Clase principal del mundo del componente
	 */
	private ComponenteSearch mundo;
	
	/**
	 * Esta aplicacion se implementa como un singleton
	 */
	private static ComponenteSearchAplicacion instancia;
	
	@Override
	public void cambiarCore(ICore c) {
		core = c;
	}

	@Override
	public Object darInstanciaModelo() {
		return mundo;
	}

	@Override
	public JPanel darPanelPrincipal() {
		return panel;
	}

	@Override
	public void iniciarEjecucion() {
		try {
			mundo = new ComponenteSearch(core);
			panel = new ComponenteSearchPanelCentral(mundo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void terminarEjecucion() {
		try {
			mundo.guardar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * Metodo necesario para interactuar con el core
	 * del telefono
	 * @return La instancia de la clase
	 */
	public static IAplicacion darInstancia()
	{
		return instancia!=null? instancia: new ComponenteSearchAplicacion();
	}

}
