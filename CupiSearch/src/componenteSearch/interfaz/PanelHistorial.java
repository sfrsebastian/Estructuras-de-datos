package componenteSearch.interfaz;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import ArbolAVl.ArbolBinarioAVLOrdenado;
import componenteSearch.mundo.ComponenteSearch;
import componenteSearch.mundo.Exploracion;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelHistorial extends JPanel {
	
	private ComponenteSearch mundo;
	
	private ComponenteSearchPanelCentral padre;
	
	private PanelHistorial self;
	
	private JList listaExploraciones;
	
	public PanelHistorial(ComponenteSearch componenteSearch, ComponenteSearchPanelCentral componenteSearchPanelCentral){
		mundo = componenteSearch;
		padre = componenteSearchPanelCentral;
		self = this;
		
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Historial de Operaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		setSize(new Dimension(320, 423));
		setPreferredSize(new Dimension(320, 423));
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.cargarPanelHome(self);
			}
		});
		btnAtras.setBounds(17, 376, 285, 29);
		add(btnAtras);
		
		listaExploraciones = new JList();
		inicializarLista(listaExploraciones);
		JScrollPane scrollPane = new JScrollPane(listaExploraciones);
		scrollPane.setBounds(17, 69, 285, 295);
		add(scrollPane);
		
		JLabel lblHistorialDeOperaciones = new JLabel("Historial de Operaciones");
		lblHistorialDeOperaciones.setBounds(78, 41, 178, 16);
		add(lblHistorialDeOperaciones);
	}

	private void inicializarLista(JList listaExploraciones2) {
		ArbolBinarioAVLOrdenado<Exploracion> exploraciones = mundo.getExploraciones();
		DefaultListModel modelo = new DefaultListModel();
		
		if(exploraciones.darPeso() > 0){
			Object[] exps = exploraciones.darArreglo();
			for (int i = 0; i < exps.length; i++) {
				Exploracion e = (Exploracion)exps[i];
				modelo.addElement(e);
			}
		}else{
			modelo.addElement("No hay recursos");
		}
		listaExploraciones2.setModel(modelo);
	}

	
}
