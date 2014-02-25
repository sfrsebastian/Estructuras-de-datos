package interfaz;

import java.awt.Dimension;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class PanelBusqueda extends JPanel {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	private InterfazCupiColegios padre;
	private JComboBox comboJornada;
	private JComboBox comboGenero;
	private JComboBox comboIcfes;
	private JComboBox comboCal;
	private JComboBox comboTipo;
	private JSpinner spinnerAnio;
	private JSpinner spinnerPuntaje;
	private JComboBox comboDepto;
	private JComboBox comboMunicipio;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public PanelBusqueda(InterfazCupiColegios interfazCupiColegios) {
		setBorder(new TitledBorder(null, "Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setSize(200, 570);
		setPreferredSize(new Dimension(230, 596));
		setLayout(null);
		
		JLabel lblCriterio = new JLabel("Criterio");
		lblCriterio.setBounds(85, 21, 47, 16);
		add(lblCriterio);
		
		JLabel lblJornada = new JLabel("Jornada:");
		lblJornada.setBounds(6, 53, 61, 16);
		add(lblJornada);
		
		comboJornada = new JComboBox();
		comboJornada.setModel(new DefaultComboBoxModel(new String[] {"Diurna", "Nocturna"}));
		comboJornada.setBounds(66, 49, 158, 27);
		add(comboJornada);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(85, 32, 47, 12);
		add(separator);
		
		JLabel lblIcfes = new JLabel("Icfes:");
		lblIcfes.setBounds(6, 81, 61, 16);
		add(lblIcfes);
		
		comboIcfes = new JComboBox();
		comboIcfes.setModel(new DefaultComboBoxModel(new String[] {"Alto", "Bajo"}));
		comboIcfes.setBounds(66, 77, 158, 27);
		add(comboIcfes);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(6, 109, 61, 16);
		add(lblGenero);
		
		comboGenero = new JComboBox();
		comboGenero.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		comboGenero.setBounds(66, 105, 158, 27);
		add(comboGenero);
		
		JLabel lblCalendario = new JLabel("Cal:");
		lblCalendario.setBounds(6, 137, 75, 16);
		add(lblCalendario);
		
		comboCal = new JComboBox();
		comboCal.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "F"}));
		comboCal.setBounds(66, 133, 158, 27);
		add(comboCal);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(6, 165, 61, 16);
		add(lblTipo);
		
		comboTipo = new JComboBox();
		comboTipo.setModel(new DefaultComboBoxModel(new String[] {"Diurno", "Nocturno"}));
		comboTipo.setBounds(66, 161, 158, 27);
		add(comboTipo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 223, 218, 12);
		add(separator_1);
		
		JButton btnBuscarCrit = new JButton("Buscar");
		btnBuscarCrit.setBounds(6, 193, 218, 29);
		add(btnBuscarCrit);
		
		JLabel lblAreaao = new JLabel("Area/A\u00F1o");
		lblAreaao.setBounds(71, 234, 61, 16);
		add(lblAreaao);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(71, 247, 61, 12);
		add(separator_2);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(6, 265, 61, 16);
		add(lblArea);
		
		JComboBox comboArea = new JComboBox();
		comboArea.setModel(new DefaultComboBoxModel(new String[] {"Sociales", "Matematicas", "Espa\u00F1ol"}));
		comboArea.setBounds(66, 261, 158, 27);
		add(comboArea);
		
		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setBounds(6, 293, 61, 16);
		add(lblAo);
		
		spinnerAnio = new JSpinner();
		SpinnerModel sm = new SpinnerNumberModel(2001,2001,2011,1);
		spinnerAnio.setBounds(71, 287, 153, 28);
		spinnerAnio.setModel(sm);
		add(spinnerAnio);
		
		JLabel lblPuntaje = new JLabel("Puntaje:");
		lblPuntaje.setBounds(6, 321, 61, 16);
		add(lblPuntaje);
		
		spinnerPuntaje = new JSpinner();
		spinnerPuntaje.setBounds(71, 315, 153, 28);
		add(spinnerPuntaje);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(6, 349, 218, 29);
		add(btnNewButton);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(6, 382, 218, 12);
		add(separator_3);
		
		JLabel lblUbicacin = new JLabel("Ubicaci\u00F3n");
		lblUbicacin.setBounds(71, 398, 75, 16);
		add(lblUbicacin);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(71, 415, 61, 12);
		add(separator_4);
		
		JLabel lblDepto = new JLabel("Depto:");
		lblDepto.setBounds(6, 430, 61, 16);
		add(lblDepto);
		
		comboDepto = new JComboBox();
		comboDepto.setModel(new DefaultComboBoxModel(new String[] {"Antioquia", "Otro"}));
		comboDepto.setBounds(71, 426, 153, 27);
		add(comboDepto);
		
		JLabel lblMunicipio = new JLabel("Municipio:");
		lblMunicipio.setBounds(6, 458, 75, 16);
		add(lblMunicipio);
		
		comboMunicipio = new JComboBox();
		comboMunicipio.setModel(new DefaultComboBoxModel(new String[] {"Bogota DC", "n1", "n2"}));
		comboMunicipio.setBounds(71, 454, 153, 27);
		add(comboMunicipio);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(6, 486, 218, 29);
		add(btnBuscar);
		padre = interfazCupiColegios;
	}
}
