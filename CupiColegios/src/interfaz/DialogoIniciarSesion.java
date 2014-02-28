package interfaz;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoIniciarSesion extends JDialog {
	
	private InterfazCupiColegios padre;
	private JTextField txtUsuario;
	private JPasswordField txtPsw;
	
	public DialogoIniciarSesion(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Iniciar sesion");
		setSize(280,200);
		getContentPane().setLayout(null);
		
		JLabel lblIngreseUsuario = new JLabel("Ingrese usuario:");
		lblIngreseUsuario.setBounds(6, 6, 197, 16);
		getContentPane().add(lblIngreseUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(6, 34, 267, 28);
		getContentPane().add(txtUsuario);
		
		JLabel label_1 = new JLabel("Contrase\u00F1a:");
		label_1.setBounds(6, 74, 134, 16);
		getContentPane().add(label_1);
		
		txtPsw = new JPasswordField();
		txtPsw.setBounds(6, 102, 267, 28);
		getContentPane().add(txtPsw);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean error = false;
					String errorTxt = "";
					
					String usuario = txtUsuario.getText();
					String pass = txtPsw.getText();
					
					if(usuario.equals("")){
						error = true;
						errorTxt = "Ingrese usuario";
					}
					if(pass.equals("")){
						error = true;
						errorTxt = "Ingrese contrasena";
					}
					
					if(!error){
						if(padre.buscarUsuario(usuario,pass)){
							mostrarAlerta("Usuario autenticado");
							salir();
						}else{
							mostrarError("Usuario o contrasena incorrectos");
						}
					}else{
						mostrarError(errorTxt);
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnIngresar.setBounds(6, 142, 117, 29);
		getContentPane().add(btnIngresar);
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
	
	private void mostrarAlerta(String error){
		JOptionPane.showMessageDialog(this, error, "Atencion", JOptionPane.INFORMATION_MESSAGE);
	}
}
