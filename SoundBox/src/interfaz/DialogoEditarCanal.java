package interfaz;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoEditarCanal extends JDialog {
	
	private InterfazCupiSoundBox padre;
	private JTextField textField;
	private JTextField textField_1;
	
	public DialogoEditarCanal(){
		setSize(250,250);
		setTitle("Editar Canal");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblCambiarNombre = new JLabel("Cambiar Nombre");
		lblCambiarNombre.setBounds(70, 6, 112, 16);
		getContentPane().add(lblCambiarNombre);
		
		textField = new JTextField();
		textField.setBounds(6, 34, 238, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAadirSonido = new JButton("A\u00F1adir Sonido");
		btnAadirSonido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogoBibliotecaSonidos dialogoBibliotecaSonidos = new DialogoBibliotecaSonidos(true);
				dialogoBibliotecaSonidos.setParent(padre);
				dialogoBibliotecaSonidos.setVisible(true);
			}
		});
		btnAadirSonido.setBounds(6, 74, 238, 29);
		getContentPane().add(btnAadirSonido);
		
		JLabel lblSonidoSeleccionado = new JLabel("Sonido Seleccionado:");
		lblSonidoSeleccionado.setBounds(6, 115, 157, 16);
		getContentPane().add(lblSonidoSeleccionado);
		
		textField_1 = new JTextField();
		textField_1.setBounds(6, 143, 238, 28);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAceptarCambios = new JButton("Aceptar Cambios");
		btnAceptarCambios.setBounds(98, 193, 146, 29);
		getContentPane().add(btnAceptarCambios);
	}
	
	public void setParent(InterfazCupiSoundBox interfaz){
		padre = interfaz;
	}
	
}
