package componenteSearch.interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.border.TitledBorder;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import componenteSearch.mundo.ComponenteSearch;
import componenteSearch.mundo.Exploracion;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSpinner;

public class ComponenteSearchPanelHome extends JPanel {
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	private JTextField textField;
	
	private ComponenteSearchPanelCentral padre;
	
	private ComponenteSearch mundo;
	
	private static ComponenteSearchPanelHome self;
	private JSpinner spinnerTiempo;
	private JSpinner spinnerNivel;
	private JLabel lblEstadisticas;
	
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	
	public ComponenteSearchPanelHome(ComponenteSearchPanelCentral papa, ComponenteSearch componenteSearch) {
		padre = papa;
		mundo = componenteSearch;
		self = this;
		
		setBorder(new TitledBorder(null, "Home", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		setSize(new Dimension(320, 423));
		setPreferredSize(new Dimension(320, 423));
		
		JButton btnCat = new JButton("Categorias");
		btnCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.cargarPanelCategoria(self);
				padre.tiempoBusqueda = (Integer)spinnerTiempo.getValue();
			}
		});
		btnCat.setBounds(6, 364, 117, 53);
		add(btnCat);
		
		JButton btnBusq = new JButton("Busqueda");
		btnBusq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.cargarPanelBusqueda(self);
				padre.tiempoBusqueda = (Integer)spinnerTiempo.getValue();
			}
		});
		btnBusq.setBounds(197, 364, 117, 53);
		add(btnBusq);
		
		JButton btnHome = new JButton("Home");
		btnHome.setEnabled(false);
		btnHome.setBounds(124, 364, 72, 53);
		add(btnHome);
		
		JButton btnAgregar = new JButton("Agregar Fuente");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String fuente = textField.getText();
					padre.agregarFuente(fuente);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnAgregar.setBounds(29, 310, 134, 29);
		add(btnAgregar);
		
		lblEstadisticas = new JLabel("Estadisticas");
		lblEstadisticas.setBackground(Color.ORANGE);
		lblEstadisticas.setBounds(29, 85, 265, 125);
		add(lblEstadisticas);
		
		textField = new JTextField("http://");
		textField.setBounds(29, 222, 265, 28);
		add(textField);
		textField.setColumns(10);
		
		spinnerTiempo = new JSpinner();
		spinnerTiempo.setModel(new SpinnerNumberModel(0,0,60,1));
		spinnerTiempo.setBounds(165, 276, 129, 28);
		add(spinnerTiempo);
		
		JLabel lblTiempoBusqueda = new JLabel("Tiempo busqueda:");
		lblTiempoBusqueda.setBounds(29, 282, 124, 16);
		add(lblTiempoBusqueda);
		
		JButton btnExplorar = new JButton("Explorar");
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinnerVal = (Integer)spinnerTiempo.getValue();
				int niveles = (Integer)spinnerNivel.getValue();
				mundo.explorarSitios(spinnerVal*1000,niveles);
				Exploracion exploracion = mundo.getExploracionActual();
				lblEstadisticas.setText("<html>Tiempo de busqueda: " + exploracion.getTiempoTotal() + " segundos" + "<br>" + "Total recursos: " + exploracion.darCantidadRecursos()+"</html>");
			}
		});
		btnExplorar.setBounds(162, 310, 132, 29);
		add(btnExplorar);
		
		JLabel lblNivelExploracion = new JLabel("Nivel Exploracion:");
		lblNivelExploracion.setBounds(29, 254, 124, 16);
		add(lblNivelExploracion);
		
		spinnerNivel = new JSpinner();
		spinnerNivel.setModel(new SpinnerNumberModel(0,0,200,1));
		spinnerNivel.setBounds(165, 248, 129, 28);
		add(spinnerNivel);
		
		JButton btnVerHistorial = new JButton("Ver Historial");
		btnVerHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.cargarPanelVistaHistorial(self);
			}
		});
		btnVerHistorial.setBounds(29, 38, 117, 29);
		add(btnVerHistorial);
	}
	
	public void setSpinnerValue(int valor){
		spinnerTiempo.setModel(new SpinnerNumberModel(valor,0,60,1));
	}

	public void setSpinnerNivel(int nivel) {
		spinnerNivel.setModel(new SpinnerNumberModel(nivel,0,200,1));
	}
}
