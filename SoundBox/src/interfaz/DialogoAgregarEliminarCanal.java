package interfaz;

import javafx.scene.control.ComboBox;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import mundo.Canal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoAgregarEliminarCanal extends JDialog {

	private InterfazCupiSoundBox padre;
	private JComboBox comboCanales;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JTextField txtNombre;
	
	public DialogoAgregarEliminarCanal(){
		setSize(300,200);
		setTitle("Agregar Eliminar Canal");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblEliminarCanal = new JLabel("Eliminar Canal:");
		lblEliminarCanal.setBounds(6, 10, 94, 16);
		getContentPane().add(lblEliminarCanal);
		
		comboCanales = new JComboBox();
		comboCanales.setBounds(112, 6, 182, 27);
		getContentPane().add(comboCanales);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Canal canalAEliminar = (Canal) comboCanales.getSelectedItem();
					if(canalAEliminar.equals("No hay canales")){
						mostrarError("No hay canal a eliminar");
					}else{
						padre.eliminarCanal(canalAEliminar);
						inicializarComboBox(comboCanales);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnEliminar.setBounds(6, 38, 288, 29);
		getContentPane().add(btnEliminar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 65, 288, 12);
		getContentPane().add(separator);
		
		JLabel lblAgregarCanal = new JLabel("Agregar Canal:");
		lblAgregarCanal.setBounds(6, 79, 159, 16);
		getContentPane().add(lblAgregarCanal);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(6, 107, 288, 28);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String canal = txtNombre.getText();
					if(canal.equals("")){
						mostrarError("Debe llenar el campo");
					}else{
					padre.agregarCanal(canal);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnAgregar.setBounds(6, 143, 288, 29);
		getContentPane().add(btnAgregar);
	}
	
	public void inicializarComboBox(JComboBox caja){
		caja.removeAll();
		
		Object[] canales = padre.darCanales();
		if(canales != null){
			for (Object canal : canales) {
				comboCanales.addItem(canal);
			}
		}else{
			comboCanales.addItem("No hay canales");
		}
	}
	
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Hola", JOptionPane.ERROR_MESSAGE);
	}
	
	public void setPadre(InterfazCupiSoundBox interfaz){
		padre = interfaz;
		inicializarComboBox(comboCanales);
	}
}
