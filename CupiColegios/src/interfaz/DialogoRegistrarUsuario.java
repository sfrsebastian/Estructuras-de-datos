package interfaz;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoRegistrarUsuario extends JDialog {

	private InterfazCupiColegios padre;
	private JPasswordField psw1;
	private JPasswordField psw2;
	private JTextField txtNombre;
	
	public DialogoRegistrarUsuario(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Registrar usuario");
		setSize(280,275);
		getContentPane().setLayout(null);
		
		JLabel lblIngreseNombreY = new JLabel("Ingrese nombre y apellidos:");
		lblIngreseNombreY.setBounds(6, 17, 197, 16);
		getContentPane().add(lblIngreseNombreY);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(6, 45, 267, 28);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(6, 85, 134, 16);
		getContentPane().add(lblContrasea);
		
		psw1 = new JPasswordField();
		psw1.setBounds(6, 113, 267, 28);
		getContentPane().add(psw1);
		
		JLabel lblRepetirContrasea = new JLabel("Repetir contrase\u00F1a:");
		lblRepetirContrasea.setBounds(6, 153, 134, 16);
		getContentPane().add(lblRepetirContrasea);
		
		psw2 = new JPasswordField();
		psw2.setBounds(6, 181, 267, 28);
		getContentPane().add(psw2);
		
		JButton btnAceptar = new JButton("Registrar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String usuario = txtNombre.getText();
					String c1 = psw1.getText();
					String c2 = psw2.getText();
					
					if(c1.equals(c2)){
						padre.registrarUsuario(usuario,c1);
						salir();
					}else{
						mostrarError("Las constrase\u00F1as no concuerdan");
					}
					
				} catch (Exception e2) {
					mostrarError("Debe llenar los campos correctamente");
				}
			}
		});
		btnAceptar.setBounds(6, 221, 117, 29);
		getContentPane().add(btnAceptar);
	}
	
	public void setParent(InterfazCupiColegios interfaz){
		padre = interfaz;
	}
	
	private void salir(){
		this.dispose();
	}
	
	/**
	 * Le muestra un error al usuario
	 * @param error El error que se quiere mostrar
	 */
	private void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
