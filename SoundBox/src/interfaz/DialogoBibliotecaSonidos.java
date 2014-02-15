package interfaz;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import mundo.Categoria;
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
	private JComboBox comboCategorias;
	private JButton btnAnadirCat;
	private JButton btnEliminarCategoria;
	
	private DialogoEditarCanal dialogoEditar;
	
	//------------------------------------
	// Constructor
	//------------------------------------
	
	public DialogoBibliotecaSonidos(boolean botonAgregarVisible, DialogoEditarCanal dialogo){
		
		setSize(600, 450);
		setTitle("Biblioteca de Sonidos");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		dialogoEditar = dialogo;
		
		JPanel panelManejarSonidos = new JPanel();
		panelManejarSonidos.setBorder(new TitledBorder(null, "Manejar Sonidos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelManejarSonidos.setBounds(6, 6, 175, 416);
		getContentPane().add(panelManejarSonidos);
		panelManejarSonidos.setLayout(null);
		
		listaSonidos = new JList();
		if(!botonAgregarVisible){
			listaSonidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}else{
			listaSonidos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		}
		listaSonidos.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {
				try{
					Sample sample = (Sample) listaSonidos.getSelectedValue();
					txtNombre.setText(sample.darNombre());
					txtCategorias.setText(arregloATexto(sample.darCategorias()));
				}
				catch(Exception e){
					//En caso de que no hayan sonidos
				}
			}
		});
		
		scrollPane = new JScrollPane(listaSonidos);
		scrollPane.setBounds(6, 20, 163, 254);
		panelManejarSonidos.add(scrollPane);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nombre", "Categoria", "Todos"}));
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
					String filtro = txtFiltro.getText();
					
					String tipoFiltro = (String) comboBox.getSelectedItem();
					if(tipoFiltro.equals("Categoria")){
						refrescarLista(padre.filtrarSonidosPorCategoria(filtro));
					}else if(tipoFiltro.equals("Todos")){
						refrescarLista(padre.darSonidos());
					}else if(tipoFiltro.equals("Nombre")){
						refrescarLista(padre.filtrarSonidosPorNombre(filtro));
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnFiltrar.setBounds(6, 368, 163, 29);
		panelManejarSonidos.add(btnFiltrar);
		
		JPanel panelAgregarSonido = new JPanel();
		panelAgregarSonido.setBorder(new TitledBorder(null, "A\u00F1adir Sonido a Canal", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAgregarSonido.setBounds(193, 317, 401, 105);
		getContentPane().add(panelAgregarSonido);
		panelAgregarSonido.setLayout(null);
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salir();
			}
		});
		botonAceptar.setBounds(282, 66, 117, 29);
		panelAgregarSonido.add(botonAceptar);
		
		botonAgregarSonido = new JButton("A\u00F1adir Sonido(s) Seleccionado(s) a Canal");
		botonAgregarSonido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object[] sonidos = listaSonidos.getSelectedValues();
					dialogoEditar.agregarSonidosACanal(sonidos);
					salir();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		botonAgregarSonido.setBounds(10, 26, 389, 29);
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
		
		JLabel lblAadirSonidosA = new JLabel("Administrar Biblioteca");
		lblAadirSonidosA.setBounds(191, 209, 241, 16);
		getContentPane().add(lblAadirSonidosA);
		
		botonEscogerArchivo = new JButton("Escoger Archivo\n");
		botonEscogerArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.escogerArchivo();
				refrescarLista(padre.darSonidos());
			}
		});
		botonEscogerArchivo.setBounds(193, 237, 175, 29);
		getContentPane().add(botonEscogerArchivo);
		
		botonEscogerCarpeta = new JButton("Escoger Carpeta");
		botonEscogerCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.escogerCarpeta();
				refrescarLista(padre.darSonidos());
			}
		});
		botonEscogerCarpeta.setBounds(193, 276, 175, 29);
		getContentPane().add(botonEscogerCarpeta);
		
		comboCategorias = new JComboBox();
		comboCategorias.setBounds(193, 124, 143, 20);
		getContentPane().add(comboCategorias);
		
		btnAnadirCat = new JButton("A\u00F1adir categoria seleccionada al sonido");
		btnAnadirCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Categoria cat = (Categoria)comboCategorias.getSelectedItem();
					Sample sonido = (Sample) listaSonidos.getSelectedValue();
					padre.agregarCategoriaASonido(sonido, cat);
					refrescar();
					
				} catch (Exception e) {
					// TODO: handle exception
					mostrarError("La categoria no es valida");
				}
			}
		});
		btnAnadirCat.setBounds(343, 123, 241, 23);
		getContentPane().add(btnAnadirCat);
		
		JLabel lblEditarCategoriasDel = new JLabel("Editar categorias del Sonido");
		lblEditarCategoriasDel.setBounds(191, 99, 209, 14);
		getContentPane().add(lblEditarCategoriasDel);

		btnEliminarCategoria = new JButton("Eliminar Categoria del Sonido Seleccionado");
		btnEliminarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Sample sonido = (Sample) listaSonidos.getSelectedValue();
					Categoria cat = (Categoria) comboCategorias.getSelectedItem();	
					if(padre.eliminarCategoriaDeSonido(sonido,cat) == null){
						mostrarError("La categoria que se quiere eliminar no esta en este sonido");
					}else{
						refrescar();
					}
				} catch (Exception e2) {
					mostrarError("Ha ocurrido un error");
				}		
			}
		});
		btnEliminarCategoria.setBounds(343, 175, 241, 23);
		getContentPane().add(btnEliminarCategoria);
		
		JButton btnEliminarSonidoSeleccionado = new JButton("Eliminar Sonido Seleccionado");
		btnEliminarSonidoSeleccionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Sample sonido = (Sample)listaSonidos.getSelectedValue();
					padre.eliminarSonido(sonido);
					refrescarLista(padre.darSonidos());
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnEliminarSonidoSeleccionado.setBounds(372, 237, 222, 29);
		getContentPane().add(btnEliminarSonidoSeleccionado);
		
	}
	
	private void salir(){
		this.dispose();
	}
	
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Hola", JOptionPane.ERROR_MESSAGE);
	}
	
	public void refrescarLista(Object[] sonidos){
		listaSonidos.removeAll();
		DefaultListModel model = new DefaultListModel();
		if(sonidos.length != 0){
			for (int i = 0; i < sonidos.length; i++) {
				Sample sonido = (Sample)sonidos[i];
				model.addElement(sonido);
			}
		}else{
			model.addElement("No hay sonidos");
		}
		listaSonidos.setModel(model);
	}
	
	public void refrescar(){
		Sample sample = (Sample) listaSonidos.getSelectedValue();
		txtNombre.setText(sample.darNombre());
		txtCategorias.setText(arregloATexto(sample.darCategorias()));
	}
	
	public void inicializarComboCategorias(JComboBox caja){
		caja.removeAll();
		Object[] categorias = padre.darCategorias();
		if(categorias.length != 0){
			for (int i = 0; i < categorias.length; i++) {
				Categoria cat = (Categoria)categorias[i];
				comboCategorias.addItem(cat);
			}
		}else{
			comboCategorias.addItem("No hay categorias");
		}
	}

	public String arregloATexto(Object[] arreglo){
		String ret = "";
		
		if(arreglo.length != 0){
		for (int i = 0; i < arreglo.length; i++) {
			Categoria cat = (Categoria)arreglo[i];
			ret += cat.darNombre() + " - ";
		}
		}else{
			ret = "No tiene categorias asignadas";
		}
		return ret;
	}
	
	public void setParent(InterfazCupiSoundBox interfaz){
		padre = interfaz;
		refrescarLista(padre.darSonidos());
		inicializarComboCategorias(comboCategorias);
	}
}

