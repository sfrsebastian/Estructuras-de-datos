package componenteSearch.app;

import javax.swing.JPanel;

import componenteSearch.interfaz.ComponenteSearchPanel;
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
	private ComponenteSearchPanel panel;
	
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
		mundo = new ComponenteSearch(core);
		panel = new ComponenteSearchPanel();
	}

	@Override
	public void terminarEjecucion() {
		panel.terminar();
	}
	
	/**
	 * Metodo necesario para interactuar con el core
	 * del telefono
	 * @return La instancia de la clase
	 */
	public static IAplicacion darInstancia()
	{
		 if ( instancia == null )
	        {
	            instancia = new ComponenteSearchAplicacion();
	        }
	     return instancia;
	}

}
