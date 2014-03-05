package interfaz;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import mundo.Departamento;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;

public class DialogoEstadisticas extends JDialog {
	
	//------------------------------------------
	// Atributos
	//------------------------------------------

	/**
	 * La referencia al padre, la interfaz pirncipal
	 */
	private InterfazCupiColegios padre;
	private JComboBox comboDeptos;
	private JTable tabla;
	
	private int estado;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public DialogoEstadisticas(){
		setResizable(false);
		setTitle("Estadisticas");
		setSize(600, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		estado = 0;
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Estadisticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 6, 588, 466);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		comboDeptos = new JComboBox();
		comboDeptos.setBounds(12, 375, 230, 27);
		panel.add(comboDeptos);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(12, 431, 230, 29);
		panel.add(btnSeleccionar);
		
		tabla = new JTable();
		tabla.setBounds(6, 25, 576, 265);
		panel.add(tabla);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 291, 576, 12);
		panel.add(separator);
		
		JLabel lblEstadicticasDepartamentoPor = new JLabel("Estadicticas Departamento por a\u00F1o");
		lblEstadicticasDepartamentoPor.setBounds(16, 315, 230, 16);
		panel.add(lblEstadicticasDepartamentoPor);
		
		JLabel lblEligaDepartamento = new JLabel("Eliga Departamento:");
		lblEligaDepartamento.setBounds(12, 353, 142, 16);
		panel.add(lblEligaDepartamento);
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Departamento depto = (Departamento)comboDeptos.getSelectedItem();
				String[][] matriz = padre.darPromedioAnios(depto.getCodigo());
				refrescarTabla(matriz, 1);
			}
		});
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	public void setParent(InterfazCupiColegios interfaz){
		padre = interfaz;
		inicializarCombos();
	}
	
	/**
	 * Le muestra un error al usuario
	 * @param error El error que se quiere mostrar
	 */
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void refrescarTabla(String[][] datos, int estado){
		tabla.removeAll();
		DefaultTableModel modelo = null;
		
		if(estado == 1){
			String[] columns = {"Departamento","Promedio"};
			modelo = new DefaultTableModel(datos, columns);
		}
		else if (estado == 2){
			String[] columns = {"",""};
		}
		else{
			String[] columns = {"",""};
		}
		tabla.setModel(modelo);
	}
	
	public void inicializarCombos(){
		Object[] deptos = padre.darDepartamentos();
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		for (int i = 0; i < deptos.length; i++) {
			Departamento depto = (Departamento)deptos[i];
			model.addElement(depto);
		}
		comboDeptos.setModel(model);
	}
}
