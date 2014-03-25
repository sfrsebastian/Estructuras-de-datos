package componenteSearch.interfaz;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import componenteSearch.mundo.ComponenteSearch;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ComponenteSearchPanelCategoria extends JPanel {
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	private JTextField textField;
	
	private ComponenteSearchPanelCentral padre;

	private ComponenteSearch mundo;
	
	private static ComponenteSearchPanelCategoria self;
	
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	
	public ComponenteSearchPanelCategoria(ComponenteSearchPanelCentral componenteSearchPanelCentral, ComponenteSearch componenteSearch){
		padre = componenteSearchPanelCentral;
		mundo = componenteSearch;
		self = this;
		
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Categorias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		setSize(new Dimension(320, 423));
		setPreferredSize(new Dimension(320, 423));
		
		JButton btnCat = new JButton("Categorias");
		btnCat.setEnabled(false);
		btnCat.setBounds(6, 364, 117, 53);
		add(btnCat);
		
		JButton btnBusq = new JButton("Busqueda");
		btnBusq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.cargarPanelBusqueda(self);
			}
		});
		btnBusq.setBounds(197, 364, 117, 53);
		add(btnBusq);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.cargarPanelHome(self);
			}
		});
		btnHome.setBounds(124, 364, 72, 53);
		add(btnHome);
		
		JLabel lblNewLabel = new JLabel("Manejar Categorias");
		lblNewLabel.setBounds(94, 32, 167, 16);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 55, 268, 201);
		add(scrollPane);
		
		JButton button = new JButton("+");
		button.setBounds(25, 268, 34, 29);
		add(button);
		
		JButton button_1 = new JButton("-");
		button_1.setBounds(71, 268, 34, 29);
		add(button_1);
		
		textField = new JTextField();
		textField.setBounds(124, 268, 169, 28);
		add(textField);
		textField.setColumns(10);
		
		JButton btnVerContenido = new JButton("Ver Contenido");
		btnVerContenido.setBounds(25, 311, 268, 29);
		add(btnVerContenido);
	}
}
