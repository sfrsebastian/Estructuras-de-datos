package interfaz;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoOpcionesProyecto extends JDialog {
	
	private InterfazCupiSoundBox padre;
	private JTextField txtAutor;
	private JTextField txtNombre;
	
	public DialogoOpcionesProyecto(){
		setSize(300,200);
		setTitle("Opciones del Proyecto");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblCambiarNombre = new JLabel("Cambiar Nombre:");
		lblCambiarNombre.setBounds(6, 18, 110, 16);
		getContentPane().add(lblCambiarNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(128, 12, 166, 28);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCambiarBpm = new JLabel("Cambiar Autor:");
		lblCambiarBpm.setBounds(6, 59, 110, 16);
		getContentPane().add(lblCambiarBpm);
		
		txtAutor = new JTextField();
		txtAutor.setBounds(128, 53, 166, 28);
		getContentPane().add(txtAutor);
		txtAutor.setColumns(10);
		
		JButton btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String nombre = txtNombre.getText();
					String autor = txtAutor.getText();
					padre.editarProyecto(nombre,autor);
					
				}catch(Exception e1){
					mostrarError("Debe llenar los campos correctamente");
				}
			}
			
		});
		btnGuardarCambios.setBounds(6, 131, 288, 29);
		getContentPane().add(btnGuardarCambios);
	}
	
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Hola", JOptionPane.ERROR_MESSAGE);
	}
	
	public void setPadre(InterfazCupiSoundBox interfaz){
		padre = interfaz;
	}

}
