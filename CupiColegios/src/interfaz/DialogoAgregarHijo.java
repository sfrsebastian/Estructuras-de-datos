package interfaz;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import mundo.Hijo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoAgregarHijo extends JDialog {
	
	private InterfazCupiColegios padre;
	private JTextField txtEdad;
	private JTextField txtNombre;
	private JComboBox comboGenero;
	private JTextField txtTelefono;
	private JTextField txtEncargado;
	private JTextField txtCiudad;
	
	private DialogoManejarHijos dialogoManejarHijos;
	
	public DialogoAgregarHijo(DialogoManejarHijos nDialogoManejarHijos){
		dialogoManejarHijos = nDialogoManejarHijos;
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(300, 320);
		setTitle("Registrar Hijo");
		getContentPane().setLayout(null);
		
		JLabel lblIngresarNombreY = new JLabel("Ingresar nombre y apellidos:");
		lblIngresarNombreY.setBounds(6, 16, 216, 16);
		getContentPane().add(lblIngresarNombreY);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(6, 44, 288, 28);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblEspecificarGenero = new JLabel("Especificar Genero:");
		lblEspecificarGenero.setBounds(6, 84, 216, 16);
		getContentPane().add(lblEspecificarGenero);
		
		comboGenero = new JComboBox();
		comboGenero.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		comboGenero.setBounds(6, 112, 130, 27);
		getContentPane().add(comboGenero);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(6, 151, 124, 16);
		getContentPane().add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setBounds(142, 145, 152, 28);
		getContentPane().add(txtEdad);
		txtEdad.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(6, 179, 124, 16);
		getContentPane().add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(142, 173, 152, 28);
		getContentPane().add(txtTelefono);

		JLabel lblEncargado = new JLabel("Encargado:");
		lblEncargado.setBounds(6, 207, 124, 16);
		getContentPane().add(lblEncargado);

		txtEncargado = new JTextField();
		txtEncargado.setColumns(10);
		txtEncargado.setBounds(142, 201, 152, 28);
		getContentPane().add(txtEncargado);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					boolean error = false;
					String errorTxt = "";

					String nombre = txtNombre.getText();
					if(nombre.equals("")){
						error = true;
						errorTxt = "Debe especificar un nombre";
					}
					String generoTxt = (String)comboGenero.getSelectedItem();
					int genero = -1;

					if(generoTxt.equals("Masculino"))
						genero = Hijo.MASCULINO;
					else
						genero = Hijo.FEMENINO;

					int edad = Integer.parseInt(txtEdad.getText());
					if(edad < 0 || edad > 18){
						error = true;
						errorTxt = "Debe ser un nino";
					}

					int telefono = Integer.parseInt(txtTelefono.getText());

					String encargado = txtEncargado.getText();
					if(encargado.equals("")){
						error = true;
						errorTxt = "Debe llenar el encargado";
					}
					
					String ciudad = txtCiudad.getText();
					if(ciudad.equals("")){
						error = true;
						errorTxt = "Escoga una ciudad";
					}
					
					if(!error){
						padre.registrarHijo(nombre,genero,edad,telefono,encargado, ciudad);
						dialogoManejarHijos.refrescarTodasListas(padre.darHijos());
						//TODO ojo!!!
						salir();
					}else{
						mostrarError(errorTxt);
					}
					
				}catch(Exception e2){
					mostrarError("Ha llenado incorretamente los datos");
				}

			}
		});
		btnNewButton.setBounds(6, 263, 288, 29);
		getContentPane().add(btnNewButton);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(6, 235, 124, 16);
		getContentPane().add(lblCiudad);
		
		txtCiudad = new JTextField();
		txtCiudad.setColumns(10);
		txtCiudad.setBounds(142, 229, 152, 28);
		getContentPane().add(txtCiudad);
	}
	
	/**
	 * Le muestra un error al usuario
	 * @param error El error que se quiere mostrar
	 */
	private void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void salir(){
		this.dispose();
	}
	
	public void setParent(InterfazCupiColegios interfaz){
		padre = interfaz;
	}

}
