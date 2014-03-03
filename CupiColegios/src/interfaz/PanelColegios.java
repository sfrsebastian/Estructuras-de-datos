package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import mundo.Anio;
import mundo.Colegio;
import mundo.Hijo;

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
	private JTextField txtDepto;
	private JTextField txtNombre;
	private JTextField txtGenero;
	private JTextField txtTipo;
	private JTextField txtJornada;
	private JTextField txtIcfes;
	private JTextField txtCal;
	private JComboBox comboHijos;
	private JComboBox comboAnio;
	
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
				
		String[] arre = {"Codigo","Nombre","Calendario","Genero","Tipo","Nivel"};
		
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
					String cod = (String) table.getValueAt(table.getSelectedRow(), 0);
					cod = cod.substring(0, 2);
					txtDepto.setText(cod);
					txtNombre.setText((String) table.getValueAt(table.getSelectedRow(), 1));
					txtGenero.setText((String) table.getValueAt(table.getSelectedRow(), 3));
					txtTipo.setText((String) table.getValueAt(table.getSelectedRow(), 4));
					txtIcfes.setText((String) table.getValueAt(table.getSelectedRow(), 5));
					txtCal.setText((String) table.getValueAt(table.getSelectedRow(), 2));
					
					//System.out.println(table.getValueAt(table.getSelectedRow(), 0));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(6, 21, 588, 340);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
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
		
		JLabel lblMuncip = new JLabel("Depto:");
		lblMuncip.setBounds(386, 415, 61, 16);
		add(lblMuncip);
		
		txtDepto = new JTextField();
		txtDepto.setEditable(false);
		txtDepto.setColumns(10);
		txtDepto.setBounds(447, 409, 134, 28);
		add(txtDepto);
		
		JLabel cert1 = new JLabel("Cert1");
		cert1.setBounds(65, 524, 127, 59);
		add(cert1);
		
		comboAnio = new JComboBox();
		comboAnio.setBounds(386, 446, 88, 27);
		add(comboAnio);
		
		JButton btnResultados = new JButton("Resultados");
		btnResultados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String codigo = (String) table.getValueAt(table.getSelectedRow(), 0);
				Anio n = (Anio)comboAnio.getSelectedItem();
				Colegio col = padre.buscarColegioAnio(codigo,n);
				
				DialogoResultados dialogoResultados = new DialogoResultados(col,n);
				dialogoResultados.setParent(padre);
				dialogoResultados.setVisible(true);
			}
		});
		btnResultados.setBounds(477, 445, 117, 29);
		add(btnResultados);
		
		comboHijos = new JComboBox();
		comboHijos.setBounds(422, 508, 159, 27);
		add(comboHijos);
		
		JButton btnNewButton = new JButton("A\u00F1adir a");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Hijo hijo = (Hijo)comboHijos.getSelectedItem();
					String codigo = (String) table.getValueAt(table.getSelectedRow(), 0);
					Colegio col = padre.buscarColegio(codigo);
					
					boolean error = false;
					String gen = col.getGenero();
					
					int genCol = 0;
					if(gen.equals("MASCULINO"))
						genCol = 1;
					else if (gen.equals("FEMENINO"))
						genCol = 2;
					
					if(genCol != 0){
					if(hijo.getGenero() != genCol)
						error = true;
						//mostrarError("No se puede agregar el hijo al colegio");
					}
					
					if(col != null && !error){
						hijo.agregarFavorito(col);
						System.out.println("Colegio agregado: " + col);
					}
					//System.out.println(hijo + " : " + cod);
				} catch (Exception e2) {
					// TODO: handle exception
					mostrarError("Ha ocurrido un error inesperado:" + e2.getMessage());
					e2.printStackTrace();
				}
			}
		});
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
	
	public void inicializarComboAnios(){
		comboAnio.removeAll();
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel<>();
		Object[] anios = padre.darAnios();
		for (int i = 0; i < anios.length; i++) {
			Anio n = (Anio)anios[i];
			modelo.addElement(n);
		}
		comboAnio.setModel(modelo);
		comboAnio.repaint();
	}

	public void refrescarTabla(Object[] resultados) {
		table.removeAll();
		
		String[][] datos = darDatosTabla(resultados);
		String[] arre = {"Codigo","Nombre","Calendario","Genero","Tipo","Nivel"};
		
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
		txtDepto.setText("");
		txtNombre.setText("");
		txtGenero.setText("");
		txtTipo.setText("");
		txtIcfes.setText("");
		txtCal.setText("");
	}
	
	private String[][] darDatosTabla(Object[] arreglo){
		String[][] data = new String[arreglo.length][6];
		for (int i = 0; i < arreglo.length; i++) {
			Colegio col = (Colegio)arreglo[i];
			data[i][0] = col.getCodigo();
			data[i][1] = col.getNombre();
			data[i][2] = col.getCalendario();
			data[i][3] = col.getGenero();
			data[i][4] = col.getTipo();
			data[i][5] = col.getNivel();
		}
		
		return data;
	}
	
	/**
	 * Le muestra un error al usuario
	 * @param error El error que se quiere mostrar
	 */
	private void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void refrescar() {
		refrescarComboHijos(comboHijos);
	}
}
