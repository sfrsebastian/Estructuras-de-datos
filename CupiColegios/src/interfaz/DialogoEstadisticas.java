package interfaz;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import mundo.Area;
import mundo.Departamento;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import javax.swing.JTextField;

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
	private JTextField txtResultado;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public DialogoEstadisticas(){
		setResizable(false);
		setTitle("Estadisticas");
		setSize(600, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		estado = 0;
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Estadisticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 6, 588, 466);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		comboDeptos = new JComboBox();
		comboDeptos.setBounds(6, 86, 230, 27);
		panel.add(comboDeptos);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(6, 125, 230, 29);
		panel.add(btnSeleccionar);
		
		tabla = new JTable();
		tabla.setBounds(248, 16, 334, 138);
		panel.add(tabla);
		
		JLabel lblEstadicticasDepartamentoPor = new JLabel("Estadicticas Departamento por a\u00F1o");
		lblEstadicticasDepartamentoPor.setBounds(10, 26, 230, 16);
		panel.add(lblEstadicticasDepartamentoPor);
		
		JLabel lblEligaDepartamento = new JLabel("Eliga Departamento:");
		lblEligaDepartamento.setBounds(6, 64, 142, 16);
		panel.add(lblEligaDepartamento);
		
		JLabel lblMateriaConMayor = new JLabel("Materia con mayor cantidad de 10s fue:");
		lblMateriaConMayor.setBounds(6, 181, 284, 16);
		panel.add(lblMateriaConMayor);
		
		txtResultado = new JTextField();
		txtResultado.setBounds(289, 175, 183, 28);
		panel.add(txtResultado);
		txtResultado.setColumns(10);
		txtResultado.setEditable(false);
		txtResultado.setText(Area.MATEMATICAS);
		
		btnSeleccionar.addActionListener(new ActionListener() {
			@Override
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
		String mejor = padre.darMejorMateria();
		txtResultado.setText(mejor);
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
			modelo = new DefaultTableModel(datos,columns) {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};		
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
