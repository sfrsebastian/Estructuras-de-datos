package componenteSearch.interfaz;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import ArbolAVl.ArbolBinarioAVLOrdenado;
import ArbolBinOrdenado.ArbolBinarioOrdenado;
import componenteSearch.mundo.Categoria;
import componenteSearch.mundo.ComponenteSearch;
import componenteSearch.mundo.Recurso;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelVistaCategoria extends JPanel {
	
	private Categoria categoria;
	
	private ComponenteSearch mundo;
	
	private ComponenteSearchPanelCentral padre;
	
	private JList listaRecursos;
	
	private static PanelVistaCategoria self;
	private JTextField txtNombre;
	private JTextField txtDesc;
	
	public PanelVistaCategoria(ComponenteSearch componenteSearch,Categoria cat,ComponenteSearchPanelCentral componenteSearchPanelCentral){
		mundo = componenteSearch;
		categoria = cat;
		padre = componenteSearchPanelCentral;
		self = this;
		
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Vista Categoria", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		setSize(new Dimension(320, 423));
		setPreferredSize(new Dimension(320, 423));
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 36, 61, 16);
		add(lblNombre);
		
		txtNombre = new JTextField(categoria.getNombre());
		txtNombre.setBounds(116, 30, 185, 28);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(20, 77, 79, 16);
		add(lblDescripcion);
		
		txtDesc = new JTextField(categoria.getDescripcion());
		txtDesc.setBounds(116, 70, 185, 28);
		add(txtDesc);
		txtDesc.setColumns(10);
		
		listaRecursos = new JList();
		inicializarLista(listaRecursos);
		JScrollPane scrollPane = new JScrollPane(listaRecursos);
		scrollPane.setBounds(20, 135, 281, 198);
		add(scrollPane);
		
		JButton btnVerRecurso = new JButton("Ver Recurso");
		btnVerRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Recurso rec = (Recurso)listaRecursos.getSelectedValue();
					padre.cargarWebView(self, rec);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnVerRecurso.setBounds(20, 376, 117, 29);
		add(btnVerRecurso);
		
		JLabel lblRecursos = new JLabel("Recursos");
		lblRecursos.setBounds(126, 102, 61, 16);
		add(lblRecursos);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoria.setDescripcion(txtDesc.getText());
				categoria.setNombre(txtNombre.getText());
			}
		});
		btnGuardar.setBounds(184, 376, 117, 29);
		add(btnGuardar);
		
		JButton btnEliminarRecuso = new JButton("Eliminar Recuso");
		btnEliminarRecuso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Recurso rec = (Recurso)listaRecursos.getSelectedValue();
					categoria.eliminarRecurso(rec);
					inicializarLista(listaRecursos);
					revalidate();
					repaint();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnEliminarRecuso.setBounds(20, 345, 138, 29);
		add(btnEliminarRecuso);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					padre.cargarPanelCategoria(self);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnAtras.setBounds(184, 345, 117, 29);
		add(btnAtras);
	}

	private void inicializarLista(JList lista) {
		ArbolBinarioAVLOrdenado<Recurso> recs = categoria.getRecursos();
		DefaultListModel modelo = new DefaultListModel();
		if(recs.darPeso() > 0){
			Object[] recursos = recs.darArreglo();
			for (int i = 0; i < recursos.length; i++) {
				Recurso r = (Recurso)recursos[i];
				modelo.addElement(r);
			}
		}else{
			modelo.addElement("No hay recurso agregado");
		}
		lista.setModel(modelo);
		revalidate();
		repaint();
	}
}
