package componenteSearch.interfaz;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class ComponenteSearchPanelBusqueda extends JPanel {
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	private JTextField textField;

    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	
	public ComponenteSearchPanelBusqueda(){
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		setSize(new Dimension(320, 423));
		setPreferredSize(new Dimension(320, 423));
		
		JButton btnCat = new JButton("Categorias");
		btnCat.setBounds(6, 364, 117, 53);
		add(btnCat);
		
		JButton btnBusq = new JButton("Busqueda");
		btnBusq.setEnabled(false);
		btnBusq.setBounds(197, 364, 117, 53);
		add(btnBusq);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(124, 364, 72, 53);
		add(btnHome);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(121, 33, 61, 16);
		add(lblBuscar);
		
		textField = new JTextField();
		textField.setBounds(25, 61, 262, 28);
		add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(25, 89, 117, 29);
		add(btnBuscar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(140, 90, 147, 27);
		add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 130, 262, 171);
		add(scrollPane);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(25, 313, 117, 27);
		add(comboBox_1);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(140, 312, 147, 29);
		add(btnAgregar);
	}
}
