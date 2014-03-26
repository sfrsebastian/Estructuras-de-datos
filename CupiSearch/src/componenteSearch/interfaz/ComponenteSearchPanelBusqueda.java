package componenteSearch.interfaz;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.SpinnerNumberModel;

import componenteSearch.mundo.ComponenteSearch;
import componenteSearch.mundo.Exploracion;
import componenteSearch.mundo.Recurso;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JSpinner;

public class ComponenteSearchPanelBusqueda extends JPanel {
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	private JTextField txtTermino;
	
	private ComponenteSearchPanelCentral padre;

	private ComponenteSearch mundo;
	private JComboBox comboFiltro;
	private JComboBox comboTipo;
	
	private JList listaResultados;
	
	private static ComponenteSearchPanelBusqueda self;
	
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	
	public ComponenteSearchPanelBusqueda(ComponenteSearchPanelCentral componenteSearchPanelCentral, ComponenteSearch componenteSearch){
		padre = componenteSearchPanelCentral;
		mundo = componenteSearch;
		self = this;
		
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		setSize(new Dimension(320, 423));
		setPreferredSize(new Dimension(320, 423));
		
		JButton btnCat = new JButton("Categorias");
		btnCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.cargarPanelCategoria(self);
			}
		});
		btnCat.setBounds(6, 364, 117, 53);
		add(btnCat);
		
		JButton btnBusq = new JButton("Busqueda");
		btnBusq.setEnabled(false);
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
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(124, 20, 61, 16);
		add(lblBuscar);
		
		txtTermino = new JTextField();
		txtTermino.setBounds(25, 48, 262, 28);
		add(txtTermino);
		txtTermino.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String termino = txtTermino.getText();
					String tipo = (String)comboFiltro.getSelectedItem();
					String filtro = (String)comboTipo.getSelectedItem();
					
					String criter = termino + ";" + filtro;
					String[] criters = criter.split(";");
					
					refrescarLista(mundo.buscarResultados(criters, 0, 0));
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(25, 75, 117, 58);
		add(btnBuscar);
		
		comboTipo = new JComboBox();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement(Recurso.IMAGEN);
		model.addElement(Recurso.TEXTO);
		comboTipo.setModel(model);
		comboTipo.setBounds(140, 76, 147, 27);
		add(comboTipo);
		
		listaResultados = new JList();
		listaResultados.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	System.out.println("Double cliked!");
		        	Recurso rec = (Recurso)listaResultados.getSelectedValue();
		        	padre.cargarWebView(self, rec);	
		        } else if (evt.getClickCount() == 3) {   // Triple-click
		            int index = list.locationToIndex(evt.getPoint());
		        }
		    }	
		});
		
		JScrollPane scrollPane = new JScrollPane(listaResultados);
		scrollPane.setBounds(25, 145, 262, 156);
		add(scrollPane);
		
		JComboBox comboAgregar = new JComboBox();
		comboAgregar.setBounds(25, 313, 117, 27);
		add(comboAgregar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(140, 312, 147, 29);
		add(btnAgregar);
		
		comboFiltro = new JComboBox();
		DefaultComboBoxModel<String> modelFiltro = new DefaultComboBoxModel<String>();
		modelFiltro.addElement("CONTIENE");
		modelFiltro.addElement("IGUAL");
		comboFiltro.setModel(modelFiltro);
		comboFiltro.setBounds(140, 106, 147, 27);
		add(comboFiltro);
		
		refrescarLista(null);
	}
	
	public void refrescarLista(Object[] objetos){
		listaResultados.removeAll();
		DefaultListModel modelo = new DefaultListModel();
		if(objetos != null){
			for (int i = 0; i < objetos.length; i++) {
				Recurso rec = (Recurso) objetos[i];
				modelo.addElement(rec);
			}
		}else{
			modelo.addElement("No hay resultados");
		}
		listaResultados.setModel(modelo);
	}
}
