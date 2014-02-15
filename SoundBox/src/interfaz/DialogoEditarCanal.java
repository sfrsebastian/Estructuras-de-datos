package interfaz;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import mundo.Canal;
import mundo.Sample;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

public class DialogoEditarCanal extends JDialog {
	
	private InterfazCupiSoundBox padre;
	private JTextField txtNombre;
	final DialogoEditarCanal yo;
	
	private PanelDibujo panelActual;
	private Object[] sonidos;
	private Canal canalActual;
	private JComboBox comboSonidos;
	
	public DialogoEditarCanal(Canal canal, PanelDibujo panelDibujo){
		setSize(250,300);
		setTitle("Editar Canal");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		yo = this;
		panelActual = panelDibujo;
		canalActual = canal;
		
		JLabel lblCambiarNombre = new JLabel("Cambiar Nombre");
		lblCambiarNombre.setBounds(70, 6, 112, 16);
		getContentPane().add(lblCambiarNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(6, 34, 238, 28);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnAadirSonido = new JButton("A\u00F1adir Sonido");
		btnAadirSonido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogoBibliotecaSonidos dialogoBibliotecaSonidos = new DialogoBibliotecaSonidos(true,yo);
				dialogoBibliotecaSonidos.setParent(padre);
				dialogoBibliotecaSonidos.setVisible(true);
			}
		});
		btnAadirSonido.setBounds(6, 74, 238, 29);
		getContentPane().add(btnAadirSonido);
		
		JLabel lblSonidoSeleccionado = new JLabel("Eliminar Sonido:");
		lblSonidoSeleccionado.setBounds(6, 139, 157, 16);
		getContentPane().add(lblSonidoSeleccionado);
		
		JButton btnAceptarCambios = new JButton("Aceptar Cambios");
		btnAceptarCambios.setBounds(98, 243, 146, 29);
		btnAceptarCambios.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = txtNombre.getText();
					if(!nombre.equals("")){
						padre.editarCanal(canalActual,nombre);
					}else{
						padre.refrescarPanelProyecto();
					}
					salir();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
				
			}
		});
		getContentPane().add(btnAceptarCambios);
		
		comboSonidos = new JComboBox();
		comboSonidos.setBounds(6, 167, 238, 27);
		getContentPane().add(comboSonidos);
		
		inicializarCombo(comboSonidos);
		
		JButton btnEliminarSonido = new JButton("Eliminar Sonido");
		btnEliminarSonido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Sample sample = (Sample)comboSonidos.getSelectedItem();
					canalActual.eliminarSonido(sample);
					inicializarCombo(comboSonidos);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnEliminarSonido.setBounds(6, 202, 238, 29);
		getContentPane().add(btnEliminarSonido);
	}
	
	public void inicializarCombo(JComboBox caja){
		caja.removeAll();
		caja.repaint();
		Object[] samples = canalActual.darSonidos();
		if(samples != null && samples.length != 0){
			for (int i = 0; i < samples.length; i++) {
				Sample actual = (Sample)samples[i];
				caja.addItem(actual);
			}
		}else{
			caja.addItem("No hay sonidos en el canal");
		}
	}
	
	public void agregarSonidosACanal(Object[] sonidos){
		panelActual.agregarSonidosACanal(canalActual, sonidos);
	}
	
	public void setParent(InterfazCupiSoundBox interfaz){
		padre = interfaz;
	}
	
	private void salir(){
		this.dispose();
	}
}
