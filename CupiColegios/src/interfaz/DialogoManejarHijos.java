package interfaz;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import mundo.Colegio;
import mundo.Hijo;

public class DialogoManejarHijos extends JDialog {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * La referencia a la interfaz
	 */
	private InterfazCupiColegios padre;
	private JTextField txtEdad;
	private JTextField txtTel;
	private JTextField txtEncargado;
	private JTextField txtNombre;
	private JTextField txtGenero;
	
	private JList listaHijos;
	private JList listaColegiosFav;
	private JList listaColegiosRec;
	
	private DialogoManejarHijos self;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public DialogoManejarHijos(){
		self = this;
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Manejar Hijos");
		setSize(new Dimension(600, 500));
		getContentPane().setLayout(null);
		
		listaHijos = new JList();
		//TSTN
//		DefaultListModel model = new DefaultListModel();
//		model.addElement("hola");
//		model.addElement("carro");
//		model.addElement("perro");
//		model.addElement("hijo");
//		listaHijos.setModel(model);
		listaHijos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaHijos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					Hijo hijo = (Hijo)listaHijos.getSelectedValue();
					refrescarListasColegios(hijo);
					refrescarListasColegios(null);
					
					
					limpiarTextos();
					txtEdad.setText(hijo.getNombre());
					txtEncargado.setText(hijo.getAcudiente());
					txtGenero.setText("" + hijo.getGenero());
					txtNombre.setText(hijo.getNombre());
					txtTel.setText("" + hijo.getTelefono());
					
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		listaHijos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	System.out.println("Double cliked!");
		        	Hijo elim = (Hijo)list.getSelectedValue();
		           // list.remove(index);
		        	padre.eliminarHijo(elim);
		        	padre.refrescarHijos();
		        	
		        	refrescarTodasListas(padre.darHijos());
		        } else if (evt.getClickCount() == 3) {   // Triple-click
		            int index = list.locationToIndex(evt.getPoint());
		        }
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane(listaHijos);
		scrollPane.setBorder(new TitledBorder(null, "Hijos Registrados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(6, 6, 168, 422);
		getContentPane().add(scrollPane);
		
		JButton btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.setBounds(6, 440, 168, 29);
		btnAnadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DialogoAgregarHijo dialogoAgregarHijo = new DialogoAgregarHijo(self);
				dialogoAgregarHijo.setParent(padre);
				dialogoAgregarHijo.setVisible(true);
			}
		});
		getContentPane().add(btnAnadir);
		
		listaColegiosFav = new JList();
		listaColegiosFav.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane_1 = new JScrollPane(listaColegiosFav);
		scrollPane_1.setBorder(new TitledBorder(null, "Favoritos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setBounds(186, 6, 192, 250);
		getContentPane().add(scrollPane_1);
		
		listaColegiosRec = new JList();
		listaColegiosRec.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane_2 = new JScrollPane(listaColegiosRec);
		scrollPane_2.setBorder(new TitledBorder(null, "Recomendados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_2.setBounds(390, 6, 204, 250);
		getContentPane().add(scrollPane_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informacion Hijo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(186, 268, 408, 201);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFoto = new JLabel("            Foto");
		lblFoto.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFoto.setBounds(16, 24, 124, 160);
		panel.add(lblFoto);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(152, 24, 61, 16);
		panel.add(lblNombre);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(152, 52, 61, 16);
		panel.add(lblGenero);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(152, 80, 61, 16);
		panel.add(lblEdad);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(152, 108, 61, 16);
		panel.add(lblTelefono);
		
		JLabel lblEncargado = new JLabel("Encargado:");
		lblEncargado.setBounds(152, 136, 69, 16);
		panel.add(lblEncargado);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(238, 18, 164, 28);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtGenero = new JTextField();
		txtGenero.setEditable(false);
		txtGenero.setColumns(10);
		txtGenero.setBounds(238, 46, 164, 28);
		panel.add(txtGenero);
		
		txtEdad = new JTextField();
		txtEdad.setEditable(false);
		txtEdad.setColumns(10);
		txtEdad.setBounds(238, 74, 164, 28);
		panel.add(txtEdad);
		
		txtTel = new JTextField();
		txtTel.setEditable(false);
		txtTel.setColumns(10);
		txtTel.setBounds(238, 102, 164, 28);
		panel.add(txtTel);
		
		txtEncargado = new JTextField();
		txtEncargado.setEditable(false);
		txtEncargado.setColumns(10);
		txtEncargado.setBounds(238, 130, 164, 28);
		panel.add(txtEncargado);
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	public void setParent(InterfazCupiColegios interfaz){
		padre = interfaz;
		refrescarTodasListas(padre.darHijos());
	}
	
	public void refrescarListasColegios(Object hijo){
		listaColegiosFav.removeAll();
		listaColegiosRec.removeAll();
		
		Object[] colegiosFav = padre.darColegiosFavoritosHijo(hijo);
		DefaultListModel mode1 = new DefaultListModel();

		if(colegiosFav != null && colegiosFav.length != 0){
			for (int i = 0; i < colegiosFav.length; i++) {
				Colegio col = (Colegio) colegiosFav[i];
				mode1.addElement(col);
			}
		}else{
			mode1.addElement("No hay colegios favoritos");
		}
		listaColegiosFav.setModel(mode1);
		
		Object[] colegiosRec = padre.darColegiosRecomendados(hijo);
		DefaultListModel mode2 = new DefaultListModel();

		if(colegiosRec != null && colegiosRec.length != 0){
			for (int i = 0; i < colegiosRec.length; i++) {
				Colegio col = (Colegio)colegiosRec[i];
				mode2.addElement(col);
			}
		}else{
			mode2.addElement("No hay colegios recomendados");
		}
		listaColegiosRec.setModel(mode2);
	}
	
	public void refrescarTodasListas(Object[] listaColegios){
		listaHijos.removeAll();
		listaColegiosFav.removeAll();
		listaColegiosRec.removeAll();
		DefaultListModel model = new DefaultListModel();
		if(listaColegios != null && listaColegios.length != 0){
			for (int i = 0; i < listaColegios.length; i++) {
				Hijo hijo = (Hijo)listaColegios[i];
				model.addElement(hijo);
			}
		}else{
			model.addElement("No hay hijos registrados");
		}
		listaHijos.setModel(model);
	}
	
	/**
	 * Limpia todos los textos
	 */
	private void limpiarTextos() {
		txtEdad.setText("");
		txtEncargado.setText("");
		txtGenero.setText("");
		txtNombre.setText("");
		txtTel.setText("");
	}

	/**
	 * Le muestra un error al usuario
	 * @param error El error que se quiere mostrar
	 */
	private void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
