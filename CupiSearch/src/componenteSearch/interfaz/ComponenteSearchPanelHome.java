package componenteSearch.interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.border.TitledBorder;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Color;

public class ComponenteSearchPanelHome extends JPanel {
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	private JTextField textField;
	
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	
	public ComponenteSearchPanelHome() {
		setBorder(new TitledBorder(null, "Home", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		setSize(new Dimension(320, 423));
		setPreferredSize(new Dimension(320, 423));
		
		JButton btnCat = new JButton("Categorias");
		btnCat.setBounds(6, 364, 117, 53);
		add(btnCat);
		
		JButton btnBusq = new JButton("Busqueda");
		btnBusq.setBounds(197, 364, 117, 53);
		add(btnBusq);
		
		JButton btnHome = new JButton("Home");
		btnHome.setEnabled(false);
		btnHome.setBounds(124, 364, 72, 53);
		add(btnHome);
		
		JButton btnAgregar = new JButton("Agregar Fuente");
		btnAgregar.setBounds(92, 310, 134, 29);
		add(btnAgregar);
		
		JLabel lblNewLabel = new JLabel("Filleeer, filler, filleeer, filler");
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setBounds(29, 33, 265, 203);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(39, 248, 240, 28);
		add(textField);
		textField.setColumns(10);
	}
}
