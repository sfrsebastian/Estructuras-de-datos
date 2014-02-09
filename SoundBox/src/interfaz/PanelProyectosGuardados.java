package interfaz;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelProyectosGuardados extends JPanel {
	private JTextField txtFiltro;
	private InterfazCupiSoundBox padre;
	private JComboBox comboFiltro;

	/**
	 * Create the panel.
	 */
	public PanelProyectosGuardados() {
		setBorder(new TitledBorder(null, "Proyectos Guardados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setSize(191, 444);
		setLayout(null);
		
		comboFiltro = new JComboBox();
		comboFiltro.setToolTipText("Filtrar por:");
		comboFiltro.setBounds(6, 314, 179, 27);
		add(comboFiltro);
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(4, 346, 181, 28);
		add(txtFiltro);
		txtFiltro.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 20, 179, 282);
		add(scrollPane);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String filtro = txtFiltro.getText();
					String combo = (String) comboFiltro.getSelectedItem();
					
					if(filtro.equals("") || combo.equals(""))
						mostrarError("Debe llenar el campo");
					padre.filtrarProyectos(filtro,combo);
					
				}catch(Exception e2){
					
				}
			}
		});
		btnFiltrar.setBounds(6, 377, 179, 29);
		add(btnFiltrar);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.setBounds(6, 409, 179, 29);
		add(btnCargar);
		
		inicializarComboBox(comboFiltro);
	}
	
	private void inicializarComboBox(JComboBox caja){
		caja.addItem("Nombre");
		caja.addItem("Autor");
		caja.setSelectedIndex(-1);
	}
	
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Hola", JOptionPane.ERROR_MESSAGE);
	}
	
	public void setParent(InterfazCupiSoundBox interfaz){
		padre = interfaz;
	}

}
