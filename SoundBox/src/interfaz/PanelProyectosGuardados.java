package interfaz;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import mundo.Proyecto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelProyectosGuardados extends JPanel {
	private JTextField txtFiltro;
	private InterfazCupiSoundBox padre;
	private JComboBox comboFiltro;
	private JList listaProyectos;

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
		
		listaProyectos = new JList();
		listaProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(listaProyectos);
		scrollPane.setBounds(6, 20, 179, 282);
		add(scrollPane);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String filtro = txtFiltro.getText();
					String combo = (String) comboFiltro.getSelectedItem();
					
					if(combo.equals("Todos")){
						refrescarListaProyectos(padre.darProyectos());
					}
					else if	(filtro.equals("") || combo.equals("") && !combo.equals("Todos")){
						mostrarError("Debe llenar el campo");
					}
					else{
						Proyecto p = padre.filtrarProyectos(filtro,combo);
						Object[] proyectos = {p}; 
						refrescarListaProyectos(proyectos);
					}
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
		});
		btnFiltrar.setBounds(6, 377, 179, 29);
		add(btnFiltrar);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Proyecto proyectoCargado = (Proyecto)listaProyectos.getSelectedValue();
					padre.cargarProyecto(proyectoCargado);
				} catch (Exception e) {
					mostrarError("No hay proyecto valido seleccionado");
				}
			}
		});
		btnCargar.setBounds(6, 409, 179, 29);
		add(btnCargar);
		
		inicializarComboBox(comboFiltro);
	}
	
	private void inicializarComboBox(JComboBox caja){
		caja.addItem("Todos");
		caja.addItem("Nombre");
		caja.addItem("Autor");
		caja.setSelectedIndex(-1);
	}
	
	public void refrescarListaProyectos(Object[] entrantes){
		DefaultListModel model = new DefaultListModel();
		listaProyectos.removeAll();
		if(entrantes.length != 0){
			for (int i = 0; i < entrantes.length; i++) {
				Proyecto p = (Proyecto) entrantes[i];
				model.addElement(p);
			}
			listaProyectos.setModel(model);
		}
		else{
			model.addElement("No hay proyectos");
			listaProyectos.setModel(model);
		}
	}
	
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Hola", JOptionPane.ERROR_MESSAGE);
	}
	
	public void setParent(InterfazCupiSoundBox interfaz){
		padre = interfaz;
		refrescarListaProyectos(padre.darProyectos());		
	}

	public Proyecto darProyectoSeleccionado() {
		return (Proyecto) listaProyectos.getSelectedValue();
	}

}
