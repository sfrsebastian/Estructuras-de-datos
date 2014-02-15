package interfaz;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import mundo.Categoria;

public class DialogoManejarCategorias extends JDialog {

	private InterfazCupiSoundBox padre;
	private JButton btnEliminarCat;
	private JButton btnAgregarCategoria;
	private JTextField txtNombreCat;
	
	private JList listaCategorias;
	
	public DialogoManejarCategorias(){
		setTitle("Manejar Categorias");
		setSize(500, 400);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Categorias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 6, 173, 366);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		listaCategorias = new JList();
		listaCategorias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(listaCategorias);
		scrollPane.setBounds(6, 23, 161, 337);
		panel.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Agregar/Eliminar Categorias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(191, 6, 303, 156);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(6, 39, 113, 16);
		panel_2.add(lblNombre);
		
		txtNombreCat = new JTextField();
		txtNombreCat.setBounds(86, 33, 211, 28);
		panel_2.add(txtNombreCat);
		txtNombreCat.setColumns(10);
		
		btnEliminarCat = new JButton("Eliminar Categoria Seleccionada");
		btnEliminarCat.setBounds(6, 97, 291, 29);
		panel_2.add(btnEliminarCat);
		
		btnAgregarCategoria = new JButton("Agregar Categoria");
		btnAgregarCategoria.setBounds(6, 67, 291, 29);
		panel_2.add(btnAgregarCategoria);
		btnAgregarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String categoria = txtNombreCat.getText();
					if(categoria.equals(""))
						mostrarError("Debe llenar el campo");
					else{
						padre.agregarCategoria(categoria);
						refrescarLista(padre.darCategorias());
					}
					
				}catch(Exception e1)
				{
					
				}
			}
		});
		btnEliminarCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Categoria categoriaEliminada = (Categoria) listaCategorias.getSelectedValue();
					padre.eliminarCategoria(categoriaEliminada);
					refrescarLista(padre.darCategorias());
					//May fail badly
				}catch(Exception e1){
					//e1.printStackTrace();
				}
			}
		});
	}
	
	public void refrescarLista(Object[] categorias){
		listaCategorias.removeAll();
		DefaultListModel model = new DefaultListModel();
		if(categorias.length != 0){
			for (int i = 0; i < categorias.length; i++) {
				Categoria categoria = (Categoria)categorias[i];
				model.addElement(categoria);
			}
		listaCategorias.setModel(model);
		}else{
			model.addElement("No hay categorias");
			listaCategorias.setModel(model);
		}
	}
	
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Hola", JOptionPane.ERROR_MESSAGE);
	}
	
	public void setPadre(InterfazCupiSoundBox interfaz){
		padre = interfaz;
		refrescarLista(padre.darCategorias());
	}
	
}
