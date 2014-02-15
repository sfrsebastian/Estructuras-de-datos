package interfaz;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import mundo.Reproductor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoNuevoProyecto extends JDialog {

	private InterfazCupiSoundBox padre;
	private JTextField txtNombre;
	private JTextField txtAutor;
	private JTextField txtCanales;
	private JButton botonCrearProyecto;
	
	public DialogoNuevoProyecto(){
		setSize(250, 250);
		setTitle("Nuevo Proyecto");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(6, 21, 61, 16);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(98, 15, 146, 28);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(6, 55, 61, 16);
		getContentPane().add(lblAutor);
		
		txtAutor = new JTextField();
		txtAutor.setBounds(98, 49, 146, 28);
		getContentPane().add(txtAutor);
		txtAutor.setColumns(10);
		
		JLabel lblCanales = new JLabel("Canales:");
		lblCanales.setBounds(6, 91, 61, 16);
		getContentPane().add(lblCanales);
		
		txtCanales = new JTextField();
		txtCanales.setBounds(98, 85, 146, 28);
		getContentPane().add(txtCanales);
		txtCanales.setColumns(10);
		
		botonCrearProyecto = new JButton("Crear Proyecto");
		botonCrearProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String nombre = txtNombre.getText();
					String autor = txtAutor.getText();
					int canales = Integer.parseInt(txtCanales.getText());
					
					if(nombre.equals("") || autor.equals("") ){
						mostrarError("Debe llenar todos los campos");
					}else{
						padre.crearProyecto(nombre, autor, canales);
						dismissSelf();
					}
					
				}catch(Exception e1){
					e1.printStackTrace();
					mostrarError("Debe llenar todos los campos correctamente");
				}
			}
		});
		botonCrearProyecto.setBounds(6, 193, 238, 29);
		getContentPane().add(botonCrearProyecto);
	}
	
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Hola", JOptionPane.ERROR_MESSAGE);
	}
	
	public void setParent(InterfazCupiSoundBox interfaz){
		padre = interfaz;
	}
	
	public void dismissSelf(){
		this.dispose();
	}
	
}
