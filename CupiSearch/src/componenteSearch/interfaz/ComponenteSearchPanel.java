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
        n.getContentPane().add(nuevo);  
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
		mensaje = new JLabel("Ingrese el texto a decodificar");
		mensaje.setBounds(6, 18, 189, 31);
		texto = new JTextField();
		texto.setBounds(6, 47, 438, 31);
		comprimir = new JButton("Comprimir");
		comprimir.setBounds(132, 90, 189, 31);
		respuesta = new JTextField();
		respuesta.setBounds(6, 155, 438, 31);
		respuesta.setEditable(false);
		descomprimir = new JButton("Descomprimir");
		descomprimir.setBounds(132, 198, 189, 31);
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
		setLayout(null);
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
