package componenteSearch.interfaz;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import componenteSearch.mundo.Recurso;
import componenteSearch.mundo.Scraper;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PanelWebView extends JPanel {

	private ComponenteSearchPanelCentral padre;
	private JTextField txtUrl;
	private JTextField txtDescrip;
	
	private static PanelWebView self;
	
	public PanelWebView(Recurso recurso, ComponenteSearchPanelCentral papa){
		padre = papa;
		self = this;
		
		setBorder(new TitledBorder(null, "Vista Recurso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		setSize(new Dimension(320, 423));
		setPreferredSize(new Dimension(320, 423));
		
		JLabel lblTomadoDe = new JLabel("Tomado de:");
		lblTomadoDe.setBounds(16, 48, 113, 16);
		add(lblTomadoDe);
		
		txtUrl = new JTextField();
		txtUrl.setText(recurso.getUrl());
		txtUrl.setEditable(false);
		txtUrl.setBounds(16, 67, 286, 28);
		add(txtUrl);
		txtUrl.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(16, 107, 113, 16);
		add(lblDescripcion);
		
		txtDescrip = new JTextField();
		txtDescrip.setText(recurso.getDescripcion());
		txtDescrip.setEditable(false);
		txtDescrip.setColumns(10);
		txtDescrip.setBounds(16, 127, 286, 28);
		add(txtDescrip);
			
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
		lblNewLabel.setVerticalAlignment(JLabel.CENTER);
		if(recurso.getTipo().equals(Recurso.IMAGEN)){
			lblNewLabel.setIcon(new ImageIcon(descargarImagen(recurso)));
			JScrollPane scrollPane = new JScrollPane(lblNewLabel);
			scrollPane.setBounds(16, 167, 286, 211);
			add(scrollPane);
		}else{
			lblNewLabel.setText("<html>" + recurso.getDescripcion() + "</html>");
			lblNewLabel.setBounds(16, 167, 286, 211);
			lblNewLabel.setBackground(Color.white);
			lblNewLabel.setOpaque(true);
			add(lblNewLabel);
			repaint();
		}
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padre.cargarPanelHome(self);
			}
		});
		btnNewButton.setBounds(6, 388, 308, 29);
		add(btnNewButton);
	}
	
	private String descargarImagen(Recurso rec){
		try {
			return padre.descargarImagen(rec);
		} catch (IOException e) {
			System.out.println("error");
		}
		return "Error";
	}
}
