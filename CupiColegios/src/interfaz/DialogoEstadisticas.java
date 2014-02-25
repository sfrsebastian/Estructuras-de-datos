package interfaz;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class DialogoEstadisticas extends JDialog {
	
	//------------------------------------------
	// Atributos
	//------------------------------------------

	/**
	 * La referencia al padre, la interfaz pirncipal
	 */
	private InterfazCupiColegios padre;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public DialogoEstadisticas(){
		
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	public void setParent(InterfazCupiColegios interfaz){
		padre = interfaz;
		setResizable(false);
		setTitle("Estadisticas");
	}
	
	/**
	 * Le muestra un error al usuario
	 * @param error El error que se quiere mostrar
	 */
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
