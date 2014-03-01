package interfaz;

import java.awt.Dimension;

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
import javax.swing.JCheckBox;

import mundo.Criterio;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelBusqueda extends JPanel {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	private InterfazCupiColegios padre;
	private JSpinner spinnerAnio;
	private JSpinner spinnerPuntaje;
	private JComboBox comboDepto;
	private JComboBox comboMunicipio;
	private JCheckBox chckbxDiurn;
	private JCheckBox chckbxNocturna;
	private JCheckBox chckbxSuperior;
	private JCheckBox chckbxSuperior_1;
	private JCheckBox chckbxAlto;
	private JCheckBox chckbxMedio;
	private JCheckBox chckbxBajo;
	private JCheckBox chckbxInferior;
	private JCheckBox chckbxMuyInferior;
	private JCheckBox chckbxMasculino;
	private JCheckBox chckbxFemenino;
	private JCheckBox chckbxMixto;
	private JCheckBox chckbxA;
	private JCheckBox chckbxB;
	private JCheckBox chckbxF;
	private JCheckBox chckbxPublico;
	private JCheckBox chckbxPrivado;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public PanelBusqueda(InterfazCupiColegios interfazCupiColegios) {
		padre = interfazCupiColegios;
		
		setBorder(new TitledBorder(null, "Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setSize(200, 570);
		setPreferredSize(new Dimension(287, 596));
		setLayout(null);
		
		chckbxPrivado = new JCheckBox("Privado");
		chckbxPrivado.setBounds(144, 230, 97, 23);
		add(chckbxPrivado);
		
		chckbxF = new JCheckBox("F");
		chckbxF.setBounds(227, 202, 54, 23);
		add(chckbxF);
		
		chckbxB = new JCheckBox("B");
		chckbxB.setBounds(144, 202, 97, 23);
		add(chckbxB);
		
		chckbxMixto = new JCheckBox("Mixto");
		chckbxMixto.setBounds(227, 176, 54, 23);
		add(chckbxMixto);
		
		chckbxFemenino = new JCheckBox("Femenino");
		chckbxFemenino.setBounds(144, 176, 97, 23);
		add(chckbxFemenino);
		
		chckbxNocturna = new JCheckBox("Nocturna");
		chckbxNocturna.setBounds(178, 50, 97, 23);
		add(chckbxNocturna);
		
		JLabel lblCriterio = new JLabel("Criterio");
		lblCriterio.setBounds(132, 27, 47, 16);
		add(lblCriterio);
		
		JLabel lblJornada = new JLabel("Jornada:");
		lblJornada.setBounds(6, 53, 61, 16);
		add(lblJornada);
		
		JLabel lblIcfes = new JLabel("Icfes:");
		lblIcfes.setBounds(6, 81, 61, 16);
		add(lblIcfes);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(6, 179, 61, 16);
		add(lblGenero);
		
		JLabel lblCalendario = new JLabel("Cal:");
		lblCalendario.setBounds(6, 207, 75, 16);
		add(lblCalendario);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(6, 235, 61, 16);
		add(lblTipo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 293, 274, 12);
		add(separator_1);
		
		JButton btnBuscarCrit = new JButton("Buscar");
		btnBuscarCrit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Criterio jornada = new Criterio();
					if(chckbxDiurn.isSelected()) jornada.agregarSubcriterio(Criterio.DIURNA);
					if(chckbxNocturna.isSelected()) jornada.agregarSubcriterio(Criterio.NOCTURNA);
				Criterio icfes = new Criterio();
					if(chckbxSuperior.isSelected()) icfes.agregarSubcriterio(Criterio.MUY_SUPERIOR);
					if(chckbxSuperior_1.isSelected()) icfes.agregarSubcriterio(Criterio.SUPERIOR);
					if(chckbxAlto.isSelected()) icfes.agregarSubcriterio(Criterio.ALTO);
					if(chckbxBajo.isSelected()) icfes.agregarSubcriterio(Criterio.BAJO);
					if(chckbxMedio.isSelected()) icfes.agregarSubcriterio(Criterio.MEDIO);
					if(chckbxInferior.isSelected()) icfes.agregarSubcriterio(Criterio.INFERIOR);
					if(chckbxInferior.isSelected()) icfes.agregarSubcriterio(Criterio.MUY_INFERIOR);
				Criterio genero = new Criterio();
					if(chckbxMasculino.isSelected()) genero.agregarSubcriterio(Criterio.MASCULINO);
					if(chckbxFemenino.isSelected()) genero.agregarSubcriterio(Criterio.FEMENINO);
					if(chckbxMixto.isSelected()) genero.agregarSubcriterio(Criterio.MIXTO);
				Criterio calendario = new Criterio();
					if(chckbxA.isSelected()) calendario.agregarSubcriterio(Criterio.CA);
					if(chckbxB.isSelected()) calendario.agregarSubcriterio(Criterio.CB);
					if(chckbxF.isSelected()) calendario.agregarSubcriterio(Criterio.CF);
				Criterio tipo = new Criterio();
					if(chckbxPrivado.isSelected()) tipo.agregarSubcriterio(Criterio.PRIVADO);
					if(chckbxPublico.isSelected()) tipo.agregarSubcriterio(Criterio.PUBLICO);
					
					try{
				Criterio[] criterios = {jornada,icfes,genero,calendario,tipo};
				padre.buscarPorCriterio(criterios);
					}catch(Exception e){
						e.printStackTrace();
					}
			}
		});
		btnBuscarCrit.setBounds(54, 263, 180, 29);
		add(btnBuscarCrit);
		
		JLabel lblAreaao = new JLabel("Area/A\u00F1o");
		lblAreaao.setBounds(118, 303, 61, 16);
		add(lblAreaao);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(6, 335, 61, 16);
		add(lblArea);
		
		JComboBox comboArea = new JComboBox();
		comboArea.setModel(new DefaultComboBoxModel(new String[] {"Sociales", "Matematicas", "Espa\u00F1ol"}));
		comboArea.setBounds(117, 331, 158, 27);
		add(comboArea);
		
		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setBounds(6, 363, 61, 16);
		add(lblAo);
		
		spinnerAnio = new JSpinner();
		SpinnerModel sm = new SpinnerNumberModel(2001,2001,2011,1);
		spinnerAnio.setBounds(122, 357, 153, 28);
		spinnerAnio.setModel(sm);
		add(spinnerAnio);
		
		JLabel lblPuntaje = new JLabel("Puntaje:");
		lblPuntaje.setBounds(6, 391, 61, 16);
		add(lblPuntaje);
		
		spinnerPuntaje = new JSpinner();
		spinnerPuntaje.setBounds(122, 385, 153, 28);
		add(spinnerPuntaje);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(54, 418, 180, 29);
		add(btnNewButton);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(6, 452, 274, 12);
		add(separator_3);
		
		JLabel lblUbicacin = new JLabel("Ubicaci\u00F3n");
		lblUbicacin.setBounds(118, 458, 75, 16);
		add(lblUbicacin);
		
		JLabel lblDepto = new JLabel("Depto:");
		lblDepto.setBounds(6, 490, 61, 16);
		add(lblDepto);
		
		comboDepto = new JComboBox();
		comboDepto.setModel(new DefaultComboBoxModel(new String[] {"Antioquia", "Otro"}));
		comboDepto.setBounds(122, 485, 153, 27);
		add(comboDepto);
		
		JLabel lblMunicipio = new JLabel("Municipio:");
		lblMunicipio.setBounds(6, 518, 75, 16);
		add(lblMunicipio);
		
		comboMunicipio = new JComboBox();
		comboMunicipio.setModel(new DefaultComboBoxModel(new String[] {"Bogota DC", "n1", "n2"}));
		comboMunicipio.setBounds(122, 513, 153, 27);
		add(comboMunicipio);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(54, 556, 180, 29);
		add(btnBuscar);
		
		chckbxDiurn = new JCheckBox("Diurna");
		chckbxDiurn.setBounds(66, 50, 97, 23);
		add(chckbxDiurn);
		
		chckbxSuperior = new JCheckBox("Muy superior");
		chckbxSuperior.setBounds(66, 81, 97, 23);
		add(chckbxSuperior);
		
		chckbxSuperior_1 = new JCheckBox("Superior");
		chckbxSuperior_1.setBounds(178, 81, 97, 23);
		add(chckbxSuperior_1);
		
		chckbxMedio = new JCheckBox("Medio");
		chckbxMedio.setBounds(178, 104, 97, 23);
		add(chckbxMedio);
		
		chckbxAlto = new JCheckBox("Alto");
		chckbxAlto.setBounds(66, 104, 97, 23);
		add(chckbxAlto);
		
		chckbxBajo = new JCheckBox("Bajo");
		chckbxBajo.setBounds(66, 126, 97, 23);
		add(chckbxBajo);
		
		chckbxInferior = new JCheckBox("Inferior");
		chckbxInferior.setBounds(178, 126, 97, 23);
		add(chckbxInferior);
		
		chckbxMuyInferior = new JCheckBox("Muy Inferior");
		chckbxMuyInferior.setBounds(66, 149, 97, 23);
		add(chckbxMuyInferior);
		
		chckbxMasculino = new JCheckBox("Masculino");
		chckbxMasculino.setBounds(66, 176, 97, 23);
		add(chckbxMasculino);
		
		chckbxA = new JCheckBox("A");
		chckbxA.setBounds(66, 202, 97, 23);
		add(chckbxA);
		
		chckbxPublico = new JCheckBox("Publico");
		chckbxPublico.setBounds(66, 230, 97, 23);
		add(chckbxPublico);
		padre = interfazCupiColegios;
	}
}
