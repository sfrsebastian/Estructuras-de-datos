package interfaz;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import mundo.Colegio;
import mundo.Hijo;

import com.sun.javafx.scene.layout.region.Margins;

import helper.LectorExcel;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PanelColegios extends JPanel {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * La refrencia a la interfaz padre
	 */
	private InterfazCupiColegios padre;
	private JTable table;
	private JTextField txtDireccion;
	private JTextField txtMunicipio;
	private JTextField txtNombre;
	private JTextField txtGenero;
	private JTextField txtTipo;
	private JTextField txtJornada;
	private JTextField txtIcfes;
	private JTextField txtCal;
	private JComboBox comboHijos;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	/**
	 * Construye el panel colegios y asigna al padre
	 * @param interfazCupiColegios
	 */
	public PanelColegios(InterfazCupiColegios interfazCupiColegios) {
		setBorder(new TitledBorder(null, "Colegios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		padre = interfazCupiColegios;
		setSize(600, 600);
		setLayout(null);
		
//		LectorExcel lector = new LectorExcel("./data/2004.xls", 8860, 19, 0, 1);
//		
//		String[][] datos = lector.leer();
//		String[] columnas = lector.leerTitulos();
//		
//		try {
//			lector.construirTablaHashing();
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
//		DefaultTableModel tableModel = new DefaultTableModel(datos,columnas){
//			
//		    @Override
//		    public boolean isCellEditable(int row, int column) {
//		        return false;
//		    }
//		};
		
		String[] arre = {"Codigo","Nombre","Calendario","Genero","Tipo"};
		
		DefaultTableModel tableModel = new DefaultTableModel(arre,4){
			
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		
		table = new JTable();
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				try {
					txtDireccion.setText((String) table.getValueAt(table.getSelectedRow(), 0));
					txtMunicipio.setText("");
					txtNombre.setText((String) table.getValueAt(table.getSelectedRow(), 1));
					txtGenero.setText((String) table.getValueAt(table.getSelectedRow(), 3));
					txtTipo.setText((String) table.getValueAt(table.getSelectedRow(), 4));
					txtIcfes.setText("");
					txtCal.setText((String) table.getValueAt(table.getSelectedRow(), 2));
					//System.out.println(table.getValueAt(table.getSelectedRow(), 0));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(6, 21, 588, 340);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		
		JLabel lblCertificados = new JLabel("Certificados:");
		lblCertificados.setBounds(6, 496, 80, 16);
		add(lblCertificados);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 484, 588, 12);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 362, 588, 12);
		add(separator_1);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(6, 379, 61, 16);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(65, 373, 127, 28);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		txtGenero = new JTextField();
		txtGenero.setEditable(false);
		txtGenero.setColumns(10);
		txtGenero.setBounds(65, 409, 127, 28);
		add(txtGenero);
		
		txtTipo = new JTextField();
		txtTipo.setEditable(false);
		txtTipo.setColumns(10);
		txtTipo.setBounds(65, 444, 127, 28);
		add(txtTipo);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(6, 415, 61, 16);
		add(lblGenero);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(6, 450, 61, 16);
		add(lblTipo);
		
		JLabel lblJornada = new JLabel("Jornada:");
		lblJornada.setBounds(209, 379, 61, 16);
		add(lblJornada);
		
		JLabel lblIcfes = new JLabel("Icfes:");
		lblIcfes.setBounds(209, 415, 61, 16);
		add(lblIcfes);
		
		JLabel lblCalend = new JLabel("Calend:");
		lblCalend.setBounds(209, 450, 61, 16);
		add(lblCalend);
		
		txtJornada = new JTextField();
		txtJornada.setEditable(false);
		txtJornada.setColumns(10);
		txtJornada.setBounds(270, 373, 104, 28);
		add(txtJornada);
		
		txtIcfes = new JTextField();
		txtIcfes.setEditable(false);
		txtIcfes.setColumns(10);
		txtIcfes.setBounds(270, 409, 104, 28);
		add(txtIcfes);
		
		txtCal = new JTextField();
		txtCal.setEditable(false);
		txtCal.setColumns(10);
		txtCal.setBounds(270, 444, 104, 28);
		add(txtCal);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(447, 373, 134, 28);
		add(txtDireccion);
		
		JLabel lblDireccion = new JLabel("Codigo:");
		lblDireccion.setBounds(386, 379, 61, 16);
		add(lblDireccion);
		
		JLabel lblMuncip = new JLabel("Muncip:");
		lblMuncip.setBounds(386, 415, 61, 16);
		add(lblMuncip);
		
		txtMunicipio = new JTextField();
		txtMunicipio.setEditable(false);
		txtMunicipio.setColumns(10);
		txtMunicipio.setBounds(447, 409, 134, 28);
		add(txtMunicipio);
		
		JLabel cert1 = new JLabel("Cert1");
		cert1.setBounds(65, 524, 127, 59);
		add(cert1);
		
		JComboBox comboAnio = new JComboBox();
		comboAnio.setModel(new DefaultComboBoxModel(new String[] {"2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011"}));
		comboAnio.setBounds(386, 446, 88, 27);
		add(comboAnio);
		
		JButton btnResultados = new JButton("Resultados");
		btnResultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnResultados.setBounds(477, 445, 117, 29);
		add(btnResultados);
		
		comboHijos = new JComboBox();
		comboHijos.setBounds(422, 508, 159, 27);
		add(comboHijos);
		
		JButton btnNewButton = new JButton("A\u00F1adir a");
		btnNewButton.setBounds(422, 540, 159, 29);
		add(btnNewButton);
	}
	
	public void refrescarComboHijos(JComboBox caja){
		caja.removeAll();
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		
		Object[] hijos = padre.darHijos();
		
		if(hijos != null && hijos.length != 0){
			for (int i = 0; i < hijos.length; i++) {
				Hijo hijoActual = (Hijo) hijos[i];
				model.addElement(hijoActual);
			}
		}else{
			model.addElement("No tiene hijos");
		}
		
		caja.setModel(model);
		caja.repaint();
	}

	public void refrescarTabla(Object[] resultados) {
		table.removeAll();
		
		String[][] datos = darDatosTabla(resultados);
		String[] arre = {"Codigo","Nombre","Calendario","Genero","Tipo"};
		
		DefaultTableModel model = new DefaultTableModel(datos,arre) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};		
		
		table.setModel(model);
	}
	
	private void limpiarTextos(){
		txtDireccion.setText("");
		txtMunicipio.setText("");
		txtNombre.setText("");
		txtGenero.setText("");
		txtTipo.setText("");
		txtIcfes.setText("");
		txtCal.setText("");
	}
	
	private String[][] darDatosTabla(Object[] arreglo){
		String[][] data = new String[arreglo.length][5];
		
		for (int i = 0; i < arreglo.length; i++) {
			Colegio col = (Colegio)arreglo[i];
			data[i][0] = col.getCodigo();
			data[i][1] = col.getNombre();
			data[i][2] = col.getCalendario();
			data[i][3] = col.getGenero();
			data[i][4] = col.getTipo();
		}
		
		return data;
	}

	public void refrescar() {
		refrescarComboHijos(comboHijos);
	}
}
