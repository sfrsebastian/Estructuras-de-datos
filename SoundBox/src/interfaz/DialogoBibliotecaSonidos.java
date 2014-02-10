package interfaz;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import mundo.Sample;

public class DialogoBibliotecaSonidos extends JDialog {

	//------------------------------------
	// Atributos
	//------------------------------------
	
	private InterfazCupiSoundBox padre;
	private JTextField txtNombre;
	private JTextField txtCategorias;
	
	private boolean botonVisible;
	private JButton botonEscogerCarpeta;
	private JButton botonEscogerArchivo;
	private JButton botonAceptar;
	private JButton botonAgregarSonido;
	private JScrollPane scrollPane;
	private JComboBox comboBox;
	private JLabel lblFiltrarPor;
	private JTextField txtFiltro;
	private JButton btnFiltrar;
	
	private JList listaSonidos;
	
	//------------------------------------
	// Constructor
	//------------------------------------
	
	public DialogoBibliotecaSonidos(boolean botonAgregarVisible){
		
		setSize(600, 450);
		setTitle("Biblioteca de Sonidos");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panelManejarSonidos = new JPanel();
		panelManejarSonidos.setBorder(new TitledBorder(null, "Manejar Sonidos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelManejarSonidos.setBounds(6, 6, 175, 416);
		getContentPane().add(panelManejarSonidos);
		panelManejarSonidos.setLayout(null);
		
		listaSonidos = new JList();
		listaSonidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane = new JScrollPane(listaSonidos);
		scrollPane.setBounds(6, 20, 163, 254);
		panelManejarSonidos.add(scrollPane);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nombre", "Categoria"}));
		comboBox.setBounds(6, 309, 163, 27);
		panelManejarSonidos.add(comboBox);
		
		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setBounds(6, 286, 129, 16);
		panelManejarSonidos.add(lblFiltrarPor);
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(6, 336, 163, 28);
		panelManejarSonidos.add(txtFiltro);
		txtFiltro.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String tipoFiltro = (String) comboBox.getSelectedItem();
					String filtro = txtFiltro.getText();
					padre.filtrarSonidos(tipoFiltro,filtro);
					refrescarLista(padre.darSonidos());
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnFiltrar.setBounds(6, 368, 163, 29);
		panelManejarSonidos.add(btnFiltrar);
		
		JPanel panelAgregarSonido = new JPanel();
		panelAgregarSonido.setBorder(new TitledBorder(null, "A\u00F1adir Sonido a Canal", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAgregarSonido.setBounds(193, 190, 401, 232);
		getContentPane().add(panelAgregarSonido);
		panelAgregarSonido.setLayout(null);
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(278, 197, 117, 29);
		panelAgregarSonido.add(botonAceptar);
		
		botonAgregarSonido = new JButton("A\u00F1adir Sonido Seleccionado a Canal");
		botonAgregarSonido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sonido = (String) listaSonidos.getSelectedValue();
					padre.agregarSonidoACanal(sonido);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		botonAgregarSonido.setBounds(6, 23, 389, 29);
		panelAgregarSonido.add(botonAgregarSonido);
		if(botonAgregarVisible)
			botonAgregarSonido.setEnabled(true);
		else
			botonAgregarSonido.setEnabled(false);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(193, 16, 61, 16);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(292, 10, 302, 28);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCategorias = new JLabel("Categorias:");
		lblCategorias.setBounds(193, 44, 83, 16);
		getContentPane().add(lblCategorias);
		
		txtCategorias = new JTextField();
		txtCategorias.setEnabled(false);
		txtCategorias.setBounds(292, 38, 302, 28);
		getContentPane().add(txtCategorias);
		txtCategorias.setColumns(10);
		
		JLabel lblAadirSonidosA = new JLabel("A\u00F1adir sonidos a la Biblioteca");
		lblAadirSonidosA.setBounds(193, 80, 241, 16);
		getContentPane().add(lblAadirSonidosA);
		
		botonEscogerArchivo = new JButton("Escoger Archivo\n");
		botonEscogerArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.escogerArchivo();
			}
		});
		botonEscogerArchivo.setBounds(193, 107, 175, 29);
		getContentPane().add(botonEscogerArchivo);
		
		botonEscogerCarpeta = new JButton("Escoger Carpeta");
		botonEscogerCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.escogerCarpeta();
			}
		});
		botonEscogerCarpeta.setBounds(193, 148, 175, 29);
		getContentPane().add(botonEscogerCarpeta);
		
	}
	
	public void refrescarLista(Sample[] sonidos){
		DefaultListModel model = new DefaultListModel();
		if(sonidos != null){
		for (Sample sample : sonidos) {
			model.addElement(sample);
		}
		}else{
			model.addElement("No hay sonidos");
		}
		listaSonidos.setModel(model);
	}
	
	public void setParent(InterfazCupiSoundBox interfaz){
		padre = interfaz;
		refrescarLista(padre.darSonidos());
	}
}

