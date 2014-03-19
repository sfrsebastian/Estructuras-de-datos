package componenteSearch.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CompresorHuffman.CompresorHuffman;
import CompresorHuffman.TextoComprimido;

import javax.swing.border.TitledBorder;

public class ComponenteSearchPanel extends JPanel {
	public static void main(String[] args) 
    {
        JFrame n = new JFrame();
        n.setMinimumSize(new Dimension(450, 650));
        n.setVisible(true);
        ComponenteSearchPanel nuevo = new ComponenteSearchPanel();
        nuevo.setVisible(true);
        n.add(nuevo);  
        n.setVisible(true);
    }
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	/**
	 * Clase principal del mundo
	 */
	//private ComponenteSearch componenteContactos;
	
	TextoComprimido comprimido;
	CompresorHuffman huffman;
	JLabel mensaje;
	JTextField texto;
	JButton comprimir;
	JTextField respuesta;
	JButton descomprimir;
	
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	
	public ComponenteSearchPanel(){//ComponenteSearch mundo) {
		setBorder(new TitledBorder("CupiSearch"));
		//componenteContactos = mundo;
		setLayout(new GridLayout(2,3));
		mensaje = new JLabel("Ingrese el texto a decodificar");
		texto = new JTextField();
		comprimir = new JButton("Comprimir");
		respuesta = new JTextField();
		respuesta.setEditable(false);
		descomprimir = new JButton("Descomprimir");
		comprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huffman = new CompresorHuffman(texto.getText());
				comprimido = huffman.comprimir();
				//JOptionPane.showMessageDialog("Se ha comprimido el texto", "mensaje",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		descomprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				respuesta.setText(comprimido.descomprimir());
			}
		});
		add(mensaje);
		add(texto);
		add(comprimir);
		add(respuesta);
		add(descomprimir);
	}
	
    //-----------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------

	public void terminar() {
		// TODO Auto-generated method stub
		
	}
}
